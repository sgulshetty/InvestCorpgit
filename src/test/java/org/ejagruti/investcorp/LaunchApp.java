package org.ejagruti.investcorp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




public class LaunchApp {
	public static WebDriver driver;
	@Test
	@Parameters("URL")
	public void Launch(String url1){
		
		//System.setProperty("webdriver.gecko.driver", "C:\\WS_ejagruti\\InvestCorp\\All Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS );
		driver.get(url1);
		
		driver.manage().window().maximize();
			
		
		
	}

}
