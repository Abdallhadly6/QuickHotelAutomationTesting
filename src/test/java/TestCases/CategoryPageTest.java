package TestCases;

import Base.TestBase;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class CategoryPageTest extends TestBase {
    public CategoryPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    CategoryPage categoryPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        categoryPage = new CategoryPage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test()
    public void addNewCategory() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickCategories();
        String englishName = "englishName" + TestUtils.getCounter();
        TestUtils.updateCounter();
        categoryPage.enterCategoryData("arabicName",englishName);
        categoryPage.clickSubmit();
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + englishName + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void updateCategoryData() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickCategories();
        String temp = "update" + TestUtils.getCounter();
        TestUtils.updateCounter();
        categoryPage.updateCategory("تحديث",temp);
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void deleteCategory() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickCategories();
        categoryPage.deleteCategory();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + categoryPage.categoryName() + "')]"));
        System.out.println(categoryPage.categoryName());
        Assert.assertFalse(list.size() > 0);
    }

}
