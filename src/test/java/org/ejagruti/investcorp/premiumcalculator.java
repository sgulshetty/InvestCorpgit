package org.ejagruti.investcorp;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class premiumcalculator {

	@Test
	@Parameters("")
public void Execute(String Testdata){
		System.out.println("Premiumcalculator");
	}
	//----------Object declaration-----//
	String Insu = "//a[.='Insurance']";
	String PremCal = "//a[.='Premium Calculator']";
	String PName ="//select[@id='idpolicyname']";
	String Ptenure = "//select[@id='idpolicytenure']";
	String TotalAdults = "//select[@id='idadults']";
	String Totalchilds = "//select[@id='idchilds']";
	String CityType = "//select[@id='idcitytype']";
	String AgeGroup = "//select[@id='idagegroup']";
	String InvestCorpEmploy = "//select[@id='idemployee']";
	String SumAssure = "//select[@id='idsi']";
	

}
