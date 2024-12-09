package TestCases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Configuration.ConfigClass;
import Pages.LoginPage;
import Utilities.DataDrivenProvider;
import Utilities.Util;

public class LoginTests {


	public static WebDriver driver = null;
	public static ExtentReports extentReport;
	static ExtentTest loginTest1 ;
	static Logger logger;

	
	@BeforeTest(groups = {"Regression", "Login"})
	public void SetUpTest() {
		
		String browser ;
		
		/* get the logs of the current class*/
		logger = LogManager.getLogger(LoginTests.class);
		
		/*  Generate Extent Report */
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("TestCasesReport.html");
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReport);
		
		/* get the browser name from the configuration file */
		browser = ConfigClass.getProperties("browser");

		/* Choose the webdriver to open if chrome or firefox, default one os edge */
		switch (browser)
		{
		case "chrome": 
			driver = new ChromeDriver();
		break;
		
		case "firefox":
			driver = new FirefoxDriver();
		break;
		
		default: 
			driver = new EdgeDriver();
		}
		
		/*The wait time for the elements loading. and for loading the page 
		 * and for the script timeout */
		 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(Util.SCRIPT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.WAIT_TIME));

		/* Maximize the window screen */
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public static void LoginTestsSetup()
	
	{
		loginTest1 = extentReport.createTest("Login Verification Test");
		
		loginTest1.info("Test Started");
		
		/* Navigate to the login page */
		driver.get(Util.BASE_URL);
		
		loginTest1.pass("Navigation to the website URL");

	}

	@Test(dataProvider = "ValidLoginData" , dataProviderClass = DataDrivenProvider.class, groups = {"Regression", "Login"})
	public static void TC01_ValidScenarioLoginVerification (String userID, String Password)
	{		
		
			/* an object of the login page class*/
			LoginPage loginObj  = new LoginPage(driver);	
		
			/* set the login data */
			loginObj.LoginUserID(userID);
			loginObj.LoginUserPassword(Password);
						
			loginTest1.pass("UserID and password are Entered");
		
			/*press the login button */
			loginObj.ClickLoginButton();
			
			loginTest1.pass("Login Button is clicked");
				
			/* login Validation*/
			loginObj.ValidLoginValidation();
			
			Util.TakeScreenShot("Valid_Login_Shot");
			
			loginTest1.pass("Login test with valid data is successful ");

	}	
	
		
	@Test(dataProvider = "InValidLoginData" , dataProviderClass = DataDrivenProvider.class, groups = {"Regression", "Login"})
	public static void TC02_InValidScenariosLoginVerification (String userID, String Password) throws Exception
	{		
		
			/* an object of the login page class*/
			LoginPage loginObj  = new LoginPage(driver);	
		
			/* set the login data */
			loginObj.LoginUserID(userID);
			loginObj.LoginUserPassword(Password);
			
			loginTest1.pass("UserID and password are Entered");
			
			/*press the login button */
			loginObj.ClickLoginButton();
			
			loginTest1.pass("Login Button is clicked");			

			/* login Validation*/
			loginObj.InvalidLoginValidation();	
						
			Util.TakeScreenShot("Invalid_Login_Shot");

			loginTest1.pass("Login test with invalid data is successful ");
			

	}	
	
	@AfterClass
	public void Teardown() 
	{
		/* Close the browser */	
		//driver.close();
		driver.quit();
		loginTest1.info("Login Test is Completed");
		
		extentReport.flush();
		
	}

}