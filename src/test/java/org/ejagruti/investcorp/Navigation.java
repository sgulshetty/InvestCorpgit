package org.ejagruti.investcorp;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Navigation {
@Test
@Parameters({"Menu", "SubMenu" })
public void Execute(String Menu1, String SubMenu1){
		System.out.println("Navigation Start");
		System.out.println("Menu is - " +Menu1);
		System.out.println("SubMenu is - " +SubMenu1);
		//------Object declaration------//
		//String Menu1="//span[.='View / Edit']";
		//String SubMenu1="//a[.='Branches']";
		//String SubMenu2 = "//a[.='Cities']";
		//String SubMenu3 = "//a[.='Accounts']";
		//String SubMenu3 = "//a[.='States']";
		//String SubMenu3 = "//a[.='Customers']";
		LaunchApp.driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS );
		if (Menu1.equals("View/Edit"))
		               {
			LaunchApp.driver.findElement(By.xpath("//span[.='View / Edit']")).click();		
			if (SubMenu1.equals("Branches")){
				LaunchApp.driver.findElement(By.xpath("//a[.='Branches']")).click();
				
			}
			if (SubMenu1.equals("Cities")){
				LaunchApp.driver.findElement(By.xpath("//a[.='Cities']")).click();
			}
			if (SubMenu1.equals("Accounts")){
				LaunchApp.driver.findElement(By.xpath("//a[.='Accounts']")).click();
		    }
			if (SubMenu1.equals("States")){
				LaunchApp.driver.findElement(By.xpath("//a[.='States']")).click();
		    }
		}
		
		if(Menu1.equals("INSURANCE")){
			LaunchApp.driver.findElement(By.xpath("//a[.='Insurance']")).click();
			if(SubMenu1.equals("PREMIUM CALCULATOR")){
				LaunchApp.driver.findElement(By.xpath("//a[.='Premium Calculator']")).click();
			}
			
		}
		
		
	}

}
