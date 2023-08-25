package com.makemytrip.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.makemytrip.qa.base.TestBase;
import com.makemytrip.qa.util.utilFunctions;

public class HomePage extends TestBase {

	// object factory

	@FindBy(xpath = "//a[contains(@href,'www.makemytrip.com/flights')]/span[contains(@class,'chFlights')]")
	WebElement flightsMenu;

	@FindBy(xpath = "//li[@data-cy='oneWayTrip']/span")
	WebElement oneWay;

	@FindBy(xpath = "//li[contains(text(),'Round Trip')]")
	WebElement roundTripMenu;

	@FindBy(xpath = "//input[@id='fromCity']")
	WebElement fromCityDrop;

	@FindBy(xpath = "//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")
	WebElement firstOption;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement searchFromCity;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement searchToCity;

	@FindBy(xpath = "//label[@for='toCity']")
	WebElement toCity;

	@FindBy(xpath = "//div[contains(@class,'dateFiled')][1]")
	WebElement departureDrop;

	@FindBy(xpath = "//div[contains(@class,'dateFiled')][2]")
	WebElement returnDrop;

	@FindBy(xpath = "//div[@data-cy='returnArea']//span")
	WebElement returndropdown;

	String departureDate = "//div[@aria-label='%replace%' and @aria-disabled='false']";
	String returnDate = "//div[@aria-label='%replace%' and @aria-disabled='false']";

	@FindBy(xpath = "//a[contains(@class,'widgetSearchBtn') and text()='Search']")
	WebElement searchBtn;

	// constructor
	public HomePage() {
		// super();
		PageFactory.initElements(driver, this);
	}

	// actions
	public void selectFlightsMenu() {
		utilFunctions.clickonScreen(0, 0);
		flightsMenu.click();
	}

	public void selectOnewayMenu() {
		oneWay.click();
	}

	public void selectRoundtripMenu() {
		utilFunctions.ExpWaitForWebelement(roundTripMenu);
		roundTripMenu.click();
	}

	public void enterDepartureCity(String fromcity) {
		fromCityDrop.click();
		searchFromCity.sendKeys(fromcity);
		utilFunctions.PageLoadWait(0.02);
		utilFunctions.ExpWaitForWebelement(firstOption);
		firstOption.click();
	}

	public void enterReturnCity(String tocity) {
		toCity.click();
		searchToCity.sendKeys(tocity);
		utilFunctions.PageLoadWait(0.02);
		utilFunctions.ExpWaitForWebelement(firstOption);
		firstOption.click();
	}

	public void enterDepartureDate(String departuredate) {
		departureDrop.click();
		WebElement departure = driver.findElement(utilFunctions.customDateFieldXpath(Integer.parseInt(departuredate)));

		if (oneWay.getAttribute("class").equalsIgnoreCase("")) {
			utilFunctions.doubleclick(departure);
		} else {
			departure.click();
		}
	}

	public void enterReturnDate(String returndate) {
		utilFunctions.PageLoadWait(0.1);
		WebElement returnd = driver.findElement(utilFunctions.customDateFieldXpath(Integer.parseInt(returndate)));
		// utilFunctions.scrollToView(returnd);
		utilFunctions.doubleclick(returnd);
	}

	public FlightInfoPage search() {
		searchBtn.click();
		utilFunctions.PageLoadWait(.3);
		driver.manage().deleteAllCookies();
		return new FlightInfoPage();
	}

}
