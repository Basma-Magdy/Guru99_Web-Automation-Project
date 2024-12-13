package TestCases;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Utilities.DataDrivenProvider;
import Utilities.Util;

public class LoginTests {

	
	@BeforeMethod (groups = {"Regression"})
	public static void LoginTestsSetup()
	
	{
		/* Navigate to the login page */
		TCPrePostConditions.driver.get(Util.BASE_URL);		
	}

	/* A test cases which read inputs from excel file and check the valid scenario of the login functionality */
	
	@Test(dataProvider = "ValidLoginData" , dataProviderClass = DataDrivenProvider.class, groups = {"Regression", "ValidLogin"})
	public static void TC01_ValidScenarioLoginVerification (String userID, String Password)
	{		
				
			TCPrePostConditions.extentTest.info("Valid Scenario Login Verification Started");
		
			/* an object of the login page class*/
			LoginPage loginObj  = new LoginPage(TCPrePostConditions.driver);	
		
			/* set the login data */
			loginObj.LoginUserID(userID);
			loginObj.LoginUserPassword(Password);
						
			TCPrePostConditions.extentTest.pass("Valid Login Data are entered");
		
			/*press the login button */
			loginObj.ClickLoginButton();
			
			TCPrePostConditions.extentTest.pass("Login Button is clicked");
				
			/* login Validation*/
			loginObj.ValidLoginValidation();
	
	}	
	
	/* A test cases which read inputs from excel file and check the invalid scenario of the login functionality */
	
	@Test(dataProvider = "InValidLoginData" , dataProviderClass = DataDrivenProvider.class, groups = {"Regression", "InvalidLogin"})
	public static void TC02_InValidScenariosLoginVerification (String userID, String Password)
	{		
		
		TCPrePostConditions.extentTest.info("Invalid Scenario Login Verification Started");
			
			/* an object of the login page class*/
			LoginPage loginObj  = new LoginPage(TCPrePostConditions.driver);	
		
			/* set the login data */
			loginObj.LoginUserID(userID);
			loginObj.LoginUserPassword(Password);
			
			TCPrePostConditions.extentTest.pass("Invalid Login Data are entered");
			
			/*press the login button */
			loginObj.ClickLoginButton();
			
			TCPrePostConditions.extentTest.pass("Login Button is clicked");			

			/* login Validation*/
			loginObj.InvalidLoginValidation();	
									
	}	

}