
package covid19system;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class VaccinationCertController implements Initializable {

     @FXML
    private Label lblD1BID;

    @FXML
    private Label lblD1VT;

    @FXML
    private Label lblD1date;

    @FXML
    private Label lblD1time;

    @FXML
    private Label lblD1venue;

    @FXML
    private Label lblD2BID;

    @FXML
    private Label lblD2VT;

    @FXML
    private Label lblD2date;

    @FXML
    private Label lblD2time;

    @FXML
    private Label lblD2venue;

    @FXML
    private Label lblID;
    
    @FXML
    private Label seconddose;
    
    @FXML
    private Label firstdose;
    
    @FXML
    private AnchorPane Cert;
        @FXML
    private Label lblName;
    
    public void getdata (String text){
        lblID.setText(text);
        getD1data();
        getD2data();
        getname();

    }
//    public void getID(String ID) {
//        lblID.setText(ID);
//        
//        
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        lblID.setText ("011121-08-1112");
        
//        getD1data();
//        getD2data();
//        getname();

    }    
    // get Dose 1 data
    public void getD1data(){
        String [] wordsinLine = null;
        boolean stat = false;
        String ID = lblID.getText();
        String dose = firstdose.getText();
        try{
            File A = new File("Appointment.txt");
            Scanner Sc = new Scanner(A);
            while(Sc.hasNextLine()){
                String Line = Sc.nextLine();
                if (!(Line.isEmpty())){
                    wordsinLine = Line.split(";");
                    if ((ID.compareTo(wordsinLine[1])== 0) && (dose.compareTo(wordsinLine[4])== 0))
                    {
                        stat = true;
                    }
                    if (stat == true){
                        lblD1date.setText(wordsinLine[2]);
                        lblD1time.setText(wordsinLine[3]);
                        lblD1venue.setText(wordsinLine[5]);
                        lblD1VT.setText(wordsinLine[6]);
                        lblD1BID.setText(wordsinLine[7]);
                    }
                }
            }
        }catch (Exception e){
            
        }
    }
    // get Dose 2 data
    public void getD2data(){
        String [] wordsinLine = null;
        boolean stat = false;
        String ID = lblID.getText();
        String dose = seconddose.getText();
        try{
            File A = new File("Appointment.txt");
            Scanner Sc = new Scanner(A);
            while(Sc.hasNextLine()){
                String Line = Sc.nextLine();
                if (!(Line.isEmpty())){
                    wordsinLine = Line.split(";");
                    if ((ID.compareTo(wordsinLine[1])== 0) && (dose.compareTo(wordsinLine[4])== 0))
                    {
                        stat = true;
                    }
                    if (stat == true){
                        lblD2date.setText(wordsinLine[2]);
                        lblD2time.setText(wordsinLine[3]);
                        lblD2venue.setText(wordsinLine[5]);
                        lblD2VT.setText(wordsinLine[6]);
                        lblD2BID.setText(wordsinLine[7]);
                    }
                }
            }
        }catch (Exception e){
            
        }
    }
    
    public void getname(){
        String [] wordsinLine = null;
        boolean stat = false;
        String ID = lblID.getText();
        
        try{
            File p = new File("people.txt");
            Scanner Sc = new Scanner(p);
            while(Sc.hasNextLine()){
                String Line = Sc.nextLine();
                if (!(Line.isEmpty())){
                    wordsinLine = Line.split(";");
                    if ((ID.compareTo(wordsinLine[4])== 0))
                    {
                        stat = true;
                    }
                    if (stat == true){
                        lblName.setText(wordsinLine[0]);
                        
                    }
                }
            }
        }catch (Exception e){
            
        }
    }
}
