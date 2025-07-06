package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class CustomerLoginPage {
   private WebDriver driver;
   private By usernameField = By.xpath("//*[@id=\"username\"]");       // 🔁 Update
   private By passwordField = By.xpath("//*[@id=\"password\"]");       // 🔁 Update
   private By loginButton = By.xpath("/html/body/app-root/app-sign-in/div/div[2]/div[2]/form/div[4]/button");      // 🔁 Update
   private By signUpLink = By.xpath("/html/body/app-root/app-sign-in/div/div[2]/div[2]/form/div[6]/p/a");        // Optional
   public CustomerLoginPage(WebDriver driver) {
       this.driver = driver;
   }
   public void enterUsername(String username) {
       driver.findElement(usernameField).sendKeys(username);
   }
   public void enterPassword(String password) {
       driver.findElement(passwordField).sendKeys(password);
   }
   public void clickLoginButton() {
       driver.findElement(loginButton).click();
   }
   public void login(String username, String password) throws InterruptedException {
       enterUsername(username);
       enterPassword(password);
       clickLoginButton();
   }
   public void clickSignUpLink() {
       driver.findElement(signUpLink).click();
   }
}