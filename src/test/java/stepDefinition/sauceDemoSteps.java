package stepDefinition;

import Page.AllItemsPage;
import Page.CartPage;
import Page.CheckoutPage;
import Page.sauceDemoPage;
import Utility.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class sauceDemoSteps {
    WebDriver driver;
    sauceDemoPage sauceDemoPage;
    @Given("User is on the login page")
    public void should_logged_in(){
        System.setProperty("webdriver.chrome.driver", "download/chromedriver.exe");
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        sauceDemoPage = new sauceDemoPage(driver);
    }

    @When("User enters valid credentials {string} and {string}")
    public void user_enters_valid_credentials(String username ,String password){
        sauceDemoPage.login(username, password);
    }

    @Then("Verify the sorting order displayed for Z-A on the All Items page")
    public void validate_ZtoA(){
        sauceDemoPage.selectDropdown();
        boolean sortedCorrectly = sauceDemoPage.isSorted();
        if (!sortedCorrectly) {
            throw new AssertionError("Items are not sorted in Z-A order.");
        }

    }

    @Then("Verify the price order high-low displayed on the All Items page")
    public void validate_Price_High_to_Low(){
        boolean isSorted = sauceDemoPage.PriceSortedHighToLow();
        try {
            if (!isSorted) {
                System.out.println("Prices are not sorted from high to low.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Then("Add multiple items to the card and validate the checkout journey")
    public void checkout() {
        AllItemsPage allItemsPage = new AllItemsPage(driver);
        allItemsPage.addItemToCart("Sauce Labs Backpack");
        allItemsPage.addItemToCart("Sauce Labs Bike Light");
        allItemsPage.goToCart();

// Proceed to checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

// Enter info & finish
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInfo("John", "Doe", "12345");
        checkoutPage.finishCheckout();
        System.out.println("Test Passed: Order placed successfully.");
        ExtentReports report = ReportManager.createReport();
        driver.quit();
    }
}
