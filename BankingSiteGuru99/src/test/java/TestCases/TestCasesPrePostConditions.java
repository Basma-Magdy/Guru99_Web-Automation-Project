package TestCases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Configuration.ConfigClass;
import Utilities.Util;

public class TestCasesPrePostConditions {
	
    
	@BeforeTest(groups = {"Regression", "Login"})
	public void SetUpTest() {
		
		String browser ;
		
		/* get the logs of the current class*/
		LoginTests.logger = LogManager.getLogger(LoginTests.class);
		
		/*  Generate Extent Report */
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("TestCasesReport.html");
		LoginTests.extentReport = new ExtentReports();
		LoginTests.extentReport.attachReporter(htmlReport);
		
		/* get the browser name from the configuration file */
		browser = ConfigClass.getProperties("browser");

		/* Choose the webdriver to open if chrome or firefox, default one os edge */
		switch (browser)
		{
		case "chrome": 
			LoginTests.driver  = new ChromeDriver();
		break;
		
		case "firefox":
			LoginTests.driver = new FirefoxDriver();
		break;
		
		default: 
			LoginTests.driver  = new EdgeDriver();
		}
		
		/*The wait time for the elements loading. and for loading the page 
		 * and for the script timeout */
		 
		LoginTests.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.WAIT_TIME));
		LoginTests.driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(Util.SCRIPT_WAIT_TIME));
		LoginTests.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.WAIT_TIME));

		/* Maximize the window screen */
		LoginTests.driver.manage().window().maximize();
		
	}
	
	
	@AfterTest
	public void Teardown() 
	{
		/* Close the browser */	
		LoginTests.driver.quit();
		LoginTests.extentReport.flush();
	}
	

    

}
