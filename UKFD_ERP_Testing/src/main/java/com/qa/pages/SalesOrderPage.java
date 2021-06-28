package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
import com.qa.util.TestUtil;

public class SalesOrderPage extends TestUtil {
	
	@FindBy(xpath = "//input[@name='inpt_customform']")
	WebElement formDropdown;
	
	@FindBy(xpath = "//input[@name='trandate']")
	WebElement dateBox;
	
	@FindBy(xpath = "//div[@class='dropdownDiv']//div")
	List<WebElement> dropdownList;
	
	@FindBy(xpath = "//input[@id='entity_display']")
	WebElement customerBox;
	
	@FindBy(xpath = "//span[@id='parent_actionbuttons_entity_fs']//a[2]")
	WebElement customerArrowBtn;
	
	@FindBy(xpath = "//div[@id='entity_fs_tooltipMenu']//a[@id='entity_popup_list']")
	WebElement customerListBtn;
	
	@FindBy(xpath = "//div[@id='popup_outerdiv']//input[@id='st']")
	WebElement searchBox;
	
	@FindBy(xpath = "//div[@id='popup_outerdiv']//input[@id='Search']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//div[@id='popup_outerdiv']//div[@id='inner_popup_div']//table//tr//td//following-sibling::td//a")
	List<WebElement> customerSearchList;
	
	@FindBy(xpath = "//textarea[@name='custbody_instruction']")
	WebElement delivery_instructions_textarea;
		
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
	
	@FindBy(xpath = "//input[@name='inpt_terms']")
	WebElement terms_dropdown;
	
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
	List<WebElement> subject;
	
	@FindBy(xpath = "//input[@id='refreshmessages']")
	WebElement refresh_communication_msgs;
	
	@FindBy(xpath = "//table[@id='messages__tab']//tr[contains(@id,'messagesrow')]//td[count(//table[@id='messages__tab']//td[@data-label='Attachments']//preceding-sibling::td)+1]")
	List<WebElement> attached_email;
	
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
	WebElement fulfilment_id;
	
	@FindBy(xpath = "//div[@class='uir-record-name']")
	WebElement fulfilment_name;
	
	@FindBy(xpath = "//div[@class='uir-record-status']")
	WebElement fulfilment_status;
	
	@FindBy(xpath = "//input[@id='newmessage']")
	WebElement email_button;
	
	@FindBy(xpath = "//a[@id='attachmentstxt']")
	WebElement attachment_tab_in_email;
	
	@FindBy(xpath = "//a[@id='mediaitem_popup_muli']//following-sibling::a")
	WebElement attach_file_arrow;
	
	@FindBy(xpath = "//a[@id='mediaitem_popup_new']")
	WebElement new_icon;
	
	@FindBy(xpath = "//span[@id='mediafile_fs_lbl_uir_label']//following-sibling::span//input")
	WebElement choose_file;
	
	@FindBy(xpath = "//input[@id='submitter']")
	WebElement save_selected_file;
	
	@FindBy(xpath = "//input[@id='mediaitem_addedit']")
	WebElement add_file;
	
	@FindBy(xpath = "//a[@id='messagestxt']")
	WebElement message_tab;
	
	@FindBy(xpath = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	WebElement body_message;
	
	@FindBy(xpath = "//input[@name='secondarysubmitter']")
	WebElement merge_and_send;
	
	@FindBy(xpath = "//input[@id='custpage_printproforma']")
	WebElement print_pro_forma;
	
	
	
	
	
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	String parentWindow;
	char ch='"';
	LoginPage loginPage;
	
	
	
	public SalesOrderPage()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);

	}
	
	public void enter_SO_details(String formName,String delivery_Instructions, String shipping_Method,String expiry_Date, String customer_Lastname, ExtentTest test) throws Exception
	{
		if(!formDropdown.getAttribute("value").trim().equals(formName))
			selectDropdownValue(formDropdown, dropdownList, formName);
		eleFocussed(customerBox);
		eleAttributeToBeNotEmpty(driver, dateBox, 15, "value");
		delivery_instructions_textarea.sendKeys(delivery_Instructions.trim());
		calculate_shipping_button.click();
		eleAvailability(driver, shipping_ok_button, 10);
		driver.findElement(By.xpath("//div[@class='x-grid3-scroller']//div[contains(text(),'"+shipping_Method.trim()+"')]")).click();
		eleClickable(driver, shipping_ok_button, 10);
		shipping_ok_button.click();
		Thread.sleep(1000);
	}
	public void provide_terms() throws InterruptedException
	{
		executor.executeScript("arguments[0].scrollIntoView(true);", location);
		eleAvailability(driver, billing_tab, 20);
		billing_tab.click();
		eleAvailability(driver, terms_dropdown, 20);
		selectDropdownValue(terms_dropdown, dropdownList, "");
		executor.executeScript("arguments[0].scrollIntoView(true);", second_sales_order_save_button);
		Thread.sleep(1500);
		second_sales_order_save_button.click();
		

		
	}
	public void payment_details(String payment_Method, String credit_Card_Number, String security_Code, String expiry_Date, String customer_Firstname, String customer_Lastname,ExtentTest test) throws InterruptedException
	{
		executor.executeScript("arguments[0].scrollIntoView(true);", location);
		Thread.sleep(1000);
		billing_tab.click();
		select_payment_method.sendKeys(payment_Method.trim());
		executor.executeScript("arguments[0].scrollIntoView(true);", select_credit_card);
		Thread.sleep(500);
		selectDropdownValue(select_credit_card, select_credit_card_from_list, "- New -");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    eleAvailability(driver,ccnumber_textbox, 10);
		ccnumber_textbox.sendKeys(credit_Card_Number.trim());
		expiry_date_textbox.sendKeys(expiry_Date.trim());
		select_payment_method.sendKeys(payment_Method.trim());
		save_card_details.click();
	    driver.switchTo().window(tabs.get(0));	
	    textToBePresentInElementValue(driver,ccname,15,customer_Firstname+" "+customer_Lastname);
		security_code_textbox.sendKeys(security_Code.trim());
		Thread.sleep(500);
		second_sales_order_save_button.click();
	}
	public void salesOrderApproval(ExtentTest test) throws InterruptedException
	{
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(1500);
		eleAvailability(driver, sales_order_approve_button, 20);
		sales_order_approve_button.click();
		eleAvailability(driver, sales_order_confirmation_message, 30);
	
		if(sales_order_confirmation_message.getText().trim().equals("Sales Order successfully Approved")&& sales_order_status.getText().trim().equals("PENDING FULFILLMENT"))
		{
			System.out.println("Confirmation banner is displayed with pending fulfillment status");
			test.pass("Confirmation Banner of Sales Order creation is displayed with "+ch+" "+ "Pending Fulfillment "+ch+" "+" Status");
		}
		else
		{
			test.pass("Confirmation Banner of Sales Order creation is not displayed with Pending Fulfillment Status");

		}
	}
	public void verifyCashSale(ExtentTest test) throws InterruptedException
	{
		executor.executeScript("arguments[0].scrollIntoView(true);", related_records_tab);
		Thread.sleep(1500);
		eleAvailability(driver, related_records_tab, 10);
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
	
	}
	public void verifyEmail(ExtentTest test)
	{
		communication_tab.click();
		executor.executeScript("arguments[0].scrollIntoView(true);", subject.get(0));
		for(int i=0;i<subject.size();i++)
		{
		if(subject.get(i).getText().trim().equals("Thanks for your order!"))
		{
			System.out.println("Order Processing Message is verified");
			test.pass("Order Processing Message Email "+ch+" "+subject.get(i).getText().trim()+" "+ch+" is displayed");
			break;
		}
		else
		{
			test.fail("Order Processing Email is not displayed");
			break;
		}
		}
		for(int i=0;i<subject.size();i++)
		{
		if(subject.get(i).getText().trim().equals("Your order has been confirmed"))
		{
			System.out.println("Order Confirmation is verified");
			test.pass("Order Confirmation Message Email "+ch+" "+subject.get(i).getText().trim()+" "+ch+" is displayed");
			break;
		}
		else
		{
			test.fail("Order Confirmation Email is not displayed");
			break;
		}
		}
		
		
	}
	public void auto_Commit_stock(String quantity, ExtentTest test) throws InterruptedException
	{
		
		String sales_order_url=driver.getCurrentUrl();
		loginPage=new LoginPage();
		loginPage.choose_required_role("Fulfilment");
		Thread.sleep(1000);
		driver.navigate().to(sales_order_url);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].scrollIntoView(true);", related_records_tab);
		waitUntilScriptIsScheduled(committed_quantity,"0",2000);
		if(committed_quantity.getText().trim().equals(quantity.trim()))
		{
			test.pass("Stock is auto committed");
		}
		else
		{
			test.fail("Stock is not auto committed");
		}
	}	
	
	public void print_pro_froma_invoice(ExtentTest test) throws AWTException, InterruptedException
	{
		eleAvailability(driver, print_pro_forma, 20);
		print_pro_forma.click();
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		Thread.sleep(3000);
		driver.switchTo().window(tabs.get(1));
		Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    Thread.sleep(5000);
		StringSelection sel = new StringSelection("C:\\Users\\Sindhuja\\Desktop\\profromatest16.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);	   
		robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(2000);     
	    robot.keyPress(KeyEvent.VK_TAB);  
	    Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER); 
	    driver.close();
	    driver.switchTo().window(tabs.get(0));
	    Thread.sleep(1000);
	    test.pass("Pro-Forma Invoice is Printed Sucessfully");
	    
	}
	public void email_pro_forma(ExtentTest test) throws InterruptedException, AWTException
	{
		
		executor.executeScript("window.scrollTo(0, 0)");
		eleAvailability(driver, communication_tab, 20);
	    communication_tab.click();
	    eleAvailability(driver, email_button, 10);
	    email_button.click();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		attachment_tab_in_email.click();
		Thread.sleep(1000);
		action.moveToElement(attach_file_arrow).build().perform();
		new_icon.click();
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		Thread.sleep(2000);
		System.out.println(tabs1.size());
		driver.switchTo().window(tabs1.get(2));
		Thread.sleep(2000);
		System.out.println("Title is: "+driver.getTitle());
		System.out.println(choose_file.isDisplayed());
		action.moveToElement(choose_file).click().build().perform();
		StringSelection sel = new StringSelection("C:\\Users\\Sindhuja\\Desktop\\profromatest16.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
		System.out.println("selection" + sel);
		Robot robot = new Robot();
		Thread.sleep(1000);
		// Press Enter

		robot.keyPress(KeyEvent.VK_ENTER);
		// Release Enter
		robot.keyRelease(KeyEvent.VK_ENTER);
		// Press CTRL+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		// Release CTRL+V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		accept_alert();
	    save_selected_file.click();
	    driver.switchTo().window(tabs.get(1));
	    Thread.sleep(1000);
		accept_alert();
	    action.moveToElement(add_file).build().perform();
	    add_file.click();
	    action.moveToElement(merge_and_send).build().perform();
	    merge_and_send.click();
	    accept_alert();
		
	}
	public void verify_profromaemail(ExtentTest test) throws InterruptedException
	{
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		Thread.sleep(2000);
		driver.switchTo().window(tabs1.get(0));
		Thread.sleep(1000);
		refresh_communication_msgs.click();
		Thread.sleep(1000);
		//executor.executeScript("arguments[0].scrollIntoView(true);", attached_email.get(0));
		//eleAvailability(driver, attached_email.get(0), 10);
		boolean emailed=false;
		for(int i=0;i<attached_email.size();i++)
		{
			if(attached_email.get(i).getText().trim().contains("profromatest16"))
			{
				emailed=true;
				System.out.println("Pro forma mail is attached and sent successfully");
				test.pass("Pro-Forma Invoice is Successfully Emailed to the customer");
				break;
			}
			
		}
		if(emailed==false)
		{
			test.fail("Pro-Forma Invoice is not emailed to the customer");
		}
	}
		
	}


	 
	
	
	
	
	

  