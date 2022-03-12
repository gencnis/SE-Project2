package SlideMgr;

import BottomNavigation.BottomBar;
import FullWindow.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SlideDeck implements java.io.Serializable
{
    private static SlideDeck ref;
    private static ArrayList<Slide> slides;
    private static Slide currentSlide;
    private  Integer slideCount = 0; //unique identifier for every slide..don't think any reasonable actions would overflow
    static BottomBar bb;
    HashMap<String, Color> colorMap; // here I save all of the color values for easy access
    Color slideColor = Color.WHITE;
    //eed to add slides after this is made
    SlideDeck()
    {
        bb = MainFrame.getBottomBar();
        slides = new ArrayList<>();

        colorMap = new HashMap<>();
        //colorMap.put("BLACK", Color.BLACK );  // Black
        colorMap.put("RED", Color.RED );    // Red
        colorMap.put("GREEN", Color.GREEN );    // Green
        colorMap.put("BLUE", Color.BLUE );    // Blue
        colorMap.put("YELLOW", Color.YELLOW);

    }



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

    public void addSlide(Slide s)
    {
        slideCount++; //increment unique identifier
        slides.add(s);

        MainFrame.updateSlideShow(s, true);

        bb.addSlideButton(slides.indexOf(s), s);
        updateSlideNumDisplay();
        MainFrame.showSlide(s);
    }

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


    public void setSlideColor(String c) {
        this.slideColor = colorMap.get(c);
    }

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

    public static void setDeck(SlideDeck slideDeck)
    {
        ref = slideDeck;
    }
    public Slide getSlide(int n){
        return slides.get(n);
    }


}
