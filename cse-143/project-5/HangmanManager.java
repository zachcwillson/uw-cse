/*
 Zachary Willson
 12 November 2015
 CSE 143 AI - Anna Pendleton
 Homework 5 - HangmanManager

 This Class manages the game evil hangman. Methods include a Constructor
 a method to return a Set of words that are being considered, the number of
 guessese left, a set of the guesses currently made, and a method to record a guess.
*/

import java.util.*;

public class HangmanManager {

    //Used sorted type of set to keep everything alphabetical order
    //Stores current pattern and guesses left
    private SortedSet<String> changingDictionary;
    private SortedSet<Character> charSet;
    private String pattern;
    private int guessLeft;

    //Exceptions: throws an IllegalArgumentException if length passed are < 1 or max < 0
    //Finds all strings in dictionary that match length passed and add
    //into a grouping of possible words
    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException();
        }
        this.pattern = "";
        for (int i = 0; i < length; i++) {
            this.pattern += "-";
        }
        this.changingDictionary = new TreeSet<String>();
        this.charSet = new TreeSet<Character>();
        this.guessLeft = max;
        for (String word : dictionary) {
            if (word.length() == length) {
                this.changingDictionary.add(word);
            }
        }
    }

    //Returns a set of the current words in the dictionary
    public Set<String> words() {
        return this.changingDictionary;
    }

    //Returns how many guesses the user has left
    public int guessesLeft() {
        return this.guessLeft;
    }

    //Returns a set of the guesses made by the client in alphabetical order
    public SortedSet<Character> guesses() {
        return this.charSet;
    }

    //Exceptions: throws an IllegalStateException if the dictionary is empty
    //Creates a pattern based on the words in the list and returns that string
    public String pattern() {
        if (this.changingDictionary.isEmpty()) {
            throw new IllegalStateException();
        }
        return spacePattern(this.pattern, this.pattern.length());
    }

    //Exception: throws IllegalStateException if no guesses are left, or if dictionary is empty
    //Exception: throws IllegalArgumentException if dictionary is empty and a guess of char has
    //been made
    //Takes in a char parameter and checks to see if that is in the pattern, returns the number
    //of times the letter occurs in the current pattern
    public int record(char guess) {
        if (this.guessLeft < 1 || this.changingDictionary.isEmpty()) {
            throw new IllegalStateException();
        } else if (!this.changingDictionary.isEmpty() && this.charSet.contains(guess)) {
            throw new IllegalArgumentException();
        }
        this.charSet.add(guess);
        this.changingDictionary = new TreeSet<String>(createMap(guess, 0).get(this.pattern));
        int guessCount = getGuessCount(guess, 0);
        if (guessCount < 1) {
            this.guessLeft--;
        }
        return guessCount;
    }

    //Maps out every remaining pattern and returns that map of type <String, TreeSet<String>>
    //Post: guess is mapped and returns a map with all the patterns
    private Map<String, TreeSet<String>> createMap(char guess, int temp) {
        Map<String, TreeSet<String>> mapper = new TreeMap<String, TreeSet<String>>();
        for (String current : this.changingDictionary) {
            String curr = patternBuilder(current, "", guess);
            if (!mapper.containsKey(curr)) {
                mapper.put(curr, new TreeSet<String>());
            }
            mapper.get(curr).add(current);
        }
        for (String mapKey : mapper.keySet()) {
            Set<String> looking = mapper.get(mapKey);
            if (temp < looking.size()) {
                this.pattern = mapKey;
                temp = looking.size();
            }
        }
        return mapper;
    }

    //Builds pattern based on guesses and previous pattern
    //Pre: pattern comes in with 0 or several unknown numbers
    //Post: pattern leaves with guessed character exposed if it is in the pattern
    private String patternBuilder(String current, String curr, char guess) {
        for (int i = 0; i < this.pattern.length(); i++) {
            if (guess == current.charAt(i)) {
                curr += guess;
            } else {
                curr += this.pattern.charAt(i);
            }
        }
        return curr;
    }

    //Counts the occurences of a character in a particular word, takes in a char to do that
    //Post: returns the number of times a letter occurs
    private int getGuessCount(char guess, int guessCount) {
        for (int i = 0; i < this.changingDictionary.first().length(); i++) {
            if (this.changingDictionary.first().charAt(i) == guess) {
                guessCount++;
            }
        }
        return guessCount;
    }

    //Spaces the letters out and returns that String
    private String spacePattern(String temp, int tempLength) {
        for (int i = 0; i < tempLength; i++) {
            temp += temp.charAt(i);
            if (i < tempLength - 1) {
                temp += " ";
            }
        }
        return temp.substring(tempLength, temp.length());
    }
}