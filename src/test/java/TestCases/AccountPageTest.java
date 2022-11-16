package TestCases;

import Base.TestBase;
import Pages.AccountPage;
import Pages.HomePage;
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

public class AccountPageTest extends TestBase {
    public AccountPageTest() throws IOException {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException {
        log = extentReports.startTest(method.getName());
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        accountPage = new AccountPage();
        // login first
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {
        tearDown(method,result);
    }

    @Test(dataProvider = "accountData")
    public void checkAccountEditData(String name) throws IOException, InterruptedException {
        homePage.clickSettings();
        homePage.clickAccount();
        accountPage.updateData(name);
        accountPage.clickSubmit();
        accountPage.acceptAlert();
        accountPage.checkSuccessOfUpdate();
        Assert.assertEquals(accountPage.updatedData().get(0), name);
    }

    @DataProvider
    public Object[][] accountData() throws IOException {
        Object[][] data = TestUtils.getDatFromExcel("account");
        return data;

    }
}
