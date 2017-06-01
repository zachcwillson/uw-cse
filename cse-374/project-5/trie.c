// Zach Willson
// CSE 374 - HW5 - trie.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "trie.h"

// This method initializes my Trie object with a single root and 127 children
Trie* init() {
    Trie* returnd = NULL;
    returnd = (Trie*)malloc(sizeof(Trie));
    if (returnd) {
      returnd->leaf = false;
      for (int i = 0; i < 127; i++) {
	returnd->child[i] = NULL;
      }
    } else {
      fprintf(stderr, "Error: malloc failed");
    }
    return returnd;
}

// This method adds the char* passed to the Trie* passed. The last letter
// will be marked as a leaf.
void add(Trie* top, char* c) {
  int strlength = strlen(c) - 1;
  Trie* curr = top;
  for (int i = 0; i < strlength; i++) {
    int index = (int) c[i];
    if (!curr->child[index]) {
      curr->child[index] = init();
    }
    curr = curr->child[index];
  }
  curr->leaf = true;
}

// This is my helper for finder, it simply recursese through a Trie and prints its contents
void finderHelp(Trie* curr) {
  for (int i = 0; i < 127; i++) {
    if (curr->child[i] != NULL) {
      printf("%c", (char) i);
      finderHelp(curr->child[i]);
    }
  }
}

// This method first figures out if the passed word is in the Trie,
// prints out the part the user has alrighty typed, then calls a
// helper to print the rest of the tree
void finder(Trie* curr, char* c, int count) {
  int passedLength = (int) strlen(c);
  bool startFlag = false;
  for (int i = 0; i < passedLength; i++) {
    if (curr->child[(int) c[i]] != NULL) {
      printf("%c", c[i]);
      curr = curr->child[(int) c[i]];
      startFlag = true;
    }
  }
  if (startFlag && !curr->leaf) {
    finderHelp(curr);
  }
}
