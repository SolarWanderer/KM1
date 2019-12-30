package Tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;

public class ServiserBank extends Setup {

    public void WritePacket () throws IOException {
        String[] name={"GRAPHS.txt","INPUT_AMOUNTS.txt","LOANS.txt","REST_ACCOUNTS.txt"};
        if(id2139==null)
        {
            id2139="9180";
        }
        File myFoo = new File("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/"+name[0]);
        FileOutputStream fooStream = new FileOutputStream(myFoo, false);
        byte[] myBytes = ("427547|25|"+id2139+"|11.11.2017|12.10.2017|11.11.2017|44488,9|208272,92|41938,8|2550,1\n" +
                "427547|25|"+id2139+"|11.12.2017|12.11.2017|11.12.2017|44488,9|165838,22|42434,7|2054,2\n" +
                "427547|25|"+id2139+"|11.01.2018|12.12.2017|11.01.2018|44488,9|123039,51|42798,71|1690,19\n" +
                "427547|25|"+id2139+"|11.02.2018|12.01.2018|11.02.2018|44488,9|79804,6|43234,91|1253,99\n" +
                "427547|25|"+id2139+"|11.03.2018|12.02.2018|11.03.2018|44488,9|36050,34|43754,26|734,64\n" +
                "427547|25|"+id2139+"|11.04.2018|12.03.2018|11.04.2018|36417,76|0|36050,34|367,42").getBytes();
        fooStream.write(myBytes);
        myFoo = new File("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/"+name[1]);
         fooStream = new FileOutputStream(myFoo, false);
        myBytes = ("1|"+id2139+"|2|643|250211,72|250211,72|250211,72|250211,72|250211,72|250211,72|11.10.2017").getBytes();
        fooStream.write(myBytes);

        myFoo = new File("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/"+name[2]);
        fooStream = new FileOutputStream(myFoo, false);
        myBytes = (id2139+"|ДКИ-2139|11.05.2013|Жуков Сергей Игоревич|46 11 668684|219997182828|||1|1|||").getBytes();
        fooStream.write(myBytes);

        myFoo = new File("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/"+name[3]);
        fooStream = new FileOutputStream(myFoo, false);
        myBytes = ("1|45507810700000002139|1|"+id2139+"|11.10.2017|250211,72|250211,72\n" +
                "2|47422810000000002139|59|"+id2139+"|11.10.2017|0|0").getBytes();
        fooStream.write(myBytes);
        fooStream.close();
    }

    private static void copyFileUsingChannel() throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        if(kgrko==null)
        {
            kgrko="48654";
        }
       File source= new File("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/Операции_2207_1111_20171010_20171011_20171012100000.xlsx");
       File dest=new File("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/Операции_"+kgrko+"_1111_20171010_20171011_20171012100000.xlsx");
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }
    @Test(priority = 0)
    public void UpdateFilesServiser() throws IOException {
        WritePacket();
        copyFileUsingChannel();
    }

  @Test(priority = 1)
    public void MoveToULReestr()  {
        GoToReestr("Контрагенты", "Юридические лица");
        $$(".breadcrumb").findBy(text("Юридические лица")).shouldBe(visible);
        $$("[ng-repeat='row in data.rows']").shouldHave(CollectionCondition.sizeGreaterThan(3));

    }

   @Test(priority = 2)
    public void CreateUL(){
        $("[title='Создать']").shouldBe(exist,enabled).click();
        $("#shortName").shouldBe(enabled).sendKeys(serviser);
        $("#ogrn").sendKeys("6437325323734");
        $("#inn").sendKeys("3008231904");
        $("#kpp").sendKeys("754768475");
        $$(".form-group").findBy(text("Кредитная организация")).find(".iCheck-helper").click();
        $$(".form-group").findBy(text("Банк-сервисер")).find(".iCheck-helper").click();
        $("#kgrko").sendKeys("1111");
        $("#bic").sendKeys("044525225");
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 2 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 3 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 4 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 5 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 6 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 7 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 8 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 9 из 10"));
        $("#next").click();
        $("#stepWizard").shouldHave(text("Шаг 10 из 10"));
        $("#next").click();
        $$("[ng-repeat='row in data.rows']").findBy(text(serviser));

    }



 @Test(priority = 3)
 public void MoveToPortfolioBank(){
     GoToReestr("Договоры", "Портфели");
     GoToReestr("Портфели", "Для Банка-сервисера");
     $$(".breadcrumb").findBy(text("Портфели для Банка-сервисера")).shouldBe(visible);

 }

 @Test(priority = 4)
 public void CreatePortfoliBank(){

     $("[title='Создать']").shouldBe(enabled).click();
     $("#name").shouldBe(enabled).sendKeys("Для Банка-сервисера");
     $("[ng-click='findServiceBank()']").click();
     $$("[ng-repeat='row in data.rows']").findBy(text("Банк-сервисер")).find(".iCheck-helper").click();
     $("[title='Выбрать']").click();
     $("#serviceBankId").shouldHave(value(serviser+" 1111"));
     $("#next").click();
     $("#stepWizard").shouldHave(text("Шаг 2 из 2"));
     $$("[ng-repeat='row in data.rows']").findBy(text("ДКИ-2139")).find(".iCheck-helper").click();
     $("[title='Включить выбранные КД']").click();
     ClickAndWaitModal("[ng-click='$ctrl.ok()']");
     $("#next").click();
     //$$("[ng-repeat='row in data.rows']").findBy(text("Для Банка-сервисера"));


 }




@Test(priority = 5)
public void UnloadPortfolio() {
    $$("[ng-repeat='row in data.rows']").findBy(text("Для Банка-сервисера")).find(By.linkText("Для Банка-сервисера")).click();
    $(By.linkText("Операции")).shouldBe(enabled).click();
    $(By.linkText("Завершить формирование портфеля")).click();
    $("#portfolioStatus").shouldHave(matchesText("Сформирован"));
    $(By.linkText("Операции")).shouldBe(enabled).click();
    $(By.linkText("Выгрузить данные")).click();
    $("#date").shouldBe(enabled).clear();
    $("#date").sendKeys("11.09.2017");
    $(".modal-header").click();
    $("[ng-click='uploadPortfolioDataForServiceBank()']").shouldBe(enabled).click();
    ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    $(By.linkText("Операции")).shouldBe(enabled).click();
    $(By.linkText("Подтвердить передачу Банку-сервисеру")).click();
    $("#date").shouldBe(enabled).clear();
    $("#date").sendKeys("11.09.2017");
    $(".modal-header").click();
    ClickAndWaitModal("[ng-click='$ctrl.ok()']");
    $("#portfolioStatus").shouldHave(matchesText("Передан"));

    }




@Test(priority = 6)
public void LoadoadPayment2139() {
GoToReestr("Бухгалтерский учет", "Входящие платежи");
    $$(".breadcrumb").findBy(text("Входящие платежи")).shouldBe(exist);
    $$("[ng-repeat='row in data.rows']").shouldHave(CollectionCondition.sizeGreaterThan(0));


    $(By.linkText("Импорт")).click();
    $(By.linkText("Новая загрузка")).click();
    $(By.id("loanKindId")).shouldBe(exist).selectOptionContainingText("Файловый обмен");
    $(By.xpath("//input[@id='excelFile']")).sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/Реестр входящих платежей для банка-сервисера.xlsx");
    $(By.xpath("//i[@class='glyphicon glyphicon-ok']")).shouldBe(exist);
    $(By.xpath("//button[text()='Загрузить']")).click();
    //$(By.xpath("//button[text()='Закрыть']")).shouldBe(exist).click();
    ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    try {
       $(".breadcrumb").find(By.linkText("Входящие платежи")).click();
       // $(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);
        $$("[ng-repeat='row in data.rows']").findBy(text("10.10.2017"));

    }
    catch (ElementNotFound ex)
    {  $(".breadcrumb").find(By.linkText("Входящие платежи")).click();
        $("[title='Обновить']").click();
        //$(By.xpath("//A[text()='1']/../../..//div[@class='icheckbox_square-green']")).shouldBe(exist);
        $$("[ng-repeat='row in data.rows']").findBy(text("10.10.2017"));

    }
    $$("[ng-repeat='row in data.rows']").findBy(text("10.10.2017")).find(".iCheck-helper").click();
    $(By.linkText("Операции")).click();
    $(By.linkText("Автоквитовка")).click();
    ClickAndWaitModal("[ng-click='$ctrl.cancel()']");



}


@Test(priority = 7)
public void MoveToJournal() {


    $$("[ng-repeat='row in data.rows']").shouldHave(CollectionCondition.sizeGreaterThan(0));
    GoToReestr("Договоры","Договоры"); //закрываем меню Договоры
    GoToReestr("Обмен данными", "Журнал взаимодействия с Банками-сервисерами");
    $$(".breadcrumb").findBy(text("Журнал взаимодействия с Банками-сервисерами")).shouldBe(visible);


}
    @Test(priority = 8)
    public void OperationsServiser()
    {
        $$("[ng-repeat='row in data.rows']").shouldHave(CollectionCondition.sizeGreaterThan(0));
        $(By.linkText("Операции")).click();
        $(By.linkText("Загрузка реестра операций от Б-С")).click();
        $("#serviceBankId").shouldBe(exist).selectOptionContainingText(serviser);
        $("#documentSelectionButton").sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/Операции_"+kgrko+"_1111_20171010_20171011_20171012100000.xlsx");
        $("[ng-click='createDocument()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

    }
  @Test(priority = 9)
  public void LoadPackServ(){
      String path="C:/Users/k.melnikov/Documents/tests/Банк-сервисер/";
      $(byText("Операции")).click();
      $(byText("Возврат договоров")).click();
      $("#serviceBank").selectOption(serviser);
      $("#unloadDate").sendKeys("11102017", Keys.TAB);

     // $("#addFileControl").sendKeys(path+"GRAPHS.txt",path+"INPUT_AMOUNTS.txt",path+"LOANS.txt",path+"REST_ACCOUNTS.txt"
      //,path+"CREDIT_CARDS.txt",path+"PACK_DATA.txt");
      $("#addFileControl").sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/GRAPHS.txt");
      $("#addFileControl").sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/INPUT_AMOUNTS.txt");
      $("#addFileControl").sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/LOANS.txt");
      $("#addFileControl").sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/REST_ACCOUNTS.txt");
      $("#addFileControl").sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/CREDIT_CARDS.txt");
      $("#addFileControl").sendKeys("C:/Users/k.melnikov/Documents/tests/Банк-сервисер/PACK_DATA.txt");
      $$("[value='item.progress'] [aria-valuetext='100%']").shouldBe(size(6));
      $("button[ng-click='createLoading()']").click();
      $$("[ng-repeat='file in packData.content']").shouldBe(size(6));
      $(By.linkText("Действия с пакетом")).click();
      $(By.linkText("Запустить загрузку")).click();
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

    @Test(priority = 10)
    public void Checkkd(){
        GoToReestr("Договоры", "Кредитные договоры");
        $$("[ng-repeat='row in data.rows']").findBy(text("ДКИ-2139")).find(linkText("ДКИ-2139")).click();
        $(".navbar-navig").find(linkText("История операций")).click();
        $(".navbar-navig").find(linkText("История операций")).parent().shouldBe(attribute("class", "ng-scope active"));
        //$$("[data='operations'] [ng-repeat='row in data.rows']").shouldHaveSize(12);
        $$("[data='operations'] [ng-repeat='row in data.rows']").filterBy(text("Исполнена")).shouldHaveSize(3);
        $$("[data='operations'] [ng-repeat='row in data.rows']").filterBy(text("Архивная")).shouldHaveSize(9);
    }
}


