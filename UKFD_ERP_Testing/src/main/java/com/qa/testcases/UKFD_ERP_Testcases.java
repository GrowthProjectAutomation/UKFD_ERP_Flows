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
	public Object[][] UKFD_SalesOrderViaContactCentre() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 0);
	}
	@DataProvider
	public Object[][] UKFD_TradeOrderViaContactCentre() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 1);
	}
	@DataProvider
	public Object[][] UKFD_TraderOrderViaContactCentre_30DaysPayment() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 2);
	}
	@DataProvider
	public Object[][] UKFD_PartialReturnPostShipment() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 3);
	}
	@DataProvider
	public Object[][] UKFD_FullReturnPostShipment() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 4);
	}
	@DataProvider
	public Object[][] TradeOrderCarpetVinyl() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 7);
	}
	@DataProvider
	public Object[][] CarpetAndUKFDFulfilledCarpetAccessory() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 5);
	}
	@DataProvider
	public Object[][] NewCarpetAndExistingCarpet() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 6);
	}
	@DataProvider
	public Object[][] TradeOrderViaContactCentreCarpet() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\UKFD_ERP_SHEET.xlsx", 8);
	}
	
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		testBase=new TestUtil();
		testBase.setUp();
	}

	//Testcase 4
//	@Test(dataProvider = "UKFD_SalesOrderViaContactCentre",priority = 1)
//	public void UKFD_SalesOrderViaContactCentre(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Credit_Card_Number,String Expiry_Date,String Security_Code,String NameOnCard,String Sales_Order_Form,String Customer_type,String Bin) throws Exception {
//		customerPage=new CustomerPage();
//		opprPage=new OpportunityPage();
//		quotePage=new QuotePage();
//		soPage=new SalesOrderPage();
//		String Terms="";
//		itemfulfilmentPage=new ItemFulfilment();
//		test=extent.createTest("Verifying New Sales Order creation via Contact Centre / Showroom  - Credit/Debit Card ");
//		customerPage.enter_Customer_details(Customer_Firstname, Customer_Lastname, Email, Phone, Address1, Address2, Address3, City, State, Zip, Customer_type,"Showroom",Credit_Card_Number,NameOnCard,Expiry_Date,Security_Code,Payment_Method, test);
//		opprPage=new OpportunityPage();
//		opprPage.enter_Opprnty_Details(Lead_source,test);
//		quotePage.enter_quote_details(Location,Item_Name,Quantity,Shipping_Method,test);
//		soPage.enter_SO_details(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_Firstname, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
//		soPage.verifyEmail("Thanks for your order!", test);
//		soPage.salesOrderApproval(test);
//		soPage.verifySOStatus("PENDING FULFILLMENT", test);
//		soPage.verifyEmail("Your order has been confirmed", test); 
//		soPage.verifyCashSaleandPO("Cash Sale", test); 
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
//		soPage.verifySOStatus("BILLED",test);
//	}
//	
	
	//Testcase 16
//	@Test(dataProvider = "UKFD_TradeOrderViaContactCentre",priority = 1)
//	public void UKFD_TradeOrderViaContactCentre(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Credit_Card_Number,String Expiry_Date,String Security_Code,String NameOnCard,String Sales_Order_Form,String Customer_type,String Bin) throws Exception {
//		customerPage=new CustomerPage();
//		opprPage=new OpportunityPage();
//		quotePage=new QuotePage();
//		soPage=new SalesOrderPage();
//		String Terms="";
//		itemfulfilmentPage=new ItemFulfilment();
//		test=extent.createTest("Verifying Trade Order via Contact Centre - Credit Card");
//		customerPage.enter_Customer_details(Customer_Firstname, Customer_Lastname, Email, Phone, Address1, Address2, Address3, City, State, Zip, Customer_type,"Trade Sales",Credit_Card_Number,NameOnCard,Expiry_Date,Security_Code,Payment_Method, test);
//		opprPage=new OpportunityPage();
//		opprPage.enter_Opprnty_Details(Lead_source,test);
//		quotePage.enter_quote_details(Location,Item_Name,Quantity,Shipping_Method,test);
//		soPage.enter_SO_details(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_Firstname, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
//		soPage.verifyEmail("Thanks for your order!", test);
//		soPage.salesOrderApproval(test);
//		soPage.verifySOStatus("PENDING FULFILLMENT", test);
//		soPage.verifyEmail("Your order has been confirmed", test); 
//		soPage.verifyCashSaleandPO("Cash Sale", test); 
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
//		soPage.verifySOStatus("BILLED",test);
//	}
	
	//Testcase 17
//	@Test(dataProvider = "UKFD_TraderOrderViaContactCentre_30DaysPayment",priority = 1)
//	public void UKFD_TraderOrderViaContactCentre_30DaysPayment(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String CreditCardType,String Credit_Card_Number,String Expiry_Date,String Security_Code,String NameOnCard,String Sales_Order_Form,String Customer_type,String Bin) throws Exception {
//		customerPage=new CustomerPage();
//		opprPage=new OpportunityPage();
//		quotePage=new QuotePage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		String Terms="";
//		try
//		{
//		test=extent.createTest("Verifying Trade Order via Contact Centre - Credit Account  (i.e. 30 days payment from despatch)");
//		customerPage.enter_Customer_details(Customer_Firstname, Customer_Lastname, Email, Phone, Address1, Address2, Address3, City, State, Zip, Customer_type,"Trade Sales",Credit_Card_Number,NameOnCard,Expiry_Date,Security_Code,CreditCardType, test);
//		opprPage.enterOpprntyDetails(Lead_source,test);
//		quotePage.enterQuoteDetails(Location,Item_Name,Quantity,Shipping_Method,test);
//		soPage.enterSoDetails(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_Firstname, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
//		soPage.verifyEmail("Thanks for your order!", test);
//		soPage.printProFormaInvoice("profromatest33.pdf",test);
//		soPage.emailProFormaInvoice("profromatest33.pdf",test);
//		soPage.verifyProFormaInvoice("profromatest33.pdf",test);	
//		soPage.salesOrderApproval(test);
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
//		soPage.verifySOStatus("PENDING BILLING",test);
//		}
//		catch(Exception e)
//		{
//			test.fail("Verifying Trade Order via Contact Centre - Credit Account  (i.e. 30 days payment from despatch) is failed due to  "+e.fillInStackTrace() );
//		}
//		
//	}

	//Testcase12
//	@Test(dataProvider = "UKFD_FullReturnPostShipment",priority = 1)
//	public void UKFD_FullReturnPostShipment(String Customer_Name,String Item_Name, String Quantity,String ReturnQuantity,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Security_Code,String Sales_Order_Form,String Return_Reason,String Return_Responsibility,String Return_Notes,String Return_Subtype,String Return_Reason3,String Return_reason4,String Supplier_Delivery_Note,String Bin) throws Exception
//	{
//		
//		test=extent.createTest("Verifying Full Order Return(Post Shipment)");
//		loginPage=new LoginPage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		returnPage=new ReturnAuthorizationPage();
//		itemReceiptPage=new ItemReceiptPage(); 
//		creditMemoPage=new CreditMemoPage();
//		customerRefundPage=new CustomerRefundPage();
//		String Terms="yes";
//		loginPage.choose_required_role("Customer Service");
//		soPage.navigateToSO();
//		soPage.enterSoDetails(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_Name, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
//		soPage.salesOrderApproval(test);
//		Map<String, String> paymentData = soPage.getPaymentData();
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
// 		soPage.navigate_to_return_authorization(test);
//		returnPage.saveReturnOrder(Quantity,ReturnQuantity,Return_Reason, Return_Responsibility, Return_Notes, Return_Subtype, Return_Reason3, Return_reason4,Supplier_Delivery_Note, test);
//		returnPage.navigateToItemReceipt("Fulfilment");
//		itemReceiptPage.saveItemReceipt(Supplier_Delivery_Note,test);
//		itemReceiptPage.verifyItemReceiptStatus(test);
//		itemReceiptPage.navigateToReturnAuthorization("Customer Service");
//		itemReceiptPage.navigateToCreditMemo("Finance");
//		creditMemoPage.saveCreditMemo();
//		creditMemoPage.verifyCreditMemoStatus(test);
//		creditMemoPage.navigateToCustomerRefund();
//		customerRefundPage.saveCustomerRefund(paymentData, test);
//		
//	}
	
	//Testcase 11
//	@Test(dataProvider = "UKFD_PartialReturnPostShipment",priority = 1)
//	public void UKFD_PartialReturnPostShipment(String Customer_Name,String Item_Name, String Quantity,String ReturnQuantity,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Security_Code,String Sales_Order_Form,String Return_Reason,String Return_Responsibility,String Return_Notes,String Return_Subtype,String Return_Reason3,String Return_reason4,String Supplier_Delivery_Note,String Bin) throws Exception
//	{
//		
//		test=extent.createTest("Verifying Partial Order Return (Post Shipmet)");
//		loginPage=new LoginPage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		returnPage=new ReturnAuthorizationPage();
//		itemReceiptPage=new ItemReceiptPage(); 
//		creditMemoPage=new CreditMemoPage();
//		customerRefundPage=new CustomerRefundPage();
//		String Terms="yes";
//		loginPage.choose_required_role("Customer Service");
//		soPage.navigateToSO();
//		soPage.enterSoDetails(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_Name, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
//		soPage.salesOrderApproval(test);
//		Map<String, String> paymentData = soPage.getPaymentData();
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
//		soPage.navigate_to_return_authorization(test);
//		returnPage.saveReturnOrder(Quantity,ReturnQuantity,Return_Reason, Return_Responsibility, Return_Notes, Return_Subtype, Return_Reason3, Return_reason4,Supplier_Delivery_Note, test);
//		returnPage.navigateToItemReceipt("Fulfilment");
//		itemReceiptPage.saveItemReceipt(Supplier_Delivery_Note,test);
//		itemReceiptPage.verifyItemReceiptStatus(test);
//		itemReceiptPage.navigateToReturnAuthorization("Customer Service");
//		itemReceiptPage.navigateToCreditMemo("Finance");
//		creditMemoPage.saveCreditMemo();
//		creditMemoPage.verifyCreditMemoStatus(test);
//		creditMemoPage.navigateToCustomerRefund();
//		customerRefundPage.saveCustomerRefund(paymentData, test);
//		
//	}
//	
	
	//Testcase 34
//	@Test(dataProvider = "TradeOrderCarpetVinyl",priority = 1)
//	public void TradeOrderCarpetVinyl(String Customer_Firstname,String Customer_Lastname,String Email, String Phone,String Address1,String Address2,String Address3,String City, String State, String Zip,String Lead_source,String Item_Name, String Quantity, String Location,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Credit_Card_Number,String Expiry_Date,String NameOnCard,String Security_Code,String Sales_Order_Form,String Customer_type,String SupplierDeliveryNote,String Bin) throws Exception 
//	{
//		String Terms="";
//		test=extent.createTest("Verifying Trade Order via Contact Centre - Carpet/Vinyl Credit Card");
//		customerPage=new CustomerPage();
//		opprPage=new OpportunityPage();
//		quotePage=new QuotePage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		itemReceiptPage =new ItemReceiptPage();
//		poPage=new PurchaseOrderPage();
//		try
//		{
//		customerPage.enter_Customer_details(Customer_Firstname, Customer_Lastname, Email, Phone, Address1, Address2, Address3, City, State, Zip, Customer_type, "Trade Sales",Credit_Card_Number,NameOnCard,Expiry_Date,Security_Code,Payment_Method, test);
//		opprPage.enterOpprntyDetails(Lead_source, test);
//		quotePage.enterQuoteDetails(Location, Item_Name, Quantity, Shipping_Method, test);
//		soPage.enterSoDetails(Sales_Order_Form, Delivery_Instructions, Shipping_Method, Customer_type, Item_Name, Quantity, Payment_Method,Terms, test);
//		soPage.verifyEmail("Thanks for your order!", test);
//		soPage.salesOrderApproval(test);
//		soPage.verifyProcessedScreen(test);
//		soPage.verifySOStatus("PENDING FULFILLMENT",test);
//		soPage.verifyEmail("Order Confirmation", test);
//		//Verifying Cash Sale
//		soPage.verifyCashSaleandPO("Cash Sale", test);
//		//Verifying Purchase Order
//		soPage.verifyCashSaleandPO("Purchase Order", test);
//		String sales_order_url=driver.getCurrentUrl();
//		//Navigating to PO from Related Records
//		soPage.navigatetoPO("Purchase Order");
//		//Navigating to Item Receipt From PO
//		poPage.navigateToReceiveFromPO("Fulfilment");
//		itemReceiptPage.saveItemReceiptfromPO(SupplierDeliveryNote, test);
//		driver.navigate().to(sales_order_url);
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
//		soPage.verifySOStatus("BILLED",test);
//		}
//		catch(Exception e)
//		{
//			test.fail("Trade Order via Contact Centre - Carpet/Vinyl Credit Card is failed due to " +e.fillInStackTrace());
//		}
//			
//	}
	
	
	//Testcase 35
	@Test(dataProvider = "TradeOrderViaContactCentreCarpet",priority = 1)
	public void TradeOrderViaContactCentreCarpet(String CustomerName,String Item_Name, String Quantity,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Security_Code,String Sales_Order_Form,String CustomerId,String SupplierDeliveryNote,String Bin) throws Exception 
	{
		
		String Customer_url="https://3460739-sb5.app.netsuite.com/app/common/entity/custjob.nl?id=";
		test=extent.createTest("Verifying Trade Order via Contact Centre - Credit Account (i.e. 30 days payment from despatch)");
		customerPage=new CustomerPage();
		soPage=new SalesOrderPage();
		itemfulfilmentPage=new ItemFulfilment();
		itemReceiptPage =new ItemReceiptPage();
		poPage=new PurchaseOrderPage();
		String Terms="";
		try
		{
		customerPage.navigateToCustomer("Trade Sales",Customer_url,CustomerId);
		customerPage.clickNewSOFromCustomer();
		soPage.enterSoDetails(Sales_Order_Form, Delivery_Instructions, Shipping_Method, CustomerName, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
		soPage.verifyEmail("Thanks for your order!", test);
		soPage.printProFormaInvoice("profromatest36.pdf",test);
		soPage.emailProFormaInvoice("profromatest36.pdf",test);
		soPage.verifyProFormaInvoice("profromatest36.pdf",test);	
		soPage.salesOrderApproval(test);
		soPage.verifyProcessedScreen(test);
		soPage.verifyEmail("Your order has been confirmed", test); 
		soPage.verifyCashSaleandPO("Purchase Order", test); 
		String sales_order_url=driver.getCurrentUrl();
		soPage.navigatetoPO("Purchase Order");
		poPage.navigateToReceiveFromPO("Fulfilment");
		itemReceiptPage.saveItemReceiptfromPO(SupplierDeliveryNote, test);
		driver.navigate().to(sales_order_url);
		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
		itemfulfilmentPage.navigateToSoFromFulfillment();
		soPage.verifySOStatus("BILLED",test);
	
		}
		catch(Exception e)
		{
			test.fail("Trade Order via Contact Centre - Credit Account (i.e. 30 days payment from despatch) is failed due to " +e.fillInStackTrace());
		}
			
	}
	
	
	//Testcase 29
//	@Test(dataProvider = "CarpetAndUKFDFulfilledCarpetAccessory",priority = 1)
//	public void CarpetAndUKFDFulfilledCarpetAccessory(String CustomerName,String Item_Name, String Quantity,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Security_Code,String Sales_Order_Form,String Terms,String CustomerId,String SupplierDeliveryNote,String Bin) throws Exception 
//	{
//		
//		String Customer_url="https://3460739-sb5.app.netsuite.com/app/common/entity/custjob.nl?id=";
//		test=extent.createTest("Verifying New Sales order via Contact Centre / Showroom - Carpet and UKFD fulfilled carpet accessories");
//		customerPage=new CustomerPage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		itemReceiptPage =new ItemReceiptPage();
//		poPage=new PurchaseOrderPage();
//		try
//		{
//		customerPage.navigateToCustomer("Sales Manager",Customer_url,CustomerId);
//		customerPage.clickNewSOFromCustomer();
//		soPage.enterSoDetails(Sales_Order_Form, Delivery_Instructions, Shipping_Method, CustomerName, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
//		soPage.verifyEmail("Thanks for your order!", test);
//		soPage.salesOrderApproval(test);
//		soPage.verifyProcessedScreen(test);
//		soPage.verifySOStatus("PENDING FULFILLMENT", test);
//		soPage.verifyEmail("Order Confirmation", test); 
//		soPage.verifyCashSaleandPO("Purchase Order", test); 
//		String sales_order_url=driver.getCurrentUrl();
//		soPage.navigatetoPO("Purchase Order");
//		poPage.navigateToReceiveFromPO("Fulfilment");
//		itemReceiptPage.saveItemReceiptfromPO(SupplierDeliveryNote, test);
//		driver.navigate().to(sales_order_url);
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
//		soPage.verifySOStatus("BILLED",test);
//		}
//		catch(Exception e)
//		{
//			test.fail("New Sales order via Contact Centre / Showroom - Carpet and UKFD fulfilled carpet accessories" +e.fillInStackTrace());
//		}
//			
//	}
	
	
	//Testcase 30
//	@Test(dataProvider = "NewCarpetAndExistingCarpet",priority = 1)
//	public void NewCarpetAndExistingCarpet(String CustomerName,String Item_Name, String Quantity,String Shipping_Method,String Delivery_Instructions,String Payment_Method,String Security_Code,String Sales_Order_Form,String Terms,String CustomerId,String SupplierDeliveryNote,String Bin) throws Exception 
//	{
//		
//		String Customer_url="https://3460739-sb5.app.netsuite.com/app/common/entity/custjob.nl?id=";
//		test=extent.createTest("Verifying New Sales order via Contact Centre / Showroom - Carpet and existing UKFD Flooring");
//		customerPage=new CustomerPage();
//		soPage=new SalesOrderPage();
//		itemfulfilmentPage=new ItemFulfilment();
//		itemReceiptPage =new ItemReceiptPage();
//		poPage=new PurchaseOrderPage();
//		try
//		{
//		customerPage.navigateToCustomer("Sales Manager",Customer_url,CustomerId);
//		customerPage.clickNewSOFromCustomer();
//		soPage.enterSoDetails(Sales_Order_Form, Delivery_Instructions, Shipping_Method, CustomerName, Item_Name, Quantity, Payment_Method.trim(),Terms, test);
//		soPage.verifyEmail("Thanks for your order!", test);
//		soPage.salesOrderApproval(test);
//		soPage.verifyProcessedScreen(test);
//		soPage.verifySOStatus("PENDING FULFILLMENT", test);
//		soPage.verifyEmail("Order Confirmation", test); 
//		soPage.verifyCashSaleandPO("Purchase Order", test); 
//		String sales_order_url=driver.getCurrentUrl();
//		soPage.navigatetoPO("Purchase Order");
//		poPage.navigateToReceiveFromPO("Fulfilment");
//		itemReceiptPage.saveItemReceiptfromPO(SupplierDeliveryNote, test);
//		driver.navigate().to(sales_order_url);
//		soPage.waitUntilStockIsAutoCommitted(Quantity, test,Item_Name);
//		itemfulfilmentPage.fulfillOrder(Item_Name, Quantity, Bin, test);
//		itemfulfilmentPage.navigateToSoFromFulfillment();
//		soPage.verifySOStatus("BILLED",test);
//		}
//		catch(Exception e)
//		{
//			test.fail("New Sales order via Contact Centre / Showroom - Carpet and existing UKFD Flooring is failed due to " +e.fillInStackTrace());
//		}
//			
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
