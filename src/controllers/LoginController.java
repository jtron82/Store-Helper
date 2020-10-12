package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.LoginModel;

public class LoginController {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label labelMsg;
	
	@FXML
	private Label labelName;

	private LoginModel login;

	public LoginController() {
		login = new LoginModel();
	}

	public void loginBtn() {

		String uname = this.username.getText();
		String pword = this.password.getText();

		if ((uname == null || uname.trim().equals("")) && (pword == null || pword.trim().equals(""))) {
			labelMsg.setText("Username and Password Cannot Be Empty");
			return;
		} else if (pword == null || pword.trim().equals("")) {
			labelMsg.setText("Password Cannot Be Empty");
			return;
		} else if (uname == null || uname.trim().equals("")) {
			labelMsg.setText("Username Cannot Be Empty");
			return;
		}

		validate(uname, pword);
	}

	public void validate(String username, String password) {

		Boolean isValid = login.validation(username, password);

		if (!isValid) {
			labelMsg.setText("User Does NOT Exist or Incorrect Credentials");
			return;
		}

		try {
			AnchorPane root;
			
			if (login.getAdmin() && isValid) {
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
			    Main.stage.setTitle("Admin"); 
			} else if (login.getStore() && isValid) {
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/StoreView.fxml"));
				Main.stage.setTitle("Store");
			} else {
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/UserView.fxml"));
				int userid = login.getId();
				UserController.setUserid(userid);
				Main.stage.setTitle("User");
			}

			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
		} catch (Exception e) {
			System.out.println("(LoginController) Error occured while inflating view: " + e);
		}
	}
}
