package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	// @BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeTest
	public void setUp() throws Exception {
		setUpBeforeClass();
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	
	  @AfterTest public void tearDown() throws Exception { 
	  Thread.sleep(1000);
	  driver.quit(); }
	 

	@Test(priority = 1)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		screenShot.captureScreenShot("First");
	}

	@Test(priority = 2, dependsOnMethods = "validLoginTest", alwaysRun = true)
	public void create_a_course() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().contains("/admin/index.php"));
		loginPOM.clickHomePageBtn();
		loginPOM.validateIfHomePageIsActive();
		loginPOM.clickCreateACourse();
		loginPOM.sendCourseName(properties.getProperty("courseName"));
		loginPOM.clickAdvanceSettingsBtn();
		loginPOM.validateFieldsInAdvanceSettings();
		loginPOM.clickCategory();
		loginPOM.sendCategory(properties.getProperty("category"));
		loginPOM.sendCategory(properties.getProperty("courseCode"));
		loginPOM.validateTheLanguage(properties.getProperty("language"));
		loginPOM.clickCreateThisCourse();
		loginPOM.clickIntroductionTextBtn();
		Thread.sleep(2000);
		loginPOM.sendIntroductionTextMsg(properties.getProperty("introductionText"));
		loginPOM.clickSaveIntorTextBtn();
		loginPOM.validateSuccessfullIntroUpdate();
		loginPOM.validateIntroText();
		screenShot.captureScreenShot("Second");

	}

	@Test(priority = 3, dependsOnMethods = "create_a_course", alwaysRun = true)
	public void enter_course_desription() throws InterruptedException {
		Thread.sleep(1000);
		loginPOM.clickOnCourseDescIcon();
		loginPOM.clickDescIcon();
		loginPOM.validateFieldsInDescPage();
		loginPOM.sendcourseDescpTitle(properties.getProperty("courseDescpTitle"));
		loginPOM.sendContentTxt(properties.getProperty("content"));
		loginPOM.clickSaveInDescPage();
		loginPOM.validateUpdateTxt();
		loginPOM.validateDescUpdateTxt();
		screenShot.captureScreenShot("Third");
	}

	@Test(priority = 4, dependsOnMethods = "enter_course_desription", alwaysRun = true)
	public void unsubscribe_registered_user() {
		loginPOM.clickOnCourseCreatedLink();
		loginPOM.clickUsersIcon();
		loginPOM.clickAddUserIcon();
		loginPOM.selectFirstLearnerToRegister();
		loginPOM.clickRegsisterBtn();
		loginPOM.selectRegLearner();
		loginPOM.clickUnsubscribeBtn();
		loginPOM.AlertPopUp();
		loginPOM.validateUnsubscribedMessgage();
		screenShot.captureScreenShot("Fourth");

	}
}
