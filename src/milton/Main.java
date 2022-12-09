package milton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

//java -cp classes milton.Main paradise_lost.txt
public class Main {
    public static void main(String[] args){
        //read file name
        String fileName = args[0];
        System.out.printf("Processing %s\n", fileName);

        try{
            File fileObj = Paths.get("src/milton/" + fileName).toFile();
            FileReader fileReader = new FileReader(fileObj);
            BufferedReader bdf = new BufferedReader(fileReader);

            //read the lines up to line 20 
            String line;
            Integer lineCount = 0;
            while ((line = bdf.readLine()) != null &&  lineCount < 20 ){
                System.out.println("> " + line);
                lineCount++;
            }
            bdf.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
