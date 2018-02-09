package org.ejagruti.investcorp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Logout {
	@Test
	public void close() {
		
		LaunchApp.driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS );
		WebElement logout = LaunchApp.driver.findElement(By.xpath("//a[.='Logout']"));
		logout.click();
		
		System.out.println("Logged out of Application");
		LaunchApp.driver.close();
		
	}
	

}
