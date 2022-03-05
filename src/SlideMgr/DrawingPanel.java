package SlideMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

//LINE 39: NEEDS TO GET BRUSH ENABLED
//LINE 43: NEEDS TO GET Brush Color
//LINE 48: NEEDS to GET Brush Size

//RESOURCE: https://stackoverflow.com/questions/22436954/saving-a-jpanel-as-an-image-object-and-drawing-it-back-onto-a-jpanel
//Original and Third Post


public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener
{

    private int xPos, yPos;//mouse positions

    DrawingPanel()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        xPos = me.getX();
        yPos = me.getY();
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
//Brush enabled
        int x = me.getX(), y = me.getY();
        Graphics g = getGraphics();

//FRONT END: SET THIS COLOR SOMEWHERE IN tHE BUTTONS
        g.setColor(Color.BLACK);



//FRONTEND: SET THE BRUSH SIZE SOME WHEERE IN THE UI
        g.drawOval(xPos, yPos, 30, 30);
        xPos = x;
        yPos = y;
    }

    public void loadDrawing(BufferedImage bi)
    {
        //opens a message dialog and displays the image parameter
        JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(bi)));
        System.out.println("w:" + bi.getWidth() + " h:" + bi.getHeight());
    }

    public BufferedImage getScreenShot()
    {

        BufferedImage image = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_RGB);
        // call the Panels's paint method, using
        // the Graphics object of the image.
        this.paint(image.getGraphics());
        return image;
    }

    //unused abstract method
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
}