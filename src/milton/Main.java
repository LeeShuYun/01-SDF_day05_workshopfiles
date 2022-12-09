package milton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

//java -cp classes milton.Main paradise_lost.txt
public class Main {
    public static void main(String[] args) throws Exception{
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
        HashMap <String, Integer> wordMap = new HashMap<>();
       
        while (true){
            line = bdf.readLine();
            if (null == line){
                break;
            }
            String[] listWords = line.trim().split(" ");
            for (String word : listWords){
                word = word.replaceAll("\\p{Punct}","");
                if (wordMap.containsKey(word)){
                    Integer wordCount = wordMap.get(word) + 1;
                    wordMap.put(word, wordCount);
                }else{
                    wordMap.put(word, 1);
                }
            }// for loop end
        }
        System.out.println("# of unique words: " + wordMap.size());
        for (String key : wordMap.keySet()){
            System.out.printf("Word: %s, Count: %s\n", key, wordMap.get(key));
        }
        
        //read the lines up to line 20 

        // while ((line = bdf.readLine()) != null &&  lineCount < 20 ){
        //     System.out.println("> " + line);
        //     lineCount++;
        // }
        bdf.close();
        fileReader.close();

    }
}
