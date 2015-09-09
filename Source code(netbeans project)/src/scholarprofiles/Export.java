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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Babetas
 */
public class Export {

    JFrame frame = new JFrame();

    public Export(String name, HashMap<String, AuthorProfile> hashpro, HashMap<String, ArrayList<CitesDetails>> hash) throws IOException {
        File file = new File(name + ".xml");
        FileWriter fileWriter = new FileWriter(file);
        ArrayList<String> aut;
        try (BufferedWriter print_line = new BufferedWriter(fileWriter)) {
            print_line.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
            print_line.write("<research>" + "\n");
            print_line.flush();
            AuthorProfile temp = hashpro.get(name);

            for (int i = 0; i < temp.papers.size(); i++) {
                print_line.write("<full>" + "\n");
                print_line.write("<paper>" + "\n");

                print_line.write("<title>" + temp.getPapers().get(i) + "</title>" + "\n");
                print_line.flush();
                aut = new ArrayList<>();
                for (int k = 0; k < temp.authors.get(i).size(); k++) {
                    String te = temp.authors.get(i).get(k);
                    aut.add(te);
                    print_line.write("<author>" + te + "</author>" + "\n");
                }
                print_line.write("<journal>" + temp.journal.get(i) + "</journal>" + "\n");
                print_line.write("<year>" + temp.year.get(i) + "</year>" + "\n");
                ArrayList<CitesDetails> h = hash.get(temp.getPapers().get(i));
                print_line.write("</paper>" + "\n");

                print_line.write("<citations>" + "\n");
                if (h != null) {
                    int self = 0;
                    for (CitesDetails h1 : h) {
                        if (!h1.pappers.contains("span")) {
                            for (int k = 0; k <= 0; k++) {
                                for (String te : h1.authors) {
                                    if (!aut.isEmpty()) {
                                        if (te.equals(aut.get(k))) {
                                            self++;
                                        }
                                    }
                                }
                            }

                            if (self > 0) {
                                print_line.write("<self-citation>" + "\n");
                            }
                            print_line.write("<paper>" + "\n");
                            print_line.write("<title>" + h1.pappers + "</title>" + "\n");
                            print_line.flush();
                            for (String te : h1.authors) {

                                print_line.write("<author>" + te + "</author>" + "\n");
                            }
                            print_line.write("<journal>" + h1.journal + "</journal>" + "\n");
                            print_line.write("</paper>" + "\n");
                            if (self > 0) {
                                print_line.write("</self-citation>" + "\n");
                            }
                            self = 0;
                        }
                    }
                }

                print_line.write("</citations>" + "\n");
                print_line.write("</full>" + "\n");
            }

            print_line.write("</research>" + "\n");
            JOptionPane.showMessageDialog(frame, "Export is completed!!!");
        }

    }
}
