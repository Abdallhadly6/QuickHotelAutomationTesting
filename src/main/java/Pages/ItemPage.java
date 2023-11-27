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
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

public class ItemPage extends TestBase {

    public ItemPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "serviceId")
    WebElement service;

    @FindBy(id = "categoryId")
    WebElement category;

    @FindBy(id = "aname0")
    WebElement aName;

    @FindBy(id = "adescription0")
    WebElement aDescription;

    @FindBy(id = "ename0")
    WebElement eName;

    @FindBy(id = "edescription0")
    WebElement eDescription;

    @FindBy(id = "taxGroupId0")
    WebElement taxGroup;

    @FindBy(id = "price0")
    WebElement price;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div[2]/div[3]/div[3]/button")
    WebElement photo;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/div[2]/div/div/div/div/div/div/div[2]/button")
    WebElement photo2;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div[3]/div[1]/button")
    WebElement submit;

    @FindBy(xpath = "//*[@id=\"cdk-drop-list-0\"]/tr[1]/td[8]")
    WebElement edit;

    @FindBy(xpath = "//*[@id=\"cdk-drop-list-0\"]/tr[1]/td[9]")
    WebElement delete;


    //ATTRIBUTES ELEMENTS
    @FindBy(xpath = "//*[@id=\"cdk-drop-list-0\"]/tr[1]/td[6]/button")
    WebElement attribute;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/div[2]/div/div[1]/div[1]/input")
    WebElement taste;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/input")
    WebElement bondok;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/div[2]/div/div[1]/div[2]/div[1]/div[2]/div/input")
    WebElement priceofattriute;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/div[3]/button")
    WebElement submitAttribute;

    ///////////////////////////////////////////////

    @FindBy(xpath = "//*[@id=\"cdk-drop-list-0\"]/tr[1]/td[2]")
    WebElement nameForDeletedItem;

    String temp;

    public void enterItemData(String EnglishName) throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(service));
        Select select = new Select(service);
        select.selectByVisibleText("خدمة اختبار اوتوميشن1");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(category));
        Select select2 = new Select(category);
        select2.selectByVisibleText("arabicName");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(aName));
        aName.clear();
        aName.sendKeys("اختبار");
        wait.until(ExpectedConditions.visibilityOf(aDescription));
        aDescription.clear();
        aDescription.sendKeys("وصف المنتج");
        wait.until(ExpectedConditions.visibilityOf(eName));
        eName.clear();
        eName.sendKeys(EnglishName);
        wait.until(ExpectedConditions.visibilityOf(eDescription));
        eDescription.clear();
        eDescription.sendKeys("item description");
        wait.until(ExpectedConditions.visibilityOf(taxGroup));
        Select select3 = new Select(taxGroup);
        select3.selectByVisibleText("اوتومايشن");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(price));
        price.clear();
        price.sendKeys("5");
        uploadPhoto();
        Thread.sleep(2000);
        clickSubmit();
    }

    public void uploadPhoto() throws AWTException, InterruptedException {
        //photo
        photo.click();
        photo2.click();
        Thread.sleep(2000);
        String path = System.getProperty("user.dir")+"\\images\\temp.jpg";
        Robot rb = new Robot();
        StringSelection str = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        // press Contol+V for pasting
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // release Contol+V for pasting
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        // for pressing and releasing Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    public void updateItem(String EnglishName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(service));
        Select select = new Select(service);
        select.selectByVisibleText("خدمة اختبار اوتوميشن1");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(category));
        Select select2 = new Select(category);
        select2.selectByVisibleText("arabicName");
        Thread.sleep(1000);
        clickEdit();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOf(eName));
        eName.clear();
        eName.sendKeys(EnglishName);
        clickSubmit();
    }

    public void addAttributes() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(service));
        Select select = new Select(service);
        select.selectByVisibleText("خدمة اختبار اوتوميشن1");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(category));
        Select select2 = new Select(category);
        select2.selectByVisibleText("arabicName");
        Thread.sleep(1000);
        attribute.click();
        Thread.sleep(1000);
        if(taste.isSelected()){
            Assert.assertTrue(true);
        }
        else {
            taste.click();
            wait.until(ExpectedConditions.visibilityOf(bondok));
            bondok.click();
            wait.until(ExpectedConditions.visibilityOf(priceofattriute));
            priceofattriute.clear();
            priceofattriute.sendKeys("3");
            Thread.sleep(1000);
            submitAttribute.click();
            acceptAlert();
            Thread.sleep(1000);
            attribute.click();
            Thread.sleep(1000);
            if(taste.isSelected()){
                Assert.assertTrue(true);
            }
            else{
                Assert.assertFalse(true);
            }
        }
    }

    public void deleteItem() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(service));
        Select select = new Select(service);
        select.selectByVisibleText("خدمة اختبار اوتوميشن1");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(category));
        Select select2 = new Select(category);
        select2.selectByVisibleText("arabicName");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(nameForDeletedItem));
        temp = nameForDeletedItem.getText();
        clickDelete();
        Thread.sleep(1000);
        acceptAlert();
        Thread.sleep(3000);
    }

    public String itemName(){
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
