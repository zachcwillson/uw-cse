/*
 * Zach Willson
 * 10/15/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 4
 *
 * This will be my awesome interactive program on admission
 */
import java.util.*;

public class Admit {

    //My main method orchastrates the rest of the amazingness
    public static void main(String[] args) {
        getIntro();
        Scanner input = new Scanner(System.in);
        System.out.println("Information for applicant #1:");
        double personA = getScores(input);
        System.out.println("Information for applicant #2:");
        double personB = getScores(input);
        ender(personA, personB);
    }

    //This method compiles returns the person scores, Taking input as parameter for scanner
    public static double getScores(Scanner input) {
        System.out.print("    do you have 1) SAT scores or 2) ACT scores? ");
        if (input.nextInt() == 2) {
            return getACT(input) + getGPA(input);
        } else {
            return getSAT(input) + getGPA(input);
        }
    }

    //This method prints the ACT numbers and returns a double them to the getScores method
    public static double getACT(Scanner input) {
        double exam;
        System.out.print("    ACT English? ");
        int actE = input.nextInt();
        System.out.print("    ACT math? ");
        int actM = input.nextInt();
        System.out.print("    ACT reading? ");
        int actR = input.nextInt();
        System.out.print("    ACT science? ");
        int actS = input.nextInt();
        exam = (actE + (2 * actM) + actR + actS) / 1.8;
        System.out.println("    exam score = " + rounder(exam));
        return exam;
    }

    //This method prints the SAT numbers and returns a double them to the getScores method
    public static double getSAT(Scanner input) {
        double exam;
        System.out.print("    SAT math? ");
        double satM = input.nextDouble();
        System.out.print("    SAT critical reading? ");
        double satCR = input.nextDouble();
        System.out.print("    SAT writing? ");
        double satW = input.nextDouble();
        exam = ((2 * satM) + satCR + satW) / 32;
        System.out.println("    exam score = " + rounder(exam));
        return exam;
    }

    //This method prints the start of the program
    public static void getIntro() {
        System.out.println("This program compares two applicants to");
        System.out.println("determine which one seems like the stronger");
        System.out.println("applicant.  For each candidate I will need");
        System.out.println("either SAT or ACT scores plus a weighted GPA.");
        System.out.println();
    }

    //This method prints and returns the gpa numbers as a double each time called
    public static double getGPA(Scanner input) {
        double score;
        System.out.print("    overall GPA? ");
        double gpaActual = input.nextDouble();
        System.out.print("    max GPA? ");
        double gpaMax = input.nextDouble();
        System.out.print("    Transcript Multiplier? ");
        double multiplier = input.nextDouble();
        score = (gpaActual / gpaMax) * 100.0 * multiplier;
        System.out.println("    GPA score = " + rounder(score));
        System.out.println();
        return score;
    }

    //This method rounds my doubles to the tenths place for correct stuff
    public static double rounder(double score) {
        return Math.round(score * 10.0) / 10.0;
    }

    //This method prints out the winning student, takes in the two scores as parameters
    public static void ender(double personOne, double personTwo) {
        System.out.println("First applicant overall score  = " + rounder(personOne));
        System.out.println("Second applicant overall score = " + rounder(personTwo));
        if (personOne > personTwo) {
            System.out.println("The first applicant seems to be better");
        } else if (personOne < personTwo) {
            System.out.println("The second applicant seems to be better");
        } else {
            System.out.println("The two applicants seem to be equal");
        }
    }
}
