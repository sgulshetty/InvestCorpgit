package org.ejagruti.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import org.ej.selenium.actions.Operations;
import org.ej.selenium.config.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TESTCASE1 {
	
	@Test
	public void TESTCASE1() throws IOException, InterruptedException{

		Operations op1 = Config.op;
		op1.TestSuiteStart("Smoke Regression Testing");
		op1.TestCaseStart("TC1", "Verify Valid Login Credential check");
		System.out.println("Test Case 1 Execution Starts");
		op1.LaunchApplication(Config.pfr.GetParameterValue("_browser"), Config.pfr.GetParameterValue("_url"),"");
		op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_username"), Config.pfr.GetParameterValue("_username"));
		op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_password"), Config.pfr.GetParameterValue("_password"));
		op1.ObjectClick(Config.or.GetParameterValue("_xpath_Loginbtn"));
		String val = op1.ObjectGetAttributeValue(Config.or.GetParameterValue("_xpath_Logoutbtn"), "innerText");
		System.out.println(val);
		
		if(val.equalsIgnoreCase("LOGOUT")){
			System.out.println("Test Case 1 is passed");
		}
		else{
			System.out.println("Test Case 1 is Failed");
		}
			
		op1.Exit();
		
		
	}
	}
	


