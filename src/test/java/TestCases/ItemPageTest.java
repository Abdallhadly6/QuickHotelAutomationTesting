package TestCases;

import Base.TestBase;
import Pages.CategoryPage;
import Pages.HomePage;
import Pages.ItemPage;
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

public class ItemPageTest extends TestBase {
    public ItemPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    ItemPage itemPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        itemPage = new ItemPage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test()
    public void addNewItem() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickItems();
        String englishName = "test" + TestUtils.getCounter();
        TestUtils.updateCounter();
        itemPage.enterItemData(englishName);
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + englishName + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void updateItemData() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickItems();
        String temp = "update" + TestUtils.getCounter();
        TestUtils.updateCounter();
        itemPage.updateItem(temp);
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void deleteItem() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickItems();
        itemPage.deleteItem();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + itemPage.itemName() + "')]"));
        System.out.println(itemPage.itemName());
        Assert.assertFalse(list.size() > 0);
    }

    @Test()
    public void addAttributesToItem() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickItems();
        itemPage.addAttributes();
    }

}
