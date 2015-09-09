    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author babs
 */
public class Cites {

    public Cites(String html, ArrayList<CitesDetails> cd) throws FileNotFoundException, UnsupportedEncodingException {
        String[] ht = html.split("<div class=\"gs_r\">");        
        for (int k = 1; k < ht.length; k++) {

            CitesDetails det = new CitesDetails();
            Pattern papper;
            papper = Pattern.compile("<h3 class=\"gs_rt\">.*?</h3>");//split for every block of paper informations
            Matcher papper_matcher = papper.matcher(ht[k]);
            while (papper_matcher.find()) {

                String[] finaltitle = null;
                String[] temp = null;
                //Retrieves the paper title
                if (papper_matcher.group().contains("href")) {
                    temp = papper_matcher.group().split("href");
                }else if(papper_matcher.group().contains("span dir=\"rtl\"")) {
                    temp = papper_matcher.group().split("rtl\">");
                } else {
                    temp = papper_matcher.group().split("</span>");
                }
                String[] temp2 = temp[1].split(">");
                if (temp2[1].contains("</a")) {
                    finaltitle = temp2[1].split("</a");
                } else {
                    finaltitle = temp2[1].split("</h3");
                }
                
                finaltitle[0]=finaltitle[0].replace("&hellip;", "");//replace &hellipc with space because creates problems
                det.addPapers(finaltitle[0]);
                 
            }

            Pattern details;
            details = Pattern.compile("<div class=\"gs_a\">.*?</div>");// splits for every paper the div class with the infos
            Matcher details_matcher = details.matcher(ht[k]);

            while (details_matcher.find()) {
                String[] result = null;
                String[] temp1=null;
                String[] temp2=null;
                String[] temp = null;
                String[] temp3 = null;
                String[] result2 = null;
                String journal = null;
                ArrayList<String> author;
                if (details_matcher.group().contains("\"gs_a\"><a href")) {//take the authors  and journal
                    temp = details_matcher.group().split("sra\">");
                    author = new ArrayList<>();
                    for (int i = 1; i < temp.length; i++) {                       
                        result = (temp[i].split("</a>"));
                        result[0]=result[0].replace("&hellip;", "");
                        author.add(result[0]);
                        if(temp[i].contains(", <a")&& i<temp.length-1){
                           temp1 = (temp[i].split(", <a"));
                          if(temp1[0].contains(", ")){
                            temp2=temp1[0].split(", ");
                             author.add(temp2[1]);
                          }                         
                         
                        }                            
                    }   
                    result2=result[1].split("-");
                    if(result2.length>2){
                        journal = result2[1].replace("</div>", "")+""+result2[2].replace("</div>", "");
                            result2[0]=result2[0].replace("&hellip;", "");
                            author.add(result2[0]);
                        
                    }
                    else if(result2.length>1){
                       journal = result2[1].replace("</div>", "");
                            result2[0]=result2[0].replace("&hellip;", "");
                            author.add(result2[0]);
                        
                    }
                    else{journal="";}
                    det.addAuthors(author);
                    journal = result[1].replace("</div>", "");
                    journal = journal.replace("&hellip;", "");
                    det.addJournal(journal);

                } else if (details_matcher.group().contains("href") && !details_matcher.group().contains("\"gs_a\"><a href")) {
                    temp = details_matcher.group().split("gs_a\">"); 
                    
                    
                    
                    result = temp[1].split("<a href");
                    author = new ArrayList<>();
                    result[0]=result[0].replace("&hellip;", "");
                    author.add(result[0]);                 
                    for (int i = 1; i < result.length; i++) {
                         temp3=result[i].split("sra\">");
                         result2 = temp3[1].split("</a>");
                         result2[0]=result2[0].replace("&hellip;", "");
                         author.add(result2[0]);                        
                    }                                                                           
                    
                    result = result2[1].split("-");             
                    
                    if(result.length>2){
                        journal = result[1].replace("</div>", "")+""+result[2].replace("</div>", "");
                       
                    }
                    else if(result.length>1){
                       journal = result[1].replace("</div>", ""); 
                       
                    }else{journal="";}
                    journal = journal.replace("&hellip;", "");
                    det.addJournal(journal);                    
                    det.addAuthors(author);

                } else if (!details_matcher.group().contains("href") && !details_matcher.group().contains("\"gs_a\"><a href")) {
                    temp = details_matcher.group().split("gs_a\">");
                    author = new ArrayList<>();
                    temp3 = temp[1].split("</div>");
                    result = temp3[0].split("-");
                    result2 = result[0].split(",");

                    for (int l = 0; l < result2.length; l++) {
                        //det.addAuthors(result2[l]);
                        result2[l]=result2[l].replace("&hellip;", "");
                        author.add(result2[l]);
                    }
                    if(result.length>2){
                        journal = result[1].replace("</div>", "")+""+result[2].replace("</div>", "");
                    }
                    else if(result.length>1){
                       journal = result[1].replace("</div>", ""); 
                    }else{
                       journal="";
                    }
                    journal = journal.replace("&hellip;", "");
                    det.addJournal(journal);
                    det.addAuthors(author);

                }

            }
            cd.add(det);
           
        }

    }
}
