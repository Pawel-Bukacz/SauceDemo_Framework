package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;

import java.util.List;
import java.util.Map;


@Log4j2
public class ProductsStep {

    WebDriver driver;

    public ProductsStep(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Add random item to cart.")
    public void addRandomItem(ProductsPage productsPage, List<Map<String, String>> productData) {
        log.info("Add random item to cart.");
        productsPage.addRandomItemToCart(productData);
    }

    @Step("Open random product details.")
    public String[] openProductDetails(ProductsPage productsPage, Map<String, String> productData) {
        log.info("Open random product details");
        productsPage
                .openURL(productData.get("URL"))
                .openProduct(productData.get("ProductName"));

        return new String[]{
                productsPage.getProductName(),
                productsPage.getProductDescription(),
                productsPage.getProductPrice(),
                productsPage.getCurrentURL()
        };
    }

    @Step("Get current URL from products page.")
    public String getCurrentURL(ProductsPage productsPage) {
        log.info("Get current URL");
        return productsPage.getCurrentURL();
    }

    @Step("Get Twitter footer URL.")
    public String getTwitterURL(ProductsPage productsPage) {
        log.info("Get Twitter footer URL");
        return productsPage.getTwitterURL();
    }

    @Step("Get Facebook footer URL.")
    public String getFacebookURL(ProductsPage productsPage) {
        log.info("Get Facebook footer URL");
        return productsPage.getFacebookURL();
    }

    @Step("Get LinkedIn footer URL.")
    public String getLinkedInURL(ProductsPage productsPage) {
        log.info("Get LinkedIn footer URL");
        return productsPage.getLinkedInURL();
    }
}
