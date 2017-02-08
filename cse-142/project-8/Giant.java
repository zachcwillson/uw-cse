/*
 * Zach Willson
 * 12/3/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 9
 *
 * This is my giant class
 */
import java.awt.*;

public class Giant extends Critter {

    //Creates some variables
    private Color color;
    private String name;
    private int count;

    //Constructs object, setting color to gray, the first name, and count to 0
    public Giant() {
        this.color = Color.GRAY;
        this.name = "fee";
        this.count = 0;
    }

    //Returns the string for the name
    public String toString() {
        return this.name;
    }

    //Returns the color of the object
    public Color getColor() {
        return this.color;
    }

    //First, every 6 moves, we move between fee, fie, foe, and fum.
    //Second, we decide how to move
    public Action getMove(CritterInfo info) {
        if (this.count < 6) {
            this.name = "fee";
        } else if (this.count < 12) {
            this.name = "fie";
        } else if (this.count < 18) {
            this.name = "foe";
        } else if (this.count < 24) {
            this.name = "fum";
            if (this.count == 23) {
                this.count = 0;
            }
        }
        this.count++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }
}
