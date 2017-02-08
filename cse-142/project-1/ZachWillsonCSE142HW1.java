package zachwillsoncse142hw1;
/*
 * Zach Willson
 * 09/26/2014
 * CSE142
 * TA: Ryan McMahon
 * Assignment 1
 *
 * This program will print out a nice song
 */
public class ZachWillsonCSE142HW1 {
    public static void main(String[] args) {
        String[] animals = {"fly", "spider", "bird", "cat", "dog", "pig"};
        int[] verse = {1, 2, 3, 4, 5, 6};
        String[] sayings = {"", "That wriggled and iggled and jiggled inside her.", "How absurd to swallow a bird.", "Imagine that to swallow a cat.", "What a hog to swallow a dog.", "How big to swallow a pig."};
        for (int i = 0; i < animals.length; i++) {
            verse(verse[i], animals[i], sayings[i]);
        }
        verse7("horse");
    }
    public static void verse(int num, String aa, String saying) {
        line1(aa);
        swallow(num, num, saying);
        System.out.println("I don't know why she swallowed that fly, \nPerhaps she'll die. \n ");
    }
    public static void verse7(String aa) {
        line1(aa);
        System.out.println("She died of course.");
    }
    public static void line1(String animal) {
        if (animal.equalsIgnoreCase("fly")) {
            System.out.println("There was an old woman who swallowed a " + animal + ".");
        } else {
            System.out.println("There was an old woman who swallowed a " + animal + ",");
        }
    }
    public static void swallow(int num, int num2, String saying) {
        if (!saying.equals("")) {
            System.out.println(saying);
        }
        if (num == 2) {
            System.out.println("She swallowed the spider to catch the fly,");
        } else if (num == 3) {
            System.out.println("She swallowed the bird to catch the spider,");
            swallow(num - 1, num, "");
        } else if (num == 4) {
            System.out.println("She swallowed the cat to catch the bird,");
            swallow(num - 1, num, "");
        } else if (num == 5) {
            System.out.println("She swallowed the dog to catch the cat,");
            swallow(num - 1, num, "");
        } else if (num == 6) {
            System.out.println("She swallowed the pig to catch the dog,");
            swallow(num - 1, num, "");
        }
    }
}