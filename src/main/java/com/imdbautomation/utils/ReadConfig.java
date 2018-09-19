

package com.imdbautomation.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	/*purpose of this class is to read the common project properties from 
	 * properties file we will create an object of this class and use it in 
	 * baseclass for the respective projects
	 *
	 */

	Properties properties;

	public ReadConfig() {
		File source = new File("./Configuration/configuration.properties");
		try {
			FileInputStream file = new FileInputStream(source);
			properties = new Properties();
			properties.load(file);

		} catch (IOException e) {
			System.out.println("inputOut Exception has occured");

		}
	}

	public String getUrl()
	{
		String url = properties.getProperty("basUrl");
		return url;

	}

	public String getChromeProperties()
	{

		String chromePath = properties.getProperty("chromepath");
		return chromePath;

	}


	public String getAppium_Version()
	{
		String appium_version = properties.getProperty("appium_version");
		return appium_version;
	}

	public String getAutomation_Name()
	{
		String automation_name = properties.getProperty("automation_name");
		return automation_name;
	}

	public String getPlatform_Name()
	{
		String platform_Name = properties.getProperty("platform_Name");
		return platform_Name;
	}


	public String getApp_Activitye()
	{
		String app_Activity = properties.getProperty("app_Activity");
		return app_Activity;
	}


}
