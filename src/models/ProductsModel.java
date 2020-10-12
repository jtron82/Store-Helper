package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsModel extends DBConnectModel {
	
	private int product_id;
	private String productName;
	private double productPrice;

	static DBConnectModel db = null;
	static Statement stmt = null;
	
	public ProductsModel() {
		db = new DBConnectModel();
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	public static void addnewProduct(String name, String price) {
		
		String SQL = "INSERT into jt_products(productName, productPrice) VALUES('"+name+"', '"+price+"');";
		
		try {
			stmt = db.connect().createStatement();
			stmt.executeUpdate(SQL);	
		} catch (SQLException sqle) {
			System.out.println("Error inserting Products: " +sqle);
		}
	}
	
	public static void deleteProduct(String id) {
		
		String SQL = "DELETE from jt_products WHERE product_id = '"+id+"';";
		
		try {
			stmt = db.connect().createStatement();
			stmt.execute(SQL);
		} catch (SQLException sqle) {
			System.out.println("Error deleting Product: " +sqle);
		}
	}
	
	public List<ProductsModel> getProducts() {
		
		List<ProductsModel> products = new ArrayList<>();
		
		String SQL = "SELECT * FROM jt_products;";
		
		try {
			stmt = db.connect().createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				ProductsModel prod = new ProductsModel();
				prod.setProduct_id(rs.getInt("product_id"));
				prod.setProductName(rs.getString("productName"));
				prod.setProductPrice(rs.getDouble("productPrice"));
				products.add(prod);
			}
		} catch (SQLException sqle) {
			System.out.println("Error obtaining Users: " +sqle);
		}
		
		return products;
	}
}
