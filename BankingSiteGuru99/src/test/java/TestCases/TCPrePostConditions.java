package TestCases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Configuration.ConfigClass;
import Utilities.Util;

public class TCPrePostConditions {
	
	 public static Logger logger;
	 public static ExtentReports extentReport;
	 public static ExtentTest extentTest ;
	 public static WebDriver driver = null;


	@BeforeTest(groups = {"Regression", "Login"})
	public void SetUpTest() {
		
		String browser ;
		
		/* get the logs of the current class*/
		logger = LogManager.getLogger();
		
		/*  Generate Extent Report */
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("TestCasesReport.html");
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReport);
		
		/* get the browser name from the configuration file */
		browser = ConfigClass.getProperties("browser");

		/* Choose the webdriver to open if chrome or firefox, default one os edge */
		switch (browser)
		{
		case "chrome": 
				driver  = new ChromeDriver();
		break;
		
		case "firefox":
				driver = new FirefoxDriver();
		break;
		
		default: 
				driver  = new EdgeDriver();
		}
		
		/*The wait time for the elements loading. and for loading the page 
		 * and for the script timeout */
		 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(Util.SCRIPT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.WAIT_TIME));

		/* Maximize the window screen */
		driver.manage().window().maximize();
		
	}
	
	
	@AfterTest
	public void Teardown() 
	{
		/* Close the browser, End the Report */	
		driver.quit();
		extentReport.flush();
	}
	

    

}
