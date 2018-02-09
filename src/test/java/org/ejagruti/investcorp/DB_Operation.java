package org.ejagruti.investcorp;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Operation {
	public static void main(String[] args) throws InstantiationException,IllegalAccessException,ClassNotFoundException,SQLException
	{
Class.forName("com.mysql.jdbc.Driver").newInstance();
java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finsys","root","admin");
Statement st = conn.createStatement();
ResultSet res = st.executeQuery("Select * from cities");

while(res.next()){
	System.out.println(res.getString("cityname"));
}

	}
	public static java.sql.Connection GetDBConnection(String dbServer,String dbName,String dbUser,String dbPassword,String dbPort)
	{
		return null;
	}
	
	}
	
