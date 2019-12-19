package Tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class Login extends Setup {
    @Test
    public void login(){

        $("[placeholder='Логин']").sendKeys("melnikov");
        $("[placeholder='Пароль']").sendKeys("melnikov1");
        $("#loginButton[type='button']").click(); // Waits until element disappears
        $("#organizationButton").shouldBe(exist);
        $(By.xpath("//BODY[@id='page-top']//TD[text()='Тестовый ФО']")).click();
        $("#organizationButton").click();
        $("#modulNSI [ng-click='showLko()']").click();
        $$("[ng-repeat='row in data.rows']").shouldHave(sizeGreaterThan(0));



    }
}
