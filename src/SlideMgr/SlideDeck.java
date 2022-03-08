package SlideMgr;

import BottomNavigation.BottomBar;
import FullWindow.MainFrame;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.util.ArrayList;

public class SlideDeck
{
    private static SlideDeck ref;
    private static ArrayList<Slide> slides;
    private static Slide currentSlide;
    private  Integer slideCount = 0; //unique identifier for every slide..don't think any reasonable actions would overflow
    static BottomBar bb;

    //eed to add slides after this is made
    SlideDeck()
    {
        bb = MainFrame.getBottomBar();
        slides = new ArrayList<>();


    }



    public  void addSlide()
    {
        Slide s = new Slide(slideCount);
        slideCount++; //increment unique identifier
        slides.add(s);

        MainFrame.updateSlideShow(s, true);

        bb.addSlideButton(slides.indexOf(s), s);

        MainFrame.showSlide(s);

    }


    public void addSlide(Integer index)
    {
        Slide s = new Slide(slideCount);
        slideCount++;
        s.setBackground(Color.WHITE);
        slides.add(index, s);


        MainFrame.updateSlideShow(s, true);
        bb.addSlideButton(slides.indexOf(s), s);
       // currentSlide = s;

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




    public ArrayList<Slide> getSlides(){return slides;}

    public static SlideDeck getSlideDeck()
    {
        // we can call this constructor
        if (ref == null)
            ref = new SlideDeck();
        return ref;
    }
    public Slide getSlide(int n){
        return slides.get(n);
    }


}
