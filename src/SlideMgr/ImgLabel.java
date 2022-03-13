package SlideMgr;



import Item.ComponentMover;
import Item.ComponentResizer;
import SlideMgr.DraggableHandler;
import SlideMgr.Slide;

import javax.swing.*;
import java.awt.image.BufferedImage;

//This is used for Image importing. Stores a reference of the image for resizing if needed.(not implemented)
public class ImgLabel extends JLabel implements java.io.Serializable
{
    transient BufferedImage img = null;


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

    //stores a resizeable version of the image in a Buffered Image
    public void storeImage(BufferedImage bufferedImage)
    {
        img = bufferedImage;
    }

    public BufferedImage getImg()
    {
        return img;
    }

}
