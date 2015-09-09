/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author babs
 */
public class AuthorProfile implements Serializable {
    
    ArrayList<String> papers = new ArrayList<>();
    ArrayList<ArrayList<String>> authors = new ArrayList<>();
    ArrayList<String>citepaths = new ArrayList<>();
    ArrayList<String>journal = new ArrayList<>();
    ArrayList<String>year = new ArrayList<>();
    String name;
    
    public AuthorProfile() {
        
    }
    void addYear(String y){
        year.add(y);
    }
    
    void addJournal(String jou){
        journal.add(jou);
    }
    void addAuthor(ArrayList<String> x){
        authors.add(x);
    }
    void addTitle(String title){
        papers.add(title);
    }
    void addCite(String link){
        citepaths.add(link);
    }
    void setName(String name){
        this.name=name;
    }
    
    ArrayList<ArrayList<String>> getAuthors(){
        return authors;
    }
    ArrayList<String> getPapers(){
        return papers;
    }
    ArrayList<String> getCites(){
        return citepaths;
    }
    
    ArrayList<String> getYear(){
        return year;
    }
    
    ArrayList<String> getJournal(){
        return journal;
    }
    String getName(){
        return name;
    }
}
