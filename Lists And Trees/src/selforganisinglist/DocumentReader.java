/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selforganisinglist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author gracesubianto
 */
public class DocumentReader 
{

    BubbleArrayList<String> reading = new BubbleArrayList<>();
    String fileName = "textinput.txt";
    File path = new File(fileName);
    
    public void readFile() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.path)));
        String firstLine = br.readLine();
        
        while(firstLine != null)
        {
            String[] words = firstLine.split(" ");
            System.out.println(firstLine); //prints out the text file
            System.out.println("");

            for (String reading : words) 
            {
                //System.out.println(reading); //prints out the words seperated
                this.reading.add(reading.toLowerCase().replace(",","").replace(".","").replace("!","").replace("(","").replace(")",""));
            }
            
            firstLine = br.readLine();  
        
        }
        br.close();
        System.out.println(this.reading.toString()); //prints out words with incrementer
    }
    
    public DocumentReader() throws IOException
    {
        readFile();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        //BubbleArrayList<String> reading = new BubbleArrayList<>();
        DocumentReader dr = new DocumentReader();      
        
    }
    
}
