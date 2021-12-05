
package oodj_vaccineregistrationsystem;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Vaccine_Pfizer extends Vaccine {
    // read Pfizer data 
    public String[] readVData () throws FileNotFoundException{
        String [] wordsinLine = null;
        String [] vData = new String[5];
        Scanner Sc = new Scanner (f);
        while (Sc.hasNextLine()) {
            String Line = Sc.nextLine();
            if (!(Line.isEmpty())) {
        	wordsinLine = Line.split(";"); 
                if((wordsinLine[1].equals("Pfizer"))){
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
}
