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

<<<<<<< Updated upstream
public class BottomBar extends JPanel
=======
/**
 * The Bottom Bar class is responsible for displaying all the slides that are currently in the slide deck.
 * Clicking on the arrow buttons will advance you forward or back one slide.
 * You can click on any nubmered button to display that numbered slide in the slideshow
 * */

public class BottomBar extends JPanel implements java.io.Serializable
>>>>>>> Stashed changes
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


<<<<<<< Updated upstream

    static SlideDeck slideDeck;
    static CardLayout slideShow;
    static JPanel mainPanel;
    ArrayList<JButton> buttons;

     public BottomBar( CardLayout slideShow, JPanel mainPanel)
=======
    static SlideDeck slideDeck; //main slide show
    static CardLayout slideShow;    //the cardlayout used to manage and display all slides
    static JPanel mainPanel;        //the center panel that holds all the slides
    ArrayList<JButton> buttons;     //holds all the nubmered slide buttons

    Color beginColor;               //original color of slide buttons

    JButton next;                   //next slide button
    JButton previous;              //previous slide button
    public BottomBar(CardLayout slideShow, JPanel mainPanel)
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream

=======
        //adds actions for forward and back slide buttons
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
>>>>>>> Stashed changes


    }

    //this needs to run after making a SlideDeck or else you get null pointer exceptions
<<<<<<< Updated upstream
    //links with the main slide deck
    public void initializeBB()
    {
        slideDeck = SlideDeck.getSlideDeck();
    }

//TODO: You may need to rearrange all the buttons to make sure each one is pointing to the right slide.
//if I insert a slide after 5 in a 10 slide show, slides 6-10 need to be updated

    public void addSlideButton(Integer index , Slide s)
=======
    //connects to  the main slide deck
    public void initializeBB() {
        slideDeck = SlideDeck.getSlideDeck();
    }



    //inserts slide button at specified index in buttons list
    public void addSlideButton(Integer index, Slide s)
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
    //updates which slide button is currently highlighted for the current slide
    //horribly inefficient
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
>>>>>>> Stashed changes
        }

    }

<<<<<<< Updated upstream
//TODO: Figure out how to remove the buttons from the menu. You may need to rebuild all the buttons again.
=======
    //removes navigation button from bottom for a removed slide
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    void rebuildDeck()
=======
    //deletes all slide buttons in bottom
    //used when loading project
    public void clearAllButtons()
    {
        for(JButton b : buttons)
        {
            this.remove(b);

        }
        buttons.clear();
    }

    //updates all slide buttons and relinks all slides to specified buttons
    public void rebuildDeck()
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream





=======

    //moves currently displayed slide and button forward one slide in the slide deck
    static void moveForward(){
            if (slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) < slideDeck.getSlides().size() -1) {
                MainFrame.showSlide(slideDeck.getSlide(slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) + 1));
            } else {
                System.out.println("END OF DECK REACHED");
            }
        }

    //moves currently displayed slide and button backward one slide in the slide deck
    static void moveBackwards(){
        if (slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) > 0) {
            MainFrame.showSlide(slideDeck.getSlide(slideDeck.getSlides().indexOf(slideDeck.getCurrentSlide()) - 1));
        } else {
            System.out.println("START OF DECK REACHED");
        }
    }
>>>>>>> Stashed changes
}
