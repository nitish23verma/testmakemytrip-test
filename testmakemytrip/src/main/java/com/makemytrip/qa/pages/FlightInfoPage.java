package com.makemytrip.qa.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.makemytrip.qa.base.TestBase;
import com.makemytrip.qa.util.utilFunctions;

public class FlightInfoPage extends TestBase {

	String cheapestprice = "";
	public double price;

	// Pagefactory
	@FindBy(xpath = "//input[@id='filter_stop0']/following::label//span[text()='Non Stop']")
	WebElement nonStopFilter;

	@FindBy(xpath = "//span[@class='checkbox-group list1 append_bottom5 fli-filter-items']//span[@class='labeltext'][contains(text(),'1 Stop')]")
	WebElement oneStopFilter;

	@FindBy(xpath = "//div[@id='ow_domrt-jrny']//div[@class='fli-list splitVw-listing']")
	List<WebElement> departureFlights;

	@FindBy(xpath = "//div[@id='rt-domrt-jrny']//div[@class='fli-list splitVw-listing']")
	List<WebElement> returnFlights;

	@FindBy(xpath = "//div[@id='applied-filter--wrapper']//a[contains(text(),'Clear')]")
	WebElement clearFilterLink;

	@FindBy(xpath = "//div[@id='ow_domrt-jrny']//input[@data-checked='true']/following::label[1]//p[@class='actual-price']/span")
	WebElement departurePriceBox;

	@FindBy(xpath = "//div[@id='rt-domrt-jrny']//input[@data-checked='true']/following::label[1]//p[@class='actual-price']/span")
	WebElement returnPriceBox;

	@FindBy(xpath = "//div[contains(@class,'splitVw-footer-left')]//p[contains(@class,'actual-price')]/span")
	WebElement footerDeparturePriceBox;

	@FindBy(xpath = "//div[contains(@class,'splitVw-footer-right ')]//p[contains(@class,'actual-price')]/span")
	WebElement footerReturnPriceBox;

	@FindBy(xpath = "//div[contains(@class,'splitVw-footer-total')]//p/span[contains(@class,'splitVw-total-fare')]")
	WebElement footerTotalPriceBox;

	@FindBy(xpath = "(//p[@class='boldFont blackText airlineName'])[1]")
	WebElement airlineName;

	@FindBy(xpath = "(//div[@class='priceSection']//p/..)[1]")
	WebElement priceSection;

	@FindBy(xpath = "(//div[@class='intlRtTimingOption appendBottom8']//b)[1]")
	WebElement depart;

	@FindBy(xpath = "(//div[@class='intlRtTimingOption appendBottom8']//b/..)[1]")
	WebElement departDay;

	@FindBy(xpath = "(//div[@class='flexOne timeInfoLeft'])[1]//span")
	WebElement departTime;

	@FindBy(xpath = "(//div[@class='flexOne timeInfoLeft'])[1]//p[2]/font")
	WebElement departCity;

	@FindBy(xpath = "(//div[@class='flexOne timeInfoRight'])[1]//span")
	WebElement departLandingTime;

	@FindBy(xpath = "(//div[@class='flexOne timeInfoRight'])[1]//p[2]/font")
	WebElement departDestinationCity;

	@FindBy(xpath = "(//div[@class='stop-info flexOne'])[1]/p")
	WebElement departJourneyHours;

	@FindBy(xpath = "(//p[@class='flightsLayoverInfo'])[1]")
	WebElement departFlightsLayover;

	@FindBy(xpath = "(//div[@class='intlRtTimingOption appendBottom8']//p)[2]/b")
	WebElement rturn;

	@FindBy(xpath = "(//div[@class='intlRtTimingOption appendBottom8']//p)[2]/b/..")
	WebElement returnDay;

	@FindBy(xpath = "(//div[@class='flexOne timeInfoLeft'])[2]//span")
	WebElement returnTime;

	// ((//div[@class='listingCard
	// appendBottom5'])[1]//div[@class='timingOptionOuter']//div[@class='flexOne
	// timeInfoLeft']/p/b/font)
	@FindBy(xpath = "(//div[@class='flexOne timeInfoLeft'])[2]//p[2]/font")
	WebElement returnCity;

	@FindBy(xpath = "(//div[@class='flexOne timeInfoRight'])[2]//span")
	WebElement returnLandingTime;

	@FindBy(xpath = "(//div[@class='flexOne timeInfoRight'])[2]//p[2]/font")
	WebElement returnDestinationCity;

	@FindBy(xpath = "(//div[@class='stop-info flexOne'])[2]/p")
	WebElement returnJourneyHours;

	@FindBy(xpath = "(//p[@class='flightsLayoverInfo'])[2]")
	WebElement returnFlightsLayover;

	@FindBy(xpath = "//p[text()='Cheapest']")
	WebElement cheapest;

	// @FindBy(xpath = "//button[@class='ViewFareBtn text-uppercase
	// clusterBtn']/..//div/div")
	@FindBy(xpath = "//p[text()='Cheapest']/..//p[contains(text(),'Duration')]")
	WebElement cheapestFare;

	@FindBy(xpath = "(//p[@class=\"checkboxTitle\"][contains(text(),'Non Stop')])[1]")
	WebElement nonstopcheckbox;

	@FindBy(xpath = "(//span[text()='View Flight Details'])[1]")
	WebElement viewDetailsLink;

	@FindBy(xpath = "(//p[@class='flightDetailsHead'])[1]")
	WebElement flightDetailsHead;

	@FindBy(xpath = "//div[@class='flightDetailsInfo']//font/b")
	WebElement viewDetailsAirlineName;

	// initialization
	public FlightInfoPage() {
		PageFactory.initElements(driver, this);
	}

	public Map<String, Integer> flightPrices(int dep, int ret) throws Exception {

		Map<String, Integer> fees = new HashMap<>();
		System.out.println("dep " + dep);
		System.out.println("ret " + ret);

		utilFunctions.ScrollToTop();
		if (utilFunctions.isVisble(clearFilterLink))
			clearFilterLink.click();

		utilFunctions.scrollToView(departureFlights.get(dep - 1));
		departureFlights.get(dep).click();
		utilFunctions.scrollToView(returnFlights.get(ret - 1));
		returnFlights.get(ret).click();

		Integer TopDeparturePrice = covertToMoney(departurePriceBox.getText());
		Integer TopReturnPrice = covertToMoney(returnPriceBox.getText());
		Integer FotDeparturePrice = covertToMoney(footerDeparturePriceBox.getText());
		Integer FotReturnPrice = covertToMoney(footerReturnPriceBox.getText());
		Integer TotalPrice = covertToMoney(footerTotalPriceBox.getText());

		fees.put("TopDeparturePrice", TopDeparturePrice);
		fees.put("TopReturnPrice", TopReturnPrice);
		fees.put("FootDeparturePrice", FotDeparturePrice);
		fees.put("FootReturnPrice", FotReturnPrice);
		fees.put("TotalPrice", TotalPrice);

		return fees;
	}

	public int covertToMoney(String raw) {
		// departurePriceBox.getText() -> Rs 7,924
		// (departurePriceBox.getText()).split(" ")[1] -> 7,924
		// (departurePriceBox.getText()).split(" ")[1].replaceAll(",$", "") -> 7924
		return Integer.parseInt(raw.split(" ")[1].replace(",", ""));
	}

	public void clickCheapest() {
		utilFunctions.ExpWaitForWebelement(cheapest);
		utilFunctions.clickonScreen(0, 0);
		cheapest.click();
	}

	public String returnCheapestPrice() {
		cheapestprice = cheapestFare.getText();
		test.log(Status.INFO, "returnCheapestPrice()::" + cheapestprice);
		return cheapestprice;
	}

	public double getCheapestPrice() {
		cheapestprice = cheapestFare.getText();
		price = utilFunctions.splitStringToGetPrice(cheapestprice);
		return price;
	}

	public void clickNonStop() {
		nonstopcheckbox.click();
	}

	public String getAirlineName() {
		String airlinename = airlineName.getText();
		return airlinename;
	}

	public String getAirlinePrice() {
		String price = priceSection.getText();
		return price;
	}

	public String getDepartureDay() {
		String departureday = departDay.getText();
		return departureday;
	}

	public String getDepartureTime() {
		String departuretime = departTime.getText();
		return departuretime;
	}

	public String getDepartureCity() {
		String departurecity = departCity.getText();
		return departurecity;
	}

	public String getDepartureJourneyHours() {
		String journeyhours = departJourneyHours.getText();
		return journeyhours;
	}

	public String getDepartureFlightsLayover() {
		String flightslayover = departFlightsLayover.getText();
		return flightslayover;
	}

	public String getDepartureLandingTime() {
		String departlandingtime = departLandingTime.getText();
		return departlandingtime;
	}

	public String getDepartureDestinationCity() {
		String departdestinationcity = departDestinationCity.getText();
		return departdestinationcity;
	}

	public String getReturnDay() {
		String returnday = returnDay.getText();
		return returnday;
	}

	public String getReturnTime() {
		String returntime = returnTime.getText();
		return returntime;
	}

	public String getReturnCity() {
		String returncity = returnCity.getText();
		return returncity;
	}

	public String getReturnJourneyHours() {
		String returnjourneyhours = returnJourneyHours.getText();
		return returnjourneyhours;
	}

	public String getReturnFlightsLayover() {
		String returnflightslayover = returnFlightsLayover.getText();
		return returnflightslayover;
	}

	public String getReturnLandingTime() {
		String returnlandingtime = returnLandingTime.getText();
		return returnlandingtime;
	}

	public String getReturnDestinationCity() {
		String returndestinationcity = returnDestinationCity.getText();
		return returndestinationcity;
	}

	// Convert this to Map for 1:1 fields and its value mapping.
	public String departureFlightDetails() {
		String depflightdetails = getAirlineName() + " | " + getAirlinePrice() + " | " + getDepartureDay() + " | "
				+ getDepartureTime() + " | " + getDepartureCity() + " | " + getDepartureJourneyHours() + " | "
				+ getDepartureFlightsLayover() + " | " + getDepartureLandingTime() + " | "
				+ getDepartureDestinationCity();
		return depflightdetails;
	}

	// Convert this to Map for 1:1 fields and its value mapping.
	public String returnFlightDetails() {
		String returnflightdetails = getAirlineName() + " | " + getAirlinePrice() + " | " + getReturnDay() + " | "
				+ getReturnTime() + getReturnCity() + " | " + getReturnJourneyHours() + " | "
				+ getReturnFlightsLayover() + " | " + getReturnLandingTime() + " | " + getReturnDestinationCity();
		return returnflightdetails;
	}

	public void clickViewDetails() {
		utilFunctions.ExpWaitForWebelement(viewDetailsLink);
		utilFunctions.scrollToView(viewDetailsLink);
		viewDetailsLink.click();
	}

	// Convert this to Map for 1:1 fields and its value mapping.
	public String onewayFlightDetails() {
		String onewayflightdetails = flightDetailsHead.getText() + " | " + viewDetailsAirlineName.getText();
		return onewayflightdetails;
	}
}
