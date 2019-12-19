package Tests;

import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.By.linkText;

public class OperationsFL extends Setup {
    @Test(priority = 0)
    public void MoveToKdReestr() {
        String txt = $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).getAttribute("class");
        if (txt.equals("ng-scope")) {
            $(By.xpath(".//span[normalize-space(.)='Договоры']/../..")).click();
        }
        $(By.xpath(".//li[normalize-space(.)='Кредитные договоры']")).click();

    }

    @Test(priority = 1)
    public void Operation2139() {
        $(By.xpath(".//div[@value='ДКИ-2139']")).shouldBe(exist, enabled).click();
        try {
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ДКИ-2139']")).shouldBe(exist, enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();

        }
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810000000002139");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("11092017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Графики']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Графики']]")).shouldBe(attribute("class", "ng-scope active"));
        $(linkText("График аннуитетных платежей")).click();
        // $(By.xpath("//H5[@class='p-l-s ng-binding'][text()='Графики']/../..//A[@class=''][text()='График аннуитетных платежей']")).shouldBe(exist).click();
        $$("[ng-click='onRowClick(row)']").shouldHaveSize(7);
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКИ-2139 ФЛ Жуков Сергей Игор']")).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));


        //Досрочное погашение задолженности

          $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();


        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        System.out.println($("[data='operations'] [ng-repeat='row in data.rows']", 0).getText());
        $("[data='operations'] [ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Погашение задолженностей"), text("11.09.2017"), text("Досрочное погашение задолженности"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(2);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Погашение текущего ОД"), text("Задолженность по ОД"), text("50 000.00"), text("RUB"), text("50 000.00"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("26"), text("Восстановление резерва по текущему ОД"), text("Резерв по текущему ОД"), text("2 500.00"), text("RUB"), text("2 500.00"));
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКИ-2139 ФЛ Жуков Сергей Игор']")).click();
        //------------------------------------------------------------------------------------------------------------
        //Погашение текущей задолженности за период с 12.08.2017 по 11.09.2017
        try{
          $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
      }
      catch (ElementNotFound e)
      {
          $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКИ-2139 ФЛ Жуков Сергей Игор']")).click();
          $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
      }
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        System.out.println($("[data='operations'] [ng-repeat='row in data.rows']", 1).getText());
        $("[data='operations'] [ng-repeat='row in data.rows']", 1).shouldHave
                (matchText("^[0-9]\\d*"), text("Погашение задолженностей"), text("11.09.2017"), text("Погашение текущей задолженности за период с 12.08.2017 по 11.09.2017"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(3);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Погашение текущего ОД"), text("Задолженность по ОД"), text("40 591.42"), text("RUB"), text("40 591.42"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("3"), text("Погашение текущих процентов (внебаланс)"), text("Требования по % по ОД-Внебаланс"), text("3 897.48"), text("RUB"), text("3 897.48"));
        $("[items='stepsData'] [ng-repeat='item in items']", 2).shouldHave
                (text("26"), text("Восстановление резерва по текущему ОД"), text("Резерв по текущему ОД"), text("2 029.57"), text("RUB"), text("2 029.57"));
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКИ-2139 ФЛ Жуков Сергей Игор']")).click();
        //------------------------------------------------------------------------------------------------------------
        //Начисление процентов за период, с 12.08.2017 по 11.09.2017

        try{
          $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
      }
      catch (ElementNotFound e)
      {
          $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКИ-2139 ФЛ Жуков Сергей Игор']")).click();
          $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
      }
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        System.out.println($("[data='operations'] [ng-repeat='row in data.rows']", 2).getText());
        $("[data='operations'] [ng-repeat='row in data.rows']", 2).shouldHave
                (matchText("^[0-9]\\d*"), text("Начисление процентов"), text("11.09.2017"), text("Начисление процентов за период, с 12.08.2017 по 11.09.2017"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(2);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Начисление процентов на ОД"), text("Проценты на ОД, набежавшие"), text("3 897.48"), text("RUB"), text("3 897.48"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("3"), text("Учет % по текущему ОД (внебаланс)"), text("Требования по % по ОД-Внебаланс"), text("3 897.48"), text("RUB"), text("3 897.48"));
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();

    }

    @Test(priority = 2)
    public void Operation2381() {
        $(By.xpath(".//div[@value='ДКА-2381']")).shouldBe(exist, enabled).click();
        try {
           // $(linkText("Сопровождение КД")).shouldBe(enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ДКА-2381']")).shouldBe(exist, enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        }
        //Вынесение на просрочку
        $(linkText("Вынести на просрочку")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("16082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Погашение задолженности 16.09.2017
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000002381");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("16092017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()'][type='button']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Погашение задолженности 22.09.2017
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000002381");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("22092017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()'][type='button']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Проверки
        //Досрочное погашение задолженност

        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();


        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        System.out.println($("[data='operations'] [ng-repeat='row in data.rows']", 0).getText());
        $("[data='operations'] [ng-repeat='row in data.rows']", 0).shouldHave
                (matchText("^[0-9]\\d*"), text("Погашение задолженностей"), text("22.09.2017"), text("Досрочное погашение задолженности"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(3);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Погашение текущего ОД"), text("Задолженность по ОД"), text("61 289.43"), text("RUB"), text("61 289.43"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("3"), text("Погашение текущих процентов (внебаланс)"), text("Требования по % по ОД-Внебаланс"), text("1 863.01"), text("RUB"), text("1 863.01"));
        $("[items='stepsData'] [ng-repeat='item in items']", 2).shouldHave
                (text("26"), text("Восстановление резерва по текущему ОД"), text("Резерв по текущему ОД"), text("3 064.47"), text("RUB"), text("3 064.47"));
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКА-2381 ФЛ Зуев Андрей Никол']")).click();
        //Погашение текущей задолженности за период с 17.08.2017 по 16.09.2017
        try{
          $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
      }
      catch (ElementNotFound e)
      {
          $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКА-2381 ФЛ Зуев Андрей Никол']")).click();
          $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).click();
      }
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='История операций']]")).shouldBe(attribute("class", "ng-scope active"));
        System.out.println($("[data='operations'] [ng-repeat='row in data.rows']", 3).getText());
        $("[data='operations'] [ng-repeat='row in data.rows']", 3).shouldHave
                (matchText("^[0-9]\\d*"), text("Погашение задолженностей"), text("16.09.2017"), text("Погашение текущей задолженности за период с 17.08.2017 по 16.09.2017"), text("Исполнена")).find(By.cssSelector("a")).click();
        $$("[items='stepsData'] [ng-repeat='item in items']").shouldHaveSize(11);
        $("[items='stepsData'] [ng-repeat='item in items']", 0).shouldHave
                (text("1"), text("Погашение текущего ОД"), text("Задолженность по ОД"), text("23 611.11"), text("RUB"), text("23 611.11"));
        $("[items='stepsData'] [ng-repeat='item in items']", 1).shouldHave
                (text("3"), text("Погашение текущих процентов (внебаланс)"), text("Требования по % по ОД-Внебаланс"), text("9 926.37"), text("RUB"), text("9 926.37"));
        $("[items='stepsData'] [ng-repeat='item in items']", 2).shouldHave
                (text("5"), text("Погашение процентов по просроченному ОД (внебаланс)"), text("Требования по % по просроченному ОД-внебаланс"), text("401.07"), text("RUB"), text("401.07"));
        $("[items='stepsData'] [ng-repeat='item in items']", 3).shouldHave
                (text("6"), text("Погашение просроченного ОД"), text("Задолженность по просроченному ОД"), text("23 611.11"), text("RUB"), text("23 611.11"));
        $("[items='stepsData'] [ng-repeat='item in items']", 4).shouldHave
                (text("7"), text("Погашение просроченных процентов (баланс)"), text("Требования по просроченным %-Баланс"), text("8 577.63"), text("RUB"), text("8 577.63"));
        $("[items='stepsData'] [ng-repeat='item in items']", 5).shouldHave
                (text("8"), text("Погашение просроченных процентов (внебаланс)"), text("Требования по просроченным %-Внебаланс"), text("1 649.54"), text("RUB"), text("1 649.54"));
        $("[items='stepsData'] [ng-repeat='item in items']", 6).shouldHave
                (text("18"), text("Погашение неустойки по просроченным % без предварительного начисления по счетам"), text("Требования по неустойке по просроченным % - начисление в КМ"), text("312.70"), text("RUB"), text("312.70"));
        $("[items='stepsData'] [ng-repeat='item in items']", 7).shouldHave
                (text("20"), text("Погашение неустойки по просроченному ОД без предварительного начисления по счетам"), text("Требования по неустойке по просроченному ОД - начисление в КМ"), text("721.92"), text("RUB"), text("721.92"));
        $("[items='stepsData'] [ng-repeat='item in items']", 8).shouldHave
                (text("26"), text("Восстановление резерва по текущему ОД"), text("Резерв по текущему ОД"), text("1 180.56"), text("RUB"), text("1 180.56"));
        $("[items='stepsData'] [ng-repeat='item in items']", 9).shouldHave
                (text("28"), text("Восстановление резерва по просроченному ОД"), text("Резерв по просроченному ОД"), text("1 180.56"), text("RUB"), text("1 180.56"));
        $("[items='stepsData'] [ng-repeat='item in items']", 10).shouldHave
                (text("29"), text("Восстановление резерва по просроченным процентам (баланс)"), text("Резерв по просроченным %-Баланс"), text("428.88"), text("RUB"), text("428.88"));
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();



    }

    @Test(priority = 3)
    public void Operation8400() {
        $(By.xpath(".//div[@value='ДКА-8400']")).shouldBe(exist, enabled).click();
        try {
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        } catch (ElementNotFound ex) {
            $(By.xpath(".//div[@value='ДКА-8400']")).shouldBe(exist, enabled).click();
            $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        }
        $(linkText("Начислить проценты")).click();
        $("#field").shouldBe(exist).clear();
        $("#field").sendKeys("16082017");
        $("[ng-click='ok()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()']").shouldBe(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Распоряжение
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Добавить распоряжение")).click();
        $("#registrationDate").shouldBe(exist).clear();
        $("#registrationDate").sendKeys("14092017");
        $("#operationDate").sendKeys("14092017");
        $(".icheckbox_square-green").click();
        $("[ng-click='next()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Погашение задолженности
        $(linkText("Сопровождение КД")).shouldBe(enabled).click();
        $(linkText("Погасить задолженность")).click();
        $("#accountId").shouldBe(exist).selectOptionContainingText("47422810100000008400");
        $("#repaymentDate").clear();
        $("#repaymentDate").sendKeys("14092017");
        $("#balance").shouldBe(attribute("class", "form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $("[ng-click='doRepaymentOperation()']").click();
        $("#page-top [style='z-index: 1040;']").shouldBe(exist);
        $("[ng-click='$ctrl.cancel()'][type='button']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        //Проверка графика
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Графики']]")).click();
        $(By.xpath("//div[@class='wrapper-content ng-scope']//li[.//*[text()='Графики']]")).shouldBe(attribute("class", "ng-scope active"));
        $(linkText("График погашения основного долга")).click();

       $("#paymentAmount").shouldHave(value("15 903.97"));
        $(By.xpath("//ol[contains(@class,'breadcrumb')]//*[text()='КД №ДКА-8400 ФЛ Шмаков Виталий Се']")).click();
        $(".breadcrumb").find(linkText("Кредитные договоры")).click();
    }
}