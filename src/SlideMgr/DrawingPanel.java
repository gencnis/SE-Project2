package SlideMgr;


import FullWindow.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//LINE 33: NEEDS TO GET Brush Color
//LINE 38: NEEDS to GET Brush Size

//RESOURCE: https://stackoverflow.com/questions/22436954/saving-a-jpanel-as-an-image-object-and-drawing-it-back-onto-a-jpanel
//Original and Third Post


public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener
{

    private int xPos, yPos;//mouse positions
    Color brushColor = Color.BLACK;
    Integer brushWidth = 10;
    Integer brushHeight = 10;
    Boolean activated = false; // This is the boolean that would make sure we can draw or not

    private BufferedImage bufferedImage;

    DrawingPanel()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // Make sure that the "bufferedImage" is non-null
    // and has the same size as this panel
    private void validateImage()
    {
        if (bufferedImage == null)
        {
            //geets you a new buffered image
            bufferedImage = new BufferedImage(
                    getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            //tests it for drawability
            Graphics g = bufferedImage.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
            g.dispose();

        }

        if (bufferedImage.getWidth() != getWidth() ||
                bufferedImage.getHeight() != getHeight())
        {
            BufferedImage newBufferedImage = new BufferedImage(
                    getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = newBufferedImage.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
            g.drawImage(bufferedImage, 0,0,null);
            g.dispose();
            bufferedImage = newBufferedImage;
        }
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        validateImage();

        // Paint the bufferedImage which stores
        // what was drawn until now
        g.drawImage(bufferedImage, 0, 0, null);
    }



    @Override
    public void mousePressed(MouseEvent me)
    {
//  //TODO:Implement a GetBrushColor() method here so you can customize the color.
//    TODO: Implement a GetBrushSize() method here so you can customize the size.
        xPos = me.getX();
        yPos = me.getY();
        if (activated){
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("png/cur.png").getImage(),
                new Point(0,0),"custom cursor"));}
        else{setCursor(Cursor.getDefaultCursor());}
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        if (activated) {
            int x = me.getX(), y = me.getY();
            validateImage();

            // Paint directly into the bufferedImage here
            Graphics g = bufferedImage.getGraphics();
            g.setColor(brushColor);
            g.fillOval(xPos, yPos, brushWidth, brushHeight);
            repaint();
            xPos = x;
            yPos = y;
        }
    }

    public void loadDrawing(BufferedImage bi)
    {
        //opens a message dialog and displays the image parameter
        JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(bi)));
        System.out.println("w:" + bi.getWidth() + " h:" + bi.getHeight());
    }

    public BufferedImage getScreenShot()
    {

        // This basically returns a "copy" of the
        // bufferedImage that stores what was drawn
        BufferedImage image = new BufferedImage(
                getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();
        return image;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
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