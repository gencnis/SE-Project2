package SlideMgr;


import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

//
/**Allows for dragging of JComponents in a JPanel
   Use this code with any added JComponent to a panel to make it draggable on screen

    DraggableHandler handler = new DraggableHandler(imgLabel, this);
        imgLabel.addMouseListener(handler);
        imgLabel.addMouseMotionListener(handler);
        imgLabel.revalidate();
        imgLabel.repaint();

    Author: Robert
 */


class DraggableHandler extends MouseInputAdapter implements java.io.Serializable
{
    JComponent item; //handles every item on a JPanel
    Point clickPoint;
    boolean dragging;
    Slide currentSlide;
    // int ogOrder;      //if you want to set it back to the original layer order on the slide



    /**Gets a target  JComponent reference and sets default dragging off
     *
     * @param item - the target item we want to be able to grab and drag
     * @param s - the slide that the item is on
     */
    public DraggableHandler(JComponent item, Slide s) //need to provide the item and the slide it is on
    {
        this.item = item;
        dragging = false;
        currentSlide = s;
    }


    /**When mouse is clicked in bounds of the object, enable dragging
     *
     * @param e - mouse pressed action, used to get the coordinates of the mouse click
     */
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

    /**Turns off dragging of objet when button is released
     *
     * @param e - mouse released event
     */
    public void mouseReleased(MouseEvent e)
    {
        dragging = false;
    }


    /**When mouse is dragged, updates coordinates of object based on mouse position
     * TODO: Kind of buggy. image is a little offset from mouse location when dragged.
     *
     * @param e - mouse dragged event. used to get coordinates of dragged mouse on screen
     */
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