package com.imdbautomation.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMechanish {
	
    /* The class is responsible for retrying the test cases more than once
     * some time there are possibilities that the cases got failed due to 
     * Network related issues, and we need to re-run the cases more than once.
     * this is handled by the testng.xml file of respective project.
     */
	public class Retry implements IRetryAnalyzer {

	private int retryCount = 0;
	private int maxRetryCount = 1;

	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
		System.out.println("Retrying to execute test " + result.getName() + " with status " + getResultStatusName(result.getStatus())
			+ " for one more time.");
		retryCount++;
		return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
		resultName = "SUCCESS";
		if (status == 2)
		resultName = "FAILURE";
		if (status == 3)
		resultName = "SKIP";
		return resultName;
	}
	}
}