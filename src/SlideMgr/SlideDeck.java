package SlideMgr;

import BottomNavigation.BottomBar;
import FullWindow.MainFrame;

import java.util.ArrayList;

<<<<<<< Updated upstream
public class SlideDeck
=======
//The slide deck is a singleton class that holds a reference to the master slide deck that holds all the slides in the show
public class SlideDeck implements java.io.Serializable
>>>>>>> Stashed changes
{
    private static SlideDeck ref;
    private static ArrayList<Slide> slides;
    private static Slide currentSlide;
    private  Integer slideCount = 0; //unique identifier for every slide..don't think any reasonable actions would overflow
    static BottomBar bb;
<<<<<<< Updated upstream

    //eed to add slides after this is made
=======
    HashMap<String, Color> colorMap; // here I save all of the color values for easy access
    Color slideColor = Color.WHITE; //default slide color

    //makes new slide deck and initialized all possible palette colors
>>>>>>> Stashed changes
    SlideDeck()
    {
        bb = MainFrame.getBottomBar();
        slides = new ArrayList<>();


    }



    //adds slide to end of slide Deck
    public  void addSlide()
    {
        Slide s = new Slide(slideCount);
        slideCount++; //increment unique identifier
        slides.add(s);

        MainFrame.updateSlideShow(s, true);

        bb.addSlideButton(slides.indexOf(s), s);
        updateSlideNumDisplay();
        MainFrame.showSlide(s);

    }

<<<<<<< Updated upstream
=======
    //adds specified Slide to end of slide deck
    //used when loading project files
    public void addSlide(Slide s)
    {
        slideCount++; //increment unique identifier
        slides.add(s);

        MainFrame.updateSlideShow(s, true);

        bb.addSlideButton(slides.indexOf(s), s);
        updateSlideNumDisplay();
        MainFrame.showSlide(s);
    }

    //inserts specified slide at specified index in slide deck
    //used for loading templates
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
>>>>>>> Stashed changes

    //inserts slide at specified index
    //used when Insert Slide is selected
    public void addSlide(Integer index)
    {
        Slide s = new Slide(slideCount);
        slideCount++;
        slides.add(index, s);


        MainFrame.updateSlideShow(s, true);
        bb.addSlideButton(slides.indexOf(s), s);
       // currentSlide = s;
        updateSlideNumDisplay(); //updates numbers on slides
        MainFrame.showSlide(s);
    }

    //removes currently displayed slide from slide deck
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

<<<<<<< Updated upstream
    public static void removeSlide(Integer index) //remove specified slide
=======
    public static BottomBar getBottomBar(){return bb;}

    //remove slide at specified index
    public static void removeSlide(Integer index)
>>>>>>> Stashed changes
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

    //replaces current slide with specified slide
    //used in template loading
    public  void setCurrentSlide(Slide s)
    {
        currentSlide = s;
    }

    public  Slide getCurrentSlide(){return currentSlide;}

    //revalidates all slide numbers in presentation are correct for each slide
    public void updateSlideNumDisplay()
    {
        for (Slide s: slides)
        {
            s.upDateSlideNumber();
        }
    }


    public ArrayList<Slide> getSlides(){return slides;}


    //singleton constructor returns only one slide deck instance
    public static SlideDeck getSlideDeck()
    {
        // we can call this constructor
        if (ref == null)
            ref = new SlideDeck();
        return ref;
    }

<<<<<<< Updated upstream
=======
    //unused I think
    //was thought of as a solution to loading a whole deck from project file but did not work well
    public static void setDeck(SlideDeck slideDeck)
    {
        ref = slideDeck;
    }
    public Slide getSlide(int n){
        return slides.get(n);
    }

>>>>>>> Stashed changes

}
