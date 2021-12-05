
package oodj_vaccineregistrationsystem;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class PersonnelViewAppointmentDetailsController implements Initializable {
    
    @FXML
    private TableView<Appointment> CAppointmentDetailsTable;
    @FXML
    TableColumn<Appointment, String> aIDCol;
    @FXML
    TableColumn<Appointment, String> aNameCol;
    @FXML
    TableColumn<Appointment, String> aICCol;
    @FXML
    TableColumn<Appointment, String> aDateCol;
    @FXML
    TableColumn<Appointment, String> aTimeCol;
    @FXML
    TableColumn<Appointment, String> aTypeCol;
    @FXML
    TableColumn<Appointment, String> aVenueCol;
    @FXML
    private Button CbtnViewRefreshTable;
    @FXML
    private Hyperlink ClinkViewForgotAID;
    @FXML
    private TextField CtxtViewAID;
    @FXML
    private AnchorPane PersonnelViewAppointmentDetails;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
    }

    public void loadTable () {
	try {
            Collection<Appointment> list = Files.readAllLines(new File ("Appointment.txt").toPath())
		.stream()
		.map(line -> {
                    String [] aData = line.split(";");
                    Appointment a = new Appointment();
                    a.setaID(aData[0]);
                    a.setaIC(aData[1]);
                    a.setaDate(aData[2]);
                    a.setaTime(aData[3]);
                    a.setaType(aData[4]);
                    a.setaVenue(aData[5]);
                    a.setaVaccine(aData[6]);
                    a.setaVBID(aData[7]);
                    return a;
		})
		.collect(Collectors.toList());
			
	ObservableList<Appointment> aData = FXCollections.observableArrayList(list);
        // create table columns
	TableColumn<Appointment, String> aIDCol = new TableColumn<>("ID");
	TableColumn<Appointment, String> aNameCol = new TableColumn<>("Name");
	TableColumn<Appointment, String> aICCol = new TableColumn<>("IC/Passport");
	TableColumn<Appointment, String> aDateCol = new TableColumn<>("Date");
	TableColumn<Appointment, String> aTimeCol = new TableColumn<>("Time");
	TableColumn<Appointment, String> aTypeCol = new TableColumn<>("Type");
        TableColumn<Appointment, String> aVenueCol = new TableColumn<>("Venue");
        TableColumn<Appointment, String> aVaccineCol = new TableColumn<>("Vaccine");
        TableColumn<Appointment, String> aVBIDCol = new TableColumn<>("Vaccine Batch");
        // add columns to table
	CAppointmentDetailsTable.getColumns().addAll(aIDCol, aNameCol, aICCol, aDateCol, aTimeCol, aTypeCol, aVenueCol, aVaccineCol, aVBIDCol);
	aIDCol.setCellValueFactory(data -> data.getValue().idProperty());
        aNameCol.setCellValueFactory(data -> data.getValue().nameProperty());
	aICCol.setCellValueFactory(data -> data.getValue().icProperty());
	aDateCol.setCellValueFactory(data -> data.getValue().dateProperty());
	aTimeCol.setCellValueFactory(data -> data.getValue().timeProperty());
	aTypeCol.setCellValueFactory(data -> data.getValue().typeProperty());
        aVenueCol.setCellValueFactory(data -> data.getValue().venueProperty());
        aVaccineCol.setCellValueFactory(data -> data.getValue().vaccineProperty());
        aVBIDCol.setCellValueFactory(data -> data.getValue().vbidProperty());
			
	// Sort and Filter the table based on Appointment ID OR IC
	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
	FilteredList<Appointment> filteredData = new FilteredList<>(aData, g -> true);
        // 2. Set the filter Predicate whenever the filter changes.
	CtxtViewAID.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
		// If filter text is empty, display all appointments.
		if (newValue.isEmpty()) {
                    return true;
                }				
		// Compare appointment id of every appointment with input id.
		String aid = newValue.toString();
		if (a.getaID().toString().contains(aid)) {
                    return true; // Filter matches appointment id.
		} else if (a.getaIC().toString().contains(CtxtViewAID.getText())) {
                    return true; // Filter matches appointment ic.
		} else {
                    return false; // Does not match.
		}
            });
	});
	// 3. Wrap the FilteredList in a SortedList. 
	SortedList<Appointment> sortedData = new SortedList<>(filteredData);
	// 4. Bind the SortedList comparator to the TableView comparator.
	sortedData.comparatorProperty().bind(CAppointmentDetailsTable.comparatorProperty());
	// 5. Add sorted (and filtered) data to the table.
	CAppointmentDetailsTable.setItems(sortedData);
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
    
     public void forgotaID() {
    	TextInputDialog td = new TextInputDialog();
    	td.setHeaderText(null);
	td.setTitle("IC/Passport Number is required");
	td.setContentText("Please provide a valid IC/Passport Number (with HYPHEN):");	
	Optional <String> result = td.showAndWait();
        String ic = result.get();
        if(ic.length() < 7){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please provide a valid IC/Passport Number with at least 7 characters !");
            Optional<ButtonType> r = alert.showAndWait();
            if(r.get() == ButtonType.OK) {
		alert.hide();
                Optional <String> ric = td.showAndWait();
                ic = ric.get();
                CtxtViewAID.setText(ic);
            }
        } else {
            CtxtViewAID.setText(ic);
        }
    }
     
     public void setName(String name){
         
     }
     
    public void reloadTable() {
        CtxtViewAID.setText("");
    	CAppointmentDetailsTable.getColumns().clear();
    	loadTable();
    }
    
    public void showPersonnel(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personnel Interface");
            primaryStage.show();
            PersonnelViewAppointmentDetails.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	    	}
    }
    
    public void logout(ActionEvent event) {
	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setHeaderText(null);
	alert.setTitle("Log Out");
	alert.setContentText("Are you sure want to log out?");
	ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
	ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
	alert.getButtonTypes().setAll(btnYes, btnNo);
	Optional<ButtonType> result = alert.showAndWait();
	if(result.get() == btnYes) {
            alert.hide();
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
                PersonnelViewAppointmentDetails.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }

    
}
