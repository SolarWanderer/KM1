package Tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.linkText;

public class MassEditing extends Setup {

    public void updateExcel(File exel,int type) throws IOException, ParseException {
      //  POIFSFileSystem fileSystem =   new POIFSFileSystem(file); //Открываем документ
        FileInputStream file = new FileInputStream(exel);
        XSSFWorkbook workBook = new XSSFWorkbook(file); // Получаем workbook
        XSSFSheet sheet = workBook.getSheetAt(0); // Проверяем только первую страницу

        Iterator<Row> rows = sheet.rowIterator(); // Перебираем все строки
        // Пропускаем "шапку" таблицы
        if (rows.hasNext()) {
            rows.next();
        }
        if(type==0){
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            //Получаем ячейки из строки по номерам столбцов
            XSSFCell type1 = row.getCell(3); //ФИО
            XSSFCell ostatok = row.getCell(4); //Адрес
            XSSFCell mesto = row.getCell(5); //Номер телефона
            type1.setCellValue("ЧДП с уменьшением ежемесячного платежа");
            ostatok.setCellValue("Переходит в ЧДП");
            mesto.setCellValue("Москва");


        }
        }
        if(type==1){
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                //Получаем ячейки из строки по номерам столбцов
                XSSFCell period = row.getCell(7);
                XSSFCell rate = row.getCell(14);

                if(period.getStringCellValue().equals("Месяц")) {
                    period.setCellValue("Год");
                    int i=36;
                    rate.setCellValue(i);

                }


            }
        }
        if(type==2){
            /*rows.next();
            rows.next();
            Date date = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "16.05.2017" );
            Date date1 = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "16.05.2020" );
            XSSFRow row = (XSSFRow) rows.next();
                row.getCell(1).setCellValue("График погашения основного долга");
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue(date);
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue(date);
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue("Дифференцированная");
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue(date);
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue(date1);
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue(0);
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue(16);
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue("Месяц");
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue(1);
            row = (XSSFRow) rows.next();
            row.getCell(1).setCellValue("Не сдвигать");
            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();

            }*/

        }

        FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\k.melnikov\\Documents\\tests\\Output.xlsx"));
        workBook.write(outFile);
        outFile.close();
        file.close();
    }
    @Test(priority = 0)
    public void MoveToKdReestr() {
        GoToReestr("Договоры","Кредитные договоры");
        $$(".breadcrumb").findBy(text("Кредитные договоры")).shouldBe(exist);

    }
   @Test(priority = 1)
    public void UnloadUL() {
        $$("[ng-repeat='row in data.rows']").findBy(text("ГС-555")).find(".iCheck-helper").click();
        $$("[ng-repeat='row in data.rows']").findBy(text("ОвГС-444")).find(".iCheck-helper").click();
        $(By.linkText("Массовое редактирование")).click();
        $(By.linkText("Выгрузить ген. соглашения")).click();
        $$(".list-left li").findBy(text("Тип ЧДП")).click();
        $("[ng-click='moveRight()']").click();
        $$(".list-left li").findBy(text("Действия с остатком платежа")).click();
        $("[ng-click='moveRight()']").click();
        $$(".list-left li").findBy(text("Место хранения досье")).click();
        $("[ng-click='moveRight()']").click();
        $$(".list-right li").shouldHaveSize(6);
        $("[ng-click='acceptWithoutSave()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");

    }
    @Test(priority = 2)
    public void DowloadAndUploadUL() throws IOException, ParseException {
        $(By.linkText("Массовое редактирование")).click();
        $(By.linkText("Журнал экспорта/импорта")).click();
     File dowload =$("[ng-repeat='row in data.rows']",0).shouldHave(
                matchText("^[0-9]\\d*"), text("Экспорт"), text("Ген.соглашения"),text("Завершен")).find(By.linkText("Завершен")).download();
    updateExcel(dowload,0);
        File cv = new File("C:\\Output.xlsx");
    $("#excelFile").uploadFile(cv);
    $("#fileType").shouldHave(text("Ген.соглашения"));
    $("[ng-click='load()']").click();
    ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
        GoToReestr("Договоры","Кредитные договоры");
        $$(".breadcrumb").findBy(text("Кредитные договоры")).shouldBe(exist);
        $$("[ng-repeat='row in data.rows']").findBy(text("ОвГС-444")).find("[value='ОвГС-444']").click();
        $("#fileLocation").shouldHave(value("Москва"));
        $("#setPartPayTypeId").shouldHave(text("ЧДП с уменьшением ежемесячного платежа"));
        $("#setRestActionId").shouldHave(text("Переходит в ЧДП"));
    }



@Test(priority = 3)
public void UnloadAndUploadRates() throws IOException, ParseException {
    MoveToKdReestr();
    $$("[ng-repeat='row in data.rows']").findBy(text("ДКА-2484")).find(".iCheck-helper").click();
    $$("[ng-repeat='row in data.rows']").findBy(text("ОвГС-444")).find(".iCheck-helper").click();
    $(By.linkText("Массовое редактирование")).click();
    $(By.linkText("Выгрузить процентные ставки в режиме редактирования")).click();
    ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
    $(By.linkText("Массовое редактирование")).click();
    $(By.linkText("Журнал экспорта/импорта")).click();
    File download =$("[ng-repeat='row in data.rows']",0).shouldHave(
            matchText("^[0-9]\\d*"), text("Экспорт"), text("Процентные ставки (редактирование)"),text("Завершен")).find(By.linkText("Завершен")).download();
    updateExcel(download,1);
    File cv = new File("C:\\Output.xlsx");
    $("#excelFile").uploadFile(cv);
    $("#fileType").shouldHave(text("Процентные ставки (редактирование)"));
    $("[ng-click='load()']").click();
    ClickAndWaitModal("[ng-click='$ctrl.cancel()']");
}
/*    @Test(priority = 4)
    public void UnloadAndUploadGraph() throws IOException, ParseException {
        MoveToKdReestr();
        $$("[ng-repeat='row in data.rows']").findBy(text("ДКА-2484")).find(linkText("ДКА-2484")).click();
        $(By.linkText("Редактирование КД")).click();
        $(linkText("Выгрузка шаблона документа для создания графика")).click();
        ClickAndWaitModal("[ng-click='$ctrl.ok()']");
        File download =$("[ng-repeat='row in data.rows']",0).shouldHave(
                matchText("^[0-9]\\d*"), text("Экспорт"), text("График (создание)"),text("Завершен")).find(By.linkText("Завершен")).download();
        updateExcel(download,2);
        File cv = new File("C:\\Output.xlsx");
        //TODO 123
        *//*$("#excelFile").uploadFile(cv);
        $("#fileType").shouldHave(text("График (создание)"));
        $("[ng-click='load()']").click();
        ClickAndWaitModal("[ng-click='$ctrl.cancel()']");*//*


    }*/
}
