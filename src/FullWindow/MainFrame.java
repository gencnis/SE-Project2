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


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    // TODO: Ideally instead of this we would have our Deck of Slides
    ArrayList<JPanel> allPanels = new ArrayList<>();

    //slideshow display
    CardLayout slideShow = new CardLayout();
    JPanel mainPanel = new JPanel();

    //TODO: Ideally instead of JPanels we want to have our Slide class
    // If that's not possible let me know (fehmi) we might be able to change some signatures around
    JPanel s1 = new JPanel();
    JPanel s2 = new JPanel();
    JPanel s3 = new JPanel();



    MainFrame()
    {
        // TODO: Here Ideally we want the slides to be added to the deck arrayList automatically when they're created
        // And that's the one we'd want to pass to the bottom border
        allPanels.add(s1);
        allPanels.add(s2);
        allPanels.add(s3);


        TopBar tb = new TopBar();
        this.add(tb, BorderLayout.NORTH);

        BottomBar bb = new BottomBar(allPanels, slideShow, mainPanel);
        this.add(bb, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);


        s1.setBackground(Color.BLACK);
        s2.setBackground(Color.GREEN);
        //CENTER SLIDESHOW CARD LAYOUT
        mainPanel.setLayout(slideShow);

        mainPanel.add(s1, "1");
        mainPanel.add(s2, "2"); //the second parameter is an identifier string
        mainPanel.add(s3, "3");
        //we can set this to be the string number identifier from the slide

        //you set the slideShow's main parent Jpanel and then the string identifier for the slide to be displayed
        //use this when you want to display a different slide
        slideShow.show(mainPanel, "1");
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
