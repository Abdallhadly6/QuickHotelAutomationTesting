package TestCases;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RoomPage;
import Pages.UserPage;
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

public class RoomPageTest extends TestBase {
    public RoomPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    RoomPage roomPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        roomPage = new RoomPage();

        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();

    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test(dataProvider = "addNewRoom")
    public void addNewRoom(String aname , String ename , String number) throws IOException, AWTException, InterruptedException {
        String temp = TestUtils.getCounter() + number;
        TestUtils.updateCounter();
        homePage.clickRoomAndGuests();
        homePage.clickRoom();
        roomPage.enterRoomData(aname , ename , temp);
        roomPage.clickSubmit();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
        Assert.assertTrue(list.size() > 0);
    }


    @Test()
    public void updateRoomData() throws IOException, AWTException, InterruptedException {
        homePage.clickRoomAndGuests();
        homePage.clickRoom();
        roomPage.clickEdit();
        roomPage.updateData();
        Assert.assertTrue(roomPage.updatedData().size() > 0);
    }

    @Test()
    public void generateQrCodeForRoom() throws IOException, AWTException, InterruptedException, NotFoundException, URISyntaxException {
        homePage.clickRoomAndGuests();
        homePage.clickRoom();
        roomPage.generateQrCode();
        roomPage.openRoomUrl();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'غرفة 103')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @DataProvider
    public Object[][] addNewRoom() throws IOException {
        Object[][] data = TestUtils.getDatFromExcel("addnewroom");
        return data;
    }

//    @Test()
//    public void download()  {
//        homePage.clickRoomAndGuests();
//        homePage.clickRoom();
//        roomPage.downloadImageFromQr();
//    }

}
