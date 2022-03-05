/**
 * This class is for the bottom bar where we would Display a list of buttons for all of the
 *
 * @Author: Fehmi Neffati
 *
 */


package BottomNavigation;

import Slide.PresentationSlide;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BottomBar extends JPanel {
    //Arraylist should hold all of the slides
    ArrayList<PresentationSlide> slides = new ArrayList<>();

    //this instantiate it
    public BottomBar(){
        this.setBackground(Color.decode("#B2D5DB"));



        for(int i = 0; i < 5; i ++){
            slides.add(new PresentationSlide());
        }
        Integer i = 0;
        for (PresentationSlide s : slides){
            i ++;
            this.add(new JButton(i.toString()) );
        }
    }

    // add function

    // remove function


}
