
package oodj_vaccineregistrationsystem;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class Appointment {
    private String aID, aIC, aDate, aTime, aType, aVenue, aVaccine, aVBID;
    private File f = new File ("Appointment.txt");
    // variables and methods to create table
    private StringProperty AID = new SimpleStringProperty();
    private StringProperty AName = new SimpleStringProperty();
    private StringProperty AIC = new SimpleStringProperty();
    private StringProperty ADate = new SimpleStringProperty();
    private StringProperty ATime = new SimpleStringProperty();
    private StringProperty AType = new SimpleStringProperty();
    private StringProperty AVenue = new SimpleStringProperty();
    private StringProperty AVaccine = new SimpleStringProperty();
    private StringProperty AVBID = new SimpleStringProperty();
    
    // create an object to access vaccine data
    Vaccine v = new Vaccine();
    
    public StringProperty idProperty(){
	return this.AID;
    }
    public StringProperty nameProperty(){
	return this.AName;
    }
    public StringProperty icProperty(){
        return this.AIC;
    }
    public StringProperty dateProperty(){
	return this.ADate;
    }
    public StringProperty timeProperty(){
        return this.ATime;
    }
    public StringProperty typeProperty(){
	return this.AType;
    }
    public StringProperty venueProperty(){
	return this.AVenue;
    }
    public StringProperty vaccineProperty(){
	return this.AVaccine;
    }
    public StringProperty vbidProperty(){
	return this.AVBID;
    }
	
    
    public String getaID(){
        return this.aID;
    }
    
    void setaID (String aid){
        this.aID = aid;
        // convert string to stringproperty
        this.idProperty().set(aid);
    }
    
    public String getaIC(){
        return this.aIC;
    }
    
    void setaIC (String aic){
        this.aIC = aic;
        this.icProperty().set(aic);
    }
    
    public String getaDate(){
        return this.aDate;
    }
    
    void setaDate (String adate){
        this.aDate = adate;
        this.dateProperty().set(adate);
    }
    
    public String getaTime(){
        return this.aTime;
    }
    
    void setaTime (String atime){
        this.aTime = atime;
        this.timeProperty().set(atime);
    }
    
    
    public String getaType(){
        return this.aType;
    }
    
    void setaType (String atype){
        this.aType = atype;
        this.typeProperty().set(atype);
    }
    
    public String getaVenue(){
        return this.aVenue;
    }
    
    void setaVenue (String avenue){
        this.aVenue = avenue;
        this.venueProperty().set(avenue);
    }
    
    public String getaVaccine(){
        return this.aVaccine;
    }
    
    void setaVaccine(String avaccine){
        this.aVaccine = avaccine;
        this.vaccineProperty().set(avaccine);
    }
    
    public String getaVBID(){
        return this.aVBID;
    }
    
    void setaVBID(String avbid){
        this.aVBID = avbid;
        this.vbidProperty().set(avbid);
    }
    
    // Write to Appointment file
    public void writeAData() throws IOException{
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter (f, true)));
        String appointmentData = getaID() + ";" + getaIC() + ";" + getaDate() + ";" + getaTime() + ";" +
            getaType() + ";" + getaVenue() + ";" + getaVaccine() + ";" + getaVBID() + ";";
        pw.println(appointmentData);
        pw.close(); 
    }
    
    // Retrive data from Appointment file
    /* 
    wordsinLine[0] = ID, wordsinLine[1] = IC, wordsinLine[2] = Date, wordsinLine[3] = Time, wordsinLine[4] = Type
    wordsinLine[5] = Venue
    */
    public String[] readAData () throws FileNotFoundException{
        String [] wordsinLine = null;
        String [] aData = new String[8];
        Scanner Sc = new Scanner (f);
        while (Sc.hasNextLine()) {
            String Line = Sc.nextLine();
            if (!(Line.isEmpty())) {
        	wordsinLine = Line.split(";"); 
                if((wordsinLine[0].equals(getaID())) || (wordsinLine[1].equals(getaIC()))){
                    aData[0] = wordsinLine[0];
                    aData[1] = wordsinLine[1];
                    aData[2]= wordsinLine[2];
                    aData[3] = wordsinLine[3];
                    aData[4] = wordsinLine[4];
                    aData[5] = wordsinLine[5];
                    aData[6] = wordsinLine[6];
                    aData[7] = wordsinLine[7];
                } 
            }
        }
        Sc.close();
        return aData;
    }
    
    // update data to Appointment file
    public void updateAData(String aid, String aic, String adate, String atime) throws IOException{
	Path p = Paths.get(".", "Appointment.txt");
	Path tempFile = Files.createTempFile(p.getParent(), "appointmentTemp", ".txt");
	try (BufferedReader reader = Files.newBufferedReader(p);
            BufferedWriter writer = Files.newBufferedWriter(tempFile)) 
        {
            String line;
            // copy everything to the temp file until the intended id/ic are found
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields[0].equals(aid) || fields[1].equals(aic)) {
                    for (int i = 0; i < fields.length; ++i) {
		        System.out.println(i + ": " + fields[i]);
		    }
                    fields[2] = adate;
                    fields[3] = atime;
		}
		writer.write(String.join(";", fields));
		writer.write(";\n");
            }
            reader.close();
            writer.close();
            // copy new file & delete temporary file
            Files.copy(tempFile, p, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(tempFile);
	} catch (IOException ex) {
            ex.printStackTrace();
	}
    }
    
    // Remove data from Appointment file
    public void removeAData(){
	String aFile = "Appointment.txt";
	File oldFile = new File ("Appointment.txt");
	String tempFile = "tempAppointment.txt";
	File newFile = new File(tempFile);
	String [] wordsinLine = null;
	try {
            PrintWriter pw = new PrintWriter (new BufferedWriter(new FileWriter (tempFile, true)));
            Scanner Sc = new Scanner(oldFile);
		                
            while (Sc.hasNextLine()) {
		String Line = Sc.nextLine();
		if (!(Line.isEmpty())) {
		    wordsinLine = Line.split(";"); 
		    //compare data with user input
		    if (!(wordsinLine[0].equals(getaID()))) {
		        pw.print(wordsinLine[0] + ";");
		        pw.print(wordsinLine[1] + ";");
		        pw.print(wordsinLine[2]+ ";");
		        pw.print(wordsinLine[3]+ ";");
		        pw.print(wordsinLine[4]+ ";");
		        pw.print(wordsinLine[5]+ ";");
                        pw.print(wordsinLine[6]+ ";");
                        pw.println(wordsinLine[7]+ ";");
		    }
		}
            }
            Sc.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File aSpare = new File (aFile);
            newFile.renameTo(aSpare);
        } catch (Exception e){
            e.printStackTrace();
        }		            	
    }
    
    //deduct quantity in Vaccine file once appointment is booked
    public void deductVQuan(String vbid) throws IOException{
        Path p = Paths.get(".", "Vaccine.txt");
	Path tempFile = Files.createTempFile(p.getParent(), "vaccineTemp", ".txt");
	try (BufferedReader reader = Files.newBufferedReader(p);
            BufferedWriter writer = Files.newBufferedWriter(tempFile)) 
        {
            String line;
            // copy everything to the temp file until the intended id/ic are found
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields[0].equals(vbid)) {
                    for (int i = 0; i < fields.length; ++i) {
		        System.out.println(i + ": " + fields[i]);
		    }
                    int quan = Integer.parseInt(fields[3]) - 1;
                    fields[3] = String.valueOf(quan);
		}
		writer.write(String.join(";", fields));
		writer.write(";\n");
            }
            reader.close();
            writer.close();
            // copy new file & delete temporary file
            Files.copy(tempFile, p, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(tempFile);
        }
    }
    
    // increase vaccine quantity once appointment is cancelled
    public void increaseVQuan(String vbid) throws IOException{
        Path p = Paths.get(".", "Vaccine.txt");
	Path tempFile = Files.createTempFile(p.getParent(), "vaccineTemp", ".txt");
	try (BufferedReader reader = Files.newBufferedReader(p);
            BufferedWriter writer = Files.newBufferedWriter(tempFile)) 
        {
            String line;
            // copy everything to the temp file until the intended id/ic are found
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields[0].equals(vbid)) {
                    for (int i = 0; i < fields.length; ++i) {
		        System.out.println(i + ": " + fields[i]);
		    }
                    int quan = Integer.parseInt(fields[3]) + 1;
                    fields[3] = String.valueOf(quan);
		}
		writer.write(String.join(";", fields));
		writer.write(";\n");
            }
            reader.close();
            writer.close();
            // copy new file & delete temporary file
            Files.copy(tempFile, p, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(tempFile);
        }
    }
    
    
}
