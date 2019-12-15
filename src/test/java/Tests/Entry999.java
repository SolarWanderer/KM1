package Tests;

import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Entry999 extends Setup {

    @Test
    public void entry() {
        String txt=$(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).getAttribute("class");
                if (txt.equals("ng-scope"))
                {
                    $(By.xpath(".//span[normalize-space(.)='Бухгалтерский учет']/../..")).click();
                }
        $(By.xpath(".//li[normalize-space(.)='Проводки']")).click();
        $(By.linkText("Операции")).shouldHave(exist).click();

        $(By.linkText("Создать")).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.xpath("//div//label//*[text()='Шифр *']/../../div//km-select")).click();
        $(By.xpath("//km-select[@id='entryCodeId']//li//a[text()='09 - Мемориальный ордер']/..")).click();
        $(By.id("docDate")).clear();
        $(By.id("docDate")).sendKeys("11082017");
        $(By.id("entryDate")).clear();
        $(By.id("entryDate")).sendKeys("11082017");
        $(By.xpath("//div//label//*[text()='Глава *']/../../div//km-select")).click();
        $(By.xpath("//km-select[@id='accChapter']//li//a[text()='В. Внебалансовые счета']/..")).click();
        $(By.id("reason")).sendKeys("проводка");
        $(By.xpath("//button[@ng-click='findDtAccnum()']")).click();
        //$(By.xpath("//button[@ng-click='findDtAccnum()']")).click();
        $(By.linkText("Реестр проводок")).shouldBe(exist).scrollIntoView(true);
       // this.actions.moveToElement(driver.findElement(By.linkText("Реестр проводок"))).perform();
        $$("[ng-class='getRowClass(row)']").shouldHaveSize(30);
        sleep(2000);
        $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).shouldBe(exist).sendKeys("9999",Keys.ENTER);
       // driver.findElement(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).sendKeys(new CharSequence[]{"9999", Keys.ENTER});
       // $(By.xpath("//td//div[@value='99998810000000000001']/../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        try {
            $(By.xpath("//td//div[@value='99998810000000000001']/../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).clear();
            $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).shouldBe(exist).sendKeys("9999",Keys.ENTER);
            $(By.xpath("//td//div[@value='99998810000000000001']/../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        }
        //driver.findElement(By.xpath("//td//div[@value='99998810000000000001']/../..//div[@class='icheckbox_square-green']")).click();
        $(By.id("selectButton")).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.id("dtAmount")).sendKeys("20000000");
        //driver.findElement(By.id("dtAmount")).sendKeys(new CharSequence[]{"20000000"});
        $(By.xpath("//h5[text()='Сумма Дт *']")).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
        $(By.xpath("//button[@ng-click='findKtAccnum()']")).click();
        $$("[ng-class='getRowClass(row)']").shouldHaveSize(30);
        sleep(2000);
        $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).shouldBe(exist).sendKeys("9999",Keys.ENTER);
        try {
            $(By.xpath("//td//div[@value='99999810000000000001']/../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        }
        catch (ElementNotFound ex)
        {
            $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).clear();
            $(By.xpath("(//TH[normalize-space(text())='Номер счета']/../..//INPUT[@ng-model='fieldValue'])[1]")).shouldBe(exist).sendKeys("9999",Keys.ENTER);
            $(By.xpath("//td//div[@value='99999810000000000001']/../..//div[@class='icheckbox_square-green']")).shouldBe(exist).click();
        }

        $(By.id("selectButton")).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));

        $(By.id("execute")).click();
        $("[modal-render='true'][tabindex='-1']").shouldBe(not(visible));
    }
}
