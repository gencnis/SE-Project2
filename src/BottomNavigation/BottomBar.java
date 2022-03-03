package BottomNavigation;

import Slide.PresentationSlide;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BottomBar extends JPanel {
    ArrayList<PresentationSlide> slides = new ArrayList<>();

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

}
