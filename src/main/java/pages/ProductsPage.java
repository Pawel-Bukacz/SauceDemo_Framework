package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Log4j2
public class ProductsPage extends BasePage {

    private static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private static final String PRODUCT_NAME_PATTERN = "//div[text()='%s']";

    @FindBy(css = "[data-test=shopping-cart-link]")
    WebElement cartLink;
    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    WebElement productName;
    @FindBy(xpath = "//div[@data-test='inventory-item-desc']")
    WebElement productDescription;
    @FindBy(xpath = "//div[@data-test='inventory-item-price']")
    WebElement productPrice;
    @FindBy(xpath = "//a[@data-test='social-twitter']")
    WebElement twitterLink;
    @FindBy(xpath = "//a[@data-test='social-facebook']")
    WebElement facebookLink;
    @FindBy(xpath = "//a[@data-test='social-linkedin']")
    WebElement linkedInLink;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsPage waitForPageToLoad() {
        log.info("Wait for Product page to load");
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return document.readyState")).toString().equals("complete");
            }
        };

        return this;
    }

    @Step("Open URL: '{url}'.")
    public ProductsPage openURL(String url) {
        log.info("Opening URL - '{}'", url);
        driver.get(url);

        return this;
    }

    @Step("Adding item '{product}' to cart.")
    public ProductsPage addToCart(String product) {
        log.info("Adding item '{}' to cart",  product);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();

        return this;
    }

    @Step("Click on cart icon.")
    public ProductsPage clickCart() {
        log.info("Click on cart icon");
        cartLink.click();

        return this;
    }

    @Step("Add random item to the cart.")
    public ProductsPage addRandomItemToCart(List<Map<String, String>> expectedProductData) {
        log.info("Adding random item to cart.");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        int randomNumber = (int) (Math.random() * expectedProductData.size());
        String product = expectedProductData.get(randomNumber).get("ProductName");
        addToCart(product);

        return this;
    }

    @Step("Open product details for '{product}'.")
    public ProductsPage openProduct(String product) {
        log.info("Opening product details page of '{}'", product);
        driver.findElement(By.xpath(String.format(PRODUCT_NAME_PATTERN, product))).click();

        return this;
    }

    @Step("Get product name.")
    public String getProductName() {
        log.info("Getting product name");
        return productName.getText();
    }

    @Step("Get product description.")
    public String getProductDescription() {
        log.info("Getting product description");
        return productDescription.getText();
    }

    @Step("Get product price.")
    public String getProductPrice() {
        log.info("Getting product price");
        return productPrice.getText();
    }

    @Step("Get current URL.")
    public String getCurrentURL() {
        log.info("Getting current URL");
        return driver.getCurrentUrl();
    }

    @Step("Get Twitter footer URL.")
    public String getTwitterURL() {
        log.info("Getting Twitter footer URL");
        return twitterLink.getAttribute("href");
    }

    @Step("Get Facebook footer URL.")
    public String getFacebookURL() {
        log.info("Getting Facebook footer URL");
        return facebookLink.getAttribute("href");
    }

    @Step("Get LinkedIn footer URL.")
    public String getLinkedInURL() {
        log.info("Getting LinkedIn footer URL");
        return linkedInLink.getAttribute("href");
    }
}