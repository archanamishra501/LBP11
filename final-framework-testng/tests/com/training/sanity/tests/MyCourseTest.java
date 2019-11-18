package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdministratorPOM;
import com.training.pom.LoginPOM;
import com.training.pom.MyCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class MyCourseTest {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private AdministratorPOM adminPOM ;
	private MyCoursePOM myCoursePOM;
	private static Properties properties;
	private ScreenShot screenShot;

	// @BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		setUpBeforeClass();
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		adminPOM = new AdministratorPOM(driver);
		myCoursePOM = new MyCoursePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(priority =1,enabled=true)
	public void myCoursesTabTest(){
		loginPOM.loginToApplication(properties.getProperty("userName"), properties.getProperty("password"));
		adminPOM.validateAdminTabInNavigationBar();
		myCoursePOM.clickMyCoursesLink();
		myCoursePOM.clickSelCourseLink();
		myCoursePOM.clickGrpIcon();
		myCoursePOM.validateGrpNavigationBar();
		myCoursePOM.createGrps(properties.getProperty("numOfGrpsToCreate"),properties.getProperty("groupName"));
		screenShot.captureScreenShot("SS of Create Group");
	}
	
	@Test(priority = 2,enabled=true)
	public void reportingTest() throws FileNotFoundException{
		loginPOM.loginToApplication(properties.getProperty("userName"), properties.getProperty("password"));
		adminPOM.validateAdminTabInNavigationBar();
		myCoursePOM.clickMyCoursesLink();
		myCoursePOM.clickSelCourseLink();
		myCoursePOM.clickReporting();
	}
	@Test(priority=3,enabled=true)
	public void generateReportTest() throws FileNotFoundException{
		loginPOM.loginToApplication(properties.getProperty("userName"), properties.getProperty("password"));
		adminPOM.validateAdminTabInNavigationBar();
		myCoursePOM.generateReport(properties.getProperty("keyword"));
	
	}
}

