package com.qa.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()
	{
		try
		{
			prop=new Properties();
			FileInputStream fis=new FileInputStream("C:\\Users\\Sindhuja\\eclipse-workspace\\UKFD_ERP_Testing\\src\\main\\java\\com\\qa\\config\\config.properties");
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
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
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

	public static void notTextToBePresentInElementValue(WebDriver driver, WebElement element, int time,String text)
	{
		new WebDriverWait(driver, time).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(element, text)));
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
	
	
	

}
