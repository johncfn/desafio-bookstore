package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.ProfilePage;
import pages.RegisterBookStorePage;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import utils.TestData;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterBookStorePageTest {
	
	static WebDriver driver;
	static RegisterBookStorePage registerBookStorePage;
	static ExtentReports extent;
	static ExtentTest test;

	private static final Logger logger = LogManager.getLogger(RegisterBookStorePageTest.class);


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Users/johnc/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/register");
		extent = ExtentManager.getInstance();
		registerBookStorePage = new RegisterBookStorePage(driver);
		logger.info("Starting browser and going to register page");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		extent.flush(); 
	    logger.info("Closing browser");
		driver.quit();
	}
	
	/*
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	*/
	
	@Test
	public void shouldRegisterNewUserAndDisplaySuccessAlert() throws InterruptedException {
		test = extent.createTest("New user register");
		logger.info("Filling fields of register page");
		test.log(Status.INFO, "Filling fields of register page");
		
		registerBookStorePage.registerNewUser(TestData.FIRST_NAME,TestData.LAST_NAME, TestData.USERNAME, TestData.PASSWORD);
		logger.info("Waiting the captcha to be solved manually");
		Thread.sleep(10000);
		logger.info("Sending register");
		test.log(Status.INFO, "Clicking on Register Button");
		registerBookStorePage.submitRegistration();
		assertEquals(registerBookStorePage.getUserCreatedAlertMessage(), "User Register Successfully.");
		test.pass("New User Registered Correcly");
		logger.info("Register test completed with success");
	}
	
	@Test
	public void shouldDisplayUsernameOnProfileAfterLogin() throws InterruptedException {
		String username = TestData.USERNAME;
		String password = TestData.PASSWORD;	
		test = extent.createTest("Check userlogin and Username on profile");
		
		logger.info("Starting login test with user: {}", username);
	    test.log(Status.INFO, "Login with user: " + username);
		
		
		registerBookStorePage.loginNewRegisteredUser();
		logger.info("Going to login page");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(username, password);
		
		ProfilePage profile = new ProfilePage(driver);
		String userLogin = profile.checkUsernameLogin();
		
		assertEquals(username, userLogin);
		ScreenshotUtil.takeScreenshot(driver, "ProfileScreen_" + username);
		test.log(Status.PASS, "Username displayed correcly after login");
	}
}
