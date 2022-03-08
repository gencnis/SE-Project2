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

public class BottomBar extends JPanel{
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

    Color beginColor;

    JButton next;
    JButton previous;
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

    //this needs to run after making a SlideDeck or else you get null pointer exceptions
    //links with the main slide deck
    public void initializeBB() {
        slideDeck = SlideDeck.getSlideDeck();
    }

    //TODO: You may need to rearrange all the buttons to make sure each one is pointing to the right slide.
    //if I insert a slide after 5 in a 10 slide show, slides 6-10 need to be updated

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

    //horribly inefficient but works at changing colors to the current slide
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

    void rebuildDeck() {
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
