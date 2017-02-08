/*
 * Zach Willson
 * 12/3/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 9
 *
 * This is my lion class
 */
import java.awt.*;
import java.util.*;

public class Lion extends Critter {

    //Initializes a color variable, as well as a random and string name and a few ints
    private Color color;
    private String name;
    private Random ran;
    private int option;
    private int count;

    //Constructs the object, sets name to "L", creates a random object, and gets the color
    public Lion() {
        this.name = "L";
        this.ran = new Random();
        this.count = 0;
        this.color = this.getColor();
    }

    //Returns the color of the object
    public Color getColor() {
        return this.color;
    }

    //Returns the string for the name
    public String toString() {
        return this.name;
    }

    //Changes the color every 3 turns and then decides how to move
    public Action getMove(CritterInfo info) {
        if (this.count % 3 == 0) {
            this.option = this.ran.nextInt(3);
            if (this.option == 0) {
                this.color = Color.RED;
            } else if (this.option == 1) {
                this.color = Color.GREEN;
            } else {
                this.color = Color.BLUE;
            }
        }
        this.count++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }
}
