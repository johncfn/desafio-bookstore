package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterBookStorePage {
	
	private WebDriver driver;
	
	public RegisterBookStorePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By firstName = By.id("firstname");
	private By lastName = By.id("lastname");
	private By userName = By.id("userName");
	private By password = By.id("password");
	private By buttonRegister = By.id("register");
	private By backToLogin = By.id("gotologin");
	
	public void registerNewUser(String fName, String lName, String uName, String pwd) {
		driver.findElement(firstName).sendKeys(fName);
		driver.findElement(lastName).sendKeys(lName);
		driver.findElement(userName).sendKeys(uName);
		driver.findElement(password).sendKeys(pwd);						
	}
	
	public void submitRegistration() {
		driver.findElement(buttonRegister).click();	
	}
	
	public void clickBackToLoginButton() {
		driver.findElement(backToLogin).click();
	}
	
	public void loginNewRegisteredUser() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(buttonRegister)).click();
		driver.findElement(backToLogin).click();
	}
	
	public String getUserCreatedAlertMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		alert.accept();		
		return message;
	}
	
	

}
