
package oodj_vaccineregistrationsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
    @FXML
    private MenuBar btnLogout;
    @FXML
    private TabPane CModifyVaccineAvailability;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // load items into combo box
        CcboModifyVaccineType.setItems(VaccineType);
        CcboModifyVaccineVenue.setItems(VaccineVenue);      
    }
    
    // load the vaccine batch id 
    @FXML
    public void loadvBID(){
        if(!"".equals(CcboModifyVaccineVenue.getValue()) && !"".equals(CcboModifyVaccineType.getValue())){
            try {
                String [] wordsinLine = null;
                String vtype = CcboModifyVaccineType.getValue();
                String vvenue = CcboModifyVaccineVenue.getValue();
                boolean stat = false;
                File vf = new File("Vaccine.txt");
                Scanner Sc = new Scanner (vf);
                while (Sc.hasNextLine()) {
                    String Line = Sc.nextLine();
                    if (!(Line.isEmpty())) {
                        wordsinLine = Line.split(";"); 
                        if((vtype.compareTo(wordsinLine[1]) == 0) && (vvenue.compareTo(wordsinLine[2]) == 0)){
                            ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                            CcboModifyBatchID.getItems().addAll(VaccineBatch);
                            stat = true;
                        } 
                    }
                }
                if (stat == false){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("No Vaccine");
                        alert.setContentText("Sorry, there is no " + vtype + " in " + vvenue + ", please re-select.");
                        Optional<ButtonType> result = alert.showAndWait();
                            if(result.get() == ButtonType.OK) {
                                alert.hide();
                                CcboModifyVaccineType.requestFocus();
                                CcboModifyBatchID.getItems().clear();
                        }
                }
                Sc.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void displayVData(){
        CcboModifyBatchID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(CcboModifyBatchID.getValue())) {
                    try {
                        v.setvType(CcboModifyVaccineType.getValue());
                        v.setvVenue(CcboModifyVaccineVenue.getValue());
                        if(v.readVData()[0] != null){
                            paneVDetails.setDisable(false);
                            CtxtModifyVaccineQuantity.setText(v.readVData()[3]);
                            CtxtModifyVaccineExpDate.setValue(LocalDate.parse(v.readVData()[4]));
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    @FXML
    public void updateVaccine() throws IOException{
        if(!CtxtModifyVaccineQuantity.getText().isEmpty()){
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setHeaderText(null);
            confirmDialog.setTitle("Confirmation Dialog");
            confirmDialog.setContentText("Are you sure want to change the vaccine details?");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            confirmDialog.getButtonTypes().setAll(btnYes, btnNo);
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if(result.get() == btnYes) {
                confirmDialog.hide();
                String vbid = CcboModifyBatchID.getValue();
                String vquan = CtxtModifyVaccineQuantity.getText();
                String vexpdate = String.valueOf(CtxtModifyVaccineExpDate.getValue());
                v.updateVData(vbid, vquan, vexpdate);
                // inform dialog
                Alert updateDialog = new Alert(Alert.AlertType.INFORMATION);
                updateDialog.setHeaderText(null);
                updateDialog.setTitle("Vaccine Details Updated Successfully");
                updateDialog.setContentText("The vaccine details have been changed successfully.");
                Optional<ButtonType> result1 = updateDialog.showAndWait();
                if(result1.get() == ButtonType.OK) {
                    updateDialog.hide();
                    refresh();
                }  
            }
        }

    }
        
    @FXML
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
    
    @FXML
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
    
    public void refresh(){
         try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("PersonnelModifyVaccineAvailability.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Modify Vaccine Availability");
            primaryStage.show();
            PersonnelModifyVAccineAvailability.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	}
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
            PersonnelModifyVAccineAvailability.getScene().getWindow().hide();
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
