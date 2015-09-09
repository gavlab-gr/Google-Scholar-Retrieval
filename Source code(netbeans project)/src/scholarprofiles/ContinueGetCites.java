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
 * @author Babetas
 */
public class ContinueGetCites {//same as get Cites

    ArrayList<CitesDetails> cd = new ArrayList<>();

    public ContinueGetCites(ArrayList<String> Last, HashMap<String, ArrayList<CitesDetails>> hash) throws MalformedURLException, IOException {
        Connection con;
        int pg = Integer.parseInt(Last.get(1));
        pg = pg - 10;
        String value = Last.get(0);
        String name = Last.get(2);
        do {
            pg = pg + 10;
            URL u = new URL("http://scholar.google.gr/scholar?start=" + Integer.toString(pg) + "&hl=en&as_sdt=0,5&sciodt=0,5&cites=" + value);
            con = new Connection(u);
            if (hash.get(name) != null) {
                Cites cites = new Cites(con.getHtml(), hash.get(name));
            } else {
                hash.put(name, cd);
                Cites cites = new Cites(con.getHtml(), hash.get(name));
            }
            Save s = new Save();
            s.SaveSteps(value, pg, name);
            Save h = new Save();
            h.Savehash(hash);

        } while (!con.getHtml().contains("<button type=\"button\" aria-label=\"Next\" disabled class=") && con.getHtml().contains("<div id=\"gs_nm\" role=\"navigation\">") && !con.getHtml().contains("<div id=\"gs_captcha_c\">"));
        if ((!con.getHtml().contains("<div id=\"gs_n\" role=\"navigation\">") || con.getHtml().contains("<button type=\"button\" aria-label=\"Next\" disabled class=")) && !con.getHtml().contains("<div id=\"gs_captcha_ccl\">")) {
            File file = new File("LastStep.txt");
            file.delete();
        } else {

        }
        Save h = new Save();
        h.Savehash(hash);
        CitesList c = new CitesList(hash.get(name), name);
        c.setVisible(true);

    }
}
