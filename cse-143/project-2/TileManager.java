/*
 Zachary Willson
 8 October 2015
 CSE 143 AI (Anna Pendleton)
 Homework 1 - TileManager

 This class manages Tiles drawn in a GUI window
 */

import java.awt.*;
import java.util.*;

public class TileManager {

    //ArrayList holds each Tile object
    private ArrayList<Tile> list;

    //Constructs TileManager
    //Post Condition: an empty TileManager is created
    public TileManager() {
        this.list = new ArrayList<Tile>();
    }

    //Adds Tile to the end of the list. New Tile appears on top of all other Tiles
    //Parameter passed is the new Tile object to be added
    public void addTile(Tile rect) {
        this.list.add(rect);
    }

    //Draws each Tile onto a window.
    //Parameter passed in is Graphics g to allow drawing
    public void drawAll(Graphics g) {
        for (Tile e : this.list) {
            e.draw(g);
        }
    }

    //When the user left clicks on a point, if the coordinates passed touch
    //Any tiles, the topmost of those tiles will be moved to the very top
    //of the screen. Parameters are the (x, y) position of the click.
    public void raise(int x, int y) {
        helper(x, y, 1);
    }

    //Checks to see if the click passed is contained by any Tiles area
    //Takes in position x, position y, and index i. Returns boolean value
    private boolean within(int x, int y, int i) {
        return (this.list.get(i).getX() + this.list.get(i).getWidth()) > x
                && (this.list.get(i).getY() + this.list.get(i).getHeight()) > y
                && this.list.get(i).getX() < x && this.list.get(i).getY() < y;
    }

    //Helper method takes in position x, position y, int set, and a boolean
    //Runs backward through the ArrayList of Tiles and arranges them based
    //On the click made. Condenses raise, lower, and delete into one method.
    //Operations are seperated by the integer set passed.
    //Set 0: lower   Set 1: raise   Set 2: delete
    private void helper(int x, int y, int set) {
        for (int i = this.list.size() - 1; i >= 0; i--) {
            if (within(x, y, i)) {
                if (i >= 0) {
                    if (set == 0) {
                        this.list.add(0, this.list.get(i));
                        this.list.remove(i + 1);
                    } else if (set == 1) {
                        this.list.add(this.list.get(i));
                        this.list.remove(i);
                    } else if (set == 2) {
                        this.list.remove(i);
                    }
                    i = -1;
                }
            }
        }
    }

    //When the user shift - left clicks on a point, if the coordinates passed touch
    //Any tiles, the topmost of those tiles will be moved to the very bottom
    //of the screen. Parameters are the (x, y) position of the click.
    public void lower(int x, int y) {
        helper(x, y, 0);
    }

    //When the user right clicks on a point, if the coordinates passed touch
    //Any tiles, the upper-most Tile will be deleted from the list, and 
    //Will be removed from the DrawingPanel. Parameters are the (x, y) position of the click.
    public void delete(int x, int y) {
        helper(x, y, 2);
    }

    //When the user shift - right clicks on a point, if the coordinates passed touch
    //Any tiles, all of the touching tiles will be deleted.
    //Parameters are the (x, y) position of the click.
    public void deleteAll(int x, int y) {
        for (int i = 0; i < this.list.size(); i++) {
            delete(x, y);
        }
    }

    //This method performs two functions. Called when user types S
    //F(1): Reorder (shuffle) tiles in list in a random order
    //F(2): Move each Tile into a new random (x, y) position in the window
    //Assigned by the top left x and y position of each Tile. The Tile will never be placed
    //So any of its x or y positions are negative.
    public void shuffle(int width, int height) {
        Collections.shuffle(this.list);
        Random rand = new Random();
        for (Tile e : this.list) {
            e.setX(rand.nextInt(width - e.getWidth()));
            e.setY(rand.nextInt(height - e.getHeight()));
        }
    }
}
