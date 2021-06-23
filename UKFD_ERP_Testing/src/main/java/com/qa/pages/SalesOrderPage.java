package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestBase;

public class SalesOrderPage extends TestBase {
	
	@FindBy(xpath = "//textarea[@name='custbody_instruction']")
	WebElement delivery_instructions_textarea;
	
	//div[contains(text(),'Light parcel Menzies')]
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement shipping_ok_button;
	
	@FindBy(xpath = "//input[@id='custpage_shippingext']")
	WebElement calculate_shipping_button;
	
	@FindBy(xpath = "//td[@id='tdbody_submitter']")
	WebElement first_save_button;
	
	@FindBy(xpath = "//td[@id='tdbody_secondarysubmitter']")
	WebElement second_save_button;
	
	@FindBy(xpath = "//input[@name='approve']")
	WebElement sales_order_approve_button;
	
	@FindBy(xpath = "//input[@name='inpt_paymentmethod']")
	WebElement select_payment_method;
	
	@FindBy(xpath = "//input[@name='inpt_creditcard']")
	WebElement select_credit_card;
	
	@FindBy(xpath = "//div[@class='dropdownDiv']//div")
	List<WebElement> select_credit_card_from_list;
	
	@FindBy(xpath = "//input[@name='ccnumber']")
	WebElement ccnumber_textbox;
	
	@FindBy(xpath = "//input[@name='ccexpiredate']")
	WebElement expiry_date_textbox;
	
	@FindBy(xpath = "//input[@id='ccsecuritycode']")
	WebElement security_code_textbox;
	
	@FindBy(xpath = "//input[@name='inpt_location']")
	WebElement location;
	
	@FindBy(xpath = "//a[@id='billingtabtxt']")
	WebElement billing_tab;
	
	@FindBy(xpath = "//td[@id='tdbody_submitter']")
	WebElement save_card_details;
	
	@FindBy(xpath = "//td[@id='tdbody_secondarysubmitter']")
	WebElement second_sales_order_save_button;
	
	@FindBy(xpath = "//input[@id='ccname']")
	WebElement ccname;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement sales_order_confirmation_message;
	
	@FindBy(xpath = "//div[@class='uir-record-status']")
	WebElement sales_order_status;
	
	@FindBy(xpath = "//a[@id='rlrcdstabtxt']")
	WebElement related_records_tab;
	
	@FindBy(xpath = "//tr[contains(@id,'linksrow')]//td[count(//table[@id='links_splits']//td[@data-label='Type']//preceding-sibling::td)+1]")
	WebElement transcation_type;
	
	@FindBy(xpath = "//tr[contains(@id,'linksrow')]//td[count(//table[@id='links_splits']//td[@data-label='Number']//preceding-sibling::td)+1]")
	WebElement cash_sale_number;
	
	@FindBy(xpath = "//a[@id='cmmnctntabtxt']")
	WebElement communication_tab;
	
	@FindBy(xpath = "//table[@id='messages__tab']//tr[contains(@id,'messagesrow')]//td[count(//table[@id='messages__tab']//td[@data-label='Type']//preceding-sibling::td)]")
	WebElement subject;
	
	Actions action=new Actions(driver);
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	WebDriverWait wait=new WebDriverWait(driver, 20);
	String parentWindow;
	
	
	
	public SalesOrderPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enter_SO_details(String delivery_Instructions, String shipping_Method, String payment_Method, String credit_Card_Number, String security_Code, String expiry_Date, String customer_Firstname, String customer_Lastname, ExtentTest test) throws Exception
	{
		eleClickable(driver, delivery_instructions_textarea, 20);
		delivery_instructions_textarea.sendKeys(delivery_Instructions.trim());
		calculate_shipping_button.click();
		eleAvailability(driver, shipping_ok_button, 10);
		driver.findElement(By.xpath("//div[@class='x-grid3-scroller']//div[contains(text(),'"+shipping_Method.trim()+"')]")).click();
		eleClickable(driver, shipping_ok_button, 10);
		shipping_ok_button.click();
		executor.executeScript("arguments[0].scrollIntoView(true);", location);
		Thread.sleep(1000);
		billing_tab.click();
		select_payment_method.sendKeys(payment_Method.trim());
		executor.executeScript("arguments[0].scrollIntoView(true);", select_credit_card);
		Thread.sleep(500);
		select_credit_card.click();
		for(int i=0;i<select_credit_card_from_list.size();i++)
		{
			if(select_credit_card_from_list.get(i).getText().trim().equals("- New -"))
			{
				select_credit_card_from_list.get(i).click();
				break;
			}
		}
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    eleAvailability(driver,ccnumber_textbox, 10);
		ccnumber_textbox.sendKeys(credit_Card_Number.trim());
		expiry_date_textbox.sendKeys(expiry_Date.trim());
		select_payment_method.sendKeys(payment_Method.trim());
		save_card_details.click();
	    driver.switchTo().window(tabs.get(0));				
		wait.until(ExpectedConditions.textToBePresentInElementValue(ccname, customer_Firstname+" "+customer_Lastname));
		security_code_textbox.sendKeys(security_Code.trim());
		Thread.sleep(500);
		second_sales_order_save_button.click();
		sales_order_approve_button.click();
		eleAvailability(driver, By.xpath("//input[@name='closeremaining']"), 20);
		//System.out.println(sales_order_confirmation_message.getText().trim());
		//System.out.println(sales_order_status.getText().trim());
		char ch='"';
		if(sales_order_confirmation_message.getText().trim().equals("Sales Order successfully Approved")&& sales_order_status.getText().trim().equals("PENDING FULFILLMENT"))
		{
			System.out.println("Confirmation banner is displayed with pending fulfillment status");
			test.pass("Confirmation Banner of Sales Order creation is displayed with "+ch+" "+ "Pending Fulfillment "+ch+" "+" Status");
		}
		else
		{
			test.pass("Confirmation Banner of Sales Order creation is not displayed with Pending Fulfillment Status");

		}
		executor.executeScript("arguments[0].scrollIntoView(true);", related_records_tab);
		Thread.sleep(1500);
		related_records_tab.click();
		if(transcation_type.getText().trim().equals("Cash Sale"))
		{
			System.out.println("Cash sale is created with "+ch+" "+cash_sale_number.getText().trim()+ch+" "+" number");
			test.pass("Cash sale is created with "+ch+" "+cash_sale_number.getText().trim()+ch+" "+" number");

		}
		else
		{
			test.fail("Cash Sale is not Created");
		}
		communication_tab.click();
		executor.executeScript("arguments[0].scrollIntoView(true);", subject);
		if(subject.getText().trim().equals("Thanks for your order!"))
		{
			System.out.println("Email Confirmation is verified");
			test.pass("Email Confirmation Message "+ch+" "+subject.getText().trim()+" "+ch+" is displayed");
		}
		else
		{
			test.fail("Email Confirmation is not displayed");
		}
		

		
		
	}
	 
	
	
	
	
	
}
  