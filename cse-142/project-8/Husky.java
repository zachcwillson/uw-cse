/*
 * Zach Willson
 * 12/3/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 9
 *
 * This is my husky class
 */
import java.awt.*;
import java.util.*;

public class Husky extends Critter {

    //Creates some variables
    private Color color;
    private String name;
    private Random ran;

    //Constructs object, setting color to dark gray, the first name, and a random
    public Husky() {
        this.color = Color.DARK_GRAY;
        this.name = "Z";
        this.ran = new Random();
    }

    //Returns the color of the object
    public Color getColor() {
        return this.color;
    }

    //Returns the string for the name
    public String toString() {
        return this.name;
    }

    //First, we see if the critter has any neighbors, and if there is an enemy, we move away
    //Second, if there is an enemy in front, we will infect them. 
    //Next, we decide how to turn depending on a random
    public Action getMove(CritterInfo info) {
        if (info.getBack() == Neighbor.OTHER || info.getLeft() == Neighbor.OTHER ||
                                                    info.getRight() == Neighbor.OTHER) {
            return Action.HOP;
        } else if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL) {
            return Action.LEFT;
        } else {
            if (this.ran.nextBoolean()) {
                return Action.RIGHT;
            } else {
                return Action.LEFT;
            }
        }
    }
}
