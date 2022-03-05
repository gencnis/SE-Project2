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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    // TODO: Ideally instead of this we would have our Deck of Slides
    //can change this to Slide
    ArrayList<Slide> allPanels = new ArrayList<>();

    //slideshow display
    CardLayout slideShow = new CardLayout();
    JPanel mainPanel = new JPanel();

    //TODO: Ideally instead of JPanels we want to have our Slide class
    // If that's not possible let me know (fehmi) we might be able to change some signatures around
    Slide s1 = new Slide();
    Slide s2 = new Slide();
    Slide s3 = new Slide();

//Image loading variables
    Slide s;
    private JButton addImg;
    File targetFile;
    BufferedImage targetImg;
    ImageIcon icon; //what you load your image into when you get it
    private static final int baseSize = 128; //default size variable, can add in our own to resize the image

    //get your desktop in most cases as a default directory
    File home = FileSystemView.getFileSystemView().getHomeDirectory();
    String basePath = home.getAbsolutePath();
//Image Loading variables


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

//image loading Button
        s = new Slide();
        JPanel top = new JPanel();
        addImg = new JButton("Add Img");
        addImg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadImage(e, baseSize, baseSize);
            }
        });
        top.add(addImg);

//uncomment both lines for demonstration. this will overwrite the top current layout for demo purposes
        //add(top, BorderLayout.NORTH);
        //add(s, BorderLayout.CENTER);

//image loading button


        this.setTitle("LearningMyFriend :শেখা ও শখা ");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
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
                setTargetImage(file, width, height);  //loads up the image file for us to use
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


    //resizes the image based on provided image and dimensions
    public BufferedImage rescale(BufferedImage originalImage, int width, int height)
    {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    //primes image for use
    public void setTargetImage(File reference, int width, int height)
    {

        try
        {
            targetFile = reference;
            targetImg = rescale(ImageIO.read(reference), width, height); //sets image to desired size
        } catch (IOException ex) {
            // Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }



        icon=new ImageIcon(targetImg); //puts image into a use able format for JPanels
        s.addImage(icon); //adds image to the slide


    }
}



