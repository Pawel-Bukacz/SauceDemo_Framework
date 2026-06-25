package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductsPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
public class CheckoutStep {

    WebDriver driver;

    public CheckoutStep(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill checkout form and continue.")
    public void fillCheckoutForm(CheckoutPage checkoutPage, String firstName, String lastName, String postalCode) {
        log.info("Fill checkout form and continue");
        checkoutPage
                .fillInCheckOutData(firstName, lastName, postalCode)
                .clickContinueButton();
    }

    @Step("Complete purchase end-to-end.")
    public void completePurchase(ProductsPage productsPage,
                                 CartPage cartPage,
                                 CheckoutPage checkoutPage,
                                 List<Map<String, String>> productData,
                                 String firstName,
                                 String lastName,
                                 String postalCode) {
        log.info("Complete purchase end-to-end");
        productsPage
                .addRandomItemToCart(productData)
                .clickCart();
        cartPage
                .clickCheckOutButton();
        checkoutPage
                .fillInCheckOutData(firstName, lastName, postalCode)
                .clickContinueButton()
                .clickFinishButton();
    }

    @Step("Collect checkout overview data.")
    public Map<String, String> collectOverviewData(CheckoutPage checkoutPage, List<Map<String, String>> expectedProductData) {
        log.info("Collect checkout overview data");
        String itemName = checkoutPage.getItemName();
        String expectedPrice = "";

        for (Map<String, String> expectedProductDatum : expectedProductData) {
            if (expectedProductDatum.get("ProductName").equals(itemName)) {
                expectedPrice = expectedProductDatum.get("Price");
            }
        }

        Map<String, String> overview = new HashMap<>();
        overview.put("itemName", itemName);
        overview.put("itemPrice", checkoutPage.getItemPrice());
        overview.put("shippingDetails", checkoutPage.getShippingInfo());
        overview.put("subTotalPrice", checkoutPage.getSubtotalLabel());
        overview.put("totalPrice", checkoutPage.getTotalLabel());
        overview.put("expectedPrice", expectedPrice);

        return overview;
    }

    @Step("Click 'Cancel' on checkout page.")
    public void clickCancelButton(CheckoutPage checkoutPage) {
        log.info("Click 'Cancel' on checkout page");
        checkoutPage.clickCancelButton();
    }

    @Step("Click 'Back home' on checkout page.")
    public void clickBackHomeButton(CheckoutPage checkoutPage) {
        log.info("Click 'Back home' on checkout page");
        checkoutPage.clickBackHomeButton();
    }

    @Step("Get current URL from checkout page.")
    public String getCurrentURL(CheckoutPage checkoutPage) {
        log.info("Get current URL");
        return checkoutPage.getCurrentURL();
    }

    @Step("Get error message from checkout page.")
    public String getErrorMessage(CheckoutPage checkoutPage) {
        log.info("Get error message");
        return checkoutPage.getErrorMessage();
    }

    @Step("Get completion header text.")
    public String getCompleteHeader(CheckoutPage checkoutPage) {
        log.info("Get completion header text");
        return checkoutPage.getCompleteHeader();
    }
}
