/**
 *
 * This is the full window that contains everything
 *
 * @Author: Fehmi Neffati
 *
 */

package FullWindow;

import BottomNavigation.BottomBar;
import Item.Text;
import TopBar.TopBar;
import SlideMgr.*;
import Utilities.IsKeyPressed;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.security.Key;


import static Utilities.ImageUtilities.setTargetImage;

public class MainFrame extends JFrame
{
    static SlideDeck slideDeck;

    //slideshow display
    static CardLayout slideShow;
    static JPanel mainPanel;
    static BottomBar bb;
    static TopBar tb;
    static Slide currentSlide;

    static MainFrame mainFrame;


    //Image loading variables
    private static final int baseSize = 128; //default image size variable, can add in our own to resize the image

    //get your desktop in most cases as a default directory
    static File home = FileSystemView.getFileSystemView().getHomeDirectory();
    static String basePath = home.getAbsolutePath();

    //Element Editing Variables
    static JComponent selectedItem = null;

    //maintain user's window size and location
    static Dimension userDimensions;
    static Point userLocation;
    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0]; //get screen
    static boolean isFullScreen = false;

    MainFrame() throws InterruptedException
    {

        slideShow = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(slideShow);



         tb = new TopBar(this);
        this.add(tb, BorderLayout.NORTH);

        bb = new BottomBar(slideShow, mainPanel);
        this.add(bb, BorderLayout.SOUTH);


        // Thread.sleep(100);


        slideDeck = SlideDeck.getSlideDeck();

        bb.initializeBB();
        slideDeck.addSlide();

        currentSlide.setBackground(Color.BLACK);
        slideDeck.addSlide();
        currentSlide.setBackground(Color.BLUE);
        slideDeck.addSlide();
        currentSlide.setBackground(Color.YELLOW);
        slideDeck.addSlide(1);
        currentSlide.setBackground(Color.GREEN);

        //slideDeck.removeSlide(); //removes current slide
        //slideDeck.removeSlide(0); //removes slide at index



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

            mainFrame = new MainFrame();

        } catch (InterruptedException e)
        {
            System.err.format("IOException: %s%n", e);
        }


        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke)
            {
                synchronized (IsKeyPressed.class)
                {
                    switch (ke.getID())
                    {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_F)
                            {
                                //f key is pressed
                                //TODO: implement full screen
                                if(!isFullScreen)
                                    fullScreen();
                                else
                                    escapeFullScreen();
                            }
                            else if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
                            {
                                //escape key is pressed
                                //TODO: implement escape full screen
                                if(isFullScreen)
                                    escapeFullScreen();
                            }
                            else if(ke.getKeyCode() == 39)  //right arrow key code
                            {
                                //goes to next slide
                                int size = slideDeck.getSlides().size();
                                int index = slideDeck.getSlides().indexOf(currentSlide);
                                if( index < (size - 1))
                                {
                                    Slide next = SlideDeck.getSlideDeck().getSlides().get(index +1);
                                    showSlide(next);
                                }
                            }
                            else if(ke.getKeyCode() == 37) //left arrow keycode
                            {
                                //goes to previous slide

                                int index = slideDeck.getSlides().indexOf(currentSlide);
                                if( index > 0)
                                {
                                    Slide prev = SlideDeck.getSlideDeck().getSlides().get(index -1);
                                    showSlide(prev);
                                }
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_W)
                            {

                                //wPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }

    //happens when button is pressed, takes custom dimensions for an image
    public static void loadImage(ActionEvent e, int width, int height)
    {
        //opens a file explorer on the desktop
        JFileChooser fc = new JFileChooser(basePath);
        fc.setFileFilter(new ImageFileFilter());  //filters out non-image items
        int res = fc.showOpenDialog(null);
        // Image found
        try {
            if (res == JFileChooser.APPROVE_OPTION)
            {
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

    public static void updateSlideShow(Slide s, boolean isAdded)
    {
        if(isAdded)
            mainPanel.add(s, s.getSlideID());
        else
            mainPanel.remove(s);


    }

    public static BottomBar getBottomBar() {return bb;}

    public static void showSlide(@NotNull Slide s)
    {
        slideShow.show(mainPanel, s.getSlideID()); //need to provide ID so it shows the correct slide
        slideDeck.setCurrentSlide(s);
        currentSlide = s;
        bb.changeSlideButtonSelection();
        System.out.print(slideDeck.getSlides().indexOf(currentSlide));
    }

    //Supposed to keep track of last selected item for deletion
    //TODO: Use this to delete item that is selected
    public static void selectItem(JComponent item)
    {
        selectedItem = item;
    }

    /**
     * Invokes the text area creating function
     */
    public static void insertText(){
        new Text(currentSlide);
    }

    //TODO: Fehmi, provide some cool background colors to change to.
    public static void changeSlideBackground(Color c){currentSlide.setBackground(c);}

    /*
       * Turns On/Off Slide Number at bottom right corner
     */
    public static void  toggleSlideNumber()
    {

        boolean b = currentSlide.getSlideNumberState();

        if(b)
        {

            currentSlide.removeSlideNumber();
        }
        else
        {

            currentSlide.showSlideNumber(mainFrame.getSize());
        }



    }




    public static void saveAsProject() {
        // TODO : PLEASE DO THIS ASAP
        // TODO: Preferably have this call a method from the SlideDeck class
        System.out.println("Tester: Project should be saved.");
    }

    /**
     * Loads a previous project from your computer storage
     */
    public static void loadFromComputer() {
        // TODO : PLEASE DO THIS ASAP
    }

    /**
     * Adds a new slide to the slide deck and displays it
     */
    public static void newSlide() {
        slideDeck.addSlide();
    }

    /**
     * Tells the cursor follower to paint the canvas if dragged
     */
    public static void draw(){
        currentSlide.setBrushColor();
        currentSlide.setActivated(true);
    }

    /**
     * Tells the cursor follower to not paint the canvas if dragged
     */
    public static void stopDrawing() {
        currentSlide.setActivated(false);
    }

    /**
     * Sets the color of the brush to the background color which should work as an eraser
     */
    public static void eraser() {
        currentSlide.setBrushColor(currentSlide.getBackground());
        currentSlide.setActivated(true);
    }

    public static Slide getCurrentSlide()
    {
        return currentSlide;
    }


    public static void fullScreen()
    {
        mainFrame.remove(tb);
        mainFrame.remove(bb);
        //mainFrame.setUndecorated(true); //removes the window border around it
        userLocation = mainFrame.getLocation();
        userDimensions = mainFrame.getSize();
        device.setFullScreenWindow(mainFrame);
    }

    public static void escapeFullScreen()
    {

        mainFrame.add(tb, BorderLayout.NORTH);
        mainFrame.add(bb, BorderLayout.SOUTH);
       // mainFrame.setUndecorated(false); //adds the window border around
        device.setFullScreenWindow(null);
        mainFrame.setSize(userDimensions);
        mainFrame.setLocation(userLocation);

    }


}



