package com.makemytrip.qa.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.makemytrip.qa.base.TestBase;
import com.makemytrip.qa.pages.FlightInfoPage;
import com.makemytrip.qa.pages.HomePage;
import com.makemytrip.qa.util.utilFunctions;

public class FailUsecaseTest extends TestBase {
	@Test
	public void SearchAndFailTest() {

		test = extent.createTest("Validate Failed Usecase and its Scrrenshot. From: " + prop.getProperty("rFrom")
				+ ", To: " + prop.getProperty("To"));
		test.assignCategory("Sanity Test");

		HomePage home = new HomePage();
		FlightInfoPage fligtinfopage = new FlightInfoPage();

		home.selectFlightsMenu();
		test.log(Status.INFO, "Clicked on Flight menu");

		home.selectRoundtripMenu();
		test.log(Status.INFO, "Selected round trip");

		// Purposefully Failing the test case.
		fligtinfopage.clickCheapest();
		test.log(Status.INFO, "Clicked on the Cheapest button");

		home.enterDepartureCity(prop.getProperty("rFrom"));
		test.log(Status.INFO, "Entered the departure city");

		home.enterReturnCity(prop.getProperty("rTo"));
		test.log(Status.INFO, "Entered return city");

	}

	@AfterMethod
	public void checkResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName() + " Test Failed.");
			String screenshotPath = utilFunctions.getScreenshot(result.getName());
			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			test.addScreenCaptureFromPath(screenshotPath);
		}
	}

}
