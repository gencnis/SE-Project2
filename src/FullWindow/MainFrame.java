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
//import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static Utilities.ImageUtilities.setTargetImage;
import static javax.swing.JFileChooser.SAVE_DIALOG;

public class MainFrame extends JFrame implements java.io.Serializable
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
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; //get screen
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


        currentSlide.changeBGColor(Color.WHITE);
        slideDeck.addSlide();
        currentSlide.changeBGColor(Color.BLUE);
        slideDeck.addSlide();
        currentSlide.changeBGColor(Color.YELLOW);
        slideDeck.addSlide(1);
        currentSlide.changeBGColor(Color.GREEN);

        //slideDeck.removeSlide(); //removes current slide
        //slideDeck.removeSlide(0); //removes slide at index



        this.add(mainPanel, BorderLayout.CENTER);
        this.setTitle("LearningMyFriend :শেখা ও শখা ");
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);


        /**
         * Got this code from the forum post attached below please refer to it for futher explanation
         * https://stackoverflow.com/questions/16530775/keylistener-not-working-for-jpanel
         */
        ActionMap actionMap = mainPanel.getActionMap();
        InputMap inputMap = mainPanel.getInputMap(2);

        String vkF = "VK_F";
        String vkESCAPE = "VK_ESCAPE";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), vkF);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), vkESCAPE);

        actionMap.put(vkF, new KeyAction(vkF));
        actionMap.put(vkESCAPE, new KeyAction(vkESCAPE));
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

    public static void showSlide(Slide s)
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
        Text t = new Text(currentSlide);
        if(t.getTextSize() < 15){
            JOptionPane.showMessageDialog(mainFrame, "Selected text size might appear small when projected.",
                    "Text size Warning", JOptionPane.WARNING_MESSAGE);

        }
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

    public static void saveAsProject(ActionEvent e)
    {
        // TODO : PLEASE DO THIS ASAP
        // TODO: Preferably have this call a method from the SlideDeck class

        File file;
        String savePath = basePath;   //where you select to save
        String fileName = "nothingSaved";   //the file name you wish to save to
        String newProjectDir = basePath; //the folder that will be made to save the project in

        //variables ot make a separate folder for images
        Path resourcesDir = null; //the path to the resources folder that holds all images
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

        //TODO: overwriting the same file does not work right now,  I think it is this line that is doing it
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) //if selected item is folder
        {
            //get out file path from the explorer
            file = fc.getSelectedFile();

            if(file.exists() && fc.getDialogType() == SAVE_DIALOG)
            {
                int result = JOptionPane.showConfirmDialog(mainFrame, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);

                //overwrite file prompt
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        savePath = fc.getSelectedFile().getAbsolutePath();
                        fileName = fc.getSelectedFile().toString();
                        //fileName = fileName + ".ser";

                        //make a path variable to write to
                        Path pathToFolder = Path.of(savePath);
                        Path pathToFile = pathToFolder.resolve(fileName);

                        //get resources folder directory
                        String recDir = savePath ;
                        recDir = recDir.replace(".ser", "");
                        recDir = recDir + " Resources";


                        resourcesDir = Paths.get(recDir);

                        //makes sure has a resources folder if ti doesn't exist
                        if(!Files.exists(resourcesDir))
                        {

                            dirMaker = new File(String.valueOf(resourcesDir));
                            dirMaker.mkdir();
                        }
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
                // System.out.println( fileName);
                newProjectDir = savePath; //makes a project folder the same name as when your project


                fileName = fileName + ".ser";


                //make a new Folder to save the project in

                //dirMaker = new File(newProjectDir);
                //dirMaker.mkdir();

                //make a new resources folder in that project path
                String recDir = savePath + " Resources";
                resourcesDir = Paths.get(recDir);

                dirMaker = new File(String.valueOf(resourcesDir));
                dirMaker.mkdir();






            }


            //make a path variable to write to
            Path pathToFolder = Paths.get(newProjectDir);
            Path pathToFile = pathToFolder.resolve(fileName);




            try {

                //loads serialized file
                FileOutputStream fileOut =
                        new FileOutputStream(String.valueOf(pathToFile));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                //TODO: make this work with all slides
                out.writeObject(slideDeck.getSlides().size());  //make sure we know how many slides we saved

                for(Slide s : slideDeck.getSlides())
                {
                    s.setCursor(Cursor.getDefaultCursor());
                    out.writeObject(s);
                }

                out.close();
                fileOut.close();
                System.out.printf("Saved Project is saved to " + String.valueOf(pathToFile));


                //write all drawing to the Resources folder
                for (Slide s: slideDeck.getSlides())
                {
                    s.writeDrawing(resourcesDir);
                }



            } catch (IOException i)
            {
                i.printStackTrace();
            }

        } else {
            System.out.println("No Selection ");
            file = null;
        }






        System.out.println("Tester: Project should be saved.");
    }

    /**
     * Loads a previous project from your computer storage
     */
    public static void loadFromComputer()
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
        if (fc.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) //if selected item is folder
        {

            loadPath = fc.getSelectedFile().getAbsolutePath();

            //gets path to resources folder
            resourcesDir = loadPath.replace(".ser", "");
            resourcesDir = resourcesDir + " Resources";








            try
            {
                FileInputStream fileIn = new FileInputStream(loadPath);
                ObjectInputStream in = new ObjectInputStream(fileIn);

                //clear all slides and buttons from current show
                for (Slide s: slideDeck.getSlides())
                {

                    updateSlideShow(s, false);

                }
                bb.clearAllButtons();
                slideDeck.getSlides().clear();


                int deckSize = (Integer) in.readObject();
                System.out.println("New project size: " + deckSize);
                //remove all currentSlides in show


                int counter = 0;
                //add all slides back to show
                //displays final slide at end of adding. can change that later
                while(counter < deckSize)
                {
                    Slide s = (Slide) in.readObject();
                    slideDeck.addSlide(s);
                    counter++;
                }

                in.close();
                fileIn.close();

                //code for reimporting a new slideDeck
                //loads the first slide in the slide show first
                showSlide(slideDeck.getSlides().get(0));



                //TODO: load Drawn Images for each slide

                for(Slide s : slideDeck.getSlides())
                {


                    if(Files.exists(Path.of(resourcesDir.concat("\\" + s.getSlideID() + "-drawing.png"))))
                    {
                        BufferedImage drawing =  ImageIO.read(new File(String.valueOf(resourcesDir) + "\\" + s.getSlideID() + "-drawing.png"));
                        s.loadDrawing(drawing);
                    }
                    else
                    {
                        System.out.println("Drawing not Found for slide " + slideDeck.getSlides().indexOf(s) );
                    }
                }


                in.close();
                fileIn.close();



                mainFrame.revalidate();
                mainFrame.repaint();
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

    /**
     * Adds a new slide to the slide deck and displays it
     */
    public static void newSlide(String c) {
        slideDeck.setSlideColor(c);
        slideDeck.addSlide();
    }

    /**
     * Tells the cursor follower to paint the canvas if dragged
     */


    public static void draw()
    {
        currentSlide.setBrushColor();
        currentSlide.setActivated(true);
    }

    /**
     * Tells the cursor follower to not paint the canvas if dragged
     */
    public static void stopDrawing()
    {
        currentSlide.setCursor(Cursor.getDefaultCursor());;
        currentSlide.setActivated(false);
    }

    /**
     * Sets the color of the brush to the background color which should work as an eraser
     */
    //TODO: set cursor to an eraser image
    public static void eraser()
    {
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



        //Another way of doing it, I tried it and it worked smoothly
        /*
        tb.setVisible(false);
        bb.setVisible(false);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
        userLocation = mainFrame.getLocation();
        userDimensions = mainFrame.getSize();
           */
    }

    public static void escapeFullScreen()
    {

        mainFrame.add(tb, BorderLayout.NORTH);
        mainFrame.add(bb, BorderLayout.SOUTH);
       // mainFrame.setUndecorated(false); //adds the window border around
        device.setFullScreenWindow(null);

        //Another way of doing it, I tried it and it worked smoothly
        /*
        tb.setVisible(true);
        bb.setVisible(true);
        mainFrame.setSize(userDimensions);
        mainFrame.setLocation(userLocation);
        isFullScreen= false;
        */

    }

    public static void removed() {
        slideDeck.removeSlide();
    }

    private class KeyAction extends AbstractAction {
        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            escapeFullScreen();
            System.out.println(actionEvt.getActionCommand() + " pressed");
        }
    }

}



