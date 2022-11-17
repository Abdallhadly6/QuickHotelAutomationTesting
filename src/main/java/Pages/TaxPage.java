package Pages;

import Base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class TaxPage extends TestBase {

    public TaxPage() throws IOException {
        // to define all element in this page
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    @FindBy(id = "taxnamear")
    WebElement aName;

    @FindBy(id = "taxnameen")
    WebElement eName;

    @FindBy(id = "amount")
    WebElement amount;

    @FindBy(xpath = "//*[@id=\"form-container\"]/form/div[4]/button")
    WebElement submit;

    @FindBy(xpath = "/html/body/app-root/div/main/app-layout/div/div/div/div[2]/app-tax/div/div[2]/table/tbody/tr[5]/td[4]/a")
    WebElement edit;

    public void enterTaxeData(String arabicName , String EnglishName , String Amount) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(aName));
        Thread.sleep(1000);
        aName.clear();
        aName.sendKeys(arabicName);
        wait.until(ExpectedConditions.visibilityOf(eName));
        Thread.sleep(1000);
        eName.clear();
        eName.sendKeys(EnglishName);
        wait.until(ExpectedConditions.visibilityOf(amount));
        amount.clear();
        amount.sendKeys(Amount);

    }

    public void update(){

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
