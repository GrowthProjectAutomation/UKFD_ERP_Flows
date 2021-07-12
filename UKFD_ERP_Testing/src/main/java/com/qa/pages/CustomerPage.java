package com.qa.pages;

import java.util.List;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

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

public class CustomerPage extends TestUtil{
	
	
	// customer navigation for creation 
	@FindBy(xpath = "//li[@data-title='Lists']")
	WebElement Lists_link;
	
	@FindBy(xpath = "//li[@data-title='Relationships']")
	WebElement Relationships_link;
	
	@FindBy(xpath = "//li[@data-title='Contacts']//preceding-sibling::li[@data-title='Customers']")
	WebElement Customers_link;
	
	@FindBy(xpath = "//li[@data-title='New']")
	WebElement New_link;
	
	// Customer fields
	
	@FindBy(xpath = "//input[@name='lastname']")
	WebElement lastname_textbox;
	
	@FindBy(xpath = "//input[@name='firstname']")
	WebElement firstname_textbox;
	
	@FindBy(xpath = "//span[@id='email_fs']//input")
	WebElement email_id_textbox;
	
	@FindBy(xpath = "//span[@id='phone_fs']//input")
	WebElement phone_number_textbox;
	
	@FindBy(xpath = "//a[@id='addresstxt']")
	WebElement address_tab;
	
	@FindBy(xpath = "//a[@id='addressbookaddress_helper_popup']")
	WebElement address_edit_link;
	
	@FindBy(xpath = "//input[@id='custrecordpca_search']")
	WebElement address_search_searchbox;
	
	@FindBy(xpath = "//input[@name='addr1']")
	WebElement address1_textbox;
	
	@FindBy(xpath = "//input[@name='addr2']")
	WebElement address2_textbox;
	
	@FindBy(xpath = "//input[@name='addr3']")
	WebElement address3_textbox;
	
	@FindBy(xpath = "//input[@name='city']")
	WebElement city_textbox;
	
	@FindBy(xpath = "//input[@name='inpt_dropdownstate']")
	WebElement select_state_dropdown;
	
	@FindBy(xpath = "//input[@name='zip']")
	WebElement zip_textbox;
	
	@FindBy(xpath = "//input[@name='ok']")
	WebElement ok_button;
	
	@FindBy(xpath = "//td[@id='tdbody_secondarysubmitter']")
	WebElement save_customer;
	
	@FindBy(xpath = "//div[contains(text(),'Confirmation')]//following-sibling::div")
	WebElement customer_confirmation_message;
	
	@FindBy(xpath = "//div[@class='uir-record-id']")
	WebElement customer_number;
	
	@FindBy(xpath = "//input[@name='inpt_subsidiary']")
	WebElement select_subsidiary;
	
	
	@FindBy(xpath = "//a[@id='salestxt']")
	WebElement sales_tab_link;
	
	@FindBy(xpath = "//input[@name='opprtnty']")
	WebElement opprtnty_button_link;
	
	@FindBy(xpath = "//input[@name='inpt_category']")
	WebElement customer_type;
	
	@FindBy(xpath = "//div[@class='dropdownDiv']//div")
	List<WebElement> dropdownList;
	
	@FindBy(xpath = "//input[@name='inpt_custentity_trade_reg_status']")
	WebElement trade_registartion_type;
	
	@FindBy(xpath = "//a[@id='financialtxt']")
	WebElement financial_tab;
	
	@FindBy(xpath = "//input[@id='inpt_currency32']")
	WebElement currencyDropdown;
	
	@FindBy(xpath = "//input[@id='currency_addedit']")
	WebElement addCurrency;
	
	@FindBy(xpath = "//a[@id='creditcardstxt']")
	WebElement creditCardSublist;
	
	@FindBy(xpath = "//input[@name='ccnumber']")
	WebElement ccnumnberTextBox;
	
	@FindBy(xpath = "//table[@id='creditcards_splits']//tr[@id='creditcards_headerrow']//following-sibling::tr[@class='uir-machine-row uir-machine-row-odd listtextnonedit uir-machine-row-focused']//td[count(//tr[@id='creditcards_headerrow']//td[@data-label='Expiration Date']//preceding-sibling::td)+1]")
	WebElement expiryDateClick;
	
	@FindBy(xpath = "//input[@name='ccexpiredate']")
	WebElement expiryDateTextBox;
	
	@FindBy(xpath = "//table[@id='creditcards_splits']//tr[@id='creditcards_headerrow']//following-sibling::tr[@class='uir-machine-row uir-machine-row-odd listtextnonedit uir-machine-row-focused']//td[count(//tr[@id='creditcards_headerrow']//td[@data-label='Card State']//preceding-sibling::td)]")
	WebElement creditCardTypeClick;
	
	@FindBy(xpath = "//input[@name='inpt_paymentmethod']")
	WebElement creditCardTypeDropDown;
	
	@FindBy(xpath = "//table[@id='creditcards_splits']//tr[@id='creditcards_headerrow']//following-sibling::tr[@class='uir-machine-row uir-machine-row-odd listtextnonedit uir-machine-row-focused']//td[count(//tr[@id='creditcards_headerrow']//td[@data-label='Default Credit Card']//preceding-sibling::td)+1]")
	WebElement defaultCreditCardClick;
	
	@FindBy(xpath = "//input[@name='ccdefault']//following-sibling::img")
	WebElement defaultCreditCard;
	
	@FindBy(xpath = "//input[@name='inpt_creditholdoverride']")
	WebElement credit_card_hold;
	
	@FindBy(xpath = "//table[@id='creditcards_splits']//tr[@id='creditcards_headerrow']//following-sibling::tr[@class='uir-machine-row uir-machine-row-odd listtextnonedit uir-machine-row-focused']//td[count(//tr[@id='creditcards_headerrow']//td[@data-label='Credit Card Type']//preceding-sibling::td)])")
	WebElement cardHolderNameClick;
	
	@FindBy(xpath = "//input[@id='creditcards_addedit']")
	WebElement addCreditCard;
	
	@FindBy(xpath = "//input[@name='ccname']")
	WebElement cardHolderNameTextBox;
	
	@FindBy(xpath = "//input[@name='inpt_quicksort']")
	WebElement quickSortDropdown;
	
	@FindBy(xpath = "//table[@id='div__bodytab']//tr[contains(@id,'row')]//td[count(//table[@id='div__labtab']//thead//div[contains(text(),'Name')]//parent::td//preceding-sibling::td)+1]")
	List<WebElement> customerList;
	
	@FindBy(xpath = "//span[@class='uir-pagination-label']")
	WebElement paginationLabel;
	
	@FindBy(xpath = "//span[@class='uir-pagination-label']//following-sibling::ul//li")
	List<WebElement> paginationList;
	
	@FindBy(xpath = "//span[@id='spn_CREATENEW_d1']//div[@class='button-new']")
	WebElement createNewBtn;
	
	@FindBy(xpath = "//div[@id='div_CREATENEW_d1']//span[text()='Sales Order']")
	WebElement newSOLink;
	

	
	
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	LoginPage loginPage;

	
	public CustomerPage()
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
	}
	public void navigateToCustomer(String role,String url, String customerId) throws InterruptedException
	{
		loginPage=new LoginPage();
		loginPage.choose_required_role(role.trim());
		String customer_url=url.trim()+customerId.trim();
		driver.navigate().to(customer_url);
	}

	public void clickNewSOFromCustomer() throws InterruptedException 
	{
		action.moveToElement(createNewBtn).build().perform();
		eleAvailability(driver, newSOLink, 10);
		newSOLink.click();
		
	}
	public void navigate_to_customer_record() throws InterruptedException
	{
		eleAvailability(driver, Lists_link, 15);
		action.moveToElement(Lists_link).build().perform();
		Thread.sleep(500);
		action.moveToElement(Relationships_link).build().perform();
		Thread.sleep(500);
		action.moveToElement(Customers_link).build().perform();
		Thread.sleep(500);
		action.moveToElement(New_link).click().build().perform();
	}
	public void enter_Customer_details(String customer_Firstname, String customer_Lastname, String email, String phone, String address1, String address2, String address3, String city, String state, String zip, String customer_type_value, String role, String credit_Card_Number, String nameOnCard, String expiry_Date, String security_Code, String payment_Method, ExtentTest test) throws InterruptedException
	{
		loginPage=new LoginPage();
		loginPage.choose_required_role(role.trim());
		Thread.sleep(1000);
		navigate_to_customer_record();
		eleAvailability(driver, firstname_textbox, 20);
		firstname_textbox.sendKeys(customer_Firstname.trim());
		lastname_textbox.sendKeys(customer_Lastname.trim());
		selectDropdownValue(customer_type, dropdownList, customer_type_value);
		if(customer_type_value.trim().equals("Trade"))
		{
			selectDropdownValue(trade_registartion_type, dropdownList, "Accepted");
		}
		action.moveToElement(email_id_textbox).build().perform();
		Thread.sleep(500);
		email_id_textbox.sendKeys(email.trim());
		phone_number_textbox.sendKeys(phone.trim());
		financial_tab.click();
		Thread.sleep(1000);
		selectDropdownValue(currencyDropdown, dropdownList, "GBP");
		addCurrency.click();
		Thread.sleep(1500);
		creditCardSublist.click();
		eleAvailability(driver, ccnumnberTextBox, 15);
		ccnumnberTextBox.sendKeys(credit_Card_Number.trim());
		Thread.sleep(1500);
		eleClickable(driver, expiryDateClick, 10);
		click(expiryDateClick);
		expiryDateClick.click();
		expiryDateTextBox.sendKeys(expiry_Date);
		Thread.sleep(1500);
		//eleClickable(driver, cardHolderNameClick, 20);
		//action.doubleClick(cardHolderNameClick).build().perform();
		cardHolderNameTextBox.sendKeys(nameOnCard.trim());
		Thread.sleep(1000);
		eleClickable(driver, creditCardTypeClick, 20);
		action.doubleClick(creditCardTypeClick).build().perform();
		selectDropdownValue(creditCardTypeDropDown, dropdownList, payment_Method.trim());
		Thread.sleep(1500);
		eleClickable(driver, defaultCreditCardClick, 10);
		defaultCreditCardClick.click();
		defaultCreditCard.click();
		addCreditCard.click();
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		Thread.sleep(1000);
		action.moveToElement(credit_card_hold).build().perform();
		Thread.sleep(1000);
		selectDropdownValue(credit_card_hold, dropdownList, "Off");
		address_tab.click();
		Thread.sleep(500);
		address_edit_link.click();
		driver.switchTo().frame("childdrecord_frame");
		Thread.sleep(1000);
		WebElement addresse=driver.findElement(By.xpath("//input[@name='addressee']"));
		textToBePresentInElementValue(driver,addresse,30, customer_Firstname+" "+customer_Lastname);
		Thread.sleep(1000);
		address1_textbox.sendKeys(address1.trim());
		address2_textbox.sendKeys(address2.trim());
		address3_textbox.sendKeys(address3.trim());
		city_textbox.sendKeys(city.trim());
		select_state_dropdown.sendKeys(state.trim());
		Thread.sleep(3000);
		zip_textbox.sendKeys(zip.trim());
		Thread.sleep(1000);
		address_search_searchbox.click();
        Thread.sleep(1000);
		ok_button.click();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		executor.executeScript("window.scrollTo(0, 0)");
		save_customer.click();
		accept_alert();		
		eleAvailability(driver, By.xpath("//input[@name='acceptpayment']"), 20);
		if(customer_confirmation_message.getText().trim().equals("Customer successfully Saved"))
		{
			test.pass("Customer is created successfully with  "+customer_number.getText().trim()+ " number" );
		}
		else
		{
			test.fail("Customer is not created");
		}
		executor.executeScript("arguments[0].scrollIntoView(true);", sales_tab_link);
		Thread.sleep(1000);
		sales_tab_link.click();
		eleAvailability(driver, opprtnty_button_link, 5);
		opprtnty_button_link.click();

	}
	
	
}
