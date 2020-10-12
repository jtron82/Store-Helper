package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecordsModel extends DBConnectModel {

	private int record_id;
	private int employee_id;
	private double customerTotal;

	DBConnectModel db = null;
	Statement stmt = null;
	
	public RecordsModel() {
		db = new DBConnectModel();
	}
	
	public int getRecord_id() {
		return record_id;
	}


	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}


	public int getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public double getCustomerTotal() {
		return customerTotal;
	}
	
	public void setCustomerTotal(double customerTotal) {
		this.customerTotal = customerTotal;
	}

	public List<RecordsModel> getRecords(int employee_id) {
		
		List<RecordsModel> records = new ArrayList<>();
		
		String SQL = "SELECT record_id, customerTotal FROM jt_records WHERE employee_id = ?;";
		
		try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setInt(1, employee_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RecordsModel record = new RecordsModel();
				record.setRecord_id(rs.getInt("record_id"));
				record.setCustomerTotal(rs.getDouble("customerTotal"));
				records.add(record);
			}
		} catch (SQLException sqle) {
			System.out.println("(RecordsModel) Error fetching Records: " +sqle);
		}
		
		return records;
	}
	
	public List<RecordsModel> getAllRecords() {
		
		List<RecordsModel> records = new ArrayList<>();
		
		String SQL = "SELECT * FROM jt_records";
		
		try {
			stmt = db.connect().createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				RecordsModel record = new RecordsModel();
				record.setRecord_id(rs.getInt("record_id"));
				record.setEmployee_id(rs.getInt("employee_id"));
				record.setCustomerTotal(rs.getDouble("customerTotal"));
				records.add(record);
			}
		} catch (SQLException sqle) {
			System.out.println("(RecordsModel) Error fetching Records: " +sqle);
		}
		
		return records;
	}
}
