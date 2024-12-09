package TestCases;

import org.openqa.selenium.WebDriver;

public class TestCasesPrePostConditions {
	
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    

}
