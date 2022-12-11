package milton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/*
 * This is an exercise in reading and writing files.
 * The program takes a book.csv and sorts them according to the publisher's name in alphabetical order using a TreeMap
 * Then it outputs a publisher.csv in sorted order
 * This only works on command line:
 * javac -sourcepath src -d classes src/milton/BookSort.java
 * java -cp classes milton.BookSort books.csv publisher.csv
 */

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
        Map <String, List<String>> bookEntryMap = new TreeMap<>();

        //first line is the header 
        String header = bufferReader.readLine(); 

        //now to process the rest of the lines as KV pairs
        while((line = bufferReader.readLine()) != null){
            line = bufferReader.readLine();
            
            if (null == line){
                break;
            }

            //splits up each cell in the csv into separate Strings in a list. publisher is in the 12th index
            String[] lineWordsList = line.trim().split(",");

            //now we process each publisher so the same ones are spelt the same ==================
            //picking out each publisher
            String publisher = lineWordsList[11];

            //catching certain publishers that have have different spelling - will put into separate function later
            if (publisher.equals("Farrar  Straus and Giroux")){
                publisher = "Farrar Straus Giroux";
                lineWordsList[11] = "Farrar Straus Giroux";
            }
            if (publisher.equals("Scholastic Inc.")){
                publisher = "Scholastic";
                lineWordsList[11] = "Scholastic";
            }
            //reconstruct each entry line
            String entryLine = String.join(",", lineWordsList);
            // ======= correction of publisher name is done =========================================
            
            //create empty List to keep all the single row Entry strings for one single publisher
            List<String> onePublisherList = new LinkedList<>();

            //checking if we have the current publisher as a key inside final Hashmap. we get a correct Linkedlist for the current publisher either way
            onePublisherList = bookEntryMap.getOrDefault(publisher, onePublisherList);

            //now that we have the publisher's list, we add the corrected csv line into our one single publisher list
            onePublisherList.add(entryLine);
    
            // we add the publisher to its list of entry strings inside the final treemap. it should fall into its natural order and be sorted according to publisher key
            bookEntryMap.put(publisher, onePublisherList);

        }
        bufferReader.close();
        fileReader.close();
       
        //now we start putting each publisher into our save file, in natural order first

        //saving out the entries according to publisher name
        FileOutputStream fos = new FileOutputStream(args[1]);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        //we write the header first
        osw.write(header);

        //now we iterate over each key <String>
        Iterator<String> itr = bookEntryMap.keySet().iterator();

        // for (String key : bookEntryMap.keySet()){  //alt way of iterating
        while(itr.hasNext()){
            String key=(String)itr.next();

            //grab the first single publisher list
            List<String> thisPublisher = bookEntryMap.get(key);

            //we unravel the list of strings for each publisher and write each one into save file. this one is unsorted
            for (Integer i = 0; i < thisPublisher.size(); i++){
                // System.out.println(thisPublisher.get(i));  //test line
                String finalEntryLine = thisPublisher.get(i) + "\n";
                osw.write(finalEntryLine);
            }
            
        }
        osw.flush();
        osw.close();
        fos.close();
        
        System.out.println("File saved!");


    }
}
