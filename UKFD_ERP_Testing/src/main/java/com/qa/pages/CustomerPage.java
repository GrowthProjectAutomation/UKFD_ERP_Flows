package com.qa.pages;

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
import com.qa.util.TestBase;

public class CustomerPage extends TestBase{
	
	
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
	
	
	
	
	Actions action=new Actions(driver);
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	WebDriverWait wait=new WebDriverWait(driver, 20);
	LoginPage loginPage;

	
	public CustomerPage()
	{
		PageFactory.initElements(driver, this);
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
	public void enter_Customer_details(String customer_Firstname, String customer_Lastname, String email, String phone, String address1, String address2, String address3, String city, String state, String zip, ExtentTest test) throws InterruptedException
	{
		loginPage=new LoginPage();
		loginPage.choose_required_role("Sales Manager");
		Thread.sleep(1000);
		navigate_to_customer_record();
		eleAvailability(driver, firstname_textbox, 20);
		firstname_textbox.sendKeys(customer_Firstname.trim());
		lastname_textbox.sendKeys(customer_Lastname.trim());
		action.moveToElement(email_id_textbox).build().perform();
		Thread.sleep(500);
		email_id_textbox.sendKeys(email.trim());
		phone_number_textbox.sendKeys(phone.trim());
		action.moveToElement(address_edit_link).build().perform();
		Thread.sleep(500);
		address_edit_link.click();
		driver.switchTo().frame("childdrecord_frame");
		Thread.sleep(1000);
		WebElement addresse=driver.findElement(By.xpath("//input[@name='addressee']"));
		wait.until(ExpectedConditions.textToBePresentInElementValue(addresse, customer_Firstname+" "+customer_Lastname));
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
		Thread.sleep(500);
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
