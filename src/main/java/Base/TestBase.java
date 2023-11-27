package Base;

import TestUtils.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//Define our driver here and all test cases will extend this class
public class TestBase {
    protected static WebDriver driver;

    protected static Properties properties;
    public static ExtentReports extentReports;
    public static ExtentTest log;
    public TestBase() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/Config/config.properties");
        properties.load(fileInputStream);
    }

    public static void initialization(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));


    }

    public static void tearDown(Method method , ITestResult result) throws IOException {
        TestUtils.screenShot(method.getName());
        if(result.getStatus() == ITestResult.SUCCESS){
            log.log(LogStatus.PASS , "Test Pass..");
            log.log(LogStatus.PASS , "<a href = '"+result.getName()+".png" + "'><span class = 'lable info'> SnapShot</span></a>");
        }
        else if(result.getStatus() == ITestResult.FAILURE){
            log.log(LogStatus.FAIL , "Test Failed..");
            log.log(LogStatus.FAIL , "<a href = '"+result.getName()+".png" + "'><span class = 'lable info'> SnapShot</span></a>");
            log.log(LogStatus.FAIL , result.getThrowable());
        }
        else {
            log.log(LogStatus.SKIP , "Test Skipped..");
            log.log(LogStatus.SKIP , "<a href = '"+result.getName()+".png" + "'><span class = 'lable info'> SnapShot</span></a>");
            log.log(LogStatus.SKIP , result.getThrowable());
        }

        driver.quit();
    }
}
