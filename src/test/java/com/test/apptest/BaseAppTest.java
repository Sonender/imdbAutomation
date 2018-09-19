package com.test.apptest;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.imdbautomation.utils.ReadConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseAppTest {

	public static Logger logger;
	ReadConfig readConfig = new ReadConfig();
	public static RemoteWebDriver driver;
	DesiredCapabilities desiredCaps = new DesiredCapabilities();
	String automation_Name = readConfig.getAutomation_Name();
	String appium_Version = readConfig.getAppium_Version();
	String platform_Name = readConfig.getPlatform_Name();
	String app_Activity = readConfig.getApp_Activitye();

	@Parameters({ "deviceType" })
	@BeforeClass
	public RemoteWebDriver initializeDriver(String deviceType) throws MalformedURLException {
		if (deviceType.equalsIgnoreCase("Android"))
			driver = this.init();
		return driver;

	}

	@SuppressWarnings("unchecked")
	private AppiumDriver<MobileElement> init() throws MalformedURLException {
		desiredCaps.setCapability(MobileCapabilityType.AUTOMATION_NAME, automation_Name);
		desiredCaps.setCapability(MobileCapabilityType.APPIUM_VERSION, appium_Version);
		desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform_Name);
		desiredCaps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, app_Activity);
		desiredCaps.setCapability(AndroidMobileCapabilityType.SUPPORTS_ALERTS, true);
		driver = new AndroidDriver<MobileElement>(desiredCaps);
		logger = Logger.getLogger("imdbAPITesting"); // Adding logger;
		PropertyConfigurator.configure("log4j.properties");
		return (AppiumDriver<MobileElement>) driver;
	}
	
	
    @AfterClass
	public void tearDown() {
    	driver.quit();

	}
}
