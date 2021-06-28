package com.qa.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class ItemFulfilment extends TestUtil {
	
	@FindBy(xpath = "//input[@id='process']")
	WebElement fulFil_button;
	
	@FindBy(xpath = "//div[@class='dropdownDiv']//div")
	List<WebElement> dropdownList;
	
	@FindBy(xpath = "//input[@id='secondaryprocess']")
	WebElement secondary_fulfil_button;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr//following-sibling::tr//td[count(//td[@data-label='Committed']//preceding-sibling::td)+1]")
	WebElement committed_quantity;
	
	@FindBy(xpath = "//input[@name='inpt_shipstatus']")
	WebElement select_shipping_status;
	
	@FindBy(xpath = "//td[@id='spn_multibutton_submitter']")
	WebElement save_item_fulfilment;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement fulfilment_id;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement fulfilment_name;
	
	@FindBy(xpath = "//div[@class='uir-record-status']")
	WebElement fulfilment_status;
	
	@FindBy(xpath = "//span[@id='createdfrom_lbl_uir_label']//following-sibling::span")
	WebElement sales_order_link;
	
	public ItemFulfilment()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
		
	}
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	
	
	
	
	
	public void item_Fulfillment( ExtentTest test) throws InterruptedException
	{
		
		secondary_fulfil_button.click();
		eleAvailability(driver, select_shipping_status, 10);
		selectDropdownValue(select_shipping_status, dropdownList, "Shipped");
		Thread.sleep(1000);
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(1500);
		save_item_fulfilment.click();
		eleAvailability(driver, fulfilment_status, 20);
		if(fulfilment_status.getText().trim().equals("SHIPPED"))
		{
			
			test.pass("Item Fulfilment is created with id name "+fulfilment_id.getText().trim()+" "+fulfilment_name.getText().trim());
			test.pass("Item Fulfilment is in "+fulfilment_status.getText().trim() +" status");
		}
		else
		{
			test.fail("Item Fulfilment is not created");
		}
		sales_order_link.click();
		eleAvailability(driver, fulfilment_status, 10);
		
		
	}
	public void verify_sales_order_status_billed(ExtentTest test)
	{
		if(fulfilment_status.getText().trim().equals("BILLED"))
		{
			test.pass("Sales order is in "+fulfilment_status.getText() +" status");
		}
		else
		{
			test.fail("Sales Order is not in Billed status");
		}
		
	}
	public void verify_sales_order_status_pending_billing(ExtentTest test)
	{
		if(fulfilment_status.getText().trim().equals("PENDING BILLING"))
		{
			test.pass("Sales order is in "+fulfilment_status.getText() +" status");
		}
		else
		{
			test.fail("Sales Order is not in pending Billing  status");
		}
		
	}
	
}
