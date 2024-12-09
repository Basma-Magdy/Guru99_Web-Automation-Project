package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestCases.LoginTests;
import Utilities.LogsUtils;
import Utilities.Util;

public class ITestListenerClass implements ITestListener{
	
		
	public void onTestStart(ITestResult result)
	{
        LogsUtils.info(" Test Case "+ result.getName() + " started");
	}
	
	
	public void onTestSuccess(ITestResult result)
	{
		Util.TakeScreenShot("Login_Screenshot");
		LogsUtils.info(" Test Case "+ result.getName() + " Passed ");
		LoginTests.loginTest1.pass("Test is Passed");

	}
	
	
	public void onTestFailure(ITestResult result)
	{
		Util.TakeScreenShot("Failure_Screenshot");
		LogsUtils.info(" Test Case "+ result.getName() + " Failed ");
		LoginTests.loginTest1.fail("Test is failed ");
	}		

	
	public void onTestSkipped(ITestResult result)
	{
        LogsUtils.info(" Test Case "+ result.getName() + " Skipped ");
		LoginTests.loginTest1.info("Test " + result.getName() + " is Skipped");

	}
	
	
	public void onFinish(ITestContext context)
	{
		LoginTests.loginTest1.info("Test " + context.getName() + " is Completed");
	}
	
}