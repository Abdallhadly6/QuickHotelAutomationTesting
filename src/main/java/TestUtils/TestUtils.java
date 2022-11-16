package TestUtils;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
public class TestUtils extends TestBase {
    public TestUtils() throws IOException {
        super();
    }

    public static void screenShot(String name ) throws IOException {
        File shot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shot, new File("QuickHotelTestReport\\"+name+".png"));
        //Screenshot screeshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        //ImageIO.write(screeshot.getImage(),"PNG",new File("QuickHotelTestReport\\"+name+".png"));
    }

    public static Object[][] getDatFromExcel(String sheetName) throws IOException {
        File file = new File(properties.getProperty("excelPath"));
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rows][cols];
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < cols ; j++){
                data[i][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        workbook.close();
        return data;
    }
}
