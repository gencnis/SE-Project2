/**
 *
 * This is the full window that contains everything
 *
 * @Author: Fehmi Neffati
 *
 */

package FullWindow;

import BottomNavigation.BottomBar;
import TopBar.TopBar;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    //SAMPLE BUTTON CLICK LISTENER CODE:
    //needs to declare buttons Here
    private JButton load;

    //slideshow display
    CardLayout slideShow = new CardLayout();
    JPanel mainPanel = new JPanel();
    JPanel s1 = new JPanel();
    JPanel s2 = new JPanel();
    JPanel s3 = new JPanel();
    JButton b1 = new JButton("Switch to Second Slide");
    JButton b2 = new JButton("Switch to Third Slide");



    MainFrame()
    {
        TopBar tb = new TopBar();
        this.add(tb, BorderLayout.NORTH);

        BottomBar bb = new BottomBar();
        this.add(bb, BorderLayout.SOUTH);






//CENTER SLIDESHOW CARD LAYOUT
        mainPanel.setLayout(slideShow);
        s1.add(b1);
        s2.add(b2);
        s1.setBackground(Color.CYAN);
        s2.setBackground(Color.BLACK);

        //implement button actions like this to keep things neat
        b1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                slideShow.show(mainPanel, "2");
            }
        });


        b2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                slideShow.show(mainPanel, "3");
            }
        });

        mainPanel.add(s1, "1");
        mainPanel.add(s2, "2"); //the second parameter is an identifier string
        mainPanel.add(s3, "3");
        //we can set this to be the string number identifier from the slide

        //you set the slideShow's main parent Jpanel
        // and then the string identifier for the slide to be displayed
        //use this when nyou want to display a different slide
        slideShow.show(mainPanel, "1");
        add(mainPanel, BorderLayout.CENTER);
        //use this when you remove and rearrange the order of stuff
        //mainPanel.remove("3");



        this.setTitle("LearningMyFriend :শেখা ও শখা ");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }



}
