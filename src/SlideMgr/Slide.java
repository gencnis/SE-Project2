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
public class Slide extends DrawingPanel {
    ArrayList<JLabel> images;
    ArrayList<JTextArea> textAreas;

    //This a test
    ArrayList<Item> items;

    String slideID;

    //manages if slide number is displayed or not
    JLabel slideNumber;
    boolean isSlideNumberShown = false;

    public Slide(Integer slideID)
    {

        this.slideID = slideID.toString();

        //slide number initializatin
        slideNumber = new JLabel();
        initializeSlideNumber(100,100);  //change the parameters to change the size of the slide number text

        images = new ArrayList<JLabel>();
        textAreas = new ArrayList<>();
        items = new ArrayList<Item>();
        setLayout(null);


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

    }



}




