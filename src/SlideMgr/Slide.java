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
import Utilities.ImageUtilities;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


/**Basic Component of the slideShow.
 * Inherits from DrawingPanel class to allow for drawing on Backgrounds.
 * Has operations for adding text, images, and bulletLists
 *
 * Author: Robert
 */
public class Slide extends DrawingPanel implements java.io.Serializable
{
    //Serial ID number needed for every class...at least this class. It didn't work without this.
    private static final long serialVersionUID = -4751816768152150898L;

    ArrayList<ImgLabel> images;


    transient ArrayList<JTextArea> textAreas;

    //This a test
   transient ArrayList<Item> items;

    String slideID;

    //manages if slide number is displayed or not
    JLabel slideNumber;
    boolean isSlideNumberShown = false;

    //unnecessary variable to hold color. Delete this later
    Color bgColor;

    JLabel backgroundImage;
    float bgAlpha = .5f;
    transient BufferedImage backGround = null;
    transient ImageIcon bgIcon = null;
    int ogWidth;
    int ogHeight;
    boolean isBGSet = false;
    Point BGLocation;

    JPanel back;

    /**Constructor - gives new slide its own unique ID number
     * Makes lists for each component type (i.e text, images) for easy reference when saving.
     *
     * @param slideID
     */
    public Slide(Integer slideID)
    {

        this.slideID = slideID.toString();

        //slide number initializatin
        slideNumber = new JLabel();
        initializeSlideNumber(100,100);  //change the parameters to change the size of the slide number text

        images = new ArrayList<ImgLabel>();
        textAreas = new ArrayList<>();
        items = new ArrayList<Item>();
        setLayout(null);


        backgroundImage = new JLabel();

        BGLocation = new Point();

        back = new JPanel();
        back.setSize(getWidth(), getHeight());

        back.addFocusListener(new FocusListener()
        {


            @Override
            public void focusGained(FocusEvent e)
            {
                MainFrame.setIsTyping(false);
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                MainFrame.setIsTyping(true);
            }
        });


    }

    /**Shows slide number in the bottom right corner of slide, scaled in proportion to slide size in program window.
     *
     * @param d - the dimensions of the program window to use to proportionately display slide number in bototm right corner.
     */
    public void showSlideNumber(Dimension d)
    {

        slideNumber.setLocation((int)(d.width * .93), (int)(d.height * .6));

        add(slideNumber);

        revalidate();
        repaint();
        isSlideNumberShown = true;
    }

    /**Hides the slide's number from the bottom right corner of the slide
     *
     */
    public void removeSlideNumber()
    {
        remove(slideNumber);
        revalidate();
        repaint();
        isSlideNumberShown = false;
    }

    /**Returns bool to check if slideNumber is visible for this slide
     *
     * @return --boolean for if slide number is visible for this slide
     */
    public boolean getSlideNumberState()
    {
        return isSlideNumberShown;
    }

    /**When a slide is inserted, this is called to make sure the slideNumber value is the same as its index in the list of slides.
     *
     */
    public void upDateSlideNumber()

    {
        Integer number = SlideDeck.getSlideDeck().getSlides().indexOf(this);
        number++;

        Integer  totalSlides =  SlideDeck.getSlideDeck().getSlides().size();
        slideNumber.setText(number.toString() + " / " + totalSlides.toString());
    }



    /**Resizes, recolors, and sets location of slide number to begin with
     *
     * @param width - width of slide
     * @param height - slide
     */

    void initializeSlideNumber(int width, int height)
    {
        slideNumber.setSize(width,height); //necessary because null layout of slide makes location go wonky otherwise
        slideNumber.setLocation(50, 20);
        slideNumber.setForeground(Color.RED);

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



    /**Called from ImageUtilities SetTargetImage() after processing image to add an ImgLabel with image to slide.
     *
     * @param icon - processed image to be added to slide
     */
    public void addImageToSlide(ImageIcon icon)
    {
        ImgLabel imgLabel = new ImgLabel(icon, this);


        imgLabel.setSize(100, 100);
        imgLabel.setBackground(Color.yellow);

        images.add(imgLabel);
        add(imgLabel);
        this.revalidate();
        this.repaint();

    }

    /**Deletes everything on a slide
     *
     */
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
     *
     * Author: Fehmi
     */
    public void addText(JTextArea s, Item t){
        s.setBounds(50, 50, 150, 150);

        //textAreas.add(s); // intial list i added to
        items.add(t);      // to test out the arrayList of items theory
        add(s);
        System.out.println("Text should be inserted");
        repaint();
    }

    /**Adds a Text component to slide that creates bullets when a key is pressed.
     *
     * @param s -
     * @param t -
     *
     * Author: Fehmi
     */
    public void addBulletList(JTextArea s, Item t){
        s.setBounds(50, 50, 150, 150);

        //textAreas.add(s); // intial list i added to
        items.add(t);      // to test out the arrayList of items theory
        add(s);
        System.out.println("BulletList should be inserted");
        repaint();
    }

    /**Saves out the drawn part of the slide to the Resources folder with its own naming convention.
     * Allows us to reload the image to be drawn on when loading a project.
     *
     * @param p - the save path of the project's resources folder
     * @throws IOException - thrown if the path is not found
     *
     * Author: Robert
     */
    public void writeDrawing(Path p) throws IOException
    {
        //TODO:include slideID with drawing so can easily sort through the images
        String path = String.valueOf(p) + "\\" + slideID + "-drawing.png";
       // System.out.println(path);
        if(drawnImage != null)
            ImageIO.write(drawnImage, "png", new File(path));
    }


    /**UNUSED
     * Loads a processed image to be a background on the slide.
     * Called from ImageUtilities SetTargetBackground() after processing a background image
     *
     * @param icon - the processed image to be used as a background for this slide
     *
     * Author: Robert
     */
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

    /**UNUSED
     * Tried to  debug background not scaling to fullscreen. Did not work.
     */
    public void presentBGSize()
    {
        if(backGround != null)
        {

            //may reduce alpha over time...
            ImageUtilities.setTargetBackground(backGround, this);
        }

    }

    /**UNUSED
     * Tried to  debug background not scaling to fullscreen. Did not work.
     */
    public void resetBGPosition()
    {
        //backgroundImage = new JLabel();
       backgroundImage.setLocation(BGLocation.x,BGLocation.y);
    }

    /**UNUSED
     * Tried to  debug background not scaling to fullscreen. Did not work.
     */
    public void resetBGSize()
    {



       // backgroundImage.setSize(getWidth(), getHeight());
        ImageUtilities.setTargetBackground(backGround,  this);



    }

    /**UNUSED
     * Tried to  debug background not scaling to fullscreen. Did not work.
     */
    public void getBGOriginalMeasurements()
    {
        ogHeight = getHeight();
        ogWidth = getWidth();

    }

    public void setBackGround(BufferedImage bi){backGround = bi;}

    //changes Background color
    public void changeBGColor(Color color)
    {
        bgColor = color;
        this.setBackground(color);
    }


}




