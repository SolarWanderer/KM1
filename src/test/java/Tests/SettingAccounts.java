package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class SettingAccounts extends Setup {
    @Test(priority = 0)
    public void MoveToKdReestr() {

        $(By.xpath("//span[text()='Бухгалтерский учет']")).click();
        $(By.linkText("Наборы счетов")).click();
    }
    @Test(priority = 1)
    public void fl(){
        $(By.linkText("Кредиты, выданные Гражданам (физическим лицам)")).should(exist).scrollIntoView(true).click();
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
        $(By.id("picEdit")).should(exist).click();
        $(By.id("144")).selectOptionContainingText("70601810000003711505");
        $(By.id("145")).selectOptionContainingText("70606810000003711503");
        $(By.id("155")).selectOptionContainingText("70606810000001511504");
        $(By.id("8")).selectOptionContainingText("70601810000002840101");
        $(By.id("10")).selectOptionContainingText("70601810000001111501");
        $(By.id("20")).selectOptionContainingText("99998810000000000001");
        $(By.id("21")).selectOptionContainingText("99999810000000000001");
        $(By.id("41")).selectOptionContainingText("70601810000001511501");
        $(By.id("42")).selectOptionContainingText("70606810000003711501");
        $(By.id("58")).selectOptionContainingText("70601810000001111502");
        $(By.id("66")).selectOptionContainingText("70601810000001511502");
        $(By.id("70")).selectOptionContainingText("70601810000001111503");
        $(By.id("81")).selectOptionContainingText("70601810000001511503");
        $(By.id("84")).selectOptionContainingText("70606810000003711502");
        $(By.id("85")).selectOptionContainingText("47422810000000000001");
        $(By.id("179")).selectOptionContainingText("10801810000000000001");
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
        $(By.id("picSave")).should(exist).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.linkText("Наборы общих счетов")).click();
    }
    @Test(priority = 2)
    public void UL(){
        $(By.linkText("Кредиты, выданные Негосударственным коммерческим организациям")).should(exist).scrollIntoView(true).click();
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
        $(By.id("picEdit")).should(exist).click();
        $(By.id("122")).selectOptionContainingText("70606810100003711203");
        $(By.id("144")).selectOptionContainingText("70601810000003711206");
        $(By.id("145")).selectOptionContainingText("70606810000003711205");
        $(By.id("8")).selectOptionContainingText("70601810100002840104");
        $(By.id("10")).selectOptionContainingText("70601810100001111201");
        $(By.id("20")).selectOptionContainingText("99998810000000000001");
        $(By.id("21")).selectOptionContainingText("99999810000000000001");
        $(By.id("41")).selectOptionContainingText("70601810100001511201");
        $(By.id("42")).selectOptionContainingText("70606810100003711201");
        $(By.id("58")).selectOptionContainingText("70601810100001111202");
        $(By.id("66")).selectOptionContainingText("70601810100001511202");
        $(By.id("67")).selectOptionContainingText("70601810100001511204");
        $(By.id("70")).selectOptionContainingText("70601810100001111203");
        $(By.id("81")).selectOptionContainingText("70601810100001511203");
        $(By.id("84")).selectOptionContainingText("70606810100003711202");
        $(By.id("85")).selectOptionContainingText("47422810000000000001");
        $(By.id("96")).selectOptionContainingText("70606810100003711204");
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
         $(By.id("picSave")).should(exist).click();
         $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.linkText("Наборы общих счетов")).click();
    }
    @Test(priority = 3)
    public void DO(){
        $(By.linkText("Договоры обеспечения")).should(exist).scrollIntoView(true).click();
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
        $(By.id("picEdit")).should(exist).click();
        $(By.id("20")).selectOptionContainingText("99998810000000000001");
        $(By.id("21")).selectOptionContainingText("99999810000000000001");
        $("[ng-click='openOrganizationSelection()']").should(exist).scrollIntoView(true);
          $(By.id("picSave")).should(exist).click();
         $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.linkText("Наборы общих счетов")).click();
    }
}
