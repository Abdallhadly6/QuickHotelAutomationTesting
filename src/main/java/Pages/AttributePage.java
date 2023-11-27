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

public class AttributePage extends TestBase {

    public AttributePage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "type")
    WebElement attributeType;

    @FindBy(id = "aname")
    WebElement aName;

    @FindBy(id = "ename")
    WebElement eName;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div[3]/div[1]/button")
    WebElement submit;

    //full xpath supervisor
    @FindBy(xpath = "//*[@id=\"cdk-drop-list-0\"]/tr[8]/td[5]")
    WebElement edit;

    @FindBy(xpath = "//*[@id=\"cdk-drop-list-0\"]/tr[8]/td[6]")
    WebElement delete;

    @FindBy(xpath = "//*[@id=\"cdk-drop-list-0\"]/tr[8]/td[2]")
    WebElement nameForDeletedAttribute;

    String temp;

    public void enterAttributeData(String arabicName , String EnglishName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(attributeType));
        Select select = new Select(attributeType);
        select.selectByVisibleText("تاريخ");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(aName));
        aName.clear();
        aName.sendKeys(arabicName);
        wait.until(ExpectedConditions.visibilityOf(eName));
        eName.clear();
        eName.sendKeys(EnglishName);
        clickSubmit();
        acceptAlert();
    }

    public void updateAttribute(String arabicName , String EnglishName) throws InterruptedException {
        clickEdit();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(aName));
        aName.clear();
        aName.sendKeys(arabicName);
        wait.until(ExpectedConditions.visibilityOf(eName));
        eName.clear();
        eName.sendKeys(EnglishName);
        clickSubmit();
        Thread.sleep(3000);
    }

    public void deleteAttribute() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(nameForDeletedAttribute));
        temp = nameForDeletedAttribute.getText();
        clickDelete();
        Thread.sleep(1000);
        acceptAlert();
        Thread.sleep(2000);
    }

    public String attributeName(){
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
