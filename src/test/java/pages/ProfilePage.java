package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
	
	private WebDriver driver;
	
	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By usernameLogin = By.id("userName-value");
	private By logoutButton = By.xpath("//button[text()='Log out']");
	
	public String checkUsernameLogin () {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName-value")));
		return driver.findElement(usernameLogin).getText();
	}
	
	public boolean isLogoutButtonDisplayed () {
		return driver.findElement(logoutButton).isDisplayed();
	}

}
