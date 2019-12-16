package Tests;
import com.codeborne.selenide.commands.ShouldHave;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class Sudebka extends Setup {
    @Test(priority = 0)
    public void MoveToKdReestr() {
        String txt=$(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).getAttribute("class");
        if (txt.equals("ng-scope"))
        {
            $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Кредитные договоры']")).click();

    }
    @Test(priority = 1)
    public void prosrocka1646() {
    $(By.xpath(".//div[@value='ДКА-1646']")).shouldBe(exist, enabled).click();
        try {
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
    } catch (
    ElementNotFound ex) {
        $(By.xpath(".//div[@value='ДКА-1646']")).shouldBe(exist, enabled).click();
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
    }

        //1
        $(By.linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("16082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //2
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("16092017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //3
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("16102017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //4
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("16112017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Проверки
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        $$("[data='operations'] [ng-repeat='row in data.rows']").filterBy(text("Начисление процентов")).shouldHaveSize(7);
        $$("[data='operations'] [ng-repeat='row in data.rows']").filterBy(text("Вынесение на просрочку")).shouldHaveSize(4);
        $$("[data='operations'] [ng-repeat='row in data.rows']").filterBy(text("Начисление неустоек")).shouldHaveSize(3);


    }
    @Test(priority = 2)
    public void Setting1646Client() {
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Общая информация']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Общая информация']]")).shouldBe(attribute("class", "ng-scope active"));
        //$("[ng-click='goToClient()']").shouldBe(enabled).click();
        try {
            $("[ng-click='goToClient()']").shouldBe(enabled).click();
            $("#main [ng-click='edit()']").shouldBe(exist,enabled);

        }
        catch (ElementNotFound ex)
        {$("[ng-click='goToClient()']").shouldBe(enabled).click();
            $("#main [ng-click='edit()']").shouldBe(exist,enabled);
        }
        $("#main [ng-click='edit()']").shouldBe(exist,enabled).click();
        $$(".form-group").findBy(text("Банкротство (ликвидация)")).find(By.cssSelector(".iCheck-helper")).click();
        //$$(".form-group").findBy(text("Банкротство (ликвидация)")).find(By.cssSelector(".iCheck-helper")).click();
       // $("#isBankRupt").shouldBe(enabled).find(".iCheck-helper").click();
        $("#clientProcedureTypeId").selectOptionContainingText("Конкурсное производство");
        $("#caseNum").sendKeys("001");
        $$(".form-group").findBy(text("Включен в реестр требований")).find(By.cssSelector(".iCheck-helper")).click();
        $("#registerCloseDate").sendKeys("31122017");
        $("#caseNum").click();
        $("#demandsAmount").sendKeys("700000");
        $$(".form-group").findBy(text("Залоговый кредитор")).find(By.cssSelector(".iCheck-helper")).click();
        $("[ng-click='openOperationDayModalCalendar()']").scrollIntoView(true);
        $("#main #picSave").click();



        try{
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКА-1646 ФЛ Шмаков Виталий Се']")).click();
            $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Параметры договора']]")).shouldBe(exist);
        }
        catch (ElementNotFound e)
        {
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКА-1646 ФЛ Шмаков Виталий Се']")).click();
            $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Параметры договора']]")).shouldBe(exist);
        }
    }

    @Test(priority = 3)
    public void Parametrs1646() {
        $(By.linkText("Редактирование КД")).click();
        $(By.linkText("Взять на редактирование")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        //TODO Махинации в параметрах договора

    }
    @Test(priority = 4)
    public void CreateWork1646() {
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Судебная работа']]")).scrollIntoView(true).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Судебная работа']]")).shouldBe(attribute("class", "ng-scope active"));
        //TODO Загрузка файлика
        //Создание судебной работы
        $("[buttons='buttomsJudicialWork'] #picAdd").click();
        $("#caseNum").shouldBe(exist).sendKeys("526-12/2017");
        $("#defendant").sendKeys("Шмаков Виталий Сергеевич");
        $("#caseApplicationDate").sendKeys("17112017");
        $("#proceedingsStartDate").sendKeys("20112017");
        $("#courtDecisionDate").sendKeys("16122017");
        $("#actTakingEffectDate").sendKeys("18122017");
        $("#courtWorkStageId").selectOptionContainingText("Суд первой инстанции");
        $("#courtWorkStateId").selectOptionContainingText("Закрыто");
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 2 из 3"));
        sleep(1000);
        $("#requirements #picAdd").shouldBe(enabled).click();
        $("#demandName").shouldBe(exist).sendKeys("Просроченный Основной долг");
        $("#demandAmount").sendKeys("566666.66");
        $("#processualResultId").selectOptionContainingText("Удовлетворено");
        $("#resultAmount").sendKeys("566666.66");
        ClickAndWaitModal("#modalWindow [ng-click='save()']");

        $("#requirements #picAdd").shouldBe(enabled).click();
        $("#demandName").shouldBe(exist).sendKeys("Просроченные проценты ");
        $("#demandAmount").sendKeys("32214.60");
        $("#processualResultId").selectOptionContainingText("Удовлетворено");
        $("#resultAmount").sendKeys("32214.60");
        ClickAndWaitModal("#modalWindow [ng-click='save()']");

        $("#requirements #picAdd").shouldBe(enabled).click();
        $("#demandName").shouldBe(exist).sendKeys("Неустойка на просроченный ОД");
        $("#demandAmount").sendKeys("25709.59");
        $("#processualResultId").selectOptionContainingText("Удовлетворено");
        $("#resultAmount").sendKeys("25709.59");
        ClickAndWaitModal("#modalWindow [ng-click='save()']");

        $("#requirements #picAdd").shouldBe(enabled).click();
        $("#demandName").shouldBe(exist).sendKeys("Неустойка на просроченные проценты");
        $("#demandAmount").sendKeys("1374.81");
        $("#processualResultId").selectOptionContainingText("Отказано в удовлетворении");
        $("#resultAmount").sendKeys("0.00");
        ClickAndWaitModal("#modalWindow [ng-click='save()']");

        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 3 из 3"));
        sleep(1000);
        $("#proceeding #picAdd").shouldBe(enabled).click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 1 из 1"));
        $("#enforcementCaseNum").sendKeys("85978/78466-И");
        $("#startDate").sendKeys("19122017");
        $("#enforcementDoc").sendKeys("Исполнительный лист ФС1256");
        $("#outputDate").sendKeys("20122017");
        $("#presentationDate").sendKeys("20122017");
        $("#debtor").sendKeys("Шмаков Виталий Сергеевич");
        $("#enforcementStateId").selectOptionContainingText("Открыто");
        $("#status").sendKeys("Исп. лист предъявлен");
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 3 из 3"));
        $("[ng-click='next()'][type='button']").click();

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Судебная работа']]")).scrollIntoView(true).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Судебная работа']]")).shouldBe(attribute("class", "ng-scope active"));
        $("[items='judicialWorks'] [ng-repeat='item in items']",0).shouldHave(
                text("526-12/2017"),text("Шмаков Виталий Сергеевич"),text("17.11.2017"),text("20.11.2017")
                ,text("16.12.2017"),text("18.12.2017"),text("Суд первой инстанции"), text("Закрыто")
                ,text("625 965.66"),text("624 590.85")
        );

    }
   @Test(priority = 5)
   public void Isparav1646(){
       $(By.linkText("Сопровождение КД")).click();
       $(By.linkText("Регистры")).scrollIntoView(true).click();
       $$("[ng-repeat='node in item.value']").shouldHaveSize(109);
       try {
           $(By.linkText("Требования по неустойке по просроченным % на просроченный ОД - начисление в КМ")).click();
           $(By.linkText("Операции")).shouldBe(exist).click();
       }
       catch (ElementNotFound ex)
       {
           $(By.linkText("Требования по неустойке по просроченным % на просроченный ОД - начисление в КМ")).click();
           $(By.linkText("Операции")).shouldBe(exist).click();
       }

       $(By.linkText("Исправительная операция")).click();
       $("#execDate").shouldBe(exist).clear();
       $("#execDate").sendKeys("18122017");
       $("#amountSpending").clear();
       $("#amountSpending").sendKeys("287.18");
       $("#reason").sendKeys("Корректировка задолженности на основании судебного решения");
       $("#next").click();
       $("[ng-repeat='row in data.rows']",0).shouldHave(
               text("18.12.2017"),text("0.00"),text("287.18"),text("0.00"),text("RUB"),text("0.00")
       );
       $(".breadcrumb").find(By.linkText("Регистры учета")).click();
       $$("[ng-repeat='node in item.value']").shouldHaveSize(109);

       try {
           $(By.linkText("Требования по неустойке по просроченным % - начисление в КМ")).click();
           $(By.linkText("Операции")).shouldBe(exist).click();
       }
       catch (ElementNotFound ex)
       {
           $(By.linkText("Требования по неустойке по просроченным % - начисление в КМ")).click();
           $(By.linkText("Операции")).shouldBe(exist).click();
       }
       $(By.linkText("Исправительная операция")).click();
       $("#execDate").shouldBe(exist).clear();
       $("#execDate").sendKeys("18122017");
       $("#amountSpending").clear();
       $("#amountSpending").sendKeys("1087.63");
       $("#reason").sendKeys("Корректировка задолженности на основании судебного решения");
       $("#next").click();
       $("[ng-repeat='row in data.rows']",0).shouldHave(
               text("18.12.2017"),text("0.00"),text("1 087.63"),text("0.00"),text("RUB"),text("0.00")
       );
       $(".breadcrumb").find(By.linkText("КД №ДКА-1646 ФЛ Шмаков Виталий Се")).click();
       //$$("[ng-repeat='item in regTree track by $index']").findBy(matchesText("Требования по неустойке по просроченным % на просроченный ОД - начисление в КМ")).find(By.linkText(""))

   }


    @Test(priority = 6)
    public void AddAccounts(){
        $(By.linkText("Редактирование КД")).shouldBe(exist).click();
        $(By.linkText("Поставить на учет")).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $("[ng-click='openOperationDayModalCalendar()']").click();
        $("#operationDayDate").clear();
        $("#operationDayDate").sendKeys("11082017");
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Счета']]")).scrollIntoView(true).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Счета']]")).shouldBe(attribute("class", "ng-scope active"));
        $("#accounts #picAdd").shouldBe(enabled).click();
        $("input#accPlanNum").shouldBe(exist).sendKeys("61212");
        $("#page-wrapper").click();
        $("#accCategoryIdModal").selectOptionContainingText("Выбытие, погашение имущества (в т.ч. приобретенных прав требований)");
        $("[ng-click='next()']").click();
        $("#accPlanNum").shouldHave(value("61212"));
        $("[ng-click='next()']").click();

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Счета']]")).scrollIntoView(true).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Счета']]")).shouldBe(attribute("class", "ng-scope active"));
        $("#accounts #picAdd").shouldBe(enabled).click();
        $("#accCategoryIdModal").shouldBe(exist).selectOptionContainingText("Задолженность по ОД, списанная за счет резервов на возможные потери по кредитам");
        $("input#accPlanNum").shouldHave(value("91802"));
        $("[ng-click='next()']").click();
        $("#accPlanNum").shouldHave(value("91802"));
        $("[ng-click='next()']").click();

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Счета']]")).scrollIntoView(true).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Счета']]")).shouldBe(attribute("class", "ng-scope active"));
        $("#accounts #picAdd").shouldBe(enabled).click();
        $("#accCategoryIdModal").shouldBe(exist).selectOptionContainingText("Неполученные проценты по кредитам, предоставленным клиентам, списанным с баланса кредитной организации");
        $("input#accPlanNum").shouldHave(value("91704"));
        $("[ng-click='next()']").click();
        $("#accPlanNum").shouldHave(value("91704"));
        $("[ng-click='next()']").click();




    }
    @Test(priority = 7)
    public void Operations1646(){
        $(By.linkText("Сопровождение КД")).shouldBe(exist).click();
        $(By.linkText("Списать задолженность за счет резерва")).scrollIntoView(true).click();
        $("#field").clear();
        $("#field").sendKeys("25122017");
        $("[ng-click='ok()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.linkText("Сопровождение КД")).shouldBe(exist).click();
        $(By.linkText("Списать безнадежную задолженность с внебаланса")).scrollIntoView(true).click();
        $("#field").clear();
        $("#field").sendKeys("26122017");
        $("[ng-click='ok()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(".breadcrumb").find(By.linkText("Кредитные договоры")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        $("[data='operations'] [ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Списание безнадежной задолженности с внебаланса"),
                        text("26.12.2017"), text("Списание безнадежной задолженности с внебаланса в 26.12.2017"),
                        text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(2);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Списание безнадежной задолженности по просроченному ОД с внебаланса"), text("566 666.66"), text("RUB"), text("566 666.66"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("2"), text("Списание безнадежной задолженности по просроченным процентам с внебаланса"), text("6 054.79"), text("RUB"), text("6 054.79"));

    }
}
