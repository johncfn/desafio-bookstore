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
import pages.Profile;
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
		logger.info("Iniciando navegador e acessando página de registro");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		extent.flush(); 
	    logger.info("Encerrando o navegador");
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
		test = extent.createTest("Registrar novo usuário com sucesso");
		logger.info("Preenchendo dados de registro");
		test.log(Status.INFO, "Preenchendo dados de registro");
		
		registerBookStorePage.registerNewUser("João", "Zinho", "Joaozinho123", "SenhaDoJoao123!");
		Thread.sleep(10000);
		logger.info("Enviando formulário de registro");
		test.log(Status.INFO, "Clicando em registrar");
		registerBookStorePage.submitRegistration();
		assertEquals(registerBookStorePage.getUserCreatedAlertMessage(), "User Register Successfully.");
		test.pass("Usuário registrado com sucesso!");
		logger.info("Teste de registro concluído com sucesso.");
	}
	
	@Test
	public void shouldDisplayUsernameOnProfileAfterLogin() throws InterruptedException {
		String username = TestData.USERNAME;
		String password = TestData.PASSWORD;	
		test = extent.createTest("Valida Login e Username no Perfil");
		
		logger.info("Iniciando teste de login com usuário: {}", username);
	    test.log(Status.INFO, "Iniciando login com usuário: " + username);
		
		
		registerBookStorePage.loginNewRegisteredUser();
		logger.info("Redirecionado para página de login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(username, password);
		
		Profile profile = new Profile(driver);
		String userLogin = profile.checkUsernameLogin();
		
		assertEquals(username, userLogin);
		ScreenshotUtil.takeScreenshot(driver, "ProfileScreen_" + username);
		test.log(Status.PASS, "Username exibido corretamente após login");
	}
}
