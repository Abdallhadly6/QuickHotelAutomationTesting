package TestCases;

import Base.TestBase;
import Pages.GuestPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RoomPage;
import TestUtils.TestUtils;
import com.google.zxing.NotFoundException;
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
import java.net.URISyntaxException;
import java.util.List;

public class GuestPageTest extends TestBase {
    public GuestPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    GuestPage guestPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        guestPage = new GuestPage();

        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();

    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test()
    public void addNewGuest() throws IOException, AWTException, InterruptedException {
        homePage.clickRoomAndGuests();
        homePage.clickGuest();
        String temp = "05478947" + TestUtils.getCounter();
        TestUtils.updateCounter();
        guestPage.enterGuestData("auto" , temp);
        guestPage.clickSubmit();
        java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void updateGuestData() throws IOException, AWTException, InterruptedException {
        homePage.clickRoomAndGuests();
        homePage.clickGuest();
        guestPage.clickEdit();
        guestPage.updateData();
        Assert.assertTrue(guestPage.updatedData().size() > 0);
    }

}
