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

public class DeleteTest {

	public static void main(String[] args) {

		String DRIVER = "", URL = "", USER = "", PASSWORD = "";
		Scanner scan = new Scanner(System.in);
		String query = "delete from employee where id=?";
		try {

			Map<String, String> dbConfigMap = new DBReader().getConfigMap();
			DRIVER = dbConfigMap.get("DRIVER");
			URL = dbConfigMap.get("URL");
			USER = dbConfigMap.get("USER");
			PASSWORD = dbConfigMap.get("PASSWORD");
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);// this will prevent commit automatically 
			PreparedStatement stmt = conn.prepareStatement(query);
			System.out.println("Enter <id> to delete");
			// Scanner : int
			stmt.setInt(1, scan.nextInt());
			System.out.println("Are you sure you want to delete record?[Y/N]");
			String response = scan.next();
			if (response.equalsIgnoreCase("Y")) {
				int rows = stmt.executeUpdate();
				if (rows > 0) {
					System.out.println("Data deleted from table");
					conn.commit();
				} else {
					System.out.println("Delete failed");
				}
			} else {
				System.out.println("Delete cancelled by user");
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
