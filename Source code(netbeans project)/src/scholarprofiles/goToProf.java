/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author babs
 */
public class goToProf {
    String url;

    public goToProf(String html) throws MalformedURLException{ // goToProf finds the exact url of the professor profile in google scholar
        Pattern pattern;
        pattern = Pattern.compile("<h4 class=\"gs_rt2\"><a href=\".*?\">");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String[] temp=matcher.group().split("<a href=\"");
            String[] finalpath=temp[1].split(">");
            
            url="http://scholar.google.com"+finalpath[0];
            
        
    }
}
    String getURL(){ // getURL returns the url of the of the professor profile in google scholar
        return url;
    }
}
