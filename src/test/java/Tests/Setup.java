package Tests;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Setup {
    public static String str;
    public static String name;
    public static String xpath;
    public static String product;
    public static String id2139=null;
    public static String kgrko=null;
    public static String serviser;
    public static int timeout;

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
        Configuration.timeout=15000;
        $(css).shouldBe(exist).click();
        try {
            $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        }
        catch (ElementShould ex)
        {
            $(css).shouldBe(exist).click();
            $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        }
        Configuration.timeout=timeout;
    }
    public static void TryStructure(String value){
       sleep(1000);
       Configuration.timeout=15000;
       try {
           $("[ng-click='continueDebtStructure()']").click();
           $(".table [ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text(value), text("RUB"));
       }
       catch (ElementShould ex){
           $("[ng-click='continueDebtStructure()']").click();
           $(".table [ng-repeat='c in sum']").shouldHave(text("ИТОГО:"),text(value), text("RUB"));
    }
        Configuration.timeout=timeout;
   }
   public static void GoToReestr(String main, String menu)
   {

       boolean a=  $$("#side-menu li").findBy(text(main)).find("ul").getAttribute("class").endsWith("collapse in");
       System.out.println("a= "+!a);
       if (!a)
       {
          // System.out.println($$("#side-menu li").findBy(text(main)).find(By.linkText(main)).getAttribute("class"));
           $$("#side-menu li").findBy(text(main)).find(By.linkText(main)).click();
           sleep(1000);
       }
       $$("#side-menu li").findBy(text(main)).find(By.linkText(menu)).click();
   }
public static void GetIdKd() {
    Pattern pt = Pattern.compile("=([\\d]+?)&");
    Matcher mt = pt.matcher(url());
    if (mt.find()) {
        id2139 = mt.group(1);
    } else {
        System.out.println("Not found");
    }
}
public static void generate() throws IOException {
    int i=0;
    File myFoo = new File("C:/Users/k.melnikov/Documents/tests/accnumber.txt");
    FileOutputStream fooStream = new FileOutputStream(myFoo, false);
    byte[] myBytes;
    while(i<9999)
    {
        if(i<10)
        {

            myBytes=("6040781000000000000"+i+"\n").getBytes();
        }
        else if(i<100){

            myBytes=("604078100000000000"+i+"\n").getBytes();
        }
        else if (i<1000)
        {

            myBytes=("60407810000000000"+i+"\n").getBytes();
        }
        else
        {

            myBytes=("6040781000000000"+i+"\n").getBytes();
        }
        fooStream.write(myBytes);
        i++;
    }
    fooStream.close();
}

    @BeforeSuite
        public void SettingBrowser() throws IOException {
        String chars = "123456789";
        timeout=15000;
        //name = "Тесты"+randomString(chars,3);
        serviser="Банк-сервисер"+randomString(chars,3);
        product= "Кредитный продукт" + randomString(chars,3);
        name="Тест1";
        str = Generate_inn(randomString(chars, 10));
        xpath = "//DIV[@class='full-height-scroll']//TD[@class='ng-binding'][text()='" + name + "']";
        System.setProperty("webdriver.chrome.driver", "C://Users//k.melnikov//IdeaProjects//KM//chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        Configuration.startMaximized=true;
        Configuration.timeout= timeout;
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = PROXY;
        Configuration.reportsFolder="C:/Users/k.melnikov/Documents/tests/reports/dowloads";
generate();

        Configuration.holdBrowserOpen = true;
        open("http://asv-km-t-bl2.dfu.i-teco.ru/km/login");


        }


}
