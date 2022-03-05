/**
 *
 * Example class that implements the Item interface
 *
 * @Author: Fehmi Neffati
 */

package Item;

import SlideMgr.*;

public class Text implements Item{

    @Override
    public void displayItem(PresentationSlide slide) {
        // TODO : DISPLAY TEXT
        slide.Display();
    }

    @Override
    public void addItem() {
        // TODO: Honestly, we might not even need this, you probably could create a constructor that would do this method

    }

    @Override
    public void removeItem() {
        // TODO: Same here with the addItem

    }
}
