
package covid19system;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PeopleViewnSearchVaccineDetailsController implements Initializable {
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
    private AnchorPane PeopleViewandSearchVaccine;
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
    
    Vaccine v = new Vaccine();
    Vaccine_AZ az = new Vaccine_AZ();
    Vaccine_Pfizer pfizer = new Vaccine_Pfizer();
    Vaccine_Sinovac sinovac = new Vaccine_Sinovac();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboVnSVaccineType.setItems(VaccineType);
        cboVnSVaccineVenue.setItems(VaccineVenue);
        cboVnSVaccineVenue.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboVnSVaccineVenue.getValue())) {
                    loadPBID();
                    loadSBID();
                    loadAZBID();
                }
            }
        });
        cboVnSVaccineType.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboVnSVaccineType.getValue())) {
                    loadBJBID();
                    loadKLCCBID();
                    loadUMBID();
                }
            }
        });
    }
    
    public void loadPBID(){
        switch (cboVnSVaccineVenue.getValue()) {
            case "Axiata Arena Bukit Jalil" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Pfizer".compareTo(wordsinLine[1]) == 0) && ("Axiata Arena Bukit Jalil".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboPfizerBID.getItems().addAll(VaccineBatch);
                                stat = true; 
                            }
                        }
                    }
                    if (stat == true){
                        PfizerTab.setDisable(false); 
                        LBLPfizerQuantity.setDisable(true);
                        LBLPfizerExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Kuala Lumpur Convention Centre (KLCC)" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Pfizer".compareTo(wordsinLine[1]) == 0) && 
                                    ("Kuala Lumpur Convention Centre (KLCC)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboPfizerBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        } 
                    }
                    if (stat == true){
                        PfizerTab.setDisable(false);
                        LBLPfizerQuantity.setDisable(true);
                        LBLPfizerExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "University Malaya (UM)" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Pfizer".compareTo(wordsinLine[1]) == 0) && ("University Malaya (UM)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboPfizerBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        }
                    }
                    if (stat == true){
                        PfizerTab.setDisable(false);
                        LBLPfizerQuantity.setDisable(true);
                        LBLPfizerExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }
    }
    
    public void showPfizer(){
        cboPfizerBID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboPfizerBID.getValue())) {
                    try {
                        pfizer.setvBID(cboPfizerBID.getValue());
                        if(pfizer.readVData()[0] != null){
                            LBLPfizerQuantity.setDisable(false);
                            lblPfizerQuantity.setText(pfizer.readVData()[0]);
                            LBLPfizerExpDate.setDisable(false);                           
                            lblPfizerExpDate.setText(pfizer.readVData()[1]);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    public void loadSBID(){
        switch (cboVnSVaccineVenue.getValue()) {
            case "Axiata Arena Bukit Jalil" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Sinovac".compareTo(wordsinLine[1]) == 0) && 
                                    ("Axiata Arena Bukit Jalil".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboSinovacBID.getItems().addAll(VaccineBatch);
                                stat = true; 
                            }
                        }
                    }
                    if (stat == true){
                        SinovacTab.setDisable(false); 
                        LBLSinovacQuantity.setDisable(true);
                        LBLSinovacExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Kuala Lumpur Convention Centre (KLCC)" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Sinovac".compareTo(wordsinLine[1]) == 0) && 
                                    ("Kuala Lumpur Convention Centre (KLCC)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboSinovacBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        } 
                    }
                    if (stat == true){
                        SinovacTab.setDisable(false); 
                        LBLSinovacQuantity.setDisable(true);
                        LBLSinovacExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "University Malaya (UM)" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Sinovac".compareTo(wordsinLine[1]) == 0) && ("University Malaya (UM)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboSinovacBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        }
                    }
                    if (stat == true){
                        SinovacTab.setDisable(false); 
                        LBLSinovacQuantity.setDisable(true);
                        LBLSinovacExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }
    }
    
    public void showSinovac(){
        cboSinovacBID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboSinovacBID.getValue())) {
                    try {
                        sinovac.setvBID(cboSinovacBID.getValue());
                        if(sinovac.readVData()[0] != null){
                            LBLSinovacQuantity.setDisable(false);
                            lblSinovacQuantity.setText(sinovac.readVData()[0]);
                            LBLSinovacExpDate.setDisable(false);                           
                            lblSinovacExpDate.setText(sinovac.readVData()[1]);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    public void loadAZBID(){
        switch (cboVnSVaccineVenue.getValue()) {
            case "Axiata Arena Bukit Jalil" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Astrazeneca".compareTo(wordsinLine[1]) == 0) && 
                                    ("Axiata Arena Bukit Jalil".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboAZBID.getItems().addAll(VaccineBatch);
                                stat = true; 
                            }
                        }
                    }
                    if (stat == true){
                        AZTab.setDisable(false); 
                        LBLAZQuantity.setDisable(true);
                        LBLAZExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Kuala Lumpur Convention Centre (KLCC)" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Astrazeneca".compareTo(wordsinLine[1]) == 0) && 
                                    ("Kuala Lumpur Convention Centre (KLCC)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboAZBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        } 
                    }
                    if (stat == true){
                        AZTab.setDisable(false); 
                        LBLAZQuantity.setDisable(true);
                        LBLAZExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "University Malaya (UM)" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Astrazeneca".compareTo(wordsinLine[1]) == 0) && 
                                    ("University Malaya (UM)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboAZBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        }
                    }
                    if (stat == true){
                        AZTab.setDisable(false); 
                        LBLAZQuantity.setDisable(true);
                        LBLAZExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }
    }
    
    public void showAZ(){
        cboAZBID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboAZBID.getValue())) {
                    az.setvBID(cboAZBID.getValue());
                    try {
                        if(az.readVData()[0] != null){
                            LBLAZQuantity.setDisable(false);
                            lblAZQuantity.setText(az.readVData()[0]);
                            LBLAZExpDate.setDisable(false);                           
                            lblAZExpDate.setText(az.readVData()[1]);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    public void loadBJBID(){
        switch (cboVnSVaccineType.getValue()) {
            case "Astrazeneca" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Astrazeneca".compareTo(wordsinLine[1]) == 0) && ("Axiata Arena Bukit Jalil".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboBJBID.getItems().addAll(VaccineBatch);
                                stat = true; 
                            }
                        }
                    }
                    if (stat == true){
                        BJTab.setDisable(false); 
                        LBLBJQuantity.setDisable(true);
                        LBLBJExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Pfizer" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Pfizer".compareTo(wordsinLine[1]) == 0) && 
                                    ("Axiata Arena Bukit Jalil".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboBJBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        } 
                    }
                    if (stat == true){
                        BJTab.setDisable(false); 
                        LBLBJQuantity.setDisable(true);
                        LBLBJExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Sinovac" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Sinovac".compareTo(wordsinLine[1]) == 0) && 
                                    ("Axiata Arena Bukit Jalil".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboBJBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        }
                    }
                    if (stat == true){
                        BJTab.setDisable(false); 
                        LBLBJQuantity.setDisable(true);
                        LBLBJExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }
    }
    
    public void showBJ(){
        cboBJBID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboBJBID.getValue())) {
                    try {
                        String vbid = cboBJBID.getValue();
                        if(v.readVData(vbid)[0] != null){
                            LBLBJQuantity.setDisable(false);
                            lblBJQuantity.setText(v.readVData(vbid)[0]);
                            LBLBJExpDate.setDisable(false);                           
                            lblBJExpDate.setText(v.readVData(vbid)[1]);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    public void loadKLCCBID(){
        switch (cboVnSVaccineType.getValue()) {
            case "Astrazeneca" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Astrazeneca".compareTo(wordsinLine[1]) == 0) && 
                                    ("Kuala Lumpur Convention Centre (KLCC)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboKLCCBID.getItems().addAll(VaccineBatch);
                                stat = true; 
                            }
                        }
                    }
                    if (stat == true){
                        KLCCTab.setDisable(false); 
                        LBLKLCCQuantity.setDisable(true);
                        LBLKLCCExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Pfizer" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Pfizer".compareTo(wordsinLine[1]) == 0) && 
                                    ("Kuala Lumpur Convention Centre (KLCC)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboKLCCBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        } 
                    }
                    if (stat == true){
                        KLCCTab.setDisable(false); 
                        LBLKLCCQuantity.setDisable(true);
                        LBLKLCCExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Sinovac" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Sinovac".compareTo(wordsinLine[1]) == 0) && 
                                    ("Kuala Lumpur Convention Centre (KLCC)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboKLCCBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        }
                    }
                    if (stat == true){
                        KLCCTab.setDisable(false); 
                        LBLKLCCQuantity.setDisable(true);
                        LBLKLCCExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }
    }
    
    public void showKLCC(){
        cboKLCCBID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboKLCCBID.getValue())) {
                    try {
                        String vbid = cboKLCCBID.getValue();
                        if(v.readVData(vbid)[0] != null){
                            LBLKLCCQuantity.setDisable(false);
                            lblKLCCQuantity.setText(v.readVData(vbid)[0]);
                            LBLKLCCExpDate.setDisable(false);                           
                            lblKLCCExpDate.setText(v.readVData(vbid)[1]);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    public void loadUMBID(){
        switch (cboVnSVaccineType.getValue()) {
            case "Astrazeneca" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Astrazeneca".compareTo(wordsinLine[1]) == 0) && 
                                    ("University Malaya (UM)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboUMBID.getItems().addAll(VaccineBatch);
                                stat = true; 
                            }
                        }
                    }
                    if (stat == true){
                        UMTab.setDisable(false); 
                        LBLUMQuantity.setDisable(true);
                        LBLUMExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Pfizer" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Pfizer".compareTo(wordsinLine[1]) == 0) && 
                                    ("University Malaya (UM)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboUMBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        } 
                    }
                    if (stat == true){
                        UMTab.setDisable(false); 
                        LBLUMQuantity.setDisable(true);
                        LBLUMExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "Sinovac" -> {
                try {
                    String [] wordsinLine = null;
                    boolean stat = false;
                    File vf = new File("Vaccine.txt");
                    Scanner Sc = new Scanner (vf);
                    while (Sc.hasNextLine()) {
                        String Line = Sc.nextLine();
                        if (!(Line.isEmpty())) {
                            wordsinLine = Line.split(";");
                            if(("Sinovac".compareTo(wordsinLine[1]) == 0) && 
                                    ("University Malaya (UM)".compareTo(wordsinLine[2]) == 0)){
                                ObservableList<String> VaccineBatch = FXCollections.observableArrayList(wordsinLine[0]);
                                cboUMBID.getItems().addAll(VaccineBatch);
                                stat = true;
                            }
                        }
                    }
                    if (stat == true){
                        UMTab.setDisable(false); 
                        LBLUMQuantity.setDisable(true);
                        LBLUMExpDate.setDisable(true);
                    }
                    Sc.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }
    }
    
    public void showUM(){
        cboUMBID.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                if (!"".equals(cboUMBID.getValue())) {
                    try {
                        String vbid = cboUMBID.getValue();
                        if(v.readVData(vbid)[0] != null){
                            LBLUMQuantity.setDisable(false);
                            lblUMQuantity.setText(v.readVData(vbid)[0]);
                            LBLUMExpDate.setDisable(false);                           
                            lblUMExpDate.setText(v.readVData(vbid)[1]);
                        }
                    } catch (Exception Ex) {
                        Ex.printStackTrace();
                    }       
                }
            }
        });
    }
    
    public void refresh1(){
        PfizerTab.setDisable(true);
        SinovacTab.setDisable(true);
        AZTab.setDisable(true);
        cboPfizerBID.getItems().clear();
        cboSinovacBID.getItems().clear();
        cboAZBID.getItems().clear();
        lblPfizerQuantity.setText("");
        lblSinovacQuantity.setText("");
        lblAZQuantity.setText("");
        lblPfizerExpDate.setText("YYYY/MM/DD");
        lblSinovacExpDate.setText("YYYY/MM/DD");
        lblAZExpDate.setText("YYYY/MM/DD");
    }
    
    public void refresh2(){
        BJTab.setDisable(true);
        KLCCTab.setDisable(true);
        UMTab.setDisable(true);
        cboBJBID.getItems().clear();
        cboKLCCBID.getItems().clear();
        cboUMBID.getItems().clear();
        lblBJQuantity.setText("");
        lblKLCCQuantity.setText("");
        lblUMQuantity.setText("");
        lblBJExpDate.setText("YYYY/MM/DD");
        lblKLCCExpDate.setText("YYYY/MM/DD");
        lblUMExpDate.setText("YYYY/MM/DD");
    }
  
    public void showPeople(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("People.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("People Interface");
            primaryStage.show();
            PeopleViewandSearchVaccine.getScene().getWindow().hide();
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
                PeopleViewandSearchVaccine.getScene().getWindow().hide();
            }
            catch (Exception e) {
		System.out.println("Can't load new window.");
            }
	} else if (result.get() == btnNo) {
            alert.hide();
	}
    }    
}
