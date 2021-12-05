
package covid19system;

import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;
import java.io.*;
import java.text.DecimalFormat;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.stage.Stage;

public class BookVaccinationAppointmentController implements Initializable {
    
    @FXML
    private AnchorPane BookVaccinationAppointment;
    @FXML
    private Button btnBackBookAppointment;
    @FXML
    private Button btnBookAppointment;
    @FXML
    private ComboBox<String> cboAddAppointmentTime;
    @FXML
    private ComboBox<String> cboAddAppointmentType;
    @FXML
    private ComboBox<String> cboAddAppointmentVenue;
    @FXML
    private ComboBox<String> cboAddAppointmentVaccine;
    @FXML
    private ComboBox<String> cboAddAppointmentvBID;
    @FXML
    private TextField lblAddAppointmentID;
    @FXML
    private Hyperlink linkNewUser;
    @FXML
    private DatePicker txtAddAppointmentDate;
    @FXML
    private TextField txtICAddAppoinment;
    
    // Add items into Combo Box
    ObservableList<String> AppointmentTime = FXCollections.observableArrayList("8:00AM", "9:00AM", "10:00AM", "11:00AM",
            "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM");
    ObservableList<String> AppointmentType = FXCollections.observableArrayList("First Dose", "Second Dose");
    ObservableList<String> AppointmentVenue = FXCollections.observableArrayList("Axiata Arena Bukit Jalil", "Kuala Lumpur Convention Centre (KLCC)", "University Malaya (UM)");
    ObservableList<String> VaccineType = FXCollections.observableArrayList("Astrazeneca", "Pfizer", "Sinovac");
    
    Appointment a = new Appointment();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboAddAppointmentTime.setItems(AppointmentTime);
        cboAddAppointmentType.setItems(AppointmentType);
        cboAddAppointmentVenue.setItems(AppointmentVenue);
        cboAddAppointmentVaccine.setItems(VaccineType);
        generateAID();
    }
    
    // load the vaccine batch id 
    public void loadvBID(){
        if(!"".equals(cboAddAppointmentVaccine.getValue()) && !"".equals(cboAddAppointmentVenue.getValue())){
            try {
                String [] wordsinLine = null;
                String vtype = cboAddAppointmentVaccine.getValue();
                String vvenue = cboAddAppointmentVenue.getValue();
                boolean stat = false;
                File vf = new File("Vaccine.txt");
                Scanner Sc = new Scanner (vf);
                while (Sc.hasNextLine()) {
                    String Line = Sc.nextLine();
                    if (!(Line.isEmpty())) {
                        wordsinLine = Line.split(";"); 
                        if((vtype.compareTo(wordsinLine[1]) == 0) && (vvenue.compareTo(wordsinLine[2]) == 0)){
                            ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                            cboAddAppointmentvBID.getItems().addAll(VaccineBatch);
                            stat = true;
                        } 
                    }
                }
                if (stat == false){
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("No Vaccine");
                        alert.setContentText("Sorry, there is no " + vtype + " in " + vvenue + ", please re-select.");
                        Optional<ButtonType> result = alert.showAndWait();
                            if(result.get() == ButtonType.OK) {
                                alert.hide();
                                cboAddAppointmentVaccine.requestFocus();
                                cboAddAppointmentvBID.getItems().clear();
                        }
                }
                Sc.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        } 
    }
    
    // auto generate the appointment ID
    public void generateAID () {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Appointment.txt"));   
            String Line;
            String LastLine = "";
            while((Line = br.readLine()) != null) {
                LastLine = Line;
            }
            String [] LastRow = LastLine.split(";");
            String LastID = LastRow[0];
            int intLastID = Integer.parseInt(LastID);
            int newAID = intLastID + 1;
            DecimalFormat dFormat = new DecimalFormat("000");
            lblAddAppointmentID.setText(dFormat.format(newAID));
            br.close();
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    public void addNewAppointment(ActionEvent event) {
	if (txtICAddAppoinment.getText().isEmpty() || txtAddAppointmentDate.getValue() == null || 
            cboAddAppointmentTime.getValue() == null ||
            cboAddAppointmentType.getValue() == null || cboAddAppointmentVenue.getValue() == null || 
            cboAddAppointmentVaccine.getValue() == null || cboAddAppointmentvBID.getValue() == null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Null Value");
            alert.setContentText("Please fill in all required fields !");
            alert.show();
	} else if (txtICAddAppoinment.getText().length() < 7){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please provide a valid IC/Passport Number with at least 7 characters !");
            Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
		    alert.hide();
		    txtICAddAppoinment.setText("");
		}
        } else {
            try {
                // check whether there are existed appointments
                boolean stat = false;
                String [] wordsinLine = null;
                String IC = txtICAddAppoinment.getText();
                String aType = cboAddAppointmentType.getValue();
                File f = new File("Appointment.txt");
                Scanner Sc = new Scanner(f);
                while (Sc.hasNextLine()) {
                    String Line = Sc.nextLine();
                    if (!(Line.isEmpty())) {
                        wordsinLine = Line.split(";"); 
                        //compare data with user input
                        if ((IC.compareTo(wordsinLine[1]) == 0) && (aType.compareTo(wordsinLine[4]) == 0)) {
                            stat = true;
                            break;
                        }
                    }   
                }   
                if (stat == true) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Existed Appointment Record");
                    alert.setContentText("An appointment has been made previously.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK) {
                        alert.hide();
                        txtAddAppointmentDate.getEditor().clear();
                        cboAddAppointmentTime.getEditor().clear();
                        cboAddAppointmentType.getEditor().clear();
                        cboAddAppointmentVenue.getEditor().clear();
                        cboAddAppointmentVaccine.getEditor().clear();
                        cboAddAppointmentvBID.getEditor().clear();
                    }
                } else {
                    // no existed appointment and book the appointment
                    a.setaID(lblAddAppointmentID.getText().trim());
                    a.setaIC(IC);
                    a.setaDate(txtAddAppointmentDate.getValue().toString());
                    a.setaTime(cboAddAppointmentTime.getValue().trim());
                    a.setaType(aType);
                    a.setaVenue(cboAddAppointmentVenue.getValue().trim());
                    a.setaVaccine(cboAddAppointmentVaccine.getValue().trim());
                    a.setaVBID(cboAddAppointmentvBID.getValue().trim());
                    a.writeAData();
                    a.deductVQuan(cboAddAppointmentvBID.getValue());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Successful Booking");
                    alert.setContentText("New Appointment has been made !");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK) {
                        alert.hide();
                        generateAID();
                        refresh();       
                    }
                }	
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 	
    
    // ensure the date selected is not before today's date
    public void verifyDate(){
        txtAddAppointmentDate.focusedProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue) { // focus lost
            if (txtAddAppointmentDate.getValue().equals(java.time.LocalDate.now()) || 
                    (txtAddAppointmentDate.getValue().isBefore(java.time.LocalDate.now()))){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Date");
                alert.setContentText("You are required to make an appointment after today.");
                Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
		    alert.hide();
                    txtAddAppointmentDate.requestFocus();
		}
            }
        }
    });
}
    
    public void refresh(){
        txtICAddAppoinment.setText("");
         try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("BookVaccinationAppointment.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Book Vaccination Appointment");
            primaryStage.show();
            BookVaccinationAppointment.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	}
    }
    
    public void showRegisterProfile(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("RegisterProfile.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Register Profile");
            primaryStage.show();
            BookVaccinationAppointment.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	    	}
    }
    
    public void showPersonnel(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personnel Interface");
            primaryStage.show();
            BookVaccinationAppointment.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	    	}
    }
    
    public void logout(ActionEvent event) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
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
                BookVaccinationAppointment.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }
}
