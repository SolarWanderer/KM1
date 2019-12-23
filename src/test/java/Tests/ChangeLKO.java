package Tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ChangeLKO extends Setup {
    @Test
    public void changeLKO(){
        $("[ng-click='openOrganizationSelection()']").click();
        $("h4.modal-title").shouldHave(Condition.matchesText("Выбор организации"));
        sleep(1000);
        try {
            $(By.xpath(xpath)).scrollIntoView(false).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath(xpath)).scrollIntoView(false).click();
        }


        //$(By.linkText(name)).scrollIntoView(true).click();
       // $("[ng-click='$ctrl.ok()'][type='button']").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()'][type='button']");
       // $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));




    }
}
