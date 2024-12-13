package Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Configuration.ConfigClass;
import TestCases.TCPrePostConditions;
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

	
	/* ValidLoginValidation is a function validates if the login has been succeeded or not with valid data */ 
	public void ValidLoginValidation()
	{
		boolean elementExist = driver.findElement(loginValidate).isDisplayed();
			if(elementExist)
			{
				ConfigClass.setProperties("LoginTestResult", "Pass");
				LogsUtils.info(" Login is done successfully ");
				TCPrePostConditions.extentTest.pass("Login is done successfully");
				
			}
			
			else
			{
				ConfigClass.setProperties("LoginTestResult", "Fail");
				LogsUtils.fatal(" Login is Failed with Valid Data " );
				Assert.assertTrue(elementExist);
			}
	}
				
	
	/* InvalidLoginValidation is a function validates if the login has been succeeded or not with invalid data */ 
	public void InvalidLoginValidation()
	{	
		try
		{
			/*  wait until the alert is present */ 
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.until(ExpectedConditions.alertIsPresent());
			
			/* Switch to the alert and get the alert message */
			 Alert errorAlert = driver.switchTo().alert();	
			 String actualErrorMessage = errorAlert.getText();
			 
			 
			 System.out.println("actualErrorMessage is :" + actualErrorMessage);
			 System.out.println("EXPECTED_ERROR_MESSAGE is :" + Util.EXPECTED_ERROR_MESSAGE);
	
			 errorAlert.accept();
				
			 if (!(actualErrorMessage.equals(Util.EXPECTED_ERROR_MESSAGE)))
			 	{
					TCPrePostConditions.extentTest.fail(" Invalid login alert data, Actual message is not the same as expected ");
					LogsUtils.error(" Invalid login alert data, Actual message is not the same as expected ");
				}
			 
			Assert.assertEquals(actualErrorMessage, Util.EXPECTED_ERROR_MESSAGE);
			TCPrePostConditions.extentTest.pass(" Invalid login alert data is present with correct message"); 
		}		

		
		catch(Exception exp)
		
			{
		
				LogsUtils.debug("Error alert issue in the invalid login, the issue is : " + exp.getMessage());
	
			}
	}

}