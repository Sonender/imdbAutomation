package com.test.apitest;

import org.testng.annotations.Test;

public class SampleTest extends BaseApiTest{  // extended the BaseAPItest to get the features of the BaseClass in this child Class


	@Test
	public void apiTest1()
	{
		System.out.println("test Case 1");
		logger.info("log the information pertaining to the test");
 
	}
	
	public void apitest2()
	{
		
		System.out.println("test Case 2");
		logger.info("log the information pertaining to the test");
 
		

	}
	
}
