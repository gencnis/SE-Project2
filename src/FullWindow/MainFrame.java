/**
 *
 * This is the full window that contains everything
 *
 * @Author: Fehmi Neffati
 *
 */

package FullWindow;

import BottomNavigation.BottomBar;
import Item.BulletList;
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


import static Utilities.ImageUtilities.setTargetBackground;
import static Utilities.ImageUtilities.setTargetImage;
import static javax.swing.JFileChooser.SAVE_DIALOG;
import java.awt.event.KeyEvent;


/*** Main Frame Class **********************
 *PURPOSE: Handles display of slideshow and routes GUI button calls to currently displayed slides.
 *
 * Supported Operations on Slideshow:
 * Add Current Displayed Slide, Remove Current Displayed Slide,
 * Load Images, Text, and Bullet Lists to Displayed Slide,
 * Save Slideshow as a Project or Save a single slide to be used as a template slide to be load layout to other slides
 *
 * Parameters:
 * ********Slide Show Display Variables***************
 * slideDeck - master instance of the slideDeck
 * slideShow - cardLayout that manages display of slides over one particular area on screen
 * mainPanel - main JComponent that holds all content for the program
 * BottomBar - GUI for the bottom bar that allows for slide selection
 * TopBar - GUI element along top of program window that allows for different user actions like adding and saving
 * currentSlide - currently displayed slide on the screen
 * mainFrame - variable to hold instance of self. Important for making calls to self
 *
 * ******Image Loading Variables*******************
 * baseSize - default Image Size variable, can add in custom values in later builds
 * home && basePath - gets a file directory for the default home directory, which is usually the Desktop
 * selectedItem - **UNUSED** was to hold a reference of the last clicked item for deletion purposes
 *
 * *****FullScreen Variables*********
 * UserDimension - holds the height and width of the user's computer screen
 * userLocation - holds location of the program on the screen **TODO: Fix scaling for fullscreen with this**
 * device - gets the user's screen
 * isFullScreen - checks for full screen
 * currentBulletList - **UNUSED** holds focus onto the bullet list and allows you to type on the currently focues on and selected list
 * isTyping - **UNUUSED** checks if user is typing to prevent fullscreen from pressing "F" key.
 */


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
    static BulletList currentBulletList;
    static Dimension windowSize;

    static boolean isTyping = false;

    /**Constructor of Main Cotent Window:
     *
     * Sets up the GUI and slideshow layout and adds 3 dummy slides for an example slideshow
     *
     * Author: Robert and Fehmi
     */
    MainFrame()
    {
        slideShow = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(slideShow);



         tb = new TopBar(this);
        this.add(tb, BorderLayout.NORTH);

        bb = new BottomBar(slideShow, mainPanel);
        this.add(bb, BorderLayout.SOUTH);





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
         * ActionMap Author: Fehmi
         *
         * Sets up a table of key presses that can be used for listening for key presses
         *
         * Got this code from the forum post attached below please refer to it for futher explanation
         * https://stackoverflow.com/questions/16530775/keylistener-not-working-for-jpanel
         */

        ActionMap actionMap = mainPanel.getActionMap();
        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);



        String vkF = "VK_F";
        String vkESCAPE = "VK_ESCAPE";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), vkF);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), vkESCAPE);


        actionMap.put(vkF, new KeyAction(vkF));
        actionMap.put(vkESCAPE, new KeyAction(vkESCAPE));

    }

    /**MAIN METHOD
     *
     * Instantiates main content panel.
     */
    public static void main(String[] args)
    {

        mainFrame = new MainFrame();

    }



    /**Happens when load image button is pressed, takes custom dimensions for an image
    * User selects an image from file explorer and set the size (otherwise baseSize is used here)
     * Moves to ImageUtilities.setTargetImage() - for resizing and conversion to Icon for use in ImgLabel container variable
    *TODO: scaling has not been implemented. Need to create backup images to reference rescale functions
     *
     *
     * @param e --takes button pressed action from GUI. unused for anything in method
     * @param width - desired width of object on screen TODO: Implement custom image size selection
     * @param height - desired height of object on screen
     *
     * Author: Robert
     */
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
                setTargetImage(file, width, height,  currentSlide);  //loads up the image file for us to use
            }
            //Image Not Found
            else {
                JOptionPane.showMessageDialog(null,
                        "An JPEG or PNG file must be selected. \nCancelling Action.", "Invalid File Chosen",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception iOException)
        {
        }

    }



    /** **NONWORKING**
     * User selects an image from File Explorer to load to the background of current slide.
     **Has preset reduced opacity to make it less obtrusive
     *
     * *******ISSUES:  ******
     * Background does not scale with slide when program window changes size.
     *
     * Uncomment line in TopBar next to load background to see functioning somewhat.
     *
     * @param e - Action event from when GUI button is pressed. Unused in method
     *
     *  Author: Robert
     */
    public static void loadBackground(ActionEvent e)
    {
        JFileChooser fc = new JFileChooser(basePath);
        fc.setFileFilter(new ImageFileFilter());  //filters out non-image items
        int res = fc.showOpenDialog(null);
        // Image found
        try {
            if (res == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                setTargetBackground(file, slideDeck.getCurrentSlide());  //loads up the image file for us to use
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

    //unimplemented: Has bug when scaling back from fullscreen
    //Uncomment the menu item in top bar to get it to run in program
    //loads up a new Background

    /****NONWORKING ********
     * Takes bufferedImage and loads to background of current slide.
     * Used when loading project from file and uses resources folder to load images back up.
     * Calls ImageUtilities.setTargetBackground() to rescale and set image alpha values before loading onto current slide.
     *
     * @param bufferedImage -Image files are held as BufferedImages, which can be resized later for use as background
     *
     * Author: Robert
     */
    public static void loadBackground(BufferedImage bufferedImage)
    {

                setTargetBackground(bufferedImage, slideDeck.getCurrentSlide());  //loads up the image file for us to use

    }



    /**Adds or removes slide from cardLayout (i.e. the thing that controls displaying slides on screen)
     *
     *
     * @param s  -the slide that we wish to add or remove from the cardLayout
     * @param isAdded - if true, adds slide to cardLayout for display, if false, removes from cardLayout
     *
     * Author: Robert
     */
    public static void updateSlideShow(Slide s, boolean isAdded)
    {
        if(isAdded)
            mainPanel.add(s, s.getSlideID());
        else
            mainPanel.remove(s);


    }




    /**Displays the provided slide on screen
     *
     * @param s -- the slide that is to be displayed on the screen
     *
     * Author: Robert
     */
    public static void showSlide(Slide s)
    {
        slideShow.show(mainPanel, s.getSlideID()); //need to provide ID so it shows the correct slide
        slideDeck.setCurrentSlide(s);
        currentSlide = s;
        bb.changeSlideButtonSelection();
        System.out.print(slideDeck.getSlides().indexOf(currentSlide));
    }



    /** **UNUSED / NOT IMPLEMENTED****
     * Supposed to keep track of last selected item for deletion
     *TODO: Use this to delete item that is selected
     *
     * @param item - refers to which item is currently selected
     */
    public static void selectItem(JComponent item)
    {
        selectedItem = item;
    }

    /**
     * Invokes the text area creating function
     * Displays an option panel to select text size and warns the user if the size is too small i.e. less than 16
     *
     * Author: Fehmi
     */
    public static void insertText()
    {
        Text t = new Text(currentSlide);
        if(t.getTextSize() < 15){
            JOptionPane.showMessageDialog(mainFrame, "Selected text size might appear small when projected.",
                    "Text size Warning", JOptionPane.WARNING_MESSAGE);

        }
    }


    /**Changes current slide's background when creating it with Add Painted Slide
     *
     * @param c - target color to change the slide to
     *
     * Author: Fehmi
     */
    public static void changeSlideBackground(Color c){currentSlide.setBackground(c);}

    /**
       * Turns On/Off Slide Number at bottom right corner of current slide
       *
       * Author: Robert
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



    /** Gets a save directory from user and makes a .ser file and resources folder in that directory.
     *writes each slide to the ser file and saves out the drawn background images to the resources folder
     *
     *TODO: Fix Overwriting existing Files
     * TODO: Add slide images to things that need to be saved to resource folder to allow for resizing in laoded project
     *
     * @param e - Action event from button press. Not used in method.
     *
     * VARIABLES:**********
     * file - the file that will be written to
     * savePath - directory where User wishes to save. Desktop by default
     * fileName - What the user wants to name the project - nothingSaved by default
     * newProjectDir - **UNUSED*** tried to save everything into a new folder. Did not work
     * resourcesDir - the directory for the Resources folder, holds all images for use when loading projects
     *   dirMaker - makes new  folders  for project
     *
     * fc - opens a file explorer window
     *
     * Author: Robert
     */
    public static void saveAsProject(ActionEvent e)
    {


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
        JOptionPane.showMessageDialog(mainFrame, "A project has been saved.",
                "Information", JOptionPane.WARNING_MESSAGE);
    }


    /**Opens a file explorer for User to select a .ser file.
     * Loads a previous project from User's computer storage
     * Loads a ser file and loads drawn backgrounds for each slide from adjacent resources folder
     *
     * Variables***
     * file - the file we are opening
     * loadPath - the directory of the .ser file
     * resourcesDir - the directory of the resources folder, automatically finds the adjacent resources folder
     * fc - file explorer window
     *
     * Author: Robert
     */
    public static void loadFromComputer()
    {
        File file;
        String loadPath = basePath;
        String resourcesDir = basePath;



        //opens a file explorer on the desktop
        JFileChooser fc = new JFileChooser(basePath);
        fc.setCurrentDirectory(new java.io.File(basePath));
        fc.setDialogTitle("Select Folder To Load");

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
        JOptionPane.showMessageDialog(mainFrame, "A Project has been loaded successfully.",
                "Information", JOptionPane.WARNING_MESSAGE);
    }




    /**Opens a file explorer for the user to select a save directory.
     * Saves a single slide as a .template file
     * Can load the single slide to any slide in the show to have the same layout
     * Saves drawn image in template resources folder *SOMETIMES DOES NOT WORK**
     *
     * @param e - Action event from button press. Unused in method.
     *
     * Variables**
     *  file - the file we are saving to
     *  savePath - the directory the user desires to save in
     *  fileName - the name of the project chosen by the user* newProjectDir - **UNUSED*** tried to save everything into a new folder. Did not work
     *  resourcesDir - the directory for the Resources folder, holds all images for use when loading projects
     *  dirMaker - makes new  folders  for project
     *  fc - opens a file explorer window
     *
     *  Author: Robert
     */
    public static void saveTemplate(ActionEvent e)
    {
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

        fc.setFileFilter(new TemplateFileFilter());
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
                        recDir = recDir.replace(".template", "");
                        recDir = recDir + " Template Resources";


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


                fileName = fileName + ".template";


                //make a new resources folder in that project path
                String recDir = savePath + " Template Resources";
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




                //only saves out current slide
                out.writeObject(currentSlide);


                out.close();
                fileOut.close();
                System.out.println("Saved Project is saved to " + String.valueOf(pathToFile));


                //write all drawing to the Resources folder

                currentSlide.writeDrawing(resourcesDir);




            } catch (IOException i)
            {
                i.printStackTrace();
            }

        } else {
            System.out.println("No Selection ");
            file = null;
        }

        System.out.println("Template was saved");

        JOptionPane.showMessageDialog(mainFrame, "A Template has been saved.",
                "Information", JOptionPane.WARNING_MESSAGE);

    }



    /**Opens a file exploerer window for the user to select a .template file to load onto the currently selected slide.
     * loads corresponding drawn image from template resources folder for further drawing
     *
     * @param e - GUI button press event . Not used in thi melody
     *
     *Author: Robert
     */
    public static void loadTemplate(ActionEvent e)
    {
        File file;
        String loadPath = basePath;
        String resourcesDir = basePath;
        String fileName = "nothingSaved";


        //opens a file explorer on the desktop
        JFileChooser fc = new JFileChooser(basePath);
        fc.setCurrentDirectory(new java.io.File(basePath));
        fc.setDialogTitle("Select Folder To Load");

        fc.setFileFilter(new TemplateFileFilter());
        //
        // disable the "All files" option.
        //
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) //if selected item is folder
        {

            loadPath = fc.getSelectedFile().getAbsolutePath();

            //gets path to resources folder
            resourcesDir = loadPath.replace(".template", "");
            resourcesDir = resourcesDir + " Template Resources";








            try
            {
                FileInputStream fileIn = new FileInputStream(loadPath);
                ObjectInputStream in = new ObjectInputStream(fileIn);

                Integer index = slideDeck.getSlides().indexOf(currentSlide);

                //removes current slide and loads a new one in place
                slideDeck.removeSlide(index);

                Slide temp = (Slide) in.readObject();
                slideDeck.addSlide(temp, index);





                in.close();
                fileIn.close();

                //code for reimporting a new slideDeck
                //loads the first slide in the slide show first
                //showSlide(slideDeck.getSlides().get(0));



                //TODO: load Drawn Images for each slide


                    //current slide is newly added slide
                    if(Files.exists(Path.of(resourcesDir.concat("\\" + currentSlide.getSlideID() + "-drawing.png"))))
                    {
                        BufferedImage drawing =  ImageIO.read(new File(String.valueOf(resourcesDir) + "\\" + currentSlide.getSlideID() + "-drawing.png"));
                        currentSlide.loadDrawing(drawing);
                    }
                    else
                    {
                        System.out.println("Drawing not Found for slide " + slideDeck.getSlides().indexOf(currentSlide) );
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
        JOptionPane.showMessageDialog(mainFrame, "A Template has been loaded.",
                "Information", JOptionPane.WARNING_MESSAGE);
    }



    /**Adds a new slide to the slide deck and displays it
     *
     * @param c - string value for which color to select as the background color. White is default
     *
     *Author: Robert
     */
    public static void newSlide(String c) {
        slideDeck.setSlideColor(c);
        slideDeck.addSlide();
        JOptionPane.showMessageDialog(mainFrame, "A new Slide has Been Added.",
                "Information", JOptionPane.WARNING_MESSAGE);
    }



    /** Tells the cursor follower to paint the canvas if dragged
     * Works with DrawingPanel.java file that is inherited by all slides
     *
     * Author: Fehmi
     */
    public static void draw()
    {
        currentSlide.setBrushColor();
        currentSlide.setActivated(true);

    }

    /**
     * Tells the cursor follower to not paint the canvas if dragged
     *  Works with DrawingPanel.java file that is inherited by all slides
     *
     *  Author: Fehmi
     */
    public static void stopDrawing()
    {
        currentSlide.setCursor(Cursor.getDefaultCursor());;
        currentSlide.setActivated(false);
    }

    /**
     * Sets the color of the brush to the background color which should work as an eraser
     *
     * Author: Fehmi
     */
    //TODO: set cursor to an eraser image
    public static void eraser()
    {
       currentSlide.setBrushColor(currentSlide.getBackground());
       currentSlide.eraser();
       currentSlide.setActivated(true);
    }

    /**
        *Returns currently displayed slide in the slide show
     */
    public static Slide getCurrentSlide()
    {
        return currentSlide;
    }

    /** Makes slideshow fullscreen
    *
    * Author: Fehmi
    */
    public static void fullScreen()
    {
        /*for (Slide s: slideDeck.getSlides())
        {
          //  s.getBGOriginalMeasurements();
        }*/
        windowSize = mainFrame.getSize();
        tb.setVisible(false);
        bb.setVisible(false);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
        userLocation = mainFrame.getLocation();
        userDimensions = mainFrame.getSize();
        isFullScreen = true;

       /* for (Slide s: slideDeck.getSlides())
        {
           // s.presentBGSize();
        }*/
        JOptionPane.showMessageDialog(mainFrame, "To exit full screen please press ESC button.",
                "Information", JOptionPane.WARNING_MESSAGE);
    }

    /**returns slideshow to original window size
     *
     * Author: Fehmi
     */
    public static void escapeFullScreen()
    {
        /* for (Slide s: slideDeck.getSlides())
        {
          // s.resetBGPosition();
        }*/

        tb.setVisible(true);
        bb.setVisible(true);
        mainFrame.setSize(userDimensions);
        mainFrame.setLocation(userLocation);
        isFullScreen= false;
        mainFrame.setSize(windowSize);


          /*  for (Slide s : slideDeck.getSlides())
            {

               // s.resetBGSize();
            }*/

        ;


    }



    /**
     * This is what connects key strokes to methods. I.e. FullScreen and ExitFullScreen
     *
     * Author: Fehmi
     */
    public class KeyAction extends AbstractAction {
        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt)
        {
            if(actionEvt.getActionCommand().equals("VK_ESCAPE"))
            {
                if (isFullScreen)
                {
                    escapeFullScreen();
                }
                else if(currentBulletList != null)
                    currentBulletList.addBullet();
            }
            if(actionEvt.getActionCommand().equals("VK_ESCAPE"))
            {

            }


        }
    }

    /**
     * This method is the same as the TextArea method, the difference here is that it will have a key listener for the Enter key
     * that way each time the user hits enter, it goes back to line and adds a bullet
     *
     * Author: Fehmi
     */

    public static void bullet() {
        currentBulletList = new BulletList(currentSlide);
        if(currentBulletList.getTextSize() < 15){
            JOptionPane.showMessageDialog(mainFrame, "Selected text size might appear small when projected.",
                    "Text size Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Removes current  slide by accessing the SlideDeck
     *
     * Author: Fehmi
     */
    public static void removed() {
        slideDeck.removeSlide();
        JOptionPane.showMessageDialog(mainFrame, "A slide has been removed.",
                "Information", JOptionPane.WARNING_MESSAGE);
    }

    public static BottomBar getBottomBar() { return bb;}

    public static GraphicsDevice getDevice()
    {
        return device;
    }

    public static void setIsTyping(boolean b)
    {
        System.out.println("focus changed");
        isTyping = b;
    }
}



