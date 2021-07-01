package com.qa.pages;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class CustomerRefundPage extends TestUtil{
	
	@FindBy(xpath = "//a[@id='paymenttxt']")
	WebElement refundMethodTab;
	
	@FindBy(xpath = "//input[@id='ccnumber']")
	WebElement cardNumberBox;
	
	@FindBy(xpath = "//input[@id='ccname']")
	WebElement cardNameBox;
	
	@FindBy(xpath = "//input[@id='btn_multibutton_submitter']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//input[@id='ok']")
	WebElement okBtn;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement confirmationMsg;
	
	@FindBy(xpath = "//span[@id='transactionnumber_fs_lbl_uir_label']//following-sibling::span")
	WebElement transactionNumberField;
		
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	
	public CustomerRefundPage()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
		
	}
	public void saveCustomerRefund(Map<String, String> paymentData, ExtentTest test) throws InterruptedException
	{
		Thread.sleep(1500);
		eleClickable(driver, refundMethodTab, 10);
		click(refundMethodTab);
		executor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		String actualCardNumber = paymentData.get("cardNumber");
		String actualCardHolderName = paymentData.get("cardHolderName");
		if(cardNumberBox.getAttribute("value").trim().equals(actualCardNumber) && 
				cardNameBox.getAttribute("value").trim().equals(actualCardHolderName)) {
			System.out.println("Payment details in the Customer Refund record are displayed correctly");
			test.pass("Payment details in the Customer Refund record are displayed correctly");
			click(saveBtn);
			eleClickable(driver, okBtn, 30);
			click(okBtn);
			eleAvailability(driver, confirmationMsg, 40);
		    String confirmationMessage = confirmationMsg.getText().trim();
		    String tranNo = transactionNumberField.getText().trim();
		    if(confirmationMessage.equals("Transaction successfully Saved")) 
		    {
		    	System.out.println("Customer Refund '"+tranNo+"' created successfully");
		    	test.pass("Customer Refund '"+tranNo+"' created successfully");
		    }
		    else 
		    {
		    	System.out.println("Customer Refund unable to create");
		    	test.fail("Customer Refund unable to create");
		    }
		}
		else
		{
			System.out.println("Payment details in the Customer Refund record are not displayed correctly");
			test.fail("Payment details in the Customer Refund record are not displayed correctly");
		}
		
	}
}


