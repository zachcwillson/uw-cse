/*
 Zachary Willson
 3 December 2015
 CSE 143 AI (Anna Pendleton)
 Homework 7 - 20 Questions

 QuestionTree class allows us to play the game 20 Questions. Methods include a constructor,
 method to play the game, method to load games past or save current game, and two methods to
 return the number of games played and number of games the computer has won.
*/

import java.util.*;
import java.io.*;

public class QuestionTree {

    //Fields to store all data
    //UI covers both visual and console based
    private QuestionNode overAllTree;
    private int totalGames;
    private int gamesWon;
    private UserInterface ui;

    //Constructs QuestionTree object, parameter passed is UserInterface
    //Throws IllegalArgumentException if UserInterface is null
    //Sets initial Tree to have computer as only node and play counts to 0
    public QuestionTree(UserInterface ui) {
        exceptions(ui);
        this.ui = ui;
        this.totalGames = 0;
        this.gamesWon = 0;
        this.overAllTree = new QuestionNode("computer");
    }

    //Plays game!
    public void play() {
        this.overAllTree = play(this.overAllTree);
    }

    //Passes in QuestionNode root and checks if it is a answer or not. If answer, asks and if
    //Correct it wins, however if it is wrong and an answer the computer asks for the object the
    //player was thinking. If overAllTree does not have the guessed object, play will add a node
    //to the tree. Returns QuestionNode
    private QuestionNode play(QuestionNode root) {
        if (root.isLeafNode()) {
            this.totalGames++;
            this.ui.print("Would your object happen to be " + root.getData() + "?");
            String firstAnswer = this.ui.nextLine();
            if (firstAnswer.trim().toLowerCase().startsWith("n")) {
                this.ui.print("I lose. What is your object?");
                String object = this.ui.nextLine();
                this.ui.print("Type a yes/no question to distinguish your item from "
                        + root.getData() + ": ");
                String question = this.ui.nextLine();
                this.ui.print("And what is the answer for your object? ");
                boolean answer = this.ui.nextBoolean();
                //If answer is no, adds object to right side, otherwise add to left side
                if (!answer) {
                    return new QuestionNode(question, root, new QuestionNode(object));
                } else {
                    return new QuestionNode(question, new QuestionNode(object), root);
                }
            } else if (firstAnswer.trim().toLowerCase().startsWith("y")) {
                this.ui.println("I win!");
                this.gamesWon++;
            }
        } else {
            //This is if the root is not a leaf node
            this.ui.print(root.getData());
            boolean answer = this.ui.nextBoolean();
            if (answer) {
                root.setLeft(play(root.getLeft()));
            } else {
                root.setRight(play(root.getRight()));
            }
        }
        return root;
    }

    //Throws IllegalArgumentException if object passed is equal to null
    private void exceptions(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }

    //Saves the current game to file, throws IllegalArgumentException if PrintStream passed is
    //null. Saved in Pre-Order Traversal.
    public void save(PrintStream output) {
        exceptions(output);
        save(output, this.overAllTree);
    }

    //Saves current game to file, parameters are PrintStream and a QuestionNode
    //Recurses to move through tree
    private void save(PrintStream output, QuestionNode root) {
        if (root.isLeafNode()) {
            output.println("A:" + root.getData());
        } else {
            output.println("Q:" + root.getData());
            save(output, root.getLeft());
            save(output, root.getRight());
        }
    }

    //Throws IllegalArgumentException if scanner passed is null. Loads game in from scanner
    //PreCondition: scanner has correct format. PostCondition: game created
    //Saved in Pre-Order Traversal.
    public void load(Scanner input) {
        exceptions(input);
        this.overAllTree = loadHelp(input, "");
    }

    //Takes in scanner input, grabs the next line, if its a question then sets the data to the
    //Question and then recurses for the left and right nodes. If it is an answer, it does not
    //Recurse and instead sets the data field to the line grabbed from scanner, minus the starting
    //Identifier
    private QuestionNode loadHelp(Scanner in, String line) {
        if (in.hasNextLine()) {
            line = in.nextLine();
        }
        if (line.startsWith("Q:")) {
            return new QuestionNode(line.substring(2), loadHelp(in, ""), loadHelp(in, ""));
        } else {
            return new QuestionNode(line.substring(2));
        }
    }

    //Returns the total number of games played
    public int totalGames() {
        return this.totalGames;
    }

    //Returns the total numbers of games won by the computer
    public int gamesWon() {
        return this.gamesWon;
    }
}