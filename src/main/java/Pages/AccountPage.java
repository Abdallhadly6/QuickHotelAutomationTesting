package Pages;

import Base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class AccountPage extends TestBase {

    public AccountPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

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
        //alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public ArrayList<String> updatedData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(nameField.getAttribute("value"));
        return data;

    }
}
