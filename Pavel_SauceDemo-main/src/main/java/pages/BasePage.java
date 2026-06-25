package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public static final String BASE_URL = "https://www.saucedemo.com/";
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public void jSClick(By locator) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    public void jSScrollDown(WebElement element) {
    }

    @Step("Verify page is loaded.")
    public abstract BasePage waitForPageToLoad();
}