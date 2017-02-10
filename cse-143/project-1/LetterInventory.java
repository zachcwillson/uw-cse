/*
 Zachary Willson
 30 September 2015
 CSE 143A
 Quiz Section AJ ()
 Homework 1 - LetterInventory

 This class takes a count of each occurance of a letter in a string
 */

public class LetterInventory {

    //Array holding count
    //Integer holding sum of all the indexes of letters[]
    //Static FINAL Variables: 'a' and 26 to help readability
    private int[] letters;
    private int size;
    public static final char A = 'a';
    public static final int MAX_SIZE = 26;

    //Constructs LetterInventory
    //Post Condition: an empty LetterInventory is created
    public LetterInventory() {
        this("");
    }

    //Constructs LetterInventory
    //Post Condition: a LetterInventory 26 spaces long is created with a count of each letter
    public LetterInventory(String data) {
        data = data.toLowerCase();
        this.letters = new int[MAX_SIZE];
        for (int i = 0; i < data.length(); i++) {
            char close = data.charAt(i);
            if (close - A <= MAX_SIZE - 1 && close - A >= 0) {
                this.letters[close - A]++;
                this.size++;

            }
        }
    }

    //Returns the integer value of the count of the character passed into the method
    public int get(char letter) {
        letter = Character.toLowerCase(letter);
        checker(letter);
        return this.letters[letter - A];
    }

    //Pre Condition: letter count is set as before
    //Sets the index of character letter to the integer value passed in the set method
    //Post Condition: index of letter is now the value of value
    public void set(char letter, int value) {
        letter = Character.toLowerCase(letter);
        checker(letter);
        this.size = this.size - this.letters[letter - A] + value;
        this.letters[letter - A] = value;
    }

    //Returns size of LetterInventory
    public int size() {
        return this.size;
    }

    //Returns the boolean if LetterInventory is empty
    public boolean isEmpty() {
        return this.size == 0;
    }

    //Constructs LetterInventory into a string with []'s closing it out
    public String toString() {
        String returner = "[";
        for (int letterIncrement = 0; letterIncrement < MAX_SIZE; letterIncrement++) {
            for (int i = 0; i < this.letters[letterIncrement]; i++) {
                returner += (char) (A + letterIncrement);
            }
        }
        return returner + "]";
    }

    //Adds one inventory to another and adds the indexes to eachother
    //Post Condition: One LetterInventory is returned with the sum of each individual index
    public LetterInventory add(LetterInventory other) {
        LetterInventory newOne = new LetterInventory();
        for (int letterIn = 0; letterIn < MAX_SIZE; letterIn++) {
            newOne.letters[letterIn] = other.letters[letterIn] + this.letters[letterIn];
        }
        newOne.size = other.size + this.size;
        return newOne;
    }

    //Subracts one inventory to another and subtracts the indexes to eachother
    //Post Condition: One LetterInventory is returned with the difference of each index
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory newOne = new LetterInventory();
        newOne.size = other.size + this.size;
        for (int letterIn = 0; letterIn < MAX_SIZE; letterIn++) {
            if (this.letters[letterIn] - other.letters[letterIn] < 0) {
                return null;
            }
            newOne.letters[letterIn] = this.letters[letterIn] - other.letters[letterIn];
        }
        newOne.size = this.size - other.size;
        return newOne;
    }

    //Returns a double 0.0-1.0 of the percent of letters that is in the LetterInventory
    public double getLetterPercentage(char letter) {
        checker(letter);
        if (this.size == 0) {
            return 0.0;
        }
        return (double) this.letters[letter - A] / (double) this.size;
    }

    //Checks to see if the character letter is outside the bounds of A-Z, returns a Exception
    private void checker(char letter) {
        if (letter - A < 0 || letter - A > MAX_SIZE - 1) {
            throw new IllegalArgumentException("Character outside of A-Z");
        }
    }
}
