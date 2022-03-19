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

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import static Utilities.ImageUtilities.setTargetImage;

<<<<<<< Updated upstream
public class MainFrame extends JFrame
=======
/**
 * MainFrame is the main content window of the whole application
 * It manages calls from the GUI and relays them to the slides that it manages
 */
public class MainFrame extends JFrame implements java.io.Serializable
>>>>>>> Stashed changes
{
    static SlideDeck slideDeck; //main slide deck holding all slides

    //slideshow display
    static CardLayout slideShow; //holds all the slides currently in the deck to display
    static JPanel mainPanel; //displays all slides
    static BottomBar bb;
    static Slide currentSlide;

    static MainFrame mainFrame; //self


<<<<<<< Updated upstream
//Image loading variables
    private static final int baseSize = 128; //default image size variable, can add in our own to resize the image
=======
    //Image loading variables
    private static final int baseSize = 128; //default image size variable, can add in our own to resize the image(unimplemented)
>>>>>>> Stashed changes

    //get your desktop in most cases as a default directory
    static File home = FileSystemView.getFileSystemView().getHomeDirectory();
    static String basePath = home.getAbsolutePath();
//Image Loading variables

//Element Editing Variables
    static JComponent selectedItem = null;

<<<<<<< Updated upstream


    MainFrame() throws InterruptedException
    {

=======
    //maintain user's window size and location
    static Dimension userDimensions;
    static Point userLocation;
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; //get screen resolution
    static boolean isFullScreen = false;

    static Dimension windowSize;

    static BulletList currentBulletList;

    //unused. Manages if user is typing in a text box
    static boolean isTyping = false;

    MainFrame() throws InterruptedException
    {
        //set up all gui elements
>>>>>>> Stashed changes
        slideShow = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(slideShow);


        TopBar tb = new TopBar(this);
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

        //use this when you want to display a different slide
        //For the second parameter:
        //use the slide.getSlideID for their unique ID code. This is not always the index in the arraylist.





//image loading Button

       // JPanel top = new JPanel();
       ///JButton  addImg = new JButton("Add Img");
        //addImg.addActionListener(new ActionListener() {
            //put in whatever dimensions you want
          /*  public void actionPerformed(ActionEvent e) {
                loadImage(e, baseSize, baseSize);
            }
        });*/
        //top.add(addImg);


<<<<<<< Updated upstream
//uncomment both lines for demonstration. this will overwrite the top current layout for demo purposes
        //add(top, BorderLayout.NORTH);
        //add(s, BorderLayout.CENTER);


//image loading button
=======
        String vkF = "VK_F";
        String vkESCAPE = "VK_ESCAPE";
        String vkRETURN = "VK_RETURN";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), vkF);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), vkESCAPE);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), vkRETURN);


        actionMap.put(vkF, new KeyAction(vkF));
        actionMap.put(vkESCAPE, new KeyAction(vkESCAPE));
        actionMap.put(vkRETURN, new KeyAction(vkRETURN));
>>>>>>> Stashed changes

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

        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

//   TODO: Call this with whatever your menu listener is and it will do all the stuff for you.
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
        currentSlide = s;
        System.out.print(slideDeck.getSlides().indexOf(currentSlide));
    }

    public static void selectItem(JComponent item)
    {
        selectedItem = item;
    }

    public static void insertText(){
        new Text(currentSlide);
        System.out.println("MainFrame Text");
    }

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

<<<<<<< Updated upstream
=======
    //gets a save directory from user and makes a .ser file and resources folder.
    //writes each slide to the ser file and saves out the drawn background image to the resources folder
    //overwriting does not work right now.
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



>>>>>>> Stashed changes



    public static void saveAsProject() {
        // TODO : PLEASE DO THIS ASAP
        System.out.println("Tester: Project should be saved.");
    }
<<<<<<< Updated upstream
=======

    /**
     * Loads a previous project from your computer storage
     * Loads a ser file and loads drawn backgrounds for each slide from adjacent resources folder
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



                //saves background drawing for each slide
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


    //saves a single slide as a .template file
    //can load the single slide to any slide in the show to have the same layout
    //saves drawn image in template resources folder
    //overwriting does not work right now
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

    }

    //loads a .template file to a slide layout
    //loads corresponding drawn image from template resources folder for further drawing
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
     * Works with DrawingPanel.java file that is inherited by all slides
     */
    public static void draw()
    {
        currentSlide.setBrushColor();
        currentSlide.setActivated(true);
    }

    /**
     * Tells the cursor follower to not paint the canvas if dragged
     *  Works with DrawingPanel.java file that is inherited by all slides
     */
    public static void stopDrawing()
    {
        currentSlide.setCursor(Cursor.getDefaultCursor());;
        currentSlide.setActivated(false);
    }

    /**
     * Sets the color of the brush to the background color which should work as an eraser
     */
    public static void eraser()
    {
       currentSlide.setBrushColor(currentSlide.getBackground());
       currentSlide.eraser();
       currentSlide.setActivated(true);
    }

    /**
        *returns currently displayed slide in the slide deck
     */
    public static Slide getCurrentSlide()
    {
        return currentSlide;
    }

    //makes slideshow fullscreen
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

    }

    //returns slideshow to original window size
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
     * This is what connects our key strokes to the actual mehtods we have
     */
    public class KeyAction extends AbstractAction
    {
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
            if(actionEvt.getActionCommand().equals("VK_RETURN"))
                {}



        }
    }

    /**
     * This method is the same as the TextArea method, the difference here is that it will have a key listener for the Enter key
     * that way each time the user hits enter, it goes back to line and adds a bullet
     */

    public static void bullet() {
        currentBulletList = new BulletList(currentSlide);
        if(currentBulletList.getTextSize() < 15){
            JOptionPane.showMessageDialog(mainFrame, "Selected text size might appear small when projected.",
                    "Text size Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Removes slide by accessing the SlideDeck
     */
    public static void removed() {
        slideDeck.removeSlide();
    }

    //used by SlideDeck
    public static BottomBar getBottomBar() { return bb;}


//unused. Checks if user is typing in Text area
    public static void setIsTyping(boolean b)
    {
        System.out.println("focus changed");
        isTyping = b;
    }
>>>>>>> Stashed changes
}



