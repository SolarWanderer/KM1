package Tests;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.By.linkText;

public class Rezerv_RVI extends Setup {
    @Test(priority = 0)
    public void MoveToKdReestr() {
        GoToReestr("Договоры","Кредитные договоры");
        $$(".breadcrumb").findBy(text("Кредитные договоры")).shouldBe(exist);


    }

    @Test(priority = 1)
    public void Rezerv1646() {
        $(By.xpath(".//div[@value='ДКА-1646']")).shouldBe(exist, enabled).click();
       // $(By.xpath(".//div[@value='ДКА-1646']")).shouldBe(exist, enabled).click();
        try {
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();;
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ДКА-1646']")).shouldBe(exist, enabled).click();
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        //Изменение категории качества
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Взять на редактирование']")).click();
      //  $(By.xpath("//DIV[@class='modal-header']/..//BUTTON[@type='button'][text()='Да']")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Категория качества']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Категория качества']]")).shouldBe(attribute("class","ng-scope active"));
        $("#qualityCategory .fa-pencil-square-o").click();
        $("#startDate").shouldHave(value("16.05.2017"));
        sleep(3000);
        $("#reserveRatio").sendKeys("100");
        $("#docNum").sendKeys("Распоряжение от 16.08.2017");
        $("#debtManageGradeName").should(exist,visible).selectOption("Хорошее");
        $("#startDate").shouldBe(enabled,visible).clear();
        $("#startDate").sendKeys("16082017");
        $("#qualityCategoryId").selectOption("V категория качества");

        $("[ng-click='change()']").click();
      //  $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Поставить на учет']")).click();
       // $(By.xpath("//*[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        //Начисление резерва
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Начислить резервы")).click();
        $("#modalWindow #operationDate").shouldBe(exist).clear();
        $("#modalWindow #operationDate").sendKeys("16082017");
        $(".modal-title").click();
        $(By.xpath("//P[text()='Выбрать актив']/..//INS[@class='iCheck-helper']")).click();
        $("#assetAccCategoryId").shouldBe(visible).selectOptionContainingText("Ссудный счет");
        $("#restAmount").shouldHave(value("566 666.66"));
        $("#amountReserve").sendKeys("500000");
        $("[ng-click='accrual()']").click();
        //$(By.xpath("//*[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        //Проверка начисления резерва
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        System.out.println($("[data='operations'] [ng-repeat='row in data.rows']", 0).getText());
        $("[data='operations'] [ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Начисление суммы резерва"), text("16.08.2017"), text("Начисление суммы резерва"), text("Исполнена")).find(By.cssSelector("a")).click();
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("10"), text("Начисление суммы резерва по текущему ОД"), text("Резерв по текущему ОД"), text("459 861.11"), text("RUB"), text("459 861.11"));
        $$(".breadcrumb").findBy(text("Кредитные договоры")).find(linkText("Кредитные договоры")).click();
    }
    @Test(priority = 2)
    public void RezervMass(){
        $(By.xpath(".//div[@value='ДКА-2400']")).shouldBe(exist);
        $(By.xpath("//A[text()='ДКИ-2373']/../../..//ins[@class='iCheck-helper']")).click();
        $(By.xpath("//A[text()='ДКА-2400']/../../..//ins[@class='iCheck-helper']")).click();
        $(By.xpath("//A[text()='ДКА-2381']/../../..//ins[@class='iCheck-helper']")).click();
        $(linkText("Операции")).click();
        $(linkText("Начислить резервы")).click();
        $("#modalWindow #operationDate").shouldBe(exist).clear();
        $("#modalWindow #operationDate").sendKeys("26092017");
        $(".modal-title").click();
        $("#standartReserve").sendKeys("100");
        $("[ng-click='accrual()']").click();
       // $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    }
    @Test(priority = 3)
    public void CreateProduct(){
        GoToReestr("Договоры", "Кредитные продукты");
        $$(".breadcrumb").findBy(text("Кредитные продукты")).shouldBe(exist);
        $("[ng-bind-html='config.icon'] .fa-plus").shouldBe(exist).click();
        $("#code").shouldBe(enabled,visible).sendKeys(product);
        $("#name").sendKeys(product);
        $("#productGroupId").selectOptionContainingText("ФЛ Ипотека");
        $("#loanCategoryId").selectOptionContainingText("Ипотечные ссуды, предоставленные физическим лицам");
        $("#loanPurposeId").selectOptionContainingText("Приобретение недвижимости");
        $("#securityKindId").selectOptionContainingText("Залог недвижимости");
        $("#clientTypeId").selectOptionContainingText("Физическое лицо");
        $("#resident").selectOptionContainingText("Резидент");
        $("#currencyId").selectOptionContainingText("Рублевый");
        $("#startDate").clear();
        $("#startDate").sendKeys("11082017");
        $(".iCheck-helper").click();
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 2 из 4"));
        $("#partPayTypeId").selectOptionContainingText("ЧДП с уменьшением ежемесячного платежа");
        $("#restActionId").selectOptionContainingText("Остается на счете");
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 3 из 4"));
        //TODO: Проверить раздел Очередность погашения
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 4 из 4"));
        sleep(300);
        $("#rviPayments #picAdd").shouldBe(enabled).click();
        $(".modal-body #startDate").shouldBe(exist,enabled).sendKeys("01012017");
        $(".modal-header").click();
        $("#amount").sendKeys("19000");
        $("#modalWindow [ng-click='save()']").click();
        //$("[style='z-index: 1050; display: block;'][tabindex='-1']").shouldBe(exist);
        //sleep(1000);
        //$("#massageWindow [ng-click='$ctrl.cancel()']").shouldBe(visible).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

        $("#rviPayments #picAdd").click();
        $(".modal-body #startDate").shouldBe(exist,enabled).sendKeys("01012018");
        $(".modal-header").click();
        $("#amount").sendKeys("20000");
        $("#modalWindow [ng-click='save()']").click();
        //$("[style='z-index: 1050; display: block;'][tabindex='-1']").shouldBe(exist);
       // sleep(1000);
       // $("#massageWindow [ng-click='$ctrl.cancel()']").shouldBe(visible).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

        $("#rviPayments #picAdd").click();
        $(".modal-body #startDate").shouldBe(exist,enabled).sendKeys("01012019");
        $(".modal-header").click();
        $("#amount").sendKeys("21000");
        $("#modalWindow [ng-click='save()']").click();
        $("[style='z-index: 1050; display: block;'][tabindex='-1']").shouldBe(exist);
      //  sleep(1000);
        //$("#massageWindow [ng-click='$ctrl.cancel()']").shouldBe(visible).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

        $("#rviPayments #picAdd").click();
        $(".modal-body #startDate").shouldBe(exist,enabled).sendKeys("01012020");
        $(".modal-header").click();
        $("#amount").sendKeys("22000");
        $("#modalWindow [ng-click='save()']").click();
       // $("[style='z-index: 1050; display: block;'][tabindex='-1']").shouldBe(exist);
       // sleep(1000);
      //  $("#massageWindow [ng-click='$ctrl.cancel()']").shouldBe(visible).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $("[ng-click='next()'][type='button']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.xpath(".//div[@value='"+product+"']")).shouldBe(exist);
        GoToReestr("Договоры","Кредитные договоры");
        $$(".breadcrumb").findBy(text("Кредитные договоры")).shouldBe(exist);

       // $("[type='button'][ng-click='next()']").click();


    }
    @Test(priority = 4)
    public void ADDProductToKD(){
        $(By.xpath(".//div[@value='ВИ-5009']")).shouldBe(exist);
        $(By.xpath("//A[text()='ВИ-5009']/../../..//ins[@class='iCheck-helper']")).click();
        //this.actions.moveToElement($(By.xpath("//A[text()='ДКА-2484']/../../..//ins[@class='iCheck-helper']"))).perform();
        $(By.xpath("//A[text()='ВИ-1602']/../../..//ins[@class='iCheck-helper']")).click();
        // this.actions.moveToElement($(By.xpath("//A[text()='ГС-555']/../../..//ins[@class='iCheck-helper']"))).perform();
        $(linkText("Операции")).click();
        $(linkText("Взять на редактирование")).click();
        $(By.xpath("//div[@class='modal-content']//button[text()='Да']")).shouldBe(exist).click();
        //sleep(1000);
       // $(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(linkText("Операции")).click();
        $(linkText("Указать кредитный продукт")).click();
        $(By.xpath("//select[@ng-model='productIdModal']")).shouldBe(exist).selectOptionContainingText(product);
        $(".modal-dialog [ng-click='next()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        $(linkText("Операции")).click();
        $(linkText("Поставить на учет")).click();
       // $(By.xpath("//div[@class='modal-content']//button[text()='Да']")).shouldBe(exist).click();
        //sleep(1000);
        //$(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    }
    @Test(priority = 5)
    public void RVIGraph(){
        GoToReestr("Договоры", "Кредитные продукты");
        $$(".breadcrumb").findBy(text("Кредитные продукты")).shouldBe(exist);
        $(By.xpath(".//div[@value='"+product+"']")).shouldBe(exist).click();
        $(linkText("Операции")).shouldBe(exist).click();
        $(linkText("Сформировать графики платежей РВИ")).click();
        $("#modalWindow #startDate").sendKeys("11082017");
        $(".modal-header").click();
        $("#modalWindow #endDate").sendKeys("31122019");
        $("[ng-click='calc()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        GoToReestr("Договоры","Кредитные договоры");
        $$(".breadcrumb").findBy(text("Кредитные договоры")).shouldBe(exist);

    }
    @Test(priority = 6)
    public void Operations5009(){
        $(By.xpath(".//div[@value='ВИ-5009']")).shouldBe(exist, enabled).click();
        try {
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ВИ-5009']")).shouldBe(exist, enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();

        }
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000005009");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("31082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        // Капитализация
        $("[data='operations'] [ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Капитализация процентов"), text("31.08.2017"), text("Капитализация процентов с датой планового погашения 31.08.2017"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(1);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Капитализация процентов"), text("Капитализированные проценты по ОД"), text("Требования по % по ОД-Внебаланс"), text("7 220.76"), text("RUB"), text("7 220.76"));

        $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ВИ-5009 ФЛ Гаевская Ирина Ал']")).click();

        //Погашение
        try{
            $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        }
        catch (ElementNotFound e)
        {
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ВИ-5009 ФЛ Гаевская Ирина Ал']")).click();
            $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        }
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        $("[data='operations'] [ng-repeat='row in data.rows']", 1).shouldHave
                (matchText("^[0-9]\\d*"), text("Погашение задолженностей"), text("31.08.2017"), text("Погашение текущей задолженности за период с 11.08.2017 по 31.08.2017"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(2);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("2"), text("Погашение текущих процентов (баланс)"), text("Требования по % по ОД-Баланс"), text("9 304.14"), text("RUB"), text("9 304.14"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("3"), text("Погашение текущих процентов (внебаланс)"), text("Требования по % по ОД-Внебаланс"), text("9 695.86"), text("RUB"), text("9 695.86"));
        $$(".breadcrumb").findBy(text("Кредитные договоры")).find(linkText("Кредитные договоры")).click();
    }

}
