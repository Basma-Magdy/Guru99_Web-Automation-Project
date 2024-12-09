package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import TestCases.TestCasesPrePostConditions;
import TestCases.LoginTests;

public class Util {
	
	/* Setting the wait time of searching for an element */
	
	public static final int WAIT_TIME = 10 ;
	public static final int SCRIPT_WAIT_TIME =  1;

	/* Setting the base url, userid and user password */
	public static final String BASE_URL = "https://www.demo.guru99.com/V4/";
	//public static final String USER_ID = "mngr602413";
	//public static final String USER_PASS = "hAnYsAh";
	public static final String EXPECTED_ERROR_MESSAGE = "User or Password is not valid";
	

	
	/* get the time stamp method */
    public static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());
    }
	
    
	/* Taking screenshot of the whole screen */
	public static void TakeScreenShot(String screenShotName)
	{
			try
			{
			String projectPath = System.getProperty("user.dir");
			String screenShotPath = projectPath + "/test-output/Screenshots/" + screenShotName + "-" + getTimeStamp() +".png";
			
			File srcfile =((TakesScreenshot)LoginTests.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcfile, new File(screenShotPath));
			}
			catch (IOException e)
			{
	            LogsUtils.error(e.getMessage());
			}
		}
		
}
