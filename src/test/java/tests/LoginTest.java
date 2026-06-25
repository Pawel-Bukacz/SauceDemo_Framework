package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;
import utils.Retry;

import static org.testng.Assert.assertEquals;
import static pages.BasePage.*;
import static helpers.LoginData.*;


@Log4j2
public class LoginTest extends BaseTest {

    @DataProvider(
            name = "Login data for negative tests."
    )
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"login", "password", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(
            testName = "Verify login with correct credentials.",
            description = "Check login with valid data.",
            groups = "smoke",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Login page")
    @Description("Verify login with valid data")
    @Severity(SeverityLevel.BLOCKER)
    public void checkLoginWithCorrectData() {
        log.info("Starting checkLoginWithCorrectData test");
        loginStep
                .loginAs(LOGIN, PASSWORD, loginPage());

        assertEquals(loginStep.getCurrentURL(loginPage()), BASE_URL + "inventory.html", "Login unsuccessful");
        log.info("Ending checkLoginWithCorrectData test");
    }

    @Test(
            testName = "Verify login with incorrect credentials or lack thereof > ",
            description = "Verify login with incorrect credentials or lack thereof.",
            groups = "regression",
            dataProvider = "Login data for negative tests.",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Login page")
    @Description("Verify login with incorrect credentials or lack thereof")
    @Severity(SeverityLevel.BLOCKER)
    public void checkLoginWithInvalidDataUsingDataProvider(String user, String password, String errorMessage) {
        log.info("Starting checkLoginWithInvalidDataUsingDataProvider test");
        loginStep
                .loginAs(user, password, loginPage());

        assertEquals(loginStep.getErrorMessage(loginPage()), errorMessage, "Incorrect error message");
        log.info("Ending checkLoginWithInvalidDataUsingDataProvider test");
    }
}