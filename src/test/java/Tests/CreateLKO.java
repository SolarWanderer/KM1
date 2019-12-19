package Tests;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateLKO extends Setup {
    String chars = "123456789";
    @Test
    public void CreateLKO (){
        $("[title='Создать'] .fa-plus").shouldBe(exist).click();
        $("#selectBikWindow[ng-model='$ctrl.bik'][placeholder='БИК']").should(exist);
        $("#selectBikWindow[ng-model='$ctrl.bik'][placeholder='БИК']").sendKeys(randomString(chars, 9));
        $("[type='button'][ng-click='$ctrl.ok()']").click();
        //$("[type='button'][ng-click='$ctrl.ok()']").click();
        ClickAndWaitModal("[type='button'][ng-click='$ctrl.ok()']");
        $("#shortName").sendKeys(name);
        $("#corrAccNum").sendKeys(randomString(chars,5));
        $("#kgrko").sendKeys(randomString(chars,5));
            kgrko=$("#kgrko").getValue();
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 2 из 6"));
        $("#licRevocationDate").shouldBe(enabled).sendKeys("11082017");
        $("#bankruptcyResolutionDate").shouldBe(enabled).sendKeys("12082017");
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 3 из 6"));
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 4 из 6"));
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 5 из 6"));
        $("#ogrn").shouldBe(enabled).sendKeys(randomString(chars,13));
        $("#inn").sendKeys(str);
        $(" #kpp").sendKeys(randomString(chars,9));
        $("[ng-click='next()'][type='button']").click();
        $(By.id("stepWizard")).shouldBe(matchesText("Шаг 6 из 6"));
        $("[type='button'][ng-click='next()']").click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        //sleep(10000);
    }
}
