package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Objects;


@Log4j2
public class SidebarPage extends BasePage {

    @FindBy(css = "#react-burger-menu-btn")
    WebElement burgerMenuButton;
    @FindBy(xpath = "//a[contains(@id, 'inventory_sidebar_link')]")
    WebElement allItemsLink;
    @FindBy(xpath = "//a[contains(@id, 'about_sidebar_link')]")
    WebElement aboutLink;
    @FindBy(xpath = "//a[contains(@id, 'logout_sidebar_link')]")
    WebElement logoutLink;
    @FindBy(xpath = "//a[contains(@id, 'reset_sidebar_link')]")
    WebElement resetAppStateLink;


    public SidebarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SidebarPage waitForPageToLoad() {
        log.info("Wait for Sidebar to load");
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return document.readyState")).toString().equals("complete");
            }
        };

        return this;
    }

    @Step("Open sidebar menu.")
    public SidebarPage openMenu() {
        log.info("Opening sidebar menu");
        burgerMenuButton.click();

        return this;
    }

    @Step("Click on 'All Items' link.")
    public SidebarPage clickAllItems() {
        log.info("Clicking on 'All Items' button");
        allItemsLink.click();

        return this;
    }

    @Step("Click on 'About' link.")
    public SidebarPage clickAbout() {
        log.info("Clicking on 'About' button");
        aboutLink.click();

        return this;
    }

    @Step("Click on 'Logout' link.")
    public SidebarPage clickLogout() {
        log.info("Clicking on 'Logout' button");
        logoutLink.click();

        return this;
    }

    @Step("Click on 'Reset App State' link.")
    public SidebarPage clickResetAppState() {
        log.info("Clicking on 'Reset App State' button");
        resetAppStateLink.click();

        return this;
    }
}