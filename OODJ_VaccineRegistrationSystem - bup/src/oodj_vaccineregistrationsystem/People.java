
package oodj_vaccineregistrationsystem;

public class People {
    private String name,phoneno,ephoneno,address,ic,password,country;
    
    public String getName(){
        return this.name;
    }
    
    void setName (String name){
        this.name = name;
    }
    
    public String getPhoneNo(){
        return this.phoneno;
    }
    
    void setPhoneNo (String phoneno){
        this.phoneno = phoneno;
    }
    
    public String getEPhoneNo(){
        return this.ephoneno;
    }
    
    void setEPhoneNo(String ephoneno){
        this.ephoneno = ephoneno;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    void setAdress (String address){
        this.address = address;
    }
    
    
    public String getIC(){
        return this.ic;
    }
    
    void setIC (String ic){
        this.ic = ic;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    void setPassword (String password){
        this.password = password;
    }
    
    public String getCountry(){
        return this.country;
    }
    
    void setCountry(String country){
        this.country = country;
    }
    
//    public String [] readPData(){
//        String [] wordsinLine = null;
//        String [] pData = new String[2];
//        Scanner Sc = new Scanner (f);
//        while (Sc.hasNextLine()) {
//            String Line = Sc.nextLine();
//            if (!(Line.isEmpty())) {
//        	wordsinLine = Line.split(";"); 
//                if((wordsinLine[0].equals(getaID())) || (wordsinLine[1].equals(getaIC()))){
//                    aData[0] = wordsinLine[0];
//                    aData[1] = wordsinLine[1];
//                    aData[2] = wordsinLine[2];
//                    aData[3] = wordsinLine[3];
//                    aData[4] = wordsinLine[4];
//                    aData[5] = wordsinLine[5];
//                    aData[6] = wordsinLine[6];
//                    aData[7] = wordsinLine[7];
//                } 
//            }
//        }
//        Sc.close();
//        return aData;
//    }
    
    
}
