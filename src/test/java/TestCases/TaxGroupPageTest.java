package TestCases;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.TaxGroupPage;
import Pages.TaxPage;
import TestUtils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class TaxGroupPageTest extends TestBase {
    public TaxGroupPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    TaxGroupPage taxGroupPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        taxGroupPage = new TaxGroupPage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }


    @Test(dataProvider = "addNewTaxGroup")
    public void addNewTaxGroup(String arabicName , String englishName) throws IOException, AWTException, InterruptedException {
        homePage.clickSettings();
        homePage.clickTaxGroup();
        String temp = TestUtils.getCounter() + englishName;
        TestUtils.updateCounter();
        taxGroupPage.enterTaxGroupData(arabicName,temp);
        taxGroupPage.clickSubmit();
        driver.navigate().refresh();
        Thread.sleep(1000);
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void updateTaxGroupData() throws IOException, AWTException, InterruptedException {
        homePage.clickSettings();
        homePage.clickTaxGroup();
        taxGroupPage.clickEdit();
        taxGroupPage.updateData();
        Assert.assertTrue(taxGroupPage.updatedData().size() > 0);
    }

    @DataProvider
    public Object[][] addNewTaxGroup() throws IOException {
        Object[][] data = TestUtils.getDatFromExcel("addnewtaxgroup");
        return data;

    }

}