package Pages;

import Base.TestBase;
import TestUtils.TestUtils;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;

public class GuestPage extends TestBase {

    public GuestPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "phone")
    WebElement phone;

    @FindBy(id = "name")
    WebElement name;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div/div[5]/button")
    WebElement submit;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guest/div[3]/div/div/div/table/tbody/tr[2]/td[5]/span/button")
    WebElement edit;


    ArrayList<WebElement> list = new ArrayList<>();

    public void enterGuestData(String gname , String gPhone) throws InterruptedException, IOException {
        wait.until(ExpectedConditions.visibilityOf(phone));
        Thread.sleep(1000);
        phone.clear();
        phone.sendKeys(gPhone);
        wait.until(ExpectedConditions.visibilityOf(name));
        Thread.sleep(1000);
        name.clear();
        name.sendKeys(gname);
    }

    public void updateData() throws IOException, InterruptedException {
        String temp =    "edit" +  TestUtils.getCounter();
        TestUtils.updateCounter();
        name.clear();
        name.sendKeys(temp);
        clickSubmit();
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

    public void clickEdit(){
        //edit
        edit.click();
    }

}
