#pragma once
#ifndef CALCULATOR_H
#define CALCULATOR_H

#define MY_INFINITY 2147483647

extern double error;     /* extern this variable to all the files that include this header */

extern double factorial_function(int x);

extern double getX(void);
extern double calculateSine(double);
extern double calculateCosine(double);
extern double calculateExponential(double);
extern double power_function(double x, int exponent);
extern double calculateTangent(double);
extern double calculateSecant(double);
extern double calculateCosecant(double);
extern double calculateCotangent(double);
#endif