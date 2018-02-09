package org.ejagruti.testcases;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.ej.selenium.actions.Operations;
import org.ej.selenium.config.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TESTCASE3 {
	@Test
	public void TESTCASE3() throws IOException, InterruptedException{
		
		Operations op1 = Config.op;
        op1.TestSuiteStart("Regression Suite Start");
        op1.TestCaseStart("TC3", "Verify Creation of a New Company");
        System.out.println("Start of TestCase3 - Creation of New Company");
        op1.LaunchApplication(Config.pfr.GetParameterValue("_browser"), Config.pfr.GetParameterValue("_url"),"");
		op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_username"), Config.pfr.GetParameterValue("_username"));
		op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_password"), Config.pfr.GetParameterValue("_password"));
		op1.ObjectClick(Config.or.GetParameterValue("_xpath_Loginbtn"));
		op1.ObjectClick(Config.or.GetParameterValue("_xpath_MCompany"));
		WebDriver frame = op1.FrameSwitchByIndex(0);
		op1.ObjectClick(Config.or.GetParameterValue("_xpath_Newbutton"));
		op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_CompName"), Config.pfr.GetParameterValue("_CompName"));
        op1.DropDownSelectByVisibleText(Config.or.GetParameterValue("_xpath_CompType"), Config.pfr.GetParameterValue("_CompType"));
        op1.DropDownSelectByVisibleText(Config.or.GetParameterValue("_xpath_CompSubType"), Config.pfr.GetParameterValue("_CompSubType"));
        op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_Address"), Config.pfr.GetParameterValue("_CompAddress"));
        op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_phone"), Config.pfr.GetParameterValue("_phone"));
        op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_email"), Config.pfr.GetParameterValue("_email"));
        op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_pan"), Config.pfr.GetParameterValue("_pan"));
        op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_tin"), Config.pfr.GetParameterValue("_tin"));
        op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_Mobile"), Config.pfr.GetParameterValue("_mobile"));
        op1.TextBoxSetValue(Config.or.GetParameterValue("_xpath_website"), Config.pfr.GetParameterValue("_website"));
	}

}
