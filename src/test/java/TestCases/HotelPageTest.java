package TestCases;

import Base.TestBase;
import Pages.AccountPage;
import Pages.HomePage;
import Pages.HotelPage;
import Pages.LoginPage;
import TestUtils.TestUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class HotelPageTest extends TestBase {
    public HotelPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    HotelPage hotelPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        hotelPage = new HotelPage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test(dataProvider = "hotelData")
    public void checkHotelEditData(String eDesc) throws IOException, InterruptedException {
        homePage.clickSettings();
        homePage.clickHotel();
        String temp =  eDesc + TestUtils.getCounter();
        TestUtils.updateCounter();
        hotelPage.updateData(temp);
        hotelPage.clickSubmit();
        hotelPage.acceptAlert();
        driver.navigate().refresh();
        Thread.sleep(1000);
        Assert.assertEquals(hotelPage.updatedData().get(0), temp);
    }

    @DataProvider
    public Object[][] hotelData() throws IOException {
        Object[][] data = TestUtils.getDatFromExcel("hotel");
        return data;

    }
}
