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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


//Manages all of the Objects in the SlideShow
public class Slide extends DrawingPanel implements java.io.Serializable
{
    //Serial ID number needed for every class
    private static final long serialVersionUID = -4751816768152150898L;

    ArrayList<ImgLabel> images;

    //TODO: make serializable
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
    int ogWidth;
    int ogHeight;
    boolean isBGSet = false;
    Point BGLocation;

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



// TODO: Fehmi: add JButtons to take you to the next and previous slides on the sides


    }


    public void showSlideNumber(Dimension d)
    {

        slideNumber.setLocation((int)(d.width * .93), (int)(d.height * .6));

        add(slideNumber);

        revalidate();
        repaint();
        isSlideNumberShown = true;
    }

    public void removeSlideNumber()
    {
        remove(slideNumber);
        revalidate();
        repaint();
        isSlideNumberShown = false;
    }

    public boolean getSlideNumberState()
    {
        return isSlideNumberShown;
    }

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

    //run this to add an image from the button action
    //makes a JLabel, adds it to reference list
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

    public void clearSlide() //basically deletes everything on a slide
    {
        images.clear();
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    public String getSlideID(){return slideID;}

    void save(ArrayList<Item> allItems){
        // TODO:
    }

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
        repaint();
    }

    public void writeDrawing(Path p) throws IOException
    {
        //TODO:include slideID with drawing so can easily sort through the images
        String path = String.valueOf(p) + "\\" + slideID + "-drawing.png";
       // System.out.println(path);
        if(drawnImage != null)
            ImageIO.write(drawnImage, "png", new File(path));
    }



    public void loadBackgroundImage(ImageIcon icon)
    {


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
            this.setComponentZOrder(backgroundImage, 10000);
            isBGSet = true;
        }


        backgroundImage.setOpaque(false);
       backgroundImage.revalidate();
       backgroundImage.repaint();
        this.revalidate();
        this.repaint();
    }

    public void presentBGSize()
    {
        if(backGround != null)
        {


            ImageUtilities.setTargetBackground(backGround, this);
        }

    }
    public void resetBGPosition()
    {
        backgroundImage.setLocation(BGLocation.x,BGLocation.y);
    }
    public void resetBGSize()
    {

        backgroundImage = new JLabel();




        ImageUtilities.setTargetBackground(backGround,  this);

    }

    public void getDimensions()
    {

        BGLocation.setLocation(backgroundImage.getLocation());
    }

    public void setBackGround(BufferedImage bi){backGround = bi;}

    public void changeBGColor(Color color)
    {
        bgColor = color;
        this.setBackground(color);
    }


}




