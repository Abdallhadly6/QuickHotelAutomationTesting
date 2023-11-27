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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class RoomPage extends TestBase {

    public RoomPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "roomnumber")
    WebElement Rnumber;

    @FindBy(id = "arabiclabeltype")
    WebElement ARType;

    @FindBy(id = "englishlabeltype")
    WebElement ERType;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div/div[4]/button")
    WebElement submit;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-room/div[3]/div/div/div/table/tbody/tr[3]/td[5]/span/button")
    WebElement edit;








    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-room/div[3]/div/div/div/table/tbody/tr[3]/td[6]/span/button")
    WebElement QRcode;

    //exit from qr code generated
    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/form/div[1]/button")
    WebElement xMark;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/form/div[3]/button")
    WebElement downloadQR;





    ArrayList<WebElement> list = new ArrayList<>();
    String RoomUrl = " ";

    public void enterRoomData(String arabicName , String EnglishName , String Number) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Rnumber));
        Thread.sleep(1000);
        Rnumber.clear();
        Rnumber.sendKeys(Number);
        wait.until(ExpectedConditions.visibilityOf(ERType));
        Thread.sleep(1000);
        ERType.clear();
        ERType.sendKeys(EnglishName);
        wait.until(ExpectedConditions.visibilityOf(ARType));
        Thread.sleep(1000);
        ARType.clear();
        ARType.sendKeys(arabicName);

    }

    public void updateData() throws IOException, InterruptedException {
        String temp =    "edit" +  TestUtils.getCounter();
        TestUtils.updateCounter();
        ERType.clear();
        ERType.sendKeys(temp);
        clickSubmit();
        driver.navigate().refresh();
        Thread.sleep(1000);
        list = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
    }

    public ArrayList<WebElement> updatedData() {
        return list;
    }

    public void generateQrCode() throws IOException, NotFoundException, URISyntaxException, InterruptedException {
        clickQRCode();
        //Pass the URL class object to store the file as image
        BufferedImage bufferedimage= ImageIO.read(new FileInputStream("Room 103.png"));
        // Process the image
        LuminanceSource luminanceSource=new BufferedImageLuminanceSource(bufferedimage);
        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(luminanceSource));
        //To Capture details of QR code
        Result result =new MultiFormatReader().decode(binaryBitmap);
        RoomUrl = String.valueOf(result);
        wait.until(ExpectedConditions.visibilityOf(xMark));
        clickExit();
    }

    public void openRoomUrl() throws InterruptedException {
        driver.navigate().to(RoomUrl);
    }


    public void clickSubmit(){
        //submit
        submit.click();
    }

    public void clickEdit(){
        //edit
        edit.click();
    }

    public void clickQRCode(){
        //edit
        QRcode.click();
    }

    public void clickExit(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", xMark);
        jse.executeScript("arguments[0].click()", xMark);
    }

//    public void downloadImageFromQr(){
//        for(int i = 1 ; i < 120 ; i++){
//            WebElement temp = driver.findElement(By.xpath("/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-room/div[3]/div/div/div/table/tbody/tr["+i+"]/td[5]/span/button"));
//            JavascriptExecutor jse = (JavascriptExecutor)driver;
//            jse.executeScript("arguments[0].scrollIntoView(true);", temp);
//            jse.executeScript("arguments[0].click()", temp);
//            jse.executeScript("arguments[0].scrollIntoView(true);", downloadQR);
//            jse.executeScript("arguments[0].click()", downloadQR);
//
//        }
//
//    }


}
