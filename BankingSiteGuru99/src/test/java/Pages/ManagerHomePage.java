package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import TestCases.TCPrePostConditions;
import Utilities.LogsUtils;
import Utilities.Util;

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
	
	
	public void ValidateUserInCorrectURL() 
	{
		String Current_URL = TCPrePostConditions.driver.getCurrentUrl();
		
		if (Current_URL.equals(Util.MANAGER_URL))
			{
				LogsUtils.info("User is in the manager webpage");
				TCPrePostConditions.extentTest.pass("User is in the manager webpage ");

			}
		else
		{
			LogsUtils.error("User is not in the manager webpage");
			TCPrePostConditions.extentTest.fail("User is not in the manager webpage ");
			Assert.assertTrue(false);
			
		}

			
	}
	/* ValidateManagerIdIsShown is a function which Validates that 
	 * Manager ID is shown properly after the login page and prints
	 * a message which indicates if it's appeared correctly or not  
	 * */
	public void ValidateManagerIdIsShown() 
	{
			
			/* get the shown text of the Manager ID Word */
		WebElement textLocator = driver.findElement(managerIdLocator);
		String shownText  = textLocator.getText();
		
		if (shownText.contains(userID))
		{
			TCPrePostConditions.extentTest.pass("Manager Id Is Shown Properly");
			LogsUtils.info("Manager Id Is Shown Properly");

		}
		
		else
		{
			TCPrePostConditions.extentTest.fail("Manager ID is NOT correct ");
			LogsUtils.error("Manager ID is NOT correct");
			softAssert.assertTrue(false, "Manager ID is NOT correct");
			softAssert.assertAll();
		}	
	}
}
