package Tests;

import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;


public class LoadPayments extends Setup {
    @Test(priority = 0)
    public void loadpayment(){
        String txt=$(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).getAttribute("class");
        if (txt.equals("ng-scope"))
        {
            $(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Входящие платежи']")).click();
        

        $(By.linkText("Операции")).shouldBe(exist);
        $(By.linkText("Импорт")).click();
        $(By.linkText("Новая загрузка")).click();
        $(By.id("loanKindId")).shouldBe(exist).selectOptionContainingText("Файловый обмен");
        $(By.xpath("//input[@id='excelFile']")).sendKeys("C:/Users/k.melnikov/Documents/tests/Реестр входящих платежей_1.xlsx");
        $(By.xpath("//i[@class='glyphicon glyphicon-ok']")).shouldBe(exist);
        $(By.xpath("//button[text()='Загрузить']")).click();
        //$(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        try {
            //$(".breadcrumb").find(linkText("Входящие платежи")).click();
            $(".breadcrumb").find(linkText("Входящие платежи")).click();
            $("[title='Обновить']").click();
            $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);

        }
        catch (ElementNotFound ex)
        {  $(".breadcrumb").find(linkText("Входящие платежи")).click();
            $("[title='Обновить']").click();
            $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);

        }
        $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.xpath("//A[text()='3']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.linkText("Операции")).click();
        $(By.linkText("Автоквитовка")).click();
       // $(By.xpath("//button[text()='Закрыть']")).shouldBe(enabled,visible,exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.linkText("Операции")).click();
        $(By.linkText("Утвердить платеж")).click();
      //  $(By.xpath("//button[text()='Да']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(By.linkText("Операции")).click();
        $(By.linkText("Исполнить выбранные")).click();
       // $(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");


    }
    @Test(priority = 1)
    public void loadpayment_svodny(){
        $(By.linkText("5")).click();
        $(By.xpath("//li//a[text()='Реестр платежей']")).shouldBe(exist);

        $(By.id("excelFile")).sendKeys("C:/Users/k.melnikov/Documents/tests/Реестр платежей сводный.xlsx");
        //$("[type='button'][ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        if($$("#downloadProcesses [title='Развернуть']").size()>0)
        {
            $("#downloadProcesses [title='Развернуть']").click();
            $("[on-update='loadRegistryProcesses(params)'] [title='Обновить']").click();
        }
        $("#calculationAccount").should(exist).scrollIntoView(true);
        sleep(2000);
        $("[on-update='loadRegistryPayments(params)'] th .icheckbox_square-green").shouldBe(exist).click();
        System.out.println($("[on-update='loadRegistryPayments(params)'] th .icheckbox_square-green").getAttribute("class"));
        $("[title='Автоквитовка']").click();
       // $("[ng-click='$ctrl.cancel()'][type='button']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $("[title='Квитовка платежей вручную']").click();
        $(By.xpath("//A[text()='00585']/../../..//INS[@class='iCheck-helper']")).shouldBe(exist).click();
        $(".ng-scope .fa-th-list").click();
        $(By.xpath("(//A[text()='ДКА-8400'][text()='ДКА-8400'])[2]/../../..//INS[@class='iCheck-helper']")).shouldBe(exist).click();
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
        $(".fa-link").click();
        //$("[ng-click='$ctrl.cancel()'][type='button']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.xpath("//*[contains(text(),'Платеж №5')]")).click();
        $(By.xpath("//A[text()='00588']/../../..//INS[@class='iCheck-helper']")).shouldBe(exist).click();
        $("[title='Изменить решение по платежу']").click();
        $("#modalWindow #paymentResolve").shouldBe(exist).selectOptionContainingText("Невыясненный платеж");
       // $("#modalWindow [ng-click='apply()']").click();
        ClickAndWaitModal("#modalWindow [ng-click='apply()']");
        $("#calculationAccount").should(exist).scrollIntoView(true);
        sleep(2000);
        $("[on-update='loadRegistryPayments(params)'] th .icheckbox_square-green").shouldBe(exist).click();
        $("[title='Утвердить платеж']").click();
       // $("[ng-click='$ctrl.ok()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $("[title='Исполнить']").click();
       // $("[ng-click='$ctrl.cancel()'][type='button']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        //$("[section-name='downloadProcesses']").scrollTo();
        $(".breadcrumb").find(linkText("Входящие платежи")).click();

    }
    @Test(enabled = false)
            public void delete()
    {
        String txt=$(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).getAttribute("class");
        if (txt.equals("ng-scope"))
        {
            $(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Входящие платежи']")).click();


        $(By.linkText("Операции")).shouldBe(exist);
        $(By.linkText("Импорт")).click();
        $(By.linkText("Новая загрузка")).click();
        $(By.id("loanKindId")).shouldBe(exist).selectOptionContainingText("Файловый обмен");
        $(By.xpath("//input[@id='excelFile']")).sendKeys("C:/Users/k.melnikov/Documents/tests/Реестр входящих платежей_1.xlsx");
        $(By.xpath("//i[@class='glyphicon glyphicon-ok']")).shouldBe(exist);
        $(By.xpath("//button[text()='Загрузить']")).click();
        //$(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(".breadcrumb").find(linkText("Входящие платежи")).click();
        $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.xpath("//A[text()='3']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.linkText("Операции")).click();
        $(By.linkText("Автоквитовка")).click();
        // $(By.xpath("//button[text()='Закрыть']")).shouldBe(enabled,visible,exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.linkText("Операции")).click();
        $(By.linkText("Утвердить платеж")).click();
        //  $(By.xpath("//button[text()='Да']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(By.linkText("Операции")).click();
        $(By.linkText("Исполнить выбранные")).click();
        // $(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.linkText("5")).click();
        $(By.xpath("//li//a[text()='Реестр платежей']")).shouldBe(exist);

        $(By.id("excelFile")).sendKeys("C:/Users/k.melnikov/Documents/tests/Реестр платежей сводный.xlsx");
        //$("[type='button'][ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        if($$("#downloadProcesses [title='Развернуть']").size()>0)
        {
            $("#downloadProcesses [title='Развернуть']").click();
            $("[on-update='loadRegistryProcesses(params)'] [title='Обновить']").click();
        }
        $("#calculationAccount").should(exist).scrollIntoView(true);
        sleep(2000);
        $("[on-update='loadRegistryPayments(params)'] th .icheckbox_square-green").shouldBe(exist).click();
        System.out.println($("[on-update='loadRegistryPayments(params)'] th .icheckbox_square-green").getAttribute("class"));
        $("[title='Автоквитовка']").click();
        // $("[ng-click='$ctrl.cancel()'][type='button']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $("[title='Квитовка платежей вручную']").click();
        $(By.xpath("//A[text()='00585']/../../..//INS[@class='iCheck-helper']")).shouldBe(exist).click();
        $(".ng-scope .fa-th-list").click();
        $(By.xpath("(//A[text()='ДКА-8400'][text()='ДКА-8400'])[2]/../../..//INS[@class='iCheck-helper']")).shouldBe(exist).click();
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
        $(".fa-link").click();
        //$("[ng-click='$ctrl.cancel()'][type='button']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.xpath("//*[contains(text(),'Платеж №5')]")).click();
        $(By.xpath("//A[text()='00588']/../../..//INS[@class='iCheck-helper']")).shouldBe(exist).click();
        $("[title='Изменить решение по платежу']").click();
        $("#modalWindow #paymentResolve").shouldBe(exist).selectOptionContainingText("Невыясненный платеж");
        // $("#modalWindow [ng-click='apply()']").click();
        ClickAndWaitModal("#modalWindow [ng-click='apply()']");
        $("#calculationAccount").should(exist).scrollIntoView(true);
        sleep(2000);
        $("[on-update='loadRegistryPayments(params)'] th .icheckbox_square-green").shouldBe(exist).click();
        $("[title='Утвердить платеж']").click();
        // $("[ng-click='$ctrl.ok()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $("[title='Исполнить']").click();
        // $("[ng-click='$ctrl.cancel()'][type='button']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        //$("[section-name='downloadProcesses']").scrollTo();
        $(".breadcrumb").find(linkText("Входящие платежи")).click();
        $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.xpath("//A[text()='3']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.linkText("Операции")).click();
        $(By.linkText("Откатить")).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(By.linkText("Операции")).click();
        $(By.linkText("Удалить")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        sleep(5000);
        $(By.linkText("5")).shouldBe(exist).click();
        $(By.linkText("Операции")).shouldBe(exist).click();
        $(By.linkText("Откатить")).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(".breadcrumb").find(linkText("Входящие платежи")).click();
        $(By.linkText("5")).shouldBe(exist).click();
        $(By.linkText("Реестр платежей")).shouldBe(exist).click();
        $(By.linkText("Удалить реестр платежей")).click();
        //$("[ng-click='$ctrl.ok()']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(".breadcrumb").find(linkText("Входящие платежи")).click();
        $(By.xpath("//A[text()='5']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        $(By.linkText("Операции")).click();
        $(By.linkText("Удалить")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        $(By.xpath("//div[text()='Нет данных']")).shouldBe(exist,visible);

    }
    @Test(enabled = false)
    public void shet(){
        //$("body").sendKeys(Keys.CONTROL+"t");
        String script = "window.open('"+ "http://asv-km-t-bl2.dfu.i-teco.ru/km/#/accounts?bc=0" +"','_blank');";
        executeJavaScript(script);
        switchTo().window(1);
        //open("http://asv-km-t-bl2.dfu.i-teco.ru/km/#/accounts?bc=0");
        $$("[ng-class='getRowClass(row)']").shouldHaveSize(30);
        $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).shouldBe(exist).sendKeys("47422",Keys.ENTER);
        try {
            $(By.xpath("//div[@value='47422810000000000001']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).clear();
            $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).shouldBe(exist).sendKeys("47422",Keys.ENTER);
            $(By.xpath("//div[@value='47422810000000000001']")).shouldBe(exist).click();
    }
    }
    public boolean error=false;
    @Test(enabled = false)
    public void ostatki()
    {
        int i=0;
        switchTo().window(0);
        while (!error) {
            System.out.println("Начало - "+i);
            String txt = $(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).getAttribute("class");
            if (txt.equals("ng-scope")) {
                $(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).click();
            }
            $(By.xpath(".//li[normalize-space(.)='Входящие платежи']")).click();

            $(By.linkText("Операции")).shouldBe(exist);
            $(By.linkText("Импорт")).click();
            $(By.linkText("Новая загрузка")).click();
            $(By.id("loanKindId")).shouldBe(exist).selectOptionContainingText("Файловый обмен");
            $(By.xpath("//input[@id='excelFile']")).sendKeys("C:/Users/k.melnikov/Documents/tests/123/Реестр входящих платежей_16.08.17.xlsx");
            $(By.xpath("//i[@class='glyphicon glyphicon-ok']")).shouldBe(exist);
            $(By.xpath("//button[text()='Загрузить']")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $(".breadcrumb").find(linkText("Входящие платежи")).click();
                $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
                $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            }
            catch (ElementNotFound ex)
            {
                $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
                $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            }


            $(By.linkText("Операции")).click();
            $(By.linkText("Автоквитовка")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Утвердить платеж")).click();

            ClickAndWaitModal("[ng-click='$ctrl.ok()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Исполнить выбранные")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='1']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='2']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
            } catch (ElementNotFound ex) {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='1']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='2']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
            }
            $("[ng-click='clearSelected()']").click();
            //-------------

            $(By.linkText("Операции")).shouldBe(exist);
            $(By.linkText("Импорт")).click();
            $(By.linkText("Новая загрузка")).click();
            $(By.id("loanKindId")).shouldBe(exist).selectOptionContainingText("Файловый обмен");
            $(By.xpath("//input[@id='excelFile']")).sendKeys("C:/Users/k.melnikov/Documents/tests/123/Реестр входящих платежей_11.09.17.xlsx");
            $(By.xpath("//i[@class='glyphicon glyphicon-ok']")).shouldBe(exist);
            $(By.xpath("//button[text()='Загрузить']")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $(".breadcrumb").find(linkText("Входящие платежи")).click();
                $(By.xpath("//A[text()='4']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
                $(By.xpath("//A[text()='5']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            }
            catch (ElementNotFound ex)
            {
                $(By.xpath("//A[text()='4']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
                $(By.xpath("//A[text()='5']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            }
            $(By.linkText("Операции")).click();
            $(By.linkText("Автоквитовка")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Утвердить платеж")).click();

            ClickAndWaitModal("[ng-click='$ctrl.ok()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Исполнить выбранные")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='4']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='5']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
            } catch (ElementNotFound ex) {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='4']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='5']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
            }
            $("[ng-click='clearSelected()']").click();
//-----------------
            $(By.linkText("Операции")).shouldBe(exist);
            $(By.linkText("Импорт")).click();
            $(By.linkText("Новая загрузка")).click();
            $(By.id("loanKindId")).shouldBe(exist).selectOptionContainingText("Файловый обмен");
            $(By.xpath("//input[@id='excelFile']")).sendKeys("C:/Users/k.melnikov/Documents/tests/123/Реестр входящих платежей_22.09.17.xlsx");
            $(By.xpath("//i[@class='glyphicon glyphicon-ok']")).shouldBe(exist);
            $(By.xpath("//button[text()='Загрузить']")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $(".breadcrumb").find(linkText("Входящие платежи")).click();
                $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);
                $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);
            }
            catch (ElementNotFound ex)
            {
                $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);
                $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);
            }
            switchTo().window(1);
            $(By.linkText("Операции")).click();
            $(By.linkText("История остатков по счету")).click();
            try {
                $("[ng-repeat='row in data.rows']", 0).shouldHave(text("22.09.2017"), text("RUB"),
                        text("1 283 300.35"), text("1 283 300.35"));
                $("[ng-repeat='row in data.rows']", 1).shouldHave(text("11.09.2017"), text("RUB"),
                        text("1 050 000.00"), text("1 050 000.00"));
                $("[ng-repeat='row in data.rows']", 2).shouldHave(text("16.08.2017"), text("RUB"),
                        text("550 000.00"), text("550 000.00"));
            } catch (ElementShould ex) {
                error = true;
                System.out.println("История 1 - "+i);
                break;
            }
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Счет учета погашений кредита в рублях (Общий) № 47422.810.0.00000000001']")).click();
            try {
                $("#balance").shouldHave(value("1 283 300.35"));
                $("#balanceRub").shouldHave(value("1 283 300.35"));
            }
            catch (ElementShould ex){
                error=true;
                System.out.println("Карточка 1 - "+i);
                break;
            }
            //-----
            switchTo().window(0);
            $(By.xpath("//A[text()='3']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.linkText("Операции")).click();
            $(By.linkText("Автоквитовка")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Утвердить платеж")).click();

            ClickAndWaitModal("[ng-click='$ctrl.ok()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Исполнить выбранные")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='3']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);

            } catch (ElementNotFound ex) {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='3']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);

            }
            $("[ng-click='clearSelected()']").click();
            switchTo().window(1);
            $(By.linkText("Операции")).click();
            $(By.linkText("История остатков по счету")).click();
            try {
                $("[ng-repeat='row in data.rows']", 0).shouldHave(text("22.09.2017"), text("RUB"),
                        text("733 300.35"), text("733 300.35"));
                $("[ng-repeat='row in data.rows']", 1).shouldHave(text("11.09.2017"), text("RUB"),
                        text("500 000.00"), text("500 000.00"));
                $("[ng-repeat='row in data.rows']", 2).shouldHave(text("16.08.2017"), text("RUB"),
                        text("0.00"), text("0.00"));
            } catch (ElementShould ex) {
                error = true;
                System.out.println("История 2 - "+i);
                break;
            }
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Счет учета погашений кредита в рублях (Общий) № 47422.810.0.00000000001']")).click();
            try {
                $("#balance").shouldHave(value("733 300.35"));
                $("#balanceRub").shouldHave(value("733 300.35"));
            }
            catch (ElementShould ex){
                error=true;
                System.out.println("Карточка 2 - "+i);
                break;
            }
            //------------------
            switchTo().window(0);
            $(By.xpath("//A[text()='6']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.linkText("Операции")).click();
            $(By.linkText("Автоквитовка")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Утвердить платеж")).click();

            ClickAndWaitModal("[ng-click='$ctrl.ok()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Исполнить выбранные")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='6']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);

            } catch (ElementNotFound ex) {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='6']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);

            }
            $("[ng-click='clearSelected()']").click();
            switchTo().window(1);
            $(By.linkText("Операции")).click();
            $(By.linkText("История остатков по счету")).click();
            try {
                $("[ng-repeat='row in data.rows']", 0).shouldHave(text("22.09.2017"), text("RUB"),
                        text("233 300.35"), text("233 300.35"));
                $("[ng-repeat='row in data.rows']", 1).shouldHave(text("11.09.2017"), text("RUB"),
                        text("0.00"), text("0.00"));
                $("[ng-repeat='row in data.rows']", 2).shouldHave(text("16.08.2017"), text("RUB"),
                        text("0.00"), text("0.00"));
            } catch (ElementShould ex) {
                error = true;
                System.out.println("История 3 - "+i);
                break;
            }
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Счет учета погашений кредита в рублях (Общий) № 47422.810.0.00000000001']")).click();
            try {
                $("#balance").shouldHave(value("233 300.35"));
                $("#balanceRub").shouldHave(value("233 300.35"));
            }
            catch (ElementShould ex){
                error=true;
                System.out.println("Карточка 3 - "+i);
                break;
            }
            //----------------------
            switchTo().window(0);
            $(By.xpath("//A[text()='9']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).scrollIntoView(true);
            $(By.xpath("//A[text()='7']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='8']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='9']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.linkText("Операции")).click();
            $(By.linkText("Автоквитовка")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Утвердить платеж")).click();

            ClickAndWaitModal("[ng-click='$ctrl.ok()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Исполнить выбранные")).click();

            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            try {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='7']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='8']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='9']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);

            } catch (ElementNotFound ex) {
                $("[title='Обновить']").click();
                $(By.xpath("//A[text()='7']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='8']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);
                $(By.xpath("//A[text()='9']/../../..//DIV[@grid-cell=''][text()='Обработан']")).shouldBe(exist);

            }
            $("[ng-click='clearSelected()']").click();
            switchTo().window(1);
            $(By.linkText("Операции")).click();
            $(By.linkText("История остатков по счету")).click();
            try {
                $("[ng-repeat='row in data.rows']", 0).shouldHave(text("22.09.2017"), text("RUB"),
                        text("0.00"), text("0.00"));
                $("[ng-repeat='row in data.rows']", 1).shouldHave(text("11.09.2017"), text("RUB"),
                        text("0.00"), text("0.00"));
                $("[ng-repeat='row in data.rows']", 2).shouldHave(text("16.08.2017"), text("RUB"),
                        text("0.00"), text("0.00"));
            } catch (ElementShould ex) {
                error = true;
                System.out.println("История 4 - "+i);
                break;
            }
            $(By.xpath("//ol[contains(@class,'breadcrumb')]//a[text()='Счет учета погашений кредита в рублях (Общий) № 47422.810.0.00000000001']")).click();
            try {
                $("#balance").shouldHave(value("0.00"));
                $("#balanceRub").shouldHave(value("0.00"));
            }
            catch (ElementShould ex){
                error=true;
                System.out.println("Карточка 4 - "+i);
                break;
            }
            //delete
            switchTo().window(0);
         /*   $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='2']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='4']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='5']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='6']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='7']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='8']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
            $(By.xpath("//A[text()='9']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();*/
         $("thead .iCheck-helper").click();
            $(By.linkText("Операции")).click();
            $(By.linkText("Откатить")).click();
            ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
            $(By.linkText("Операции")).click();
            $(By.linkText("Удалить")).click();
            ClickAndWaitModal("[ng-click='$ctrl.ok()']");
            try {
                $("[title='Обновить']").click();
                $(By.xpath("//div[text()='Нет данных']")).shouldBe(exist);

            } catch (ElementNotFound ex) {
                $("[title='Обновить']").click();
                $(By.xpath("//div[text()='Нет данных']")).shouldBe(exist);

            }
            System.out.println("Конец - "+i);
            i=i+1;
        }
    }
}
