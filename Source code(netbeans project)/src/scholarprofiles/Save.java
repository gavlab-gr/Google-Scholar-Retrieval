/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author babs
 */
public class Save  {

    PrintWriter writer;

    void Savehash(HashMap<String, ArrayList<CitesDetails>> det) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File file = new File("hash"); //saves the structure with citations     
        
        FileOutputStream f = new FileOutputStream(file);  
        ObjectOutputStream s = new ObjectOutputStream(f);          
        s.writeObject(det);
        s.flush();
        s.close();
        f.close();

    }
    
        void Savepro(HashMap<String, AuthorProfile> det) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File file = new File("hashpro"); //saves the structure with the list of researcher's papers     
        
        FileOutputStream f = new FileOutputStream(file);  
        ObjectOutputStream s = new ObjectOutputStream(f);          
        s.writeObject(det);
        s.flush();
        s.close();
        f.close();

    }
    
    
    
    void SaveSteps(String value , int page,String name) throws FileNotFoundException, IOException{
        File file = new File("LastStep.txt");//Store the last step that we were before scholar provide us a captcha
        FileWriter fileWriter = new FileWriter(file);        
        BufferedWriter print_line = new BufferedWriter(fileWriter);
        print_line.flush();
        print_line.write(value+",");
        print_line.write(page+",");
        print_line.write(name);
        print_line.close();
    }
}
