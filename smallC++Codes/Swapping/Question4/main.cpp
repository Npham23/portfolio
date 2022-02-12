/*
Nathan Pham
CS2 Final Exam
Question 4
*/

#include <iostream>
using namespace std;

template <class T>
void swap(T * x, T*  y)
{
	T temp = *x;

	if (*x == *y)
	{
		throw "Both values are the same!";
	}
	else 
	{
		*x = *y;
		*y = temp;
		throw "Successful!!! values has been swapped!";
		return;
	}
	return;
}

int main()
{
	int a = 10;
	int b = 20;
	int c = 20; // same value as b, but I will swapping a and b
	float d = 4.33;
	float e = 5.25;
	float f = 5.25; //same value as e, later, e's value will be swapped
	float g = 10.0; // same value as a, but it a will swap with b
	
	cout << "Before swap int value 1: " << a << " int value 2: " << b << endl;
	try {
		swap(&a, &b);
	}
	catch (const char* msg)
	{
		cout << msg << endl << endl;
	}

	cout << "Before swap int value 1: " << a << " int value 3: " << c << endl;// remember, a and b swapped values, a is now the same as c
	try {
		swap(&a, &c);
	}
	catch (const char* msg)
	{
		cout << msg << endl << endl;
	}
	
	cout << "Before swap float value 4: " << d << " floaat value 5: " << e << endl;
	try {
		swap(&d, &e);
	}
	catch (const char* msg)
	{
		cout << msg << endl << endl;
	}

	cout << "Before swap float value 4: " << d << " floaat value 6: " << f << endl;// value d has been changed
	try {
		swap(&d, &f);
	}
	catch (const char* msg)
	{
		cout << msg << endl << endl;
	}

	cout << "Before swap int value 1: " << a << " float value 7: " << g << endl;
	try {
		float h = (float)a;
		swap(&h, &g);
	}
	catch (const char* msg)
	{
		cout << msg << endl << endl;
	}

	cout << "Before swap int value 3: " << c << " float value 7: " << g << endl;
	try {
		float h = (float)c;
		swap(&h, &g);
	}
	catch (const char* msg)
	{
		cout << msg << endl << endl;
	}

	system("pause");
	return 0;
}