package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import TestCases.TCPrePostConditions;
import Utilities.LogsUtils;
import Utilities.Util;

public class ITestListenerClass implements ITestListener{
	
		
	public void onTestStart(ITestResult result)
	{
		TCPrePostConditions.extentTest = TCPrePostConditions.extentReport.createTest(result.getName());
        LogsUtils.info("Test Case "+ result.getName() + " started");
	}
	
	
	public void onTestSuccess(ITestResult result) 
	{
		LogsUtils.info("Test Case "+ result.getName() + " Passed ");
		TCPrePostConditions.extentTest.pass("Test is Passed");

	}
	
	
	public void onTestFailure(ITestResult result)
	{
		Util.TakeScreenShot(result.getName() + " Failure_Screenshot");
		LogsUtils.info("Test Case "+ result.getName() + " Failed ");
		TCPrePostConditions.extentTest.fail("Test is failed ");
	}		

	
	public void onTestSkipped(ITestResult result)
	{
        LogsUtils.info("Test Case "+ result.getName() + " Skipped ");
		TCPrePostConditions.extentTest.skip("Test " + result.getName() + " is Skipped");

	}
	
	
}