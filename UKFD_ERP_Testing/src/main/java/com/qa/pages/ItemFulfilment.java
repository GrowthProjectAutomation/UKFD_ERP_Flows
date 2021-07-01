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
	WebElement recordId;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement recordName;
	
	@FindBy(xpath = "//div[@class='uir-record-status']")
	WebElement recordStatus;
	
	@FindBy(xpath = "//span[@id='createdfrom_lbl_uir_label']//following-sibling::span")
	WebElement sales_order_link;
	
	@FindBy(xpath = "//span[@class='sp_SummaryField']//a")
	WebElement inventoryDetailIcon;
	
	@FindBy(xpath = "//input[@name='inpt_binnumber']")
	WebElement binDropDownarrow;
	
	@FindBy(xpath = "//input[@name='quantity_formattedValue']")
	WebElement quantityTextBox;
	
	@FindBy(xpath = "//input[@id='inventoryassignment_addedit']")
	WebElement addInventoryDetail;
	
	@FindBy(xpath = "//input[@id='secondaryok']")
	WebElement okButton;
	
	@FindBy(xpath = "//table[@id='inventoryassignment_splits']//tr[@class='uir-machine-row uir-machine-row-odd listtextnonedit uir-machine-row-focused']//td[count(//table[@id='inventoryassignment_splits']//td[@data-label='Quantity']//preceding-sibling::td)+1]")
	WebElement quantityClick;
	
	
	
	public ItemFulfilment()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
		
	}
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	
	
	
	
	
	public void item_Fulfillment( String bin, String quantity, ExtentTest test) throws InterruptedException
	{
		
		click(secondary_fulfil_button);
		eleAvailability(driver, select_shipping_status, 10);
		selectDropdownValue(select_shipping_status, dropdownList, "Shipped");
		Thread.sleep(1000);
		if(!bin.trim().equals(""))
		{
		    executor.executeScript("window.scrollBy(500,0)", "");
		    Thread.sleep(1000);
			click(inventoryDetailIcon);
			driver.switchTo().frame("childdrecord_frame");
			Thread.sleep(1000);
			eleAvailability(driver, binDropDownarrow, 10);
			selectDropdownValue(binDropDownarrow, dropdownList, bin.trim());
			notTextToBePresentInElementValue(driver, addInventoryDetail, 0, quantity);
			Thread.sleep(1500);
			action.doubleClick(quantityClick).build().perform();
			quantityTextBox.sendKeys(quantity.trim());
			addInventoryDetail.click();
			okButton.click();
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			eleAvailability(driver, select_shipping_status, 10);
			
		}
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(1500);
		save_item_fulfilment.click();
		
		
	}
	public void verify_fulfillment_status(String status,ExtentTest test)
	{
		eleAvailability(driver, recordStatus, 20);
		if(recordStatus.getText().trim().equals(status.trim()))
		{
			
			test.pass("Item Fulfilment is created with id name "+recordId.getText().trim()+" "+recordName.getText().trim());
			test.pass("Item Fulfilment is in "+recordStatus.getText().trim() +" status");
		}
		else
		{
			test.fail("Item Fulfilment is not created");
		}

	}
	public void navigate_to_sales_order_from_fulfilment()
	{
		sales_order_link.click();
		eleAvailability(driver, recordStatus, 10);
	}
	public void verify_sales_order_status(String Status,ExtentTest test)
	{
		sales_order_link.click();
		eleAvailability(driver, recordStatus, 10);
		if(recordStatus.getText().trim().equals(Status.trim()))
		{
			test.pass("Sales order is in "+recordStatus.getText() +" status");
		}
		else
		{
			test.fail("Sales Order is not in " +Status+" status");
		}
		
	}

	
}
