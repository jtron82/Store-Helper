package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.ProductsModel;
import models.RecordsModel;
import models.UsersModel;

public class AdminController implements Initializable {

	@FXML
	private TableView<UsersModel> usersTable;
	
	@FXML
	private TableColumn<UsersModel, String> uid;
	
	@FXML
	private TableColumn<UsersModel, String> user;
	
	@FXML
	private TableColumn<UsersModel, String> pass;
	
	@FXML
	private TableView<RecordsModel> recordsTable;
	
	@FXML
	private TableColumn<RecordsModel, String> rid;

	@FXML
	private TableColumn<RecordsModel, String> uid2;
	
	@FXML
	private TableColumn<RecordsModel, String> cTotal;
	
	@FXML
	private TableView<ProductsModel> productsTable;
	
	@FXML
	private TableColumn<ProductsModel, String> pid;
	
	@FXML
	private TableColumn<ProductsModel, String> productName;
	
	@FXML
	private TableColumn<ProductsModel, String> productPrice;
	
	@FXML
	private Pane edituserPane;
	
	@FXML
	private Pane editprodsPane;
	
	@FXML 
	private Label notify;
	
	@FXML
	private Label notify2;
	
	@FXML
	private TextField deletebyID;
	
	@FXML
	private TextField newUsername;
	
	@FXML
	private TextField newPassword;
	
	@FXML 
	private TextField deletebyID2;
	
	@FXML
	private TextField newProduct;
	
	@FXML
	private TextField newPrice;
 	
	UsersModel um;
	RecordsModel rm;
	ProductsModel pm;
	
	public AdminController() {
		um = new UsersModel();
		rm = new RecordsModel();
		pm = new ProductsModel();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		uid.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("user_id"));
		user.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("username"));
		pass.setCellValueFactory(new PropertyValueFactory<UsersModel, String>("password"));
		
		rid.setCellValueFactory(new PropertyValueFactory<RecordsModel, String>("record_id"));
		uid2.setCellValueFactory(new PropertyValueFactory<RecordsModel, String>("employee_id"));
		cTotal.setCellValueFactory(new PropertyValueFactory<RecordsModel, String>("customerTotal"));
		
		pid.setCellValueFactory(new PropertyValueFactory<ProductsModel, String>("product_id"));
		productName.setCellValueFactory(new PropertyValueFactory<ProductsModel, String>("productName"));
		productPrice.setCellValueFactory(new PropertyValueFactory<ProductsModel, String>("productPrice"));
		
		usersTable.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(usersTable));
		
		recordsTable.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(recordsTable));
		
		productsTable.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(productsTable));
		
		usersTable.setVisible(false);
		edituserPane.setVisible(false);
		recordsTable.setVisible(false);
		productsTable.setVisible(false);
		editprodsPane.setVisible(false);
	}
	
	public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
	
	public void crudUsers() throws IOException {
		recordsTable.setVisible(false);
		edituserPane.setVisible(false);
		productsTable.setVisible(false);
		editprodsPane.setVisible(false);
		usersTable.getItems().setAll(um.getUsers());
		usersTable.setVisible(true);
	}
	
	public void crudRecords() throws IOException {
		usersTable.setVisible(false);
		edituserPane.setVisible(false);
		productsTable.setVisible(false);
		editprodsPane.setVisible(false);
		recordsTable.getItems().setAll(rm.getAllRecords());
		recordsTable.setVisible(true);
	}
	
	public void crudProducts() {
		usersTable.setVisible(false);
		edituserPane.setVisible(false);
		recordsTable.setVisible(false);
		editprodsPane.setVisible(false);
		productsTable.getItems().setAll(pm.getProducts());
		productsTable.setVisible(true);
	}
	
	public void editUsers() {
		usersTable.setVisible(false);
		recordsTable.setVisible(false);
		productsTable.setVisible(false);
		editprodsPane.setVisible(false);
		edituserPane.setVisible(true);
		notify.setText("");
	}
	
	public void addnewUser() {
		String u = newUsername.getText();
		String p = newPassword.getText();
		UsersModel.addnewUser(u, p);
		notify.setText("New User Added!");
		newUsername.clear();
		newPassword.clear();
	}
	
	public void deleteUser() {
		String d = deletebyID.getText();
		UsersModel.deleteUser(d);
		notify.setText("User Has Been Deleted!");
		deletebyID.clear();
	}
	
	public void editProducts() {
		usersTable.setVisible(false);
		recordsTable.setVisible(false);
		edituserPane.setVisible(false);
		productsTable.setVisible(false);
		editprodsPane.setVisible(true);
		notify2.setText("");
	}
	
	public void addnewProduct() {
		String prod = newProduct.getText();
		String price = newPrice.getText();
		ProductsModel.addnewProduct(prod, price);
		notify2.setText("New Product Added!");
		newProduct.clear();
		newPrice.clear();
	}
	
	public void deleteProduct() {
		String d = deletebyID2.getText();
		ProductsModel.deleteProduct(d);
		notify2.setText("Product Deleted!");
		deletebyID2.clear();
	}
	
	public void logout() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setTitle("Login");
			Main.stage.setScene(scene);
		} catch (Exception e) {
			System.out.println("(UserController) Error occured while inflating view: " +e);
		}
	}

}
