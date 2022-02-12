#include <iostream>
#include <fstream>
#include <string.h>
#include <iomanip>
#include "person.h"
#include "employee.h"
#include "employee_test.h"
using namespace std;

ifstream & operator >> (ifstream & infile, Person * p)
{
	string stateString;

	infile >> p->lastName;
	infile >> p->firstName;
	infile >> p->address;
	infile >> p->city;
	infile >> p->state;
	infile >> p->zipCode;
	infile >> p->telephone;

	return infile;
}
ofstream & operator << (ofstream & outfile, Person * p)
{

	outfile << p->lastName << SPACE;
	outfile << p->firstName << SPACE;
	outfile << p->address << SPACE;
	outfile << p->city << SPACE;
	outfile << p->state << SPACE;
	outfile << p->zipCode << SPACE;
	outfile << p->telephone << SPACE;

	return outfile;
}
ostream & operator << (ostream & strm, Person * p)
{

	strm << p->lastName << SPACE;
	strm << p->firstName << SPACE;
	strm << p->address << SPACE;
	strm << p->city << SPACE;
	strm << p->state << SPACE;
	strm << p->zipCode << SPACE;
	strm << p->telephone << SPACE;

	return strm;
}



