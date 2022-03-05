/**
 * This class is for the bottom bar where we would Display a list of buttons for all of the
 *
 * @Author: Fehmi Neffati
 *
 */


package BottomNavigation;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BottomBar extends JPanel {
    //Arraylist should hold all of the slides
    //ArrayList<PresentationSlide> slides = new ArrayList<>();

    /***
     * Here we look at every slide we have, we make a button for it with an index reference
     *
     * @param slides TODO: Ideally would be ArrayList of slides not JPanel
     * @param slideShow idk what this is or if we could change but all's cool since it works
     * @param mainPanel TODO: TODO: Ideally would be Slide not JPanel
     */
    public BottomBar(ArrayList<JPanel> slides, CardLayout slideShow, JPanel mainPanel){
        this.setBackground(Color.decode("#B2D5DB"));

        Integer i = 0;
        // TODO: Here I had to change the signature from Slide to JPanel, Ideally we would have slides
        for (JPanel s : slides){
            i ++;
            JButton c = new JButton(i.toString());
            Integer finalI = i;
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slideShow.show(mainPanel, finalI.toString());
                }
            });
            this.add(c);
        }
    }

    // add function

    // remove function


}
