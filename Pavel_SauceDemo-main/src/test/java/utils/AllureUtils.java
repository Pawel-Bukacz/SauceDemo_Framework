package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;


public class AllureUtils {

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}