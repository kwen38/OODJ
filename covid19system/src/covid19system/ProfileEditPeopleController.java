
package covid19system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

 


public class ProfileEditPeopleController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblID;

    @FXML
    private Label lblName;

    @FXML
    private Label lblvaccinationstatus;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtENo;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhoneno;
    
     @FXML
    private AnchorPane ProfileEditPeople;
     Alert alert = new Alert(Alert.AlertType.WARNING);
    
     public void getdata (String text){
         lblID.setText(text);
         getdata1();
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    }    
    
    public void getdata1 (){
         String [] wordsinLine = null;
         boolean stat = false;
         String ID = lblID.getText();
         try{
             File SD = new File("people.txt");
             Scanner Sc = new Scanner(SD);
             while(Sc.hasNextLine()){
                 String Line = Sc.nextLine();
                 if (!(Line.isEmpty())){
                     wordsinLine = Line.split(";");
                     if (ID.compareTo(wordsinLine[4])== 0 ){
                         
                         stat = true;
                     }
                     if (stat == true){
                         lblName.setText(wordsinLine[0]);
                         txtPhoneno.setText(wordsinLine[1]);
                         txtENo.setText(wordsinLine[2]);
                         txtCountry.setText(wordsinLine[6]);
                         txtAddress.setText(wordsinLine[3]);
                         txtPassword.setText(wordsinLine[5]);
                     }
                 }
             }
         }catch(Exception e){
             
         }
    }
    
    public void back ()throws Exception{
        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Login");
        primaryStage.show();
        ProfileEditPeople.getScene().getWindow().hide();
    }
    
    public void save() throws Exception{
        String IC = lblID.getText();
        String phoneno = txtPhoneno.getText();
        String Ephoneno = txtENo.getText();
        String country = txtCountry.getText();
        String address = txtAddress.getText();
        String password = txtPassword.getText();
       
        ArrayList<String> tempArray = new ArrayList<>();
        File file = new File ("people.txt");
        try {
            String line; String [] lineArray = null;
                       
            try { 
           BufferedReader br = new BufferedReader (new FileReader(file));{
            
              while((line = br.readLine()) != null) {
                    lineArray = line.split(";");
                    if(lineArray[4].equals(IC)) {
                        tempArray.add(lineArray[0]+";"+ phoneno + ";" + Ephoneno + ";" + address+ ";" + lineArray[4] +";" + password + ";" + country);
                                alert.setHeaderText(null);
        		alert.setTitle("Successfully Updated");
        		alert.setContentText("Updated Successfully!");
        		alert.show();
                    }else{
                        tempArray.add(line);
                    }
                }
                br.close();
            
                
            }
        }catch (FileNotFoundException ex){
            
        }
        } catch (Exception ex) {
            
        }
              
         try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for(int k = 0; k < tempArray.size(); k ++) {
                    bw.write(tempArray.get(k));
                    bw.newLine();
                }
            bw.close();
            }
        }catch (IOException ex) {
            
        }
    }
}
