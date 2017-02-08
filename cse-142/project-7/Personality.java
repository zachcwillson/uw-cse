import java.io.*;
import java.util.*;

/*
 * Zach Willson
 * 10/23/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 7
 *
 * This porject asks a user for file to input into the program, parses through the file
 * and picks out a name and a string of answers. The program then sorts through the answers
 * and delegates the name as a given personality type. The computer then writes this information
 * to a file named by the user.
 */

public class Personality {

    public static final int FOUR = 4;

    public static void main(String[] args) throws FileNotFoundException {
        starter();
        char[] type1 = {'E', 'S', 'T', 'J'};
        char[] type2 = {'I', 'N', 'F', 'P'};
        Scanner inputIn = new Scanner(System.in);
        System.out.print("input file name? ");
        String inputFile = inputIn.nextLine();
        System.out.print("output file name? ");
        String outputFile = inputIn.nextLine();
        test(new PrintStream(outputFile), new Scanner(new File(inputFile)), type1, type2);
    }

    //While scanner has a nextLine, it takes the name and answers and sends the info to sort and
    //process the data. It ends by printing the data to the given output file. Parameters are
    //a output file (PrintStream) a scanner object, and the two char options.
    public static void test(PrintStream out, Scanner scan, char[] type1, char[] type2) {
        while (scan.hasNextLine()) {
            double[] aCounter = new double[FOUR];
            double[] bCounter = new double[FOUR];
            int[] percent = new int[FOUR];
            String name = scan.nextLine();
            String answers = scan.nextLine();
            sortTheAnswers(answers, aCounter, bCounter);
            String type = printType(percent, bCounter, aCounter, type1, type2, "");
            out.println(name + ": " + Arrays.toString(percent) + " = " + type);
        }
    }

    //Decides which question type an answer is. Takes in a double array and int i which is the
    //number it is in the long answers string.
    public static void sort(double[] counter, int i) {
        if (i % 7 == 0) {
            counter[0]++;
        } else if (i % 7 == 1 || i % 7 == 2) {
            counter[1]++;
        } else if (i % 7 == 3 || i % 7 == 4) {
            counter[2]++;
        } else {
            counter[3]++;
        }
    }

    //Takes in parameters int[] percent, and two double[] counters, both char[] types, and an
    //empty string. This went through the four types and put together and returned the string
    //that represented the personality type. The if statment decides which delegation it gets.
    public static String printType(int[] percent, double[] bCounter, double[] aCounter,
                                                char[] type1, char[] type2, String type) {
        for (int i = 0; i < FOUR; i++) {
            percent[i] = (int) Math.round(100.0 * bCounter[i] / (bCounter[i] + aCounter[i]));
            if (percent[i] < 50) {
                type += type1[i];
            } else if (percent[i] > 50) {
                type += type2[i];
            } else {
                type += "X";
            }
        }
        return type;
    }

    //Pulls in the long string of answers, and the two doubles counters, then checks each
    //char to see which it is. It skips if its a "-". If its a match it calls teh sort method to
    //see which question type it was.
    public static void sortTheAnswers(String answers, double[] aCounter, double[] bCounter) {
        for (int i = 0; i < answers.length(); i++) {
            if (answers.charAt(i) == 'a' || answers.charAt(i) == 'A') {
                sort(aCounter, i);
            } else if (answers.charAt(i) == 'b' || answers.charAt(i) == 'B') {
                sort(bCounter, i);
            }
        }
    }

    //Simple start method prints the beginning text.
    public static void starter() {
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter.  It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }
}
