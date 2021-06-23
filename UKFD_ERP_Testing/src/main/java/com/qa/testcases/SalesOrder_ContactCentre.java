package com.qa.testcases;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.excelReader.ExcelReader;
import com.qa.pages.CustomerPage;
import com.qa.pages.LoginPage;
import com.qa.pages.OpportunityPage;
import com.qa.pages.QuotePage;
import com.qa.pages.SalesOrderPage;
import com.qa.util.TestUtil;

public class SalesOrder_ContactCentre {

	ExtentTest test;
	ExtentReports extent;
	ExtentSparkReporter htmlReporter;
	ExcelReader reader;
	LoginPage loginPage;
	CustomerPage customerPage;
	OpportunityPage opprPage;
	QuotePage quotePage;
	SalesOrderPage soPage;
	TestUtil testBase;
	public void send_email() throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("./GrowthProjectReport/GrowthProjectReport.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("sindhuja.b@tvarana.com", "Sindhu@123"));
		email.setSSLOnConnect(true);
		email.addTo("sindhuja.b@tvarana.com", "Sindhuja");
		email.setFrom("sindhuja.b@tvarana.com", "Sindhuja");
		email.setSubject("Growth Project Test Report");
		email.setMsg("Here is the report please find the attachment");
		email.attach(attachment);
		email.send();
	}

	@BeforeTest
	public void setExtent() {
		// specify location of the report
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/GrowthProjectReport/GrowthProjectReport.html");
		htmlReporter.config().setDocumentTitle("Growth Project Test Report"); // Tile of report
		htmlReporter.config().setReportName("Growth Project Test Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Passing General information
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Sindhuja");
	}

	@AfterTest
	public void endReport() throws EmailException {
		extent.flush();
		send_email();
	}


	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
																					// report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extent.removeTest(test);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			// test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		// driver.quit();
	}

	@DataProvider
	public Object[][] UKFD_Contact_Centre() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD(Contact_centre).xlsx", 0);
	}


	@BeforeClass
	public void setUp() throws InterruptedException {
		testBase=new TestUtil();
		testBase.setUp();
	}

	@Test(dataProvider = "UKFD_Contact_Centre",priority = 1)
	public void so_Creation_Contact_Centre(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Credit_Card_Number,String Expiry_Date,String Security_Code,String Sales_Order_Form) throws Exception {
		test=extent.createTest("Verifying New Sales Order creation via Contact Centre / Showroom  - Credit/Debit Card ");
		customerPage=new CustomerPage();
		customerPage.enter_Customer_details(Customer_Firstname,Customer_Lastname,Email,Phone,Address1,Address2,Address3,City,State,Zip,test);
		opprPage=new OpportunityPage();
		opprPage.enter_Opprnty_Details(Lead_source,test);
		quotePage=new QuotePage();
		quotePage.enter_quote_details(Location,Item_Name,Quantity,Shipping_Method,test);
		soPage=new SalesOrderPage();
		soPage.enter_SO_details(Sales_Order_Form,Delivery_Instructions,Shipping_Method,Payment_Method,Credit_Card_Number,Security_Code,Expiry_Date,Customer_Firstname,Customer_Lastname,test);
		soPage.salesOrderApproval(test);
		soPage.verifyCashSale(test);
		soPage.verifyEmail(test);
	}
	
}
