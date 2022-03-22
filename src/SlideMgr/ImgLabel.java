package SlideMgr;



import Item.ComponentMover;
import Item.ComponentResizer;
import SlideMgr.DraggableHandler;
import SlideMgr.Slide;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**This is used for Image importing and displays an image on a slide.
 * Stores a reference of the image for resizing if needed.(not implemented)
 *
 * Author: Robert
 */
public class ImgLabel extends JLabel implements java.io.Serializable
{
    transient BufferedImage img = null;


    /**Constructor - gets an image to display and which slide it should be added to
     *
     * @param i - the image you wish to display with this imgLabel
     * @param s - the slide which this Image should be displayed
     */
    public ImgLabel(Icon i, Slide s)
    {
        this.setIcon(i);

       // ComponentResizer cr = new ComponentResizer();
        //cr.registerComponent(this);
        ComponentMover cm = new ComponentMover();
        cm.registerComponent(this);
        //cm.setDragInsets( cr.getDragInsets() );


       /* DraggableHandler handler = new DraggableHandler(this, s);
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
        this.revalidate();
        this.repaint();*/
    }

    /**stores a resizeable version of the image in a Buffered Image
     *
     * @param bufferedImage - the target image we wish to keep a reference of for resizing.
     */
    public void storeImage(BufferedImage bufferedImage)
    {
        img = bufferedImage;
    }

    public BufferedImage getImg()
    {
        return img;
    }

}
