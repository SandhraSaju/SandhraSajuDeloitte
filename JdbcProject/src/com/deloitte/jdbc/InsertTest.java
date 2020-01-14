package com.deloitte.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {

		Properties props = new Properties();
		String DRIVER = "", URL = "", USER = "", PASSWORD = "";
		Scanner scan = new Scanner(System.in);
		String query = " insert into employee(id,name,age,design,doj,dept) values (?,?,?,?,?,?)";
		try {
	
			props.load(new FileInputStream("db.config"));
			Enumeration em = props.propertyNames();
			String key = (String) em.nextElement();
			DRIVER = props.getProperty("DRIVER");
			URL = props.getProperty("URL");
			USER = props.getProperty("USER");
			PASSWORD = props.getProperty("PASSWORD");
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(query);
			System.out.println("Enter <id> <name> <age> <design> <doj> <dept>");
			stmt.setInt(1,scan.nextInt());
			stmt.setString(2, scan.next());
			stmt.setInt(3, scan.nextInt());
			stmt.setString(4,scan.next());
			stmt.setString(5, scan.next());
			stmt.setInt(6, scan.nextInt());
			
			
			
			int rows = stmt.executeUpdate();
			if(rows > 0)
			{
				System.out.println("Data inserted in table");
			}
			else
			{
				System.out.println("Insert failed");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
