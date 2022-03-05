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
public class Slide extends JPanel
{
    ArrayList<JLabel> images;
    DrawingPanel dp;
    public Slide()
    {
        images = new ArrayList<JLabel>();
        setLayout(null);
        for (JLabel img:images)
        {
            add(img);
        }


    }


    //run this to add an image from the button action
    //makes a JLabel, adds it to reference list
    public void addImage(ImageIcon icon)
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

    void display(){
        // TODO: DISPLAY A SLIDE
    }
    void add(){
        // TODO: ADD AN ITEM TO THE LIST OF ITEMS OF THIS SLIDE
    }

    void save(ArrayList<Item> allItems){
        // TODO:
    }

    void Remove(Item item){
    }
}




