package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnectModel {
	
	protected static Connection connection;
	
	public Connection connect(){
		return connection;
	}
	
	private static String DB_URL = "jdbc:mysql://www.papademas.net:3307/510labs";
	private static String USER = "db510", PASS = "510";
	
	public DBConnectModel() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException sqle) {
			System.out.println("Error connecting to database: " +sqle);
			System.exit(-1);
		}
	}
}
