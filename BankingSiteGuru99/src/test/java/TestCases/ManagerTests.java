package TestCases;

import org.testng.annotations.Test;

import Utilities.DataDrivenProvider;
import Pages.LoginPage;
import Pages.ManagerHomePage;
import Utilities.Util;

public class ManagerTests {

	
	@Test(priority = 1 ,groups = {"ManagerValidLogin"},
			dataProvider = "ManagerLoginData" , dataProviderClass = DataDrivenProvider.class)
	
	public static void TC03_LoginWithManagerID(String userID,String Password)
	{
		/* Navigate to the login page */
		TCPrePostConditions.driver.get(Util.BASE_URL);
		
		TCPrePostConditions.extentTest.info("Valid Scenario With Manager ID Login Verification Test Started");
	
		/* an object of the login and manager pages*/
		LoginPage loginObj  = new LoginPage(TCPrePostConditions.driver);
		ManagerHomePage managerObj = new ManagerHomePage(TCPrePostConditions.driver, userID);
	
		/* set the login data */
		loginObj.LoginUserID(userID);
		loginObj.LoginUserPassword(Password);
					
		TCPrePostConditions.extentTest.pass("Valid Login Data are entered");
	
		/*press the login button */
		loginObj.ClickLoginButton();
		
		TCPrePostConditions.extentTest.pass("Login Button is clicked");
	
		/* Validate user is in the correct url of manager page */
		managerObj.ValidateUserInCorrectURL();
		
	}
	
	
	/* ManagerUserIdPresesnce A Test which check if the Manager specific ID is
	 *  appeared properly in the Manager home page or not */
	
	@Test(priority = 2 ,dependsOnGroups = {"ManagerValidLogin"},
			dataProvider = "ManagerLoginData" , dataProviderClass = DataDrivenProvider.class)
	public static void TC04_ManagerUserIdPresesnce(String userID, String password) 
	{
		TCPrePostConditions.extentTest.info("Test Started");

		/* An Instance of the manager Home Page with driver after login */
		ManagerHomePage managerObj = new ManagerHomePage(TCPrePostConditions.driver, userID);
		
		/* check the manager id is shown on the manager page */
		managerObj.ValidateManagerIdIsShown();
		
	}

}