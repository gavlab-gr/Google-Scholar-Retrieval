/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * @author babs
 */
public class Connection {

    String html;
    HttpURLConnection google;

    public Connection(URL url) throws MalformedURLException, UnsupportedEncodingException, IOException {
        google =(HttpURLConnection) url.openConnection();
        google.setRequestProperty("User-Agent", "jabref");
        String mimeType = google.getContentType();
        StringBuilder content = new StringBuilder();
        
        CookieHandler cm;
        try {
            if ((cm = CookieHandler.getDefault()) == null) {
                cm = new CookieHandlerImpl();
                CookieHandler.setDefault(cm);
            }
        } catch (SecurityException e) {
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(google.getInputStream(), "ASCII"));

        String line;
        int flag = 0;
        while ((line = reader.readLine()) != null) {
            if (flag == 0) {
                flag++;
                html = line;
            } else {
                html = html + line;
            }
        }
        reader.close();
    }

    String getHtml() {
        return html;
    }

}
