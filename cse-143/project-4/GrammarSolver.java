/*
 Zachary Willson
 29 October 2015
 CSE 143 AI (Anna Pendleton)
 Homework 4 - GrammarSolver

 This Class stores the relationship of words to their grammar component and generates sentences
 Methods include: constructor, a contains method to find if a string is contained in object,
 a method to show all the grammar symbols included, and a method to generate sentences.
*/

import java.util.*;

public class GrammarSolver {

    //Map object stores the key and an Array of the words associated with it
    private Map<String, String[]> gMap;

    //Throws an IllegalArgumentException if List passed is null or empty
    //Splits each string in the List into its terminal and non-terminal parts
    //Throws an IllegalArgumentException if there is more than one of the same terminal type
    public GrammarSolver(List<String> rules) {
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.gMap = new TreeMap<String, String[]>();
        for (int i = 0; i < rules.size(); i++) {
            String[] delimit1 = rules.get(i).split("::=");
            String leftSide = delimit1[0];
            String[] rightDelimit = delimit1[1].split("[|]");
            if (!this.gMap.containsKey(leftSide)) {
                this.gMap.put(leftSide, rightDelimit);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    //Returns if the GrammarSolver object contains the String passed.
    //Throws an IllegalArgumentException if the String passed is null or of zero length
    public boolean contains(String symbol) {
        exceptions(symbol);
        return this.gMap.containsKey(symbol);
    }

    //Returns the Set of Strings of the types of grammars GrammarSolver contains
    public Set<String> getSymbols() {
        return this.gMap.keySet();
    }

    //Generates sentences based on the grammar rules
    //Throws an IllegalArgumentException if the String passed is null or of zero length
    //Randomly decides between terminal parts to use
    public String generate(String symbol) {
        exceptions(symbol);
        String finalString = "";
        if (!contains(symbol)) {
            return symbol.trim();
        }
        String pick = this.gMap.get(symbol)[new Random().nextInt(this.gMap.get(symbol).length)];
        String[] splitPick = pick.trim().split("[ \t]+");
        for (int i = 0; i < splitPick.length - 1; i++) {
            finalString += generate(splitPick[i]) + " ";
        }
        return finalString += generate(splitPick[splitPick.length - 1]);

    }

    //Throws an IllegalArgumentException if String passed is null or a length of 0
    private void exceptions(String symbol) {
        if (symbol == null || symbol.length() == 0) {
            throw new IllegalArgumentException();
        }
    }
}
