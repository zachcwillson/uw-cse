package asciiart;

/**
 * Zach Willson
 * 10/07/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 2a
 * 
 * This will print my ASCII art of cotton candy!
 * 
 */
public class AsciiArt {

    public static void main(String[] args) {
        partA();
    }

    public static void partA() {
        System.out.println(" ______   ");
        for (int size = 0; size < 2; size++) {
            System.out.println("(      )");
        }
        System.out.println("( ^  ^ )");
        System.out.println("(   >  )");
        System.out.println("(   U  )");
        System.out.println(" (    )");
        System.out.println("  (  )");
        for (int i = 0; i < 3; i++){
            System.out.println("   ||");
        }
    }
}
