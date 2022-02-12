#include <iostream>
#include <iomanip>
#include "calculator.h"//gets the header file 
using namespace std;

/*-------------------------------------------------------------------*/
/* Project 1														 */
/* this program displays a menu to calculate one of three functions: */
/* sine, cosine, and exponential (e^x)                               */
/* only addtion, subtraction, multiplication, and division are used  */
/*-------------------------------------------------------------------*/

/*
Nathan Pham
Computer Science 2
Project 1
"I have neither given nor received unauthorized aid in completing this work, nor have I presented someone else's work as my own."
Task: Utilize this given project and just add the cosine and exp functions, not sine because that was done in this project
Extra Credit: Implement the following trigonometric functions in a similar way without using library functions:
tangent, cotangent, secant, cosecant.
*/

/* define function prototypes */
void printCalculatorMenu(void);
void printAnswer(double result);

double error = 1.0E-10;  /* this is the global error tolerance */

int main(void)
{
	double x = 0.0F;
	double answer = 0.0F;

	int choice;

	do
	{
		cout << "Please choose one of the following options: " << endl;

		/* display menu choices */
		printCalculatorMenu();
		cin >> choice;

		cout << endl;

		switch (choice)
		{
		case 1:
			x = getX();
			answer = calculateSine(x);
			printAnswer(answer);
			break;
		case 2:
			x = getX();
			answer = calculateCosine(x);
			printAnswer(answer);
			break;
		case 3:
			x = getX();
			answer = calculateExponential(x);
			printAnswer(answer);
			break;
		case 5:
			x = getX();
			answer = calculateTangent(x);
			printAnswer(answer);
			break;
		case 6:
			x = getX();
			answer = calculateCotangent(x);
			printAnswer(answer);
			break;
		case 7:
			x = getX();
			answer = calculateSecant(x);
			printAnswer(answer);
			break;
		case 8:
			x = getX();
			answer = calculateCosecant(x);
			printAnswer(answer);
			break;
		default:
			/* do nothing */
			if (choice != 4)
			{
				cout << "Enter a valid choice, 1 through 8 ... " << endl;
			}
			break;//quit
		}

	} while (choice != 4);//loops
}

void printCalculatorMenu(void)
{
	cout << "1.	Calculate the Sine function of a X" << endl;
	cout << "2.	Calculate the cosine function of X" << endl;
	cout << "3.	Calculate the exponential function of X" << endl;
	cout << "5. \tCalculate the Tangent function of X" << endl;
	cout << "6. \tCalculate the Cotangent function of X" << endl;
	cout << "7. \tCalculate the Secant function of X" << endl;
	cout << "8. \tCalculate the Cosecant function of X" << endl;
	cout << "4.	to quit ..." << endl;
	return;
}

double getX()
{
	double inputX;

	cout << "Enter the value for X ... ";
	cin >> inputX;
	return inputX;
}

void printAnswer(double result)
{
	cout << fixed << setprecision(15);
	cout << "The result is = " << result << endl;
}