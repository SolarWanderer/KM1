package Tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.openqa.selenium.By.linkText;

public class SettingsKD extends Setup {
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
    public void SetSomeKDtoEditMode() {
       $(By.xpath(".//div[@value='ДКА-2299']")).shouldBe(exist);

       

        $(By.xpath("//A[text()='ДКА-2400']/../../..//ins[@class='iCheck-helper']")).click();
        //this.actions.moveToElement($(By.xpath("//A[text()='ДКА-2484']/../../..//ins[@class='iCheck-helper']"))).perform();
        $(By.xpath("//A[text()='ДКА-2484']/../../..//ins[@class='iCheck-helper']")).click();
       // this.actions.moveToElement($(By.xpath("//A[text()='ГС-555']/../../..//ins[@class='iCheck-helper']"))).perform();
        $(By.xpath("//A[text()='ГС-555']/../../..//ins[@class='iCheck-helper']")).click();
      //  this.actions.moveToElement($(By.xpath("//A[text()='ОвГС-444']/../../..//ins[@class='iCheck-helper']"))).perform();
        $(By.xpath("//A[text()='ОвГС-444']/../../..//ins[@class='iCheck-helper']")).click();
        $(linkText("Операции")).click();
        $(linkText("Взять на редактирование")).click();
        $(By.xpath("//div[@class='modal-content']//button[text()='Да']")).shouldBe(exist).click();
        //(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    }
    @Test(priority = 2)
    public void Setting2299() {
       
        $(By.xpath("//div[@value='ДКА-2299']")).shouldBe(exist).click();
        try {
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath("//div[@value='ДКА-2299']")).shouldBe(exist).click();
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Взять на редактирование']")).shouldBe(exist).click();
      //  $(By.xpath("//BUTTON[@type='button'][text()='Да']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Параметры договора')]/..//a[@id='picEdit']")));
        $("#feature #picEdit").click();
        $$("#feature .form-group").findBy(text("Расчет процентов с учетом сумм в графике")).find(".iCheck-helper").click();
        $("#feature #picSave").click();
        $(By.xpath(".//div[@class='toast ng-scope toast-success']")).shouldBe(exist).click();
        $(By.xpath("//a[text()='Редактирование КД']")).click();
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Поставить на учет']")).click();
        //$(By.xpath("//div[@class='modal-content']//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }

    @Test(priority = 3)
    public void Setting5009() {
        $(By.xpath(".//div[@value='ВИ-5009']")).shouldBe(exist).click();
        try {
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath(".//div[@value='ВИ-5009']")).shouldBe(exist).click();
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Взять на редактирование']")).click();
        //$(By.xpath("//DIV[@class='modal-header']/..//BUTTON[@type='button'][text()='Да']")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")));
       // WebElement scroll = $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]"));
       // scroll.click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).shouldBe(attribute("class","ng-scope active"));
       // wait.until(ExpectedConditions.attributeContains(scroll, "class", "ng-scope active"));
        //$(By.xpath("//section[@id='repaymentTerms']//a[@id='picEdit']")).click();
        $("#repaymentTerms #picEdit").click();
        $(By.id("setPartPayTypeId")).selectOptionContainingText("ЧДП с уменьшением ежемесячного платежа");
        $(By.id("setRestActionId")).selectOptionContainingText("Переходит в ЧДП");
        $(By.id("setPayAccountOrderId")).selectOptionContainingText("В дату по договору");
       // this.scrollToElement($(By.xpath("//h5[contains(text(),'Условия погашения')]/..//a[@id='picSave']")));
        //$(".operationsPanel").
        $("#repaymentTerms #picSave").scrollIntoView(false).click();
        $(By.xpath(".//div[@class='toast ng-scope toast-success']")).shouldBe(exist).click();
       // $(By.xpath(".//div[@class='toast ng-scope toast-success']")).click();
        $(By.xpath("//a[text()='Редактирование КД']")).click();
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Поставить на учет']")).click();
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.id("massageWindow"), 1));
       // $(By.xpath("//*[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        //WebElement o = $(By.xpath("//DIV[@class='modal-header']/..//BUTTON[@type='button'][text()='Закрыть']"));
        //JavascriptExecutor js = (JavascriptExecutor)driver;
       // js.executeScript("var evt = document.createEvent('MouseEvents');evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);arguments[0].dispatchEvent(evt);", new Object[]{o});
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
    @Test(priority = 4)
    public void Setting2139() {
        $(By.xpath(".//div[@value='ДКИ-2139']")).shouldBe(exist,enabled).click();
        try {
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath(".//div[@value='ДКИ-2139']")).shouldBe(exist,enabled).click();
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Взять на редактирование']")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        GetIdKd();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).shouldBe(attribute("class","ng-scope active"));
        // wait.until(ExpectedConditions.attributeContains(scroll, "class", "ng-scope active"));
        $("#repaymentTerms #picEdit").click();
        $(By.id("setPartPayTypeId")).selectOptionContainingText("ЧДП с уменьшением срока");
        $(By.id("setRestActionId")).selectOptionContainingText("Переходит в ЧДП");
        $(By.id("setPayAccountOrderId")).selectOptionContainingText("В дату по договору");

        $("#repaymentTerms #picSave").scrollIntoView(false).click();
        $(By.xpath(".//div[@class='toast ng-scope toast-success']")).shouldBe(exist).click();

        $(By.xpath("//a[text()='Редактирование КД']")).click();
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Поставить на учет']")).click();
        //$(By.xpath("//*[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
    @Test(priority = 5)
    public void Setting2381() {
        $(By.xpath(".//div[@value='ДКА-2381']")).shouldBe(exist).click();
        try {
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath(".//div[@value='ДКА-2381']")).shouldBe(exist).click();
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Взять на редактирование']")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).shouldBe(attribute("class","ng-scope active"));
        // wait.until(ExpectedConditions.attributeContains(scroll, "class", "ng-scope active"));
        $("#repaymentTerms #picEdit").click();
        $(By.id("setPartPayTypeId")).selectOptionContainingText("ЧДП с уменьшением ежемесячного платежа");
        $(By.id("setRestActionId")).selectOptionContainingText("Переходит в ЧДП");
        $(By.id("partPayReserveDays")).sendKeys("30");
        $(By.id("setPayAccountOrderId")).selectOptionContainingText("В дату поступления");

        $("#repaymentTerms #picSave").scrollIntoView(false).click();
        $(By.xpath(".//div[@class='toast ng-scope toast-success']")).shouldBe(exist).click();

        $(By.xpath("//a[text()='Редактирование КД']")).click();
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Поставить на учет']")).click();
       // $(By.xpath("//*[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
    @Test(priority = 6)
    public void Setting2288() {

        $(By.xpath("//div[@value='ДКА-2288']")).shouldBe(exist).click();
        try {
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath("//div[@value='ДКА-2288']")).shouldBe(exist).click();
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Взять на редактирование']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Параметры договора')]/..//a[@id='picEdit']")));
        $("#feature #picEdit").click();
        $$("#feature .form-group").findBy(text("Расчет процентов с учетом сумм в графике")).find(".iCheck-helper").click();
        $("#feature #picSave").click();
        $(By.xpath(".//div[@class='toast ng-scope toast-success']")).shouldBe(exist).click();
        $(By.xpath("//a[text()='Редактирование КД']")).click();
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Поставить на учет']")).click();
       // $(By.xpath("//div[@class='modal-content']//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
       // $(".breadcrumb").find(linkText("Кредитные договоры")).click();
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
    @Test(priority = 7)
    public void Setting8400() {
        $(By.xpath(".//div[@value='ДКА-8400']")).shouldBe(exist).click();
        try {
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath(".//div[@value='ДКА-8400']")).shouldBe(exist).click();
            $(By.xpath("//a[text()='Редактирование КД']")).shouldBe(exist).click();
        }
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Взять на редактирование']")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Условия погашения']]")).shouldBe(attribute("class","ng-scope active"));
        // wait.until(ExpectedConditions.attributeContains(scroll, "class", "ng-scope active"));
        $("#repaymentTerms #picEdit").click();
        $(By.id("setPartPayTypeId")).selectOptionContainingText("ЧДП с уменьшением ежемесячного платежа");
        $(By.id("setRestActionId")).selectOptionContainingText("Остается на счете");
        $(By.id("setPayAccountOrderId")).selectOptionContainingText("План отвязан от факта");
        $("#repaymentTerms #picSave").scrollIntoView(false).click();
       // $(By.xpath("//h5[contains(text(),'Условия погашения')]/..//a[@id='picSave']")).shouldBe(exist).click();
        $(By.xpath(".//div[@class='toast ng-scope toast-success']")).shouldBe(exist).click();

        $(By.xpath("//a[text()='Редактирование КД']")).click();
        $(By.xpath("//a[text()='Редактирование КД']/..//a[text()='Поставить на учет']")).click();
       // $(By.xpath("//*[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
}
