package Configuration;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigClass {
	
	 /* properties class instance */
	static Properties prop = new Properties();
	
	/* get the path of the current project*/
	static String projectPath = System.getProperty("user.dir");
	
	
	public static String getProperties(String propName) 
	{
		String propValue = null;
		
			try 
			{
			
			/*get the properties file called config.properties*/
			InputStream inputConfigFile = new FileInputStream(projectPath +"/src/test/java/Configuration/config.properties");
			
			/* load the properties file*/
			prop.load(inputConfigFile);
			
			/* get values from properties file*/
			propValue = prop.getProperty(propName);
			
			}
			
			catch(Exception exp)
			{	
				System.out.println("Message is :"+ exp.getMessage());
				System.out.println("Reason is :"+ exp.getCause());
			}
			
			return  propValue;		
	}
	
		
		public static void setProperties(String propName, String propValue) {
			
			try {
			/*Set the properties file called config.properties*/
			OutputStream outputConfigFile = new FileOutputStream(projectPath +"/src/test/java/Configuration/config.properties");
			
			/* set the config with its value */
			prop.setProperty(propName, propValue);
			
			/*store this data in the output file specified */
			prop.store(outputConfigFile, null);
			
			}catch(Exception exp)
			{	
				System.out.println("Message is :"+ exp.getMessage());
				System.out.println("Reason is :"+ exp.getCause());
			}
		
		}
}
