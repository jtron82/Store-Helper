package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersModel extends DBConnectModel {
	
	private int user_id;
	private String username;
	private String password;
	
	static DBConnectModel db = null;
	static Statement stmt = null;
	
	public UsersModel() {
		db = new DBConnectModel();
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void addnewUser(String uname, String pword) {
		
		String SQL = "INSERT into jt_users(username, password) VALUES('"+uname+"', '"+pword+"');";
		
		try {
			stmt = db.connect().createStatement();
			stmt.executeUpdate(SQL);	
		} catch (SQLException sqle) {
			System.out.println("Error inserting New User: " +sqle);
		}
	}
	
	public static void deleteUser(String id) {
		
		String SQL = "DELETE from jt_users WHERE user_id = '"+id+"';";
		
		try {
			stmt = db.connect().createStatement();
			stmt.execute(SQL);
		} catch (SQLException sqle) {
			System.out.println("Error deleting User: " +sqle);
		}
	}
	
	public List<UsersModel> getUsers() {
		
		List<UsersModel> users = new ArrayList<>();
		
		String SQL = "SELECT * FROM jt_users;";
		
		try {
			stmt = db.connect().createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				UsersModel user = new UsersModel();
				user.setUser_id(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException sqle) {
			System.out.println("Error obtaining Users: " +sqle);
		}
		
		return users;
	}
}