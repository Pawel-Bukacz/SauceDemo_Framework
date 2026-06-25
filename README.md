# SauceDemo Test Automation Framework

This repository contains an automated UI testing framework for [SauceDemo](https://www.saucedemo.com/), a sample
e-commerce application.

## 🛠️ Tech Stack & Architecture

* **Language:** Java 25
* **UI Automation:** Selenium WebDriver
* **Test Runner / Assertions:** TestNG
* **Design Pattern:** Page Object Model (POM)

## 🏗️ Test Approach

The framework is designed with scalability and maintainability in mind:

1. **Page Object Model:** UI locators and user actions are separated from test logic into dedicated Page classes (
   `LoginPage`, `ProductsPage`, `CartPage`, `CheckoutPage ).
2. **Data-Driven Approach:** Expected product details and user credentials are provided via Helper classes (
   `ProductsData`, `LoginData`, `CheckoutData`), allowing tests to randomly select and verify dynamic data.
3. **Automated Setup/Teardown:** The `BaseTest` class utilizes TestNG `@BeforeMethod` and `@AfterMethod` annotations to
   automatically launch and close the browser for every test, ensuring a clean state.
4. **Different types of tests:** `Smoke`, `regression`, `e2e` and `cross browser` tests suits are available to run.
5. **Optimized Execution:** Tests run on Google Chrome in `--headless` and `--incognito` modes with popups/notifications
   disabled to ensure fast, un-flaky execution.

---

## 🚀 How to Run the Tests

To run the automated suite locally, open terminal in the project root and execute one of the following Maven commands:

**For smoke tests**:
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/smoke.xml"
```

**For regression tests**:
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/regression.xml"
```

**For e2e tests**:
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/e2e.xml"
```

**For cross browser tests**:
```bash
mvn clean test -DsuiteXmlFile="src/test/resources/cross_browser.xml"
```

**To run all tests:**
```bash
mvn clean test
```

--- 

## ✅ Testing Checklist

Below is the comprehensive checklist of scenarios covered by this automation suite:

### 1. Authentication (`LoginTest.java`)

- [x] Login with valid credentials (Standard User).
- [x] Login with an empty Username field (Verify "Epic sadface: Username is required").
- [x] Login with an empty Password field (Verify "Epic sadface: Password is required").
- [x] Login with invalid credentials (Verify "Epic sadface: Username and password do not match...").

### 2. Sidebar Navigation (`SideBarTest.java`)

- [x] Verify "All Items" sidebar link redirects properly.
- [x] Verify "About" sidebar link redirects to the official SauceLabs website.
- [x] Verify "Logout" link terminates the session and redirects to the login page.
- [x] Verify "Reset App State" successfully clears the shopping cart badge.

### 3. Inventory & Products (`InventoryTest.java`)

- [x] Open a random product details page and verify the specific Product Name.
- [x] Verify the Product Description matches expected data.
- [x] Verify the Product Price matches expected data.
- [x] Verify the Product URL corresponds to the correct item.

### 4. Shopping Cart (`ShoppingCartTest.java`)

- [x] Add a single random item to the cart and verify it appears with the correct Name and Price.
- [x] Add *all* items to the cart and verify the UI matches the expected data for every row.
- [x] Verify the "Continue Shopping" button successfully returns the user to the Inventory page.

### 5. Checkout Flow (`ShoppingCartTest.java` & `E2ETest.java`)

- [x] Verify "Cancel" button on the Checkout Information page returns the user to the Cart.
- [x] Proceed through Checkout with valid User Information (First Name, Last Name, Postal Code).
- [x] Attempt Checkout with an empty First Name field (Verify error).
- [x] Attempt Checkout with an empty Last Name field (Verify error).
- [x] Attempt Checkout with an empty Postal Code field (Verify error).
- [x] **Checkout Overview Validations:**
    - [x] Verify individual Item Price.
    - [x] Verify Subtotal Price calculates correctly.
    - [x] Verify Shipping details ("Free Pony Express Delivery!").
    - [x] Verify Final Total accurately calculates base price + 8% Sales Tax.
- [x] **End-to-End Purchase:** Complete the checkout process and verify the "Thank you for your order!" success message
  and redirection.

### 6. Footer (`FooterTest.java`)

- [x] Verify the Twitter icon links to the correct `https://twitter.com/saucelabs` URL.
- [x] Verify the Facebook icon links to the correct `https://www.facebook.com/saucelabs` URL.
- [x] Verify the LinkedIn icon links to the correct `https://www.linkedin.com/company/sauce-labs/` URL.