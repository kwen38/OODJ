
package covid19system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PeopleViewAppointmentDetailsController implements Initializable {
    
    @FXML
    private AnchorPane PeopleViewAppointmentDetails;
    @FXML
    private TextField PlblViewAppointmentDate;
    @FXML
    private TextField PlblViewAppointmentIC;
    @FXML
    private TextField PlblViewAppointmentID;
    @FXML
    private TextField PlblViewAppointmentName;
    @FXML
    private TextField PlblViewAppointmentTime;
    @FXML
    private TextField PlblViewAppointmentType;
    @FXML
    private TextField PlblViewAppointmentVenue;
    @FXML
    private TextField PlblViewAppointmentVaccine;
    @FXML
    private TextField PlblViewAppointmentvBID;
    @FXML
    private Button btnBackViewAppointment;
    
    Appointment a = new Appointment();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (a.readAData()[1] != null){
                PlblViewAppointmentID.setText(a.readAData()[0]);
                PlblViewAppointmentName.setText("");
                PlblViewAppointmentIC.setText(a.readAData()[1]);
                PlblViewAppointmentDate.setText(a.readAData()[2]);
                PlblViewAppointmentTime.setText(a.readAData()[3]);
                PlblViewAppointmentType.setText(a.readAData()[4]);
                PlblViewAppointmentVenue.setText(a.readAData()[5]);
                PlblViewAppointmentVaccine.setText(a.readAData()[5]);
                PlblViewAppointmentvBID.setText(a.readAData()[5]);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("No Record");
                alert.setContentText("You have not make an appointment before.");
                Optional<ButtonType> result1 = alert.showAndWait();
                if(result1.get() == ButtonType.OK) {    
                    alert.hide();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PeopleCancelAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
       
    public void showPeople(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("People.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("People Interface");
            primaryStage.show();
            PeopleViewAppointmentDetails.getScene().getWindow().hide();
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
                PeopleViewAppointmentDetails.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }
    
}
