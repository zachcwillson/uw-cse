import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//CSE 373 Starter Code @Author Kevin Quinn
//TextAssociator represents a collection of associations between words.
//See write-up for implementation details and hints

// Zach Willson
// CSE 373 WI17
// Homework 3 - Due 2/10/17
public class TextAssociator {
    private WordInfoSeparateChain[] table;
    private int size;

    // INNER CLASS Represents a separate chain in your implementation of your
    // hashing A WordInfoSeparateChain is a list of WordInfo objects that have
    // all been hashed to the same index of the TextAssociator
    private class WordInfoSeparateChain {

        private List<WordInfo> chain;

        // Creates an empty WordInfoSeparateChain without any WordInfo
        public WordInfoSeparateChain() {
            this.chain = new ArrayList<WordInfo>();
        }

        // Adds a WordInfo object to the SeparateCahin Returns true if the
        // WordInfo was
        // successfully added, false otherwise
        public boolean add(WordInfo wi) {
            if (this.chain.contains(wi)) {
                return false;
            }
            this.chain.add(wi);
            return false;
        }

        // Removes the given WordInfo object from the separate chain Returns
        // true if the
        // WordInfo was successfully removed, false otherwise
        public boolean remove(WordInfo wi) {
            if (this.chain.contains(wi)) {
                this.chain.remove(wi);
                return true;
            }
            return false;
        }

        // Returns the size of this separate chain
        public int size() {
            return this.chain.size();
        }

        // Returns the String representation of this separate chain
        public String toString() {
            return this.chain.toString();
        }

        // Returns the list of WordInfo objects in this chain
        public List<WordInfo> getElements() {
            return this.chain;
        }
    }

    // Creates a new TextAssociator without any associations
    public TextAssociator() {
        this.table = new WordInfoSeparateChain[787];
        for (int i = 0; i < 787; i++) {
            this.table[i] = new WordInfoSeparateChain();
        }
        this.size = 0;
    }

    // Adds a word with no associations to the TextAssociator Returns False if
    // this word is already contained in your TextAssociator , Returns True if
    // this word is successfully added
    public boolean addNewWord(String word) {
        int index = hashCode(word, this.table.length);
        resize();
        if (containsWord(index, word)) {
            return false;
        } else {
            this.table[index].add(new WordInfo(word));
            this.size++;
        }
        return true;
    }

    // This method handles resizing my array if my load factor gets too high.
    // I first have to find the average nodes per index, and if there is more
    // than one
    // Per index, I resize and rehash the Chains.
    private void resize() {
        int nodes = 0;
        for (int i = 0; i < this.table.length; i++) {
            nodes += this.table[i].size();
        }
        if ((double) nodes / (double) this.table.length > 1.0) {
            WordInfoSeparateChain[] newChain = new WordInfoSeparateChain[this.table.length * 3];
            for (int i = 0; i < newChain.length; i++) {
                newChain[i] = new WordInfoSeparateChain();
            }
            // After I create a new Array, I need to rehash my values into it.
            for (int i = 0; i < this.table.length; i++) {
                for (WordInfo wi : this.table[i].getElements()) {
                    int index = hashCode(wi.getWord(), newChain.length);
                    newChain[index].add(wi);
                }
            }
            this.table = newChain;
        }
    }

    // Passing in an index and String, checks to see if that index of the array
    // (a given WordInfoSeparateChain) contains the String in its set.
    private boolean containsWord(int index, String word) {
        boolean flag = false;
        for (WordInfo w : this.table[index].getElements()) {
            if (w.getWord().equals(word)) {
                flag = true;
            }
        }
        return flag;
    }

    // Adds an association between the given words. Returns true if association
    // correctly added, returns false if first parameter does not already exist
    // in the TextAssociator or if the association between the two words already
    // exists
    public boolean addAssociation(String word, String association) {
        int index = hashCode(word, this.table.length);
        for (WordInfo w : this.table[index].getElements()) {
            if (w.getAssociations().contains(association)) {
                return false;
            }
            if (w.getWord().equalsIgnoreCase(word)) {
                w.addAssociation(association);
                return true;
            }
        }
        return false;
    }

    // Remove the given word from the TextAssociator, returns false if word was
    // not contained, returns true if the word was successfully removed. Note
    // that only a source word can be removed by this method, not an
    // association.
    public boolean remove(String word) {
        int index = hashCode(word, this.table.length);
        if (!containsWord(index, word)) {
            return false;
        }
        for (WordInfo w : this.table[index].getElements()) {
            if (w.getWord().equalsIgnoreCase(word)) {
                this.table[index].remove(w);
                this.size--;
                return true;
            }
        }
        return false;
    }

    // Returns a set of all the words associated with the given String Returns
    // null if the given String does not exist in the TextAssociator
    public Set<String> getAssociations(String word) {
        int index = hashCode(word, this.table.length);
        for (WordInfo w : this.table[index].getElements()) {
            if (w.getWord().equalsIgnoreCase(word)) {
                return w.getAssociations();
            }
        }
        return null;
    }

    // Prints the current associations between words being stored to System.out
    public void prettyPrint() {
        System.out.println("Current number of elements : " + size);
        System.out.println("Current table size: " + table.length);
        // Walk through every possible index in the table
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                WordInfoSeparateChain bucket = table[i];
                // For each separate chain, grab each individual WordInfo
                for (WordInfo curr : bucket.getElements()) {
                    System.out.println("\tin table index, " + i + ": " + curr);
                }
            }
        }
        System.out.println();
    }

    // This takes in String and a prime number to create the hash index for the
    // given string.
    // Uses the String hashCode function and then mods that to fit into the
    // array.
    private int hashCode(String word, int prime) {
        return Math.abs(word.hashCode()) % prime;
    }
}