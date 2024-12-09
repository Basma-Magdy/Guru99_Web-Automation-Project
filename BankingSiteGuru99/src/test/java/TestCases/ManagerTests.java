package TestCases;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Pages.ManagerHomePage;
import Utilities.DataDrivenProvider;

public class ManagerTests {

	public static ExtentTest extentTest2;

	/* ManagerUserIdPresesnce A Test which check if the Manager specific ID is
	 *  appeared properly in the Manager home page or not */
	
	@Test(dataProvider = "LoginData" , dataProviderClass = DataDrivenProvider.class ,dependsOnGroups = {"Login"})
	public static void TC03_ManagerUserIdPresesnce(String userID, String password) 
	{
		extentTest2 = LoginTests.extentReport.createTest("Manager UserId Presesnce Test");
		extentTest2.info("Test Started");

		/* An Instance of the manager Home Page with driver after login */
		ManagerHomePage managerObj = new ManagerHomePage(LoginTests.driver, userID);
		
		/* Check if user is manager or not */
		managerObj.ValidateUserIsManager();
		
		extentTest2.pass("User Is Manager");
	
		/* if user is manager, check the manager id is shown on the manager page */
		managerObj.ValidateManagerIdIsShown();
		
	}
	

	@AfterMethod
	public void Teardown() 
	{
	
		extentTest2.info("Manager Test is Completed");
		
		
	}
}