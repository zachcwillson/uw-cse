/*
 * Zach Willson
 * 12/3/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 9
 *
 * This is my bear class
 */
import java.awt.*;

public class Bear extends Critter {

    //A counter, color, and name variable intializers
    private int count;
    private Color color;
    private String name;

    //Constructor that decides between the color and initializes the name and count
    public Bear(Boolean polar) {
        if (polar) {
            this.color = Color.WHITE;
        }
        this.name = "/";
        this.count = 1;
    }

    //Returns the color of the object
    public Color getColor() {
        return this.color;
    }

    //Returns the string for the name
    public String toString() {
        return this.name;
    }

    //Switches every other turn from / to \, and then moves as the bear is supposed to
    public Action getMove(CritterInfo info) {
        this.count++;
        if (this.count % 2 == 0) {
            this.name = "/";
        } else {
            this.name = "\\";
        }
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }
}
