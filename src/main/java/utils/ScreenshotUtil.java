package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String name) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "src/screenshots/" + name + "_" + timestamp + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(filename));
            System.out.println("Screenshot salva em: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}