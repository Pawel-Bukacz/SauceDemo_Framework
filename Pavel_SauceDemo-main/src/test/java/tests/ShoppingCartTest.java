package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;
import utils.Retry;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static helpers.LoginData.*;
import static helpers.CheckoutData.*;
import static pages.BasePage.*;


@Log4j2
public class ShoppingCartTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        log.info("@BeforeMethod for ShoppingCartTest start");
        loginStep
                .loginAs(LOGIN, PASSWORD, loginPage());
    }

    @DataProvider(name = "Invalid checkout data")
    public Object[][] invalidCheckoutData() {
        return new Object[][]{
                {"", LAST_NAME, POSTAL_CODE, "Error: First Name is required"},
                {FIRST_NAME, "", POSTAL_CODE, "Error: Last Name is required"},
                {FIRST_NAME, LAST_NAME, "", "Error: Postal Code is required"}
        };
    }

    @Test(
            testName = "Verify successfully adding random item to cart.",
            description = "Verify successfully adding random item to cart.",
            groups = "smoke",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Shopping cart pages")
    @Description("Verify user can add item to the cart.")
    @Severity(SeverityLevel.BLOCKER)
    public void addOneRandomItemTotTheCart() {
        log.info("Starting addOneRandomItemTotTheCart test");
        String[] testData = cartStep.addOneRandomItemToCart(cartPage(), productsPage(), expectedProductData);

        assertEquals(testData[2], testData[0], "Product is not in the cart");
        assertEquals(testData[3], testData[1], "Product is not in the cart");
        log.info("Ending addOneRandomItemTotTheCart test");
    }

    @Test(
            testName = "Verify successfully adding all items to cart.",
            description = "Verify successfully adding all items to cart.",
            groups = "regression",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Shopping cart pages")
    @Description("Verify successfully adding all items to cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void addiAllItemsToCart() {
        log.info("Starting addiAllItemsToCart test");
        List<Map<String, String>> actualCartData = cartStep.addAllItemsToCart(cartPage(), productsPage(), expectedProductData);

        for (int i = 0; i < expectedProductData.size(); i++) {
            String expectedItemName = expectedProductData.get(i).get("ProductName");
            String expectedPrice = expectedProductData.get(i).get("Price");

            assertEquals(actualCartData.get(i).get("ProductName"), expectedItemName, "Product name mismatch at row " + i);
            assertEquals(actualCartData.get(i).get("Price"), expectedPrice, "Price mismatch for product: " + expectedItemName);
        }
        log.info("Ending addiAllItemsToCart test");
    }

    @Test(
            testName = "Verify returning to Products page from Cart page works.",
            description = "Verify returning to Products page from Cart page works.",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Shopping cart pages")
    @Description("Verify returning to Products page from Cart page works.")
    @Severity(SeverityLevel.CRITICAL)
    public void checkReturningToProductsPageFromCart() {
        log.info("Starting checkReturningToProductsPageFromCart test");
        cartStep
                .addRandomItemAndReturnToProducts(cartPage(), productsPage(), expectedProductData);

        assertEquals(productsStep.getCurrentURL(productsPage()), BASE_URL + "inventory.html", "Continue shopping button does not work!");
        log.info("Ending checkReturningToProductsPageFromCart test");
    }

    @Test(
            testName = "Verify Cart page's 'Cancel' button works.",
            description = "Verify Cart page's 'Cancel' button works.",
            retryAnalyzer = Retry.class,
            groups = "regression"
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Shopping cart pages")
    @Description("Verify Cart page's 'Cancel' button works.")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCheckoutCancelButton() {
        log.info("Starting checkoutCancelButton test");
        cartStep
                .addRandomItemAndProceedToCheckout(cartPage(), productsPage(), expectedProductData);
        checkoutStep
                .clickCancelButton(checkoutPage());

        assertEquals(cartStep.getCurrentURL(cartPage()), BASE_URL + "cart.html", "Continue shopping button does not work!");
        log.info("Ending checkoutCancelButton test");
    }

    @Test(
            testName = "Verify checkout possible with correct data input.",
            description = "Verify checkout possible with correct data input.",
            retryAnalyzer = Retry.class,
            groups = "regression"
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Shopping cart pages")
    @Description("Verify checkout possible with correct data input.")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCorrectCheckoutDataInput() {
        log.info("Starting checkCorrectCheckoutDataInput test");
        cartStep
                .addRandomItemAndProceedToCheckout(cartPage(), productsPage(), expectedProductData);
        checkoutStep
                .fillCheckoutForm(checkoutPage(), FIRST_NAME, LAST_NAME, POSTAL_CODE);

        assertEquals(checkoutStep.getCurrentURL(checkoutPage()), BASE_URL + "checkout-step-two.html", "Checkout overview page is not open!");
        log.info("Ending checkCorrectCheckoutDataInput test");
    }

    @Test(
            dataProvider = "Invalid checkout data",
            testName = "Verify errors for different checkout information fields > ",
            description = "Verify errors for different checkout information fields.",
            groups = "regression",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Shopping cart pages")
    @Description("Verify errors for different checkout information fields.")
    @Severity(SeverityLevel.CRITICAL)
    public void checkIncorrectFirstNameCheckoutDataInput(String firstName, String lastName, String postalCode, String errorMessage) {
        log.info("Starting checkIncorrectFirstNameCheckoutDataInput test");
        cartStep
                .addRandomItemAndProceedToCheckout(cartPage(), productsPage(), expectedProductData);
        checkoutStep
                .fillCheckoutForm(checkoutPage(), firstName, lastName, postalCode);

        assertEquals(checkoutStep.getErrorMessage(checkoutPage()), errorMessage, "Checkout overview page is not open!");
        log.info("Ending checkIncorrectFirstNameCheckoutDataInput test");
    }

    @Test(
            testName = "Verify second checkout page.",
            description = "Verify second checkout page.",
            groups = "smoke",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Shopping cart pages")
    @Description("Verify second checkout page.")
    @Severity(SeverityLevel.BLOCKER)
    public void checkCheckoutOverviewPage() {
        log.info("Starting checkCheckoutOverviewPage test");
        cartStep
                .addRandomItemAndProceedToCheckout(cartPage(), productsPage(), expectedProductData);
        checkoutStep
                .fillCheckoutForm(checkoutPage(), FIRST_NAME, LAST_NAME, POSTAL_CODE);

        Map<String, String> overview = checkoutStep.collectOverviewData(checkoutPage(), expectedProductData);
        String expectedPrice = overview.get("expectedPrice");

        assertEquals(overview.get("itemPrice"), expectedPrice, "Incorrect product price!");
        assertEquals(overview.get("subTotalPrice").replaceAll(".*\\$", ""), expectedPrice.replaceAll(".*\\$", ""), "Incorrect subtotal price!");
        assertEquals(overview.get("shippingDetails"), "Free Pony Express Delivery!", "Incorrect delivery operator!");
        assertEquals(overview.get("totalPrice").replaceAll(".*\\$", ""), String.format(java.util.Locale.US, "%.2f", Double.parseDouble(expectedPrice.replaceAll(".*\\$", "")) * 1.08), "Incorrect total price!");
        log.info("Ending checkCheckoutOverviewPage test");
    }
}