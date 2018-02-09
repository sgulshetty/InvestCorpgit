package org.ejagruti.investcorp;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClientLogin {
	@Test
    @Parameters({"username", "password" })
public void Execute(String Username1,String Password1){
System.out.println("ClientLogin");	

//................object declaration

//String SignIn = "//*[.='Sign In']";
//String Username = "//input[@placeholder='Username']";
//String Password = "//input[@placeholder='Password']";
//String Submit= "//input[@name='submit']";
//String Logout = "//a[.='Logout']";
//WebDriver driver1 = new FirefoxDriver();


LaunchApp.driver.findElement(By.xpath("//*[.='Sign In']")).click();
LaunchApp.driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS );

System.out.println("Sign has been clicked");

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

