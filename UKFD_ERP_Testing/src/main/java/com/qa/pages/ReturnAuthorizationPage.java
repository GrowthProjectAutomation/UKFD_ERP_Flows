package com.qa.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class ReturnAuthorizationPage extends TestUtil{
	@FindBy(xpath = "//input[@name='inpt_custbody_returnreason']")
	WebElement returnReasonDropdown;
	
	@FindBy(xpath = "//div[@class='dropdownDiv']//div")
	List<WebElement> dropdownList;
	
	@FindBy(xpath = "//input[@name='inpt_custbody_returnresponsibility']")
	WebElement returnResponsibilityDropdown;
	
	@FindBy(xpath = "//textarea[@name='custbody_returnnotes']")
	WebElement returnNotesBox;
	
	@FindBy(xpath = "//input[@name='inpt_custbodyreturn_sub_type']")
	WebElement returnReasonSubtypeDropdown;
	
	@FindBy(xpath = "//input[@name='inpt_custbody_return_reason_3']")
	WebElement returnReason3Dropdown;
	
	@FindBy(xpath = "//input[@name='inpt_custbody_return_reason_4']")
	WebElement returnReason4Dropdown;
	
	@FindBy(xpath = "//input[@name='custbody_returndate']")
	WebElement returnDateBox;
	
	@FindBy(xpath = "//input[@name='custbody_refundamount_formattedValue']")
	WebElement refundAmountBox;
	
	@FindBy(xpath = "//textarea[@name='custbody_instruction']")
	WebElement instructionsBox;
	
	@FindBy(xpath = "//span[@id='total_val']")
	WebElement totalAmountFieldValue;
	
	@FindBy(xpath = "//input[@id='btn_multibutton_submitter']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement confirmationMsg;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement recordNumber;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement recordEntityName;
	
	@FindBy(xpath = "//div[@class='uir-record-status']")
	WebElement recordStatus;
	
	@FindBy(xpath = "//input[@id='refund']")
	WebElement refundBtn;
	
	@FindBy(xpath = "//input[@id='closeremaining']")
	WebElement closeBtn;
	
	@FindBy(xpath = "//a[@id='rlrcdstabtxt']")
	WebElement relatedRecordsTab;
	
	@FindBy(xpath = "//td[text()='Credit Memo']//parent::tr//a")
	WebElement creditMemoLink;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[contains(@id,'item_row_')]//td[count(//tr[@id='item_headerrow']//td[@data-label='Qty / Length']//preceding-sibling::td)+1]")
	WebElement quantityClick;
	
	@FindBy(xpath = "//input[@name='quantity_formattedValue']")
	WebElement quantityInputBox;
	
	@FindBy(xpath = "//input[@id='item_addedit']")
	WebElement addItemButton;
	
	@FindBy(xpath = "//input[@name='receive']")
	WebElement itemReceiveButton;
	
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	LoginPage loginPage;
	
	public ReturnAuthorizationPage()
	{
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	
	public void saveReturnOrder(String quantity,String returnQuantity,String returnReason, String returnResponsibility,String returnNotes, String returnReasonSubType,String returnReason3,String returnReason4,String supplyNote,ExtentTest test) throws InterruptedException, ParseException {
		eleClickable(driver, returnReasonDropdown, 10);
		selectDropdownValue(returnReasonDropdown, dropdownList, returnReason);
		selectDropdownValue(returnResponsibilityDropdown, dropdownList, returnResponsibility);
		returnNotesBox.sendKeys(returnNotes);
		Thread.sleep(2500);
		selectDropdownValue(returnReasonSubtypeDropdown, dropdownList, returnReasonSubType);
		selectDropdownValue(returnReason3Dropdown, dropdownList, returnReason3.trim());
		textToBePresentInElementValue(driver, returnReason3Dropdown,20, returnReason3.trim());
		Thread.sleep(3000);
		selectDropdownValue(returnReason4Dropdown, dropdownList, returnReason4.trim());
		action.moveToElement(returnDateBox).sendKeys(returnDateBox, required_date("0")).build().perform();
		action.moveToElement(quantityClick).build().perform();
		Thread.sleep(1000);
		quantityClick.click();
		eleAvailability(driver, quantityInputBox, 10);
		quantityInputBox.sendKeys(returnQuantity);
		addItemButton.click();
		Thread.sleep(1000);
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(1000);
		String total_amount=totalAmountFieldValue.getText().trim();
		action.moveToElement(refundAmountBox).sendKeys(refundAmountBox, totalAmountFieldValue.getText()).build().perform();
		action.moveToElement(instructionsBox).sendKeys(instructionsBox, totalAmountFieldValue.getText()).build().perform();
		click(saveBtn);
		eleAvailability(driver, confirmationMsg, 40);
	    String confirmationMessage = confirmationMsg.getText().trim();
	    String tranNo = recordNumber.getText().trim();
	    if(confirmationMessage.equals("Transaction successfully Saved"))
	    {
	    	System.out.println("Return Authorisation '"+tranNo+"' created successfully");
	    	test.pass("Return Authorisation '"+tranNo+"' created successfully");
	    }
	    else 
	    {
	    	System.out.println("Return Authorisation unable to create");
	    	test.fail("Return Authorisation unable to create");
	    }
	}
	public void navigateToItemReceipt(String role) throws InterruptedException
	{
		
		String url=driver.getCurrentUrl();
		loginPage=new LoginPage();
		loginPage.choose_required_role(role.trim());
		driver.navigate().to(url);
		eleAvailability(driver, itemReceiveButton, 20);
		itemReceiveButton.click();
		
	}
	
	
	
	
	

}
