package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfilesModel extends DBConnectModel {

	private String firstName;
	private String lastName;
	private String address;
	private String dateofBirth;
	private String email;
	private String phoneNumber;

	static DBConnectModel conn = null;
	static Statement stmt = null;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public ProfilesModel() {
		conn = new DBConnectModel();
	}
	
	public static void updatefName(int user_id, String fname) {
		
		String SQL = "UPDATE jt_profiles SET firstName = '"+fname+"' WHERE profile_id = '"+user_id+"';";

		try {
			stmt = conn.connect().createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException sqle) {
			System.out.println("(UsersModel) Error: " +sqle);
		}
	}
	
	public static void updatelName(int user_id, String lname) {
		
		String SQL = "UPDATE jt_profiles SET lastName = '"+lname+"' WHERE profile_id = '"+user_id+"';";

		try {
			stmt = conn.connect().createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException sqle) {
			System.out.println("(UsersModel) Error: " +sqle);
		}
	}
	
	public static void updateAddress(int user_id, String address) {
		
		String SQL = "UPDATE jt_profiles SET address = '"+address+"' WHERE profile_id = '"+user_id+"';";

		try {
			stmt = conn.connect().createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException sqle) {
			System.out.println("(UsersModel) Error: " +sqle);
		}
	}
	
	public static void updateDOB(int user_id, String dob) {
		
		String SQL = "UPDATE jt_profiles SET dateofBirth = '"+dob+"' WHERE profile_id = '"+user_id+"';";

		try {
			stmt = conn.connect().createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException sqle) {
			System.out.println("(UsersModel) Error: " +sqle);
		}
	}
	
	public static void updateEmail(int user_id, String email) {
		
		String SQL = "UPDATE jt_profiles SET email = '"+email+"' WHERE profile_id = '"+user_id+"';";

		try {
			stmt = conn.connect().createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException sqle) {
			System.out.println("(UsersModel) Error: " +sqle);
		}
	}
	
	public static void updatephNumber(int user_id, String phN) {
		
		String SQL = "UPDATE jt_profiles SET phoneNumber = '"+phN+"' WHERE profile_id = '"+user_id+"';";

		try {
			stmt = conn.connect().createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException sqle) {
			System.out.println("(UsersModel) Error: " +sqle);
		}
	}

	public void getProfile(int user_id) {
		
		String SQL = "SELECT * FROM jt_profiles WHERE profile_id = ?;";
		
		try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setInt(1,  user_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				this.setFirstName(rs.getString("firstName"));
				this.setLastName(rs.getString("lastName"));
				this.setAddress(rs.getString("address"));
				this.setDateofBirth(rs.getString("dateofBirth"));
				this.setEmail(rs.getString("email"));
				this.setPhoneNumber(rs.getString("phoneNumber"));
			}
		} catch (SQLException sqle) {
			System.out.println("(UsersModel) Error: " +sqle);
		}
	}
}
