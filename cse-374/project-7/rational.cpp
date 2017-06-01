/*
 * Zach Willson
 * CSE 374 - HW7 = 17WI
 * Implementation of rational number type
 * CSE 374 HW7, 17wi
 */

#include "rational.h"

// Method declarations
int gcdCheck(int old_n, int old_d, int flag);
int gcm(int x, int y);

// Constructors
// Creates a Rational with 0/1 as result
Rational::Rational() {
  num = 0;
  denom = 1;
}

// Creates a Rational with n/1 as result (n is integer passed in)
Rational::Rational(int n) {
  num = n;
  denom = 1;
}

// Creates a Rational with n/d as result (n and d are integer passed in)
Rational::Rational(int n, int d) {
  num = n;
  denom = d;
  // Simplify
  int old_n = num;
  int old_d = denom;
  num = gcdCheck(old_n, old_d, 0);
  denom = gcdCheck(old_n, old_d, 1);
}

// Returns the numerator of specific rational object (integer)
int Rational::n() {
  return num;
}

// Returns the denominator of specific rational object (integer)
int Rational::d() {
  return denom;
}

// Methods

// Adds this Rational to other passed Rational and returns a new Rational.
// Rational will be simplified
Rational Rational::plus(Rational other) {
  Rational returnd;
  if (d() == other.d()) {
    returnd.num = n() + other.n();
    returnd.denom = d();
  } else {
    int thisNum = n() * other.d();
    int thisDenom = d() * other.d();
    int otherNum = other.n() * d();
    int covfefe = thisNum + otherNum;
    returnd.num = covfefe;
    returnd.denom = thisDenom;
  }
  // Simplify
  int old_n = returnd.num;
  int old_d = returnd.denom;
  returnd.num = gcdCheck(old_n, old_d, 0);
  returnd.denom = gcdCheck(old_n, old_d, 1);
  return returnd;
}

// Subtracts other Rational from this Rational passed, returns a new Rational.
// Rational will be simplified
Rational Rational::minus(Rational other) {
  Rational returnd;
  if (d() == other.d()) {
    returnd.num = n() - other.n();
    returnd.denom = d();
  } else {
    int covfefeNum = n() * other.d();
    int covfefeDenom = d() * other.d();
    int otherCovfefe = other.n() * d();
    int finalCovfefe = covfefeNum - otherCovfefe;
    returnd.num = finalCovfefe;
    returnd.denom = covfefeDenom;
  }
  // Simplify
  int old_n = returnd.num;
  int old_d = returnd.denom;
  returnd.num = gcdCheck(old_n, old_d, 0);
  returnd.denom = gcdCheck(old_n, old_d, 1);
  return returnd;
}

// Multiplies this with other Rational, returns a new simplified Rational
Rational Rational::times(Rational other) {
  return Rational(n() * other.n(), d() * other.d());
}

// Divides this Rational with other Rational, returns a new simplified Rational
Rational Rational::div(Rational other) {
  return Rational(n() * other.d(), d() * other.n());
}

// Checks Rational object to make it in Greatest Common Divisor
// Passed params: int numerator, int denominator, int flag.
// Flag represents if the method needs to return a numerator or denominator
int gcdCheck(int old_n, int old_d, int flag) {
  int gcmNum = gcm(old_n, old_d);
  if (flag == 0) {
    return old_n / gcmNum;
  } else {
    return old_d / gcmNum;
  }
}

// Recurses until we reach the smallest factor between the two integers passed.
// Returns an integer
int gcm(int x, int y) {
  if (y == 0) {
    return x;
  } else {
    return gcm(y, x % y);
  }
}
