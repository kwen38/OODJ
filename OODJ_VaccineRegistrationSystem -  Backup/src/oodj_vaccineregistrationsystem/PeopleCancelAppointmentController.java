
package oodj_vaccineregistrationsystem;

import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PeopleCancelAppointmentController implements Initializable {
    
    @FXML
    private AnchorPane PeopleCancelAppointment;
    @FXML
    private Button btnCancelAppointment;
    @FXML
    private TextField lblCancelAppointmentDate;
    @FXML
    private TextField lblCancelAppointmentTime;
    @FXML
    private TextField lblCancelAppointmentID;
    @FXML
    private TextField lblCancelAppointmentName;
    @FXML
    private TextField lblCancelAppointmentType;
    @FXML
    private TextField lblCancelAppointmentVenue;
    @FXML
    private TextField lblCancelAppointmentVaccine;
    @FXML
    private TextField lblCancelAppointmentvBID;
    @FXML
    private Hyperlink linkCancelForgotAID;
    @FXML
    private TextField txtaIDCancelAppoinment;
    @FXML
    private Pane paneADetails;

    Appointment a = new Appointment();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtaIDCancelAppoinment.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(txtaIDCancelAppoinment.getText())) {
                    try {
                        a.setaID(txtaIDCancelAppoinment.getText());
                        if(a.readAData()[0] != null){
                            paneADetails.setDisable(false);
                            lblCancelAppointmentID.setText(a.readAData()[0]);
                            lblCancelAppointmentDate.setText(a.readAData()[2]);
                            lblCancelAppointmentTime.setText(a.readAData()[3]);
                            lblCancelAppointmentType.setText(a.readAData()[4]);
                            lblCancelAppointmentVenue.setText(a.readAData()[5]); 
                            lblCancelAppointmentVaccine.setText(a.readAData()[6]);
                            lblCancelAppointmentvBID.setText(a.readAData()[7]);
                        } else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setTitle("No Existed Record");
                            alert.setContentText("We are not able to find the specific record.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if(result.get() == ButtonType.OK) {
                                alert.hide();
                                txtaIDCancelAppoinment.setText("");
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
                    lblCancelAppointmentID.setText(a.readAData()[0]);
                    lblCancelAppointmentDate.setText(a.readAData()[2]);
                    lblCancelAppointmentTime.setText(a.readAData()[3]);
                    lblCancelAppointmentType.setText(a.readAData()[4]);
                    lblCancelAppointmentVenue.setText(a.readAData()[5]); 
                    lblCancelAppointmentVaccine.setText(a.readAData()[6]);
                    lblCancelAppointmentvBID.setText(a.readAData()[7]);
                }
                else {
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
    
    @FXML
    public void cancelAppointment() throws IOException{
        if(!(txtaIDCancelAppoinment.getText().isEmpty()) || !(lblCancelAppointmentName.getText().isEmpty()) || 
             !(lblCancelAppointmentID.getText().isEmpty()) || !(lblCancelAppointmentDate.getText().isEmpty()) ||
                !(lblCancelAppointmentTime.getText().isEmpty()) || !(lblCancelAppointmentType.getText().isEmpty()) ||
                !(lblCancelAppointmentVenue.getText().isEmpty()) || !(lblCancelAppointmentVaccine.getText().isEmpty()) ||
                !(lblCancelAppointmentvBID.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation Dialog");
            alert.setContentText("Are you sure want to cancel this appointment?");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(btnYes, btnNo);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == btnYes) {
                alert.hide();
                a.setaID(lblCancelAppointmentID.getText());
                a.removeAData();
                a.increaseVQuan(lblCancelAppointmentvBID.getText());
                // inform dialog
                Alert removeDialog = new Alert(Alert.AlertType.INFORMATION);
                removeDialog.setHeaderText(null);
                removeDialog.setTitle("Appointment Removed Successfully");
                removeDialog.setContentText("The appointment has been cancelled.");
                Optional<ButtonType> result1 = removeDialog.showAndWait();
                if(result1.get() == ButtonType.OK) {
                        removeDialog.hide();
                        refresh();
                        paneADetails.setDisable(true);
                }  
            } else if (result.get() == btnNo) {
                alert.hide();
            }
        }
    }
    
    public void refresh(){
        txtaIDCancelAppoinment.setText("");
        lblCancelAppointmentName.setText("");
        lblCancelAppointmentID.setText("");
        lblCancelAppointmentDate.setText("");
        lblCancelAppointmentTime.setText("");
        lblCancelAppointmentType.setText("");
        lblCancelAppointmentVenue.setText("");
        lblCancelAppointmentVaccine.setText("");
        lblCancelAppointmentvBID.setText("");
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
            PeopleCancelAppointment.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	    	}
    }
    
    @FXML
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
                PeopleCancelAppointment.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }
}
