/**
 * This class is for the bottom bar where we would Display a list of buttons for all of the
 *
 * @Author: Fehmi Neffati
 *
 */


package BottomNavigation;



import FullWindow.MainFrame;
import SlideMgr.Slide;
import SlideMgr.SlideDeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BottomBar extends JPanel
{
    //Arraylist should hold all of the slides
    //ArrayList<PresentationSlide> slides = new ArrayList<>();

    /***
     * Here we look at every slide we have, we make a button for it with an index reference
     *
     * @param slides TODO: Ideally would be ArrayList of slides not JPanel
     * @param slideShow idk what this is or if we could change but all's cool since it works
     * @param mainPanel TODO: TODO: Ideally would be Slide not JPanel
     */



    static SlideDeck slideDeck;
    static CardLayout slideShow;
    static JPanel mainPanel;
    ArrayList<JButton> buttons;

     public BottomBar( CardLayout slideShow, JPanel mainPanel)
    {
        buttons = new ArrayList<JButton>();
        this.slideShow = slideShow;
        this.mainPanel = mainPanel;
        //slideDeck = SlideDeck.getSlideDeck();

        this.setBackground(Color.decode("#B2D5DB"));
        JButton next = new JButton("->");
        next.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton previous = new JButton("->");
        previous.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(next);
        this.add(previous);




    }

    //this needs to run after making a SlideDeck or else you get null pointer exceptions
    //links with the main slide deck
    public void initializeBB()
    {
        slideDeck = SlideDeck.getSlideDeck();
    }

//TODO: You may need to rearrange all the buttons to make sure each one is pointing to the right slide.
//if I insert a slide after 5 in a 10 slide show, slides 6-10 need to be updated

    public void addSlideButton(Integer index , Slide s)
    {

        Integer temp = index + 1;
        JButton c = new JButton(temp.toString());


        c.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainFrame.showSlide(s);
            }
        });
        buttons.add(index, c);
        this.add(c, index);



        if(index < slideDeck.getSlides().size() - 1) //if not adding on the end, have to rebuild the buttons;
        {
            rebuildDeck();

        }

    }

//TODO: Figure out how to remove the buttons from the menu. You may need to rebuild all the buttons again.
    //this will be called via SlideDeck removeSlide()
    public void removeSlideButton(Slide s)
    {

        int index = slideDeck.getSlides().indexOf(s);
        this.remove(index);
        buttons.remove(index);
        rebuildDeck();
        //get rid of the button
        //get ird

        //rebuild Slide Show to display correct order of buttons

    }

    void rebuildDeck()
    {
        this.removeAll();
        Integer index = 1;
        for(JButton b : buttons)
        {
            b.setText(index.toString());
            this.add(b);
            index++;
        }
    }





}
