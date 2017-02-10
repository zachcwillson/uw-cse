/*
 Zachary Willson
 10 December 2015
 CSE 143 AI (Anna Pendleton)
 Homework 8 - HuffmanNode

 HuffmanNode stores information, stores the occurance of a character, the character itself
 and left and right nodes of itself
*/

public class HuffmanNode implements Comparable<HuffmanNode> {

    //Fields contain information for Node
    public int frequency;
    public char character;
    public HuffmanNode left;
    public HuffmanNode right;

    //Constructs empty HuffmanNode
    public HuffmanNode() {
        this(0, '\0');
    }

    //Constructs HuffmanNode with passed integer (count of character) and character
    public HuffmanNode(int frequency, char character) {
        this(frequency, character, null, null);
    }

    //Constructs HuffmanNode with passed integer (count of character) and character, and a left
    //and right node
    public HuffmanNode(int frequency, char character, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.character = character;
        this.left = left;
        this.right = right;
    }

    //Compares two HuffmanNodes and figures which comes before the other
    //Compares based on frequency, returns an integer, positive if this comes after other,
    //negative of this comes before other, and 0 if they are "equal"
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}