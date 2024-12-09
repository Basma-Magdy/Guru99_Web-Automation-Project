package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import Configuration.ConfigClass;
import TestCases.ManagerTests;

import org.testng.SkipException;

public class ManagerHomePage {

	WebDriver driver = null;
	String userID = null;

	SoftAssert softAssert = new SoftAssert();
	
	/* Displayed Manager ID Text Locator */
	By managerIdLocator = By.xpath("//td[contains(text(),'Manger Id')]");

	/* a constructor to intialize the login page elements */ 
	public ManagerHomePage(WebDriver driver, String userID)
	{
		this.driver = driver ;
		this.userID = userID;
	}	
	
	
	/* ValidateUserIsManager is a function used to check if the login username is for a manager then proceed,
	 * if for someone else then skip the manager test cases 
	 */
	
	public void ValidateUserIsManager()
	{
		
		/* Skip test if the userID is not for Manager */
		if (!(userID.contains("mngr")))
		{
			ConfigClass.setProperties("ManagerTestResult", "Skipped");
			ManagerTests.extentTest2.skip("Test is Skipped, The user is not a manager ");
			
			throw new SkipException("=== the User is not a manager, Test is skipped ===");

		}
      
	}
      
	/* ValidateManagerIdIsShown is a function which Validates that 
	 * Manager ID is shown properly after the login page and prints
	 * a message which indicates if it's appeared correctly or not  
	 * */
	public void ValidateManagerIdIsShown() 
	{
		try 
		{
			
			/* get the shown text of the Manager ID Word */
		WebElement textLocator = driver.findElement(managerIdLocator);
		String shownText  = textLocator.getText();
		
		if (shownText.contains(userID))
			System.out.println(" == Manager ID is shown correctly == ");
			ConfigClass.setProperties("ManagerTestResult", "Pass");
			ManagerTests.extentTest2.pass("Validate Manager Id Is Shown Correctly");

		}
		
		catch(Exception exp)
		{
			ConfigClass.setProperties("ManagerTestResult", "Fail");
			ManagerTests.extentTest2.fail("Manager ID is NOT shown correctly ");
			softAssert.assertTrue(false ," == Manager ID is NOT correct == ");
		}	
	}
}
