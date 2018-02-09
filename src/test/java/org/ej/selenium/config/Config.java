package org.ej.selenium.config;

import org.ej.selenium.actions.Operations;
import org.ej.selenium.actions.PropertiesFileReader;

public class Config {
	public static  Operations op=new Operations();
	public static PropertiesFileReader pfr=new PropertiesFileReader("Parameters");
	public static PropertiesFileReader or=new PropertiesFileReader("or");
	
}
