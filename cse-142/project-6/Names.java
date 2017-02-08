/*
 * Zach Willson
 * 10/23/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 6
 * 
 * This project prompts a user for input and creates a graph if the inputed vaules matched
 * what is in the file!
 */

import java.io.*;
import java.util.*;
import java.awt.*;

public class Names {

    public static final int DECADENUM = 14;
    public static final int STARTYEAR = 1880;
    public static final int DISTANCE = 70;
    public static final String TEXT = "names.txt";

    //This is main method, it throws the FileNotFoundException and starts off, then searches for
    //The name and gender inputed by user in the text file
    public static void main(String[] args) throws FileNotFoundException {
        start();
        Scanner input = new Scanner(System.in);
        System.out.print("name? ");
        String nameInput = input.nextLine();
        System.out.print("gender (M or F)? ");
        String genderGiven = input.nextLine();
        if (search(new Scanner(new File(TEXT)), nameInput, genderGiven, 0, true, 0)) {
            System.out.println("name/gender combination not found");
        }
    }

    //This method draws the starting black lines for the DrawingPanel
    //I push in parameter Graphics g to allow me to draw
    public static void drawLi(Graphics g) {
        g.drawLine(0, 25, width(), 25);
        g.drawLine(0, 525, width(), 525);
        for (int i = 0; i <= DECADENUM; i++) {
            g.drawLine(DISTANCE * i, 0, DISTANCE * i, 550);
            g.drawString(String.valueOf(STARTYEAR + (10 * (i - 1))), DISTANCE * (i - 1), 550);
        }
        g.setColor(Color.red);
    }

    //Calculates the width of my DrawingPanel dependent on the number of decades and distance
    public static int width() {
        return DECADENUM * DISTANCE;
    }

    //Divides my numbers by 2. I did it a lot so I thought it useful to put in its own method
    public static int dTwo(int num) {
        return num / 2;
    }

    //This draws the last name to fix a bug where it always put the name on the top
    //Parameters last, name, gender, start, and graphics g allows me to push all the variables in
    public static void enderDraw(int last, String name, String gender, int start, Graphics g) {
        if (last != 0) {
            g.drawString(startWrite(name, gender, last), start, 24 + dTwo(last));
        } else {
            g.drawString(startWrite(name, gender, last), start, 525);
        }
    }

    //This parameter combines 2 given strings and a number to print in the chart
    public static String startWrite(String name, String gender, int num) {
        return name + " " + gender + " " + num;
    }

    //This starts my program. A bunch of println statements with a reference to my global variable
    public static void start() {
        System.out.println("This program allows you to search through the");
        System.out.println("data from the Social Security Administration");
        System.out.println("to see how popular a particular name has been");
        System.out.println("since " + STARTYEAR + ".");
        System.out.println();
    }

    //I noticed reduncancy so I made a new method to shorten it
    //Strings, integers, and graphics g are passed through to do all I need
    public static void drawSame(String name, String gender, int start, int lastNum, Graphics g) {
        g.drawString(startWrite(name, gender, lastNum), start, 24 + dTwo(lastNum));
    }

    //I noticed reduncancy again so I made a new method to shorten it
    //Strings, integers, and graphics g are passed through to do all I need
    public static void drawSame2(String name, String gender, int start, int lastNum, Graphics g) {
        g.drawString(startWrite(name, gender, lastNum), start, 525);
    }

    //returns num divided by 2 plus 25
    public static int five(int num) {
        return 25 + dTwo(num);
    }

    //returns num divided by 2 plus 24
    public static int four(int num) {
        return 24 + dTwo(num);
    }

    //This method scans through the txt file. Once it finds matching strings for name and gender
    //It scans on the same line for the integers following the match. It then graphs the points
    public static boolean search(Scanner fileRead, String nameInput, String genderGiven,
                                                int nextNum, boolean notFound, int start) {
        while (fileRead.hasNextLine()) {
            String name = fileRead.next();
            String gender = fileRead.next();
            if (name.equalsIgnoreCase(nameInput) && gender.equalsIgnoreCase(genderGiven)) {
                notFound = false;
                DrawingPanel p = new DrawingPanel(width(), 550);
                Graphics g = p.getGraphics();
                drawLi(g);
                int lastNum = fileRead.nextInt();
                while (fileRead.hasNextInt()) {
                    nextNum = fileRead.nextInt();
                    if (lastNum != 0 && nextNum != 0) {
                        if (lastNum % 2 == 0) {
                            if (nextNum % 2 != 0) {
                                g.drawLine(start, 24 + dTwo(lastNum), start + DISTANCE,
                                                                            five(nextNum));
                                drawSame(name, gender, start, lastNum, g);
                            } else {
                                g.drawLine(start, 24 + dTwo(lastNum), start + DISTANCE,
                                                                            four(nextNum));
                                drawSame(name, gender, start, lastNum, g);
                            }
                        } else {
                            g.drawLine(start, 25 + dTwo(lastNum), start + DISTANCE, four(nextNum));
                            g.drawString(startWrite(name, gender, lastNum), start, five(lastNum));
                        }
                    } else if (lastNum == 0 && nextNum != 0) {
                        g.drawLine(start, 525, start + DISTANCE, five(nextNum));
                        drawSame2(name, gender, start, lastNum, g);
                    } else if (lastNum != 0 && nextNum == 0) {
                        g.drawLine(start, 24 + dTwo(lastNum), start + DISTANCE, 525);
                        drawSame(name, gender, start, lastNum, g);
                    } else {
                        g.drawLine(start, 525, start + DISTANCE, 525);
                        drawSame2(name, gender, start, lastNum, g);
                    }
                    start += DISTANCE;
                    lastNum = nextNum;
                }
                enderDraw(lastNum, name, gender, start, g);
            }
            fileRead.nextLine();
        }
        return notFound;
    }
}
