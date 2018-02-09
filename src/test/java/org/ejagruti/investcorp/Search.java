package org.ejagruti.investcorp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Search {
	@Test
	@Parameters({"Selectcoulumn","SearchString","SubMenu" })
public void Execute(String Selectcoulumn1,String SearchString1,String Submenu1){
		System.out.println("Search");
		System.out.println("Search Column: " + Selectcoulumn1);
		System.out.println("Search String: " + SearchString1);
		
		if (Submenu1.equals("Branches"))
		{
		WebElement search = LaunchApp.driver.findElement(By.xpath("//*[@id='searchtext']"));
		search.sendKeys(SearchString1);
		WebElement col = LaunchApp.driver.findElement(By.xpath("//*[@id='searchdropdown']"));
		Select selcol = new Select(col);
		selcol.selectByVisibleText("IFSC CODE");
		WebElement img = LaunchApp.driver.findElement(By.xpath("//img[@src='images/search.jpg']"));
		img.click();
		}
		
		if (Submenu1.equals("Cities"))
		{
		LaunchApp.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);	
		WebElement search = LaunchApp.driver.findElement(By.xpath("//*[@id='searchtext']"));
		search.sendKeys(SearchString1);
		WebElement col = LaunchApp.driver.findElement(By.xpath("//*[@id='searchdropdown']"));
		Select selcol = new Select(col);
		selcol.selectByVisibleText("CITYNAME");
		WebElement img = LaunchApp.driver.findElement(By.xpath("//img[@src='images/search.jpg']"));
		img.click();
		LaunchApp.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}
	}
//-------Object declaration------//
	String Search = "//*[@id='searchtext']";
	String SelectC="//*[@id='searchdropdown']";
	String image = "//img[@src='images/search.jpg']";
}
