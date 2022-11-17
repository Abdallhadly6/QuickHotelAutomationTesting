package Pages;

import Base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

public class ServicesPage extends TestBase {

    public ServicesPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    @FindBy(id = "aname")
    WebElement aName;

    @FindBy(id = "ename")
    WebElement eName;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div/div/div[3]/button")
    WebElement photo;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/div[2]/div/div/div/div/div/div/div[2]/button")
    WebElement photo2;




    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div/div/div[4]/button")
    WebElement submit;


    //full xpath supervisor
    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-services/div/div[3]/div/div/div/table/tbody/tr[7]/td[4]")
    WebElement edit;

    public void enterServiceData(String arabicName , String EnglishName){
        aName.clear();
        aName.sendKeys(arabicName);
        eName.clear();
        eName.sendKeys(EnglishName);

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

}
