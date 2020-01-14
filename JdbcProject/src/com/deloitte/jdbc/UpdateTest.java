package com.deloitte.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {

		String DRIVER = "", URL = "", USER = "", PASSWORD = "";
		Scanner scan = new Scanner(System.in);
		String query = "update employee set age =?,design=? where id =?";
		try {

			Map<String, String> dbConfigMap = new DBReader().getConfigMap();
			DRIVER = dbConfigMap.get("DRIVER");
			URL = dbConfigMap.get("URL");
			USER = dbConfigMap.get("USER");
			PASSWORD = dbConfigMap.get("PASSWORD");
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(query);
			System.out.println("Enter <id> <new-age> <new-desig>");
			// Scanner : int int String
			stmt.setInt(3, scan.nextInt());
			stmt.setInt(1, scan.nextInt());
			stmt.setString(2, scan.next());
			int rows = stmt.executeUpdate();
			if (rows > 0) {
				System.out.println("Data inserted in table");
			} else {
				System.out.println("Insert failed");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
