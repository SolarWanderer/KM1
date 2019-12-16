package Tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.commands.ShouldHave;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class Cessions extends Setup {
    @Test(priority = 0)
    public void MoveToCessions() {
        String txt = $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).getAttribute("class");
        if (txt.equals("ng-scope")) {
            $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Договоры цессии']")).click();

    }
    @Test(priority = 1)
    public void c_0001() {
        $(By.xpath(".//div[@value='Ц-0001']")).shouldBe(exist, enabled).click();
        try {
            $(By.xpath("//a[text()='Сопровождение договора']")).shouldBe(exist);
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='Ц-0001']")).shouldBe(exist, enabled).click();
            $(By.xpath("//a[text()='Сопровождение договора']")).shouldBe(exist);
        }
        $("#general #picEdit").click();
        $(By.xpath("//H5[text()='Наличие оригинала договора']/../..//INS[@class='iCheck-helper']")).shouldBe(enabled).click();
        $("#general #picSave").click();
        $(By.xpath(".//div[@class='toast ng-scope toast-success']")).shouldBe(exist).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Реестр требований']]")).click();

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Реестр требований']]")).shouldBe(attribute("class", "ng-scope active"));
     $$("[ng-repeat='row in data.rows']").findBy(text("ДКА-2299")).find(".iCheck-helper").click();
     //TODO Переписать все галочки
        $("#customPics[title='Исключить выбранные КД']").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $$("[ng-repeat='row in data.rows']").shouldHaveSize(1);
        $("[ng-click='notIncludedLoan()']").click();
        $$("[ng-repeat='row in data.rows']").shouldHaveSize(2);
        $$("[ng-repeat='row in data.rows']").findBy(text("ДКА-2299")).find(".iCheck-helper").click();
        $("#customPics[title='Включить выбранные КД']").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $$("[ng-repeat='row in data.rows']").shouldHaveSize(1);
        $("[ng-click='includedLoan()']").click();
        $$("[ng-repeat='row in data.rows']").shouldHaveSize(2);
        //$$("#list li").filterBy(cssClass("enabled")).findBy(exactText("foo")).find(".remove").click();
    }

    @Test(priority = 2)
    public void MoveToKdReestr() {
        String txt = $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).getAttribute("class");
        if (txt.equals("ng-scope")) {
            $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Кредитные договоры']")).click();

    }
    @Test(priority = 3)
    public void operation2299() {
       // $$("[ng-repeat='row in data.rows']").shouldHaveSize(20).findBy(text("ДКА-2299")).find(By.linkText("ДКА-2299")).click();
        $(By.xpath(".//div[@value='ДКА-2299']")).shouldBe(exist, enabled).click();
        try {
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ДКА-2299']")).shouldBe(exist, enabled).click();
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        }
        $(By.linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000002299");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("16082017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Структура задолженности")).click();
        $("#operationDate").shouldBe(visible).clear();
        $("#operationDate").sendKeys("16092017");
        $("[ng-click='continueDebtStructure()']").click();

        $("[ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text("23 248.86"), text("RUB"));
        //$("[ng-click='cancel()']").click();
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Кредитные договоры']")).click();

    }
    @Test(priority = 4)
    public void operation2288() {
        $(By.xpath(".//div[@value='ДКА-2288']")).shouldBe(exist, enabled).click();
        try {
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ДКА-2288']")).shouldBe(exist, enabled).click();
            $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        }

        $(By.linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("16082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Погашение задолженности
        $(By.xpath("//a[text()='Сопровождение КД']")).shouldBe(exist).click();
        $(By.linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000002288");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("16092017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //TODO Проверки
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Кредитные договоры']")).click();

    }


}
