/**
 *
 * This is the class that you would extend and then start working on your functions and methods
 *
 *
 *
 * @Author : FEHMI NEFFATI
 */

package Slide;

import Item.Item;

import java.util.ArrayList;

public abstract class Slide {
    ArrayList<Item> allItems = new ArrayList<Item>();


    void display(){
        // TODO: DISPLAY A SLIDE
    }
    void add(){
        // TODO: ADD AN ITEM TO THE LIST OF ITEMS OF THIS SLIDE
    }

    void save(ArrayList<Item> allItems){
        // TODO:
    }

    void Remove(Item item){
    }
}
