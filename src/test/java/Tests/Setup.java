package Tests;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Setup {
    public static String str;
    public static String name;
    public static String xpath;
    public static String product;

   public static String randomString(String chars, int length) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return sb.toString();
    }

  public   static String Generate_inn(String inn) {
        int[] arg = new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
        Integer temp = 0;

        int t;
        for(t = 0; t < 10; ++t) {
            temp = temp + arg[t] * Character.getNumericValue(inn.charAt(t));
        }

        t = temp % 11;
        if (t > 9) {
            temp = t % 10;
        } else {
            temp = t;
        }

        String s = inn.substring(0, 9) + String.valueOf(temp);
        return s;
    }
    public static void ClickAndWaitModal(String css){
       sleep(1000);
        $(css).shouldBe(exist).click();
        try {
            $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        }
        catch (ElementShould ex)
        {
            $(css).shouldBe(exist).click();
            $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        }
    }
    public static void TryStructure(String value){
       sleep(1000);
       try {
           $("[ng-click='continueDebtStructure()']").click();
           $(".table [ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text(value), text("RUB"));
       }
       catch (ElementShould ex){
           $("[ng-click='continueDebtStructure()']").click();
           $(".table [ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text(value), text("RUB"));
    }
   }

    @BeforeSuite
        public void SettingBrowser() {
        String chars = "123456789";
        //name = "Тесты"+randomString(chars,3);
        product= "Кредитный продукт" + randomString(chars,3);
        name="ПМИ17.12_1";
        str = Generate_inn(randomString(chars, 10));
        xpath = "//DIV[@class='full-height-scroll']//TD[@class='ng-binding'][text()='" + name + "']";
        System.setProperty("webdriver.chrome.driver", "C://Users//k.melnikov//IdeaProjects//KM//chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        Configuration.startMaximized=true;
        Configuration.timeout= 30000;


        Configuration.holdBrowserOpen = true;
        open("http://asv-km-t-bl2.dfu.i-teco.ru/km/login");

        }


}
