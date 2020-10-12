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
import models.ProfilesModel;
import models.RecordsModel;

public class UserController implements Initializable {
	
	@FXML
	private Pane vprofilePane;
	
	@FXML
	private Pane eprofilePane;
	
	@FXML
	private Pane blankPane;
	
	@FXML
	private TableView<RecordsModel> vrecordsTable;
	
	@FXML
	private TableColumn<RecordsModel, String> rid;
	
	@FXML
	private TableColumn<RecordsModel, String> cbalance;

	@FXML
	private Label labelName;
	
	@FXML
	private Label notifyUpdate;
	
	@FXML
	private Label userfName;
	
	@FXML
	private Label userlName;
	
	@FXML
	private Label userAddress;
	
	@FXML
	private Label userDOB;
	
	@FXML
	private Label userEmail;
	
	@FXML
	private Label userphNumber;
	
	@FXML
	private TextField fnameEdit;

	@FXML
	private TextField lnameEdit;
	
	@FXML
	private TextField addressEdit;

	@FXML
	private TextField dobEdit;

	@FXML
	private TextField emailEdit;

	@FXML
	private TextField phnumberEdit;
	
	public static int userid;
	static ProfilesModel profiles;
	RecordsModel records;
	
	public UserController() {
		profiles = new ProfilesModel();
		records = new RecordsModel();
	}
	
	public static void setUserid(int userid) {
		UserController.userid = userid;
		System.out.println("Welcome id: " +userid);
		profiles.getProfile(userid);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		rid.setCellValueFactory(new PropertyValueFactory<RecordsModel, String>("record_id"));
		cbalance.setCellValueFactory(new PropertyValueFactory<RecordsModel, String>("customerTotal"));
	
		vrecordsTable.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(vrecordsTable));

		vrecordsTable.setVisible(false);
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
	
	public void viewProfile() {
		eprofilePane.setVisible(false);
		vrecordsTable.setVisible(false);
		blankPane.setVisible(false);
		
		userfName.setText(profiles.getFirstName());
		userlName.setText(profiles.getLastName());
		userAddress.setText(profiles.getAddress());
		userDOB.setText(profiles.getDateofBirth());
		userEmail.setText(profiles.getEmail());
		userphNumber.setText(profiles.getPhoneNumber());
		labelName.setText(profiles.getFirstName());
		
		vprofilePane.setVisible(true);
	}
	
	public void editProfile() {
		vprofilePane.setVisible(false);
		eprofilePane.setVisible(true);
		vrecordsTable.setVisible(false);
		blankPane.setVisible(false);
		notifyUpdate.setText("");
	}
	
	public void viewRecords() throws IOException {
		vprofilePane.setVisible(false);
		eprofilePane.setVisible(false);
		blankPane.setVisible(false);
		
		vrecordsTable.getItems().setAll(records.getRecords(userid));
		vrecordsTable.setVisible(true);
	}
	
	public void updatefName() {
		String fn = fnameEdit.getText();
		ProfilesModel.updatefName(userid, fn);
		notifyUpdate.setText("First Name Updated!");
		fnameEdit.clear();
		profiles.getProfile(userid);
	}
	
	public void updatelName() {
		String ln = lnameEdit.getText();
		ProfilesModel.updatelName(userid, ln);
		notifyUpdate.setText("Last Name Updated!");
		lnameEdit.clear();
		profiles.getProfile(userid);
	}
	
	public void updateAddress() {
		String add = addressEdit.getText();
		ProfilesModel.updateAddress(userid, add);
		notifyUpdate.setText("Address Updated!");
		addressEdit.clear();
		profiles.getProfile(userid);
	}
	
	public void updateDOB() {
		String dob = dobEdit.getText();
		ProfilesModel.updateDOB(userid, dob);
		notifyUpdate.setText("Date of Birth Updated!");
		dobEdit.clear();
		profiles.getProfile(userid);
	}
	
	public void updateEmail() {
		String email = emailEdit.getText();
		ProfilesModel.updateEmail(userid, email);
		notifyUpdate.setText("Email Updated!");
		emailEdit.clear();
		profiles.getProfile(userid);
	}
	
	public void updatephNumber() {
		String ph = phnumberEdit.getText();
		ProfilesModel.updatephNumber(userid, ph);
		notifyUpdate.setText("Phone Number Updated!");
		phnumberEdit.clear();
		profiles.getProfile(userid);
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
