package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllItemsPage {
    WebDriver driver;

    public AllItemsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Add items by product name
    public void addItemToCart(String productName) {
        // Construct dynamic XPath based on item name
        String addToCartXpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(addToCartXpath)).click();
    }

    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
