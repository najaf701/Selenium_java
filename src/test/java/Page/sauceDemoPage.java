package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sauceDemoPage {
    WebDriver driver;

    // Constructor
    public sauceDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes WebElements
    }

    // Element Locators
    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    By itemNameLocator = By.cssSelector(".inventory_item_name");
    By priceLocator = By.cssSelector(".inventory_item_price");
    // Actions
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(String.valueOf(loginButton))));
        loginButton.click();
    }

    public void selectDropdown() {
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Name (Z to A)");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isSorted() {
        //Get all item names from UI
        List<WebElement> itemElements = driver.findElements(itemNameLocator);
        List<String> actualItemNames = new ArrayList<>();

        for (WebElement item : itemElements) {
            actualItemNames.add(item.getText().trim());
        }
        List<String> expectedSortedNames = new ArrayList<>(actualItemNames);
        expectedSortedNames.sort(Collections.reverseOrder());

        //Compare UI order with expected order
        return actualItemNames.equals(expectedSortedNames);
    }

    public boolean PriceSortedHighToLow() {
        List<WebElement> priceElements = driver.findElements(priceLocator);
        List<Double> actualPrices = new ArrayList<>();

        // Step 1: Extract price values from UI and convert to double
        for (WebElement price : priceElements) {
            String priceText = price.getText().replace("$", "").trim();
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Step 2: Create a copy and sort it in descending order
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        // Step 3: Compare actual order with expected descending order
        return actualPrices.equals(expectedPrices);
    }
}
