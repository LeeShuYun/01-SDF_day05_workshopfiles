package milton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class BookSort {
    public static void main (String[] args) throws Exception{

        //read file name from the console.
        // java -cp milton.Main paradise_lost.txt output.csv
        String fileName = args[0];
        System.out.printf("Processing %s\n", fileName);

        File fileObj = Paths.get("src/milton/" + fileName).toFile();
        FileReader fileReader = new FileReader(fileObj);
        BufferedReader bufferReader = new BufferedReader(fileReader);

        String line;

        //hashmap to hold the entries in <publisher>:<EntryLine> K:V
        Map <String, List> bookEntryMap = new HashMap<>();

        //first line is the header 
        String header = bufferReader.readLine(); 

        //now to process the rest of the lines as KV pairs
        for (Integer i = 0; i < 100; i++ ){
            line = bufferReader.readLine();
            
            if (null == line){
                break;
            }
            
            //create an empty List to keep all the single row Entry strings
            List<String> onePublisherList = new LinkedList<>();

            //splits up each cell in the csv into separate Strings. publisher is in the 12th cell
            String[] lineWordsList = line.trim().split(",");

            //process each publisher so the same ones are spelt the same
            //picking out each publisher
            String publisher = lineWordsList[11];
            //catching certain publishers thave have different spelling
            if (publisher.equals("Farrar  Straus and Giroux")){
                publisher = "Farrar Straus Giroux";
                lineWordsList[11] = "Farrar Straus Giroux";
            }
            if (publisher.equals("Scholastic Inc.")){
                publisher = "Scholastic";
                lineWordsList[11] = "Scholastic";
            }
            
            String cleanLineEntry = lineWordsList.join(" ", lineWordsList);
            String l = onePublisherList.add(cleanLineEntry);
            System.out.printf("%s %s", lineWordsList[11],cleanLineEntry);

            // String[] publisherNameList = lineWordsList[11].trim().split(" ");
            // if (publisherNameList[0].equalsIgnoreCase("scholastic")){
            //     publisher = "Scholastic Inc";
            // }


            // bookEntryMap.put(lineWordsList[11], lineWordsList);
            // bookEntryMap.getOrDefault(word, lineWordsList);
        }
        bufferReader.close();
        fileReader.close();
       

        //creates a set of unique publishers
        Set<String> uniquePublishers = bookEntryMap.keySet();
        
        //sorts the set by alphabetical order
        //Collections.sort(uniquePublishers);
        
/* 
        //saving out the entries according to publisher name
        FileOutputStream fos = new FileOutputStream(args[1]);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        osw.write(HEADER);

        for (String key : bookEntryMap.keySet()){
            String thisLine = String.format("%s, %d\n", key, bookEntryMap.get(key));
            osw.write(thisLine);
        }
        osw.flush();
        osw.close();
        fos.close();
        
        System.out.println("File saved!");*/

        // workshop homeworks - training to think in a systemic thinking way
        // we should sort the books according to publisher and print
        // or save them into files with <publisher>.csv with the books they published into the file!

        //read input

        //filter publisher list
        
        //filter the book name

    }
}
