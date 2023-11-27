package TestCases;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ServicesPage;
import Pages.UserPage;
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

public class UserPageTest extends TestBase {
    public UserPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    UserPage userPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        userPage = new UserPage();

    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test(dataProvider = "addNewUser")
    public void addNewUser(String name , String uname , String email , String pass1 , String pass2) throws IOException, AWTException, InterruptedException {
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();

        String temp1 = TestUtils.getCounter() + uname;
        String temp2 = TestUtils.getCounter() + email;
        TestUtils.updateCounter();
        homePage.clickSettings();
        homePage.clickUsers();
        userPage.enterUserData(name,temp1,temp2,pass1,pass2,"0524875455");
        userPage.clickSubmit();
        userPage.acceptAlert();
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp1 + "')]"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void ChangeUserStatus() throws IOException, AWTException, InterruptedException {
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();

        homePage.clickSettings();
        homePage.clickUsers();
        userPage.clickChange();

        String temp =  userPage.getStatus();
        if(temp.equals("check")){
            homePage.clickLogOut();
            //login as manager
            loginPage.enterData("manager",properties.getProperty("userPassword"));
            loginPage.clickLogin();
            Assert.assertTrue(homePage.getLogOutLink());
        }
        else if(temp.equals("xmark")){
            homePage.clickLogOut();
            //login as manager
            loginPage.enterData("manager",properties.getProperty("userPassword"));
            loginPage.clickLogin();
            userPage.acceptAlert();
            Assert.assertTrue(loginPage.getForgetPass());
        }
    }


    @Test()
    public void updateUserData() throws IOException, AWTException, InterruptedException {
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();

        homePage.clickSettings();
        homePage.clickUsers();
        userPage.clickEdit();
        userPage.updateData();
        Assert.assertTrue(userPage.updatedData().size() > 0);
    }

    @DataProvider
    public Object[][] addNewUser() throws IOException {
        Object[][] data = TestUtils.getDatFromExcel("addnewuser");
        return data;

    }

}
