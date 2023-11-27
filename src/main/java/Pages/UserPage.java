package Pages;

import Base.TestBase;
import TestUtils.TestUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class UserPage extends TestBase {

    public UserPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "userType")
    WebElement userType;

    @FindBy(id = "uname")
    WebElement name;

    @FindBy(id = "username")
    WebElement uname;

    @FindBy(id = "uemail")
    WebElement email;

    @FindBy(id = "password")
    WebElement pass1;

    @FindBy(id = "repassword")
    WebElement pass2;

    @FindBy(id = "uphone")
    WebElement phone;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-internaluser/div[2]/div/div/div/div/div/div/form/div/div[8]/div[1]/button")
    WebElement submit;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-internaluser/div[3]/div/div/div/table/tbody/tr[1]/td[7]/span/button")
    WebElement statusButton;

    @FindBy(xpath= "//*[td[7]]//*//*//*//*//*")
    WebElement checkIcon;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-internaluser/div[3]/div/div/div/table/tbody/tr[5]/td[8]")
    WebElement edit;

    ArrayList<WebElement> list = new ArrayList<>();


    public void enterUserData(String namef , String unamef , String emailf , String pass1f , String pass2f , String phonef) throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(userType));
        Select select = new Select(userType);
        select.selectByValue("6");
        wait.until(ExpectedConditions.visibilityOf(name));
        name.clear();
        name.sendKeys(namef);
        wait.until(ExpectedConditions.visibilityOf(uname));
        uname.clear();
        uname.sendKeys(unamef);
        wait.until(ExpectedConditions.visibilityOf(email));
        email.clear();
        email.sendKeys(emailf);
        wait.until(ExpectedConditions.visibilityOf(pass1));
        Thread.sleep(1000);
        pass1.clear();
        pass1.sendKeys(pass1f);
        wait.until(ExpectedConditions.visibilityOf(pass2));
        Thread.sleep(1000);
        pass2.clear();
        pass2.sendKeys(pass2f);
        wait.until(ExpectedConditions.visibilityOf(phone));
        phone.clear();
        phone.sendKeys(phonef);
    }

    public void updateData() throws IOException, InterruptedException {
        String temp =    "edit" +  TestUtils.getCounter();
        TestUtils.updateCounter();
        uname.clear();
        uname.sendKeys(temp);
        clickSubmit();
        clickSubmit();
        acceptAlert();
        driver.navigate().refresh();
        Thread.sleep(1000);
        list = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
    }

    public ArrayList<WebElement> updatedData() {
        return list;
    }

    public void clickSubmit(){
        //submit
        submit.click();
    }

    public void clickEdit() throws InterruptedException {
        //edit
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", edit);
        jse.executeScript("arguments[0].click()", edit);
    }

    public void acceptAlert() throws InterruptedException {
        //alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void clickChange()  {
        //status
        statusButton.click();

    }

    public String getStatus(){
        //status
        return checkIcon.getAttribute("data-icon");
    }

}
