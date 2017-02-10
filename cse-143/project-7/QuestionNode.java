/*
 Zachary Willson
 3 December 2015
 CSE 143 AI (Anna Pendleton)
 Homework 7 - 20 Questions

 QuestionNode allows us to construct our linked list of nodes
*/

public class QuestionNode {

    //Fields to hold all data
    private String data;
    private QuestionNode left;
    private QuestionNode right;

    //Constructs QuestionNode with given String passed
    public QuestionNode(String data) {
        this(data, null, null);
    }

    //Constructs QuestionNode with given String passed, and left and right QuestionNode branches
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    //Returns a boolean if the node is a leaf node
    public boolean isLeafNode() {
        return this.left == null && this.right == null;
    }

    //Sets data to the string passed
    public void setData(String data) {
        this.data = data;
    }

    //Returns data field as a String
    public String getData() {
        return this.data;
    }

    //Sets left QuestionNode to QuestionNode passed
    public void setLeft(QuestionNode left) {
        this.left = left;
    }

    //Returns data field as a QuestionNode
    public QuestionNode getLeft() {
        return this.left;
    }

    //Sets right QuestionNode to QuestionNode passed
    public void setRight(QuestionNode right) {
        this.right = right;
    }

    //Returns data field as a QuestionNode
    public QuestionNode getRight() {
        return this.right;
    }
}