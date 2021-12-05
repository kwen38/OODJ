
package oodj_vaccineregistrationsystem;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class PersonnelController implements Initializable {
    
    @FXML
    private TableView<Appointment> AppointmentTable;
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
    private Button btnAppointment;
    @FXML
    private MenuBar btnLogout;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnVaccine;
    @FXML
    private TextField txtTodayDate;
    @FXML
    private AnchorPane Personnel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      setDate();
      loadTable();
    }   
    
    public void setDate() {
	//Set date & time display format
	//"hh" in pattern is for 12 hour time format and "aa" is for AM/PM
	SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
	//Setting the time zone
	dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	txtTodayDate.setText(dateTimeInGMT.format(new Date()));
	SimpleDateFormat todayDate = new SimpleDateFormat("dd-MM-yyyy");
    }
    
    public void loadTable() {
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
	AppointmentTable.getColumns().addAll(aIDCol, aNameCol, aICCol, aDateCol, aTimeCol, aTypeCol, aVenueCol, aVaccineCol, aVBIDCol);
	aIDCol.setCellValueFactory(data -> data.getValue().idProperty());
        aNameCol.setCellValueFactory(data -> data.getValue().nameProperty());
	aICCol.setCellValueFactory(data -> data.getValue().icProperty());
	aDateCol.setCellValueFactory(data -> data.getValue().dateProperty());
	aTimeCol.setCellValueFactory(data -> data.getValue().timeProperty());
	aTypeCol.setCellValueFactory(data -> data.getValue().typeProperty());
        aVenueCol.setCellValueFactory(data -> data.getValue().venueProperty());
        aVaccineCol.setCellValueFactory(data -> data.getValue().vaccineProperty());
        aVBIDCol.setCellValueFactory(data -> data.getValue().vbidProperty());
				
	// Sort and Filter the table based on CheckinTime
	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
	FilteredList<Appointment> filteredData = new FilteredList<>(aData, a -> true);
	// 2. Set the filter Predicate whenever the filter changes.
	txtTodayDate.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                // Compare check-in date with today's date.
                String date = newValue;
                if (a.getaDate().contains(date)) {
                    return true; // Filter matches today date.
                } else {
                    return false; // Does not match.
                }
            });
        });
	// 3. Wrap the FilteredList in a SortedList. 
	SortedList<Appointment> sortedData = new SortedList<>(filteredData);
	// 4. Bind the SortedList comparator to the TableView comparator.
	sortedData.comparatorProperty().bind(AppointmentTable.comparatorProperty());
	// 5. Add sorted (and filtered) data to the table.
	AppointmentTable.setItems(sortedData);
	AppointmentTable.setPlaceholder(new Label("No appointment today"));
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
    
    public void showBookAppointment() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("BookVaccinationAppointment.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Book Vaccination Appointment");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
    
    public void showModifyAppointment() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PersonnelModifyAppointmentDetails.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Modify Appointment Details");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
       
    public void showViewAppointment() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PersonnelViewAppointmentDetails.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("View Appointment Details");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
    
    public void showSearchAppointment() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PersonnelSearchAppointment.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Search Vaccination Appointment");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
    
    public void showCancelAppointment() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PersonnelCancelAppointment.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Cancel Vaccination Appointment");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
    
    public void showAddVaccine() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PersonnelAddNewVaccine.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Add Vaccine");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
    
    public void showModifyVaccine() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PersonnelModifyVaccineAvailability.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Modify Vaccine Availability");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
    
    public void showViewnSearchVaccine() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View&SearchVaccineDetails.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("View and Search Vaccine");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
    }
    
    public void showRemoveVaccine() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PersonnelRemoveVaccine.fxml"));
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Remove Vaccine");
	primaryStage.show();
        Personnel.getScene().getWindow().hide();
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
                Personnel.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }
}
