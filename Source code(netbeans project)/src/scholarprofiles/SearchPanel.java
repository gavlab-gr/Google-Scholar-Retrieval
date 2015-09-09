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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author babs
 */
public class SearchPanel extends JFrame {
    ImageIcon icon = new ImageIcon("images/logo1.png");
    JLabel label = new JLabel(icon);
    JTextField text = new JTextField("",25);
    JButton search = new JButton("Search");
    clickListener clk = new clickListener();
    JPanel upperboard;
    public SearchPanel() {
        super("Google Scholar Retrieval");
        search.setPreferredSize(new Dimension(80,20));
        JPanel blanc = new JPanel(new GridLayout(5,0));
        
       
        this.setMinimumSize(new Dimension(300,200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        FlowLayout x = new FlowLayout();
        x.setHgap(35);
        upperboard = new JPanel(x);
        add(upperboard);
        
        JPanel main = new JPanel(new GridLayout(5, 0));
        upperboard.add(main);
        main.add(blanc);
        JPanel up = new JPanel(new FlowLayout());
        up.add(label);
        main.add(up);

        JPanel mid = new JPanel(new FlowLayout());
        mid.add(text);
        main.add(mid);

        JPanel down = new JPanel(new FlowLayout());
        down.add(search);
        main.add(down);
        main.add(blanc);
        search.addActionListener(clk);

    }

    private class clickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String cmd = e.getActionCommand();
            if (cmd.equals("Search")) {               
                try {
                    getProfile p = new getProfile(text.getText());
                    AuthorProfile prof = p.getPro();
                   
                } catch (IOException ex) {
                    Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
              
                    
                
            }
        }
    }

}
