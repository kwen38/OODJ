/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19system;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;

    @FXML
    private TextField txtID;

    @FXML
    private PasswordField txtPassword;

    @FXML 
    private AnchorPane Login;
    @FXML 
    private AnchorPane Login_Personnel;
    @FXML 
    private AnchorPane Register;
    
      
    
    Alert alert = new Alert(Alert.AlertType.WARNING);
    
//    public class login (String ID){
//        this.txtID = ID;
//    }
//    public String getID() {
//        return ID;
//    }
//
//    public void setID(String ID) {
//        this.txtID = ID;
//    }
            
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        
        
    }    
   /* private String StaffID = txtID.getText().toString();
    public String getStaffID(){
        
        return StaffID;
    }*/
    
  
  
    
    
     public void LoginPeople(ActionEvent event) throws Exception {         
    	 String [] wordsinLine = null;
         boolean stat = false;
         String StaffID = txtID.getText().toString();
         String StaffPassword = txtPassword.getText().toString();        
         
         File sData = new File ("people.txt");
         Scanner Sc = new Scanner (sData);
         while (Sc.hasNextLine()){
             String Line = Sc.nextLine();
             if (!(Line.isEmpty())) {
                 wordsinLine = Line.split(";");
                 if ((StaffID.compareTo(wordsinLine[4]) == 0) && (StaffPassword.compareTo(wordsinLine[5]) == 0)){
                 stat = true;
                 break;
             }
             }
         }
         if (stat == true ){       
                
         
//        Stage primaryStage = new Stage ();         
//        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));        
//        Scene scene = new Scene (root);
//        primaryStage.setScene (scene);
//        primaryStage.setTitle ("Profile");
//        primaryStage.show();
//        Login.getScene().getWindow().hide();
        
        Stage primaryStage = new Stage ();  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));   
        Parent root = (Parent) loader.load();
        ProfileController procontroller= loader.getController();
        procontroller.myftn(txtID.getText());      
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Profile");
        primaryStage.show();
        Login.getScene().getWindow().hide();
        
        return;
      
         }
         else 
            
        		alert.setHeaderText(null);
        		alert.setTitle("Null Value");
        		alert.setContentText("Wrong username/password, please try again");
        		alert.show();
                        
             }
     @FXML
     public void showRegistrationPage (ActionEvent event)throws Exception{
        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Register");
        primaryStage.show();
        Login.getScene().getWindow().hide();
        
    }
     
  
     @FXML
    public void showPersonnelPage (ActionEvent event) throws Exception{
        
        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginPersonnel.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("LoginPersonnel");
        primaryStage.show();
        Login.getScene().getWindow().hide();

        }
    
    }
   
        
    

