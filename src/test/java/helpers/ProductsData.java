package helpers;

import java.util.*;


public class ProductsData {

    public static List<Map<String, String>> productsData() {
        return List.of(
                Map.of(
                        "ProductName", "Sauce Labs Fleece Jacket",
                        "ProductDescription", "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                        "URL", "https://www.saucedemo.com/inventory-item.html?id=5",
                        "Price", "$49.99"
                ),
                Map.of(
                        "ProductName", "Sauce Labs Backpack",
                        "ProductDescription", "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                        "URL", "https://www.saucedemo.com/inventory-item.html?id=4",
                        "Price", "$29.99"
                ),
                Map.of(
                        "ProductName", "Sauce Labs Bolt T-Shirt",
                        "ProductDescription", "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                        "URL", "https://www.saucedemo.com/inventory-item.html?id=1",
                        "Price", "$15.99"
                ),
                Map.of(
                        "ProductName", "Test.allTheThings() T-Shirt (Red)",
                        "ProductDescription", "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.",
                        "URL", "https://www.saucedemo.com/inventory-item.html?id=3",
                        "Price", "$15.99"
                ),
                Map.of(
                        "ProductName", "Sauce Labs Bike Light",
                        "ProductDescription", "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                        "URL", "https://www.saucedemo.com/inventory-item.html?id=0", // id=0? xD
                        "Price", "$9.99"
                ),
                Map.of(
                        "ProductName", "Sauce Labs Onesie",
                        "ProductDescription", "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                        "URL", "https://www.saucedemo.com/inventory-item.html?id=2",
                        "Price", "$7.99"
                )
        );
    }
}