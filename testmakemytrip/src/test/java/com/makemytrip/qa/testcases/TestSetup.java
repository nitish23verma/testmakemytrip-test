package com.makemytrip.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.makemytrip.qa.base.TestBase;

public class TestSetup extends TestBase {

	@BeforeTest
	public void setUp() {
		readPropertyFile();
		driverInitialization();
		setExtentReport();
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
		driver.quit();
	}

}
