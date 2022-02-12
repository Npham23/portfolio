#include <iostream>
#include <fstream>
#include "Employee.h"
#include "employee_test.h"
#include "person.h"


ifstream & operator >> (ifstream & infile, Employee e[])
{
	Person * p;
	string temp; // read hourly wage as a string
	int i = 0u;
	while (!infile.eof())
	{
		infile >> temp;
		e[i].setEmployeeNumber(temp);
		p = &e[i];
		infile >> p;
		infile >> temp;
		e[i].setEmployeeWage(stof(temp));
		e[i].employeeCount++;
		i++;

	}
	return infile;
}
ofstream & operator << (ofstream & outfile, Employee e[])
{
	Person * p;
	for (int i = 0; i < e[i].employeeCount; i++)
	{
		outfile << e[i].employeeNumber << SPACE;
		p = &e[i];
		outfile << p;
		outfile << to_string(e[i].getEmployeeWage()) << endl;
	}
	return outfile;
}
ostream & operator << (ostream & strm, Employee e[])
{
	Person * p;
	for (int i = 0; i < e[i].employeeCount; i++)
	{
		strm << e[i].employeeNumber << SPACE;
		p = &e[i];
		strm << p;
		strm << to_string(e[i].getEmployeeWage()) << endl;
	}
	return strm;
}
int Employee::employeeCount = 0;