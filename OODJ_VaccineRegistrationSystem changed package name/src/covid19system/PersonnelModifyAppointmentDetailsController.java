
package covid19system;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PersonnelModifyAppointmentDetailsController implements Initializable {
    
    
    @FXML
    private Button CbtnUpdateAppointmentDetails;
    @FXML
    private ComboBox<String> CcboModifyAppoinmentTime;
    @FXML
    private TextField ClblModifyAppoinmentType;
    @FXML
    private TextField ClblModifyAppointmentVenue;
    @FXML
    private TextField ClblModifyAppointmentVaccine;
    @FXML
    private TextField ClblModifyAppointmentvBID;
    @FXML
    private TextField ClblModifyAppointmentID;
    @FXML
    private TextField ClblModifyAppointmentName;
    @FXML
    private Hyperlink ClinkModifyForgotAID;
    @FXML
    private TextField CtxtIDModifyAppointment;
    @FXML
    private DatePicker CtxtModifyAppointmentDate;
    @FXML
    private AnchorPane PersonnelModifyAppointmentDetails;
    @FXML
    private Pane paneADetails;
    
    // Add items into Combo Box
    ObservableList<String> AppointmentTime = FXCollections.observableArrayList("8:00AM", "9:00AM", "10:00AM", "11:00AM",
            "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM");

    Appointment a = new Appointment();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CcboModifyAppoinmentTime.setItems(AppointmentTime);
        CtxtIDModifyAppointment.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(CtxtIDModifyAppointment.getText())) {
                    try {
                        a.setaID(CtxtIDModifyAppointment.getText());
                        if(a.readAData()[0] != null){
                            paneADetails.setDisable(false);
                            ClblModifyAppointmentName.setDisable(true);
                            ClblModifyAppointmentID.setDisable(true);
                            ClblModifyAppoinmentType.setDisable(true);
                            ClblModifyAppointmentVenue.setDisable(true);
                            ClblModifyAppointmentVaccine.setDisable(true);
                            ClblModifyAppointmentvBID.setDisable(true);
                            ClblModifyAppointmentID.setText(a.readAData()[0]);
                            LocalDate ldate = LocalDate.parse(a.readAData()[2]); // convert string value to Local Date format
                            CtxtModifyAppointmentDate.setValue(ldate); // pass the converted date to be shown on respective field
                            CcboModifyAppoinmentTime.getSelectionModel().select(a.readAData()[3]);
                            ClblModifyAppoinmentType.setText(a.readAData()[4]);
                            ClblModifyAppointmentVenue.setText(a.readAData()[5]);  
                            ClblModifyAppointmentVaccine.setText(a.readAData()[6]);
                            ClblModifyAppointmentvBID.setText(a.readAData()[7]);
                        } else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setTitle("No Existed Record");
                            alert.setContentText("We are not able to find the specific record.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if(result.get() == ButtonType.OK) {
                                alert.hide();
                                CtxtIDModifyAppointment.setText("");
                            }
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                } else {
                    refresh();
                    Alert nullAlert = new Alert(Alert.AlertType.WARNING);
                    nullAlert.setHeaderText(null);
                    nullAlert.setTitle("Invalid Appointment ID");
                    nullAlert.setContentText("Please specify a valid appointment ID.");
                    Optional<ButtonType> result = nullAlert.showAndWait();
                    if(result.get() == ButtonType.OK) {
                    nullAlert.hide();
                    }
                }
            }
        });
    }  

    public void forgotaID() {
        refresh();
    	TextInputDialog td = new TextInputDialog();
    	td.setHeaderText(null);
	td.setTitle("IC/Passport Number is required");
	td.setContentText("Please provide a valid IC/Passport Number (with HYPHEN):");	
	Optional <String> result = td.showAndWait();
	if(result.isPresent()) {
            CtxtIDModifyAppointment.setText(result.get());
            try {
                //a.setaIC(result.get());
                if (result.get().equals(a.readAData()[1])){
                    paneADetails.setDisable(false);
                    ClblModifyAppointmentName.setDisable(true);
                    ClblModifyAppointmentID.setDisable(true);
                    ClblModifyAppoinmentType.setDisable(true);
                    ClblModifyAppointmentVenue.setDisable(true);
                    ClblModifyAppointmentVaccine.setDisable(true);
                    ClblModifyAppointmentvBID.setDisable(true);
                    ClblModifyAppointmentID.setText(a.readAData()[0]);
                    LocalDate ldate = LocalDate.parse(a.readAData()[2]); // convert string value to Local Date format
                    CtxtModifyAppointmentDate.setValue(ldate); // pass the converted date to be shown on respective field
                    CcboModifyAppoinmentTime.getSelectionModel().select(a.readAData()[3]);
                    ClblModifyAppoinmentType.setText(a.readAData()[4]);
                    ClblModifyAppointmentVenue.setText(a.readAData()[5]);  
                    ClblModifyAppointmentVaccine.setText(a.readAData()[6]);
                    ClblModifyAppointmentvBID.setText(a.readAData()[7]);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("No Existed Record");
                    alert.setContentText("We are not able to find the specific record.");
                    Optional<ButtonType> result1 = alert.showAndWait();
                    if(result1.get() == ButtonType.OK) {
                        alert.hide(); 
                    }    
                }
            } catch (Exception e){
                e.printStackTrace();
            }
	}
    }
    
    public void updateAppointment() throws IOException{
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setHeaderText(null);
            confirmDialog.setTitle("Confirmation Dialog");
            confirmDialog.setContentText("Are you sure want to change the appointment details?");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            confirmDialog.getButtonTypes().setAll(btnYes, btnNo);
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if(result.get() == btnYes) {
                confirmDialog.hide();
                String aid = ClblModifyAppointmentID.getText();
                String aic = CtxtIDModifyAppointment.getText();
                String adate = String.valueOf(CtxtModifyAppointmentDate.getValue());
                String atime = CcboModifyAppoinmentTime.getValue();
                a.updateAData(aid, aic, adate, atime);
                // inform dialog
                Alert updateDialog = new Alert(Alert.AlertType.INFORMATION);
                updateDialog.setHeaderText(null);
                updateDialog.setTitle("Appointment Details Updated Successfully");
                updateDialog.setContentText("The appointment details have been changed successfully.");
                Optional<ButtonType> result1 = updateDialog.showAndWait();
                if(result1.get() == ButtonType.OK) {
                        updateDialog.hide();
                        refresh();
                }  
            }
    }
    
    // ensure the date selected is not before today's date
    public void verifyDate(){
        CtxtModifyAppointmentDate.focusedProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue) { // focus lost
            if (CtxtModifyAppointmentDate.getValue().equals(java.time.LocalDate.now()) || 
                    (CtxtModifyAppointmentDate.getValue().isBefore(java.time.LocalDate.now()))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Date");
                alert.setContentText("You are required to make an appointment after today.");
                Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
		    alert.hide();
		}
            }
        }
    });
}
    
    public void refresh(){
        paneADetails.setDisable(true);
        CtxtIDModifyAppointment.setText("");
        ClblModifyAppointmentID.setText("");
        ClblModifyAppointmentName.setText("");
        CtxtModifyAppointmentDate.setValue(null);
        CcboModifyAppoinmentTime.setValue(null);
        ClblModifyAppoinmentType.setText("");
        ClblModifyAppointmentVenue.setText("");
        ClblModifyAppointmentVaccine.setText("");
        ClblModifyAppointmentvBID.setText("");
    }
    
    public void showPersonnel(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personnel Interface");
            primaryStage.show();
            PersonnelModifyAppointmentDetails.getScene().getWindow().hide();
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
                PersonnelModifyAppointmentDetails.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }
    
}
