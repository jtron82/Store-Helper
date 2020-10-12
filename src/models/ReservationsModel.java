package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationsModel extends DBConnectModel {

	private int reservation_id;
	private String customerName;
	private String date;
	private String time;
	
	static DBConnectModel db = null;
	static Statement stmt = null;
	
	public ReservationsModel() {
		db = new DBConnectModel();
	}
	
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public static void addReservation(String name, String date, String time) {
		
		String SQL = "INSERT into jt_reservations(customerName, date, time) VALUES('"+name+"', '"+date+"', '"+time+"');";
		
		try {
			stmt = db.connect().createStatement();
			stmt.executeUpdate(SQL);	
		} catch (SQLException sqle) {
			System.out.println("Error inserting Reservation: " +sqle);
		}
	}
	
	public static void delReservation(String id) {
		
		String SQL = "DELETE from jt_reservations WHERE reservation_id = '"+id+"';";
		
		try {
			stmt = db.connect().createStatement();
			stmt.execute(SQL);
		} catch (SQLException sqle) {
			System.out.println("Error deleting Reservation: " +sqle);
		}
	}
	
	public List<ReservationsModel> getReservations() {
		
		List<ReservationsModel> reserve = new ArrayList<>();
		
		String SQL = "SELECT * FROM jt_reservations;";
		
		try {
			stmt = db.connect().createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				ReservationsModel rm = new ReservationsModel();
				rm.setReservation_id(rs.getInt("reservation_id"));
				rm.setCustomerName(rs.getString("customerName"));
				rm.setDate(rs.getString("date"));
				rm.setTime(rs.getString("time"));
				reserve.add(rm);
			}
		} catch (SQLException sqle) {
			System.out.println("Error fetching Reservations: " + sqle);
		}
		
		return reserve;
	}
}
