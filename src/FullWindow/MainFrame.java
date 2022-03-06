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
import SlideMgr.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static Utilities.ImageUtilities.setTargetImage;

public class MainFrame extends JFrame
{
    static SlideDeck slideDeck;

    //slideshow display
    static CardLayout slideShow;
    static JPanel mainPanel;
    static BottomBar bb;
    static Slide currentSlide;


//Image loading variables
    private static final int baseSize = 128; //default image size variable, can add in our own to resize the image

    //get your desktop in most cases as a default directory
    File home = FileSystemView.getFileSystemView().getHomeDirectory();
    String basePath = home.getAbsolutePath();
//Image Loading variables


    MainFrame() throws InterruptedException
    {
        slideShow = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(slideShow);


        TopBar tb = new TopBar();
        this.add(tb, BorderLayout.NORTH);

        bb = new BottomBar(slideShow, mainPanel);
        this.add(bb, BorderLayout.SOUTH);


        Thread.sleep(100);


        slideDeck = SlideDeck.getSlideDeck();

        bb.initializeBB();
        slideDeck.addSlide();

        currentSlide.setBackground(Color.BLACK);
        slideDeck.addSlide();
        currentSlide.setBackground(Color.BLUE);
        slideDeck.addSlide();
        currentSlide.setBackground(Color.YELLOW);
        slideDeck.addSlide(1);













        //use this when you want to display a different slide
        slideShow.show(mainPanel, "1");
        //each slide has an identifier that is linke to each button

        //use this when you remove and rearrange the order of stuff
        //mainPanel.remove("3");

//image loading Button
      // s = new Slide();
       // JPanel top = new JPanel();
        //addImg = new JButton("Add Img");
       /* addImg.addActionListener(new ActionListener() {
            //put in whatever dimensions you want
            public void actionPerformed(ActionEvent e) {
                loadImage(e, baseSize, baseSize);
            }
        });
        top.add(addImg);*/

//uncomment both lines for demonstration. this will overwrite the top current layout for demo purposes
        //add(top, BorderLayout.NORTH);
        //add(s, BorderLayout.CENTER);

//image loading button

        this.add(mainPanel, BorderLayout.CENTER);
        this.setTitle("LearningMyFriend :শেখা ও শখা ");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        try
        {
            new MainFrame();
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    //happens when button is pressed, takes custom dimensions for an image
    protected void loadImage(ActionEvent e, int width, int height)
    {
        //opens a file explorer on the desktop
        JFileChooser fc = new JFileChooser(basePath);
        fc.setFileFilter(new ImageFileFilter());  //filters out non-image items
        int res = fc.showOpenDialog(null);
        // Image found
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                setTargetImage(file, width, height, slideDeck.getCurrentSlide());  //loads up the image file for us to use
            } //Image Not Found
            else {
                JOptionPane.showMessageDialog(null,
                        "An JPEG or PNG file must be selected. \nCancelling Action.", "Invalid File Chosen",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception iOException)
        {
        }

    }

    //adds slide on the end


    public static void updateSlideShow(Slide s, boolean isAdded)
    {
        if(isAdded)
            mainPanel.add(s, s.getSlideID());
        else
            mainPanel.remove(s);


    }

    public static BottomBar getBottomBar() {return bb;}

    public static void showSlide(Slide s)
    {
        slideShow.show(mainPanel, s.getSlideID()); //need to provide ID so it shows the correct slide
        slideDeck.setCurrentSlide(s);
        currentSlide = slideDeck.getCurrentSlide();
    }



}



