package Pages;

import Base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginPage extends TestBase {

    public LoginPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }


    JavascriptExecutor js =( (JavascriptExecutor) driver);
    //
    @FindBy(id = "username")
    WebElement userId;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonLogin;

    @FindBy(xpath = "/html/body/app-root/div/main/app-login/div/div/div/div[2]/div/div/form/div[3]/div/span/a")
    WebElement forgetPass;

    @FindBy(xpath = "//*[@id=\"dropdownBasic1\"]/img")
    WebElement LanguageIcon1;

    @FindBy(xpath = "/html/body/app-root/div/main/app-login/app-hheader/div/div/div[1]/div/div/div/div[2]/nav/ul/li[5]/div/div/a")
    WebElement LanguageIcon2;


    @FindBy(xpath = "/html/body/app-root/div/main/app-login/div/div/div/div[2]/div/div/h5")
    WebElement LanguageCheck;






    public void enterData(String user , String pass){
        userId.clear();
        userId.sendKeys(user);
        password.clear();
        password.sendKeys(pass);
    }

    public void clickLogin(){
        //login
        buttonLogin.click();
    }

    public boolean getForgetPass() {
        //
        return forgetPass.isDisplayed();
    }

}
