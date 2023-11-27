package Pages;

import Base.TestBase;
import TestUtils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TaxGroupPage extends TestBase {

    public TaxGroupPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    @FindBy(xpath = "//*[@id=\"taxes\"]/div/div[1]/span/span[2]/span")
    WebElement taxes;

    @FindBy(xpath = "//*[@id=\"taxes\"]/div/div[2]/ul[2]/li[1]")
    WebElement tax;

    @FindBy(id = "taxnamear")
    WebElement arname;

    @FindBy(id = "taxnameen")
    WebElement ename;

    @FindBy(xpath = "//*[@id=\"form-container\"]/form/div[4]/button")
    WebElement submit;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-taxgroup/div/div[2]/table/tbody/tr[2]/td[4]/a")
    WebElement edit;

    ArrayList<WebElement> list = new ArrayList<>();

    public void enterTaxGroupData(String arabicName , String EnglishName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(arname));
        Thread.sleep(1000);
        arname.clear();
        arname.sendKeys(arabicName);
        wait.until(ExpectedConditions.visibilityOf(ename));
        Thread.sleep(1000);
        ename.clear();
        ename.sendKeys(EnglishName);
        wait.until(ExpectedConditions.visibilityOf(taxes));
        taxes.click();
        wait.until(ExpectedConditions.visibilityOf(tax));
        tax.click();
    }

    public void updateData() throws IOException, InterruptedException {
        String temp =    "edit" +  TestUtils.getCounter();
        TestUtils.updateCounter();
        ename.clear();
        ename.sendKeys(temp);
        clickSubmit();
        driver.navigate().refresh();
        Thread.sleep(1000);
        list = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
    }

    public ArrayList<WebElement> updatedData() {
        return list;
    }


    public void clickSubmit(){
        //submit
        submit.click();
    }

    public void clickEdit(){
        //edit
        edit.click();
    }

}
