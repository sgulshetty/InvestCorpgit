package org.ej.selenium.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SGL {

	public static WebDriver driver=null;
	public static WebDriverWait wait=null;
	
	public void LaunchApplication(String BrowserName,String URL)
	
	{
		if(BrowserName.equals("Firefox"))
		{
	  driver = new FirefoxDriver();
	  driver.get(URL);	  	  
		}
		wait =new WebDriverWait(driver,10);				
	}
	//----button
	
	public void ButtonClick(String xPath)
	{
	  WebElement retobj=CheckObjectExists(xPath);
	  retobj.click();
	}
	
	public WebElement CheckObjectExists(String xPath)
	{
	  WebElement obj=	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	  return obj;
	}
	
	
	public static void main(String[] args) {
            SGL ss = new SGL();
           
            ss.LaunchApplication("Firefox", "http://server:90/finsys/seleniumtask.htm#");
            driver.findElement(By.xpath("//a[.='Button']")).click();
            
            
            ss.ButtonClick("//input[@name='ejagruti']");
           // ss.LaunchApplication("Chrome", "http://localhost:1979/investcorp1/mainpage/index.html");
            
	}

}
