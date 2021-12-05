
package covid19system;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PersonnelAddNewVaccineController implements Initializable {
    
    @FXML
    private TabPane CNewVaccine;
    @FXML
    private Button CbtnAddNewVaccine;
    @FXML
    private Button CbtnAddVaccineMinus;
    @FXML
    private Button CbtnAddVaccinePlus;
    @FXML
    private Button CbtnBackAddNewVaccine;
    @FXML
    private ComboBox<String> CcboAddVaccineType;
    @FXML
    private ComboBox<String> CcboAddVaccineVenue;
    @FXML
    private TextField CtxtAddVaccineBatchID;
    @FXML
    private DatePicker CtxtAddVaccineExpDate;
    @FXML
    private TextField CtxtAddVaccineQuantity;
    @FXML
    private AnchorPane PersonnelAddNewVaccine;
    
    ObservableList<String> VaccineType = FXCollections.observableArrayList("Astrazeneca", "Pfizer", "Sinovac");
    ObservableList<String> VaccineVenue = FXCollections.observableArrayList("Axiata Arena Bukit Jalil", 
                "Kuala Lumpur Convention Centre (KLCC)", "University Malaya (UM)");
    
    //Create instance for vaccine class
    Vaccine v = new Vaccine();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CcboAddVaccineType.setItems(VaccineType);
        CcboAddVaccineVenue.setItems(VaccineVenue);
        generateBID();
    }    
    
    // auto generate the vaccine batch ID
    public void generateBID () {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Vaccine.txt"));   
            String Line;
            String LastLine = "";
            while((Line = br.readLine()) != null) {
                LastLine = Line;
            }
            String [] LastRow = LastLine.split(";");
            String LastID = LastRow[0];
            int intLastID = Integer.parseInt(LastID);
            int newBID = intLastID + 1;
            DecimalFormat dFormat = new DecimalFormat("000");
            CtxtAddVaccineBatchID.setText(dFormat.format(newBID));
            br.close();
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    public void addNewVaccine(ActionEvent event) {
	if (CtxtAddVaccineBatchID.getText().isEmpty() || CcboAddVaccineType.getValue() == null || 
            CcboAddVaccineVenue.getValue() == null ||
            CtxtAddVaccineQuantity.getText().isEmpty() || CtxtAddVaccineExpDate.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Null Value");
            alert.setContentText("Please fill in all required fields !");
            alert.show();
	} else if (!CtxtAddVaccineQuantity.getText().matches("\\d*")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please provide only NUMBERS for Vaccine Quantity");
            Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
		    alert.hide();
		    CtxtAddVaccineQuantity.setText("");
		}
        } else {
            try {
                // check whether there are duplicated records
                boolean stat = false;
                String [] wordsinLine = null;
                String ID = CtxtAddVaccineBatchID.getText();
                String vType = CcboAddVaccineType.getValue();
                String vVenue = CcboAddVaccineVenue.getValue();
                File f = new File("Vaccine.txt");
                Scanner Sc = new Scanner(f);
                while (Sc.hasNextLine()) {
                    String Line = Sc.nextLine();
                    if (!(Line.isEmpty())) {
                        wordsinLine = Line.split(";"); 
                        //compare data with user input
                        if ((ID.compareTo(wordsinLine[0]) == 0) && (vType.compareTo(wordsinLine[1]) == 0) &&
                                (vVenue.compareTo(wordsinLine[2]) == 0)) {
                            stat = true;
                            break;
                        }
                    }   
                }   
                if (stat == true) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Existed Records");
                    alert.setContentText("Vaccines with the batch ID: " + ID + "have been recorded.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK) {
                        alert.hide();
                        Alert askDialog = new Alert(Alert.AlertType.CONFIRMATION);
                        askDialog.setHeaderText(null);
                        askDialog.setTitle("Confirmation Dialog");
                        askDialog.setContentText("Are you wish to change the quantity of the vaccines?");
                        ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                        ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
                        askDialog.getButtonTypes().setAll(btnYes, btnNo);
                        Optional<ButtonType> askR = askDialog.showAndWait();
                        if(askR.get() == btnYes) {
                                alert.hide();
                                showModifyVaccine();
                        } else if (result.get() == btnNo) {
                            alert.hide();
                        }
                    }
                } else {
                    // no existed records
                    v.setvBID(ID);
                    v.setvType(vType);
                    v.setvVenue(vVenue);
                    v.setvQuan(Integer.parseInt(CtxtAddVaccineQuantity.getText()));
                    v.setvExpDate(CtxtAddVaccineExpDate.getValue());
                    v.writeVData();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Successful");
                    alert.setContentText("The vaccines has been successfully added !");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK) {
                        alert.hide();
                        refresh();
                        generateBID();                        
                    }
                }	
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void verifyDate(){
        CtxtAddVaccineExpDate.focusedProperty().addListener((obs, oldValue, newValue) -> {
        if (!newValue) { // focus lost
            if (CtxtAddVaccineExpDate.getValue().equals(java.time.LocalDate.now()) || 
                    (CtxtAddVaccineExpDate.getValue().isBefore(java.time.LocalDate.now()))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Expiration Date");
                alert.setContentText("The expiration date for the vaccines is invalid.");
                Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
		    alert.hide();
                    CtxtAddVaccineExpDate.requestFocus();
		}
            }
        }
    });
}
    
    public void add(){
        int num = 0;
        if(CtxtAddVaccineQuantity.getText().isEmpty()){
            num += 1;
            CtxtAddVaccineQuantity.setText(String.valueOf(num));
        } else {
            num = Integer.parseInt(CtxtAddVaccineQuantity.getText()) + 1;
            CtxtAddVaccineQuantity.setText(String.valueOf(num));
        }
    }
    
    public void minus(){
        if(CtxtAddVaccineQuantity.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Null Value");
            alert.setContentText("There is no number provided.");
            alert.show();
        } else{
            int num = Integer.parseInt(CtxtAddVaccineQuantity.getText());
            num = num - 1;
            CtxtAddVaccineQuantity.setText(String.valueOf(num));
        }
    }

    public void refresh(){
        CcboAddVaccineType.setValue(null);
        CcboAddVaccineVenue.setValue(null);
        CtxtAddVaccineQuantity.setText("");
        CtxtAddVaccineExpDate.setValue(null);
    }
    
    public void showPersonnel(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Personnel.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Personnel Interface");
            primaryStage.show();
            PersonnelAddNewVaccine.getScene().getWindow().hide();
	}
	catch (Exception e) {
	    System.out.println("Can't load new window.");
	}
    }
    
    public void showModifyVaccine(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("PersonnelModifyVaccineAvailability.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Modify Vaccine Availability");
            primaryStage.show();
            PersonnelAddNewVaccine.getScene().getWindow().hide();
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
                PersonnelAddNewVaccine.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }    
    
}
