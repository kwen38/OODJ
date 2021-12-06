
package covid19system;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginPersonnelController implements Initializable {

       @FXML
    private Button LoginPersonnel;

    @FXML
    private AnchorPane Login_Personnel;

    @FXML
    private Button btnSignup;

    @FXML
    private TextField txtID;

    @FXML
    private PasswordField txtPassword;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void LoginPersonnel(ActionEvent event) throws Exception {
    	 if (txtID.getText().equals("admin") && String.valueOf(txtPassword.getText()).equals("admin")) {
            
            Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("Profile_Edit.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Profile_Edit");
				primaryStage.show();
                                LoginPersonnel.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
        		alert.setHeaderText(null);
        		alert.setTitle("Null Value");
        		alert.setContentText("Wrong username/password, please try again");
        		alert.show();
        }
    	}
     public void showPeoplePage (ActionEvent event)throws Exception{    
        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Login");
        primaryStage.show();
        Login_Personnel.getScene().getWindow().hide();
       
    }
     public void showRegistrationPage (ActionEvent event)throws Exception{
        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Register");
        primaryStage.show();
        Login_Personnel.getScene().getWindow().hide();
        
    }
}
