package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel extends DBConnectModel {
	
	private Boolean admin;
	private Boolean store;
	private int id;
	
	public Boolean getStore() {
		return store;
	}

	public void setStore(Boolean store) {
		this.store = store;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean validation(String username, String password) {
		
		String SQL = "SELECT * FROM jt_users WHERE username = ? and password = ?;";
		
		try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				setId(rs.getInt("user_id"));
				setAdmin(rs.getBoolean("admin"));
				setStore(rs.getBoolean("store"));
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return false;
	}
}
