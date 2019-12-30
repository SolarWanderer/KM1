package Tests;

import com.codeborne.selenide.conditions.Value;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class CreateKd2018 extends Setup {
    String Kd2018 = "тест5";
    @Test(priority = 0)
    public void MoveToKdReestr() {
        GoToReestr("Договоры","Кредитные договоры");
        $$(".breadcrumb").findBy(text("Кредитные договоры")).shouldBe(exist);
    }
    @Test(priority = 1)
    public void Create2018() {
        $("[title='Создать']").shouldBe(exist).click();
        $("[ng-model='clientTypeId']").shouldBe(exist).selectOptionContainingText("Физическое лицо");
        $("[ng-click='next()']").click();
        $$("[ng-repeat='row in data.rows']").findBy(text("Богульников Максим Николаевич")).find(".iCheck-helper").click();
        $("#selectButton").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $("#loanNum").shouldBe(enabled).sendKeys(Kd2018);
        $("#loanKindId").selectOptionContainingText("Кредитная карта");
        $("#loanDate").sendKeys("06062016");
        $("#endDate").sendKeys("06062019");
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 2 из 15"));
        $("#loanType").selectOptionContainingText("Кредит");
        $("#loanPurpose").selectOptionContainingText("Кредитная карта");
        $("#bkiLoanPurpose").selectOptionContainingText("Кредитная карта");
        $("#loanCurrencyInp").sendKeys("643");
        $(".km-autocomplete-higtligth").shouldHave(text("643")).click();
        $("#issueLimit").sendKeys("200000");
        $("#issueLimitRub").sendKeys("200000");
        $("#next").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 3 из 15"));
        $("#cardNum").sendKeys("1234567890123456789");
        $("#reportDay").sendKeys("31");
        $("#gracePeriodDays").sendKeys("0");
        $("#billingPeriodDays").sendKeys("25");
        $("#minPaymentCalcTypeId").selectOptionContainingText("Фиксированная сумма");
        $("#minPaymentCalcValue[ng-show='data.creditCard.minPaymentCalcTypeId == 1']").sendKeys("15000");
        $("#minPaymentLimitValue").sendKeys("0");
        $("#activeGracePeriod").selectOptionContainingText("Нет");
        $("#lastReportDate").sendKeys("31072017");
        $("#currentMinPayment").sendKeys("15000");
        $("#minPaymentCalcDate").sendKeys("31072017");
        $("#minPaymentBalance").sendKeys("15000");
        $("#minPaymentTermsViolation").selectOptionContainingText("Нет");
        $("#arrearMonths").sendKeys("0");
        $("#gracePeriodStartDate").sendKeys("06062016");
        $("#gracePeriodTermsViolation").selectOptionContainingText("Нет");
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 4 из 15"));
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 5 из 15"));
        $("#next").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 6 из 15"));
        $("#repaySchemaKind").selectOptionContainingText("Свободная");
        $("#periodType").selectOptionContainingText("Нет");
        $("#periodLength").sendKeys("1");
        $("#paymentDay").sendKeys("25");
        $("#preferentialOdPeriodDays").sendKeys("0");
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 7 из 15"));
        try {
            $("[on-add='createAccount'] #picAdd").click();
            $("#accCategoryIdModal").shouldBe(enabled).selectOption("Ссудный счет");
        }
        catch (ElementNotFound e)
        {
            $("[on-add='createAccount'] #picAdd").click();
            $("#accCategoryIdModal").shouldBe(enabled).selectOption("Ссудный счет");
        }
        $("#accPlanNum[maxlength='5']").shouldHave(value("45508"));
        $("[ng-click='next()']").click();
        $("#openDate").clear();
        $("#openDate").sendKeys("06062016");
        $("#next").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $("#next").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 8 из 15"));
        $("#next").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 9 из 15"));
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 10 из 15"));
        $$(".form-group").findBy(text("КД включен в ПОС")).find(".iCheck-helper").click();
        $("#clientFinStateName").selectOptionContainingText("Хорошее");
        $("#debtManageGradeName").selectOptionContainingText("Хорошее");
        $("#qualityCategoryName").selectOptionContainingText("I категория качества");
        $("#qualityReserveRatio").sendKeys("0");
        $("#qualityDocNum2").sendKeys("555");
        $("#qualityStartDate2").sendKeys("06062016");
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 11 из 15"));
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 12 из 15"));
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 13 из 15"));
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 14 из 15"));
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 15 из 15"));
        $("#finish").click();
        $$("[ng-repeat='button in buttons']").findBy(text("Сохранить как черновик")).click();
        $$("[ng-repeat='row in data.rows']").findBy(text(Kd2018)).shouldBe(exist);

    }
   @Test(priority = 2)
   public void OpenKd(){
       $$("[ng-repeat='row in data.rows']").findBy(text(Kd2018)).find("[value='"+Kd2018+"']").click();
   }
   @Test(priority = 3)
   public void InputAmounts() {

       $(".navbar-navig").find(linkText("Входные суммы")).click();
       $(".navbar-navig").find(linkText("Входные суммы")).parent().shouldBe(attribute("class", "ng-scope active"));
       $("#operationDate").sendKeys("06062016");
       $$("[ng-repeat='res in dataDefault']").findBy(text("Ссудный счет")).find("[ng-model='res.amount']").clear();
       $$("[ng-repeat='res in dataDefault']").findBy(text("Ссудный счет")).find("[ng-model='res.amount']").sendKeys("188397.26");
       $("[ng-click='runDistributeInputAmounts()']").click();
       $("[ng-click='$ctrl.ok()']").shouldBe(enabled).click();
       ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
       $(".navbar-navig").find(linkText("История операций")).click();
       $(".navbar-navig").find(linkText("История операций")).parent().shouldBe(attribute("class", "ng-scope active"));
       $("[on-update='loadData(params)'] [title='Обновить']").click();
       $("[ng-repeat='row in data.rows']", 0).shouldHave
               (matchText("^[0-9]\\d*"), text("Разнесение входной суммы"), text("06.06.2016"),
                       text("Перенос начального остатка по счету"), text("Исполнена"));
   }
    @Test(priority = 4)
    public void AddRates() {
        $("#loanNum").shouldHave(value(Kd2018));
        $(".navbar-navig").find(linkText("Процентные ставки")).click();
        $(".navbar-navig").find(linkText("Процентные ставки")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("[on-add='addRates'] #picAdd").click();
        $("#rateTypeId").selectOptionContainingText("% за кредит (от остатка на ссудном счете)");
        $("#rateKindId").selectOptionContainingText("Фиксированная");
        $("#ratePayPeriodId").selectOptionContainingText("Год");
        $("#units").selectOptionContainingText("Проценты");
        $("#periodTypeId").selectOptionContainingText("Месяц");
        $("#periodLength").sendKeys("1");
        $("#paymentDay").sendKeys("31");
        $("#preferentialPeriodDays").sendKeys("0");
        $("#next").click();
        $("#stepWizard").shouldBe(matchesText("Шаг 2 из 2"));
        sleep(500);
        $("[on-add='showValue'] #picAdd").click();
        $("#effectiveFrom").clear();
        $("#effectiveFrom").sendKeys("06062016");
        $("#value").sendKeys("20");
        ClickAndWaitModal("#modalWindow [ng-click='save()']");
        $("#next").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        $(".navbar-navig").find(linkText("Процентные ставки")).click();
        $(".navbar-navig").find(linkText("Процентные ставки")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("[on-add='addRates'] #picAdd").click();
        $("#rateTypeId").selectOptionContainingText("% за кредит (от остатка просроченного основного долга)");
        $("#rateKindId").selectOptionContainingText("Фиксированная");
        $("#ratePayPeriodId").selectOptionContainingText("Год");
        $("#units").selectOptionContainingText("Проценты");
        $("#periodTypeId").selectOptionContainingText("Месяц");
        $("#periodLength").sendKeys("1");
        $("#paymentDay").sendKeys("31");
        $("#preferentialPeriodDays").sendKeys("0");
        $("#next").click();
        $("#stepWizard").shouldBe(matchesText("Шаг 2 из 2"));
        sleep(500);
        $("[on-add='showValue'] #picAdd").click();
        $("#effectiveFrom").clear();
        $("#effectiveFrom").sendKeys("06062016");
        $("#value").sendKeys("30");
        ClickAndWaitModal("#modalWindow [ng-click='save()']");
        $("#next").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        $(".navbar-navig").find(linkText("Процентные ставки")).click();
        $(".navbar-navig").find(linkText("Процентные ставки")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("[on-add='addRates'] #picAdd").click();
        $("#rateTypeId").selectOptionContainingText("Повышенные % за несвоевременное погашение минимального платежа по кредитным картам");
        $("#rateKindId").selectOptionContainingText("Фиксированная");
        $("#ratePayPeriodId").selectOptionContainingText("Год");
        $("#units").selectOptionContainingText("Проценты");
        $("#periodTypeId").selectOptionContainingText("Месяц");
        $("#periodLength").sendKeys("1");
        $("#paymentDay").sendKeys("31");
        $("#preferentialPeriodDays").sendKeys("0");
        $("#next").click();
        $("#stepWizard").shouldBe(matchesText("Шаг 2 из 2"));
        sleep(500);
        $("[on-add='showValue'] #picAdd").click();
        $("#effectiveFrom").clear();
        $("#effectiveFrom").sendKeys("06062016");
        $("#value").sendKeys("30");
        ClickAndWaitModal("#modalWindow [ng-click='save()']");
        $("#next").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        $$("#rates [ng-repeat='item in items']").shouldHaveSize(3);


    }
    @Test(priority = 5)
    public void AddAccounts() {
        $(".navbar-navig").find(linkText("Счета")).click();
        $(".navbar-navig").find(linkText("Счета")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("#accounts #picAdd").click();
        $("#accCategoryIdModal").shouldBe(enabled).selectOption("Баланс - Требования банка по получению процентов");
    $("#accPlanNum[maxlength='5']").shouldHave(value("47427"));
    $("[ng-click='next()']").click();
    $("#openDate").clear();
    $("#openDate").sendKeys("06062016");
    $("#next").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $("#loanNum").shouldHave(value(Kd2018));

        $(".navbar-navig").find(linkText("Счета")).click();
        $(".navbar-navig").find(linkText("Счета")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("#accounts #picAdd").click();
        $("#accCategoryIdModal").shouldBe(enabled).selectOption("Внебаланс - Требования банка по получению процентов");
        $("#accPlanNum[maxlength='5']").shouldHave(value("91604"));
        $("[ng-click='next()']").click();
        $("#openDate").clear();
        $("#openDate").sendKeys("06062016");
        $("#next").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $("#loanNum").shouldHave(value(Kd2018));

        $(".navbar-navig").find(linkText("Счета")).click();
        $(".navbar-navig").find(linkText("Счета")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("#accounts #picAdd").click();
        $("#accCategoryIdModal").shouldBe(enabled).selectOption("Счет учета погашений кредита в руб");
        $("#accPlanNum[maxlength='5']").shouldHave(value("47422"));
        $("[ng-click='next()']").click();
        $("#openDate").clear();
        $("#openDate").sendKeys("06062016");
        $("#next").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $("#loanNum").shouldHave(value(Kd2018));
        $$("#accounts [ng-repeat='item in items']").shouldHaveSize(4);
    }
    @Test(priority = 6)
    public void AddGraph() {
        $(".navbar-navig").find(linkText("Графики")).click();
        $(".navbar-navig").find(linkText("Графики")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("#graphs #picAdd").click();
        $("#graphTypeId").selectOption("График минимальных платежей");
        $("#startDate").sendKeys("06062016");
        $("#next").click();
      $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 2 из 3"));
        $("#planEndDate").clear();
        $("#planEndDate").sendKeys("31072017");
        $("#paymentDay").clear();
        $("#paymentDay").sendKeys("25");
        $("#periodTypeId").selectOption("Месяц");
        $("#dateCorrTypeId").selectOption("Не сдвигать");
        $("#next").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 3 из 3"));
        sleep(500);
        $("#paymentGraph #picAdd").click();
        $("#paymentDate").sendKeys("25082017");
        $("#calcStartDate").sendKeys("01072017");
        $("#calcEndDate").sendKeys("31072017");
        $("#amount").sendKeys("15000");
        $("#balance").sendKeys("15000");
        ClickAndWaitModal("[ng-click='save()']");
        $("[ng-repeat='row in data.rows']",0).shouldHave(
                text("25.08.2017"),text("01.07.2017"),text("31.07.2017"),text("15 000.00")
        ,text("Запланирован"), text("15 000.00"));
        $("#next").click();
        $("#loanNum").shouldHave(value(Kd2018));
    }
    @Test(priority = 7)
    public void Finish2018() {
        $(".cardKD-page").click();
        $(linkText("Редактирование КД")).shouldBe(exist).click();
        $(linkText("Поставить на учет")).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $("#accountsSetId").shouldHave(text("Кредиты, выданные Гражданам (физическим лицам)"));
    }
}
