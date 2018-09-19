package com.test.apptest;

import org.testng.annotations.Test;

public class Sampletest extends BaseAppTest{

	

	@Test
	public void appTest1()
	{
		System.out.println("test Case 1");
		logger.info("log the information pertaining to the test");
 
	}
	
	public void apptest2()
	{
		
		System.out.println("test Case w");
		logger.info("log the information pertaining to the test");
 
		

	}

}

