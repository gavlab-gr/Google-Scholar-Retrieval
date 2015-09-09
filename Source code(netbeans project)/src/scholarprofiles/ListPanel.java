/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author babs
 */
public class ListPanel extends JFrame {

    clickListener clk = new clickListener();
    JTextField txt;
    DefaultListModel listmodel;
    int[] x;
    AuthorProfile prof;
    HashMap<String, AuthorProfile> hashpro = new HashMap();
    HashMap<String, ArrayList<CitesDetails>> hash = new HashMap<>();
    Read read = new Read();
    Save save = new Save();
    JLabel author;
    JFrame frame = new JFrame();

    public ListPanel() throws IOException, FileNotFoundException, ClassNotFoundException {

        super("Google Scholar Retrieval");//Initialize the data structures with the stored informations

        hashpro = read.Readprof(hashpro);
        hash = read.Readhash(hash);
        URL myURL = new URL("http://scholar.google.com/scholar?start=0&q=:manolis+author:wallace&hl=en&as_sdt=0,5");
        Connection con = new Connection(myURL);
        if ((con.getHtml().contains("<button type=\"button\" aria-label=\"Next\" disabled class=") || con.getHtml().contains("<div id=\"gs_nm\" role=\"navigation\">")) && !con.getHtml().contains("<div class=\"rc-anchor rc-anchor-normal rc-anchor-standard\"><div class=\"rc-anchor-aria-status\">")) {
            File toRead = new File("LastStep.txt");//continue the search from the point that google provide us a captcha
            if (toRead.exists()) {
                ContinueGetCites cgc = new ContinueGetCites(read.ReadLast(), hash);

            }
        } else {
            JOptionPane.showMessageDialog(frame, "Google Scholar is not ready for mining.\n Restart your modem or try again in some hours");
        }

        JMenuBar menu = new JMenuBar();

        this.setMinimumSize(new Dimension(700, 600));//user interface
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(menu);
        
        ImageIcon ic=new ImageIcon("g.gif");       
        JLabel picLabel = new JLabel();   
        picLabel.setIcon(ic);

        JMenu file = new JMenu("File");
        menu.add(file);
        JMenu help = new JMenu("Help");
        JMenu about = new JMenu("About");
        JMenuItem export = new JMenuItem("Export");
        JMenuItem inf = new JMenuItem("Infos");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(export);
        file.add(exit);
        menu.add(file);
        menu.add(help);
        menu.add(about);
        about.add(inf);
        JPanel aristero = new JPanel(new GridLayout(2, 0));
        JPanel ariup = new JPanel(new GridLayout(6, 0));
        JPanel aridown = new JPanel(new FlowLayout());
        JPanel d = new JPanel(new FlowLayout());
        JButton search = new JButton("Search");
        JButton view = new JButton("View Cites");
        search.setSize(new Dimension(80, 30));
        view.setSize(new Dimension(80, 30));
        txt = new JTextField("", 15);
        ariup.add(txt);
        aridown.add(search);
        aridown.add(view);
        ariup.add(aridown);
        aristero.add(ariup);
        d.add(picLabel);
        aristero.add(d);
        add(aristero, BorderLayout.WEST);


        JPanel blanc = new JPanel(new GridLayout(1, 3));
        JPanel up = new JPanel(new GridLayout(1, 5));
        JPanel left = new JPanel(new GridLayout(4, 0));
        author = new JLabel("Researcher:  ");
        JLabel fields = new JLabel("Topics:  ");
        JLabel link = new JLabel("Link:  ");
        JLabel name = new JLabel();
        left.add(author);
        left.add(fields);
        left.add(link);
        up.add(left);
        name.setText(txt.getText());
        left.add(name);
        up.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
      

        add(up, BorderLayout.NORTH);

        JPanel mid = new JPanel(new GridLayout(0, 1));
        listmodel = new DefaultListModel();
        JList<String> list = new JList(listmodel);
        mid.add(new JScrollPane(list));
        mid.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        add(mid);
        export.addActionListener(clk);
        inf.addActionListener(clk);
        exit.addActionListener(clk);
        search.addActionListener(clk);
        view.addActionListener(clk);
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                x = list.getSelectedIndices();

            }
        });

    }

    private class clickListener implements ActionListener {

        public clickListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String cmd = e.getActionCommand();

            if (cmd.equals("Search")) {
                listmodel.removeAllElements();
                author.setText("Researcher: " + txt.getText());
                URL myURL = null;
                if (hashpro.get(txt.getText()) == null) {
                    try {
                        myURL = new URL("http://scholar.google.com/scholar?start=0&q=:manolis+author:wallace&hl=en&as_sdt=0,5");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ListPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Connection con = null;
                    try {
                        con = new Connection(myURL);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ListPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (con.getHtml().contains("<button type=\"button\" aria-label=\"Next\" disabled class=") || con.getHtml().contains("<div id=\"gs_nm\" role=\"navigation\">")) {
                        //checks if we already the researcher's list on our data structure

                        try {
                            getProfile p = new getProfile(txt.getText()); //Connects to scholar and retrieve the profile of the researcher and get the list

                            prof = p.getPro(); //
                            for (int i = 0; i < prof.getPapers().size(); i++) { //we format the list with html code in order to be good in reading on our ui
                                String build = "<html>" + prof.getPapers().get(i) + "<br/>" + prof.getAuthors().get(i) + "<br/>" + prof.getJournal().get(i) + "<br/>" + prof.getYear().get(i) + "<br/></html>";
                                listmodel.addElement(build);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        hashpro.put(txt.getText(), prof);//Save the researcher's profile on our structure for the future
                        try {
                            save.Savepro(hashpro);// we save this structure on a file
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(ListPanel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ListPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else {
                        JOptionPane.showMessageDialog(frame, "Google Scholar is not ready for mining.\n Restart your modem or try again in some hours");
                    }
                } else {// if we have already this researcher on our data structure

                    prof = hashpro.get(txt.getText());//we retrieve the list just using as a key the researcher's name
                    for (int i = 0; i < prof.getPapers().size(); i++) {//we format the list with html code in order to be good in reading on our ui
                        String build = "<html>" + prof.getPapers().get(i) + "<br/>" + prof.getAuthors().get(i) + "<br/>" + prof.getJournal().get(i) + "<br/>" + prof.getYear().get(i) + "<br/></html>";
                        listmodel.addElement(build);
                    }
                }
            }
            if (cmd.equals("View Cites")) {//retrieve and display the citations

                for (int i = 0; i < x.length; i++) {
                    try {
                        if (hash.get(prof.getPapers().get(x[i])) != null) {//check if we already have a list with the citations and we retrieve it for display
                            ArrayList<CitesDetails> cites = hash.get(prof.getPapers().get(x[i]));//we get the citations from our structure
                            CitesList cl = new CitesList(cites, prof.getPapers().get(x[i]));
                            cl.setVisible(true);//we display them
                        } else {
                            getCites c = new getCites(prof, x[i], hash);// Retrieve from google scholar the citations
                            if (c.getFlag()) {
                                JOptionPane.showMessageDialog(frame, "Google Scholar is not ready for mining.\n Restart your modem or try again in some hours");
                                break;

                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ListPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (cmd.equals("Export")) {
                try {
                    Export x = new Export(txt.getText(), hashpro, hash);// we export our data in xml documents
                } catch (IOException ex) {
                    Logger.getLogger(ListPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(cmd.equals("Infos")){
                JOptionPane.showMessageDialog(frame, "Written by:\nNikolas Bampetas\nGAV LAB \nhttp://gav.uop.gr/ \nAll rights reserved");
            }
            if (cmd.equals("Exit")) {
                System.exit(0);//exit the program

            }
        }
    }
}
