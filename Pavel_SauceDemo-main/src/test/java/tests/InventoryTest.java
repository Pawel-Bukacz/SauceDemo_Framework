package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import static helpers.LoginData.*;
import static org.testng.Assert.assertEquals;


@Log4j2
public class InventoryTest extends BaseTest {

    @Test(
            groups = "smoke",
            description = "Verify random product's details - name, description, price and URL.",
            testName = "Verify product details."
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Inventory page")
    @Description("Verify details of a random product.")
    @Severity(SeverityLevel.BLOCKER)
    public void checkRandomProductDetails() {
        log.info("Starting checkRandomProductDetails test");
        loginStep
                .loginAs(LOGIN, PASSWORD, loginPage());

        int randomNumber = (int) (Math.random() * expectedProductData.size());
        String[] actualDetails = productsStep.openProductDetails(productsPage(), expectedProductData.get(randomNumber));

        assertEquals(actualDetails[0], expectedProductData.get(randomNumber).get("ProductName"), "Incorrect product name!");
        assertEquals(actualDetails[1], expectedProductData.get(randomNumber).get("ProductDescription"), "Incorrect product description!");
        assertEquals(actualDetails[2], expectedProductData.get(randomNumber).get("Price"), "Incorrect product price!");
        assertEquals(actualDetails[3], expectedProductData.get(randomNumber).get("URL"), "Incorrect product URL!");
        log.info("Ending checkRandomProductDetails test");
    }
}