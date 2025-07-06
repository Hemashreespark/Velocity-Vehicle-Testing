package tests;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void testStickyNavigationBar() {
        // Step 1: Load the homepage
        HomePage homePage = new HomePage(driver);

        // Step 2: Get the navigation bar element
        WebElement navBar = homePage.getNavBar();

        // Step 3: Get the Y-position of the nav bar before scrolling
        Number initialY = (Number) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].getBoundingClientRect().top;", navBar);

        // Step 4: Scroll down the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1000);");

        // Step 5: Get the Y-position of the nav bar after scrolling
        Number scrolledY = (Number) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].getBoundingClientRect().top;", navBar);

        // Step 6: Convert to double and compare
        double initialYValue = initialY.doubleValue();
        double scrolledYValue = scrolledY.doubleValue();

        // Step 7: Assert that the nav bar position hasn't changed (i.e., it's sticky)
        Assert.assertEquals(scrolledYValue, initialYValue, "Navigation bar is not sticky.");
    }
}
