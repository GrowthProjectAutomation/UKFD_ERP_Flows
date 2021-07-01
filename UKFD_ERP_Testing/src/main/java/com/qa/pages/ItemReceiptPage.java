package com.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;



public class ItemReceiptPage extends TestUtil
	{
	
	@FindBy(xpath = "//input[@id='custbody_supplierdeliverynote']")
	WebElement supplierDeliveryNote;
	
	@FindBy(xpath = "//span[@id='restock1_fs']//img")
	WebElement restockCheckBox;
	
	@FindBy(xpath = "//input[@id='btn_secondarymultibutton_submitter']")
	WebElement saveButton;
	
	@FindBy(xpath = "//input[@id='btn_multibutton_submitter']")
	WebElement firstSaveBtn;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement confirmationMsg;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement recordNumber;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement recordEntityName;
	
	@FindBy(xpath = "//div[@class='uir-record-status']")
	WebElement recordStatus;
	
	@FindBy(xpath = "//span[@id='createdfrom_lbl_uir_label']//following-sibling::span")
	WebElement returnAuthorizationLink;
	
	@FindBy(xpath = "//input[@id='refund']")
	WebElement refundButton;

	
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	LoginPage loginPage;
	
	public ItemReceiptPage()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
	}
	public void saveItemReceipt(String supplier_Delivery_Note, ExtentTest test) throws InterruptedException
	{
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(2000);
		supplierDeliveryNote.sendKeys(supplier_Delivery_Note.trim());
		Thread.sleep(1000);
	    executor.executeScript("window.scrollBy(500,0)", "");
	    Thread.sleep(1000);
		//enableCheckbox(restockCheckBox);
	    restockCheckBox.click();
	    accept_alert();
		saveButton.click();
		
	}
	public void saveItemReceiptfromPO(String supplier_Delivery_Note, ExtentTest test) throws InterruptedException
	{
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(2000);
		supplierDeliveryNote.sendKeys(supplier_Delivery_Note.trim());
		Thread.sleep(1000);
		firstSaveBtn.click();
		
	}
	public void verifyItemReceiptStatus(ExtentTest test)
	{
		
		 String confirmationMessage = confirmationMsg.getText().trim();
		    String tranNo = recordNumber.getText().trim();
		    if(confirmationMessage.equals("Transaction successfully Saved"))
		    {
		    	System.out.println("Item Receipt '"+tranNo+"' created successfully");
		    	test.pass("Item Receipt '"+tranNo+"' created successfully");
		    }
		    else
		    {
		    	System.out.println("Item Receipt unable to create");
		    	test.fail("Item Receipt unable to create");
		    }
		
	}
	public void navigateToReturnAuthorization() throws InterruptedException
	{
		returnAuthorizationLink.click();
		String url=driver.getCurrentUrl();
		loginPage=new LoginPage();
		loginPage.choose_required_role("Customer Service");
		driver.navigate().to(url);
	}
	
	public void navigateToCreditMemo() throws InterruptedException
	{
		String url=driver.getCurrentUrl();
		loginPage=new LoginPage();
		loginPage.choose_required_role("Finance");
		driver.navigate().to(url);
		refundButton.click();
		
	}
	
	

}
