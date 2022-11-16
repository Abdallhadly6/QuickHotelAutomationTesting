package Pages;

import Base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;

public class AccountPage extends TestBase {

    public AccountPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @FindBy(id = "uname")
    WebElement nameField;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-myprofile/div[2]/div/div/div/div/div/div/form/div/div[7]/button")
    WebElement submit;

    public void updateData(String uname) throws InterruptedException {
        nameField.clear();
        nameField.sendKeys(uname);
    }

    public void clickSubmit() throws InterruptedException {
        //submit
        submit.click();
        Thread.sleep(2000);
    }

    public void acceptAlert() throws InterruptedException {
        //submit
        driver.switchTo().alert().accept();
    }

    public void checkSuccessOfUpdate() throws InterruptedException {
        homePage.clickLogOut();
        loginPage.enterData(properties.getProperty("userId"),properties.getProperty("userPassword"));
        loginPage.clickLogin();
        homePage.clickSettings();
        homePage.clickAccount();
        Thread.sleep(2000);
    }

    public ArrayList<String> updatedData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(nameField.getAttribute("value"));
        return data;

    }
}
