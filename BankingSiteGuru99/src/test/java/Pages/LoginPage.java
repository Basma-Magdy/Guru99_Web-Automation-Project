package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Configuration.ConfigClass;
import TestCases.LoginTests;
import Utilities.LogsUtils;
import Utilities.Util;

public class LoginPage {

	WebDriver driver = null;

	/* the userID, password and Login Button elements */
	By userIdLocator = By.name("uid");
	By userPassLocator = By.name("password");
	By loginButton = By.xpath("//input[@value='LOGIN']");

	/* Balance Inquiry Word Element */
	By loginValidate = By.linkText("Balance Enquiry");

	
	/* a constructor to intialize the login page elements */ 
	public LoginPage(WebDriver driver)
	{
		this.driver = driver ;	
	}


	/* LoginUserID is a function to find the userID element and set the entered data */
	public void LoginUserID(String userID)
	{
		driver.findElement(userIdLocator).sendKeys(userID);
		
	}

	
	/* LoginUserPassword is a function to find the password element and set the entered data */
	public void LoginUserPassword(String userPassword)
	{
		driver.findElement(userPassLocator).sendKeys(userPassword);
	}

	
	/* ClickLoginButton is a function used to find the login button and click it */
	public void ClickLoginButton()
	{
		driver.findElement(loginButton).click();
	}

	
	/* LoginValidation is a function validates if the login has been succeeded or not */ 
	public void ValidLoginValidation()
	{
		boolean elementExist = driver.findElement(loginValidate).isDisplayed();
					if(elementExist)
					{
						ConfigClass.setProperties("LoginTestResult", "Pass");
						LogsUtils.info(" Login is done successfully ");
						LoginTests.loginTest1.pass("Login is done successfully");
						
					}
					
					else
					{
						ConfigClass.setProperties("LoginTestResult", "Fail");
						LogsUtils.fatal(" Login is Failed with Valid Data " );
						Assert.assertTrue(elementExist);
					}
	}
				
	
	/* LoginValidation is a function validates if the login has been succeeded or not */ 
	public void InvalidLoginValidation()
	{
		String actualErrorMessage;
			try
				{
					Alert errorAlert = driver.switchTo().alert();
	
					actualErrorMessage = errorAlert.getText();
						
					errorAlert.accept();
					
					if (!(new String(actualErrorMessage).equals(Util.EXPECTED_ERROR_MESSAGE)))
					{
						LoginTests.loginTest1.fail(" Invalid login alert data, Actual message is not the same as expected ");
						LogsUtils.error(" Invalid login alert data, Actual message is not the same as expected ");
					}
					Assert.assertEquals(actualErrorMessage, Util.EXPECTED_ERROR_MESSAGE);
					

				}
				
				catch(Exception exp)
				{
					
					LogsUtils.fatal(" Login with invalid data is failed ");	
					LoginTests.loginTest1.fail(" Login with invalid data is failed ");
					Assert.assertTrue(false);
				}
	}
}
	