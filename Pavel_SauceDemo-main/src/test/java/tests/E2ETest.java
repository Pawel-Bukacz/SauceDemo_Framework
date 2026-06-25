package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import utils.Retry;

import static org.testng.Assert.assertEquals;
import static pages.BasePage.*;
import static helpers.LoginData.*;
import static helpers.CheckoutData.*;


@Log4j2
public class E2ETest extends BaseTest {

    @Test(
            groups = "regression",
            description = "E2E verifying successful purchase.",
            testName = "Verify successful purchase is possible.",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("E2E purchase flow")
    @Description("Verify e2e purchase flow.")
    @Severity(SeverityLevel.CRITICAL)
    public void e2EPurchaseTest() {
        log.info("Starting e2EPurchaseTest test");
        loginStep
                .loginAs(LOGIN, PASSWORD, loginPage());
        checkoutStep.
                completePurchase(productsPage(), cartPage(), checkoutPage(), expectedProductData, FIRST_NAME, LAST_NAME, POSTAL_CODE);

        assertEquals(checkoutStep.getCurrentURL(checkoutPage()), BASE_URL + "checkout-complete.html", "Purchase was not completed!");
        assertEquals(checkoutStep.getCompleteHeader(checkoutPage()), "Thank you for your order!", "Purchase was not completed!");

        checkoutStep
                .clickBackHomeButton(checkoutPage());

        assertEquals(productsStep.getCurrentURL(productsPage()), BASE_URL + "inventory.html", "Purchase was not completed!");
        log.info("Ending e2EPurchaseTest test");
    }
}