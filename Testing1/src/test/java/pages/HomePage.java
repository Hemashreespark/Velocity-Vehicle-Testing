package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    // Existing locator
    private By signUpButton = By.xpath("/html/body/app-root/app-navbar/mat-toolbar/div[3]/button[4]/span[2]");

    // New locator for navigation bar
    private By navBar = By.cssSelector("mat-toolbar"); // Update if needed

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    // New method to get the navigation bar element
    public WebElement getNavBar() {
        return driver.findElement(navBar);
    }
}
