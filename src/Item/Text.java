/**
 *
 * Example class that implements the Item interface
 *
 * The goal with the class is that we create a new instance of this class everytime the user clicks on the insert text button
 * Then we would add that instance to an Arraylist of items inside a specific slide
 *
 * @Author: Fehmi Neffati
 */

package Item;

import SlideMgr.*;


import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.FocusListener;

public class Text extends JComponent implements Item, java.io.Serializable
{

    public JTextArea textArea;
    public String textEntry = "Insert Text here\n";                  // The text entry that the user will enter / edit
    public Integer textSize;

    /**
     * This is the constructor, it creates a component Resizer and a Componenet Dragger (highly reccomend you check out the link in the header of the class I made)
     * This is exactly what we need since it's resizable, and draggable.
     * I highly recommend you create an image class that implements Item, and follow this method
     *
     * @param currentSlide when you create a new instance of an item in the MainFrame, just go ahead and add the
     *                     slide on which you want that Item to be which is : currentSlide
     */
    public Text(Slide currentSlide){
        setSize();
        Font font =  new Font(this.setFont().toString(), Font.BOLD, textSize);
        ComponentResizer cr = new ComponentResizer();
        textArea = new JTextArea(textEntry);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        cr.registerComponent(textArea);
        ComponentMover cm = new ComponentMover();
        cm.registerComponent(textArea);
        cm.setDragInsets( cr.getDragInsets() );
        currentSlide.addText(textArea, this);
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
    void setSize() {
        Object[] possibleValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};

        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose the text Size Please: ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[ 0 ]);
        textSize = Integer.parseInt(selectedValue.toString());
    }

    public Integer getTextSize() {
        return textSize;
    }
}