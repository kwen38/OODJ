
package covid19system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmergencyNo;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhoneNo;
    
    @FXML
    private TextField txtCountry;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    class register {
        String name, phoneno, ephoneno, address, ID, password, country;
        register(String name, String phoneno, String ephoneno, String address, String ID, String password, String country)
        {
            name = name;
            phoneno = phoneno;
            ephoneno = ephoneno;
            address = address;
            ID = ID;
            password = password;
            country = country;
        }
        
        public String getname()
        {
          return name;
        }
        
        public String getphoneno()
        {
            return phoneno;
        }
        
        public String getephoneno()
        {
            return ephoneno;
        }
        
        public String getaddress()
        {
            return address;
        }
        
        public String getID()
        {
            return ID;
        }
        
        public String getpassword()
        {
            return password;
        }
        
        public String getcountry()
        {
            return country;
        }
                }

    public void register(ActionEvent event){
        if (txtAddress.getText().trim().isBlank()
                || txtEmergencyNo.getText().trim().isBlank()
                || txtID.getText().trim().isBlank()
                || txtName.getText().trim().isBlank()
                || txtPassword.getText().trim().isBlank()
                || txtPhoneNo.getText().trim().isBlank()){
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
	    		alert.setTitle("Blank Value");
	    		alert.setContentText("Every field is required!");
	    		Optional<ButtonType> result = alert.showAndWait();
	    			if(result.get() == ButtonType.OK) {
	    			alert.hide();
                                return;
	    			                               }
        }
        
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();
        String emergencyNo = txtEmergencyNo.getText().trim();
        String ID = txtID.getText().trim();
        String password = txtPassword.getText().trim();
        String phoneNo = txtPhoneNo.getText().trim();
        
        // 12 is IC length  8 is passport length
        if (ID.length()!= 14 && ID.length() != 9){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
	    		alert.setTitle("Wrong format");
	    		alert.setContentText("IC/Passport number is invalid!");
	    		Optional<ButtonType> result = alert.showAndWait();
	    			if(result.get() == ButtonType.OK) {
	    			alert.hide();
                                return;
	    			                               }
        
        }
         if (emergencyNo.length() !=11 || phoneNo.length() !=11){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setHeaderText(null);
	    		alert.setTitle("Wrong format");
	    		alert.setContentText("Phone number is invalid!");
	    		Optional<ButtonType> result = alert.showAndWait();
	    			if(result.get() == ButtonType.OK) {
	    			alert.hide();
                                return;
	    			                               }
                                
         }  
         try{
             File fData = new File ("people.txt");
             
             
             FileWriter fw = new FileWriter(fData, true);
             BufferedWriter bw = new BufferedWriter (fw);
             PrintWriter pw = new PrintWriter(bw);
             String peopleData = txtName.getText().toString()+";"+ txtPhoneNo.getText().toString()+";"+ txtEmergencyNo.getText().toString()+";"+ txtAddress.getText().toString()+";"+txtID.getText().toString()+";"+txtPassword.getText().toString()+";"+txtCountry.getText().toString();
             bw.newLine();
             pw.print(peopleData);
             pw.close();
             
             
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    		alert.setHeaderText(null);
		    		alert.setTitle("Successful Register");
		    		alert.setContentText("Register Successfully!");
		    		Optional<ButtonType> result = alert.showAndWait();
		    			if(result.get() == ButtonType.OK) {
		    			alert.hide();
                                        return;
		    			}
         } catch (Exception e){
             
         }
    }
     public void registerback(ActionEvent event)throws Exception{
         Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Login");
        primaryStage.show();
     }
}
