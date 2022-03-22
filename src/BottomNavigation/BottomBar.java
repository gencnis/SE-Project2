/**
 * This class is for the bottom bar where we would Display a list of buttons for all of the
 *
 *
 * Some resources:
 * https://www.youtube.com/watch?v=BJ7fr9XwS2o&ab_channel=BroCode
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html
 * https://stackoverflow.com/questions/10866501/add-arrow-keylistener-to-a-jframe-that-implements-actionlistener
 * https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
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

public class BottomBar extends JPanel implements java.io.Serializable
{
    //Arraylist should hold all of the slides
    //ArrayList<PresentationSlide> slides = new ArrayList<>();

    /***
     * Here we look at every slide we have, we make a button for it with an index reference
     *
     * @param slides reference to master slideDeck
     * @param slideShow holds the cardLayout template so slides all appear in the same spot on screen
     * @param mainPanel mainPanel is a reference to the main content window
     *                  Author: Fehmi
     */


    static SlideDeck slideDeck;
    static CardLayout slideShow;
    static JPanel mainPanel;
    ArrayList<JButton> buttons;

    Color beginColor;

    JButton next;
    JButton previous;

    /**
     *
     * @param slideShow
     * @param mainPanel
     */
    public BottomBar(CardLayout slideShow, JPanel mainPanel)
    {
        buttons = new ArrayList<JButton>();
        this.slideShow = slideShow;
        this.mainPanel = mainPanel;
        //slideDeck = SlideDeck.getSlideDeck();

        this.setBackground(Color.decode("#B2D5DB"));
        next = new JButton("→");
        previous = new JButton("←");

        beginColor = next.getBackground();

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveForward();
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackwards();
            }
        });


    }



    /**This needs to run after making a SlideDeck or else you get null pointer exceptions
     *updates reference to  the master slide deck
     *
     * Author: Robert
     */
    public void initializeBB() {
        slideDeck = SlideDeck.getSlideDeck();
    }




    /**Adds a slide button in at the provided index and shifts all other slides back one.
     *
     * if I insert a slide after 5 in a 10 slide show, slides 6-10 need to be updated
     * @param index -- the index you wish to insert the new slide at in the slide deck
     * @param s -- the slide we wish to insert
     */
    public void addSlideButton(Integer index, Slide s)
    {

        Integer temp = index + 1;
        JButton c = new JButton(temp.toString());


        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.showSlide(s);
            }
        });
        buttons.add(index, c);
        this.add(c, index);


        if (index < slideDeck.getSlides().size() - 1) //if not adding on the end, have to rebuild the buttons;
        {
            rebuildDeck();
        }
        this.add(next, BorderLayout.LINE_START);
    }



    /**Horribly inefficient but works at changing colors  of the bottom buttons to reflect the currently selected slide
     * The currently selected slide is blue while the other buttons are white.
     *
     * Inefficient because it goes through the whole arrayList to change each slide button's color each time called.
     * Author: Robert
     */
    public void changeSlideButtonSelection()
    {
        Slide currentSlide= MainFrame.getCurrentSlide();
        int currentSlideIndex = SlideDeck.getSlideDeck().getSlides().indexOf(currentSlide);

        for (JButton b : buttons)
        {
            int buttonIndex = buttons.indexOf(b);
            if(buttonIndex == currentSlideIndex)
            {
                b.setBackground(Color.BLUE);
            }
            else
            {
                b.setBackground(beginColor);
                b.setForeground(Color.BLACK);
            }
        }
    }


    //

    /**Removes a slide button based on the slide that is to be removed and it's index in the slide deck
     * This will be called via SlideDeck removeSlide()
     *
     * @param s - the slide we wish to remove from the slide deck
     *
     * Author: Robert
     */
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

    /**Removes all buttons from bottom bar. Used when loading a project file.
     *
     * Author: Robert
     */
    public void clearAllButtons()
    {
        for(JButton b : buttons)
        {
            this.remove(b);

        }
        buttons.clear();
    }

    /**Deletes the whole bottom bar and re-adds all buttons for each slide.
     * Used when removing or adding slides in the middle of the deck.
     * Not exactly efficient.
     *
     * Author: Robert
     */
    public void rebuildDeck()
    {
        this.removeAll();
        this.add(previous, BorderLayout.LINE_END);
        Integer index = 1;
        for (JButton b : buttons) {
            b.setText(index.toString());
            this.add(b);
            index++;
        }
    }


    static void moveForward(){
            if (slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) < slideDeck.getSlides().size() -1) {
                MainFrame.showSlide(slideDeck.getSlide(slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) + 1));
            } else {
                System.out.println("END OF DECK REACHED");
            }
        }

    static void moveBackwards(){
        if (slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) > 0) {
            MainFrame.showSlide(slideDeck.getSlide(slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) - 1));
        } else {
            System.out.println("START OF DECK REACHED");
        }
    }
}
