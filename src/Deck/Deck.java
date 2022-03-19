/**
 *
 * This is a Deck abstract class that you could extend to make your Deck concrete class where you define all of the
 * Methods (Look at PresentationDeck.java)
 *
 * You could override those methods defined below in your conrete class to have them cater to your needs and bend them around
 *
 *
 * @Author: Fehmi Neffati
 *
 */


package Deck;

import SlideMgr.Slide;

import java.util.ArrayList;


//unused
public abstract class Deck {
    ArrayList<Slide> slides = new ArrayList<Slide>();


    public abstract void display();
    public abstract void add(Slide slide);
    public abstract void Remove(Slide slide);
    public abstract void load();
    public abstract void save();

}
