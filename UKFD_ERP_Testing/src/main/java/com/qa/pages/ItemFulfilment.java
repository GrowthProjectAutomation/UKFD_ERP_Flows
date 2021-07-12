package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class ItemFulfilment extends TestUtil {
	
	
	
	@FindBy(xpath = "//input[@id='item_autoenter']")
	WebElement itemSelectBtn;
	
	@FindBy(xpath = "//div[@class='dropdownDiv']//div")
	List<WebElement> dropdownList;
	
	@FindBy(xpath = "//input[@id='btn_multibutton_submitter']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement confirmationMsg;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement recordNumber;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement recordEntityName;

	@FindBy(xpath = "//span[@id='createdfrom_lbl_uir_label']//following-sibling::span//a")
	WebElement createdFromLink;
	
	@FindBy(xpath = "//iframe[@name='childdrecord_frame']")
	WebElement inventoryDetailFrame;
	
	@FindBy(xpath = "//input[@name='inpt_binnumber']")
	WebElement binDropdown;
	
	@FindBy(xpath = "//input[@name='quantity_formattedValue']")
	WebElement quantityBox;
	
	@FindBy(xpath = "//input[@name='inventoryassignment_addedit']")
	WebElement addBtn;
	
	@FindBy(xpath = "//input[@name='secondaryok']")
	WebElement okBtn;
	
	@FindBy(xpath = "//input[@id='process']")
	WebElement fulFil_button;
	
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
	
	@FindBy(xpath = "//table[@id='inventoryassignment_splits']//tr[@class='uir-machine-row uir-machine-row-odd listtextnonedit uir-machine-row-focused']//td[count(//table[@id='inventoryassignment_splits']//td[@data-label='Quantity']//preceding-sibling::td)+1]")
	WebElement quantityClick;
	
	@FindBy(xpath = "//tr[contains(@id,'itemrow')]//td[count(//tr[@id='itemheader']//div[text()='Item']//parent::td//preceding-sibling::td)+1]//a")
	List<WebElement> itemList;
	
	@FindBy(xpath = "//tr[contains(@id,'itemrow')]//td[count(//tr[@id='itemheader']//div[text()='Fulfill']//parent::td//preceding-sibling::td)+1]//img")
	List<WebElement> fulfillCheckboxList;
	
	
	
	
	public ItemFulfilment()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
		
	}
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	
	public void fulfillOrder(String fulfillItem, String fulfillQuantity, String bin, ExtentTest test) throws InterruptedException {
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		eleFocussed(itemSelectBtn);
		click(secondary_fulfil_button);
		eleAvailability(driver, select_shipping_status, 10);
		Thread.sleep(1500);
		selectDropdownValue(select_shipping_status, dropdownList, "Shipped");
		eleattributeContainsText(driver, select_shipping_status, 20, "value", "Shipped");
		for (int i = 0; i < fulfillCheckboxList.size(); i++) 
		{
			disableCheckbox(fulfillCheckboxList.get(i));
		}
		if(fulfillItem.contains(",")) 
		{
			String[] fulfillItemsList = fulfillItem.split(",");
			String[] quantityList = fulfillQuantity.split(",");
			String[] binList = bin.split(",");
			for (int i = 0; i < fulfillItemsList.length; i++)
			{
				if(bin.contains(","))
				{
				fulfillItem(fulfillItemsList[i], quantityList[i], binList[i]);
				}
				else
				{
					fulfillItem(fulfillItemsList[i], quantityList[i], bin.trim());
				}
				
			}
		}
		else
		{
			fulfillItem(fulfillItem, fulfillQuantity, bin);
		}
		action.moveToElement(saveBtn).build().perform();
		Thread.sleep(1500);
		je.executeScript("arguments[0].click();", saveBtn);
		while(isAlertPresent_()==true)
			driver.switchTo().alert().accept();
		eleAvailability(driver, confirmationMsg, 40);
	    String confirmationMessage = confirmationMsg.getText().trim();
	    String tranNo = recordNumber.getText().trim();
	    String tranStatus = recordStatus.getText().trim();
	    if(confirmationMessage.equals("Transaction successfully Saved")) 
	    {
	    	System.out.println("Item Fulfillment '"+tranNo+"' created successfully with "+tranStatus+" status");
	    	test.pass("Item Fulfillment '"+tranNo+"' created successfully with "+tranStatus+" status");
	    }
	    else 
	    {
	    	System.out.println("Item Fulfillment unable to create");
	    	test.fail("Item Fulfillment unable to create");
	    }
	}

	public void fulfillItem(String fulfillItem, String fulfillQuantity, String bin) throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		String rowId = "";
		for (int i = 0; i < itemList.size(); i++) 
		{
			if(fulfillItem.trim().contains(itemList.get(i).getText().trim())) 
			{
				WebElement parentTr = (WebElement)je.executeScript("return arguments[0].parentNode.parentNode", itemList.get(i));
				rowId = parentTr.getAttribute("id");
			}
		}
		System.out.println(rowId);
		WebElement fulfillCheckbox = driver.findElement(By.xpath("//tr[@id='"+
				rowId+"']//input[contains(@id,'itemreceive')]//following-sibling::img"));
		WebElement fulfillQuantityBox = driver.findElement(By.xpath("//tr[@id='"+
				rowId+"']//span[contains(@id,'item_quantity')]//input[@type='text']"));
		
		enableCheckbox(fulfillCheckbox);
		click(fulfillQuantityBox);
		if(!fulfillQuantityBox.getAttribute("value").trim().equals(fulfillQuantity)) 
		{
			Thread.sleep(1500);
			clearValueEnterText(fulfillQuantityBox, fulfillQuantity);
			WebElement inventoryDetailBtn = driver.findElement(By.xpath("//tr[@id='"+
					rowId+"']//a[contains(@class,'inventorydetail_helper_popup_')]"));
			
			click(inventoryDetailBtn);
			driver.switchTo().frame(inventoryDetailFrame);
			click(okBtn);
			driver.switchTo().defaultContent();
			Thread.sleep(1500);
		}
		
		System.out.println("Bin is: "+bin);
		if(!bin.trim().isBlank())
		{
			WebElement inventoryDetailBtn = driver.findElement(By.xpath("//tr[@id='"+
					rowId+"']//a[contains(@class,'i_inventorydetail')]"));
			
			if(inventoryDetailBtn.getAttribute("class").contains("i_inventorydetailneeded"))
			{
			WebElement inventoryDetailBtn_click = driver.findElement(By.xpath("//tr[@id='"+
					rowId+"']//a[contains(@class,'i_inventorydetailneeded')]"));
			click(inventoryDetailBtn_click);
			driver.switchTo().frame("childdrecord_frame");
			Thread.sleep(1000);
			eleAvailability(driver, binDropDownarrow, 10);
			selectDropdownValue(binDropDownarrow, dropdownList, bin.trim());
			Thread.sleep(1500);
			action.doubleClick(quantityClick).build().perform();
			quantityTextBox.sendKeys(fulfillQuantity.trim());
			addInventoryDetail.click();
			okBtn.click();
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			}
			else
			{
				System.out.println("Inventory Detail is not needed");
			}
		}
		Thread.sleep(3000);
	}
	
	
	
//	public void item_Fulfillment( String bin, String quantity, String item_Name, ExtentTest test) throws InterruptedException
//	{
//		
//		
//		click(secondary_fulfil_button);
//		eleAvailability(driver, select_shipping_status, 10);
//		selectDropdownValue(select_shipping_status, dropdownList, "Shipped");
//		Thread.sleep(1000);
//		
//		//configuring inventory
//		if(!bin.trim().equals(""))
//		{
//			
//		    executor.executeScript("window.scrollBy(500,0)", "");
//		    Thread.sleep(1000);
//		    
//		    if(item_Name.contains(","))
//		    {
//		    	String[] items=item_Name.split(",");
//		    	String [] bins=bin.split(",");
//		    	String [] quantitys=quantity.split(",");
//		    	
//		    	for(int i=0;i<items.length;i++)
//		    	{
//			    	WebElement inventory_icon=driver.findElement(By.xpath("//a[text()='"+items[i]+"']//following::td[count(//td[@data-label='Inventory Detail']//preceding-sibling::td)-1]//span[@id='item_inventorydetail1_fs']"));
//			    	configuring_inventory(inventory_icon,items[i],bins[i],quantitys[i]);
//		    	}
//		    }
//		    else
//		    {
//		    	WebElement inventory_icon=driver.findElement(By.xpath("	//a[text()='"+item_Name+"']//following::td[count(//td[@data-label='Inventory Detail']//preceding-sibling::td)-1]//span[@id='item_inventorydetail1_fs']"));
//		    	configuring_inventory(addInventoryDetail, item_Name, bin, quantity);
//		    }
//		}
//		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
//		Thread.sleep(1500);
//		save_item_fulfilment.click();
//		
//		
//	}
//		
//		public void configuring_inventory(WebElement inventoryIcon,String item,String bin,String quantity) throws InterruptedException
//		{
//			click(inventoryIcon);
//			driver.switchTo().frame("childdrecord_frame");
//			Thread.sleep(1000);
//			eleAvailability(driver, binDropDownarrow, 10);
//			selectDropdownValue(binDropDownarrow, dropdownList, bin.trim());
//			notTextToBePresentInElementValue(driver, inventoryIcon, 0, quantity);
//			Thread.sleep(1500);
//			action.doubleClick(quantityClick).build().perform();
//			quantityTextBox.sendKeys(quantity.trim());
//			addInventoryDetail.click();
//			okButton.click();
//			driver.switchTo().defaultContent();
//			Thread.sleep(1000);
//			eleAvailability(driver, select_shipping_status, 10);
//		}
//		

	public void navigateToSoFromFulfillment()
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
	//a[text()='CAR-MAD-OSC-4']//following::td[count(//td[@data-label='Inventory Detail']//preceding-sibling::td)-1]//span[@id='item_inventorydetail1_fs']
	
}
