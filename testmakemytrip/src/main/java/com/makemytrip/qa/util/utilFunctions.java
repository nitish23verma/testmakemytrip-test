package com.makemytrip.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makemytrip.qa.base.TestBase;

public class utilFunctions extends TestBase {

	/*
	 * 1. ExpWaitForWebelement() 2. getExcelData() 3. getCurrentAndReturnDates() 4.
	 * customXpath() 5. scrollToBottom() 6. ScrollToTop() 7. scrollToView() 8.
	 * getScreenshot()
	 *
	 */

	/**************************************
	 * Function Name: ExpWaitForWebelement Author: Nitish Created Date: 2023-08-22
	 * Purpose: Explicit wait for a web element Prerequisites: WebElement that takes
	 * more time to load. Change History:
	 *
	 **************************************/
	public static void ExpWaitForWebelement(WebElement element) {
		WebDriverWait expWait = new WebDriverWait(driver, 60);
		expWait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void PageLoadWait(double min) {
		try {
			Thread.sleep((long) (min * 1000 * 60));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void doubleclick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	public static void clickonScreen(int xCoordinate, int yCoordinate) {
		Actions actions = new Actions(driver);
		// actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
		actions.moveByOffset(xCoordinate, yCoordinate).click().build().perform();
		actions.moveByOffset(xCoordinate, yCoordinate).click().build().perform();
		actions.moveByOffset(xCoordinate, yCoordinate).click().build().perform();
		actions.moveByOffset(xCoordinate, yCoordinate).click().build().perform();
		actions.moveByOffset(xCoordinate, yCoordinate).click().build().perform();
		actions.moveByOffset(xCoordinate, yCoordinate).click().build().perform();
	}

	public static void elementIsClickable(WebElement element) {
		element = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
		// ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}

	/**************************************
	 * Function Name: getCurrentAndReturnDates Author: Nitish Created Date:
	 * 2023-08-22 Purpose: Get the current date and another date that is x(from
	 * property file) days from today Prerequisites: numeric value for x Change
	 * History: getCurrentAndReturnDates() should be called to assign the values to
	 * departureDate and returnDate
	 *
	 **************************************/
	public static String departureDate;
	public static String returnDate;

	public static utilFunctions getCurrentAndReturnDates() {
		utilFunctions date = new utilFunctions();
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, 1); // please delete this line. added for test the
		// tomorrow date.
		String[] dateArr = cal.getTime().toString().split(" ");
		utilFunctions.departureDate = dateArr[0] + " " + dateArr[1] + " " + dateArr[2] + " " + dateArr[5];
		cal.add(Calendar.DATE, Integer.parseInt(prop.getProperty("NoOfdays")));
		dateArr = cal.getTime().toString().split(" ");
		utilFunctions.returnDate = dateArr[0] + " " + dateArr[1] + " " + dateArr[2] + " " + dateArr[5];
		return date;
	}

	/**************************************
	 * Function Name: customXpath Author: Nitish Created Date: 2023-08-22 Purpose:
	 * Insert a java variable to xpath Prerequisites: Change History:
	 *
	 **************************************/
	public static By customXpath(String xpath, String param) {
		String rawPath = xpath.replaceAll("%replace%", param);
		return By.xpath(rawPath);
	}

	public static By customDateFieldXpath(int date) {
		String rawPath = "(//div[@class='dateInnerCell']/p[text()='" + date + "'])[1]";
		return By.xpath(rawPath);
	}

	/**************************************
	 * Functions Names: scrollToBottom ScrollToTop scrollToView Author: Nitish
	 * Created Date: 2023-08-22 Purpose: scroll the page through javascript executer
	 * Prerequisites: Change History:
	 *
	 * @throws InterruptedException
	 *
	 **************************************/
	public static void scrollToBottom() throws InterruptedException {
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

		try {
			long currentHeight = Long.parseLong(jsDriver.executeScript("return document.body.scrollHeight").toString());

			while (true) {
				jsDriver.executeScript("window.scrollTo(0,document.body.scrollHeight)");
				Thread.sleep(300);

				long newHeight = Long.parseLong(jsDriver.executeScript("return document.body.scrollHeight").toString());

				if (currentHeight == newHeight)
					break;
				currentHeight = newHeight;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ScrollToTop() {
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public static void scrollToView(WebElement element) {
		// Actions actions = new Actions(driver);
		// actions.moveToElement(element);
		// actions.perform();
		// element.sendKeys(Keys.DOWN);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", element);
		utilFunctions.PageLoadWait(0.5);

		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
		// JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		// jsDriver.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**************************************
	 * Function Name: getScreenshot Author: Nitish Created Date: 2023-08-22 Purpose:
	 * Get screen shot of the test Prerequisites: apachi commons.io dependency
	 * Change History:
	 *
	 **************************************/

	public static String getScreenshot(String imageName) {

		String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/Screenshots/" + imageName + currentDate + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			System.out.println("Failed to capture the screen " + e.getMessage());
		}

		return destination;
	}

	/**************************************
	 * Function Name: isVisble Author: Nitish Created Date: 2023-08-22 Purpose:
	 * Check the emement is present in DOM or not. If not, return false
	 * Prerequisites: apachi commons.io dependency Change History:
	 *
	 **************************************/
	@SuppressWarnings("deprecation")
	public static boolean isVisble(WebElement element) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			if (element.isDisplayed()) {
				flag = true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return flag;
	}

	public static double number = 0;

	public static double splitStringToGetNumber(String toSplit) {
		String pattern = "\\d{1,3}(,\\d{3})*(\\.\\d+)?";
		// Regular expression to match numbers with or without commas
		Pattern regexPattern = Pattern.compile(pattern);
		Matcher matcher = regexPattern.matcher(toSplit);

		if (matcher.find()) {
			String numberString = matcher.group();
			// Remove commas and convert to an integer value
			number = Double.parseDouble(numberString.replaceAll(",", ""));
		} else {
			System.out.println("No number found in the input string.");
			return 0; // Or any other suitable default value
		}
		return number;
	}

	public static double splitStringToGetPrice(String price) {
		String[] splitted = price.split("\\s+");
		for (int i = 0; i < splitted.length; i++) {
			number = Double.parseDouble(splitted[1].replaceAll(",", ""));
		}
		return number;
	}

}
