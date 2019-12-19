package Tests;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.By.linkText;

public class OperationsUL extends Setup {
    @Test(priority = 0)
    public void MoveToKdReestr() {
        String txt = $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).getAttribute("class");
        if (txt.equals("ng-scope")) {
            $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Кредитные договоры']")).click();

    }
   @Test(priority = 1)
    public void ULlimits(){
        $(By.xpath(".//div[@value='ОвГС-777']")).shouldBe(exist);
        $(By.xpath("//A[text()='ОвГС-777']/../../..//ins[@class='iCheck-helper']")).click();
        $(By.xpath("//A[text()='НВКЛ-666']/../../..//ins[@class='iCheck-helper']")).click();
        $(By.xpath("//A[text()='ОвГС-888']/../../..//ins[@class='iCheck-helper']")).click();
        $(linkText("Операции")).click();
        $(linkText("Списать лимиты")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("12082017");

        $("[ng-click='ok()']").click();
        // $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

    }
    @Test(priority = 2)
    public void operation666(){
        $(By.xpath(".//div[@value='НВКЛ-666']")).shouldBe(exist, enabled).click();
        try {
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='НВКЛ-666']")).shouldBe(exist, enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        }
       $(linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("05102017");
        TryStructure("474 862.98");
       // $("[ng-click='continueDebtStructure()']").click();
       // $(".table [ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text("474 862.98"), text("RUB"));
        $("[ng-click='cancel()']").click();
        //вынесение на просрочку
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("05102017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Погашение задолженности
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000006660");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("06112017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        //Проверки
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        $("[ng-show='showGeneralAgreementData()'] [ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("НВКЛ-666"),text("ГС"),text("Погашение задолженностей"), text("06.11.2017"), text("Погашение задолженностей в 06.11.2017 г."), text("Исполнена")).find(By.cssSelector("[key='id'] a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(2);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("11"), text("Погашение прочих комиссий"), text("Требования по прочим комиссиям"), text("5 000.00"), text("RUB"), text("5 000.00"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("31"), text("Восстановление резерва по прочим комиссия"), text("Резерв по прочим комиссиям"), text("750.00"), text("RUB"), text("750.00"));
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №НВКЛ-666 ЮЛ ООО \"Стройка М\"']")).click();
        try{
            $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        }
        catch (ElementNotFound e)
        {
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №НВКЛ-666 ЮЛ ООО \"Стройка М\"']")).click();
            $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
        }
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        //TODO проверки
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
    @Test(priority = 3)
    public void operation777(){
        $(By.xpath(".//div[@value='ОвГС-777']")).shouldBe(exist, enabled).click();
        try {
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ОвГС-777']")).shouldBe(exist, enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        }
        $(linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("14082017");
        TryStructure("2 553 082.19");
       // $("[ng-click='continueDebtStructure()']").click();
       // $(".table [ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text("2 553 082.19"), text("RUB"));
        $("[ng-click='cancel()']").click();
        //Погашение задолженности
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000007770");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("14082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Погашение задолженности
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000007770");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("21082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Погашение задолженности
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000007770");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("24082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //TODO проверки
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
    @Test(priority = 4)
    public void operation888(){
        $(By.xpath(".//div[@value='ОвГС-888']")).shouldBe(exist, enabled).click();
        try {
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ОвГС-888']")).shouldBe(exist, enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        }
        $(linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("13092017");
        TryStructure("2 407 945.20");
       // $("[ng-click='continueDebtStructure()']").click();
       // $(".table [ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text("2 407 945.20"), text("RUB"));
        $("[ng-click='cancel()']").click();
        //Погашение задолженности
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000008880");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("13092017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }



}
