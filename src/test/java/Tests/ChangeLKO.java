package Tests;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ChangeLKO extends Setup {
    @Test
    public void changeLKO(){
        $("[ng-click='openOrganizationSelection()']").click();
        $("h4.modal-title").shouldHave(Condition.matchesText("Выбор организации"));
        $(By.xpath(xpath)).scrollIntoView(true).click();
       // $("[ng-click='$ctrl.ok()'][type='button']").click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()'][type='button']");
       // $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));




    }
}
