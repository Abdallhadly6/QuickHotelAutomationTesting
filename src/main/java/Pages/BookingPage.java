package Pages;

import Base.TestBase;
import TestUtils.TestUtils;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BookingPage extends TestBase {

    public BookingPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(xpath = "//*[@id=\"roomId\"]/div/div/div[2]/input")
    WebElement roomMenu;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div/div[1]/div/ng-select/ng-dropdown-panel/div/div/div[3]")
    WebElement room103;

    @FindBy(id = "guestPhone")
    WebElement phone;

    @FindBy(id = "guestName")
    WebElement name;

    @FindBy(xpath = "//*[@id=\"checkIn\"]")
    WebElement datefield;

    @FindBy(xpath = "//*[@id=\"owl-dt-picker-0\"]/div[2]/owl-date-time-calendar/div[2]/owl-date-time-month-view/table/tbody/tr[5]/td[1]/span")
    WebElement nov27;

    @FindBy(xpath = "//*[@id=\"owl-dt-picker-0\"]/div[2]/div/button[2]/span")
    WebElement set;

    @FindBy(xpath = "//*[@id=\"collapseExample\"]/div/div/form/div/div[7]/button")
    WebElement submit;


    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guestroom/div[3]/div/div/div/table/tbody/tr[1]/td[6]/span/button")
    WebElement edit;


    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guestroom/div[3]/div/div/div/table/tbody/tr[1]/td[8]/span/button")
    WebElement finish;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guestroom/div[3]/div/div/div/table/tbody/tr[1]/td[7]/span/button")
    WebElement QRcode;

    //exit from qr code generated
    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/form/div[1]/button")
    WebElement xMark;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guestroom/div[3]/div/div/div/table/tbody/tr[1]/td[5]")
    public WebElement bookingPassword;


    @FindBy(xpath = "/html/body/app-root/div/main/app-home/div/div/div[2]/button[1]")
    WebElement userPasswordButton;

    @FindBy(id = "password")
    WebElement userPasswordField;

    @FindBy(xpath = "/html/body/ngb-modal-window/div/div/div/div/button")
    WebElement submitPassword;


    ArrayList<WebElement> list = new ArrayList<>();
    String start;
    String end;
    String RoomUrl = " ";
    String bookingPasswordTemp;
    public void getNotFinishedBookingDate() throws InterruptedException, ParseException {
        roomMenu.click();
        room103.click();
        Thread.sleep(1000);
        // body of all booking data at this room to check date when enter new one
        java.util.List<WebElement> element = driver.findElements(By.xpath("/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guestroom/div[3]/div/div/div/table/tbody"));
        if (element.size() > 0){
            start = driver.findElement(By.xpath("/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guestroom/div[3]/div/div/div/table/tbody/tr[1]/td[2]")).getText();
            end = driver.findElement(By.xpath("/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-guestroom/div[3]/div/div/div/table/tbody/tr[1]/td[3]")).getText();
        }
    }

    public void enterBookingData() throws InterruptedException, IOException, ParseException {
        String temp = "05478947" + TestUtils.getCounter();
        TestUtils.updateCounter();

        wait.until(ExpectedConditions.visibilityOf(phone));
        Thread.sleep(1000);
        phone.clear();
        phone.sendKeys(temp);
        wait.until(ExpectedConditions.visibilityOf(name));
        Thread.sleep(1000);
        name.clear();
        name.sendKeys("auto");
        selectDate();

        //check date after
        if(start!= null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss");
            Date date6 = formatter.parse(start);
            Date date7 = formatter.parse(end);
            Date input = formatter.parse("Nov 27, 2022, 12:00:00 PM");

            if ((input.before(date7) || input.equals(date7)) && (input.before(date6) || input.equals(date6))) {
                clickSubmit();
                acceptAlert();
                Assert.assertTrue(true);
            }
        }
        else{
            clickSubmit();
            Thread.sleep(1000);
            java.util.List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
            Assert.assertTrue(list.size() > 0);
        }
    }

    public void selectDate(){
        wait.until(ExpectedConditions.visibilityOf(datefield));
        datefield.click();
        wait.until(ExpectedConditions.visibilityOf(nov27));
        nov27.click();
        wait.until(ExpectedConditions.visibilityOf(set));
        set.click();
    }



    public void updateData() throws IOException, InterruptedException {
        roomMenu.click();
        room103.click();
        clickEdit();
        String temp =    "test" +  TestUtils.getCounter();
        TestUtils.updateCounter();
        name.clear();
        name.sendKeys(temp);
        clickSubmit();
        Thread.sleep(1000);
        list = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[contains(text(),'" + temp + "')]"));
    }

    public ArrayList<WebElement> updatedData() {
        //
        return list;
    }

    public void generateQrCode() throws IOException, NotFoundException, URISyntaxException, InterruptedException {
        roomMenu.click();
        room103.click();
        bookingPasswordTemp = bookingPassword.getText();
        clickQRCode();
        //Pass the URL class object to store the file as image
        BufferedImage bufferedimage= ImageIO.read(new FileInputStream("Room 103.png"));
        // Process the image
        LuminanceSource luminanceSource=new BufferedImageLuminanceSource(bufferedimage);
        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(luminanceSource));
        //To Capture details of QR code
        Result result =new MultiFormatReader().decodeWithState(binaryBitmap);
        RoomUrl = String.valueOf(result);
        wait.until(ExpectedConditions.visibilityOf(xMark));
        clickExit();
    }

    public void openRoomUrl() throws InterruptedException {
        driver.navigate().to(RoomUrl);
        wait.until(ExpectedConditions.visibilityOf(userPasswordButton));
        userPasswordButton.click();
        wait.until(ExpectedConditions.visibilityOf(userPasswordField));
        userPasswordField.clear();
        userPasswordField.sendKeys(bookingPasswordTemp);
        submitPassword.click();
    }


    public void finish(){
        roomMenu.click();
        room103.click();
        bookingPasswordTemp = bookingPassword.getText();
        finish.click();
        driver.navigate().refresh();
        roomMenu.click();
        room103.click();
    }

    public String getPassword(){
        return bookingPasswordTemp;
    }


    public void clickSubmit(){
        //submit
        submit.click();
    }

    public void clickEdit(){
        //edit
        edit.click();
    }


    public void acceptAlert() throws InterruptedException {
        //alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
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

}
