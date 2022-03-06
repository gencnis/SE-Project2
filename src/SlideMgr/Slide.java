/**
 *
 * This is the class that you would extend and then start working on your functions and methods
 *
 *
 *
 * @Author : FEHMI NEFFATI
 */

package SlideMgr;

import Item.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


//Manages all of the Objects in the SlideShow
public class Slide extends DrawingPanel
{
    ArrayList<JLabel> images;

    String slideID;



    public Slide(Integer slideID)
    {

        this.slideID = slideID.toString();

        images = new ArrayList<JLabel>();
        setLayout(null);

// TODO: Fehmi: add JButtons to take you to the next and previous slides on the sides


    }




    //run this to add an image from the button action
    //makes a JLabel, adds it to reference list
    public void addImageToSlide(ImageIcon icon)
    {
        JLabel imgLabel=new JLabel(icon);


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



}




