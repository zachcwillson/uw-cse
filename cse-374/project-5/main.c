// Zach Willson
// CSE 374 - HW5 - main.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "trie.h"

// This function controls user input, and will continue to run until the user enters Ctrl-D or
// if a file is used, the EOF character is seen. For each parial word passed, the function will
// print out the completions of that partial.
void interact(Trie* t) {
  char str[500];
  int scan = scanf("%s", str);
  while (scan != EOF) {
    finder(t, str, 0);
    printf("\n");
    scan = scanf("%s", str);
  }
}

// While there are lines of text in the file, adds each line of text to the Trie
// Assumes there is only one word per line, calls the interact fuction after
void makeTrie(FILE* f) {
  Trie* t = init();
  char str[500];
  while (fgets(str, 500, f) != NULL) {
    add(t, str);
  }
  interact(t);
  free(t);
}

// main attempts to open file, writing to stderr if it can't
// calls the makeTrie function if file could be opened.
int main(int argc, char* args[]) {
  char* file = args[1];
  FILE* f;
  f = fopen(file, "r");
  if (f == NULL) {
    fprintf(stderr, "Error: file not found\n");
    return 1;
  }
  makeTrie(f);
  return 0;
}
