package listener;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Pages.LoginPage;
import TestCases.LoginTests;
import Utilities.LogsUtils;
import Utilities.Util;

public class ITestListenerClass implements ITestListener{
	
		
	public void onTestStart(ITestResult result)
	{
		//System.out.println("******* Test Started :" + result.getName());
        LogsUtils.info(" Test Case "+ result.getName() + " started");

	}
	
	
	public void onTestSuccess(ITestResult result)
	{
		
		//System.out.println("******* Test is Successful :" + result.getName());
		LogsUtils.info(" Test Case "+ result.getName() + " Passed ");

	}
	
	
	public void onTestFailure(ITestResult result)
	{
		Util.TakeScreenShot("Failure_Shot");
		//System.out.println("******* Test failed :" + result.getName());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		//System.out.println("******* Test Skipped :" + result.getName());
        LogsUtils.info(" Test Case "+ result.getName() + " Skipped ");

	}
	
	
	public void onFinish(ITestContext context)
	{
		System.out.println("******* Test is Completed :" + context.getName());

	}
	
}