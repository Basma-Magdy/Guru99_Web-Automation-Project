package Pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Configuration.ConfigClass;
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
						LogsUtils.info(" -- Valid Login data test is passed --");
					}
					
					else
					{
						ConfigClass.setProperties("LoginTestResult", "Fail");
						LogsUtils.fatal(" ---Valid Login data test is FAILED --" );
						Assert.assertTrue(elementExist, "=== Login is failed ===");
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
					
					Assert.assertEquals(actualErrorMessage, Util.EXPECTED_ERROR_MESSAGE, "-- Invaild login data alert is not the same as expected---");

				}
				
				catch(Exception exp)
				{
					
					LogsUtils.fatal(" --- Login with invalid data is not correct ---");				
					ConfigClass.setProperties("LoginTestResult", "Fail");
					Assert.assertTrue(false);
				}
	}
}
	