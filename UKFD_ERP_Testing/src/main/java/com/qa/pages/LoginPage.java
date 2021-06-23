package com.qa.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.util.TestUtil;

public class LoginPage extends TestUtil {

	// we have to define page objects with the help of @FindBy
	@FindBy(name = "email")
	WebElement username;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(name = "submitButton")
	WebElement submit;
	
	@FindBy(xpath = "//div[@id='spn_cRR_d1']")
	WebElement choose_role;
	
	@FindBy(xpath = "//span[@class='ns-role-menuitem-text']")
	List<WebElement> all_roles;
	
	@FindBy(xpath = "//div[@class='ns-role']//span[@class='ns-role-name']")
	WebElement existing_role;
	
	@FindBy(xpath = "//a[@href='?account_switch=3460739_SB5']")
	WebElement SB5_link;

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	// login function:
	public void login(String usrn, String pswd) throws InterruptedException {
		username.sendKeys(usrn);
		password.sendKeys(pswd);
		submit.click();
		Thread.sleep(200);
		SB5_link.click();
		
	}
	public void choose_required_role(String rolename) throws InterruptedException
	{
		if(existing_role.getText().trim().equals(rolename.trim()))
		{
			System.out.println("already in that role");
		}
		else
		{
		Actions action=new Actions(driver);
		action.moveToElement(choose_role).build().perform();
		Thread.sleep(1000);
		for(int i=0;i<all_roles.size();i++)
		{
			if(all_roles.get(i).getText().trim().equals(rolename.trim()))
			{
				all_roles.get(i).click();
				break;
			}
		}
		
		}
		
	}


}
