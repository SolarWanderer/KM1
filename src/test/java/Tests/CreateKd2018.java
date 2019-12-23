package Tests;

import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.By.linkText;

public class CreateKd2018 extends Setup {
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
        $("#loanNum").shouldBe(enabled).sendKeys("КК-"+randomString("123456789",4));
        $("#loanKindId").selectOptionContainingText("Кредитная карта");
        $("#loanDate").sendKeys("06062016");
        $("#endDate").sendKeys("06062019");
        $("[ng-click='next()']").click();
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 2 из 15"));
        $("#loanType").selectOptionContainingText("Кредит");
        $("#loanPurpose").selectOptionContainingText("Кредитная карта");
        $("#bkiLoanPurpose").selectOptionContainingText("Кредитная карта");
        $("#loanCurrencyInp").sendKeys("643");
        $(".km-autocomplete-higtligth").shouldHave(text("643")).click();
        $("#issueLimit").sendKeys("200000");
        $("#issueLimitRub").sendKeys("200000");

        $("[ng-click='next()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(".fixed-wizard .page-number").shouldBe(matchesText("Шаг 3 из 15"));
        $("#cardNum").sendKeys("1234567890123456789");
        $("#reportDay").sendKeys("31");
        $("#gracePeriodDays").sendKeys("0");
        $("#billingPeriodDays").sendKeys("25");
        $("#minPaymentCalcTypeId").selectOptionContainingText("Фиксированная сумма");



    }

}
