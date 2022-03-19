package SlideMgr;


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

<<<<<<< Updated upstream

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener
=======
//Drawing Panel is the base class for All Slides
//It extends JPanel and adds a Buffered Image for the user to draw onto when the draw function is selected from the GUI
public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, java.io.Serializable
>>>>>>> Stashed changes
{

    private int xPos, yPos;//mouse positions
    Color brushColor = Color.BLACK;
    Integer brushWidth = 10;
    Integer brushHeight = 10;

<<<<<<< Updated upstream
    private BufferedImage bufferedImage;

=======
    transient protected   BufferedImage drawnImage; //can't serialize images, saved to a resources folder

    //cursor images are also non-serializeable
    transient  Toolkit toolkit;
    transient static Cursor brushCursor;
    transient static Cursor eraserCursor;

    //adds mouse listener and sets up cursors and the color map or available color options
>>>>>>> Stashed changes
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


    //draws image to screen
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        validateImage();

        // Paint the bufferedImage which stores
        // what was drawn until now
        g.drawImage(bufferedImage, 0, 0, null);
    }

<<<<<<< Updated upstream
=======
    public Color getBrushColor() {
        return brushColor;
    }
    public void setBrushSize(int n){
        brushHeight = n;
        brushWidth  = n;
    }

    //sets brushcolor from set of options
    public void setBrushColor()
    {
        Object[] possibleValues = { "BLACK", "RED", "BLUE", "GREEN"};

        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose A Text Color Please: ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);

        System.out.println(selectedValue.toString());
        setBrushColor(colorMap.get(selectedValue.toString()));
    }

    public void setBrushColor(Color brushColor) {
        this.brushColor = brushColor;
    }

    //changes cursor to earaser image
    public void eraser(){
        setCursor(eraserCursor);
        setBrushSize(50);
    }
>>>>>>> Stashed changes


    //when clicked checks if brush is activated and updates cursor approporiately
    @Override
    public void mousePressed(MouseEvent me)
    {
//  //TODO:Implement a GetBrushColor() method here so you can customize the color.
//    TODO: Implement a GetBrushSize() method here so you can customize the size.
        xPos = me.getX();
        yPos = me.getY();
    }

    //if activated, paints an oval to the screen of selected color and brush size
    @Override
    public void mouseDragged(MouseEvent me)
    {
        int x = me.getX(), y = me.getY();
        validateImage();

        // Paint directly into the bufferedImage here
        Graphics g = bufferedImage.getGraphics();
        g.setColor(brushColor);
        g.drawOval(xPos, yPos, brushWidth, brushHeight);
        repaint();
        xPos = x;
        yPos = y;
    }

    //loads the up a previous drawn image to continue using in the prohect
    //called from load in Main Frame
    public void loadDrawing(BufferedImage bi)
    {
        //opens a message dialog and displays the image parameter
        JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(bi)));
        System.out.println("w:" + bi.getWidth() + " h:" + bi.getHeight());
    }

    //gets a snapshot of the drawn image for testinc
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

<<<<<<< Updated upstream
=======


    //sets if we can paint or not
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

>>>>>>> Stashed changes
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