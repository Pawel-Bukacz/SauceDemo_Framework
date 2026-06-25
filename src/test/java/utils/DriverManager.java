package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    @Step("Closing browser.")
    public static void quit() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
        }
        DRIVER.remove();
    }
}