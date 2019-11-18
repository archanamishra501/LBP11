package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.base.Verify;

public class AdministratorPOM {
	
	
	private WebDriver driver;
	
	public AdministratorPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='navbar']/descendant::li/a[@title='Administration']")
	private WebElement adminTab; 
	
	
	public void validateAdminTabInNavigationBar(){
		Verify.verify(adminTab.isDisplayed());
	}

}
