package org.ejagruti.testcases;

import java.io.IOException;
import java.net.UnknownHostException;

import org.ej.selenium.actions.Operations;
import org.ej.selenium.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TESTCASE2 {
    
	@Test()
	public void TESTCASE2() throws IOException, InterruptedException {
		
		Operations op1 = Config.op;
		op1.TestSuiteStart("Smoke Regression Testing");
		op1.TestCaseStart("TC2", "Verify Invalid Login Credential check Failure");
		System.out.println("Test Case 2 Execution Starts");
		op1.LaunchApplication(Config.pfr.GetParameterValue("_browser"), Config.pfr.GetParameterValue("_url"), "");
		op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_username"), Config.pfr.GetParameterValue("_invalid_username"));
		op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_password"), Config.pfr.GetParameterValue("_invalid_password"));
		op1.ObjectClick(Config.or.GetParameterValue("_xpath_Loginbtn"));
		WebElement logfail = Operations.driver.findElement(By.xpath(Config.or.GetParameterValue("_xpath_loginerror")));
		String error = logfail.getText();
		if(error.equalsIgnoreCase("Please Enter Valid Username or Password!!!"))
		{
			System.out.println("Test Case 2 is passed");
		}
		else{
			System.out.println("Test Case 2 is Failed");
		}
		op1.Exit();
}
			
			
		
		
			
	
		
			
	}
		


