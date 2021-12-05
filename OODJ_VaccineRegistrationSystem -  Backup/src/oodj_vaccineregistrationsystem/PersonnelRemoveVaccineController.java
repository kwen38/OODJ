
package oodj_vaccineregistrationsystem;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PersonnelRemoveVaccineController implements Initializable {
    
    @FXML
    private Button CbtnBackRemoveVaccine;
    @FXML
    private Button CbtnRemoveVaccine;
    @FXML
    private ComboBox<String> CcboRemoveBatchID;
    @FXML
    private ComboBox<String> CcboRemoveVaccineType;
    @FXML
    private ComboBox<String> CcboRemoveVaccineVenue;
    @FXML
    private Label ClblRemoveVaccineExpDate;
    @FXML
    private Label ClblRemoveVaccineQuantity;
    @FXML
    private Pane paneVDetails;
    @FXML
    private AnchorPane PersonnelRemoveVaccine;
    
    ObservableList<String> VaccineType = FXCollections.observableArrayList("Astrazeneca", "Pfizer", "Sinovac");
    ObservableList<String> VaccineVenue = FXCollections.observableArrayList("Axiata Arena Bukit Jalil", 
                "Kuala Lumpur Convention Centre (KLCC)", "University Malaya (UM)");
    
    Vaccine v = new Vaccine();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // load items into combo box
        CcboRemoveVaccineType.setItems(VaccineType);
        CcboRemoveVaccineVenue.setItems(VaccineVenue); 
    }  
    
    @FXML
    public void remove() throws IOException{
        if(!(CcboRemoveVaccineType.getValue().isEmpty()) || !(CcboRemoveVaccineVenue.getValue().isEmpty()) ||
                !(CcboRemoveBatchID.getValue().isEmpty()) || 
                !(ClblRemoveVaccineQuantity.getText().isEmpty()) || !(ClblRemoveVaccineExpDate.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation Dialog");
            alert.setContentText("Are you sure want to remove vaccines Batch " + CcboRemoveBatchID.getValue() + "?");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(btnYes, btnNo);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == btnYes) {
                alert.hide();
                v.setvBID(CcboRemoveBatchID.getValue());
                v.removeVData();
                // inform dialog
                Alert removeDialog = new Alert(Alert.AlertType.INFORMATION);
                removeDialog.setHeaderText(null);
                removeDialog.setTitle("Vaccine Removed Successfully");
                removeDialog.setContentText("The vaccines Batch " + CcboRemoveBatchID.getValue() + "have been removed.");
                Optional<ButtonType> result1 = removeDialog.showAndWait();
                if(result1.get() == ButtonType.OK) {
                        removeDialog.hide();
                        refresh();
                }  
            } else if (result.get() == btnNo) {
                alert.hide();
            }
        }
    }
    
    public void refresh(){
        CcboRemoveVaccineType.setValue(null);
        CcboRemoveVaccineVenue.setValue(null);
        CcboRemoveBatchID.setValue(null);
        ClblRemoveVaccineQuantity.setText("");
        ClblRemoveVaccineExpDate.setText("");
    }
   
    public void showPersonnel(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personnel Interface");
            primaryStage.show();
            PersonnelRemoveVaccine.getScene().getWindow().hide();
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
                PersonnelRemoveVaccine.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }
    
}
