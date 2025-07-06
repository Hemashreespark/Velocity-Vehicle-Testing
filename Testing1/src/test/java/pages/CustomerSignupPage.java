package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class CustomerSignupPage {
   private WebDriver driver;
   private By nameField = By.xpath("//*[@id=\"name\"]");
   private By genderMale = By.xpath("/html/body/app-root/app-customer-sign-up/div/div[2]/form/div[1]/div[2]/div/label[1]"); // 🔁 Add female if needed
   private By emailField = By.xpath("//*[@id=\"email\"]");
   private By addressField = By.xpath("//*[@id=\"address\"]");
   private By phoneField = By.xpath("//*[@id=\"phone\"]");
   private By passwordField = By.xpath("//*[@id=\"password\"]");
   private By confirmPasswordField = By.xpath("//*[@id=\"confirmPassword\"]");
   private By cityField = By.xpath("//*[@id=\"city\"]");
   private By pincodeField = By.xpath("//*[@id=\"pincode\"]");
   private By customerAge = By.xpath("//*[@id=\"age\"]");
   private By submitButton = By.xpath("/html/body/app-root/app-customer-sign-up/div/div[2]/form/div[6]/button");
   private By ok=By.xpath("//button[text()='OK']");
   

   private By profileIcon = By.xpath("//span[@class='user-initials-circle']");
   private By aboutOption = By.xpath("//div[contains(@id,'mat-menu-panel')]//button[.//span[text()='About']]");

   public CustomerSignupPage(WebDriver driver) {
       this.driver = driver;
   }
   public void fillSignUpForm(String name, String gender, String email, String address, String phone,
                              String password, String confirmPassword, String city, String pincode, String age) {
       driver.findElement(nameField).sendKeys(name);
       if (gender.equalsIgnoreCase("male")) {
           driver.findElement(genderMale).click();
       } else {
           driver.findElement(By.xpath("/html/body/app-root/app-customer-sign-up/div/div[2]/form/div[1]/div[2]/div/label[2]")).click(); // 🔁 Ensure correct ID exists
       }
       driver.findElement(emailField).sendKeys(email);
       driver.findElement(addressField).sendKeys(address);
       driver.findElement(phoneField).sendKeys(phone);
       driver.findElement(passwordField).sendKeys(password);
       driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
       driver.findElement(cityField).sendKeys(city);
       driver.findElement(pincodeField).sendKeys(pincode);
       driver.findElement(customerAge).sendKeys(age);
   }

   public WebElement getProfileIcon() {return driver.findElement(profileIcon);}

   public WebElement getAboutOption() {return driver.findElement(aboutOption);}

	
	public WebElement getOkButton() {return driver.findElement(ok);}

   public void clickSubmit() {
       driver.findElement(submitButton).click();
   }
}