#include <iostream>
#include <fstream>
#include "Employee.h"
#include "employee_test.h"
#include "person.h"

using namespace std;
int main()
{
	ofstream outputFile("employees.out", ios::out);
	ifstream inputFile("employees.txt", ios::in);
	Employee employees[100];
	inputFile >> employees;
	outputFile << employees;
	cout << employees;
	cout << "\n The output has been written to employees.out" << endl;

	/* must close all the files before exiting */
	outputFile.close();
	inputFile.close();
	system("pause");
	return 0;
}