// Zach Willson
// CSE 374
// Homework 4
// This program is basically a limited grep in c
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int tolower(int x);

// This method helps decide if flags are present and goes through the line
// and looks for a match. If there is a match, prints out, and makes the necessary
// checks if flags are passed
void helpcases(char* match, char* str, int flag, int count) {
  if (flag == 2 || flag == 3) {
    for (int i = 0; match[i]; i++) {
      match[i] = tolower(match[i]);
    }
    for (int j = 0; str[j]; j++) {
      str[j] = tolower(str[j]);
    }
  }
  if (strstr(str, match) != NULL) {
    if (flag == 1 || flag == 3) {
      printf("%d ", count);
    }
    printf("%s", str);
  }
}

// This method opens the file and passes it to helpcases
// Returns 1 if file is not found, prints to stderr
// Returns 0 if file is opened and searched
// Searches file and calls the correct method with a flag indicator
int openfile(char* match, char* file, int flag) {
  FILE* f;
  char str[500];
  f = fopen(file, "r");
  if (f == NULL) {
    fprintf(stderr, "Error: file not found\n");
    return 1;
  }
  int count = 0;
  while (fgets(str, 500, f) != NULL) {
    count++;
    helpcases(match, str, flag, count);
  }
  return 0;
}

// This method handles if there are more than one file passed after the STRING
// If else tree shows where the STRING and FILES start.
// Returns 0 if successful
int files(int start, int argc, char** args, int flag) {
  int a;
  if (start == 1) {
    while (start < argc - 1) {
      start++;
      char* fileparam = args[start];
      a += openfile(args[1], fileparam, flag);
    }
  } else if (start == 2) {
    while (start < argc - 1) {
      start++;
      char* fileparam = args[start];
      a += openfile(args[2], fileparam, flag);
    }
  } else if (start == 3) {
    while (start < argc - 1) {
      start++;
      char* fileparam = args[start];
      a += openfile(args[3], fileparam, flag);
    }
  }
  return a;
}

// Starts program: if there are not enough args, exits
// If enough args, calls the files method with parameters to differentiate
// the different flags passed.
int main(int argc, char** args) {
  if (argc < 3 || (argc <= 3 && (strcmp(args[1], "-n") == 0 || strcmp(args[1], "-i") == 0))) {
    fprintf(stderr, "Not passed enough parameters\n");
    return 1;
  }
  if ((strcmp(args[1], "-i") == 0 && strcmp(args[2], "-n") == 0) || (strcmp(args[1], "-n") == 0 && strcmp(args[2], "-i") == 0)) {
    return files(3, argc, args, 3);
  } else if (strcmp(args[1], "-i") == 0) {
    return files(2, argc, args, 2);
  } else if (strcmp(args[1], "-n") == 0) {
    return files(2, argc, args, 1);
  } else {
    return files(1, argc, args, 0);
  }
}
