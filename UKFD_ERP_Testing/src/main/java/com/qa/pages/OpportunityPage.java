package com.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class OpportunityPage extends TestUtil{
	
	@FindBy(xpath = "//input[@name='inpt_leadsource']")
	WebElement select_lead_source;//5 Call Centre
	
	@FindBy(xpath = "//td[@id='spn_multibutton_submitter']")
	WebElement save_button;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement opprnty_confirmation_message;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement opprtny_id;
	
	@FindBy(xpath = "//a[@id='salestabtxt']")
	WebElement sales_tab_link;
	
	@FindBy(xpath = "//input[@id='createestimate']")
	WebElement new_quote_button_link;
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	

	public OpportunityPage()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);

	}
	
	public void enter_Opprnty_Details(String lead_source, ExtentTest test) throws InterruptedException
	{
		eleClickable(driver, select_lead_source, 10);
		select_lead_source.sendKeys(lead_source.trim());
		save_button.click();
		if(opprnty_confirmation_message.getText().trim().equals("Transaction successfully Saved"))
		{
			test.pass("Opportunity is created successfully with id number "+opprtny_id.getText().trim());
		}
		else
		{
			test.fail("Opportunity is not created");
		}
		executor.executeScript("arguments[0].scrollIntoView(true);", sales_tab_link);
		Thread.sleep(1000);
		sales_tab_link.click();
		new_quote_button_link.click();
		

	}
	

}
