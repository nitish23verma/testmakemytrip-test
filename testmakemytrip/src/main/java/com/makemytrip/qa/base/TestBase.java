package com.makemytrip.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	// make the path dynamic
	// get the browser from properties file

	public static Properties prop;
	public static WebDriver driver;

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childTest;

	// Initialize the driver
	public void driverInitialization() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	// get the data from property file to 'prop' object.
	public void readPropertyFile() {
		try {

			FileInputStream propFile = new FileInputStream(".\\config\\config.properties");
			prop = new Properties();
			prop.load(propFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Setup extend report v4
	public void setExtentReport() {

		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/extentReports/MMTFunctionalTestReport.html");
		htmlReporter.config().setDocumentTitle("Makemytrip Automation Report"); // Title
		htmlReporter.config().setReportName("Functional Testing"); // Report Name
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Nitish Verma");
	}
}
