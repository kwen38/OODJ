
package oodj_vaccineregistrationsystem;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class PersonnelModifyVaccineAvailabilityController implements Initializable {

    @FXML
    private Button CbtnAddVaccineMinus;
    @FXML
    private Button CbtnAddVaccinePlus;
    @FXML
    private Button CbtnBackModifyNewVaccine;
    @FXML
    private Button CbtnModifyNewVaccine;
    @FXML
    private ComboBox<String> CcboModifyBatchID;
    @FXML
    private ComboBox<String> CcboModifyVaccineType;
    @FXML
    private ComboBox<String> CcboModifyVaccineVenue;
    @FXML
    private DatePicker CtxtModifyVaccineExpDate;
    @FXML
    private TextField CtxtModifyVaccineQuantity;
    @FXML
    private AnchorPane PersonnelModifyVAccineAvailability;
    @FXML
    private Pane paneVDetails;
    
    ObservableList<String> VaccineType = FXCollections.observableArrayList("Astrazeneca", "Pfizer", "Sinovac");
    ObservableList<String> VaccineVenue = FXCollections.observableArrayList("Axiata Arena Bukit Jalil", 
                "Kuala Lumpur Convention Centre (KLCC)", "University Malaya (UM)");
    
    Vaccine v = new Vaccine();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // load items into combo box
        CcboModifyVaccineType.setItems(VaccineType);
        CcboModifyVaccineVenue.setItems(VaccineVenue);      
//        CcboModifyVaccineType.focusedProperty().addListener((obs, oldValue, newValue) -> {
//            if (!newValue) { // focus lost
//                if (!"".equals(CcboModifyVaccineType.getValue())) {
//                    v.setvType(CcboModifyVaccineType.getValue());
//                }
//            }
//        });
//        CcboModifyBatchID.focusedProperty().addListener((obs, oldValue, newValue) -> {
//            if (!newValue) {
//                try {
//
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(PersonnelModifyVaccineAvailabilityController.class.getName()).log(Level.SEVERE, null, ex);
//                } 
//            }
//        });
    }
    
    public void check(){
        CcboModifyVaccineVenue.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(CcboModifyVaccineType.getValue()) && !"".equals(CcboModifyVaccineVenue.getValue())) {
                    v.setvType(CcboModifyVaccineType.getValue());
                    v.setvVenue(CcboModifyVaccineVenue.getValue());
                }
            }
        });
        try {
            v.readVData();
            if(v.readVData()[0] != null){
                ObservableList<String> vaccineBID = FXCollections.observableArrayList(v.readVData()[0]);
                System.out.println(vaccineBID);
                CcboModifyBatchID.getItems().addAll(vaccineBID);
            } else {
                System.out.println("no record");
            }   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonnelModifyVaccineAvailabilityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
             
    public void add(){
        int num = 0;
        if(CtxtModifyVaccineQuantity.getText().isEmpty()){
            num += 1;
            CtxtModifyVaccineQuantity.setText(String.valueOf(num));
        } else {
            num = Integer.parseInt(CtxtModifyVaccineQuantity.getText()) + 1;
            CtxtModifyVaccineQuantity.setText(String.valueOf(num));
        }
    }
    
    public void minus(){
        if(CtxtModifyVaccineQuantity.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Null Value");
            alert.setContentText("There is no number provided.");
            alert.show();
        } else{
            int num = Integer.parseInt(CtxtModifyVaccineQuantity.getText());
            num = num - 1;
            CtxtModifyVaccineQuantity.setText(String.valueOf(num));
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
            PersonnelModifyVAccineAvailability.getScene().getWindow().hide();
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
                PersonnelModifyVAccineAvailability.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }    
    
    
   
}
