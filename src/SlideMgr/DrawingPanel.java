package SlideMgr;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;


//LINE 33: NEEDS TO GET Brush Color
//LINE 38: NEEDS to GET Brush Size

//RESOURCE: https://stackoverflow.com/questions/22436954/saving-a-jpanel-as-an-image-object-and-drawing-it-back-onto-a-jpanel
//Original and Third Post

/**Base Class for each slide. Allows for drawing on the background when enabled
 *
 * Author: Robert
 */
public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, java.io.Serializable
{

    private Integer xPos, yPos;//mouse positions
    Color brushColor = Color.BLACK;
    Integer brushWidth = 10;
    Integer brushHeight = 10;
    Boolean activated = false; // This is the boolean that would make sure we can draw or not
    Boolean erase = false; //checks if we are erasing from the surface

    //Fehmi's map for colors
   HashMap<String, Color> colorMap; // here I save all of the color values for easy access

    transient protected   BufferedImage drawnImage;

    transient  Toolkit toolkit;
    transient static Cursor brushCursor;
    transient static Cursor eraserCursor;

    /**Constructor : Initializes mouse and motion listeners, gets toolkit for cursor designs, initializes color map
     * colorMap holds every choice for brush color for drawing.
     *
     *
     *
     */
    DrawingPanel()
    {
        addMouseListener(this);
        addMouseMotionListener(this);

        toolkit =  Toolkit.getDefaultToolkit();

        //Fehmi's code for color map and cursor shapes
        brushCursor = toolkit.createCustomCursor(new ImageIcon("png/cur.png").getImage(),
                                               new Point(0,0),"custom cursor");
        eraserCursor = toolkit.createCustomCursor(new ImageIcon("png/erase.png").getImage(),
                new Point(0,0),"eraser");
        colorMap = new HashMap<>();
        colorMap.put("BLACK", Color.BLACK );  // Black
        colorMap.put("RED", Color.RED );    // Red
        colorMap.put("GREEN", Color.GREEN );    // Green
        colorMap.put("BLUE", Color.BLUE );    // Blue


    }



    /**Make sure that the bufferedImage to be used as the drawing surface is non-null
     * and has the same size as this panel
     *
     */
    private void validateImage()
    {
        if (drawnImage == null)
        {
            //gets you a new buffered image
            drawnImage = new BufferedImage(
                    getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            //tests it for drawability
            Graphics g = drawnImage.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
            g.dispose();
        }

        if (drawnImage.getWidth() != getWidth() ||
                drawnImage.getHeight() != getHeight())
        {
            BufferedImage newBufferedImage = new BufferedImage(
                    getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = newBufferedImage.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
            g.drawImage(drawnImage, 0,0,null);
            g.dispose();
            drawnImage = newBufferedImage;
        }
    }

    /**Paints on the background bufferedImage.
     *
     * @param g - paints using this instance of Graphics
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        validateImage();

        // Paint the bufferedImage which stores
        // What was drawn until now
        g.drawImage(drawnImage, 0, 0, null);
    }

    public Color getBrushColor() {
        return brushColor;
    }

    public void setBrushSize(int n){
        brushHeight = n;
        brushWidth  = n;
    }

    /**Displays an option pane from which to select a brush color from a set of available colors.
     *
     * Author: Fehmi
     */
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

    /**Enables erasing on the drawing surface
     *
     * Author: Fehmi
     */
    public void eraser(){
        setCursor(eraserCursor);
        setBrushSize(50);
        erase = true;
    }

    /**Gets coordinates of mouse and draws an oval on the screen at those coordinates in the specified brush color.
     * If erasing, paints the image the same color as the background.
     *
     * @param me - mouse pressed event
     */
    @Override
    public void mousePressed(MouseEvent me)
    {
        //TODO:Implement a GetBrushColor() method here so you can customize the color.
        //TODO: Implement a GetBrushSize() method here so you can customize the size.
        xPos = me.getX();
        yPos = me.getY();


        //Fehmi's if statements
       if (activated)
       {
           if(erase)
           {
               setCursor(eraserCursor);
           }
           if(!erase) {
               setCursor(brushCursor);
               setBrushSize(10);
           }
       }
        else{
            setCursor(Cursor.getDefaultCursor());
        }
        repaint();
    }

    /**Draws ovals of specified color at subsequent mouse coordinates when dragged if drawing is enabled
     *
     * @param me - mouse dragged event
     *
     *Author: Robert
     */
    @Override
    public void mouseDragged(MouseEvent me)
    {
        if (activated)
        {
            int x = me.getX(), y = me.getY();
            validateImage();

            // Paint directly into the bufferedImage here
            Graphics g = drawnImage.getGraphics();
            g.setColor(brushColor);
            g.fillOval(xPos, yPos, brushWidth, brushHeight);
            repaint();
            xPos = x;
            yPos = y;
        }
    }

    /**Loads a buffered image to act as the drawing surface of the slide
     *
     * @param bi - the target buffered image to be used as a drawing surface
     *
     * Author: Robert
     */
    public void loadDrawing(BufferedImage bi)
    {
        //opens a message dialog and displays the image parameter
        // JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(bi)));
        //System.out.println("w:" + bi.getWidth() + " h:" + bi.getHeight());

        drawnImage = bi;

        Graphics g = drawnImage.getGraphics();
        validateImage();
        g.drawImage(drawnImage, 0, 0, null);


    }

    /**UNUSED
     * Returns screen shot of background image as Buffered Image
     *
     * @return - screenshot of background image
     */
    public BufferedImage getScreenShot()
    {

        // This basically returns a "copy" of the
        // bufferedImage that stores what was drawn
        BufferedImage image = new BufferedImage(
                getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.drawImage(drawnImage, 0, 0, null);
        g.dispose();
        return image;
    }


    //Allows for turning on and off drawing on a slide.
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    //unused abstract methods
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