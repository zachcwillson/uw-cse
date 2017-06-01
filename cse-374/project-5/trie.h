// Zach Willson
// CSE 374 - HW5 - trie.h
#ifndef TRIE_H
#define TRIE_H
// Define struct Trie, with methods init, add, finderHelper, and finder
typedef struct Trie {
  struct Trie* child[127];
  bool leaf;
} Trie;

Trie* init();
void add(Trie* top, char* c);
void finderHelper(Trie* curr);
void finder(Trie* curr, char* c, int count);
#endif  // TRIE_H
