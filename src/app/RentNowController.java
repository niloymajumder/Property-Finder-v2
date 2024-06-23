package app;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uap.DataHandler;
import uap.Property;
import uap.User;

public class RentNowController {
	Stage stage;
	Scene scene;
	Parent root;
	 @FXML
	    private DatePicker datepicker;

	    @FXML
	    private TextField idField,idField1;

	    @FXML
	    private TextField startDateField;

	    @FXML
		public void switchToLogInOverview(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("LogInOverview.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    @FXML
	    public void Done(ActionEvent event) throws FileNotFoundException,IOException {
	    	 try {
	    		 Property ID =  Main.PM.findProperty(idField1.getText()); 
	             User leaseFor = Main.PM.findUser(idField.getText()); 
	             int leaseDuration = Integer.parseInt(startDateField.getText()); 
	             String leaseStartDate = datepicker.getValue().toString(); 
	             Main.PM.leaseProperty(ID, leaseFor, leaseStartDate, leaseDuration); 
	             DataHandler.saveData(Main.PM);
	         } catch (Exception e) {
	             System.out.println(e.getMessage());
	         }
			Parent root = FXMLLoader.load(getClass().getResource("CommercialSpaceOverview.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	
		@FXML
		public void switchToCommercialOverview(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("CommercialSpaceOverview.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}

