package app;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uap.CommercialSpace;
import uap.NotAvailableException;
import uap.Property;
import uap.User;

public class CommercialSpaceController implements Initializable {
	Stage stage;
	Scene scene;
	Parent root;
		 @FXML
	    private TextArea floorSpaceField;
	 @FXML
	    private TextArea locationField;
	@FXML
    private TableColumn<CommercialSpace, String> availabilityTable;

    @FXML
    private TableColumn<CommercialSpace, Boolean> fireexitTable;

    @FXML
    private TableColumn<CommercialSpace, Double> floorspaceTable;

    @FXML
    private TableColumn<CommercialSpace, String> idTable;

    @FXML
    private TableColumn<CommercialSpace, String> locationtable;

    @FXML
    private TableColumn<CommercialSpace, Double> rentTable;

    @FXML
    private TableView<CommercialSpace> table;
    
    ObservableList<CommercialSpace> clist = FXCollections.observableArrayList();

    public void initialize(URL arg0, ResourceBundle arg1) {
    	 idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
    	 locationtable.setCellValueFactory(new PropertyValueFactory<>("location"));
    	 rentTable.setCellValueFactory(new PropertyValueFactory<>("rent"));
    	 floorspaceTable.setCellValueFactory(new PropertyValueFactory<>("floorSpace"));
    	 fireexitTable.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().hasFireExit()));
    	 availabilityTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAvailable() ? "✔" : "✘"));
         table.setItems(clist);

    }
    
    @FXML
    void searchCommercial(ActionEvent event) {
        String location = locationField.getText();
        double floorSpace = Double.parseDouble(floorSpaceField.getText());
        try {
            clist.setAll(Main.PM.getCommercialSpaces(location, floorSpace));
        } catch (NotAvailableException e) {
            System.out.println(e.getMessage());
      }
    }
    @FXML
    void viewAllCommercial(ActionEvent event) {
   	 try {
            clist.setAll(Main.PM.getCommercialSpaces());
        } catch (NotAvailableException e) {
            System.out.println(e.getMessage());
        }
   }
    
    @FXML
    void leaseOver(ActionEvent event) {
        try {
        	CommercialSpace x = table.getSelectionModel().getSelectedItem();
            Property property = Main.PM.findProperty(x.getId());
            Main.PM.leaseOver(property);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
	public void switchToApartmentOverview(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ApartmentOverview.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void switchToAdminPanel(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminPanelApartment.fxml"));
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
	@FXML
	public void userAparment(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UserApartmentOverview.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	@FXML
	public void rentNow(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LeasePage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
		}

	
}
