package tests;

import helpers.ProductsData;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import pages.*;
import steps.*;
import utils.DriverManager;

import java.time.Duration;
import java.util.*;


@Log4j2
@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected WebDriver driver;
    public List<Map<String, String>> expectedProductData;

    private static final ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();
    private static final ThreadLocal<ProductsPage> productsPage = new ThreadLocal<>();
    private static final ThreadLocal<CartPage> cartPage = new ThreadLocal<>();
    private static final ThreadLocal<CheckoutPage> checkoutPage = new ThreadLocal<>();
    private static final ThreadLocal<SidebarPage> sidebarPage = new ThreadLocal<>();

    protected LoginStep loginStep;
    protected CartStep cartStep;
    protected ProductsStep productsStep;
    protected CheckoutStep checkoutStep;
    protected SidebarStep sidebarStep;


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    @Step("Browsers preparation.")
    public void beforeTest(@Optional("chrome") String browser, ITestContext iTestContext) {
        DriverManager.setDriver(createDriver(browser));
        this.driver = DriverManager.getDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        iTestContext.setAttribute("driver", driver);
        loginPage.set(new LoginPage(this.driver));
        productsPage.set(new ProductsPage(this.driver));
        cartPage.set(new CartPage(this.driver));
        checkoutPage.set(new CheckoutPage(this.driver));
        sidebarPage.set(new SidebarPage(this.driver));
        loginStep = new LoginStep(this.driver);
        cartStep = new CartStep(this.driver);
        productsStep = new ProductsStep(this.driver);
        checkoutStep = new CheckoutStep(this.driver);
        sidebarStep = new SidebarStep(this.driver);
        expectedProductData = ProductsData.productsData();
    }

    private WebDriver createDriver(String browser) {
        log.info("Creating driver with browser: {}", browser);
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (browser.equalsIgnoreCase("firefox")) {
            if (System.getProperty("headless", "true").equals("true")) {
                chromeOptions.addArguments("--headless");
            }
            firefoxOptions.addArguments("--incognito");
            firefoxOptions.addArguments("--disable-notifications");
            firefoxOptions.addArguments("--disable-popup-blocking");
            firefoxOptions.addArguments("--disable-infobars");
            firefoxOptions.addArguments("--no-sandbox");
            firefoxOptions.addArguments("--disable-dev-shm-usage");
            firefoxOptions.addArguments("--disable-gpu");
            return new FirefoxDriver(firefoxOptions);
        } else if (browser.equalsIgnoreCase("chrome")) {
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-infobars");
            if (System.getProperty("headless", "true").equals("true")) {
                chromeOptions.addArguments("--headless");
            }
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-gpu");
            return new ChromeDriver(chromeOptions);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    @AfterMethod(alwaysRun = true)
    @Step("Closing browsers.")
    public void afterTest() {
        log.info("Closing browsers");
        DriverManager.quit();
        loginPage.remove();
        productsPage.remove();
        cartPage.remove();
        checkoutPage.remove();
        sidebarPage.remove();
    }

    public ProductsPage productsPage() {
        return productsPage.get();
    }

    public LoginPage loginPage() {
        return loginPage.get();
    }

    public CartPage cartPage() {
        return cartPage.get();
    }

    public CheckoutPage checkoutPage() {
        return checkoutPage.get();
    }

    public SidebarPage sidebarPage() {
        return sidebarPage.get();
    }
}