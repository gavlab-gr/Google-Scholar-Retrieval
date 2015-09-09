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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author babs
 */
public class CitesList extends JFrame {

    DefaultListModel listmodel;

    public CitesList(ArrayList<CitesDetails> cd,String name) {
        super(name);
        this.setMinimumSize(new Dimension(700, 500));//Dimensions of the citations list display
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

   
        listmodel = new DefaultListModel();
        JList<String> list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
        //setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        add(new JScrollPane(list));
        
        
        for (int i = 0; i < cd.size(); i++) {
            String build = "<html>" + cd.get(i).getPappers() + "<br/>" + cd.get(i).getAuthors() + "<br/>" + cd.get(i).getJournal() + "<br/></html>";
            listmodel.addElement(build);
        }
    }

}
