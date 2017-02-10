/*
 Zachary Willson
 22 October 2015
 CSE 143 AI (Anna Pendleton) Homework 3 - Assassins

 This Class manages the game Assassin. Available methods include constructor,
 a method to print the kill ring or graveyard, check to see if someone is in either ring,
 a method to check if the game is over, who the winner is, and a method to kill someone
*/

import java.util.*;

public class AssassinManager {

    //AssassinNodes for the Kill Circle and Graveyard
    private AssassinNode killList;
    private AssassinNode graveList;

    //Constructs class taking an List of names, if empty/null throws an IllegalArgumentException
    public AssassinManager(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.killList = new AssassinNode(names.get(0));
        AssassinNode current = this.killList;
        for (int i = 1; i < names.size(); i++) {
            current.next = new AssassinNode(names.get(i));
            current = current.next;
        }
    }

    //Prints who is stalking who in Kill Circle, each on a new line
    public void printKillRing() {
        AssassinNode current = this.killList;
        while (current.next != null) {
            current = printHelper(current, " is stalking ", current.next.name);
        }
        printHelper(current, " is stalking ", this.killList.name);
    }

    //Prints who killed who in the Graveyard, each on a new line
    public void printGraveyard() {
        AssassinNode current = this.graveList;
        while (current != null) {
            current = printHelper(current, " was killed by ", current.killer);
        }
    }

    //Assists print. Takes AssassinNode and two Strings, and returns the next Node
    private AssassinNode printHelper(AssassinNode current, String text, String end) {
        System.out.println("  " + current.name + text + end);
        return current.next;
    }

    //Takes in a String name and checks to see if the Kill Ring contains that name
    public boolean killRingContains(String name) {
        return contains(this.killList, name);
    }

    //Takes in a String name and checks to see if the Graveyard contains that name
    public boolean graveyardContains(String name) {
        return contains(this.graveList, name);
    }

    //Takes in AssassinNode currentPlayer and a string name (ignores case)
    //Returns true if the name is in the LinkedList
    private boolean contains(AssassinNode current, String name) {
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //Returns if the game is over
    public boolean isGameOver() {
        return this.killList.next == null;
    }

    //Returns the winner of the game, if game is not over returns null
    public String winner() {
        if (isGameOver()) {
            return this.killList.name;
        }
        return null;
    }

    //Takes in a String of the name of the person to be "killed". If game is over returns
    //IllegalStateException, if the person is already dead, returns an IllegalArgumentException
    //Finds the player and moves them from the kill ring to the graveyard (killing them)
    //Rearranges the killRing to incorporate the lost player, ignores case of name
    public void kill(String name) {
        if (isGameOver()) {
            throw new IllegalStateException();
        } else if (!killRingContains(name)) {
            throw new IllegalArgumentException();
        }
        AssassinNode current = this.killList;
        if (!current.name.equalsIgnoreCase(name)) {
            while (!current.next.name.equalsIgnoreCase(name)) {
                current = current.next;
            }
            killHelper(current.next, current, true);
        } else {
            AssassinNode temp = current;
            current = current.next;
            temp.next = null;
            this.killList = current;
            current = this.killList;
            while (current.next != null) {
                current = current.next;
            }
            killHelper(temp, current, false);
        }
    }

    //Assists the kill method in moving nodes. Takes in two AssassinNodes and boolean
    private void killHelper(AssassinNode temp, AssassinNode current, boolean check) {
        temp.killer = current.name;
        if (check) {
            current.next = current.next.next;
            temp.next = null;
        }
        temp.next = this.graveList;
        this.graveList = temp;
    }
}
