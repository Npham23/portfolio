#ifndef COMPANY_H
#define COMPANY_H
#include "customer.h"
#include "common.h"
#include "employee.h"
#include "template_linked_list.h"

#include <string.h>
#include <fstream>

using namespace std;

class Company : public Employee
{
protected:
	string companyName;
	string address;
	string city;
	StateAbbr state;
	string zipCode;
	string telephone;

	Employee e;

	LinkedList <Employee> employeeList;

public:
	Company()
	{
		ifstream employeeInputFile("Employees.txt");
		string stateString;
		employeeInputFile >> companyName;
		employeeInputFile >> address;
		employeeInputFile >> city;
		employeeInputFile >> stateString;
		state = getStateEnum(stateString);
		employeeInputFile >> zipCode;
		employeeInputFile >> telephone;

		while (!employeeInputFile.eof())
		{
			employeeInputFile >> e;
			employeeList.appendNode(e);
		}
		employeeInputFile.close();
	}
	virtual void Add(Employee & e)
	{
		Employee employee = e;
		employeeList.insertNode(employee);
	}

	friend ofstream & operator << (ofstream &  outfile, Company * c);
};
ofstream & operator << (ofstream &  outfile, Company * c)
{
	outfile << c->companyName << SPACE;
	outfile << c->address << SPACE;
	outfile << c->city << SPACE;
	outfile << c->state << SPACE;
	outfile << c->zipCode << SPACE;
	outfile << c->telephone << endl;
}
#endif