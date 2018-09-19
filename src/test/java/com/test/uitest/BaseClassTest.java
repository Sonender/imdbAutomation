package com.test.uitest;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.imdbautomation.utils.ReadConfig;

public class BaseClassTest {
	
	/*
	 * The purpose of the class is to serve as a staring point for
	 * automation, it will take the parameters from testng.xml
	 * that on which browser should it run upon(here I have only selected single
	 * browser for demo purpose), and the BeforClass annotation ensures that it 
	 * will run prior to each test cases in the test class, and we will quit the
	 * browser later on.
	 */
	
	public static Logger logger;
	public static WebDriver driver;
	ReadConfig readConfig = new ReadConfig();
	public String url= readConfig.getUrl();
	public String chromepath= readConfig.getChromeProperties();

	
	@Parameters({"browserType"})
	@BeforeClass
	public WebDriver initializeBrowser(){//@Optional("chrome")String browser) {
		System.out.println(chromepath);
		System.setProperty("webdriver.chrome.driver", chromepath);
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		driver = new ChromeDriver(caps);
		driver.manage().window().maximize();
		driver.get(url);
	    logger= Logger.getLogger("imdbTesting"); // Adding logger;
	    PropertyConfigurator.configure("log4j.properties");
	return driver;
	}
	
	@AfterClass
	 public void tearDown()
	 {
		driver.quit();
	 }
	
	public void captureScree(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot screenShot= (TakesScreenshot) driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+ ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screentshot taken");
	}
	
	

}
