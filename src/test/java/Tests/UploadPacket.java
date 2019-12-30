package Tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UploadPacket extends Setup {
    @Test
    public void upload(){
        $("#modulNSI [ng-click='showLko()']").shouldBe(exist);
        $("[ng-click='showDataPackets()']").click();
    $("[ng-click='click(mainCheck)']").click();
    $("#packType").shouldHave(Condition.visible);
        $("#packType").selectOptionContainingText("Загрузка 2.0. Полная");
        $("#unloadDate").sendKeys("11082017");
        $("#addFileControl").sendKeys("C:/Users/k.melnikov/Documents/tests/testpack.zip");
        $$(".glyphicon.glyphicon-ok").shouldBe(CollectionCondition.size(23));
        $("button[ng-click='createLoading()']").click();
        $("#packOrg").shouldHave(attribute("class","form-control ng-pristine ng-untouched ng-valid ng-not-empty"));
        $(By.linkText("Действия с пакетом")).click();
        $(By.linkText("Запустить загрузку")).click();
       // $("[ng-click='$ctrl.cancel()'][type='button']").shouldBe(exist).click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        Configuration.timeout=6000000;
        $("[ng-class='getClassForStatus(pack.packageStatusId)']").shouldHave(matchesText("Обработан без ошибок"));
        Configuration.timeout=timeout;
        $("[type='button'][ng-click='loadData()']").click();
        $(By.linkText("Действия с пакетом")).click();
        $(By.linkText("Утвердить загрузку")).click();
        //$("[ng-click='confirmLoad()'][type='button']").click();
        ClickAndWaitModal("[ng-click='confirmLoad()'][type='button']");
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    }
}
