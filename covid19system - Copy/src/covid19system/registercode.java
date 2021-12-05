
package covid19system;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class registercode extends SaveLoad  {
    private String name, phoneNo, emergencyNo, address, ID, password;
    
    static {
    storageDirectory = "./peopleinfo";
            }
    
     public registercode (String name, String phoneNo, String emergencyNo, String address, String ID, String password ){
        this.name = name;
        this.ID = ID;
        this.emergencyNo = emergencyNo;
        this.password = password;
        this.phoneNo = phoneNo;
        this.address = address;
        
        
               
    }
     public registercode (String filename){
        try {
            Load (storageDirectory + "/" + filename);
            
        }catch(Exception e){
            System.out.println("Failed to load data from file");
            
        }
    }
	public void Load(String filepath) throws IOException {
            File file = new File (filepath);
            
            Scanner inputFile = new Scanner (file);
            while (inputFile.hasNext()){
                this.name = inputFile.nextLine();
                this.ID = inputFile.nextLine();
                this.emergencyNo = inputFile.nextLine();
                this.password = inputFile.nextLine();
                this.phoneNo = inputFile.nextLine();
                this.address = inputFile.nextLine();
            }
            inputFile.close();
        }
        
        public void showData() {
            System.out.println("name:" + name);
            System.out.println("ID:" + ID);
            System.out.println("emergencyNo:" + emergencyNo);
            System.out.println("password:" + password);
            System.out.println("phoneNo:" + phoneNo);
            System.out.println("address:" + address);
        }
        
        @Override 
        public void Save() throws IOException {
            //the filename will be ID number 
        String filepath = storageDirectory + "/" +  this.ID;
        FileWriter fw = new FileWriter(filepath, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(this.name);
        bw.newLine();
        bw.write(this.ID);
        bw.newLine();
        bw.write(this.emergencyNo);
        bw.newLine();
        bw.write(this.password);
        bw.newLine();
        bw.write(this.phoneNo);
        bw.newLine();
        bw.write(this.address);
        bw.newLine();
        
        }
        
        private static void CheckDir(){
        File bookingDirectory = new File (storageDirectory);
        if (!bookingDirectory.exists()){
            bookingDirectory.mkdirs();
        }
        }
            
        public String getname(){
            return name;
        }   
        public void setName (String name){
            this.name = name;           
        } 
        
        public String ID(){
            return ID;          
        }
        public void setID (String ID){
            this.ID = ID;
        }
        public String emergencyNo(){
            return emergencyNo;
        }
        public void setemergencyNo (String emergencyNo){
            this.emergencyNo = emergencyNo;
        }
        
        public String getpassword(){
            return password;            
        }        
        public void setpassword(){
            this.password = password;
        }
        
        public String getphoneNo(){
            return phoneNo;            
        }        
        public void setphoneNo(){
            this.phoneNo = phoneNo;
        }
        
        public String getaddress(){
            return address;            
        }        
        public void setaddress(){
            this.address = address;
        }
        public static void main(String[] args) {
		launch(args);
	}
        
        public static String getStorageDirectory(){
            CheckDir();
            
            return storageDirectory;
        }
    }

