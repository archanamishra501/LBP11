package com.training.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class LoginPOM {
	private WebDriver driver;

	public LoginPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "login")
	private WebElement userName;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//button[text() ='Login']")
	private WebElement loginBtn;

	// Actions act = Actions(driver);
	@FindBy(linkText = "Homepage")
	private WebElement homePageBtn;

	@FindBy(xpath = "//li[@class='homepage active']")
	private WebElement activeHomePage;

	@FindBy(linkText = "Create a course")
	private WebElement createACourse;

	@FindBy(id = "title")
	private WebElement courseName;

	@FindBy(id = "advanced_params")
	private WebElement clickAdvanceSettings;

	@FindBy(xpath = "//label[contains(text(),'Category')]")
	private WebElement categoryLabel;

	@FindBy(xpath = "//label[contains(text(),'Language')]")
	private WebElement languageLabel;

	@FindBy(xpath = "//label[contains(text(),'Course code')]")
	private WebElement courseCodeLabel;

	@FindBy(className = "filter-option")
	private WebElement category;

	@FindBy(xpath="(//button[@data-id='add_course_category_code']/following::div[@role='listbox']/ul)[1]/li/a/span")
	private List<WebElement> selectCategory;
	
	@FindBy(xpath="//input[@id='add_course_wanted_code']")
	private WebElement courseCode;
	
	@FindBy(xpath="//button[@id='add_course_submit']")
	private WebElement createThisCourseBtn;
	
	@FindBy(xpath="//button[@data-id='add_course_course_language']")
	private WebElement languageEl;
	
	@FindBy(xpath="//select[@id='add_course_course_language']/option")
	private List<WebElement> languageOptions;
	
	@FindBy(xpath= "//a[@title = 'Add an introduction text']")
	private WebElement AddIntroductionTextBtn;
	
	@FindBy(xpath= "//iframe[contains(@title,'Rich Text Editor, intro_content')]")
	private WebElement locateFrame;
	
	@FindBy(xpath= "//body[@class = 'cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	private WebElement enterIntroductionText;
	
	@FindBy(xpath="//button[text() =' Save intro text']")
	private WebElement clickSaveIntroText;
	
	@FindBy(xpath="//div[text() = 'Intro was updated']")
	private WebElement validateIntroWasUpdate;
	
	@FindBy(xpath="//p[text() = 'This is a selenium course.']")
	private WebElement validateIntroText;
	
//	@FindBy(linkText= " selenium-Test3111")
//	private WebElement clickOnCourseCreated;
	
	@FindBy(xpath = "//img[contains(@title , 'Course description')]")
	private WebElement clickCourseDescriptionIcon;
	
	@FindBy(xpath= "//img[@src='http://elearningm1.upskills.in/main/img/icons/32/info.png']")
	private WebElement clickDescriptionIcon;
	
	@FindBy(xpath ="//label[@for ='course_description_title']")
	private WebElement titleLabel;
	
	@FindBy(xpath ="//label[@for ='course_description_contentDescription']")
	private WebElement contentLabel;
	
	@FindBy(xpath = "//input[@id= 'course_description_title']")
	private WebElement enterCourseDescTitle;
	
	@FindBy(xpath= "//iframe[contains(@title,'Rich Text Editor, contentDescription')]")
	private WebElement locateFrameInDescription;
	
	@FindBy(xpath= "//body[@class = 'cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	private WebElement enterContentText;

	@FindBy(xpath="//button[text() =' Save']")
	private WebElement clickSaveBtn;
	
	@FindBy(xpath= "//div[text() ='The description has been updated']")
	private WebElement validateUpdateMessage;
	
	@FindBy(xpath= "//p[text() = 'selenium course for beginners.']")
	private WebElement validateUpdateDesc;
	
	@FindBy(xpath="//a[starts-with(text(),' selenium-Test')]")
	private WebElement clickOnCourseCreated;
	
	@FindBy(xpath="//img[@src = 'http://elearningm1.upskills.in/main/img/icons/64/members.png']")
	private WebElement clickUsrIc;
	
	@FindBy(xpath="//img[@src = 'http://elearningm1.upskills.in/main/img/icons/32/add-user.png']")
	private WebElement clkAddUsr;
	
	@FindBy(xpath="//input[@type='checkbox'][@value='110']")
	private WebElement registerCheckBox;
	
	@FindBy(xpath="(//a[text() = 'Register'])[1]")
	private WebElement clkRegBtn;
	
	@FindBy(xpath="//input[@type='checkbox'][@value='110']")
	private WebElement selectRegisterdLearner;
	
	@FindBy(xpath="(//a[text() = 'Unsubscribe'])")
	private WebElement clickUnscrbBtn;
	
	@FindBy(xpath= "//div[text() = 'User is now unsubscribed']")
	private WebElement UnsubscribedMessage;
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void sendPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void clickLoginBtn() {
		this.loginBtn.click();
	}

	public void clickHomePageBtn() {
		this.homePageBtn.click();
	}
	
	public  void loginToApplication(String userName, String password){
		sendUserName(userName);
		sendPassword(password);
		clickLoginBtn();
	}

	public void validateIfHomePageIsActive() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(activeHomePage.isDisplayed());
	}

	public void clickCreateACourse() throws InterruptedException {
		this.createACourse.click();
		Thread.sleep(1000);
	}

	public void sendCourseName(String courseName) {
		this.courseName.clear();
		this.courseName.sendKeys(courseName);
	}

	public void clickAdvanceSettingsBtn() {
		this.clickAdvanceSettings.click();
	}

	public void validateFieldsInAdvanceSettings() {
		Assert.assertTrue(categoryLabel.isDisplayed() && languageLabel.isDisplayed() && courseCodeLabel.isDisplayed());
	}

	public void clickCategory() {
		this.category.click();

	}

	public void sendCategory(String category) {
		for(WebElement e:selectCategory){
			if(e.getText().contains(category))
				e.click();
		}
		
	}
	
	public void sendCourseCode(String courseCode){
		this.courseCode.clear();
		this.courseCode.sendKeys(courseCode);
	}
	
	public void clickCreateThisCourse() throws InterruptedException{
		Thread.sleep(2000);
		this.createThisCourseBtn.click();
	}

	public void validateTheLanguage(String langVal) {
		if(!this.languageEl.getAttribute("title").contains(langVal)){
			for(WebElement we : languageOptions){
				if(we.getText().contains(langVal))
					we.click();
			}
		}
		
	}
	
	public void clickIntroductionTextBtn(){
		this.AddIntroductionTextBtn.click();
	}
	
	public void sendIntroductionTextMsg(String introductionText ){
		driver.switchTo().frame(locateFrame);
		this.enterIntroductionText.click();
		this.enterIntroductionText.sendKeys(introductionText);
		driver.switchTo().defaultContent();
	}
	
	public void clickSaveIntorTextBtn(){
		this.clickSaveIntroText.click();
	}
	
	public void validateSuccessfullIntroUpdate(){
		System.out.println(this.validateIntroWasUpdate.getText());
	}
	public void validateIntroText(){
		System.out.println(this.validateIntroText.getText());
	}
//	public void clickCourseCreated(){
//		this.clickOnCourseCreated.click();
//	}
	public void clickOnCourseDescIcon(){
		this.clickCourseDescriptionIcon.click();
	}

	public void clickDescIcon(){
		this.clickDescriptionIcon.click();
	}
	public void validateFieldsInDescPage(){
		Assert.assertTrue(titleLabel.isDisplayed() && contentLabel.isDisplayed());
	}
	
	public void sendcourseDescpTitle(String courseDescpTitle){
		this.enterCourseDescTitle.clear();
		this.enterCourseDescTitle.sendKeys(courseDescpTitle);
	}
	public void sendContentTxt(String content ){
		driver.switchTo().frame(locateFrameInDescription);
		this.enterContentText.click();
		this.enterContentText.sendKeys(content);
		driver.switchTo().defaultContent();
	}
	public void clickSaveInDescPage(){
		this.clickSaveBtn.click();
	}
	public void validateUpdateTxt(){
		System.out.println(this.validateUpdateMessage.getText());
	}
	public void validateDescUpdateTxt(){
		System.out.println(this.validateUpdateDesc.getText());
	}
	public void clickOnCourseCreatedLink(){
		this.clickOnCourseCreated.click();
	}
	
	public void clickUsersIcon(){
		this.clickUsrIc.click();
	}
	public void clickAddUserIcon(){
		this.clkAddUsr.click();
	}
	
	/* select the checkbox and then register the learner */
	
	public void selectFirstLearnerToRegister(){
		this.registerCheckBox.click();
		}
	public void clickRegsisterBtn(){
		this.clkRegBtn.click();
	}
	public void selectRegLearner(){
		this.selectRegisterdLearner.click();
	}
	public void clickUnsubscribeBtn(){
		this.clickUnscrbBtn.click();
	}
	public void AlertPopUp(){
		Alert alert = driver.switchTo().alert();
		String alertMessage = driver.switchTo().alert().getText();
		System.out.println(alertMessage);
		alert.accept();
		
	}
	
	
	public void validateUnsubscribedMessgage(){
		System.out.println(this.UnsubscribedMessage.getText());
	}
}
