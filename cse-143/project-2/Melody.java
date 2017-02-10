/*
 Zachary Willson
 15 October 2015
 CSE 143 AI (Anna Pendleton)
 Homework 2 - Melody

 This Class holds Notes in a Melody
 Methods include ways to print to a String, get the Duration of the Melody,
 change the tempo, reverse the song, append one to another, or play the Melody
 */

import java.util.*;

public class Melody {

    //Queue to hold notes
    //double to hold song length (seconds)
    private Queue<Note> notes;
    private double duration;

    //Initializes Melody to store the passed Queue of Notes
    //Throws an IllegalArgumentException if Queue passed in is null
    public Melody(Queue<Note> song) {
        if (song == null) {
            throw new IllegalArgumentException();
        }
        this.notes = new LinkedList<Note>(song);
        findDuration(song);
    }

    //Takes the original Queue and finds the song length,
    //Takes in a Queue<Note> parameter
    //Calls the repeatHelper to add durations for repeating sections
    private void findDuration(Queue<Note> song) {
        for (int i = 0; i < this.notes.size(); i++) {
            if (song.peek().isRepeat()) {
                i += repeatDurationHelp(song.remove());
                while (!song.peek().isRepeat()) {
                    i += repeatDurationHelp(song.remove());
                }
                i += repeatDurationHelp(song.remove());
            } else {
                this.duration += song.remove().getDuration();
            }
        }
    }

    //Doubles length of note and adds to the length of Melody
    //Takes in parameter Note s
    //Returns 1 to the method that called it
    private int repeatDurationHelp(Note s) {
        this.duration += (s.getDuration() * 2);
        return 1;
    }

    //Returns a double of the total length of the song in seconds
    //If song has repeated sections, those sections will be counted twice
    public double getTotalDuration() {
        return this.duration;
    }

    //Returns a String representation of the Melody
    public String toString() {
        String returned = "";
        for (int i = 0; i < this.notes.size(); i++) {
            returned += this.notes.peek().toString() + "\n";
            this.notes.add(this.notes.remove());
        }
        return returned;
    }

    //Changes the tempo of the Melody, takes in a double as a multiplier
    //Tempos passed < 1.0 will speed the song up, while > 1.0 will slow the song down
    //Throws IllegalArgumentException if tempo is less than 0
    public void changeTempo(double tempo) {
        if (tempo < 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < this.notes.size(); i++) {
            this.notes.peek().setDuration(this.notes.peek().getDuration() * tempo);
            this.notes.add(this.notes.remove());
        }
        this.duration *= tempo;
    }

    //Reverses the order of the song. Notes that play first will now play last
    //Pre: Order is A, B, C, D
    //Post: Order is D, C, B, A
    public void reverse() {
        Stack<Note> temp = new Stack<Note>();
        while (!this.notes.isEmpty()) {
            temp.push(this.notes.remove());
        }
        while (!temp.isEmpty()) {
            this.notes.add(temp.pop());
        }
    }

    //Adds all Notes from the given other song to the end of this song.
    //Parameters are a Melody object, IllegalArguementException thrown if null
    public void append(Melody other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        this.duration += other.duration;
        for (int i = 0; i < other.notes.size(); i++) {
            this.notes.add(other.notes.peek());
            other.notes.add(other.notes.remove());
        }
    }

    //Plays the Melody by calling all the Note's play method
    //Can be called more than once
    public void play() {
        for (int i = 0; i < this.notes.size(); i++) {
            if (this.notes.peek().isRepeat()) {
                i += playRepeat(new LinkedList<Note>(), 0);
            } else {
                this.notes.peek().play();
                this.notes.add(this.notes.remove());
            }
        }
    }

    //Handles the task of playing the repeated sections
    //Takes in Queue<Note> and int i and returns i's value
    //Calls repeat method to handle taking the Note into the temp Queue
    //And adding it back into the Melody
    private int playRepeat(Queue<Note> temp, int i) {
        i += repeatHelp(temp);
        while (!this.notes.peek().isRepeat()) {
            i += repeatHelp(temp);
        }
        i += repeatHelp(temp);
        for (int j = 0; j < temp.size() * 2; j++) {
            temp.peek().play();
            temp.add(temp.remove());
        }
        return i;
    }

    //Adds next Note to temp Queue<Note> (passed as parameter)
    //Adds back to Melody and returns the int 1
    private int repeatHelp(Queue<Note> temp) {
        temp.add(this.notes.peek());
        this.notes.add(this.notes.remove());
        return 1;
    }
}
