/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author babs
 */
public class getPapers {

    AuthorProfile prof;

    public getPapers(String html, String[] name, AuthorProfile prof) {
        this.prof = prof;
        prof.setName(name[0]+name[1]);
        String[] ht = html.split("<tr class=\"gsc_a_tr\">");//split the html page at the points tha you can find the papers titles.
        for (int i = 1; i < ht.length; i++) {//loop for all the papers
            Pattern title;
            String[] finalpath = null;
            title = Pattern.compile("<td class=\"gsc_a_t\"><a href=\".*?</a>");//gets title
            Matcher matcher = title.matcher(ht[i]);
            while (matcher.find()) {
                String[] temp = matcher.group().split("class=\"gsc_a_at\">");
                finalpath = temp[1].split("</a>");
            }
            prof.addTitle(finalpath[0]);

            Pattern authors;
            ArrayList<String> a = new ArrayList<>();
            authors = Pattern.compile("</a><div class=\"gs_gray\">.*?</div>");// gets authors
            Matcher matcher2 = authors.matcher(ht[i]);
            finalpath[0]="";
            while (matcher2.find()) {
                if (matcher2.group().compareTo("</a><div class=\"gs_gray\"></div>") != 0) {
                    String[] temp = matcher2.group().split("<div class=\"gs_gray\">");
                    finalpath = temp[1].split("</div>");
                    String[] author = finalpath[0].split(",");
                    for (int j = 0; j < author.length; j++) {
                        a.add(author[j]);

                    }
                }

            }
            prof.addAuthor(a);
            a = new ArrayList<>();

            Pattern journal;
            journal = Pattern.compile("</div><div class=\"gs_gray\">.*?</div>");//get journal
            Matcher matcher3 = journal.matcher(ht[i]);
            finalpath = null;
            while (matcher3.find()) {
                String[] temp = matcher3.group().split("<div class=\"gs_gray\">");
                finalpath = temp[1].split("<");
                finalpath[0] = finalpath[0].replace(",", ".");
            }
            prof.addJournal(finalpath[0]);

            Pattern cites;
            cites = Pattern.compile("<td class=\"gsc_a_c\"><a href=\".*?</td>");//gets link for citations
            Matcher matcher4 = cites.matcher(ht[i]);
            finalpath[0]="";
            while (matcher4.find()) {
                if (matcher4.group().compareTo("<td class=\"gsc_a_c\"><a href=\"\" class=\"gsc_a_ac\">&nbsp;</a></td>") != 0) {
                    String[] temp = matcher4.group().split("cites=");
                    finalpath = temp[1].split("\"");
                }
            }
            
            prof.addCite(finalpath[0]);

            Pattern year;
            year = Pattern.compile("<span class=\"gsc_a_h\">.*?</span>");// gets year
            Matcher matcher5 = year.matcher(ht[i]);
            finalpath[0]="";
            while (matcher5.find()) {
                if (matcher5.group().compareTo("<span class=\"gsc_a_h\"></span>") != 0) {
                    String[] temp = matcher5.group().split("<span class=\"gsc_a_h\">");
                    finalpath = temp[1].split("</span>");
                }
            }

            prof.addYear(finalpath[0]);
        }
        
    }

    AuthorProfile getAuthor() {//returs all the informations for the researcher
        return prof;
    }
}
