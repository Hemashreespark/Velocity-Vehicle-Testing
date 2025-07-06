package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CustomerLoginPage;
import pages.HomePage;

public class CustomerLoginTest extends BaseTest {

    @Test
    public void testValidLogin() throws InterruptedException{
        // Step 1: From home → click signup to land on login page
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpButton();

        // Step 2: Login page → enter credentials and login
        CustomerLoginPage loginPage = new CustomerLoginPage(driver);
        loginPage.login("C_BOBBYCHE001", "Bobby@16");

        // Step 3: Wait for URL to contain "/customer"
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean urlContainsCustomer = wait.until(ExpectedConditions.urlContains("/customer"));

        // Step 4: Log current URL for debugging
        //String currentUrl = driver.getCurrentUrl();
        //System.out.println("Current URL after login: " + currentUrl);

        // Step 5: Assert login success
        Assert.assertTrue(urlContainsCustomer, "Login failed and did not redirect to home page.");
        System.out.println("Successful Login and redirected to home page");
    }
    
    @Test
    public void testInvalidLogin() throws InterruptedException {
        // Step 1: Navigate to login page
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpButton();

        // Step 2: Attempt login with invalid credentials
        CustomerLoginPage loginPage = new CustomerLoginPage(driver);
        loginPage.login("INVALID_USER", "WrongPassword");
        

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-sign-in/div/div[2]/div[1]/div")));

        // Step 5: Assert that login did NOT redirect to customer page
        //Assert.assertFalse(currentUrl.contains("/customer"), "Invalid login should not redirect to customer page.");
        
        Assert.assertTrue(errorMsg.isDisplayed(),"Error message not displayed for invalid login");
        System.out.println("Error message is shown for invalid login");

    }
    
}
