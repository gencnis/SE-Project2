package Item;

import FullWindow.MainFrame;
import SlideMgr.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * This follows the same structure as the Text Item class
 * The difference is that it adds a bullet point at every new line
 *
 * Author: Fehmi Neffati
 */

public class BulletList extends JComponent implements Item, java.io.Serializable
{

    public JTextArea textArea;
    public String textEntry = "";                  // The text entry that the user will enter / edit
    public Integer textSize;

    /**
     * This is the constructor, it creates a component Resizer and a Componenet Dragger (highly reccomend you check out the link in the header of the class I made)
     * This is exactly what we need since it's resizable, and draggable.
     * I highly recommend you create an image class that implements Item, and follow this method
     *
     * @param currentSlide when you create a new instance of an item in the MainFrame, just go ahead and add the
     *                     slide on which you want that Item to be which is : currentSlide
     */
    public BulletList(Slide currentSlide){
        setSize();
        Font font =  new Font(this.setFont().toString(), Font.BOLD, textSize);
        ComponentResizer cr = new ComponentResizer();
        textArea = new JTextArea(textEntry);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.append("•");
        cr.registerComponent(textArea);
        ComponentMover cm = new ComponentMover();
        cm.registerComponent(textArea);
        cm.setDragInsets( cr.getDragInsets() );
        currentSlide.addBulletList(textArea, this);
        JOptionPane.showMessageDialog(null,
                "To add a new bullet to your list, please press the ESCAPE Button. Thank you.", "BulletList",
                JOptionPane.INFORMATION_MESSAGE, null);


    }
    /**
     * Allows the user the pick from a list of Fonts, if you want to add more fonts you could find the desired one,
     * and add it to the array of possibleValues
     * @return an Object selected value. We will later convert it to string.
     */

    Object setFont(){
        Object[] possibleValues = { "Arial", "Helvetica", "Calibri", "Times New Roman"};


        return JOptionPane.showInputDialog(null,
                "Choose the text Font Please: ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
    }

    /**
     *
     * Same thing as above but instead of returning a value, we go ahead and set a global variable corresponding.
     */
    void setSize(){
        Object[] possibleValues = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};

        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose the text Size Please: ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        textSize = Integer.parseInt(selectedValue.toString());
    }

    public Integer getTextSize() {
        return textSize;
    }
    public void addBullet(){
        textArea.append("\n•");
    }

}