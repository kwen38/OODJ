
package covid19system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;


public class Profile_EditController implements Initializable {

    @FXML
    private AnchorPane Profile_Edit;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

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
    private TextField txtID;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhoneno;

  
    Alert alert = new Alert(Alert.AlertType.WARNING);

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void search () throws Exception {
         String [] wordsinLine = null;
         boolean stat = false;
         String StaffID = txtID.getText().toString();
         
         File sData = new File ("people.txt");
         Scanner Sc = new Scanner (sData);
         while (Sc.hasNextLine()){
             String Line = Sc.nextLine();
             if (!(Line.isEmpty())) {
                 wordsinLine = Line.split(";");
                 if ((StaffID.compareTo(wordsinLine[4]) == 0)){
                 stat = true;
                 break;
             }
             }
         }
         
         if (stat == true ){       
          lblName.setText(wordsinLine[0]);
          txtPhoneno.setText(wordsinLine[1]);
         txtENo.setText(wordsinLine[2]);
         txtCountry.setText(wordsinLine[6]);
         txtAddress.setText(wordsinLine[3]);
         txtPassword.setText(wordsinLine[5]);
         return;
         }
         else    
            
        		alert.setHeaderText(null);
        		alert.setTitle("Record not found");
        		alert.setContentText("Record not found!");
        		alert.show();
    }
    
    public void editprofileback ()throws Exception{
        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Login");
        primaryStage.show();
        Profile_Edit.getScene().getWindow().hide();
    }
    
        
       
        

    

                
    public void editrecord() throws Exception{
        String IC = txtID.getText();
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
        
//        try{
//            String ID = txtID.getText().toString();
//            Path p = Paths.get(".", "people.txt");
//            Path tempFile = Files.createTempFile(p.getParent(),"guestTemp","txt");
//            try(BufferedReader reader = Files.newBufferedReader(p);
//                    BufferedWriter writer = Files.newBufferedWriter(tempFile)){
//                String line;
//                
//                while ((line = reader.readLine()) != null) {
//                String[] fields = line.split(";");
//                if (ID.equals(fields[4])){
//                    for (int i = 0; i<fields.length; ++i){
//                        System.out.println(i+ ":" + fields[i]);
//                    }
//                    fields[0]=lblName.getText().toString();
//                    fields[1]=txtPhoneno.getText().toString();
//                    fields[2]=txtENo.getText().toString();
//                    fields[3]=txtAddress.getText().toString();
//                    fields[5]=txtPassword.getText().toString();
//                    fields[6]=txtCountry.getText().toString();
//                }
//                writer.write(String.join(";", fields));
//                writer.newLine();
//            }
//            }
//            Files.copy(tempFile, p, StandardCopyOption.REPLACE_EXISTING);
//            Files.delete(tempFile);
//            Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setHeaderText(null);
//			alert.setTitle("Updated Successfully");
//			alert.setContentText("Data has been updated successfully!");
//			Optional<ButtonType> result = alert.showAndWait();
//				if(result.get() == ButtonType.OK) {
//				alert.hide();
//				}
//                   
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
    }
    
    
     
}

