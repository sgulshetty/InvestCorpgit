package org.ej.selenium.actions;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.ejagruti.generic.TextOperations;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Operations{

	public static WebDriver driver=null;
	public static WebDriverWait wait=null;
	private int timeout=30;
	private int Counter=0;
	private boolean LogEnabled=false;
	private boolean ResultEnabled=false;
	private String LogFilePath;
	private String ResultFolderpath;
	
	
	public Operations(boolean IsLogEnabled, String LogFolderpath)
	{
		LogEnabled=IsLogEnabled;
		LogFilePath=LogFolderpath+"\\Log_"+TextOperations.getDateTime().replace(" ", "_").replace(":","_")+".txt";
		if(LogEnabled)
		{
			TextOperations.CreateTextFile(LogFilePath);
			
		}
		
	}
	public Operations(String ResultFolderPath,boolean isResultEnabled)
	{
		ResultEnabled = isResultEnabled;
		this.ResultFolderpath = ResultFolderPath;
	}
	
	public Operations(){
	}
	
	
	public void TestSuiteStart(String SuiteName) throws UnknownHostException
	{
		HTMLReportGenerator.TestSuiteStart(ResultFolderpath+"\\"+SuiteName+"_Result_"+TextOperations.getDateTime("ddMMyyyyHHmmSSS")+".html","Santosh");
	}
	public void TestSuiteEnd()
	{
		HTMLReportGenerator.TestSuiteEnd();
	}
	public void TestCaseStart(String TCID,String Title)
	{
		HTMLReportGenerator.TestCaseStart(TCID, Title);
	}
	public void TestCaseEnd()
	{
		HTMLReportGenerator.TestCaseEnd();
	}

	public void LaunchApplication(String BrowserName,String URL,String WebDriverExePath) throws IOException
	{
	   try
	   {
			if(BrowserName.equalsIgnoreCase("ff"))
			{
				driver=new FirefoxDriver();
			}
			if(BrowserName.equalsIgnoreCase("ch"))
			{
				System.setProperty("webdriver.chrome.driver",WebDriverExePath);
				driver=new ChromeDriver();
			}
			if(BrowserName.equalsIgnoreCase("ie"))
			{
				System.setProperty("webdriver.ie.driver",WebDriverExePath);
				driver=new InternetExplorerDriver();
			}
	    	wait=new WebDriverWait(driver,timeout);
			driver.get(URL);
			driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to Launch Application";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath = TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				HTMLReportGenerator.StepDetails("PASS", "LaunchApplication", message, screenshotpath);}
			System.out.println(message);
			
			
	 }
	   
	   catch(Exception ex){
			String message=TextOperations.getDateTime()+"---ERROR----Step Number:"+(Counter++)+"Failed to launch Application"+"\n";
			System.out.println(message);
			if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath =TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				HTMLReportGenerator.StepDetails("FAIL", "LaunchApplication", message, screenshotpath);}
			throw new WebDriverException (message);
		}
	}
	
	public void Navigate(String URL){
		driver = new FirefoxDriver();
		driver.navigate().to(URL);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public WebElement IsObjectExists(String xPath) throws IOException
	{
	try
	{
	  WebElement obj=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Object was able to find";
		if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
		if(ResultEnabled){
			String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
			HTMLReportGenerator.StepDetails("PASS", "IsObject Exists", message, screenshotpath);}
		System.out.println(message);
		return obj;
	}
	  catch(Exception e){
		  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Object was not able to find";
		  System.out.println(message);
		  if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
		  if(ResultEnabled){
			  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
			  HTMLReportGenerator.StepDetails("FAIL", "IsObject Exists", message, screenshotpath);}
		  
		  throw new WebDriverException (message);
		  
		  
	  }
	}
	//###############Object######################
	public String ObjectGetAttributeValue(String xPath,String AttributeName) throws IOException
	{
	   try{
		   WebElement obj = IsObjectExists(xPath);
		   String op = obj.getAttribute(AttributeName);
		  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to Get the AttributeName";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				HTMLReportGenerator.StepDetails("PASS", "ObjectGetAttributeValue", message, screenshotpath);}
			System.out.println(message);
		   return op;
	   }
	   catch (Exception e){
		      String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Failed to Get the Attribute Value";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				  HTMLReportGenerator.StepDetails("FAIL", "ObjectGetAttributeValue", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
	   }

	}
	
	public void ObjectClick(String xPath) throws IOException
	{
		 try{
			   WebElement obj=IsObjectExists(xPath);
			   obj.click();
			   String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to Click on the Object";
				if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
				if(ResultEnabled){
					String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
					HTMLReportGenerator.StepDetails("PASS", "ObjectClick", message, screenshotpath);}
				System.out.println(message);
			   
		 }
		 catch(Exception e){
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Failed to Click on the Object";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				  HTMLReportGenerator.StepDetails("FAIL", "ObjectClick", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
			 
		 }
	}
	
	
	public void ObjectDoubleClick(String xPath) throws IOException
	{
	    try{
		WebElement obj=IsObjectExists(xPath);
		Actions act=new Actions(driver);
		act.doubleClick(obj).build().perform();
		String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to doubleClick on the Object";
		if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
		if(ResultEnabled){
			String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
			HTMLReportGenerator.StepDetails("PASS", "ObjectDoubleClick", message, screenshotpath);}
		System.out.println(message);
	  }
	    catch(Exception e){
	    	String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Failed to doubleClick on the Object";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				  HTMLReportGenerator.StepDetails("FAIL", "ObjectDoulbeClick", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
	    	
	    }
	}
	
	public void ObjectRightClick(String xPath) throws IOException{
		try{
			WebElement obj=IsObjectExists(xPath);
			Actions act=new Actions(driver);
			act.contextClick(obj).build().perform();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to RightClick on the Object";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){ 
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				HTMLReportGenerator.StepDetails("PASS", "ObjectRightClick", message, screenshotpath);}
			System.out.println(message);
			
		}
		catch(Exception e){
			  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Failed to RightClick on the Object";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				  HTMLReportGenerator.StepDetails("FAIL", "ObjectRightClick", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
			
		}
		
	}
	
	//###############TextBox######################
	public void TextBoxSetValue(String xPath,String Value) throws InterruptedException, IOException
	{
	  try	{
		WebElement obj=IsObjectExists(xPath);
		obj.clear();
		Thread.sleep(1000);
		obj.sendKeys(Value);
		String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" TextBox Value has been Set Successfully";
		if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
		if(ResultEnabled){
			String screenshotpath =	TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
			HTMLReportGenerator.StepDetails("PASS", "TextBoxSetValue", message, screenshotpath);}
		System.out.println(message);
	    }
	  catch(Exception e){
		  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" TextBox Value has Failed to Set";
		  System.out.println(message);
		  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
		  if(ResultEnabled){
			  String screenshotpath=  TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
			  HTMLReportGenerator.StepDetails("FAIL", "ObjectRightClick", message, screenshotpath);}
		  
		  throw new WebDriverException (message);
		  
	  }
	}
	
	
	public void TextBoxAppendValue(String xPath,String Value) throws IOException
	{
		
	try
	{
		WebElement obj=IsObjectExists(xPath);
		obj.sendKeys(Value);
		String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" TextBox Value has been Appended Successfully";
		if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
		if(ResultEnabled){
			String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
			HTMLReportGenerator.StepDetails("PASS", "TextBoxAppendValue", message, screenshotpath);}
		System.out.println(message);
	}
	  catch(Exception e){
		  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" TextBox Value Appending has Failed";
		  System.out.println(message);
		  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
		  if(ResultEnabled){
			  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
			  HTMLReportGenerator.StepDetails("FAIL", "TextBoxAppendValue", message, screenshotpath);}
		  
		  throw new WebDriverException (message);
		  
	  }
	}
	
	//#################Table#####################
		public int TableGetRowCount(String xPath) throws IOException
		{
			try{ 
			WebElement obj=IsObjectExists(xPath);
			int count =  obj.findElements(By.tagName("tr")).size();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Table Row count";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				HTMLReportGenerator.StepDetails("PASS", "TableGetRowCount", message, screenshotpath);}
			System.out.println(message);
			return count;
		}
			catch(Exception e){
				String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to get the Table Row Count";
				  System.out.println(message);
				  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
				  if(ResultEnabled){
					  String screenshotpath=  TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
					  HTMLReportGenerator.StepDetails("FAIL", "TableGetRowCount", message, screenshotpath);}
				  
				  throw new WebDriverException (message);
				
			}
		}
		
		
		public int TableGetColumnCount(String xPath,int RowNumber) throws IOException
		{
			
		 try
		{
			WebElement obj=IsObjectExists(xPath);
			int count = obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).size();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to get Table Column count";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				HTMLReportGenerator.StepDetails("PASS", "TableGetColumnCount", message, screenshotpath);}
			System.out.println(message);
			return count;
		}
		 catch(Exception e){
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to get the Table Column Count";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				  HTMLReportGenerator.StepDetails("FAIL", "TableGetColumnCount", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
			 
		 }
		
		}
		public String TableGetCellValue(String xPath,int RowNumber,int ColumnNumber) throws IOException
		{
			
		
		 try{
			WebElement obj=IsObjectExists(xPath);
			String  CellValue = obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).get(ColumnNumber).getText();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to get Table CellValue";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				HTMLReportGenerator.StepDetails("PASS", "TableGetCellValue", message, screenshotpath);}
			System.out.println(message);
			return CellValue;
		}
		 catch(Exception e){
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to get the Table Cell Value";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=  TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				  HTMLReportGenerator.StepDetails("FAIL", "TableGetCellValue", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
		 }
			 
		 }
		//######################Frame#################
		public WebDriver FrameSwitchByIndex(int Index) throws IOException
		{
		 try{	
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Frame Switched By Index";
				if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
				if(ResultEnabled){
					String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
					HTMLReportGenerator.StepDetails("PASS", "FrameSwitchByIndex", message, screenshotpath);}
				System.out.println(message);
			return   driver.switchTo().frame(Index);
		}
		 catch(Exception e){
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to Switch the Frame";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath+"\\png\\", driver);
				  HTMLReportGenerator.StepDetails("FAIL", "FrameSwitchByIndex", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
			 
		 }
		}
		
		
		public WebDriver FrameSwitchByXPath(String xPath) throws IOException
		{
			
		try
		{
			 WebElement obj=  driver.findElement(By.xpath(xPath));
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Frame Switched By Xpath";
				if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
				if(ResultEnabled){
					String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
					HTMLReportGenerator.StepDetails("PASS", "FrameSwitchByXPath", message, screenshotpath);}
				System.out.println(message);
			 return  driver.switchTo().frame(obj);
		}
		catch(Exception e){
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to Switch the Frame By Xpath";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				  HTMLReportGenerator.StepDetails("FAIL", "FrameSwitchByXPath", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
		}
		}
		
		public WebDriver FrameSwitchByName(String NameOfTheFrame) throws IOException
		{
		 try	
		 {		
			  
			  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Frame Switched By Name";
				if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
				if(ResultEnabled){
					String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
					HTMLReportGenerator.StepDetails("PASS", "FrameSwitchByName", message, screenshotpath);}
				System.out.println(message);
				return  driver.switchTo().frame(NameOfTheFrame);
		}
		 catch(Exception e){
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to Switch the Frame By Name";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				  HTMLReportGenerator.StepDetails("FAIL", "FrameSwitchByName", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
		 }
			 
		 }
		//######################Drop Down#################
		public void DropDownSelectByVisibleText(String xPath,String Value) throws IOException
		{
		try	
		
		{
			WebElement obj=IsObjectExists(xPath);
			Select sel=new Select(obj);
			sel.selectByVisibleText(Value);
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Dropdown Select By Visible Text ";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				HTMLReportGenerator.StepDetails("PASS", "DropDownSelectByVisibleText", message, screenshotpath);}
			System.out.println(message);
		}
		catch(Exception e){
			  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to Dropdown Select By Visible Text";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=  TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				  HTMLReportGenerator.StepDetails("FAIL", "DropDownSelectByVisibleText", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
			
		}
		}
		public void DropDownSelectByIndex(String xPath,int Index) throws IOException
		{
			try{
			WebElement obj=IsObjectExists(xPath);
			Select sel=new Select(obj);
			sel.selectByIndex(Index);
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Dropdown Select By Index ";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				HTMLReportGenerator.StepDetails("PASS", "DropDownSelectByIndex", message, screenshotpath);}
			System.out.println(message);
		}
			catch(Exception e){
				  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to Dropdown Select By Index";
				  System.out.println(message);
				  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
				  if(ResultEnabled){
					  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
					  HTMLReportGenerator.StepDetails("FAIL", "DropDownSelectByIndex", message, screenshotpath);}
				  
				  throw new WebDriverException (message);
				
			}
		}
			
		public void DropDownSelectByOptionValue(String xPath,String OptionValue) throws IOException
		{
		try{
			WebElement obj=IsObjectExists(xPath);
			Select sel=new Select(obj);
			sel.selectByValue(OptionValue);
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Dropdown Select By Option Value ";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				HTMLReportGenerator.StepDetails("PASS", "DropDownSelectByOptionValue", message, screenshotpath);}
			System.out.println(message);
		    }
		catch(Exception e){
			   String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to Dropdown Select By Option Value";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath=  TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				  HTMLReportGenerator.StepDetails("FAIL", "DropDownSelectByOptionValue", message, screenshotpath);}
			  
			  throw new WebDriverException (message);
			}
		}
		public String DropDownGetSelectedValue(String xPath) throws IOException
		{
		  try{	
			WebElement obj=IsObjectExists(xPath);
			Select sel=new Select(obj);
			String SelValue = sel.getFirstSelectedOption().getText();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" DropDownGetSelectedValue Select By Option Value ";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				HTMLReportGenerator.StepDetails("PASS", "DropDownGetSelectedValue", message, screenshotpath);}
			System.out.println(message);
			return SelValue;
		}
		  catch(Exception e){
			  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to DropDownGetSelectedValue Select By Option Value";
			  System.out.println(message);
			  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
			  if(ResultEnabled){
				  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				  HTMLReportGenerator.StepDetails("FAIL", "DropDownGetSelectedValue", message, screenshotpath);}
			  throw new WebDriverException (message);
		  }
		  }
		public ArrayList<String> DropDownGetAllSelectedValue(String xPath) throws IOException
		{
			try{
			WebElement obj=IsObjectExists(xPath);
			Select sel=new Select(obj);
			ArrayList<String> allSelectedValue=new ArrayList<String>();
			 for(WebElement ele: sel.getAllSelectedOptions())
			 {
				 allSelectedValue.add(ele.getText());
			 }
			 String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" DropDownGetAllSelectedValue can be done ";
				if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
				if(ResultEnabled){
					String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
					HTMLReportGenerator.StepDetails("PASS", "DropDownGetAllSelectedValue", message, screenshotpath);}
				System.out.println(message);
				return allSelectedValue;
		}
			catch(Exception e){
				String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to DropDownGetAllSelectedValue cannot be done";
				  System.out.println(message);
				  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
				  if(ResultEnabled){
					  String screenshotpath=  TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
					  HTMLReportGenerator.StepDetails("FAIL", "DropDownGetAllSelectedValue", message, screenshotpath);}
				  throw new WebDriverException (message);
			}
			}
		
		//###############CheckBox################
		public void Checkbox(String xPath) throws IOException
		{
			try{
			WebElement obj=IsObjectExists(xPath);
			obj.click();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to do the Checkbox Selection ";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				HTMLReportGenerator.StepDetails("PASS", "Checkbox", message, screenshotpath);}
			System.out.println(message);
			}
			catch(Exception e){
				  String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to do the Checkbox Selection";
				  System.out.println(message);
				  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
				  if(ResultEnabled){
					  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
					  HTMLReportGenerator.StepDetails("FAIL", "Checkbox", message, screenshotpath);}
				  throw new WebDriverException (message);
				
			}
			
		}
		
		public void Exit(){
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
		
		public void UnCheckbox(String xPath) throws IOException{
			try{
			WebElement obj=IsObjectExists(xPath);
			obj.click();
			String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Able to do the UnCheckbox Selection ";
			if(LogEnabled){TextOperations.AppendTextFile(LogFilePath, message);}
			if(ResultEnabled){
				String screenshotpath=TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
				HTMLReportGenerator.StepDetails("PASS", "Checkbox", message, screenshotpath);}
			System.out.println(message);
		}
			catch(Exception e){
				String message=TextOperations.getDateTime()+"Step Number:"+(Counter++)+" Not Able to do the UnCheckbox Selection";
				  System.out.println(message);
				  if(LogEnabled){	TextOperations.AppendTextFile(LogFilePath, message);}
				  if(ResultEnabled){
					  String screenshotpath= TakeScreenShot.TakeScreenShot(ResultFolderpath, driver);
					  HTMLReportGenerator.StepDetails("FAIL", "Checkbox", message, screenshotpath);}
				  throw new WebDriverException (message);
			}
			}
		
		//#########Alert########################
		public void AlertAccept(String xPath){
			Alert al = driver.switchTo().alert();
			al.accept();
		}
		
		public void AlertReject(String xPath){
			Alert al = driver.switchTo().alert();
				al.dismiss();
		}
		
private void LoginTestCase_TC1(Operations op) throws InterruptedException, IOException{
			System.out.println("I am in TC1");
			op.LaunchApplication("ff", "http://localhost:90/finsys", "");
			op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
			op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
			op.ObjectClick("//span[.='Login']");
  String val=op.ObjectGetAttributeValue("//a[.='LOGOUT']", "innerText");
  System.out.println(val);
     if(val.equalsIgnoreCase("LOGOUT")){
    	 if(ResultEnabled){
    		 TextOperations.AppendTextFile(ResultFolderpath, "Test Case 1 is passed");	}
	 		System.out.println("Test Case 1 is passed");
     }
     else{
    	 if(ResultEnabled){TextOperations.AppendTextFile(ResultFolderpath, "Test Case 1 is Failed");	}
	 		System.out.println("Test Case 1 is Failed");	
     }
		}

private void LoginFailTestCase_TC2(Operations op) throws InterruptedException, IOException{
	System.out.println("I am in TC2");
	op.LaunchApplication("ff", "http://localhost:90/finsys", "");
	op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfmxsd");
	op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
	op.ObjectClick("//span[.='Login']");
	WebElement logfail = driver.findElement(By.xpath("//div[@id='error']"));
	String error = logfail.getText();
	if(error.equalsIgnoreCase("Please Enter Valid Username or Password!!!")){
   	 if(ResultEnabled){TextOperations.AppendTextFile(ResultFolderpath, "Test Case 2 is Passed");	}
	 		System.out.println("Test Case 2 is passed");
	}
	else
		if(ResultEnabled){TextOperations.AppendTextFile(ResultFolderpath, "Test Case 2 is Failed");	}
		System.out.println("Test Case 2 is Failed");
}

private void CreateNewCompany_TC3(Operations op) throws IOException, InterruptedException{
	System.out.println("I am in TC3");
	op.LaunchApplication("ff", "http://localhost:90/finsys", "");
	op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
	op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
	op.ObjectClick("//span[.='Login']");
	op.ObjectClick("//a[.='Manage Company']");
	//op.FrameSwitchByIndex(1);
	//op.ObjectClick("//span[.='New']");
}




	public static void main(String[] args) throws InterruptedException, IOException {
	
	/*	Operations op=new Operations(true,"C:\\Users\\dell\\Desktop\\Logs");
		op.LaunchApplication("ff", "http://toolsqa.wpengine.com/automation-practice-form", "");
		//op.Navigate("http://localhost:90/finsys");
		//op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
		//op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		op.Check("//*[@id='profession-0']");
		
		System.out.println("in main"); */
				
		Operations op=new Operations("C:\\Users\\dell\\Desktop\\Selenium\\result", true);
		//Operations op1=new Operations(true,"C:\\Users\\dell\\Desktop\\Selenium\\Log");
		op.TestSuiteStart("Smoke Testing");
		
		op.TestCaseStart("Tc001", "Verify the Valid Login Functionality");
		op.LoginTestCase_TC1(op);
		op.TestCaseEnd();
		
		//op.TestCaseStart("TC002", "Verify Invalid Username and Password Functionality");
		//op.LoginFailTestCase_TC2(op);
		//op.TestCaseEnd();
		
		//op.TestCaseStart("TC003", "Verify Creation of New Company");
		//op.CreateNewCompany_TC3(op);
		//op.TestCaseEnd();
		
		op.TestSuiteEnd();
			
	}

	

}
