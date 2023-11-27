package TestCases;

import Base.TestBase;
import Pages.AttributeDetailsPage;
import Pages.CategoryPage;
import Pages.HomePage;
import Pages.LoginPage;
import TestUtils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class AttributeDetailsPageTest extends TestBase {
    public AttributeDetailsPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    AttributeDetailsPage attributeDetailsPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        attributeDetailsPage = new AttributeDetailsPage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test()
    public void addNewAttributeDetails() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickAttributeDetails();
        String englishName = "englishName" + TestUtils.getCounter();
        //TestUtils.updateCounter();
        attributeDetailsPage.enterAttributeDetailsData("arabicName",englishName);
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + englishName + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void updateAttributeDetailsData() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickAttributeDetails();
        String temp = "update" + TestUtils.getCounter();
        //TestUtils.updateCounter();
        attributeDetailsPage.updateAttributeDetails("تحديث2",temp);
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void deleteAttribute() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickAttributeDetails();
        attributeDetailsPage.deleteAttributeDetails();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + attributeDetailsPage.attributeDetailsName() + "')]"));
        System.out.println(attributeDetailsPage.attributeDetailsName());
        Assert.assertFalse(list.size() > 0);
    }

}
