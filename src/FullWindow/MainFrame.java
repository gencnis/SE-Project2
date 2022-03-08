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
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;


import static Utilities.ImageUtilities.setTargetImage;
import static javax.swing.JFileChooser.SAVE_DIALOG;

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

  /*  protected void save(ActionEvent e)
    {
        File file;
        String savePath = basePath;   //where you select to save
        String fileName = "nothingSaved";   //the file name you wish to save to
        String newProjectDir = basePath; //the folder that will be made to save the project in

        //variables ot make a separate folder for images
        Path resourcesDir; //the path to the resources folder that holds all images
        File dirMaker; //makes new project folders

        //opens a file explorer on the desktop
        JFileChooser fc = new JFileChooser(basePath);
        fc.setCurrentDirectory(new java.io.File(basePath));
        fc.setDialogTitle("Select Folder To Save");

        fc.setFileFilter(new ProjectFileFilter());
        //
        // disable the "All files" option.
        //
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) //if selected item is folder
        {
            //get out file path from the explorer
            file = fc.getSelectedFile();

            if(file.exists() && fc.getDialogType() == SAVE_DIALOG)
            {
                int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);

                //overwrite file prompt
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        savePath = fc.getSelectedFile().getAbsolutePath();
                        fileName = fc.getSelectedFile().toString();
                        //fileName = fileName + ".ser";

                        //make a path variable to write to
                        Path pathToFolder = Path.of(savePath);
                        Path pathToFile = pathToFolder.resolve(fileName);
                        return;
                    case JOptionPane.NO_OPTION:
                        return;
                    case JOptionPane.CLOSED_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                        //cancelSelection();
                        return;
                }
            }
            else
            {
                savePath = fc.getSelectedFile().getAbsolutePath();

                fileName = fc.getSelectedFile().toString();
                System.out.println( fileName);
                newProjectDir = savePath; //makes a project folder the same name as when your project


                fileName = fileName + ".ser";


                //make a new Folder to save the project in

                //dirMaker = new File(newProjectDir);
                //dirMaker.mkdir();

                //make a new resources folder in that project path
                String recDir = savePath + " sResources";
                resourcesDir = Paths.get(recDir);

                dirMaker = new File(String.valueOf(resourcesDir));
                dirMaker.mkdir();






            }


            //make a path variable to write to
            Path pathToFolder = Paths.get(newProjectDir);
            Path pathToFile = pathToFolder.resolve(fileName);



//TODO: back up images to save here
            try {

                //loads serialized file
                FileOutputStream fileOut =
                        new FileOutputStream(String.valueOf(pathToFile));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                //TODO: make this work with all slides
                out.writeObject(s);
                out.close();
                fileOut.close();
                System.out.printf("Saved Project is saved to " + String.valueOf(pathToFile));
            } catch (IOException i) {
                i.printStackTrace();
            }

        } else {
            System.out.println("No Selection ");
            file = null;
        }


    }


    protected void load(ActionEvent e)
    {
        File file;
        String loadPath = basePath;
        String resourcesDir = basePath;
        String fileName = "nothingSaved";

        //opens a file explorer on the desktop
        JFileChooser fc = new JFileChooser(basePath);
        fc.setCurrentDirectory(new java.io.File(basePath));
        fc.setDialogTitle("Select Folder To Save");

        fc.setFileFilter(new ProjectFileFilter());
        //
        // disable the "All files" option.
        //
        fc.setAcceptAllFileFilterUsed(false);
        //fc.showSaveDialog(null);
        //
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) //if selected item is folder
        {

            loadPath = fc.getSelectedFile().getAbsolutePath();

            resourcesDir = loadPath + "Resources";







            try {
                FileInputStream fileIn = new FileInputStream(loadPath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                //TODO: Make this work for Arraylist of slides
                Slide slide = (Slide) in.readObject();
                in.close();
                fileIn.close();
                s = slide;
                add(s, BorderLayout.CENTER);
                revalidate();
                repaint();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Employee class not found");
                c.printStackTrace();
                return;
            }

        } else {
            System.out.println("No Selection ");
            file = null;
        }

    }





    protected void clear(ActionEvent e)
    {
        remove(s);
        revalidate();
        repaint();
    }*/

}



