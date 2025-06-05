package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By inputUsername = By.id("userName");
	private By inputPassword = By.id("password");
	private By buttonLogin = By.id("login");
	
	public void loginUser(String username, String password) {
		driver.findElement(inputUsername).sendKeys(username);
		driver.findElement(inputPassword ).sendKeys(password);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(buttonLogin)).click();
	}

}
