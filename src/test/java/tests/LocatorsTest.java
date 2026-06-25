package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import utils.*;

import static helpers.LoginData.*;
import static org.testng.Assert.*;


@Log4j2
public class LocatorsTest extends BaseTest {

    @Test(
            groups = "regression",
            description = "Verify different locator on Products page.",
            testName = "Verify locators on Products page.",
            retryAnalyzer = Retry.class
    )
    @Owner("Pavel")
    @Epic("Sauce demo")
    @Feature("Locators")
    @Description("Verify different selectors across products page")
    @Severity(SeverityLevel.NORMAL)
    public void allTheLocators() {
        log.info("Starting allTheLocators test");
        loginPage().open();
        loginPage().login(LOGIN, PASSWORD);

        // Default Selenium locators.
        String id = driver.findElement(By.id("header_container")).getText();
        String name = driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).getText();
        String className = driver.findElement(By.className("title")).getText();
        String tag = driver.findElement(By.tagName("footer")).getText();
        String linkedText = driver.findElement(By.linkText("Sauce Labs Bike Light")).getText();
        String partialLinkText = driver.findElement(By.partialLinkText("Bike Light")).getText();

        // Xpath locators.
        String xpathAttribute = driver.findElement(By.xpath("//*[@data-test='item-1-title-link']")).getText();
        String xpathTextFullMatch = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText();
        String xpathAttributePartialMatch = driver.findElement(By.xpath("//a[contains(@id, 'ut_sidebar_link')]")).getAttribute("textContent");
        String xpathTextPartialMatch = driver.findElement(By.xpath("//div[contains(., 'All Rights Reserved')]")).getText();
        String xpathAncestor = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']")).getText();
        String xpathDescendant = driver.findElement(By.xpath("//div[@class='inventory_list']//descendant::div[@class='inventory_item_price']")).getText();
        String xpathFollowing = driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']//following::button[1]")).getText();
        WebElement xpathParent = driver.findElement(By.xpath("//img[@alt='Sauce Labs Fleece Jacket']/parent::a"));
        String xpathPreceding = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']//preceding::div[@class='inventory_item_name '][1]")).getText();
        WebElement xpathWithAndCondition = driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ' and @id='add-to-cart-sauce-labs-backpack']"));

        // CSS locators.
        String cssClass = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        String cssClass1Class2 = driver.findElement(By.cssSelector(".bm-item.menu-item")).getAttribute("textContent");
        String cssClass1_Class2 = driver.findElement(By.cssSelector(".inventory_item .inventory_item_name")).getText();
        WebElement cssId = driver.findElement(By.cssSelector("#item_5_img_link"));
        String cssTag = driver.findElement(By.cssSelector("footer")).getText();
        String cssTagClass = driver.findElement(By.cssSelector("div.inventory_item_desc")).getText();
        String cssAttributeValue = driver.findElement(By.cssSelector("[data-test=item-5-title-link]")).getText();
        WebElement cssAttributeContainingValue = driver.findElement(By.cssSelector("[class~='btn_small']"));
        WebElement cssAttributeContainOrStartsWithValue = driver.findElement(By.cssSelector("[data-test|='social']"));
        WebElement cssAttributeStartsWithValue = driver.findElement(By.cssSelector("[id^='react-burger']"));
        String cssAttributeEndsWithValue = driver.findElement(By.cssSelector("[data-test$='-2-title-link']")).getText();
        String cssAttributeAnyMatchValue = driver.findElement(By.cssSelector("[data-test*='item-1-title']")).getText();


        /*
        ================== Assertions ==================
         */


        // Assertions for default Selenium locators.
        assertTrue(id.contains("Swag Labs"));
        assertEquals(name, "Add to cart");
        assertEquals(className, "Products");
        assertTrue(tag.contains("Sauce Labs"));
        assertEquals(linkedText, "Sauce Labs Bike Light");
        assertEquals(partialLinkText, "Sauce Labs Bike Light");

        // Assertions for xpath locators.
        assertEquals(xpathAttribute, "Sauce Labs Bolt T-Shirt");
        assertEquals(xpathTextFullMatch, "Sauce Labs Backpack");
        assertEquals(xpathAttributePartialMatch, "About");
        assertTrue(xpathTextPartialMatch.contains("All Rights Reserved"));
        assertTrue(xpathAncestor.contains("Backpack"));
        assertTrue(xpathDescendant.contains(".99"));
        assertEquals(xpathFollowing, "Add to cart");
        assertTrue(xpathParent.isDisplayed());
        assertEquals(xpathPreceding, "Sauce Labs Onesie");
        assertTrue(xpathWithAndCondition.isDisplayed());

        // Assertions for CSS locators.
        assertTrue(cssClass.contains("Backpack"));
        assertEquals(cssClass1Class2, "All Items");
        assertEquals(cssClass1_Class2, "Sauce Labs Backpack");
        assertTrue(cssId.isDisplayed());
        assertTrue(cssTag.contains("Rights"));
        assertTrue(cssTagClass.contains("with the sleek"));
        assertEquals(cssAttributeValue, "Sauce Labs Fleece Jacket");
        assertTrue(cssAttributeContainingValue.isDisplayed());
        assertTrue(cssAttributeContainOrStartsWithValue.isDisplayed());
        assertTrue(cssAttributeStartsWithValue.isDisplayed());
        assertEquals(cssAttributeEndsWithValue, "Sauce Labs Onesie");
        assertEquals(cssAttributeAnyMatchValue, "Sauce Labs Bolt T-Shirt");

        log.info("Ending allTheLocators test");
    }
}