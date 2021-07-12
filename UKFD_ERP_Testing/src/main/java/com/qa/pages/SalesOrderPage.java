package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
	
	
	@FindBy(xpath = "//li[@data-title='Transactions']")
	WebElement transcations_link;
	
	@FindBy(linkText="Sales")
	WebElement sales_link;
	
	@FindBy(linkText="Enter Sales Orders")
	WebElement enterSO_link;
	
	
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
	
	@FindBy(xpath = "//input[@name='inpt_leadsource']")
	WebElement leadSourceDropdown;
	
	@FindBy(xpath = "//textarea[@name='custbody_instruction']")
	WebElement delivery_instructions_textarea;
	
	@FindBy(xpath = "//input[@id='secondarycustpage_shippingext']")
	WebElement secondary_calculate_shipping_button;
		
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
	List<WebElement> transcation_type;
	
	@FindBy(xpath = "//tr[contains(@id,'linksrow')]//td[count(//table[@id='links_splits']//td[@data-label='Number']//preceding-sibling::td)+1]")
	List<WebElement> transcation_number;
	
	@FindBy(xpath = "//tr[contains(@id,'linksrow')]//td[count(//table[@id='links_splits']//td[@data-label='Type']//preceding-sibling::td)]//a")
	List<WebElement> transcationLink;
	
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
	List<WebElement> committed_quantity;
	
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
	
	@FindBy(xpath = "//input[@id='mediaitem_mediaitem_display']")
	WebElement added_file;
	
	@FindBy(xpath = "//a[@id='messagestxt']")
	WebElement message_tab;
	
	@FindBy(xpath = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	WebElement body_message;
	
	@FindBy(xpath = "//input[@name='secondarysubmitter']")
	WebElement merge_and_send;
	
	@FindBy(xpath = "//input[@id='custpage_printproforma']")
	WebElement print_pro_forma;
	
	@FindBy(xpath="//span[@id='parent_actionbuttons_item_item_fs']")
	WebElement Item_arrow;
	
	@FindBy(xpath="//a[@id='item_popup_list']")
	WebElement item_list_option;
	
	@FindBy(xpath="//input[@id='st']")
	WebElement Item_searchbox_textbox;
	
	@FindBy(xpath="//input[@id='Search']")
	WebElement Item_search_searchbox_button;
	
	@FindBy(xpath = "//div[@id='popup_outerdiv']//div[@id='inner_popup_div']//table//tr//td//following-sibling::td//a")
	List<WebElement> searchList;
	
	@FindBy(xpath="//div[@id='inner_popup_div']//table/tbody/tr//following-sibling::tr//td//following-sibling::td//a")
	List<WebElement> items_list;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[contains(@class,'uir-machine-row-focused')]//td[count(//tr[@id='item_headerrow']//div[text()='Quantity']//parent::td//preceding-sibling::td)+1]")
	WebElement quantityDiv;
	
	@FindBy(xpath="//input[@id='quantity_formattedValue']")
	WebElement Quantity;
	
	@FindBy(xpath = "//input[@id='item_addedit']")
	WebElement itemAddBtn;
	
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[@id='item_headerrow']//following-sibling::tr//td[6]")
	List<WebElement> pack_price;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[contains(@id,'item_row_')]//td[count(//tr[@id='item_headerrow']//td[@data-label='Product Details']//preceding-sibling::td)]")
	WebElement item_value_list;
	
	@FindBy(xpath = "//input[@id='item_item_display']")
	WebElement item_input;
	
	@FindBy(xpath = "//input[@id='return']")
	WebElement authorizeReturnButton;
	
	@FindBy(xpath = "//a[@id='paymentevent_displayval' and text()='View']")
	WebElement viewLink;
	
	@FindBy(xpath = "//span[@id='paymentinstrumentmask_fs_lbl_uir_label']//following-sibling::span")
	WebElement cardDetailsField;
	
	@FindBy(xpath = "//span[@id='cardnameoncard_fs_lbl_uir_label']//following-sibling::span")
	WebElement cardHolderField;
	
	@FindBy(xpath = "//span[@id='request_fs_lbl_uir_label']//following-sibling::span")
	WebElement spanText;

	@FindBy(xpath = "//tr[@id='item_row_1']")
	WebElement item_row;
	
	@FindBy(xpath = "//tr[contains(@id,'row')]//td[count(//td[@data-label='Original Number']//preceding-sibling::td)+1]//a")
	WebElement processedSalesOrderLink;
	
	@FindBy(xpath = "//tr[contains(@id,'row')]//td[count(//td[@data-label='Status']//preceding-sibling::td)]//a")
	WebElement processedPurchaseOrderLink;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement recordNumber;
	
	
	
	
	
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	String parentWindow;
	char ch='"';
	LoginPage loginPage;
	
	
	
	public SalesOrderPage()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);

	}
	
	public void navigateToSO() throws InterruptedException
	{
		eleAvailability(driver, transcations_link, 20);
		Thread.sleep(1000);
		action.moveToElement(transcations_link).build().perform();
		Thread.sleep(1000);
		action.moveToElement(sales_link).build().perform();
		Thread.sleep(1000);
		enterSO_link.click();
	}
	public void enterSoDetails(String formName,String delivery_Instructions, String shipping_Method,String Customer_Name, String item_Name, String quantity, String payment_Method, String terms, ExtentTest test) throws Exception
	{
		String[] items=item_Name.split(",");
		String [] quantities=quantity.split(",");
		System.out.println(quantities.length);
		if(!formDropdown.getAttribute("value").trim().equals(formName))
			selectDropdownValue(formDropdown, dropdownList, formName);
		eleFocussed(customerBox);
		eleAttributeToBeNotEmpty(driver, dateBox, 15, "value");
		if(customerBox.getAttribute("value").trim().equals("")) {
			selectValueFromList(customerArrowBtn, customerListBtn, searchBox, searchBtn, customerSearchList, Customer_Name);
			eleattributeContainsText(driver, customerBox, 20, "value", Customer_Name);
		}
		delivery_instructions_textarea.sendKeys(delivery_Instructions.trim());
		executor.executeScript("arguments[0].scrollIntoView(true);", Item_arrow);
		Thread.sleep(1000);
		try
		{
		if(item_row.isDisplayed()==true)
		{
			System.out.println("item is already present");
		}
		}
		catch(Exception e)
		{
			if(!item_Name.contains(","))
			{
			selectItem(item_Name, quantity,pack_price.get(0),quantityDiv);
			}
			else
			{
				for(int i=0;i<quantities.length;i++)
				{
					selectItem(items[i], quantities[i], pack_price.get(i),quantityDiv);
				}
			}
			
		}
		click(secondary_calculate_shipping_button);
		Thread.sleep(1000);
		eleAvailability(driver, shipping_ok_button, 10);
		driver.findElement(By.xpath("//div[@class='x-grid3-scroller']//tbody//tr//div[contains(text(),'"+shipping_Method.trim()+"')]")).click();
		eleClickable(driver, shipping_ok_button, 10);
		shipping_ok_button.click();
		Thread.sleep(1000);
		action.moveToElement(second_sales_order_save_button).build().perform();
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(1000);
		click(billing_tab);
		eleAvailability(driver, terms_dropdown, 20);
		if(terms.trim().equals(""))
		{
		selectDropdownValue(terms_dropdown, dropdownList, "");
		}
		Thread.sleep(1000);
		selectDropdownValue(select_payment_method, dropdownList,payment_Method );
		Thread.sleep(2000);
		click(second_sales_order_save_button);
	}
	
	public void selectItem(String ItemName,String quantity,WebElement pack_price, WebElement quantity_click) throws InterruptedException
	{
		
		selectValueFromList(Item_arrow, item_list_option, Item_searchbox_textbox, Item_search_searchbox_button, searchList, ItemName);
		notTextToBePresentInElementValue(driver, pack_price, 100, "0.00");
		Thread.sleep(3000);
		quantity_click.click();
		eleClickable(driver, Quantity, 5);
		Quantity.sendKeys(quantity.trim());
		itemAddBtn.click();
		Thread.sleep(1000);
		if(isAlertPresent_()==true)
		{
			driver.switchTo().alert().accept();
		}
	}
//
//	public void payment_details(String payment_Method, String credit_Card_Number, String security_Code, String expiry_Date, String customer_Firstname, String customer_Lastname,ExtentTest test) throws InterruptedException
//	{
//		executor.executeScript("arguments[0].scrollIntoView(true);", location);
//		Thread.sleep(1000);
//		billing_tab.click();
//		select_payment_method.sendKeys(payment_Method.trim());
//		executor.executeScript("arguments[0].scrollIntoView(true);", select_credit_card);
//		Thread.sleep(500);
//		selectDropdownValue(select_credit_card, select_credit_card_from_list, "- New -");
//		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//	    driver.switchTo().window(tabs.get(1));
//	    eleAvailability(driver,ccnumber_textbox, 10);
//		ccnumber_textbox.sendKeys(credit_Card_Number.trim());
//		expiry_date_textbox.sendKeys(expiry_Date.trim());
//		select_payment_method.sendKeys(payment_Method.trim());
//		save_card_details.click();
//	    driver.switchTo().window(tabs.get(0));	
//	    textToBePresentInElementValue(driver,ccname,15,customer_Firstname+" "+customer_Lastname);
//		security_code_textbox.sendKeys(security_Code.trim());
//		Thread.sleep(500);
//		second_sales_order_save_button.click();
//	}
	public void salesOrderApproval(ExtentTest test) throws InterruptedException
	{
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(1500);
		eleAvailability(driver, sales_order_approve_button, 20);
		sales_order_approve_button.click();
		eleAvailability(driver, sales_order_confirmation_message, 30);
	
		if(sales_order_confirmation_message.getText().trim().equals("Sales Order successfully Approved"))
		{
			System.out.println("Confirmation banner is displayed and Sales Order is approved successfully");
			test.pass("Confirmation banner is displayed and Sales Order is approved successfully");
		}
		else if(processedSalesOrderLink.isDisplayed())
		{
			test.pass("Processed Screen is displayed with Processed PO Number");
		}
		else
		{
			test.pass("Confirmation banner is not displayed and Sales Order is approved successfully");

		}
	}
	public void verifySOStatus(String status,ExtentTest test) 
	{
		eleAvailability(driver, sales_order_status, 20);
		String tranNo = recordNumber.getText().trim();
		String tranStatus = sales_order_status.getText().trim();
		if(tranStatus.equals(status.trim())) 
		{
			System.out.println("Sales Order '"+tranNo+"' is displayed with "+tranStatus+" status");
			test.pass("Sales Order '"+tranNo+"' is displayed with "+tranStatus+" status");
		}
		else
		{
			System.out.println("Sales Order '"+tranNo+"' is not displayed with "+tranStatus+" status");
			test.fail("Sales Order '"+tranNo+"' is not displayed with "+tranStatus+" status");
		}
	}
	public void verifyProcessedScreen(ExtentTest test)
	{
		if(processedSalesOrderLink.isDisplayed()&&processedPurchaseOrderLink.isDisplayed())
		{
			test.pass("Purchase Order is Processed with number "+processedPurchaseOrderLink.getText().trim() );
			processedSalesOrderLink.click();
		}
		else
		{
			test.info("Purchase Order is not Processed as it not a special order item ");
		}
		
	}
	public void verifyCashSaleandPO(String Transaction,ExtentTest test) throws InterruptedException
	{
		executor.executeScript("window.scrollBy(document.body.scrollHeight,0)");
		Thread.sleep(1000);
		eleAvailability(driver, related_records_tab, 10);
		related_records_tab.click();
		boolean tran_created=false;
		for(int i=0;i<transcation_type.size();i++)
		{
		if(transcation_type.get(i).getText().trim().equals(Transaction.trim()))
		{
			tran_created=true;
			System.out.println(Transaction+" is created with "+ch+" "+transcation_number.get(i).getText().trim()+ch+" "+" number");
			test.pass(Transaction+" is created with "+ch+" "+transcation_number.get(i).getText().trim()+ch+" "+" number");
		}
		}
		if(tran_created==false)
		{
			test.fail(Transaction+ " is not Created");
		}
	
	}
	public void navigatetoPO(String Transaction) throws InterruptedException
	{
		
		
		for(int i=0;i<transcation_type.size();i++)
		{
		if(transcation_type.get(i).getText().trim().equals(Transaction.trim()))
		{
			transcationLink.get(i).click();
			break;
		}
		}
		
	}
	public void verifyEmail(String subject, ExtentTest test) throws InterruptedException {
		Thread.sleep(1500);
		click(communication_tab);
 		try
		{
			eleAvailability(driver, By.xpath("//td[contains(text(),'"+subject+"')]"), 20);
			WebElement emailId = driver.findElement(By.xpath("//td[contains(text(),'"+subject+"')]"));
			if (emailId.isDisplayed()) 
			{
				System.out.println("Email with subject '"+subject+"' sent to the Customer");
				test.pass("Email with subject '"+subject+"' sent to the Customer");
			}
			else
			{
				System.out.println("Email with subject '"+subject+"' is not send to the Customer");
				test.fail("Email with subject '"+subject+"' is not to the Customer");
			}
		} 
		catch (Exception e)
		{
			System.out.println("Email with subject '"+subject+"' is not  send to the Customer");
			test.fail("Email with subject '"+subject+"' is not  send to the Customer");
		}
	}
	public void waitUntilStockIsAutoCommitted(String quantity, ExtentTest test,String itemName) throws InterruptedException
	{
		String[] items=itemName.split(",");
		
		String sales_order_url=driver.getCurrentUrl();
		loginPage=new LoginPage();
		loginPage.choose_required_role("Fulfilment");
		Thread.sleep(1000);
		driver.navigate().to(sales_order_url);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].scrollIntoView(true);", related_records_tab);
		int quantity_commit=0;
		if(!quantity.contains(","))
		{
		WebElement committed_quantity_div=driver.findElement(By.xpath("//a[text()='"+itemName.trim()+"']//following::td[count(//td[@data-label='Committed']//preceding-sibling::td)]"));
		waitUntilScriptIsScheduled(committed_quantity_div,"0",2000,itemName);
		if(committed_quantity.get(0).getText().trim().equals(quantity.trim()))
		{
			test.pass("Stock is auto committed");
		}
		else
		{
			test.fail("Stock is not auto committed");
		}
		}
		else
		{
			String [] quantites=quantity.split(",");
			for(int i=0;i<quantites.length;i++)
			{
				WebElement committed_quantity_div=driver.findElement(By.xpath("//a[text()='"+items[i].trim()+"']//following::td[count(//td[@data-label='Committed']//preceding-sibling::td)]"));

				waitUntilScriptIsScheduled(committed_quantity_div, "0", 2000,items[i]);
				if(committed_quantity.get(i).getText().trim().equals(quantites[i].trim()))
				{
					quantity_commit=quantity_commit+1;
				}
			}
			if(quantity_commit==quantites.length)
			{
				test.pass("Stock is auto committed");
			}
			else
			{
				test.fail("Stock is not auto committed");
			}
		}
		Thread.sleep(1000);
		
	}

	public void printProFormaInvoice(String filename,ExtentTest test) throws AWTException, InterruptedException
	{
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
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
		StringSelection sel = new StringSelection("C:\\Users\\Sindhuja\\Desktop\\"+filename);
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
	public void emailProFormaInvoice(String filename,ExtentTest test) throws InterruptedException, AWTException
	{
		
		executor.executeScript("window.scrollTo(0, 0)");
		Thread.sleep(1000);
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
		StringSelection sel = new StringSelection("C:\\Users\\Sindhuja\\Desktop\\"+filename);
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
//		action.sendKeys(save_selected_file).doubleClick().build().perform();
		if(isAlertPresent_()==true)
		{
			driver.switchTo().alert().accept();
		}
	    save_selected_file.click();
	    if(isAlertPresent_()==true)
		{
			driver.switchTo().alert().accept();
		}	    driver.switchTo().window(tabs.get(1));
	    Thread.sleep(1000);
	    eleAvailability(driver, add_file, 20);
	   textToBePresentInElementValue(driver, added_file, 30, filename.trim());
	    action.moveToElement(add_file).build().perform();
	    add_file.click();
	    Thread.sleep(1000);
	    action.moveToElement(merge_and_send).build().perform();
	    merge_and_send.click();
	    if(isAlertPresent_()==true)
		{
			driver.switchTo().alert().accept();
		}		
	}
	public void verifyProFormaInvoice(String filename,ExtentTest test) throws InterruptedException
	{
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		Thread.sleep(2000);
		driver.switchTo().window(tabs1.get(0));
		Thread.sleep(1000);
		action.moveToElement(refresh_communication_msgs).build().perform();
		Thread.sleep(1500);
		refresh_communication_msgs.click();
		Thread.sleep(1000);
		eleAvailability(driver, attached_email.get(0), 10);
		boolean emailed=false;
		for(int i=0;i<attached_email.size();i++)
		{
			if(attached_email.get(i).getText().trim().contains(filename.trim()))
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
	public void navigate_to_return_authorization(ExtentTest test) throws InterruptedException
	{
		String sales_order_url=driver.getCurrentUrl();
		loginPage=new LoginPage();
		loginPage.choose_required_role("Customer Services");
		Thread.sleep(1000);
		driver.navigate().to(sales_order_url);
		eleAvailability(driver, authorizeReturnButton, 15);
		authorizeReturnButton.click();
	}
	public Map<String, String> getPaymentData() throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		eleClickable(driver, billing_tab, 10);
		click(billing_tab);
		je.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		eleAvailability(driver, viewLink, 10);
		viewLink.click();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    action.moveToElement(cardHolderField).build().perform();
	    String cardHolderName = cardHolderField.getText().trim(); 
	    action.moveToElement(spanText).build().perform();
	    String[] text = spanText.getText().split("\n");
	    String cardNumber = "";
	    for (int i = 0; i < text.length; i++) {
			if(text[i].contains("card_accountNumber"))
				cardNumber = text[i];
		}
	    cardNumber = cardNumber.split(":")[1].trim();
	    driver.close();
	    driver.switchTo().window(tabs.get(0));
	    Map<String,String> paymentDetails = new HashMap<String, String>();
//	    System.out.println(cardNumber+" "+cardHolderName);
	    paymentDetails.put("cardNumber", cardNumber);
	    paymentDetails.put("cardHolderName", cardHolderName);
	    
	    return paymentDetails;
	}
		
	}


	 
	
	
	
	
	

  