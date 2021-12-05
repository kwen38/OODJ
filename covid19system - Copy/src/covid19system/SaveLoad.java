
package covid19system;

import java.io.IOException;

public class SaveLoad {
    protected static String storageDirectory = "./save";
    
    public void Save () throws IOException{
        System.out.println("Saved");
    }
    
    public void Load (String filepath) throws IOException {
        System.out.println("Loaded from:" + filepath);
    }
}
