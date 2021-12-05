
package covid19system;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PersonnelSearchAppointmentController implements Initializable {
    
    @FXML
    private Button CbtnBackSearchAppointment;
    @FXML
    private Button CbtnSearchAppointment;
    @FXML
    private TextField ClblSearchAppointmentDate;
    @FXML
    private TextField ClblSearchAppointmentID;
    @FXML
    private TextField ClblSearchAppointmentName;
    @FXML
    private TextField ClblSearchAppointmentTime;
    @FXML
    private TextField ClblSearchAppointmentType;
    @FXML
    private TextField ClblSearchAppointmentVenue;
    @FXML
    private TextField ClblSearchAppointmentVaccine;
    @FXML
    private TextField ClblSearchAppointmentvBID;
    @FXML
    private Hyperlink ClinkSearchForgotAID;
    @FXML
    private TextField CtxtSearchAID;
    @FXML
    private AnchorPane PersonnelSearchAppointment;
    @FXML
    private Pane paneADetails;

    Appointment a = new Appointment();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
    
    @FXML
    public void searchAData(){
        if (!CtxtSearchAID.getText().isEmpty()) {
            try {
                a.setaID(CtxtSearchAID.getText());
                if(a.readAData()[0] != null){
                paneADetails.setDisable(false);
                ClblSearchAppointmentID.setText(a.readAData()[0]);
                ClblSearchAppointmentDate.setText(a.readAData()[2]);
                ClblSearchAppointmentTime.setText(a.readAData()[3]);
                ClblSearchAppointmentType.setText(a.readAData()[4]);
                ClblSearchAppointmentVenue.setText(a.readAData()[5]);  
                ClblSearchAppointmentVaccine.setText(a.readAData()[6]); 
                ClblSearchAppointmentvBID.setText(a.readAData()[7]); 
                } else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("No Existed Record");
                    alert.setContentText("We are not able to find the specific record.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK) {
                        alert.hide();
                        CtxtSearchAID.setText("");
                   }
                }
            } catch (Exception Ex) {
                 Ex.printStackTrace();
            }       
        } else {
            refresh();
            Alert nullAlert = new Alert(AlertType.WARNING);
            nullAlert.setHeaderText(null);
            nullAlert.setTitle("Invalid Appointment ID");
            nullAlert.setContentText("Please specify a valid appointment ID.");
            Optional<ButtonType> result = nullAlert.showAndWait();
            if(result.get() == ButtonType.OK) {
                nullAlert.hide();
            }
        }
    }

    @FXML
    public void forgotaID() {
        refresh();
    	TextInputDialog td = new TextInputDialog();
    	td.setHeaderText(null);
	td.setTitle("IC/Passport Number is required");
	td.setContentText("Please provide a valid IC/Passport Number (with HYPHEN):");	
	Optional <String> result = td.showAndWait();
	if(result.isPresent()) {
            try {
                //a.setaIC(result.get());
                if (result.get().equals(a.readAData()[1])){
                    paneADetails.setDisable(false);
                    ClblSearchAppointmentID.setText(a.readAData()[0]);
                    ClblSearchAppointmentDate.setText(a.readAData()[2]);
                    ClblSearchAppointmentTime.setText(a.readAData()[3]);
                    ClblSearchAppointmentType.setText(a.readAData()[4]);
                    ClblSearchAppointmentVenue.setText(a.readAData()[5]); 
                    ClblSearchAppointmentVaccine.setText(a.readAData()[6]); 
                    ClblSearchAppointmentvBID.setText(a.readAData()[7]); 
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
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
    
    public void refresh(){
        paneADetails.setDisable(true);
        CtxtSearchAID.setText("");
        ClblSearchAppointmentName.setText("");
        ClblSearchAppointmentID.setText("");
        ClblSearchAppointmentDate.setText("");
        ClblSearchAppointmentTime.setText("");
        ClblSearchAppointmentType.setText("");
        ClblSearchAppointmentVenue.setText("");
        ClblSearchAppointmentVaccine.setText("");
        ClblSearchAppointmentvBID.setText("");
    }
    
    @FXML
    public void showPersonnel(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personnel Interface");
            primaryStage.show();
            PersonnelSearchAppointment.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	}
    }
    
    @FXML
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
                PersonnelSearchAppointment.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }
    
}
