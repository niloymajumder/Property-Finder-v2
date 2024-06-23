package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uap.Apartment;
import uap.DataHandler;
import uap.NotAvailableException;

public class AddApartmentController implements Initializable{
	
	Stage stage;
	Scene scene;
	Parent root;
	
    @FXML
    private TextField bedField;

    @FXML
    private CheckBox checkGenerator;

    @FXML
    private TextField floorField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField rentField;

    @FXML
    private TextField washroomField;

    @FXML
    private TableView<Apartment> mainTable;


    @FXML
    private TableColumn<Apartment, Integer> tableBedRooms;

    @FXML
    private TableColumn<Apartment, Double> tableFloorSpace;

    @FXML
    private TableColumn<Apartment, Boolean> tableGenerator;

    @FXML
    private TableColumn<Apartment, String> tableID;

    @FXML
    private TableColumn<Apartment, String> tableLocation;

    @FXML
    private TableColumn<Apartment, Double> tableRent;

    @FXML
    private TableColumn<Apartment, Integer> tableWashrooms;
    ObservableList<Apartment> clist = FXCollections.observableArrayList();
    public void initialize(URL arg0, ResourceBundle arg1) {
    	tableBedRooms.setCellValueFactory(new PropertyValueFactory<>("noOfBed"));
    	tableFloorSpace.setCellValueFactory(new PropertyValueFactory<>("floorSpace"));
    	tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tableWashrooms.setCellValueFactory(new PropertyValueFactory<>("noOfWashRoom"));
    	tableLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
   	tableGenerator.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().hasGenerator()));
   	tableRent.setCellValueFactory(new PropertyValueFactory<>("rent"));
   	mainTable.setItems(clist);

   }
    @FXML
    void viewAllUser(ActionEvent event) throws NotAvailableException {
      	 clist.setAll(Main.PM.getAppartments());
      }
    @FXML
    void enterApartment(ActionEvent event) throws FileNotFoundException, IOException {
        String location = locationField.getText();
        double rent = Double.parseDouble(rentField.getText());
        int washRoom = Integer.parseInt(washroomField.getText());
        int bedRoom = Integer.parseInt(bedField.getText());
        double floorSpace = Double.parseDouble(floorField.getText());
        boolean hasGenerator = checkGenerator.isSelected();
        String id = Main.PM.addProperty(location, rent, floorSpace, bedRoom, washRoom, hasGenerator);
        DataHandler.saveData(Main.PM);
        System.out.println(""+id);
        locationField.clear();
        rentField.clear(); 
        floorField.clear(); 
        washroomField.clear();
        bedField.clear();
        checkGenerator.setSelected(false);
    }
    
    @FXML
	public void switchToLogInOverview(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogInOverview.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
    @FXML
	public void switchBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ApartmentOverview.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
    @FXML
	public void addUser(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminPanelUser.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
    @FXML
	public void addCommercialSpace(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminPanelCommercial.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
    
    
    
    

}
