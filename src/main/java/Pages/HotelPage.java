package Pages;

import Base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;

public class HotelPage extends TestBase {

    public HotelPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @FindBy(id = "edescription")
    WebElement enDescription;

    @FindBy(id = "adescription")
    WebElement arDescription;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-profile/div[2]/div/div/div/div/div/div/form/div[3]/div/button")
    WebElement submit;

    public void updateData(String endesc , String ardesc) throws InterruptedException {
        enDescription.clear();
        enDescription.sendKeys(endesc);
        arDescription.clear();
        arDescription.sendKeys(ardesc);

    }

    public void clickSubmit() throws InterruptedException {
        //submit
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", submit);
        Thread.sleep(2000);
        submit.click();
        Thread.sleep(2000);
    }

    public void acceptAlert() throws InterruptedException {
        //submit
        driver.switchTo().alert().accept();
    }

    public void checkSuccessOfUpdate() throws InterruptedException {
        homePage.clickLogOut();
        loginPage.enterData(properties.getProperty("userId"), properties.getProperty("userPassword"));
        loginPage.clickLogin();
        homePage.clickSettings();
        homePage.clickHotel();
        Thread.sleep(2000);
    }

    public ArrayList<String> updatedData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(enDescription.getAttribute("value"));
        data.add(arDescription.getAttribute("value"));
        return data;

    }
}