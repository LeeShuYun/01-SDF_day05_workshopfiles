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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//java -cp classes milton.Main paradise_lost.txt
public class Main {
    public static void main(String[] args) throws Exception{

        String HEADER = "word,count\n";

        //read file name
        String fileName = args[0];
        System.out.printf("Processing %s\n", fileName);

        File fileObj = Paths.get("src/milton/" + fileName).toFile();
        FileReader fileReader = new FileReader(fileObj);
        BufferedReader bdf = new BufferedReader(fileReader);

        String line;
                
        //wordcount in 100 lines
        /*Integer totalWords = 0;
        for (Integer i = 0; i < 100; i++ ){
            line = bdf.readLine();
            if (null == line)
                break;
            String[] listWords = line.trim().split(" ");
            totalWords += listWords.length;
            // System.out.printf(" %d: %s\n", lineCount, line.toUpperCase());
            // lineCount++;
        }*/

        //number of unique words that have appeared
        //total number of each unique word

        // use the Interface type Map instead of HashMap because it gives flexibility
        // HashMap is the concrete type, Map is the abstract type
        // we can change the HashMap to something later
        // it's like void print(LinkedList<Integer>)
        Map <String, Integer> wordMap = new HashMap<>();
       
        for (Integer i = 0; i < 100; i++ ){
            line = bdf.readLine();
            if (null == line){
                break;
            }
            String[] listWords = line.trim().split(" ");
            for (String word : listWords){
                word = word.replaceAll("\\p{Punct}","");
                word = word.toLowerCase();
                Integer v = wordMap.getOrDefault(word, 0);
                v++;
                wordMap.put(word, v);
                /* this is the if else method
                if (wordMap.containsKey(word)){
                    // Integer wordCount = wordMap.get(word) + 1;
                    wordMap.put(word, wordMap.get(word) + 1);
                }else{
                    wordMap.put(word, 1);
                }*/
            }// for loop end
        }
        Set<String> uniqueWords = wordMap.keySet();

        
        //prints and saves each line of words
        for (String key : uniqueWords){
            System.out.printf(" %s  %s\n", key, wordMap.get(key));
            // String thisLine = key + "," + wordMap.get(key);
            // bfw.write(thisLine);
        }
        System.out.println("# of unique words: " + wordMap.size());
        
        bdf.close();
        fileReader.close();

        // //sets output file for saving our words count list
        // String outputFilePath = "src/milton/output.csv"; 
        // FileWriter fw = new FileWriter(outputFilePath, false); // append mode / write mode    
        // BufferedWriter bfw = new BufferedWriter(fw);

        //saving out the word count list
        FileOutputStream fos = new FileOutputStream(args[1]);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        osw.write(HEADER);

        for (String key : wordMap.keySet()){
            String thisLine = String.format("%s, %d\n", key, wordMap.get(key));
            osw.write(thisLine);
        }
        osw.flush();
        osw.close();
        fos.close();
        
        System.out.println("File saved!");

        // workshop homeworks - training to think in a systemic thinking way
        // we should sort the books according to publisher and print
        // or save them into files with <publisher>.csv with the books they published into the file!
    }
}
