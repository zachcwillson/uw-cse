/*
 * Zach Willson
 * 10/23/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 5
 *
 * This is the magic guessing game where we guess between 1 and a constant!
 */
import java.util.*;

public class Guess {

    public static final int MAX = 100;

    //In a do while loop, my program keeps asking to play a game until the user doesn't say yes
    public static void main(String[] args) {
        starter();
        Random wutang = new Random();
        Scanner clan = new Scanner(System.in);
        String quitter;
        int games = 0;
        int total = 0;
        int tCounter;
        int min = 9999;
        do {
            tCounter = playGame(wutang, clan, 0); //Gets guess count from single game
            if (tCounter < min) { //Checks to see if it is smaller than the minimum
                min = tCounter;
            }
            total += tCounter; //Adds guesses to running string of games
            games++; //Adds game count by one
            System.out.print("Do you want to play again? ");
            quitter = clan.next();
            System.out.println();
        } while (quitter.startsWith("Y") || quitter.startsWith("y"));
        ender(games, total, min); //Gives statistics
    }

    //This plays a single game, taking in random, scanner and reinitializing try counter to 0
    public static int playGame(Random rand, Scanner input, int counter) {
        System.out.println("I'm thinking of a number between 1 and " + MAX + "...");
        int number = rand.nextInt(MAX) + 1;
        int guess = 9999;
        while (number != guess) {
            System.out.print("Your guess? ");
            guess = input.nextInt();
            counter++;
            if (number < guess) {
                System.out.println("Its lower.");
            } else if (number > guess) {
                System.out.println("Its higher.");
            }
        }
        System.out.println("You got it right in " + counter + " guesses");
        return counter;
    }

    //Starts my program with all the introductory println statements
    public static void starter() {
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and");
        System.out.println(MAX + " and will allow you to guess until");
        System.out.println("you get it.  For each guess, I will tell you");
        System.out.println("whether the right answer is higher or lower \nthan your guess.");
        System.out.println();
    }

    //This ends my program, taking in # of games, total guesses, and best game as parameters
    public static void ender(double games, double total, int best) {
        System.out.println("Overall Results:");
        System.out.println("    total games   = " + (int) games);
        System.out.println("    total guesses = " + (int) total);
        System.out.println("    guesses/game  = " + (total / games));
        System.out.println("    best game     = " + best);
    }
}
