package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Objects;


@Log4j2
public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//button[@data-test='cancel']")
    WebElement cancelButton;
    @FindBy(xpath = "//*[@id='continue']")
    WebElement continueButton;
    @FindBy(xpath = "//input[@data-test='firstName']")
    WebElement firstNameField;
    @FindBy(xpath = "//input[@data-test='lastName']")
    WebElement lastNameField;
    @FindBy(xpath = "//input[@data-test='postalCode']")
    WebElement postalCodeField;
    @FindBy(xpath = "//*[@data-test='error']")
    WebElement errorMessage;
    @FindBy(xpath = "//button[@data-test='finish']")
    WebElement finishButton;
    @FindBy(xpath = "//button[@data-test='back-to-products']")
    WebElement backHomeButton;
    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    WebElement itemName;
    @FindBy(xpath = "//div[@data-test='inventory-item-price']")
    WebElement itemPrice;
    @FindBy(xpath = "//div[@data-test='shipping-info-value']")
    WebElement shippingInfo;
    @FindBy(xpath = "//div[@data-test='subtotal-label']")
    WebElement subtotalLabel;
    @FindBy(xpath = "//div[@data-test='total-label']")
    WebElement totalLabel;
    @FindBy(xpath = "//*[@data-test='complete-header']")
    WebElement completeHeader;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutPage waitForPageToLoad() {
        log.info("Waiting for 'Checkout' page to load");
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return document.readyState")).toString().equals("complete");
            }
        };

        return this;
    }

    @Step("Click on 'Cancel' button.")
    public CheckoutPage clickCancelButton() {
        log.info("Clicking on 'Cancel' button");
        cancelButton.click();

        return this;
    }

    @Step("Click on 'Continue' button.")
    public CheckoutPage clickContinueButton() {
        log.info("Clicking on 'Continue' button");
        continueButton.click();

        return this;
    }

    @Step("Fill out client checkout data - first name: '{firstName}', last name: '{lastName}' and postal code: '{postalCode}'")
    public CheckoutPage fillInCheckOutData(String firstName, String lastName, String postalCode) {
        log.info("Entering checkout data: first name - '{}', last name - '{}', postal code - '{}'",  firstName, lastName, postalCode);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);

        return this;
    }

    @Step("Click on 'Finish' button.")
    public CheckoutPage clickFinishButton() {
        log.info("Clicking on 'Finish' button");
        finishButton.click();

        return this;
    }

    @Step("Click on 'Back home' button.")
    public CheckoutPage clickBackHomeButton() {
        log.info("Clicking on 'Back home' button");
        backHomeButton.click();

        return this;
    }

    @Step("Get error message.")
    public String getErrorMessage() {
        log.info("Getting error message");
        return errorMessage.getText();
    }

    @Step("Get item name.")
    public String getItemName() {
        log.info("Getting item name");
        return itemName.getText();
    }

    @Step("Get item price.")
    public String getItemPrice() {
        log.info("Getting item price");
        return itemPrice.getText();
    }

    @Step("Get shipping info.")
    public String getShippingInfo() {
        log.info("Getting shipping info");
        return shippingInfo.getText();
    }

    @Step("Get subtotal label.")
    public String getSubtotalLabel() {
        log.info("Getting subtotal info");
        return subtotalLabel.getText();
    }

    @Step("Get total label.")
    public String getTotalLabel() {
        log.info("Getting total label");
        return totalLabel.getText();
    }

    @Step("Get completion header.")
    public String getCompleteHeader() {
        log.info("Getting 'Completed' message header");
        return completeHeader.getText();
    }

    @Step("Get current URL.")
    public String getCurrentURL() {
        log.info("Getting current URL");
        return driver.getCurrentUrl();
    }
}