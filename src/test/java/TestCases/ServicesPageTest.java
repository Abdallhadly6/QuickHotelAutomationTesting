package TestCases;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ServicesPage;
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

public class ServicesPageTest extends TestBase {
    public ServicesPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    ServicesPage servicesPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        servicesPage = new ServicesPage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test(dataProvider = "addNewService")
    public void addNewService(String arabicName , String englishName) throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickServices();
        servicesPage.enterServiceData(arabicName,englishName);
        servicesPage.uploadPhoto();
        Thread.sleep(10000);
        servicesPage.clickSubmit();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + arabicName + "')]"));
        Assert.assertTrue(list.size() > 0);

    }

    @Test()
    public void updateServiceData() throws IOException, AWTException, InterruptedException {
        homePage.clickMenu();
        homePage.clickServices();
        servicesPage.clickEdit();
        servicesPage.enterServiceData("تحديث","update");
        servicesPage.clickSubmit();
    }

    @DataProvider
    public Object[][] addNewService() throws IOException {
        Object[][] data = TestUtils.getDatFromExcel("addnewservice");
        return data;

    }

}
