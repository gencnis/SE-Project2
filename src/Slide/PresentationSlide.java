package Slide;
/**
 *
 * Concrete Class for a singular presentation Slide.
 * This should help us create, Display, and Delete Slides easily
 * Each slide should have a HashMap of the items it will contain
 * and each deck will be composed of many slides
 *
 *
 *
 * @Author: Fehmi Neffati
 */

import javax.swing.*;
import java.awt.*;

public class PresentationSlide implements Slide {

    //Attributes


    //Constructor


    //Methods

    @Override
    public void Display() {
    }

    @Override
    public void addSlide() {

    }

    @Override
    public void removeSlide(Slide slide) {

    }

    public static void main(String[] args) {
        PresentationSlide slide1 = new PresentationSlide();
        slide1.Display();
    }
}