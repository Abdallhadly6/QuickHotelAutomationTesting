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
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginPage extends TestBase {

    public LoginPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }


    JavascriptExecutor js =( (JavascriptExecutor) driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    //
    @FindBy(id = "username")
    WebElement userId;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonLogin;

    @FindBy(xpath = "/html/body/app-root/div/main/app-login/div/div/div/div[2]/div/div/form/div[3]/div/span/a")
    WebElement forgetPass;


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

    public void acceptAlert() throws InterruptedException {
        //alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

}
