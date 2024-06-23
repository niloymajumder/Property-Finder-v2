package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uap.CommercialSpace;
import uap.DataHandler;
import uap.NotAvailableException;

public class AdminCommercialController implements Initializable {
	Stage stage;
	Scene scene;
	Parent root;

	@FXML
	private Button prevBtn;
	@FXML
	private Button nextBtn;

	@FXML
	private CheckBox fireClick;

	@FXML
	private TextField floorspaceField;

	@FXML
	private TextField locationField;

	@FXML
	private TextField rentField;
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
		availabilityTable.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().isAvailable() ? "✔" : "✘"));
		table.setItems(clist);

	}

	@FXML
	void searchCommercial(ActionEvent event) {
		String location = locationField.getText();
		double floorSpace = Double.parseDouble(floorspaceField.getText());
		try {
			clist.setAll(Main.PM.getCommercialSpaces(location, floorSpace));
		} catch (NotAvailableException e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void enterCommercialSpace(ActionEvent event) throws FileNotFoundException, IOException {
		String location = locationField.getText();
		double rent = Double.parseDouble(rentField.getText());
		double floorSpace = Double.parseDouble(floorspaceField.getText());
		boolean hasFireExit = fireClick.isSelected();
		String id = Main.PM.addProperty(location, rent, floorSpace, hasFireExit);
		DataHandler.saveData(Main.PM);
		System.out.println("" + id);
		locationField.clear();
		rentField.clear();
		floorspaceField.clear();
		fireClick.setSelected(false);
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
	public void switchToLogInOverview(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogInOverview.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void addApartment(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminApartmentPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void addCommercialSpace(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminCommercialSpace.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void addUser(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminUserPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void switchBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ApartmentPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	@FXML
    void prevPage(ActionEvent event) throws IOException {
    	 System.out.println("Previous");
	        Main up6 = new Main();
	            up6.changeScene("AdminApartmentPage.fxml");
    }
    
    @FXML
    void nextPage(ActionEvent event) throws IOException {
    	 System.out.println("Next");
	        Main up5 = new Main();
	            up5.changeScene("AdminUserPage.fxml");
    }
}
