package com.training.pom;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.base.Verify;

public class MyCoursePOM{
	private WebDriver driver;
	
	public MyCoursePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText= "My courses")
	private WebElement myCoursesLink;
	
	@FindBy(linkText= "selenium-Test3119")
	private WebElement selCourseLink;
	
	@FindBy(xpath= "//img[@title = 'Groups']")
	private WebElement GrpIcon;
	
	@FindBy(css="ul.breadcrumb")
	private WebElement GroupNavBar ;
	
	@FindBy(xpath= "//img[@title = 'Create new group(s)']")
	private WebElement createGrpIcon;
	
	@FindBy(css="ul.breadcrumb")
	private WebElement newGrpsCreationBar ;
	
	@FindBy(id = "create_groups_number_of_groups")
	private WebElement createNumOfGrps ;
	
	@FindBy(id="create_groups_submit")
	private WebElement proceedToCreateGrpBtn ;
	
	@FindBy(name="group_0_name")
	private WebElement grpName ;
	
	@FindBy(id="create_groups_step2_submit")
	private WebElement createGrpsBtn ;
	
	@FindBy(xpath="//div[text()='group(s) has (have) been added']")
	private WebElement validateGrpsAddedTxt;
	
	@FindBy(linkText="IBMBatch11")
	private WebElement validateGrpName;
	
	@FindBy(xpath="//img[@title ='Group members']")
	private WebElement grpMemIcon;
	
	@FindBy(xpath="//select[@id = 'group_members']")
	private WebElement ListofStudentsReg;
	
	@FindBy(id="group_members_rightSelected")
	private WebElement rightArrowIcon;
	
	@FindBy(id="group_edit_submit")
	private WebElement SaveSettings;
	
	@FindBy(xpath="//div/select/option[text() ='Archana Mishra (archana310) - ARCHANA310']")
	private WebElement studentSelected;
	
	@FindBy(xpath= "//div[text() = 'Group settings modified']")
	private WebElement grpSettingsModifiedMsg;
	
	@FindBy(xpath="//img[@title ='Edit this group']")
	private WebElement editSettingsIcon;
	
	@FindBy(xpath="//legend[text() ='Default settings for new groups']")
	private WebElement defaultSetOfNewGrps;
	
	@FindBy(xpath="//input[@name ='self_registration_allowed']")
	private WebElement learnerSelfRegCheckBox;
	
	@FindBy(xpath="//input[@name ='self_unregistration_allowed']")
	private WebElement learnerSelfUnRegCheckBox;
	
	@FindBy(xpath ="//img[@title ='Reporting']")
	private WebElement reportingIcon;
	
	@FindBy(xpath="//div[@class='tracking-course-summary']")
	private WebElement trackingCourseSummary;
	
	@FindBy(xpath="//img[@title='Details']")
	private WebElement detailsIcon;
	
	@FindBy(xpath="//h2[@class='details-title']")
	private WebElement courseNameInReporting;
	
	@FindBy(xpath="//span[contains(text(),'0%')]")
	private WebElement courseProgressPercentage;
	
	@FindBy(xpath="//img[@title='Quiz']")
	private WebElement quizIcon;

	@FindBy(linkText="Reporting")
	private WebElement reportingTab;
	
	@FindBy(className="breadcrumb")
	private WebElement reportingBarDisplay;
	
	@FindBy(linkText="Followed students")
	private WebElement followedStudentsLink;
	
	@FindBy(xpath="//h3[text()='Learners']")
	private WebElement learnersBarDisplay;
	
	@FindBy(id="search_user_keyword")
	private WebElement keywordField;
	
	@FindBy(id="search_user_submit")
	private WebElement searchBtn;
	
	@FindBy(xpath="//table/tbody/tr[2]")
	private WebElement searchedLearnerValues;
	
	@FindBy(xpath="//img[@title ='Details sunil']")
	private WebElement detailsSunilIcon;
	
	@FindBy(xpath="//div[@class ='panel-body']")
	private WebElement studentInfo;
	
	@FindBy(xpath="//img[@title ='Details'][1]")
	private WebElement learnerCourseDetailsIcon;
	
	@FindBy(xpath="//li[contains(text(),'Learner details in course')]")
	private WebElement learnerDetailsInCourseBar;

	public void clickMyCoursesLink(){
		this.myCoursesLink.click();
	}
	public void clickSelCourseLink(){
		this.selCourseLink.click();
	}
	public void clickGrpIcon(){
		this.GrpIcon.click();
	}
	public void validateGrpNavigationBar(){
		Verify.verify(GroupNavBar.isDisplayed());
	}
	
	//To verify whether application allows teacher to create a group & modify the settings
	public void createGrps(String numOfGrpsToCreate, String groupName){
		this.createGrpIcon.click();
		Verify.verify(newGrpsCreationBar.isDisplayed());
		createNumOfGrps.clear();
		createNumOfGrps.sendKeys(numOfGrpsToCreate);
		proceedToCreateGrpBtn.click();
		grpName.clear();
		grpName.sendKeys(groupName);
		grpName.getText();
		createGrpsBtn.click();
		Assert.assertTrue(validateGrpsAddedTxt.isDisplayed());
		System.out.println(validateGrpName.getText());
		grpMemIcon.click();
		System.out.println(ListofStudentsReg.getText());
		Select grpMem = new Select(ListofStudentsReg);
		grpMem.selectByVisibleText("Archana Mishra (archana310) - ARCHANA310");
		rightArrowIcon.click();
		Assert.assertTrue(studentSelected.isDisplayed());
		SaveSettings.click();
		Assert.assertTrue(grpSettingsModifiedMsg.isDisplayed());
		//Assert.assertTrue(validateGrpsAddedTxt.isDisplayed());
		System.out.println(validateGrpName.getText());
		editSettingsIcon.click();
		Assert.assertTrue(defaultSetOfNewGrps.isDisplayed());
		learnerSelfRegCheckBox.click();
		learnerSelfRegCheckBox.isSelected();
		learnerSelfUnRegCheckBox.click();
		learnerSelfUnRegCheckBox.isSelected();
		SaveSettings.click();
		Assert.assertTrue(grpSettingsModifiedMsg.isDisplayed());
		System.out.println(grpSettingsModifiedMsg.getText());
	}
	
	//TO verify whether application allows teacher to Report & send mail to student about the test submitted
	public void clickReporting() throws FileNotFoundException{
		try {
			reportingIcon.click();
			System.out.println(trackingCourseSummary.getText());
			detailsIcon.click();
			Assert.assertEquals(courseNameInReporting.getText(), "selenium-Test3119");
			Assert.assertEquals(courseProgressPercentage.getText(), "0%");
			Assert.assertFalse(quizIcon.isDisplayed());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//TO verify whether application allows teacher to generate report based on the student
	public void generateReport(String keyword) throws FileNotFoundException{
		try{
		reportingTab.click();
		Assert.assertTrue(reportingBarDisplay.isDisplayed());
		followedStudentsLink.click();
		Assert.assertTrue(learnersBarDisplay.isDisplayed());
		keywordField.sendKeys(keyword);
		searchBtn.click();
		System.out.println(searchedLearnerValues.getText());
		detailsSunilIcon.click();
		System.out.println(studentInfo.getText());
		String courseDetailsStr = "((//table[contains(@class,'table table-striped table-hover courses-tracking')])[1]/descendant::tr)";
		List<WebElement> courseDetailsList = driver.findElements(By.xpath(courseDetailsStr));
		for(int i=2;i<courseDetailsList.size();i++){
			WebElement courseDetailsEl = driver.findElement(By.xpath("((//table[contains(@class,'table table-striped table-hover courses-tracking')])[1]/descendant::tr["+i+"]/td/a)[1]"));
			System.out.println(courseDetailsEl.getText());
		}
		learnerCourseDetailsIcon.click();
		System.out.println(learnerDetailsInCourseBar.getText());
		Assert.assertFalse(quizIcon.isDisplayed());
	} catch (NoSuchElementException e) {
		e.printStackTrace();
	}
	}
	

}

