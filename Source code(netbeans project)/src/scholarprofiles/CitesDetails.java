/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author babs
 */
public class CitesDetails implements Serializable {

    String pappers;
    String details;
    ArrayList<String> authors = new ArrayList<>();
    String journal;

    public CitesDetails() {

    }

    void addPapers(String y) {
        this.pappers = y;
    }

    void addAuthors(ArrayList<String> y) {
        authors=y;
    }

    void addJournal(String y) {
        journal = y;
    }

    void addDetails(String jou) {
        details = jou;
    }

    String getPappers() {
        return pappers;
    }
    
    ArrayList<String> getAuthors() {
        return authors;
    }
    
    String getDetails() {
        return details;
    }

    String getJournal() {
        return journal;
    }

}
