 package com.qa.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.pages.LoginPage;


public class TestUtil {
	public static WebDriver driver;
	public static Properties prop;
	LoginPage loginPage;
	public static Actions action;

	public TestUtil()
	{
		try
		{
			prop=new Properties();
			FileInputStream fis=new FileInputStream("C:\\Users\\Sindhuja\\git\\repository\\UKFD_ERP_Testing\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		}
		catch(IOException e)
		{
			e.getMessage();
		}
		
	}
	public static void initilizaton()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Projects\\chrome_exe\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
	}
	public void setUp() throws InterruptedException {
		initilizaton();
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	public boolean isAlertPresent(){
	    boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(driver, 15 /*timeout in seconds*/);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	        
	    } catch (TimeoutException eTO) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}
	public void accept_alert()
	{
		
		if(isAlertPresent()==true)
		{
			Alert alert=driver.switchTo().alert();
			alert.accept();
		}
		else
		{
			System.out.println("no alert");
		}
	}
	
	public static ExpectedCondition<Boolean> eleFocussed(final WebElement element){
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return element.equals(driver.switchTo().activeElement());
			}
		};
	}
	public String windowHandle() throws Exception {

		String parentWindow;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String currentwindow = driver.getWindowHandle();
		parentWindow = currentwindow;
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> i = allWindows.iterator();
		while (i.hasNext()) {
			String childwindow = i.next();
			if (!childwindow.equalsIgnoreCase(currentwindow)) {
				driver.switchTo().window(childwindow);
			}
		}
		return parentWindow;
	}
	

	public static void notTextToBePresentInElementValue(WebDriver driver, WebElement element, int time,String text)
	{
		new WebDriverWait(driver, time).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(element, text)));
	}
	public static void textToBePresentInElementValue(WebDriver driver, WebElement element, int time,String text)
	{
		new WebDriverWait(driver, time).until(ExpectedConditions.textToBePresentInElementValue(element, text));
	}
	public static void frameSwitchable(WebDriver driver, WebElement element, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}
	
	public static void eleAvailability(WebDriver driver, By locator, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static void eleAvailability(WebDriver driver, WebElement element, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void eleSelectionStateToBe(WebDriver driver, WebElement element, int time, boolean selected) {
		new WebDriverWait(driver, time).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
	}
	
	public static void eleClickable(WebDriver driver, WebElement element, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void eleClickable(WebDriver driver, By locator, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static void eleToBeInvisible(WebDriver driver, WebElement locator, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOf(locator));
	}
	
	public static void eleContainsText(WebDriver driver, WebElement element, int time, String text) {
		new WebDriverWait(driver, time).until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	public static void eleAttributeToBeNotEmpty(WebDriver driver, WebElement element, int time, String attribute) {
		new WebDriverWait(driver, time).until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
	}
	public void waitUntilTextNotToBeInWebElement(WebElement element, String textValue, int time) throws InterruptedException {
	    int timer = 0;
	    final int pollInterval = 500;
	    while (timer < time*1000) {
	        if (element.getText().equalsIgnoreCase(textValue)) {
	           Thread.sleep(500);
	           timer += pollInterval;
	        } else {
	            return;
	        }
	    }
	    throw new TimeoutException("Maximum time exceeded.");
	}
	
	
	
	public void waitUntilScriptIsScheduled(WebElement element,String value, int time) throws InterruptedException {
	    int timer = 0;
	    final int pollInterval = 8000;
	    while (timer < time*1000) {
	 
	        if (element.getText().trim().equals(value))
	        {
	           driver.navigate().refresh();
	           Thread.sleep(pollInterval);
	           timer += pollInterval;
	        }
	        else 
	        {
	            return;
	        }
	    	
	    
	    }
	    throw new TimeoutException("Maximum time exceeded.");
	}
	
	public void waitUntilTabsCountEquals(int tabs, int time) throws InterruptedException {
	    int timer = 0;
	    final int pollInterval = 500;
	    while (timer < time*1000) {
	        if (driver.getWindowHandles().size() != tabs) {
	           Thread.sleep(500);
	           timer += pollInterval;
	        } else {
	            return;
	        }
	    }
	    throw new TimeoutException("Maximum time exceeded.");
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public double convertStringToDouble(String str) {
		double value = Double.parseDouble(str);
		return value;
	}

	public void selectDropdownValue(WebElement dropdown, List<WebElement> dropdownList, String value) {
		action = new Actions(driver);
		action.moveToElement(dropdown).click().build().perform();
		for (int i = 0; i < dropdownList.size(); i++) {
			String formValue = dropdownList.get(i).getText().trim();
			if (formValue.equals(value)) {
				
				dropdownList.get(i).click();
				break;
			}
		}
	}
	
	public void verifyAndSelectListValue(WebElement dropdown, List<WebElement> dropdownList, String value) {
		action = new Actions(driver);
		action.moveToElement(dropdown).build().perform();
		if (!dropdown.getAttribute("value").trim().equals(value)) {
			dropdown.click();
			for (int i = 0; i < dropdownList.size(); i++) {
				String formValue = dropdownList.get(i).getText().trim();
				if (formValue.equals(value)) {
					dropdownList.get(i).click();
					break;
				}
			}
		}
	}
	
	public void selectValueFromList(WebElement arrowBtn, WebElement listBtn, WebElement searchBox, WebElement searchBtn, List<WebElement> searchList, String value) throws InterruptedException {
		arrowBtn.click();
		listBtn.click();
		eleClickable(driver, searchBox, 30);
		searchBox.sendKeys(value);
		searchBtn.click();
		eleAvailability(driver, By.xpath("//a[contains(text(),'"+value+"')]"), 30);
		Thread.sleep(1500);
		for(int j=0;j<searchList.size();j++) {
			String parentCustomer = searchList.get(j).getText().trim();
			if(parentCustomer.equals(value)) {
				searchList.get(j).click();
				break;
			}
		}
	}
	
	public void enableCheckbox(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebElement parentSpan = (WebElement)je.executeScript("return arguments[0].parentNode;", element);
		if(parentSpan.getAttribute("class").equals("checkbox_unck"))
			element.click();
	}

	public void disableCheckbox(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebElement parentSpan = (WebElement)je.executeScript("return arguments[0].parentNode;", element);
		if(!parentSpan.getAttribute("class").equals("checkbox_unck"))
			element.click();
	}
	
	public void click(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}
	
	

}
