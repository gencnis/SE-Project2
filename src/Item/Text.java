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
import java.awt.*;

public class Text implements Item{

    public JTextArea textArea;
    public String textEntry = "Insert Text here\n";                  // The text entry that the user will enter / edit


    /**
     * This is the constructor, it creates a component Resizer and a Componenet Dragger (highly reccomend you check out the link in the header of the class I made)
     * This is exactly what we need since it's resizable, and draggable.
     * I highly recommend you create an image class that implements Item, and follow this method
     *
     * @param currentSlide when you create a new instance of an item in the MainFrame, just go ahead and add the
     *                     slide on which you want that Item to be which is : currentSlide
     */
    public Text(Slide currentSlide){
        Font font =  new Font(this.setFont().toString(), Font.BOLD, 20);
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

    Object setFont(){
        Object[] possibleValues = { "Arial", "Helvetica", "Calibri", "Times New Roman"};


        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose A Font Please: ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        return selectedValue;
    }
}