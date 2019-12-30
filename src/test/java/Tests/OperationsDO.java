package Tests;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.linkText;

public class OperationsDO extends Setup {
    @Test(priority = 0)
    public void DZA2381() {
        GoToReestr("Договоры", "Договоры обеспечения");
        $$("[ng-repeat='row in data.rows']").findBy(text("ДЗА-2381")).find("[value='ДЗА-2381']").click();
        $(By.linkText("Сопровождение ДО")).click();
        $(By.linkText("Переоценить объект залога")).click();
        $("#reassessmentReason").sendKeys("Продажа");
        $("#assessmentCompany").sendKeys("ООО «Оценка плюс»");
        $("#assessmentDate").sendKeys("16112017");
        $("#assessedValue").sendKeys("700000");
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 2 из 2"));
        $("#pledgeValueChangeDate").sendKeys("16112017");
        $("#next").click();
        $("[ng-click='$ctrl.ok()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(".navbar-navig").find(linkText("История операций")).click();
        $(".navbar-navig").find(linkText("История операций")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("[ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Уменьшение суммы обеспечения"), text("16.11.2017"), text("Уменьшение суммы обеспечения"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(1);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Уменьшение суммы обеспечения с отражением в бухгалтерском учете"), text("Сумма обеспечения"), text("100 000.00"), text("RUB"), text("100 000.00"));


    }
    @Test(priority = 1)
    public void DZA1646() {
        GoToReestr("Договоры", "Договоры обеспечения");
        $$("[ng-repeat='row in data.rows']").findBy(text("ДЗA-1646")).find("[value='ДЗA-1646']").click();
        $(By.linkText("Сопровождение ДО")).click();
        $(By.linkText("Списать сумму обеспечения")).click();
        $("#entryDate").clear();
        $("#entryDate").sendKeys("16112017");
        $("[ng-click='writeoff()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.linkText("Сопровождение ДО")).click();
        $(By.linkText("Списать ценности")).click();
        $("#entryDate").clear();
        $("#entryDate").sendKeys("16112017");
        $("[ng-click='writeoff()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(".navbar-navig").find(linkText("История операций")).click();
        $(".navbar-navig").find(linkText("История операций")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("[ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Списание ценностей"), text("16.11.2017"),
                        text("Списание ценностей"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(1);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Списание ценностей, кроме ц.б., принятых по договору обеспечения в хранилище"), text("Принятое имущество в хранилище по ДО ( ПТС)"), text("1.00"), text("RUB"), text("1.00"));
        $(".breadcrumb").find(linkText("ДО ДЗA-1646 ФЛ Шмаков Виталий Се")).click();

        $(".navbar-navig").find(linkText("История операций")).click();
        $(".navbar-navig").find(linkText("История операций")).parent().shouldBe(attribute("class", "ng-scope active"));
        $("[ng-repeat='row in data.rows']", 1).shouldHave
                (matchText("^[0-9]\\d*"), text("Списание суммы обеспечения"), text("16.11.2017"),
                        text("Списание суммы обеспечения"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(1);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Списание суммы обеспечения"), text("Сумма обеспечения"), text("800 000.00"), text("RUB"), text("800 000.00"));
        $(".breadcrumb").find(linkText("ДО ДЗA-1646 ФЛ Шмаков Виталий Се")).click();
    }

}
