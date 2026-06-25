package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Objects;


@Log4j2
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(css = "[data-test=error]")
    WebElement errorMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Opens login page")
    public LoginPage open() {
        log.info("Opening Login page");
        driver.get(BASE_URL);

        return this;
    }

    @Step("Verify page is loaded")
    public LoginPage waitForPageToLoad() {
        log.info("Waiting for Login page to load");
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return document.readyState")).toString().equals("complete");
            }
        };
        return this;
    }

    @Step("Login with credentials of user: '{user}' and password: '{password}'")
    public LoginPage login(String user, String password) {
        log.info("Loggin in using username - '{}' and password - '{}'", user, password);
        waitForPageToLoad();
        usernameField.sendKeys(user);
        passwordField.sendKeys(password);
        loginButton.click();

        return this;
    }

    @Step("Get error message.")
    public String getErrorMessage() {
        log.info("Getting error message");
        return errorMessage.getText();
    }

    @Step("Get current URL.")
    public String getCurrentURL() {
        log.info("Getting current URL");
        return driver.getCurrentUrl();
    }
}