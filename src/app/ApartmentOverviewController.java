package app;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uap.Apartment;
import uap.CommercialSpace;
import uap.NotAvailableException;
import uap.Property;

public class ApartmentOverviewController  implements Initializable{
	
	Stage stage;
	Scene scene;
	Parent root;
	
	@FXML
    private TextArea floorSpaceField, bedField, washroomField;
	@FXML
    private TextArea locationField;

	  @FXML
	    private TableView<Apartment> main_table;

	    @FXML
	    private TableColumn<Apartment, Integer> table_Bedrooms;

	    @FXML
	    private TableColumn<Apartment, Double> table_Floor_Space;

	    @FXML
	    private TableColumn<Apartment, Boolean> table_Generator;

	    @FXML
	    private TableColumn<Apartment, String> table_ID;

	    @FXML
	    private TableColumn<Apartment, Double> table_Rent;

	    @FXML
	    private TableColumn<Apartment, Integer> table_Washrooms;

	    @FXML
	    private TableColumn<Apartment, String> table_location;
	    ObservableList<Apartment> clist = FXCollections.observableArrayList();
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	table_Bedrooms.setCellValueFactory(new PropertyValueFactory<>("noOfBed"));
	    	table_Floor_Space.setCellValueFactory(new PropertyValueFactory<>("floorSpace"));
	    	table_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	 table_Washrooms.setCellValueFactory(new PropertyValueFactory<>("noOfWashRoom"));
	    	table_location.setCellValueFactory(new PropertyValueFactory<>("location"));
	    	table_Generator.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().hasGenerator()));
	    	table_Rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
	    	main_table.setItems(clist);

	   }
	
	    @FXML
	    void viewAllUser(ActionEvent event) throws NotAvailableException {
	      	 clist.setAll(Main.PM.getAppartments());
	      }
    @FXML
    void searchApartment(ActionEvent event) {

        String location = locationField.getText();
        double floorSpace = Double.parseDouble(floorSpaceField.getText());
        int noOfBed = Integer.parseInt(bedField.getText());
        int noOfWashroom = Integer.parseInt(washroomField.getText());
        
        try {
            clist.setAll(Main.PM.getAppartments(location, noOfBed, noOfWashroom, floorSpace));
        } catch (NotAvailableException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void switchToLogInOverview(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogInOverview.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToCommercialOverview(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("CommercialSpaceOverview.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToAdminPanel(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminPanelApartment.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void userCommercial(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UserCommercial.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
