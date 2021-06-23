package com.qa.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.pages.LoginPage;

public class TestUtil extends TestBase {

	public static int IMPLICIT_WAIT = 30;
	static LoginPage loginpage;

	public TestUtil() {
		super();
	}

	public void setUp() throws InterruptedException {
		initilizaton();
		loginpage = new LoginPage();
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	}
