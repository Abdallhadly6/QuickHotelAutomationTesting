package Pages;

import Base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class AttributeDetailsPage extends TestBase {

    public AttributeDetailsPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "attributeId")
    WebElement attribute;

    @FindBy(id = "aname0")
    WebElement aName;

    @FindBy(id = "ename0")
    WebElement eName;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div[3]/div[1]/button")
    WebElement submit;

    //full xpath supervisor
    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-hattributedetails/div[3]/div/div/div/table/tbody/tr[1]/td[4]")
    WebElement edit;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-hattributedetails/div[3]/div/div/div/table/tbody/tr[1]/td[5]")
    WebElement delete;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-hattributedetails/div[3]/div/div/div/table/tbody/tr[1]/td[2]")
    WebElement nameForDeletedAttributeDetails;

    String temp;

    public void enterAttributeDetailsData(String arabicName , String EnglishName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(attribute));
        Select select = new Select(attribute);
        select.selectByVisibleText("اوتوميشن2");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(aName));
        aName.clear();
        aName.sendKeys(arabicName);
        wait.until(ExpectedConditions.visibilityOf(eName));
        eName.clear();
        eName.sendKeys(EnglishName);
        clickSubmit();
    }

    public void updateAttributeDetails(String arabicName , String EnglishName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(attribute));
        Select select = new Select(attribute);
        select.selectByVisibleText("اوتوميشن2");
        Thread.sleep(1000);
        clickEdit();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(aName));
        aName.clear();
        aName.sendKeys(arabicName);
        wait.until(ExpectedConditions.visibilityOf(eName));
        eName.clear();
        eName.sendKeys(EnglishName);
        clickSubmit();
        Thread.sleep(1000);
    }

    public void deleteAttributeDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(attribute));
        Select select = new Select(attribute);
        select.selectByVisibleText("اوتوميشن2");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(nameForDeletedAttributeDetails));
        temp = nameForDeletedAttributeDetails.getText();
        clickDelete();
        Thread.sleep(1000);
        acceptAlert();
        Thread.sleep(2000);
    }

    public String attributeDetailsName(){
        return temp;
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

    public void clickDelete() throws InterruptedException {
        //edit
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", delete);
        jse.executeScript("arguments[0].click()", delete);
    }

    public void acceptAlert() throws InterruptedException {
        //alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

}
