
package oodj_vaccineregistrationsystem;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Scanner;
import javafx.beans.property.*;

public class Vaccine {
    public String vBID, vType, vVenue;
    public int vQuan;
    public LocalDate vExpDate;
    public File f = new File ("Vaccine.txt");
    
    // variables and methods to create table
    private StringProperty VBID = new SimpleStringProperty();
    private StringProperty VType = new SimpleStringProperty();
    private StringProperty VVenue = new SimpleStringProperty();
    private StringProperty VQuan = new SimpleStringProperty();
    private StringProperty VExpDate = new SimpleStringProperty();
    
    public StringProperty idProperty(){
	return this.VBID;
    }
    public StringProperty typeProperty(){
	return this.VType;
    }
    public StringProperty venueProperty(){
        return this.VVenue;
    }
    public StringProperty quanProperty(){
	return this.VQuan;
    }
    public StringProperty dateProperty(){
        return this.VExpDate;
    }
	
    public String getvBID(){
        return this.vBID;
    }
    
    void setvBID (String vbid){
        this.vBID = vbid;
        // convert string to stringproperty
        this.idProperty().set(vbid);
    }
    
    public String getvType(){
        return this.vType;
    }
    
    void setvType (String vtype){
        this.vType = vtype;
        this.typeProperty().set(vtype);
    }
    
    public String getvVenue(){
        return this.vVenue;
    }
    
    void setvVenue (String vvenue){
        this.vVenue = vvenue;
        this.venueProperty().set(vvenue);
    }
    
    public int getvQuan(){
        return this.vQuan;
    }
    
    void setvQuan (int vquan){
        this.vQuan = vquan;
        this.quanProperty().set(String.valueOf(vquan));
    }
    
    
    public LocalDate getvExpDate(){
        return this.vExpDate;
    }
    
    void setvExpDate (LocalDate vexpdate){
        this.vExpDate = vexpdate;
        this.dateProperty().set(String.valueOf(vexpdate));
    }
   
        // Write to Vaccine file
    public void writeVData() throws IOException{
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter (f, true)));
        String vaccineData = getvBID() + ";" + getvType() + ";" + getvVenue() + ";" + getvQuan() + ";" +
            getvExpDate() + ";";
        pw.println(vaccineData);
        pw.close();  
    }
    
    // read data from Vaccine file
    public String[] readVData () throws FileNotFoundException{
        String [] wordsinLine = null;
        String [] vData = new String[5];
        Scanner Sc = new Scanner (f);
        while (Sc.hasNextLine()) {
            String Line = Sc.nextLine();
            if (!(Line.isEmpty())) {
        	wordsinLine = Line.split(";"); 
                if((wordsinLine[1].equals(getvType())) && (wordsinLine[2].equals(getvVenue()))){
                    vData[0] = wordsinLine[0];
                    vData[1] = wordsinLine[1];
                    vData[2]= wordsinLine[2];
                    vData[3] = wordsinLine[3];
                    vData[4] = wordsinLine[4];
                } 
            }
        }
        Sc.close();
        return vData;
    }
    
    public String[] readVData (String vbid) throws FileNotFoundException{
        String [] wordsinLine = null;
        String [] vData = new String[2];
        Scanner Sc = new Scanner (f);
        while (Sc.hasNextLine()) {
            String Line = Sc.nextLine();
            if (!(Line.isEmpty())) {
        	wordsinLine = Line.split(";"); 
                if((wordsinLine[0].equals(vbid))){
                    vData[0] = wordsinLine[3];
                    vData[1] = wordsinLine[4];
                } 
            }
        }
        Sc.close();
        return vData;
    }
    
    // update data to Vaccine file
    public void updateVData(String bid, String vquan, String vexpdate) throws IOException{
	Path p = Paths.get(".", "Vaccine.txt");
	Path tempFile = Files.createTempFile(p.getParent(), "vaccineTemp", ".txt");
	try (BufferedReader reader = Files.newBufferedReader(p);
            BufferedWriter writer = Files.newBufferedWriter(tempFile)) 
        {
            String line;
            // copy everything to the temp file until the intended id is found
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields[0].equals(bid)) {
                    for (int i = 0; i < fields.length; ++i) {
		        System.out.println(i + ": " + fields[i]);
		    }
                    fields[3] = vquan;
                    fields[4] = vexpdate;
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
    
    // remove data from Vaccine file
    public void removeVData(){
	String aFile = "Vaccine.txt";
	File oldFile = new File ("Vaccine.txt");
	String tempFile = "tempVaccine.txt";
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
		    if (!(wordsinLine[0].equals(getvBID()))) {
		        pw.print(wordsinLine[0] + ";");
		        pw.print(wordsinLine[1] + ";");
		        pw.print(wordsinLine[2]+ ";");
		        pw.print(wordsinLine[3]+ ";");
		        pw.println(wordsinLine[4]+ ";");
		    }
		}
            }
            Sc.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File vSpare = new File (aFile);
            newFile.renameTo(vSpare);
        } catch (Exception e){
            e.printStackTrace();
        }		            	
    }
}
