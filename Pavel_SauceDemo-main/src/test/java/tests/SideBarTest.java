package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Retry;

import static helpers.LoginData.*;
import static org.testng.Assert.assertEquals;
import static pages.BasePage.*;


@Log4j2
public class SideBarTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        log.info("Starting setup for SideBarTest");
        loginStep
                .loginAs(LOGIN, PASSWORD, loginPage());
    }

    @Test(
            testName = "Verify All Items button in Sidebar menu.",
            description = "Verify All Items button in Sidebar menu returns to Products page.",
            groups = "smoke"
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Sidebar")
    @Description("Verify 'All Items' button in Sidebar menu returns to Products page.")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAllItemsButton() {
        log.info("Starting checkAllItemsButton test");
        productsStep
                .addRandomItem(productsPage(), expectedProductData);
        sidebarStep
                .openAllItems(sidebarPage());

        assertEquals(productsStep.getCurrentURL(productsPage()), BASE_URL + "inventory.html", "Unsuccessful logout!");
        log.info("Ending checkAllItemsButton test");
    }

    @Test(
            testName = "Verify About button in Sidebar menu.",
            description = "Verify About button in Sidebar menu redirects to Sauce Labs official page.",
            groups = "regression",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Sidebar")
    @Description("Verify About button in Sidebar menu redirects to Sauce Labs official page.")
    @Severity(SeverityLevel.NORMAL)
    public void checkAboutButton() {
        log.info("Starting checkAboutButton test");
        sidebarStep
                .openAbout(sidebarPage());

        assertEquals(productsStep.getCurrentURL(productsPage()), "https://saucelabs.com/", "Didn't land on official SauceLabs page!");
        log.info("Ending checkAboutButton test");
    }

    @Test(
            testName = "Verify Logout button in Sidebar menu.",
            description = "Verify Logout button in Sidebar menu triggers logout.",
            groups = "smoke",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Sidebar")
    @Description("Verify Logout button in Sidebar menu triggers logout.")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLogoutButton() {
        log.info("Starting checkLogoutButton test");
        sidebarStep
                .logout(sidebarPage());

        assertEquals(loginStep.getCurrentURL(loginPage()), BASE_URL, "Unsuccessful logout!");
        log.info("Ending checkLogoutButton test");
    }

    @Test(
            testName = "Verify Reset App State button in Sidebar menu.",
            description = "Verify Reset App State button in Sidebar menu removes all items from cart.",
            groups = "smoke",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Sidebar")
    @Description("Verify Reset App State button in Sidebar menu removes all items from cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void checkResetAppStateButton() {
        log.info("Starting checkResetAppStateButton test");
        cartStep
                .addRandomItemAndReturnToProducts(cartPage(), productsPage(), expectedProductData);
        sidebarStep
                .resetAppState(sidebarPage());

        assertEquals(cartStep.getCartBadgeCount(cartPage()), "", "Items are still in the cart!");
        log.info("Ending checkResetAppStateButton test");
    }
}