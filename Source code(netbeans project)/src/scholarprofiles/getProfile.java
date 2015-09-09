/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author babs
 */
public class getProfile {
    AuthorProfile profile;
    public getProfile(String name) throws IOException, InterruptedException {
        Connection con;
        Save tofile;
        
        queryFix query = new queryFix(name);// fix researcher name-surname
        String[] search = query.getString();
        URL myURL = new URL("http://scholar.google.com/scholar?start=0&q=:"+search[0]+"+author:"+search[1]+"&hl=en&as_sdt=0,5");//Creating the url with the full name of the researcher
        con = new Connection(myURL);//connection to scholar google
        goToProf prof = new goToProf(con.getHtml());//Find the url with the list of the papers of the researcher
        profile = new AuthorProfile();
        int pages = -100;
        do {//collecting all the papers titles
            pages = pages + 100;
            String url = prof.getURL();
            URL u = new URL(url + "&cstart=" + Integer.toString(pages) + "&pagesize=100");//next page in google scholar
            con = new Connection(u);
                      
           if (!con.getHtml().contains("There are no articles in this profile.")) {
                
                getPapers pap = new getPapers(con.getHtml(), search, profile);
            } 
        
            
        } while (!con.getHtml().contains("There are no articles in this profile."));//finish the retrieval
     

}
   AuthorProfile getPro (){
       return profile;
   }
}
