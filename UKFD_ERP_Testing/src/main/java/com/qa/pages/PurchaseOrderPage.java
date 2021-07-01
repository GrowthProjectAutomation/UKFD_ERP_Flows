package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.TestUtil;

public class PurchaseOrderPage extends TestUtil {
	
	@FindBy(xpath = "//input[@name='receive']")
	WebElement receiveBtn;
	LoginPage loginPage;
	
	public PurchaseOrderPage()
	{
		PageFactory.initElements(driver, this);
	}

	public void navigateToReceive() throws InterruptedException
	{
		String url=driver.getCurrentUrl();
		loginPage=new LoginPage();
		loginPage.choose_required_role("Fulfilment");
		driver.navigate().to(url);
		eleAvailability(driver, receiveBtn, 20);
		receiveBtn.click();
	}
}
