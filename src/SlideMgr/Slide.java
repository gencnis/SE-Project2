/**
 *
 * This is the class that you would extend and then start working on your functions and methods
 *
 *
 *
 * @Author : FEHMI NEFFATI
 */

package SlideMgr;

import FullWindow.MainFrame;
import Item.Item;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;


//Manages all of the Objects in the SlideShow
<<<<<<< Updated upstream
public class Slide extends DrawingPanel {
    ArrayList<JLabel> images;
    ArrayList<JTextArea> textAreas;

    //This a test
    ArrayList<Item> items;
=======
//holds text, bullet lists, images, and inherits Drawing from base class DrawingPanel
//all slide actions are called from MainFrame methods
public class Slide extends DrawingPanel implements java.io.Serializable
{
    //Serial ID number needed for every class(unsure about that)
    private static final long serialVersionUID = -4751816768152150898L;

    ArrayList<ImgLabel> images;

    //TODO: back up images in resources folder
    transient ArrayList<JTextArea> textAreas;


   transient ArrayList<Item> items;
>>>>>>> Stashed changes

    String slideID; //unique slide identifier

    //the index of the slide in the current slide show
    JLabel slideNumber;
    boolean isSlideNumberShown = false;

<<<<<<< Updated upstream
=======
    //unnecessary variable to hold color. Delete this later
    Color bgColor;

    //TODO: background won't properly scale back in reset BG size method
    //background image variables
    JLabel backgroundImage;
    float bgAlpha = .5f;
    transient BufferedImage backGround = null;
    transient ImageIcon bgIcon = null;
    int ogWidth;
    int ogHeight;
    boolean isBGSet = false;
    Point BGLocation;

    //unused
    JPanel back;

    /**
     * initializes all lists and objects and sets the slide's identifier number
     * @param slideID unique identifier of slide
     */
>>>>>>> Stashed changes
    public Slide(Integer slideID)
    {

        this.slideID = slideID.toString();

        //slide number initializatin
        slideNumber = new JLabel();
        initializeSlideNumber(100,100);  //change the parameters to change the size of the slide number text

        images = new ArrayList<JLabel>();
        textAreas = new ArrayList<>();
        items = new ArrayList<Item>();
        setLayout(null); //necessary to make drawable


<<<<<<< Updated upstream
// TODO: Fehmi: add JButtons to take you to the next and previous slides on the sides
=======
        backgroundImage = new JLabel();

        BGLocation = new Point();

>>>>>>> Stashed changes


    }

    /**
     *  sows current order in slide deck in the bottom right corner absed on size of window
     * @param d   gets size of slide so it displays in bottom corner proportionarely
     */
    public void showSlideNumber(Dimension d)
    {

        slideNumber.setLocation((int)(d.width * .93), (int)(d.height * .6));

        add(slideNumber);

        revalidate();
        repaint();
        isSlideNumberShown = true;
    }

    /**
     * removes number from corner of slide
     */
    public void removeSlideNumber()
    {
        remove(slideNumber);
        revalidate();
        repaint();
        isSlideNumberShown = false;
    }

    //checks if slides are shown or not for this slide
    public boolean getSlideNumberState()
    {
        return isSlideNumberShown;
    }

    //updates current standing in slide show and displays the correct number of this side's order in the show
    public void upDateSlideNumber()

    {
        Integer number = SlideDeck.getSlideDeck().getSlides().indexOf(this);
        number++;

        Integer  totalSlides =  SlideDeck.getSlideDeck().getSlides().size();
        slideNumber.setText(number.toString() + " / " + totalSlides.toString());
    }

    //resizes, recolors, and sets location of slide number to begin with
    void initializeSlideNumber(int width, int height)
    {
        slideNumber.setSize(width,height); //necessary because null layout of slide makes location go wonky otherwise
        slideNumber.setLocation(50, 20);
        slideNumber.setForeground(Color.WHITE);

        //make number displayable
        DraggableHandler handler = new DraggableHandler(slideNumber, this);
        slideNumber.addMouseListener(handler);
        slideNumber.addMouseMotionListener(handler);
        slideNumber.revalidate();
        slideNumber.repaint();
        //end slide number intialization


        //the rest of this code is for making sure the text is set to fit inside the size of the set container
        Font labelFont = slideNumber.getFont();
        String labelText = slideNumber.getText();

        int stringWidth = (int)(slideNumber.getWidth() * .9);
        int componentWidth = slideNumber.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = slideNumber.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        slideNumber.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

    //run this to add an image from the button action
    //makes a ImgLabel, adds it to reference list
    //this is called from ImageUtilities after resizing and formatting the image for use with ImgLabels
    public void addImageToSlide(ImageIcon icon)
    {
        JLabel imgLabel = new JLabel(icon);


        imgLabel.setSize(100, 100);
        imgLabel.setBackground(Color.yellow);

        images.add(imgLabel);
        add(imgLabel);

        //makes things draggable
        DraggableHandler handler = new DraggableHandler(imgLabel, this);
        imgLabel.addMouseListener(handler);
        imgLabel.addMouseMotionListener(handler);
        imgLabel.revalidate();
        imgLabel.repaint();
        //end makes things draggable
    }

    //deletes everything on a slide
    public void clearSlide()
    {
        images.clear();
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    public String getSlideID(){return slideID;}


    /**
     * This is to keep the text in this current slide, it is being called from the Class Text.java
     * @param s is a text area
     * @param t is the Item we're passing, no matter what it might be (picture, etc) it gets saved and added
     */
    public void addText(JTextArea s, Item t){
        s.setBounds(50, 50, 150, 150);

        //textAreas.add(s); // intial list i added to
        items.add(t);      // to test out the arrayList of items theory
        add(s);
        System.out.println("Text should be inserted");
<<<<<<< Updated upstream
=======
        repaint();
    }

    /**
     * adds a bullet list to the slide
     * @param s  the text area that will use a bullet list
     * @param t     the item that refers to the bullet list
     */
    public void addBulletList(JTextArea s, Item t){
        s.setBounds(50, 50, 150, 150);
>>>>>>> Stashed changes

    }

<<<<<<< Updated upstream
=======
    /**
     * Saves an Image to the resources folder. Names it specifically with each slideID numbers for easy retrieval
     *
     * @param p             the directory path to which the image is to be saved
     * @throws IOException  if the path isn't found throws this
     */
    public void writeDrawing(Path p) throws IOException
    {
        //TODO:include slideID with drawing so can easily sort through the images
        String path = String.valueOf(p) + "\\" + slideID + "-drawing.png";
       // System.out.println(path);
        if(drawnImage != null)
            ImageIO.write(drawnImage, "png", new File(path));
    }


    /**
     * Takes an icon file to set as the background of the slide.
     * Background is just a large slide-sized jLabel with an image loade into it
     * @param icon the processed image to be set as the backgroung
     */
    //called from ImageUtilities after resizing the image and setting alpha transparency values
    public void loadBackgroundImage(ImageIcon icon)
    {

        bgIcon = icon;
        backgroundImage.setIcon(icon);


       backgroundImage.setSize(this.getWidth(), this.getHeight());

       //doesn't work, want to watch window size dynamically
        backgroundImage.addComponentListener(new ComponentAdapter() {

                                                 @Override
                                                 public void componentResized(ComponentEvent e) {
                                                     int w = getWidth();
                                                     int h = getHeight();
                                                     backgroundImage.setSize(w, h);
                                                 }
                                             });


        add(backgroundImage);

       /* Graphics2D g2 = (Graphics2D) getGraphics();
        Composite old = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
        //float x = (getWidth() - backgroundImage.getWidth())/2;
        //float y = (getHeight()- backgroundImage.getHeight())/2;
        g2.drawRenderedImage((RenderedImage) backgroundImage, AffineTransform.getTranslateInstance(getWidth(), getHeight()));
        g2.setComposite(old);*/

        if(!isBGSet)
        {
            this.setComponentZOrder(backgroundImage, this.getComponentCount() - 1);
            isBGSet = true;
        }


        backgroundImage.setOpaque(false);
        getBGOriginalMeasurements();
       backgroundImage.revalidate();
       backgroundImage.repaint();
        this.revalidate();
        this.repaint();
    }

    //makes background image big enough to cover full screen sldie
    public void presentBGSize()
    {
        if(backGround != null)
        {

            //may reduce alpha over time...
            ImageUtilities.setTargetBackground(backGround, this);
        }

    }
    //broken
    //resets background to be the same location as it was originally
    public void resetBGPosition()
    {
        //backgroundImage = new JLabel();
       backgroundImage.setLocation(BGLocation.x,BGLocation.y);
    }

    //unused
    //resets background size to be the same as original window
    public void resetBGSize()
    {



       // backgroundImage.setSize(getWidth(), getHeight());
        ImageUtilities.setTargetBackground(backGround,  this);



    }

    //doesn't work
    //sets default window size dimensions for background image to be scaled back to
    public void getBGOriginalMeasurements()
    {
        ogHeight = getHeight();
        ogWidth = getWidth();

    }

    //probablu broken
    //updates the background reference with a new image
    //used when resizing and alpha setting in ImageUtilities
    public void setBackGround(BufferedImage bi){backGround = bi;}

    //unneeded
    //changes slide Color and keeps a reference of color
    public void changeBGColor(Color color)
    {
        bgColor = color;
        this.setBackground(color);
    }
>>>>>>> Stashed changes


}




