package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.ProductsPage;

import java.util.List;
import java.util.Map;


@Log4j2
public class CartStep {

    WebDriver driver;

    public CartStep(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Add one random item to cart and collect data.")
    public String[] addOneRandomItemToCart(CartPage cartPage, ProductsPage productsPage, List<Map<String, String>> productData) {
        log.info("Adding one random item to cart and collecting its data");
        return cartPage
                .collectDataOfARandomlyAddedItem(productsPage, productData);
    }

    @Step("Add all items to cart and collect data.")
    public List<Map<String, String>> addAllItemsToCart(CartPage cartPage, ProductsPage productsPage, List<Map<String, String>> productData) {
        log.info("Adding all items to cart and collecting their data");
        return cartPage
                .collectDataOfAllRandomlyAddedItems(productsPage, productData);
    }

    @Step("Add random item and proceed to checkout.")
    public void addRandomItemAndProceedToCheckout(CartPage cartPage, ProductsPage productsPage, List<Map<String, String>> productData) {
        log.info("Adding random item and proceeding to checkout");
        productsPage
                .addRandomItemToCart(productData)
                .clickCart();
        cartPage
                .clickCheckOutButton();
    }

    @Step("Add random item, open cart and return to products page.")
    public void addRandomItemAndReturnToProducts(CartPage cartPage, ProductsPage productsPage, List<Map<String, String>> productData) {
        log.info("Adding random item and return to products page");
        productsPage
                .addRandomItemToCart(productData)
                .clickCart();
        cartPage
                .clickContinueShopping();
    }

    @Step("Get current URL from cart page.")
    public String getCurrentURL(CartPage cartPage) {
        log.info("Get current URL");
        return cartPage
                .getCurrentURL();
    }

    @Step("Get cart badge count.")
    public String getCartBadgeCount(CartPage cartPage) {
        log.info("Get cart badge count");
        return cartPage
                .getCartBadgeCount();
    }
}
