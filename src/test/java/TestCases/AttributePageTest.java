package TestCases;

import Base.TestBase;
import Pages.AttributePage;
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

public class AttributePageTest extends TestBase {
    public AttributePageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    AttributePage attributePage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        attributePage = new AttributePage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test()
    public void addNewAttribute() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickAttributes();
        String englishName = "englishName" + TestUtils.getCounter();
        TestUtils.updateCounter();
        attributePage.enterAttributeData("arabicName",englishName);
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + englishName + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void updateAttributeData() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickAttributes();
        String temp = "update" + TestUtils.getCounter();
        TestUtils.updateCounter();
        attributePage.updateAttribute("تحديث",temp);
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void deleteAttribute() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickAttributes();
        attributePage.deleteAttribute();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + attributePage.attributeName() + "')]"));
        System.out.println(attributePage.attributeName());
        Assert.assertFalse(list.size() > 0);
    }

}
