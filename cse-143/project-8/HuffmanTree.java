/*
 Zachary Willson
 10 December 2015
 CSE 143 AI (Anna Pendleton)
 Homework 8 - HuffmanTree

 HuffmanTree allows us to compress text files. To compress, use HuffmanTree(int[]) and 
 write(PrintStream). To Decompress, use HuffmanTree(Scanner) and 
 decode(BitInputStream, PrintStream, int)
*/

import java.util.*;
import java.io.*;

public class HuffmanTree {

    //Fields allow us to store Huffman Tree of frequency of characters.
    private HuffmanNode tree;

    //Pre: counts is not null
    //Takes in parameter of an array of counts
    //Builds Huffman Tree with each character prioritized by its count
    public HuffmanTree(int[] counts) {
        Queue<HuffmanNode> q = new PriorityQueue<HuffmanNode>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                q.add(new HuffmanNode(counts[i], (char) i));
            }
        }
        q.add(new HuffmanNode(1, (char) counts.length));
        while (q.size() > 1) {
            HuffmanNode one = q.remove();
            HuffmanNode two = q.remove();
            q.add(new HuffmanNode(one.frequency + two.frequency, '\0', one, two));
        }
        this.tree = q.peek();
    }

    //Writes a code for each character to a PrintStream object
    //Pre: PrintStream object passed is not null
    public void write(PrintStream output) {
        writeHelper(output, this.tree, "");
    }

    //Checks to see if we have reached a leafNode, if so, prints out the integer value of the
    //character and on a new line, the code (Steps in tree) to that respective character
    //If not, we recurse adding a left node and a right node.
    private void writeHelper(PrintStream output, HuffmanNode root, String code) {
        if (root.left == null && root.right == null) {
            output.println((int) root.character + "\n" + code);
        } else {
            writeHelper(output, root.left, code + "0");
            writeHelper(output, root.right, code + "1");
        }
    }

    //Pre: Parameter passed (Scanner) is not null
    //Builds Huffman Tree with each character prioritized by its count
    //Comes from code file, assumes in correct format
    public HuffmanTree(Scanner input) {
        this.tree = new HuffmanNode();
        while (input.hasNext()) {
            this.tree = huffHelp(this.tree, Integer.parseInt(input.nextLine()), input.nextLine());
        }
    }

    //Parameters passed: Scanner, HuffmanNode, String, int
    //Each time we recurse, we take one letter off the front of code, so if that code is empty,
    //We set our root field letter to that integer but as a character
    //Then we return it. If the String is not empty, we see if it starts with 0 or 1. Depending
    //On that, we either go to the left or right and recurse.
    private HuffmanNode huffHelp(HuffmanNode root, int n, String code) {
        if (code.isEmpty()) {
            root = new HuffmanNode(root.frequency, (char) n);
        } else if (code.charAt(0) == '0') {
            if (root.left == null) {
                root.left = new HuffmanNode();
            }
            root.left = huffHelp(root.left, n, code.substring(1));
        } else if (code.charAt(0) == '1') {
            if (root.right == null) {
                root.right = new HuffmanNode();
            }
            root.right = huffHelp(root.right, n, code.substring(1));
        }
        return root;
    }

    //Reads individual vits from the input stream and writes those to the PrintStream output
    //Passed. Assumes that the input stream is legal coding for Huffman code
    //Parameters passed: BitInputStream, PrintStream, int
    public void decode(BitInputStream input, PrintStream output, int eof) {
        char current = '\0';
        while (current != (char) eof) {
            current = decodeHelper(input, eof, this.tree);
            if (current != (char) eof) {
                output.write(current);
            }
        }
    }

    //Parameters passed: BitInputStream, PrintStream, int, and HuffmanNode; returns a char
    //If HuffmanNode right and left are null, we return that nodes letter field
    //Otherwise, we read in a bit, and if it is 0 we recurse and return the left, and if 1
    //We recurse and return the right. We then return the EOF.
    private char decodeHelper(BitInputStream input, int eof, HuffmanNode root) {
        if (root.left == null && root.right == null) {
            return root.character;
        } else {
            int bit = input.readBit();
            if (bit == 0) {
                return decodeHelper(input, eof, root.left);
            } else if (bit == 1) {
                return decodeHelper(input, eof, root.right);
            }
            return (char) eof;
        }
    }
}