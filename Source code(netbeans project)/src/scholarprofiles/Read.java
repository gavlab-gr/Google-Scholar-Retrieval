/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author babs
 */
public class Read {

    HashMap<String, ArrayList<CitesDetails>> Readhash(HashMap<String, ArrayList<CitesDetails>> hash) throws FileNotFoundException, IOException, ClassNotFoundException{
    File toRead=new File("hash");//reads the structure with citations
        if(toRead.exists()){
        FileInputStream fis=new FileInputStream(toRead); 
        ObjectInputStream ois=new ObjectInputStream(fis);

        hash=(HashMap<String, ArrayList<CitesDetails>>)ois.readObject();

        ois.close();
        fis.close();
        } 
       return hash; 
    }
    
    HashMap<String,AuthorProfile> Readprof(HashMap<String, AuthorProfile> hash) throws FileNotFoundException, IOException, ClassNotFoundException{
    File toRead=new File("hashpro"); //reads the structure with the list of researcher's papers    
        if(toRead.exists()){
        FileInputStream fis=new FileInputStream(toRead);        
        ObjectInputStream ois=new ObjectInputStream(fis);

        hash=(HashMap<String, AuthorProfile>)ois.readObject();
        
        ois.close();
        fis.close();
        }
       return hash; 
    }
    
    ArrayList<String> ReadLast() throws FileNotFoundException, IOException{
     File toRead=new File("LastStep.txt");//Store the last step that we were before scholar provide us a captcha
     ArrayList<String> read= new ArrayList<>();
         if(toRead.exists()){
             BufferedReader br=null;
             String line;
             br=new BufferedReader(new FileReader(toRead));
             while((line=br.readLine())!=null){
                 String[] word=line.split(",");
                 for(int i=0;i<word.length;i++){
                 read.add(word[i]);
                 
                 }
             }
             br.close();
         }
        return read;
        
    }
    
    
}
    

    

