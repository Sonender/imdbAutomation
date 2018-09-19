package com.test.apitest;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class BaseApiTest {
	

	
	public static Logger logger; // Adding logger;
	public void apiTest()
	{
	
	 logger= Logger.getLogger("imdbAPITesting"); // Adding logger;
	  PropertyConfigurator.configure("log4j.properties");

	}
 
}
