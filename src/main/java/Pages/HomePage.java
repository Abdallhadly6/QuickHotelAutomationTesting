package Pages;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class HomePage extends TestBase {

    public HomePage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/app-admintopbar/div/div/div/nav/div/ul/li[2]/a")
    WebElement buttonLogOut;


    ////   settings
    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/button[2]")
    WebElement buttonSettings;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[2]/a[1]")
    WebElement buttonAccount;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[2]/a[2]")
    WebElement buttonHotel;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[2]/a[3]")
    WebElement buttonUsers;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[2]/a[5]")
    WebElement buttonTax;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[2]/a[6]")
    WebElement buttonTaxGroup;



    ////   rooms
    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/button[3]")
    WebElement buttonRoomsAndGuests;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[3]/a[1]")
    WebElement buttonRooms;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[3]/a[2]")
    WebElement buttonGuests;


    ////   booking
    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/button[4]")
    WebElement buttonBookingMenu;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[4]/a[1]")
    WebElement buttonBooking;


    ////   menu
    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/button[5]")
    WebElement buttonMenu;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[5]/a[1]")
    WebElement buttonServices;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[5]/a[2]")
    WebElement buttonCategories;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[5]/a[3]")
    WebElement buttonItems;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[5]/a[4]")
    WebElement buttonAttributes;

    @FindBy(xpath = "//*[@id=\"mySidebar\"]/div/div[5]/a[5]")
    WebElement buttonAttributeDetails;





    public boolean getLogOutLink() {
        List<WebElement> list = driver.findElements(By.xpath("/html/body/app-root/div/main/app-layout/div/app-admintopbar/div/div/div/nav/div/ul/li[2]/a"));
        boolean temp = false;
        if(list.size()>0){
            return true;
        }
        return temp;
    }

    public void clickLogOut(){
        //logout
        buttonLogOut.click();
    }


    public void clickSettings(){
        //settings
        buttonSettings.click();
    }

    public void clickAccount(){
        //account
        buttonAccount.click();
    }

    public void clickHotel(){
        //hotel
        buttonHotel.click();
    }

    public void clickUsers(){
        //users
        buttonUsers.click();
    }

    public void clickTax(){
        //users
        buttonTax.click();
    }

    public void clickTaxGroup(){
        //users
        buttonTaxGroup.click();
    }


    public void clickRoomAndGuests(){
        //users
        buttonRoomsAndGuests.click();
    }

    public void clickRoom(){
        //users
        buttonRooms.click();
    }

    public void clickGuest(){
        //users
        buttonGuests.click();
    }

    public void clickBookingMenu(){
        //users
        buttonBookingMenu.click();
    }

    public void clickBooking(){
        //users
        buttonBooking.click();
    }

    public void clickMenu(){
        //menu
        buttonMenu.click();
    }

    public void clickServices(){
        //services
        buttonServices.click();
    }

    public void clickCategories(){
        //services
        buttonCategories.click();
    }

    public void clickItems(){
        //services
        buttonItems.click();
    }

    public void clickAttributes(){
        //services
        buttonAttributes.click();
    }

    public void clickAttributeDetails(){
        //services
        buttonAttributeDetails.click();
    }




}
