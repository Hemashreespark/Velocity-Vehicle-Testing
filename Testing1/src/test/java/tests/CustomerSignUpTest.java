package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CustomerLoginPage;
import pages.CustomerSignupPage;
import pages.HomePage;

public class CustomerSignUpTest extends BaseTest {

    @Test

    public void testValidSignUp() 
    {

        // Step 1: From home → click signup → lands on login page

        HomePage homePage = new HomePage(driver);

        homePage.clickSignUpButton();

        // Step 2: On login page → click signup link to go to signup form

        CustomerLoginPage loginPage = new CustomerLoginPage(driver);

        loginPage.clickSignUpLink();

        // Step 3: Fill signup form and submit

        CustomerSignupPage signUpPage = new CustomerSignupPage(driver);

        signUpPage.fillSignUpForm(

                "Hemashree", "female", "two@gmail.com", "123 Main St",

                "1357924681", "Hemashree@16", "Hemashree@16", "Chennai", "600001", "21"

        );

        signUpPage.clickSubmit();

        // Step 4: Validate redirection to home page after signup
        Assert.assertTrue(
        	    driver.getCurrentUrl().contains("/customer"),"Signup did not redirect to customer home page."
        	);
        
        System.out.println("Customer successfully signed up and redirected to home page");


    }
    
    @Test
    public void InvalidSignup() throws InterruptedException {
        // Step 1: Navigate to signup form
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpButton();

        CustomerLoginPage loginPage = new CustomerLoginPage(driver);
        loginPage.clickSignUpLink();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        // Step 2: Fill form with invalid username and submit
        CustomerSignupPage signUpPage = new CustomerSignupPage(driver);
        signUpPage.fillSignUpForm(
            "Bobby", "female", "special16@gmail.com", "Siruseri",
            "9999999916", "Hemashree@16", "Hemashree@16", "Chennai", "600021", "21"
        );
        signUpPage.clickSubmit();

        
        WebElement popup = wait.until(ExpectedConditions.elementToBeClickable(
        		signUpPage.getOkButton()));



        Assert.assertTrue(popup.isDisplayed(), "Expected error message not found error");
        System.out.println("Error is shown when customer tries to signup with already used mail id and phone number");
    }
    
    
    
    @Test
    public void UsernameWithSpecialCharacter_404Check() throws InterruptedException {
        // Step 1: Navigate to signup form
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpButton();

        CustomerLoginPage loginPage = new CustomerLoginPage(driver);
        loginPage.clickSignUpLink();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        // Step 2: Fill form with invalid username and submit
        CustomerSignupPage signUpPage = new CustomerSignupPage(driver);
        signUpPage.fillSignUpForm(
            "@@$#", "female", "special16@gmail.com", "Siruseri",
            "9999999916", "Hemashree@16", "Hemashree@16", "Chennai", "600021", "21"
        );
        signUpPage.clickSubmit();

        // Step 3: Wait for overlay and click OK on info popup
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".popup-overlay")));

        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(
        		signUpPage.getOkButton()));
        okButton.click();

        // Step 4: Click profile icon using JavaScript
        WebElement profileIcon = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[@class='user-initials-circle']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileIcon);

        Thread.sleep(2000);
        // Step 5: Wait for menu and click "About"
     // Wait for any mat-menu-panel to be visible
        WebElement menuPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@id,'mat-menu-panel')]")
        ));

        Thread.sleep(2000);
        // Then find and click the "About" option inside it

        WebElement aboutOption = wait.until(ExpectedConditions.elementToBeClickable(signUpPage.getAboutOption()));
        aboutOption.click();

        

        Thread.sleep(2000);
        // Step 6: Assert 404 page is shown
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/profile"), "Expected profile page not found error");
    }

    @Test
    public void missingField() throws InterruptedException {
        // Step 1: Navigate to signup form
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpButton();

        CustomerLoginPage loginPage = new CustomerLoginPage(driver);
        loginPage.clickSignUpLink();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        // Step 2: Fill form with invalid username and submit
        CustomerSignupPage signUpPage = new CustomerSignupPage(driver);
        signUpPage.fillSignUpForm(
            "Bobby", "female", "special16@gmail.com", "Siruseri",
            "9999999916", "Hemashree@16", "Hemashree@16", "", "600021", "21"
        );
        signUpPage.clickSubmit();
        
        WebElement errfield=driver.findElement(By.xpath("/html/body/app-root/app-customer-sign-up/div/div[2]/form/div[4]/div[2]/div/div"));

        Assert.assertTrue(errfield.isDisplayed(), "Expected error message not found error");
        System.out.println("Error is shown when customer leaves a field empty");
    }

    
    
    
    

}
 