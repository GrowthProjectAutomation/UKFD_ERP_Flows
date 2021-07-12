package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class QuotePage extends TestUtil {
	
	@FindBy(xpath = "//input[@name='inpt_location']")
	WebElement select_location;
	
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
	WebElement quantity_click;
	
	@FindBy(xpath="//input[@id='quantity_formattedValue']")
	WebElement Quantity;
	
	@FindBy(xpath = "//table[@id='item_splits']//tr[@id='item_headerrow']//following-sibling::tr//td[6]")
	WebElement pack_price;
	
	@FindBy(xpath="//td[@id='spn_secondarymultibutton_submitter']")
	WebElement save_quote;
	
	@FindBy(xpath="//div[@class='uir-record-id']")
	WebElement id;
	
	@FindBy(xpath="//div[@class='uir-record-status']")
	WebElement status;
	
	@FindBy(xpath = "//input[@id='inpt_location5']")
	WebElement select_location_dropdown;
	
	@FindBy(xpath = "//input[@id='ismultishipto_fs_inp']//following-sibling::img")
	WebElement enable_line_item_shipping_checkbox;
	
	@FindBy(xpath = "//a[@id='shippingtxt']")
	WebElement shipping_tab;
	
	@FindBy(xpath = "//input[@id='inpt_shipmethod25']")
	WebElement select_shipping_method_dropdown;
	
	@FindBy(xpath = "//input[@id='shippingcost_formattedValue']")
	WebElement shipping_cost;
	
	@FindBy(xpath = "//a[@id='accntingtabtxt']")
	WebElement accounting_tab;
	
	@FindBy(xpath = "//a[@id='itemstxt']")
	WebElement items_tab;
	
	@FindBy(xpath = "//input[@id='btn_multibutton_submitter']")
	WebElement first_save_button;
	
	@FindBy(xpath = "//span[@id='entity_fs_lbl_uir_label']//following-sibling::span//a")
	WebElement customer_link;
	
	@FindBy(xpath = "//a[@class='pgm_menu']")
	WebElement page_menu;
	
	@FindBy(xpath = "//span[@class='ac_text_pad']")
	List<WebElement> list_of_pages;
	
	@FindBy(xpath = "//input[@name='createsalesord']")
	WebElement create_sales_order_button;
	
	@FindBy(xpath = "//div[@class='descr']")
	WebElement quote_confirmation_message;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement quote_id;
	
	@FindBy(xpath = "//span[@id='shippingtaxcode_fs_lbl_uir_label']//following-sibling::span//span//div//input")
	WebElement shipping_tax_code;
	
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	WebDriverWait wait=new WebDriverWait(driver, 100);
	LoginPage loginPage;

	
	
	
	public QuotePage()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
	}
	 
	public void enterQuoteDetails(String location, String item_Name, String quantity, String shipping_Method, ExtentTest test) throws InterruptedException
	{
		
		action.moveToElement(select_location_dropdown).build().perform();
		Thread.sleep(500);
		select_location_dropdown.sendKeys(location.trim());
		Thread.sleep(1000);
		enable_line_item_shipping_checkbox.click();
		executor.executeScript("window.scrollBy(0,-350)", "");
		eleClickable(driver, enable_line_item_shipping_checkbox, 10);
		enable_line_item_shipping_checkbox.click();
		Thread.sleep(1000);
		selectValueFromList(Item_arrow, item_list_option, Item_searchbox_textbox, Item_search_searchbox_button, searchList, item_Name);
		notTextToBePresentInElementValue(driver, pack_price, 100, "0.00");
		Thread.sleep(3000);
		quantity_click.click();
		eleClickable(driver, Quantity, 5);
		Quantity.sendKeys(quantity.trim());
		executor.executeScript("window.scrollBy(0,-350)", "");
		Thread.sleep(500);
		shipping_tab.click();
		eleAvailability(driver, select_shipping_method_dropdown, 10);
		select_shipping_method_dropdown.sendKeys(shipping_Method.trim());
		Thread.sleep(1000);
		shipping_cost.click();
		action.moveToElement(shipping_tax_code).build().perform();
		textToBePresentInElementValue(driver, shipping_tax_code, 15, "VAT:S-GB");
		executor.executeScript("window.scrollTo(0,0)");
		eleClickable(driver, first_save_button, 20);
		first_save_button.click();
		if(isAlertPresent_()==true)
		{
			driver.switchTo().alert().accept();
		}
		eleAvailability(driver, create_sales_order_button, 20);
		if(quote_confirmation_message.getText().trim().equals("Transaction successfully Saved"))
		{
			test.pass("Quote is created successfully with id number "+quote_id.getText().trim());
		}
		else
		{
			test.fail("Quote is not created");
		}
		
		create_sales_order_button.click();
		
		
	}
	
	
	
	

}
