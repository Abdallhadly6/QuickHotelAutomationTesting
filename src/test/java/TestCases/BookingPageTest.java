package TestCases;

import Base.TestBase;
import Pages.BookingPage;
import Pages.GuestPage;
import Pages.HomePage;
import Pages.LoginPage;
import TestUtils.TestUtils;
import com.google.zxing.NotFoundException;
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
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

public class BookingPageTest extends TestBase {
    public BookingPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    BookingPage bookingPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        bookingPage = new BookingPage();

        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();

    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test()
    public void addNewBookingOrder() throws IOException, AWTException, InterruptedException, ParseException {
        homePage.clickBookingMenu();
        homePage.clickBooking();
        bookingPage.getNotFinishedBookingDate();
        bookingPage.enterBookingData();
    }

    @Test()
    public void generateQrCodeForBooking() throws IOException, AWTException, InterruptedException, NotFoundException, URISyntaxException {
        homePage.clickBookingMenu();
        homePage.clickBooking();
        bookingPage.generateQrCode();
        bookingPage.openRoomUrl();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'غرفة 103')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test()
    public void finishBooking() throws IOException, AWTException, InterruptedException, NotFoundException, URISyntaxException {
        homePage.clickBookingMenu();
        homePage.clickBooking();
        bookingPage.finish();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + bookingPage.getPassword() + "')]"));
        Assert.assertFalse(list.size() > 0);
    }

    @Test()
    public void updateBookingData() throws IOException, AWTException, InterruptedException {
        homePage.clickBookingMenu();
        homePage.clickBooking();
        bookingPage.updateData();
        Assert.assertTrue(bookingPage.updatedData().size() > 0);
    }

}
