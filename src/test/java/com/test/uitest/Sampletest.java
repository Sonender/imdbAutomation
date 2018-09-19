package com.test.uitest;

import org.junit.Assert;
import org.testng.annotations.Test;

public class Sampletest extends BaseClassTest { 

	  /* Extended the baseclass so that the 
	   * all the test cases get the required input 
	   * data fro the base class/;
	   * 
	   */

	@Test
	public void homePageTest() {
	logger.info("URL Opened "); // Added logging to the test cases
    
	if (driver.getTitle().equalsIgnoreCase("IMDb - Moview, TV and Celebrities - iMDb")) {
		logger.info("test is passed");
		Assert.assertTrue(true);

	} else {
		logger.info("test is failed");
		Assert.assertFalse(false);

	}

	}

}
