package org.ej.selenium.actions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
//*import org.ejagruti.generic.Generic.UniqueValueEnum;
//import org.ejagruti.modules.LaunchApp;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 * @How to Use: Pass the path of the image where you want to store the snapshot
 * @author ABHISHEK PRIYADARSHI
 *
 */
public class TakeScreenShot {
	public static String TakeScreenShot(String ImagePath,WebDriver driver) throws IOException{
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(ImagePath);
		//FileUtils.copyFile(src, dest);
		FileUtils.copyFileToDirectory(src, dest);
		String s = src.toString();  // This line stores the image file path where it is located
		//System.out.println(s);  // Prints like - C:\Users\dell\AppData\Local\Temp\screenshot6054167561213842697.png
		return s;  // Returns the image file path back
	}
	public static void main(String[] args) throws InterruptedException, IOException
	{
		WebDriver driver=new FirefoxDriver();
		driver.get("http://localhost:90/finsys");
		Thread.sleep(10000);
		TakeScreenShot("C:\\Users\\dell\\Desktop\\Selenium\\result\\png1\\",driver);
	}

}
