package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;


@Log4j2
public class LoginStep {

    WebDriver driver;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Login as user '{user}'.")
    public void loginAs(String user, String password, LoginPage loginPage) {
        log.info("Login as user '{}' and password '{}'",  user, password);
        loginPage
                .open()
                .waitForPageToLoad()
                .login(user, password);
    }

    @Step("Get current URL from login page.")
    public String getCurrentURL(LoginPage loginPage) {
        log.info("Get current URL");
        return loginPage.getCurrentURL();
    }

    @Step("Get error message from login page.")
    public String getErrorMessage(LoginPage loginPage) {
        log.info("Get error message");
        return loginPage.getErrorMessage();
    }
}
