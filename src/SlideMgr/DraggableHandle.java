package SlideMgr;


import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

//Allows for dragging of JComponents in a JPanel
/*
   Use this code with any added JComponent to a panel to make it draggable on screen

    DraggableHandler handler = new DraggableHandler(imgLabel, this);
        imgLabel.addMouseListener(handler);
        imgLabel.addMouseMotionListener(handler);
        imgLabel.revalidate();
        imgLabel.repaint();
 */
class DraggableHandler extends MouseInputAdapter implements java.io.Serializable
{
    JComponent item; //handles every item on a JPanel
    Point clickPoint;
    boolean dragging;
    Slide currentSlide;
    // int ogOrder;      //if you want to set it back to the original layer order on the slide

    //gets a item reference and sets default dragging off
    public DraggableHandler(JComponent item, Slide s) //need to provide the item and the slide it is on
    {
        this.item = item;
        dragging = false;
        currentSlide = s;
    }


    //when mouse is clicked in bounds of the object, enable dragging
    public void mousePressed(MouseEvent e)
    {
        // ogOrder = item.getComponentZOrder(currentSlide); not sure if this works to get the original layer order
        clickPoint = e.getPoint();
        currentSlide.setComponentZOrder(item, 0); //makes sure it is on top when clicked
        item.repaint();
        // r = item.getBounds();
        if(item.contains(clickPoint)) //checks if it is in bounds of the click point
        {
            dragging = true;
            System.out.println("grabbed");
        }

       // EditProperties.resize(item, 300,300);
    }

    //turns of dragging of objet when button is released
    public void mouseReleased(MouseEvent e)
    {
        dragging = false;
    }


    //when mouse is dragged, updates coordinates of object based on mouse position
    public void mouseDragged(MouseEvent e)
    {
        if(dragging)
        {

            int thirdX = e.getXOnScreen() - clickPoint.x;
            int thirdY = e.getYOnScreen() - clickPoint.y;
            item.setLocation(thirdX, thirdY);
        }
    }
}