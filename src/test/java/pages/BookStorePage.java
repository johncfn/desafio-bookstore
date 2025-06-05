package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookStorePage {
	
	private WebDriver driver;
	
	public BookStorePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By searchFied = By.id("searchBox");
	private By table = By.xpath("//div[@class='ReactTable -striped -highlight']");	
	private By bookStoreButton = By.xpath("//div[@class='element-list collapse show']//li[@id='item-2']");		
	private By bookTitle = By.xpath("//div[@class='rt-tbody']//div[1]//div[1]//div[2]"); 
	private By bookAuthor = By.xpath("//div[@class='rt-tbody']/div/div/div[4]");

	
	public void searchABookOnSearchField(String searchWord) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(searchFied));
		
		driver.findElement(searchFied).sendKeys(searchWord);
		
	}
	
	public int countNumberOfRows() {
		WebElement tableRow = driver.findElement(table);
		List<WebElement> images = tableRow.findElements(By.tagName("img"));
		return images.size();
	}
	
	public void clickOnBookStoreFromProfile() {
		driver.findElement(bookStoreButton).click();
	}	

	public String getBookTitle(int index) {
	    return driver.findElements(bookTitle).get(index).getText();
	}

	public String getBookAuthor(int index) {
	    return driver.findElements(bookAuthor).get(index).getText();
	}
	

}