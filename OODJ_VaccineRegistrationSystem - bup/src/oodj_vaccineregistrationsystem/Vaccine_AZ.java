
package oodj_vaccineregistrationsystem;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vaccine_AZ extends Vaccine {
    
    // read AZ data 
    @Override
    public String[] readVData () throws FileNotFoundException{
        String [] wordsinLine = null;
        String [] vData = new String[2];
        Scanner Sc = new Scanner (f);
        while (Sc.hasNextLine()) {
            String Line = Sc.nextLine();
            if (!(Line.isEmpty())) {
        	wordsinLine = Line.split(";"); 
                if((wordsinLine[0].equals(getvBID()))){
                    vData[0] = wordsinLine[3];
                    vData[1] = wordsinLine[4];
                } 
            }
        }
        Sc.close();
        return vData;
    }
}
