package com.makemytrip.qa.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.makemytrip.qa.base.TestBase;
import com.makemytrip.qa.pages.HomePage;
import com.makemytrip.qa.util.utilFunctions;

public class SkipUsecaseTest extends TestBase {
	@Test
	public void SearchAndSkipTest() {

		test = extent.createTest("Validate Skipped Usecase and its Scrrenshot.");
		test.assignCategory("Sanity Test");

		HomePage home = new HomePage();

		home.selectFlightsMenu();
		test.log(Status.INFO, "Clicked on Flight menu");

		home.selectRoundtripMenu();
		test.log(Status.INFO, "Selected round trip");

	}

	@AfterMethod
	public void checkResult(ITestResult result) throws Exception {
		test.skip(MarkupHelper.createLabel(result.getName() + " Case Skipped", ExtentColor.YELLOW));
		String screenshotPath = utilFunctions.getScreenshot(result.getName());
		test.skip(result.getName() + " skipped....!!!!",
				MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		test.addScreenCaptureFromPath(screenshotPath);

	}

}
