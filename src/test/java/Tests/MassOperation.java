package Tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementShould;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class MassOperation extends Setup {
    @Test(priority = 0)
    public void procenti() {
        GoToReestr("Регламентные процедуры", "Начисление процентов");
        $("#field").clear();
        $("#field").sendKeys("28092017");
        $("[ng-click='ok()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    }

    @Test(priority = 1)
    public void checkmassna4(){
        GoToReestr("Регламентные процедуры", "Журнал пакетных операций");
        Configuration.timeout=10000;
        try {

            $$("[ng-repeat='row in data.rows']").find(text("Начисление процентов")).shouldHave(text("Завершена"));
        }
        catch (ElementShould e){
            $("[title='Обновить']").click();
            $$("[ng-repeat='row in data.rows']").find(text("Начисление процентов")).shouldHave(text("Завершена"));

        }
        Configuration.timeout=timeout;
        $$("[ng-repeat='row in data.rows']").find(text("Начисление процентов")).find("[key='id'] a").click();
        $$("#contracts [ng-repeat='row in data.rows']").filterBy(text("Успешно")).shouldHaveSize(17);
    }
    @Test(priority = 2)
    public void prosro4ka(){
        GoToReestr("Регламентные процедуры", "Вынесение на просрочку");
        $("#field").clear();
        $("#field").sendKeys("28092017");
        $("[ng-click='ok()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    }
    @Test(priority = 3)
    public void checkmassprosro4ka(){
        GoToReestr("Регламентные процедуры", "Журнал пакетных операций");
        Configuration.timeout=10000;
        try {

            $$("[ng-repeat='row in data.rows']").find(text("Вынесение на просрочку")).shouldHave(text("Завершена"));
        }
        catch (ElementShould e){
            $("[title='Обновить']").click();
            $$("[ng-repeat='row in data.rows']").find(text("Вынесение на просрочку")).shouldHave(text("Завершена"));

        }
        Configuration.timeout=timeout;
        $$("[ng-repeat='row in data.rows']").find(text("Вынесение на просрочку")).find("[key='id'] a").click();
        $$("#contracts [ng-repeat='row in data.rows']").filterBy(text("Успешно")).shouldHaveSize(14);
        $$("#contracts [ng-repeat='row in data.rows']").filterBy(text("Ошибка")).shouldHaveSize(3);

    }
}
