package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleBooleanProperty;
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
import uap.DataHandler;
import uap.NotAvailableException;
import uap.PropertyManager;
import uap.User;

public class AddUserController implements Initializable{
	Stage stage;
	Scene scene;
	Parent root;
	 private ObservableList<User> uList = FXCollections.observableArrayList();
    @FXML
    private CheckBox adminCheck;

    @FXML
    private TextField ageField;

    @FXML
    private TextField nameField;
    @FXML
    private TableColumn<User, Boolean> adminTable;


    @FXML
    private TableColumn<User, Integer> ageTable;

    @FXML
    private TableColumn<User, String> idTable;

    @FXML
    private TableColumn<User, String> nameTable;

    @FXML
    private TableView<User> table;
    
   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
    	ageTable.setCellValueFactory(new PropertyValueFactory<>("age"));
    	adminTable.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAdmin()));
        table.setItems(uList);

    }
    @FXML
    void viewAllUser(ActionEvent event) {
      	 uList.setAll(Main.PM.getUsers());
     	    ArrayList<User> user = null;
     	    user = Main.PM.getUsers();
     	  ObservableList<User> Xuser = FXCollections.observableArrayList(user);
     	    table.setItems(Xuser);
     	}
      
    
    @FXML
    void enterUser(ActionEvent event) throws FileNotFoundException, IOException {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        boolean isAdmin = adminCheck.isSelected();
        String id = Main.PM.addUser(name, age, isAdmin);
        DataHandler.saveData(Main.PM);
        System.out.println(""+id);
        ageField.clear();
        nameField.clear(); 
        adminCheck.setSelected(false);
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
	public void addApartment(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminPanelApartment.fxml"));
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
	@FXML
	public void switchToApartmentOverview(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ApartmentOverview.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
