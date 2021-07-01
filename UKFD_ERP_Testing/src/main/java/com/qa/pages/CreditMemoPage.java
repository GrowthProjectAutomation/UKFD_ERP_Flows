package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class CreditMemoPage extends TestUtil {
	
	@FindBy(xpath = "//td[@id='spn_multibutton_submitter']")
	WebElement submitCreditMemo;
	
	@FindBy(xpath = "//input[@id='refund']")
	WebElement refundButton;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement confirmationMsg;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement recordNumber;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement recordEntityName;
	
	@FindBy(xpath = "//div[@class='uir-record-status']")
	WebElement recordStatus;
	
	
	
	public CreditMemoPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void saveCreditMemo()
	{
		
		submitCreditMemo.click();
	}
	public void navigateToCustomerRefund()
	{
		eleAvailability(driver, refundButton, 15);
		refundButton.click();
	}
	public void verifyCreditMemoStatus(ExtentTest test)
	{
		
		 String confirmationMessage = confirmationMsg.getText().trim();
		    String tranNo = recordNumber.getText().trim();
		    if(confirmationMessage.equals("Transaction successfully Saved"))
		    {
		    	System.out.println("Credit Memo '"+tranNo+"' created successfully");
		    	test.pass("Credit Memo '"+tranNo+"' created successfully");
		    }
		    else
		    {
		    	System.out.println("Credit Memo unable to create");
		    	test.fail("Credit Memo unable to create");
		    }
		
	}
	
	

}
