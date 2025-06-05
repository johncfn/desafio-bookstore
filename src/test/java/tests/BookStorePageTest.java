package tests;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.TestData;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pages.BookStorePage;
import pages.LoginPage;
import pages.Profile;

public class BookStorePageTest {
	
	static WebDriver driver;
	static BookStorePage bookStorePage;
	static LoginPage loginPage;
	static Profile profile;
	static ExtentReports extent;
	static ExtentTest test;
	
	private static final Logger logger = LogManager.getLogger(BookStorePageTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Users/johnc/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/login");
		extent = ExtentManager.getInstance();
		loginPage = new LoginPage(driver);
		bookStorePage = new BookStorePage(driver);
		profile = new Profile(driver);
		logger.info("Iniciando navegador e acessando página de login");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		extent.flush(); 
	    logger.info("Encerrando o navegador");
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldSearchABookOnSearchFieldAndVerifyTheResult() throws InterruptedException {
		test = extent.createTest("Fazer Pesquisa após login");
		logger.info("Fazer Login com usuário");
		test.log(Status.INFO, "Fazer Login com usuário");
		loginPage.loginUser(TestData.USERNAME, TestData.PASSWORD);
		String userLogin = profile.checkUsernameLogin();
		assertEquals(TestData.USERNAME, userLogin);
		
		
		logger.info("Indo para página de pesquisa de Livros");
		test.log(Status.INFO, "Indo para página de pesquisa de Livros");
		bookStorePage.clickOnBookStoreFromProfile();
		
		logger.info("Pesquisando livro");
		test.log(Status.INFO, "Pesquisando livro");
		bookStorePage.searchABookOnSearchField("javascript");	
		
		logger.info("Contando numero de livros retornados");
		int rows = bookStorePage.countNumberOfRows();
		logger.info("Numero de livros: " + bookStorePage.countNumberOfRows());		
		
		String title = bookStorePage.getBookTitle(0);
        String author = bookStorePage.getBookAuthor(0);       
        
        String mensagem = String.format(
                "Livro %s do autor %s, foi encontrado entre os %d resultados da pesquisa.",
                title, author, rows
        );
        
        logger.info(mensagem);
        test.log(Status.PASS, "Numero de livros listados e apresentado");
        ScreenshotUtil.takeScreenshot(driver, "{JavaScript}_SearchResults");
       		
	}

}
