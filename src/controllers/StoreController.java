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
import models.ReservationsModel;

public class StoreController implements Initializable {
	
	@FXML
	private TableView<ReservationsModel> reserveTable;
	
	@FXML
	private TableColumn<ReservationsModel, String> rid;
	
	@FXML
	private TableColumn<ReservationsModel, String> customerName;
	
	@FXML
	private TableColumn<ReservationsModel, String> date;
	
	@FXML
	private TableColumn<ReservationsModel, String> time;
	
	@FXML
	private Pane calculatorPane;
	
	@FXML
	private Pane editreservePane;
	
	@FXML
	private TextField reserveName;
	
	@FXML
	private TextField reserveDate;
	
	@FXML
	private TextField reserveTime;
	
	@FXML
	private TextField delresID;
	
	@FXML
	private Label updateNotify;
	
	ReservationsModel rm;
	
	public StoreController() {
		rm = new ReservationsModel();
	}

	public void initialize(URL location, ResourceBundle resources) {
		rid.setCellValueFactory(new PropertyValueFactory<ReservationsModel, String>("reservation_id"));
		customerName.setCellValueFactory(new PropertyValueFactory<ReservationsModel, String>("customerName"));
		date.setCellValueFactory(new PropertyValueFactory<ReservationsModel, String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<ReservationsModel, String>("time"));
		
		reserveTable.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(reserveTable));
		
		calculatorPane.setVisible(false);
		editreservePane.setVisible(false);
		reserveTable.setVisible(false);
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
	
	public void reservations() throws IOException {
		calculatorPane.setVisible(false);
		editreservePane.setVisible(false);
		
		reserveTable.getItems().setAll(rm.getReservations());
		reserveTable.setVisible(true);
	}
	
	public void calculator() {
		reserveTable.setVisible(false);
		editreservePane.setVisible(false);
		calculatorPane.setVisible(true);
	}
	
	public void editReservation() {
		calculatorPane.setVisible(false);
		reserveTable.setVisible(false);
		editreservePane.setVisible(true);
		updateNotify.setText("");
	}
	
	public void addReservation() {
		String n = reserveName.getText();
		String d = reserveDate.getText();
		String t = reserveTime.getText();
		ReservationsModel.addReservation(n, d, t);
		updateNotify.setText("New Reservation Added!");
		reserveName.clear();
		reserveDate.clear();
		reserveTime.clear();
	}
	
	public void delReservation() {
		String id = delresID.getText();
		ReservationsModel.delReservation(id);
		updateNotify.setText("Selected Reservation ID Deleted!");
		delresID.clear();
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
