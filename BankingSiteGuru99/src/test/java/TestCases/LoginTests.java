package TestCases;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Pages.LoginPage;
import Utilities.DataDrivenProvider;
import Utilities.Util;

public class LoginTests {


	public static WebDriver driver = null;
	public static ExtentReports extentReport;
	public static ExtentTest loginTest1 ;
	static Logger logger;

	
	@BeforeMethod (groups = {"Regression", "Login"})
	public static void LoginTestsSetup()
	
	{
		/* Navigate to the login page */
		driver.get(Util.BASE_URL);		
	}

	/* A test cases which read inputs from excel file and check the valid scenario of the login functionality */
	
	@Test(dataProvider = "ValidLoginData" , dataProviderClass = DataDrivenProvider.class, groups = {"Regression", "Login"})
	public static void TC01_ValidScenarioLoginVerification (String userID, String Password)
	{		
		
			loginTest1 = extentReport.createTest(" TC01 - Valid Login Verification ");
		
			loginTest1.info("Valid Scenario Login Verification Started");
		
			/* an object of the login page class*/
			LoginPage loginObj  = new LoginPage(driver);	
		
			/* set the login data */
			loginObj.LoginUserID(userID);
			loginObj.LoginUserPassword(Password);
						
			loginTest1.pass("Valid Login Data are entered");
		
			/*press the login button */
			loginObj.ClickLoginButton();
			
			loginTest1.pass("Login Button is clicked");
				
			/* login Validation*/
			loginObj.ValidLoginValidation();
	
	}	
	
	/* A test cases which read inputs from excel file and check the invalid scenario of the login functionality */
	
	@Test(dataProvider = "InValidLoginData" , dataProviderClass = DataDrivenProvider.class, groups = {"Regression", "Login"})
	public static void TC02_InValidScenariosLoginVerification (String userID, String Password)
	{		
			loginTest1 = extentReport.createTest(" TC02 - Invalid Login Verification ");
		
			loginTest1.info("Invalid Scenario Login Verification Started");
			
			/* an object of the login page class*/
			LoginPage loginObj  = new LoginPage(driver);	
		
			/* set the login data */
			loginObj.LoginUserID(userID);
			loginObj.LoginUserPassword(Password);
			
			loginTest1.pass("Invalid Login Data are entered");
			
			/*press the login button */
			loginObj.ClickLoginButton();
			
			loginTest1.pass("Login Button is clicked");			

			/* login Validation*/
			loginObj.InvalidLoginValidation();	
									
	}	

}