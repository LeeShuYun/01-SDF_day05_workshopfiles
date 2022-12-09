package milton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

//java -cp classes milton.Main paradise_lost.txt
public class Main {
    public static void main(String[] args) throws Exception{
        //read file name
        String fileName = args[0];
        System.out.printf("Processing %s\n", fileName);

        // File fileObj = Paths.get("src/milton/" + fileName).toFile();
        // FileReader fileReader = new FileReader(fileObj);
        String line;
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bdf = new BufferedReader(fileReader);
                
        Integer lineCount = 0;
        while(lineCount < 100){
            line = bdf.readLine();
            if (null == line)
                break;
            System.out.printf(" %d: %s\n", lineCount, line.toUpperCase());
            lineCount++;
        }
        
        //read the lines up to line 20 

        // while ((line = bdf.readLine()) != null &&  lineCount < 20 ){
        //     System.out.println("> " + line);
        //     lineCount++;
        // }
        bdf.close();

    }
}
