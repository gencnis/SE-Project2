package SlideMgr;

import BottomNavigation.BottomBar;
import FullWindow.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**Holds the master list of Slides used by the whole program
 * Keeps BottomBar up to date if slides are removed or added.
 *
 * Author: Robert
 */
public class SlideDeck implements java.io.Serializable
{
    private static SlideDeck ref; //singleton
    private static ArrayList<Slide> slides; //master slide deck
    private static Slide currentSlide;
    private  Integer slideCount = 0; //unique identifier for every slide..don't think any reasonable actions would overflow
    static BottomBar bb;

    //Fehmi's Color Map
    HashMap<String, Color> colorMap; // here I save all of the color values for easy access
    Color slideColor = Color.WHITE;



    /**Costructor - initializes the main slide deck and Color Map
     * Need to add slides AFTER this is made
     */
    SlideDeck()
    {
        bb = MainFrame.getBottomBar();
        slides = new ArrayList<>();

        //Fehmi's Color Map
        colorMap = new HashMap<>();
        //colorMap.put("BLACK", Color.BLACK );  // Black
        colorMap.put("RED", Color.RED );    // Red
        colorMap.put("GREEN", Color.GREEN );    // Green
        colorMap.put("BLUE", Color.BLUE );    // Blue
        colorMap.put("YELLOW", Color.YELLOW);

    }


    /**Adds a new slide to the end of the slide deck
     *
     */
    public  void addSlide()
    {
        Slide s = new Slide(slideCount);
        s.setBackground(slideColor);
        slideCount++; //increment unique identifier
        slides.add(s);

        MainFrame.updateSlideShow(s, true);

        bb.addSlideButton(slides.indexOf(s), s);
        updateSlideNumDisplay();
        MainFrame.showSlide(s);

    }

    /**Adds specified slide to end of the slide deck.
     * Used when loading a project to populate an empty slide deck.
     *
     * @param s - the slide we wish to add to the deck
     */
    public void addSlide(Slide s)
    {
        slideCount++; //increment unique identifier
        slides.add(s);

        MainFrame.updateSlideShow(s, true);

        bb.addSlideButton(slides.indexOf(s), s);
        updateSlideNumDisplay();
        MainFrame.showSlide(s);
    }

    /**Adds specified slide at a specified index in the slide deck
     * Used when loading slides from project file.
     *
     * @param s - the slide we wish to as to the deck
     * @param index - the index at which we want to insert the slide
     */
    public void addSlide(Slide s, Integer index)
    {
        slideCount++;
        slides.add(index, s);


        MainFrame.updateSlideShow(s, true);
        bb.addSlideButton(slides.indexOf(s), s);
        // currentSlide = s;
        updateSlideNumDisplay(); //updates numbers on slides
        MainFrame.showSlide(s);
    }

    /**Changes the slide color based on specified color.
     * Called when inserting painted slides.
     *
     * @param c - the target color we wish to change slides to
     */
    public void setSlideColor(String c) {
        this.slideColor = colorMap.get(c);
    }

    /**Adds a blank slide at specificed index
     *
     * @param index - the index of the slideshow we wisht to insert a new slide at
     */
    public void addSlide(Integer index)
    {

        Slide s = new Slide(slideCount);
        s.changeBGColor(slideColor);;
        slideCount++;
        slides.add(index, s);


        MainFrame.updateSlideShow(s, true);
        bb.addSlideButton(slides.indexOf(s), s);
       // currentSlide = s;
        updateSlideNumDisplay(); //updates numbers on slides
        MainFrame.showSlide(s);
    }

    /**Removes the currently displayed slide from the slide deck
     * Updates Bottom Bar display accordingly
     *
     */
    public  void removeSlide()
    {
        Integer currentSlideIndex = slides.indexOf(currentSlide);
        Slide previousSlide;
        if(slides.size() > 0)
        {
            if(currentSlideIndex > 0)
                previousSlide = slides.get(currentSlideIndex - 1); //get previous slide
            else if(currentSlideIndex == 0)
                previousSlide = slides.get(currentSlideIndex + 1); //if deleting the beginning, get next slide
            else
            {
                previousSlide = null;
                System.out.println("There is a problem removing slides Slide Deck - RemoveSlide()");
            }



            Slide temp = currentSlide; //get temporary reference
            if(previousSlide != null)
                MainFrame.showSlide(previousSlide); //also updates currentSlide so need to get the temporary reference first



//          TODO: Call whatever you need to update the Front End bottom bar buttons here.
            bb.removeSlideButton(temp);
            slides.remove(temp);
            updateSlideNumDisplay(); //updates numbers on slides
            MainFrame.updateSlideShow(temp, false); //removes from slideshow
            temp.clearSlide();

            //temp gets destroyed after this

        }
        else
        {
            currentSlide.clearSlide();
        }

    }

    public static BottomBar getBottomBar(){return bb;}

    /**Removes slide at specified index
     *
     * @param index - index of slide we wish to remove
     */
    public static void removeSlide(Integer index) //remove specified slide
    {
        Slide previousSlide;
        if(slides.size() > 0)
        {
            if(index > 0)
                previousSlide = slides.get(index- 1); //get previous slide to show
            else if(index== 0)
                previousSlide = slides.get(index + 1); //if deleting the beginning, get next slide to show
            else
            {
                previousSlide = null;
                System.out.println("There is a problem removing slides Slide Deck - RemoveSlide()");
                System.out.println("Provided Index: " + index);
            }





            Slide temp = slides.get(index); //get temporary reference
            if(previousSlide != null)
                MainFrame.showSlide(previousSlide); //also updates currentSlide so need to get the temporary reference first



//          TODO: Call whatever you need to update the Front End bottom bar buttons here.
            bb.removeSlideButton(temp);
            slides.remove(temp);
            MainFrame.updateSlideShow(temp, false); //removes from slideshow
            temp.clearSlide();



        }
        else
        {
            currentSlide.clearSlide();
        }

    }

    public  void setCurrentSlide(Slide s)
    {
        currentSlide = s;
    }

    public  Slide getCurrentSlide(){return currentSlide;}

    /**Refreshes the slide numbers to be displayed for the whole slide deck
     *
     */
    public void updateSlideNumDisplay()
    {
        for (Slide s: slides)
        {
            s.upDateSlideNumber();
        }
    }


    public ArrayList<Slide> getSlides(){return slides;}

    public static SlideDeck getSlideDeck()
    {
        // we can call this constructor
        if (ref == null)
            ref = new SlideDeck();
        return ref;
    }

    /**Sets a whole slide deck....ONLY TO BE USED WHEN LEADING PROJECTS
     *
     * @param slideDeck - slideDeck from save file to be loaded
     */
    public static void setDeck(SlideDeck slideDeck)
    {
        ref = slideDeck;
    }
    public Slide getSlide(int n){
        return slides.get(n);
    }


}
