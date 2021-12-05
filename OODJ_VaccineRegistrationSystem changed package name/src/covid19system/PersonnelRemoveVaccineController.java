
package covid19system;

import java.io.*;
import java.net.URL;
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
    
    // load the vaccine batch id 
    public void loadvBID(){
        if(!"".equals(CcboRemoveVaccineVenue.getValue()) && !"".equals(CcboRemoveVaccineType.getValue())){
            try {
                String [] wordsinLine = null;
                String vtype = CcboRemoveVaccineType.getValue();
                String vvenue = CcboRemoveVaccineVenue.getValue();
                boolean stat = false;
                File vf = new File("Vaccine.txt");
                Scanner Sc = new Scanner (vf);
                while (Sc.hasNextLine()) {
                    String Line = Sc.nextLine();
                    if (!(Line.isEmpty())) {
                        wordsinLine = Line.split(";"); 
                        if((vtype.compareTo(wordsinLine[1]) == 0) && (vvenue.compareTo(wordsinLine[2]) == 0)){
                            ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                            CcboRemoveBatchID.getItems().addAll(VaccineBatch);
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
                                CcboRemoveVaccineType.requestFocus();
                                CcboRemoveBatchID.getItems().clear();
                        }
                }
                Sc.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void displayVData(){
        CcboRemoveBatchID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(CcboRemoveBatchID.getValue())) {
                    try {
                        v.setvType(CcboRemoveVaccineType.getValue());
                        v.setvVenue(CcboRemoveVaccineVenue.getValue());
                        if(v.readVData()[0] != null){
                            paneVDetails.setDisable(false);
                            ClblRemoveVaccineQuantity.setText(" " + v.readVData()[3]);
                            ClblRemoveVaccineExpDate.setText(" " + v.readVData()[4]);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    @FXML
    public void removeVaccine() throws IOException{
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
                removeDialog.setContentText("Vaccines Batch " + CcboRemoveBatchID.getValue() + " have been removed.");
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
         try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("PersonnelRemoveVaccine.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Remove Vaccine");
            primaryStage.show();
            PersonnelRemoveVaccine.getScene().getWindow().hide();
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
            PersonnelRemoveVaccine.getScene().getWindow().hide();
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
