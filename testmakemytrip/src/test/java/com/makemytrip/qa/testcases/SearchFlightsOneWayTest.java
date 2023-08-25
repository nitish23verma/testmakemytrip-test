package com.makemytrip.qa.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.makemytrip.qa.base.TestBase;
import com.makemytrip.qa.pages.FlightInfoPage;
import com.makemytrip.qa.pages.HomePage;
import com.makemytrip.qa.util.utilFunctions;

public class SearchFlightsOneWayTest extends TestBase {
	@Test
	public void searchForOneWayFlight() {

		test = extent.createTest("Validate Cheapest Price of One way flight From: " + prop.getProperty("From")
				+ ", To: " + prop.getProperty("To"));
		test.assignCategory("Sanity Test");

		HomePage home = new HomePage();

		utilFunctions.clickonScreen(0, 0);
		test.log(Status.INFO, "Clicked on Home Screen to remove popup.");

		home.selectOnewayMenu();
		test.log(Status.INFO, "Selected Oneway trip");

		home.enterDepartureCity(prop.getProperty("From"));
		test.log(Status.INFO, "Entered the departure city");

		home.enterReturnCity(prop.getProperty("To"));
		test.log(Status.INFO, "Entered return city");

		home.enterDepartureDate(prop.getProperty("DepartureDate"));
		test.log(Status.INFO, "Entered Departure date");

		FlightInfoPage fligtinfopage = home.search();
		test.log(Status.INFO, "Clicked on the search button");

		fligtinfopage.clickCheapest();
		test.log(Status.INFO, "Clicked on the Cheapest button");

		double cheapestprice = fligtinfopage.getCheapestPrice();
		test.log(Status.INFO, "Cheapest price is : " + cheapestprice);

		fligtinfopage.clickViewDetails();
		test.log(Status.INFO, "Clicked on View Details button.");

		String flightdetails = fligtinfopage.onewayFlightDetails();
		test.log(Status.INFO, "One way Flight Details are : " + flightdetails);

	}

	@AfterMethod
	public void checkResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.FAIL, result.getName() + " Test Failed.");

			String screenshotPath = utilFunctions.getScreenshot(result.getName());
			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			test.addScreenCaptureFromPath(screenshotPath);

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip(MarkupHelper.createLabel(result.getName() + " Test case Skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel("Test Pass", ExtentColor.GREEN));
			test.pass(MarkupHelper.createLabel("Test Pass", ExtentColor.GREEN));
		}
	}

}
