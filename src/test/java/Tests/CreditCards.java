package Tests;

import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditCards extends Setup {
    @Test(priority = 0)
    public void MoveToKdReestr() {
        String txt = $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).getAttribute("class");
        if (txt.equals("ng-scope")) {
            $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Кредитные договоры']")).click();

    }
    @Test(priority = 1)
    public void KK3001(){
        $(By.xpath(".//div[@value='КК-3001']")).shouldBe(exist, enabled).click();
        try {
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='КК-3001']")).shouldBe(exist, enabled).click();
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        }
        $(By.linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("18082017");
       // $("[ng-click='continueDebtStructure()']").click();
   //  System.out.println($(".table tr",4).getText());
       // $(".table tr",4).shouldHave(text("ИТОГО:"),text("11 053.76"), text("RUB"));
        TryStructure("11 053.76");
        $("[ng-click='cancel()']").click();

        //Погашение
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000003001");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("18082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Вынесение на просрочку
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("25082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        //Отчетный период
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Обработать отчетный период по картам")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("31082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        //Погашение
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000003001");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("25092017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Проверки TODO
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));

        $("[data='operations'] [ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Погашение задолженностей"), text("25.09.2017"), text("Погашение текущей задолженности за период с 01.08.2017 по 31.08.2017"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(4);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Погашение текущего ОД"), text("Задолженность по ОД"), text("6 768.75"), text("RUB"), text("6 768.75"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("3"), text("Погашение текущих процентов (внебаланс)"), text("Требования по % по ОД-Внебаланс"), text("3 592.94"), text("RUB"), text("3 592.94"));
        $("[items='stepsData'] [ng-repeat='item in items']", 2).shouldHave
                (text("5"), text("Погашение процентов по просроченному ОД (внебаланс)"), text("Требования по % по просроченному ОД-внебаланс"), text("32.79"), text("RUB"), text("32.79"));
        $("[items='stepsData'] [ng-repeat='item in items']", 3).shouldHave
                (text("6"), text("Погашение просроченного ОД"), text("Задолженность по просроченному ОД"), text("6 648.29"), text("RUB"), text("6 648.29"));
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Кредитные договоры']")).click();

    }
    @Test(priority = 2)
    public void KK3004(){
        $(By.xpath(".//div[@value='КК-3004']")).shouldBe(exist, enabled).click();
        try {
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='КК-3004']")).shouldBe(exist, enabled).click();
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        }
        $(By.linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("25082017");
       // $("[ng-click='continueDebtStructure()']").click();
       // System.out.println($(".table tr",4).getText());
       // $(".table tr",4).shouldHave(text("ИТОГО:"),text("17 580.79"), text("RUB"));
        TryStructure("17 580.79");
        $("[ng-click='cancel()']").click();
        //Погашение
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000003004");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("25082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Отчетный период
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Обработать отчетный период по картам")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("31082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        //Структура
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("31082017");
        TryStructure("15 000.00");
      //  $("[ng-click='continueDebtStructure()']").click();
       // System.out.println($(".table tr",3).getText());
       // $(".table tr",3).shouldHave(text("ИТОГО:"),text("15 000.00"), text("RUB"));
        $("[ng-click='cancel()']").click();
        //TODO Проверки
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Кредитные договоры']")).click();
    }
    @Test(priority = 3)
    public void KK3006(){
        $(By.xpath(".//div[@value='КК-3006']")).shouldBe(exist, enabled).click();
        try {
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='КК-3006']")).shouldBe(exist, enabled).click();
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        }
        $(By.linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("25082017");
        TryStructure("5 837.04");
      //  $("[ng-click='continueDebtStructure()']").click();
       // System.out.println($(".table tr",4).getText());
       // $(".table tr",4).shouldHave(text("ИТОГО:"),text("5 837.04"), text("RUB"));
        $("[ng-click='cancel()']").click();
        //Погашение

        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000003006");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("25082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Отчетный период
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Обработать отчетный период по картам")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("31082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Структура
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("31082017");
        TryStructure("5 000.00");
       // $("[ng-click='continueDebtStructure()']").click();
       // System.out.println($(".table tr",4).getText());
       // $(".table tr",3).shouldHave(text("ИТОГО:"),text("5 000.00"), text("RUB"));
        $("[ng-click='cancel()']").click();
        //TODO Проверки
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Кредитные договоры']")).click();
    }
}
