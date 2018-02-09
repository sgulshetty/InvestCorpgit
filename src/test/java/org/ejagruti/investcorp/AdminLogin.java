package org.ejagruti.investcorp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AdminLogin {
	@Test
	@Parameters({"username", "password" })
	public void Execute(String Username1,String Password1){
		System.out.println("Adminlogin");
		
		//---object declaration-----//
		//String Admin ="//b[.='Admin Panel']";
		//String Username = "//input[@placeholder='Username']";
		//String Password = "//input[@placeholder='Password']";
		//String Submit= "//input[@name='submit']";
		//String Logout = "//a[.='Logout']";
		LaunchApp.driver.findElement(By.xpath("//b[.='Admin Panel']")).click();
		LaunchApp.driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS );
		System.out.println("Admin Link has been clicked");
		
		LaunchApp.driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS );
		WebElement Login = LaunchApp.driver.findElement(By.xpath("//input[@placeholder='Username']"));
		Login.sendKeys(Username1);

		LaunchApp.driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS );
		WebElement Pass = LaunchApp.driver.findElement(By.xpath("//input[@placeholder='Password']"));
		Pass.sendKeys(Password1);
		
		WebElement submit = LaunchApp.driver.findElement(By.xpath("//input[@name='submit']"));
		submit.click();
		
		
	}

}
