
package covid19system;


import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ProfileController implements Initializable {

    
     @FXML
    private AnchorPane Profile;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEdit;

    @FXML
    private Label lblID;

    @FXML
    private Label lblName;

    @FXML
    private Label lbladdress;

    @FXML
    private Label lblcountry;

    @FXML
    private Label lblemergencyno;

    @FXML
    private Label lblphoneno;
       
    @FXML
    private Button btnGenerate;
    
    Alert alert = new Alert(Alert.AlertType.WARNING);
    
     public void myftn(String text) {
        lblID.setText(text);
        getdata();
        return;
    }
     
//    ProfileController p = new ProfileController();
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
//        lblID.setText("03928392");
        
//        lblID.setText(p.getID());
        
            getdata();
        
        }    
   
    
    public void getdata() {
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
                         lblName.setText(wordsinLine[0]);
                         lblphoneno.setText(wordsinLine[1]);
                         lblemergencyno.setText(wordsinLine[2]);
                         lblcountry.setText(wordsinLine[3]);
                         lbladdress.setText(wordsinLine[6]);
                         
                         stat = true;
                     }
                     if (stat == true){
                         
                     }
                 }
             }
         }catch(Exception e){
             
         }
                        

    }
    
     public void profileback(ActionEvent event)throws Exception{
         Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Login");
        primaryStage.show();
        Profile.getScene().getWindow().hide();
     }
     
     public void generatecert(ActionEvent event)throws Exception{
        Stage primaryStage = new Stage ();  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VaccinationCert.fxml"));   
        Parent root = (Parent) loader.load();
        ProfileController procontroller= loader.getController();
        procontroller.myftn(lblID.getText());      
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Vaccination Certificate");
        primaryStage.show();
        Profile.getScene().getWindow().hide();
        
//         Stage primaryStage = new Stage ();
//        Parent root = FXMLLoader.load(getClass().getResource("VaccinationCert.fxml"));
//        Scene scene = new Scene (root);
//        primaryStage.setScene (scene);
//        primaryStage.setTitle ("Vaccination Certificate");
//        primaryStage.show();
//        Profile.getScene().getWindow().hide();
     }
    
     @FXML
    public void editpage(ActionEvent event) throws Exception{
 Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Profile_Edit.fxml"));
        Scene scene = new Scene (root);
        primaryStage.setScene (scene);
        primaryStage.setTitle ("Edit Profile");
        primaryStage.show();
        Profile.getScene().getWindow().hide();
    }
//      public void editpage (ActionEvent event)throws Exception{
//         Stage primaryStage = new Stage ();
//        Parent root = FXMLLoader.load(getClass().getResource("Profile_Edit.fxml"));
//        Scene scene = new Scene (root);
//        primaryStage.setScene (scene);
//       primaryStage.setTitle ("Edit Profile");
//        primaryStage.show();
//        
//        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Login/Profile.fxml"));   
//         Parent root = (Parent) loader.load();                 
//         Stage stage = new Stage();
//         stage.setScene(new Scene(root));
//         stage.show();*/
//     }
}
