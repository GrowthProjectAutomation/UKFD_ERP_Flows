package com.qa.testcases;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Map;

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
import com.qa.pages.CreditMemoPage;
import com.qa.pages.CustomerPage;
import com.qa.pages.CustomerRefundPage;
import com.qa.pages.ItemFulfilment;
import com.qa.pages.ItemReceiptPage;
import com.qa.pages.LoginPage;
import com.qa.pages.OpportunityPage;
import com.qa.pages.PurchaseOrderPage;
import com.qa.pages.QuotePage;
import com.qa.pages.ReturnAuthorizationPage;
import com.qa.pages.SalesOrderPage;
import com.qa.util.TestUtil;
import com.sun.mail.imap.protocol.Item;

public class UKFD_ERP_Testcases extends TestUtil {

	ExtentTest test;
	ExtentReports extent;
	ExtentSparkReporter htmlReporter;
	ExcelReader reader;
	LoginPage loginPage;
	CustomerPage customerPage;
	OpportunityPage opprPage;
	QuotePage quotePage;
	SalesOrderPage soPage;
	ItemFulfilment itemfulfilmentPage;
	ReturnAuthorizationPage returnPage;
	ItemReceiptPage itemReceiptPage;
	CreditMemoPage creditMemoPage;
	CustomerRefundPage customerRefundPage;
	PurchaseOrderPage poPage;
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
	@DataProvider
	public Object[][] UKFD_Trade_Centre() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD(Contact_centre).xlsx", 1);
	}
	@DataProvider
	public Object[][] UKFD_Return() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD(Contact_centre).xlsx", 2);
	}
	@DataProvider
	public Object[][] Carpet_Vinyl() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD(Contact_centre).xlsx", 3);
	}

	@BeforeClass
	public void setUp() throws InterruptedException {
		testBase=new TestUtil();
		testBase.setUp();
	}
//
//	@Test(dataProvider = "UKFD_Contact_Centre",priority = 1)
//	public void so_Creation_Contact_Centre(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Credit_Card_Number,String Expiry_Date,String Security_Code,String Sales_Order_Form,String Customer_type,String Role) throws Exception {
//		customerPage=new CustomerPage();
//		opprPage=new OpportunityPage();
//		quotePage=new QuotePage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		
//		if(Customer_type.trim().equals("General"))
//		{
//		test=extent.createTest("Verifying New Sales Order creation via Contact Centre / Showroom  - Credit/Debit Card ");
//		}
//		else
//		{
//			test=extent.createTest("Verifying Trade Order via Contact Centre - Credit Card");
//		}
//		customerPage.enter_Customer_details(Customer_Firstname,Customer_Lastname,Email,Phone,Address1,Address2,Address3,City,State,Zip,Customer_type,Role,test);
//		opprPage=new OpportunityPage();
//		opprPage.enter_Opprnty_Details(Lead_source,test);
//		quotePage.enter_quote_details(Location,Item_Name,Quantity,Shipping_Method,test);
//		soPage.enter_SO_details(Sales_Order_Form,Delivery_Instructions,Shipping_Method,Customer_Firstname,Customer_Lastname,test);
//		soPage.payment_details(Payment_Method, Credit_Card_Number, Security_Code, Expiry_Date, Customer_Firstname, Customer_Lastname, test);
//		soPage.salesOrderApproval(test);
//		soPage.verifyCashSale(test);
//		soPage.verifyEmail(test);
//		soPage.auto_Commit_stock(Quantity,test);
//		itemfulfilmentPage.item_Fulfillment(test);
//	}
//	@Test(dataProvider = "UKFD_Trade_Centre",priority = 1)
//	public void so_Creation_Trade_Centre(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Credit_Card_Number,String Expiry_Date,String Security_Code,String Sales_Order_Form,String Customer_type,String Role) throws Exception {
//		customerPage=new CustomerPage();
//		opprPage=new OpportunityPage();
//		quotePage=new QuotePage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		test=extent.createTest("Trade Order via Contact Centre - Credit Account");
//		customerPage.enter_Customer_details(Customer_Firstname,Customer_Lastname,Email,Phone,Address1,Address2,Address3,City,State,Zip,Customer_type,Role,test);
//		opprPage.enter_Opprnty_Details(Lead_source,test);
//		quotePage.enter_quote_details(Location,Item_Name,Quantity,Shipping_Method,test);
//		soPage.enter_SO_details(Sales_Order_Form,Delivery_Instructions,Shipping_Method,Customer_Firstname,Customer_Lastname,test);
//		soPage.provide_terms();
//		soPage.print_pro_froma_invoice(test);
//		soPage.email_pro_forma(test);
//		soPage.verify_profromaemail(test);
//		soPage.salesOrderApproval(test);
//		soPage.auto_Commit_stock(Quantity, test);
//		itemfulfilmentPage.item_Fulfillment(test);
//		itemfulfilmentPage.verify_sales_order_status_pending_billing(test);
//		
//	}

//	@Test(dataProvider = "UKFD_Return",priority = 1)
//	public void so_Creation_Return(String Customer_Name,String Item_Name, String Quantity,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Security_Code,String Sales_Order_Form,String Role,String Only_Name,String Terms,	String Return_Reason,String Return_Responsibility,String Return_Notes,String Return_Subtype,String Return_Reason3,String Return_reason4,String Supplier_Delivery_Note) throws Exception
//	{
//		if(Supplier_Delivery_Note.trim().equals("partial return"))
//		{
//		test=extent.createTest("Verifying Partial Order Return (Post Shipmet)");
//		}
//		else
//		{
//			test=extent.createTest("Verifying Full Order Return(Post Shipment)");
//		}
//		loginPage=new LoginPage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		returnPage=new ReturnAuthorizationPage();
//		itemReceiptPage=new ItemReceiptPage(); 
//		creditMemoPage=new CreditMemoPage();
//		customerRefundPage=new CustomerRefundPage();
//		loginPage.choose_required_role(Role.trim());
//		soPage.navigateToSO();
//		soPage.enter_SO_details(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_Name,Item_Name,Quantity, Payment_Method,test);
//		soPage.salesOrderApproval(test);
//		Map<String, String> paymentData = soPage.getPaymentData();
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test);
//		itemfulfilmentPage.item_Fulfillment(test);
//		itemfulfilmentPage.navigate_to_sales_order_from_fulfilment();
//		soPage.navigate_to_return_authorization(test);
//		returnPage.saveReturnOrder(Quantity,Return_Reason, Return_Responsibility, Return_Notes, Return_Subtype, Return_Reason3, Return_reason4,Supplier_Delivery_Note, test);
//		returnPage.navigateToItemReceipt();
//		itemReceiptPage.saveItemReceipt(Supplier_Delivery_Note,test);
//		itemReceiptPage.verifyItemReceiptStatus(test);
//		itemReceiptPage.navigateToReturnAuthorization();
//		itemReceiptPage.navigateToCreditMemo();
//		creditMemoPage.saveCreditMemo();
//		creditMemoPage.verifyCreditMemoStatus(test);
//		creditMemoPage.navigateToCustomerRefund();
//		customerRefundPage.saveCustomerRefund(paymentData, test);
//		
//	}
	
	@Test(dataProvider = "Carpet_Vinyl",priority = 1)
	public void so_Creation_Contact_Centre(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Credit_Card_Number,String Expiry_Date,String NameOnCard,String Security_Code,String Sales_Order_Form,String Customer_type,String Role,String SupplierDeliveryNote,String Bin) throws Exception 
	{
		String Terms="";
		test=extent.createTest("Verifying Trade Order via Contact Centre - Carpet/Vinyl Credit Card");
		customerPage=new CustomerPage();
		opprPage=new OpportunityPage();
		quotePage=new QuotePage();
		soPage=new SalesOrderPage();
		itemfulfilmentPage=new ItemFulfilment();
		itemReceiptPage =new ItemReceiptPage();
		poPage=new PurchaseOrderPage();
		try
		{
		customerPage.enter_Customer_details(Customer_Firstname, Customer_Lastname, Email, Phone, Address1, Address2, Address3, City, State, Zip, Customer_type, Role,Credit_Card_Number,NameOnCard,Expiry_Date,Security_Code,Payment_Method, test);
		opprPage.enter_Opprnty_Details(Lead_source, test);
		quotePage.enter_quote_details(Location, Item_Name, Quantity, Shipping_Method, test);
		soPage.enter_SO_details(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_type, Item_Name, Quantity, Payment_Method,Terms, test);
		soPage.verifyEmail("Thanks for your order!", test);
		soPage.salesOrderApproval(test);
		soPage.verifyProcessedScreen(test);
		soPage.verifySOStatus("PENDING FULFILLMENT",test);
		soPage.verifyEmail("Order Confirmation", test);
		//Verifying Cash Sale
		soPage.verifyCashSaleandPO("Cash Sale", test);
		//Verifying Purchase Order
		soPage.verifyCashSaleandPO("Purchase Order", test);
		String sales_order_url=driver.getCurrentUrl();
		//Navigating to PO from Related Records
		soPage.navigatetoPO("Purchase Order");
		//Navigating to Item Receipt From PO
		poPage.navigateToReceiveFromPO("Fulfilment");
		itemReceiptPage.saveItemReceiptfromPO(SupplierDeliveryNote, test);
		driver.navigate().to(sales_order_url);
		soPage.waitUntilStockIsAutoCommitted(Quantity, test);
		itemfulfilmentPage.item_Fulfillment(Bin,Quantity,test); 
		itemfulfilmentPage.verify_fulfillment_status("SHIPPED",test);
		itemfulfilmentPage.verify_sales_order_status("BILLED",test);
		}
		catch(Exception e)
		{
			test.fail("Trade Order via Contact Centre - Carpet/Vinyl Credit Card is failed due to " +e.fillInStackTrace());
		}
			
	}
	
}
