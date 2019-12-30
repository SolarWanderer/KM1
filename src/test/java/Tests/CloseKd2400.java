package Tests;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class CloseKd2400 extends Setup {
@Test(priority = 0)
    public void AddAdressFL(){
    GoToReestr("Договоры","Кредитные договоры");
    $$("[ng-repeat='row in data.rows']").findBy(text("Богульников Максим Николаевич")).find("[value='Богульников Максим Николаевич']").click();
    $(".navbar-navig").find(linkText("Адреса")).click();
    $(".navbar-navig").find(linkText("Адреса")).parent().shouldBe(attribute("class", "active"));
    $("#addresses #picAdd").click();
    $("#addressType").selectOption("Почтовый адрес");
    $("[ng-click='newKladrModal()']").click();
    $("#district").sendKeys("Москва г.");
    $("[ng-click='ok()']").click();
    $("#next").click();
    $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
}
    @Test(priority = 1)
    public void IspravOp2400(){
        GoToReestr("Договоры","Кредитные договоры");
        $$("[ng-repeat='row in data.rows']").findBy(text("ДКА-2400")).find("[value='ДКА-2400']").click();
        $(linkText("Сопровождение КД")).click();
        $(linkText("Регистры")).scrollIntoView(true).click();
        $$("[ng-repeat='node in item.value']").shouldHaveSize(109);
        try {
            $(linkText("Проценты на ОД, набежавшие")).click();
            $(linkText("Операции")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(linkText("Проценты на ОД, набежавшие")).click();
            $(linkText("Операции")).shouldBe(exist).click();
        }

        $(linkText("Исправительная операция")).click();
        $("#execDate").shouldBe(exist).clear();
        $("#execDate").sendKeys("11082017");
        $("#amountSpending").clear();
        $("#amountSpending").sendKeys("1.00");
        $("#reason").sendKeys("Корректировка");
        $("#next").click();
        $("[ng-repeat='row in data.rows']",0).shouldHave(
                text("11.08.2017"),text("1.00"),text("1.00"),text("0.00"),text("RUB"),text("0.00")
        );
        $(".breadcrumb").find(linkText("КД №ДКА-2400 ФЛ Богульников Макси")).click();
    }
    @Test(priority = 2)
    public void Closekd() throws FileNotFoundException {
       $(linkText("Редактирование КД")).shouldBe(exist).click();
        $(linkText("Поставить на учет")).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        $(linkText("Сопровождение КД")).shouldBe(exist).click();
        $(linkText("Закрыть КД")).click();
        $("#closeDate").clear();
        $("#closeDate").sendKeys("16082017");
        $(".modal-header").click();
        $("[ng-click='next()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

         $("#menuOperation").find(linkText("Отчеты")).shouldBe(enabled).click();
         File file= $(linkText("Справка о факте закрытия кредита")).download();


        //$(linkText("Справка о факте закрытия кредита")).click();
    // File file= $(linkText("Справка о факте закрытия кредита")).download();



    }

}
