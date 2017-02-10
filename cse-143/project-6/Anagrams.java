/*
 Zachary Willson
 19 November 2015
 CSE 143AI (Anna Pendleton)
 Homework 6 - Anagrams

 Anagrams allows us to print all the anagrams from a given phrase. Methods include a constructor
 a method to get all the words you could make with a given string. There are two print methods,
 the first takes in only a String, while the second takes in both a String and an integer. The
 integer is for the max set of words to print.
*/

import java.util.*;

public class Anagrams {

    //Field stores all of our possible words
    private Set<String> myDictionary;

    //Parameters passed: Set<String>()
    //Throws IllegalArgumentException() if the dictionary passed is null
    //Constructs Anagrams object
    public Anagrams(Set<String> dictionary) {
        exceptions(dictionary, 1);
        this.myDictionary = new TreeSet<String>(dictionary);
    }

    //Returns a Set of words that are anagrams of the given word. Parameters passed: String
    //Throws an IllegalArgument Excepetion if string passed is null. Set is of type Set<String>.
    public Set<String> getWords(String phrase) {
        exceptions(phrase, 1);
        LetterInventory first = new LetterInventory(phrase);
        Set<String> returnWords = new TreeSet<String>();
        for (String s : this.myDictionary) {
            LetterInventory currentWord = new LetterInventory(s);
            if (first.contains(currentWord)) {
                returnWords.add(s);
            }
        }
        return returnWords;
    }

    //Prints all anagrams from the phrase passed
    //Parameters passed: String
    //Throws an IllegalArgument Excepetion if string passed is null 
    public void print(String phrase) {
        print(phrase, 0);
    }

    //Prints anagrams from the phrase passed, but only ones that are less than max in length
    //Parameters passed: String and integer
    //Throws an IllegalArgument Excepetion if string passed is null 
    public void print(String phrase, int max) {
        exceptions(phrase, max);
        print(getWords(phrase), new ArrayList<String>(), new LetterInventory(phrase), max);
    }

    //Prints anagrams from the phrase passed, but only ones that are less than max in length
    //Parameters: TreeSet<String> of dictionary, ArrayList<String> current
    //LetterInventory for letters, and integer of max number used
    private void print(Set<String> dic, List<String> curr, LetterInventory letter, int max) {
        if (letter.isEmpty() && (curr.size() <= max || max == 0)) {
            System.out.println(curr);
        } else {
            for (String thisWord : dic) {
                LetterInventory newWord = new LetterInventory(thisWord);
                if (letter.contains(newWord)) {
                    letter.subtract(newWord);
                    curr.add(thisWord);
                    print(dic, curr, letter, max);
                    curr.remove(curr.size() - 1);
                    letter.add(newWord);
                }
            }
            max -= 1;
        }
    }

    //Throws an IllegalArgument Excepetion if Object passed is null or max passed is < 0
    private void exceptions(Object o, int max) {
        if (o == null || max < 0) {
            throw new IllegalArgumentException();
        }
    }
}
