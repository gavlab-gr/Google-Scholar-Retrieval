/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author babs
 */
public class getCites {
    HashMap<String, ArrayList<CitesDetails>> hash ;
    ArrayList<CitesDetails> cd = new ArrayList<>();
    boolean flag;
    public getCites(AuthorProfile profile,int value,HashMap<String, ArrayList<CitesDetails>> has) throws MalformedURLException, IOException {
            hash=has;
            flag=false;
            int pg = -10;
            Connection con;
            hash.put(profile.getPapers().get(value), cd);
            String name=profile.getPapers().get(value);
            Save s =new Save();
            
            do {                
                pg = pg + 10;
                s.SaveSteps(profile.getCites().get(value),pg,name);//save last step
                URL u = new URL("http://scholar.google.gr/scholar?start=" + Integer.toString(pg) + "&hl=en&as_sdt=0,5&sciodt=0,5&cites=" + profile.getCites().get(value));//creates link for the citation list that google scholar provides
                con = new Connection(u);                
                Cites cites = new Cites(con.getHtml(), cd);//Mine the data from the downloaded html                  
                Save h = new Save();
                h.Savehash(hash); //save the above information             
            } while (!con.getHtml().contains("<button type=\"button\" aria-label=\"Next\" disabled class=") && con.getHtml().contains("<div id=\"gs_nm\" role=\"navigation\">") && !con.getHtml().contains("<div id=\"gs_captcha_c\">")); 
           if((!con.getHtml().contains("<div id=\"gs_n\" role=\"navigation\">") || con.getHtml().contains("<button type=\"button\" aria-label=\"Next\" disabled class="))&& !con.getHtml().contains("<div id=\"gs_captcha_ccl\">")){    //if everything was fine with the retrieval and google scholar does not denied the access          
                File file = new File("LastStep.txt");//delete last step file 
                file.delete();                
            }  else{
               flag=true;//else make flag true and stop the mining
            }         
            Save h = new Save();
            h.Savehash(hash);//save the last informations
            CitesList c = new CitesList(cd,name);//project them
            c.setVisible(true);
            
     
           
    }
    
    boolean getFlag(){
        return flag;
        
    }
}
