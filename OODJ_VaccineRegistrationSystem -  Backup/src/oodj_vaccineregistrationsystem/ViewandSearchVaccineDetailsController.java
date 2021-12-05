
package oodj_vaccineregistrationsystem;

import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class ViewandSearchVaccineDetailsController implements Initializable {
    
    @FXML
    private Pane AZTab;
    @FXML
    private Pane BJTab;
    @FXML
    private Button CbtnBackViewVaccineType;
    @FXML
    private Button CbtnBackViewVaccineVenue;
    @FXML
    private Pane KLCCTab;
    @FXML
    private Pane PfizerTab;
    @FXML
    private Pane SinovacTab;
    @FXML
    private Pane UMTab;
    @FXML
    private AnchorPane ViewandSearchVaccineDetails;
    @FXML
    private ComboBox<String> cboAZBID;
    @FXML
    private ComboBox<String> cboBJBID;
    @FXML
    private ComboBox<String> cboKLCCBID;
    @FXML
    private ComboBox<String> cboPfizerBID;
    @FXML
    private ComboBox<String> cboSinovacBID;
    @FXML
    private ComboBox<String> cboUMBID;
    @FXML
    private ComboBox<String> cboVnSVaccineType;
    @FXML
    private ComboBox<String> cboVnSVaccineVenue;
    @FXML
    private Label lblAZExpDate;
    @FXML
    private Label lblAZQuantity;
    @FXML
    private Label lblBJExpDate;
    @FXML
    private Label lblBJQuantity;
    @FXML
    private Label lblKLCCExpDate;
    @FXML
    private Label lblKLCCQuantity;
    @FXML
    private Label lblPfizerExpDate;
    @FXML
    private Label lblPfizerQuantity;
    @FXML
    private Label lblSinovacExpDate;
    @FXML
    private Label lblSinovacQuantity;
    @FXML
    private Label lblUMExpDate;
    @FXML
    private Label lblUMQuantity;
    @FXML
    private Label LBLAZExpDate;
    @FXML
    private Label LBLAZQuantity;
    @FXML
    private Label LBLKLCCExpDate;
    @FXML
    private Label LBLKLCCQuantity;
    @FXML
    private Label LBLPfizerExpDate;
    @FXML
    private Label LBLPfizerQuantity;
    @FXML
    private Label LBLSinovacExpDate;
    @FXML
    private Label LBLSinovacQuantity;
    @FXML
    private Label LBLUMExpDate;
    @FXML
    private Label LBLUMQuantity;
    @FXML
    private Label LBLBJExpDate;
    @FXML
    private Label LBLBJQuantity;

    
    ObservableList<String> VaccineType = FXCollections.observableArrayList("Astrazeneca", "Pfizer", "Sinovac");
    ObservableList<String> VaccineVenue = FXCollections.observableArrayList("Axiata Arena Bukit Jalil", 
                "Kuala Lumpur Convention Centre (KLCC)", "University Malaya (UM)");


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboVnSVaccineType.setItems(VaccineType);
        cboVnSVaccineVenue.setItems(VaccineVenue);
        cboVnSVaccineVenue.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboVnSVaccineVenue.getValue())) {
                    PfizerTab.setDisable(false);
                    LBLPfizerQuantity.setDisable(true);
                    LBLPfizerExpDate.setDisable(true);
                    SinovacTab.setDisable(false);
                    LBLSinovacQuantity.setDisable(true);
                    LBLSinovacExpDate.setDisable(true);
                    AZTab.setDisable(false);
                    LBLAZQuantity.setDisable(true);
                    LBLAZExpDate.setDisable(true);
                }
            }
        });
        cboVnSVaccineType.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboVnSVaccineType.getValue())) {
                    BJTab.setDisable(false);
                    LBLBJQuantity.setDisable(true);
                    LBLBJExpDate.setDisable(true);
                    KLCCTab.setDisable(false);
                    LBLKLCCQuantity.setDisable(true);
                    LBLKLCCExpDate.setDisable(true);
                    UMTab.setDisable(false);
                    LBLUMQuantity.setDisable(true);
                    LBLUMExpDate.setDisable(true);
                }
            }
        });
    }

   
    public void showPersonnel(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personnel Interface");
            primaryStage.show();
            ViewandSearchVaccineDetails.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
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
            ViewandSearchVaccineDetails.getScene().getWindow().hide();
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
                ViewandSearchVaccineDetails.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }    
    
}
