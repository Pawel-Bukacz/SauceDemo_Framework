package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Log4j2
public class CartPage extends BasePage {

    @FindBy(xpath = "//button[@data-test='continue-shopping']")
    WebElement continueShoppingButton;
    @FindBy(xpath = "//button[@data-test='checkout']")
    WebElement checkoutButton;
    @FindBy(xpath = "//div[@data-test='inventory-item']")
    WebElement randomItem;
    @FindBy(xpath = "//div[@data-test='inventory-item']")
    List<WebElement> cartItems;
    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    WebElement itemName;
    @FindBy(xpath = "//div[@data-test='inventory-item-price']")
    WebElement itemPrice;
    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    WebElement cartBadge;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage waitForPageToLoad() {
        log.info("Waiting for the Cart page to load");
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return document.readyState")).toString().equals("complete");
            }
        };

        return this;
    }

    @Step("Click on 'Continue shopping' button.")
    public CartPage clickContinueShopping() {
        log.info("Clicking on 'Continue shopping' button");
        continueShoppingButton.click();

        return this;
    }

    @Step("Click on 'Checkout' button.")
    public CartPage clickCheckOutButton() {
        log.info("Clicking on 'Checkout' button");
        checkoutButton.click();

        return this;
    }

    public String[] collectDataOfARandomlyAddedItem(ProductsPage productsPage, List<Map<String, String>> productData) {
        log.info("Scraping data of a randomly added item");
        productsPage.addRandomItemToCart(productData)
                .clickCart();

        String actualItemName = itemName.getText();
        String actualPrice = itemPrice.getText();
        String expectedItemName = "";
        String expectedPrice = "";

        for (Map<String, String> expectedProductDatum : productData) {
            if (actualItemName.equals(expectedProductDatum.get("ProductName"))) {
                expectedItemName = expectedProductDatum.get("ProductName");
            }
            if (actualPrice.equals(expectedProductDatum.get("Price"))) {
                expectedPrice = expectedProductDatum.get("Price");
            }
        }

        return new String[]{actualItemName, actualPrice, expectedItemName, expectedPrice};
    }

    public List<Map<String, String>> collectDataOfAllRandomlyAddedItems(ProductsPage productsPage, List<Map<String, String>> productData) {
        log.info("Scraping data of all added item");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

        productData.forEach(productMap ->
                productsPage.addToCart(productMap.get("ProductName"))
        );

        productsPage.clickCart();

        List<Map<String, String>> actualCartData = new ArrayList<>();

        for (WebElement currentCartItem : cartItems) {
            String actualItemName = currentCartItem.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
            String actualPrice = currentCartItem.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText();

            Map<String, String> row = new HashMap<>();
            row.put("ProductName", actualItemName);
            row.put("Price", actualPrice);
            actualCartData.add(row);
        }

        return actualCartData;
    }

    @Step("Get cart badge count.")
    public String getCartBadgeCount() {
        log.info("Getting cart badge count");
        return cartBadge.getText();
    }

    @Step("Get current URL.")
    public String getCurrentURL() {
        log.info("Getting current URL");
        return driver.getCurrentUrl();
    }
}