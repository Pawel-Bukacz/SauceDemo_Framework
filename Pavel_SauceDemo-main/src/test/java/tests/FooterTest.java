package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import utils.Retry;

import static helpers.LoginData.*;
import static org.testng.Assert.assertEquals;


@Log4j2
public class FooterTest extends BaseTest {

    @Test(
            groups = "regression",
            description = "Test verifies URLs to social networks, encoded into respective icons.",
            testName = "Verify socials URLs in footer.",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Footer")
    @Description("Verify socials URLs in footer.")
    @Severity(SeverityLevel.NORMAL)
    public void checkFooterSocialsLinks() {
        log.info("Starting checkFooterSocialsLinks test");
        loginStep
                .loginAs(LOGIN, PASSWORD, loginPage());

        assertEquals(productsStep.getTwitterURL(productsPage()), "https://twitter.com/saucelabs", "Incorrect twitter URL!");
        assertEquals(productsStep.getFacebookURL(productsPage()), "https://www.facebook.com/saucelabs", "Incorrect twitter URL!");
        assertEquals(productsStep.getLinkedInURL(productsPage()), "https://www.linkedin.com/company/sauce-labs/", "Incorrect twitter URL!");
        log.info("Ending checkFooterSocialsLinks test");
    }
}