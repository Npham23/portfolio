#ifndef PERSON_H
#define PERSON_H

#include <iostream>
#include <fstream>
#include <string.h>
#include "employee_test.h"
#include "employee.h"
using namespace std;

class Person
{
	protected:
	string lastName;
	string firstName;
	string address;
	string city;
	//StateAbbr state;
	string state;
	string zipCode;
	string telephone;

public:
	Person()
	{
		lastName = "";
		firstName = "";
		address = "";
		city = "";
		state = "";
		zipCode = "";
		telephone = "";
	}
		Person(string lname, string fname, string addrs, string citystr,
		string statestr, string zip, string phone)
		{
		  // implement overloaded constructor here as discussed for the
		  // Class A, B, C with inheritance
			lastName = lname;
			firstName = fname;
			address = addrs;
			city = citystr;
			//state = getStateEnum(statestr);
			state = statestr;
			zipCode = zip;
			telephone = phone;

		}
		friend ifstream & operator >> (ifstream & infile, Person * p);
		friend ofstream & operator << (ofstream & outfile, Person * p);
		friend ostream & operator << (ostream & strm, Person * p);
	  // define all of the accessors and mutators here
		void setLastName(const string str)
		{
			lastName = str;
		}
		void setFirstName(const string str)
		{
			firstName = str;
		}
		void setAddress(const string str)
		{
			address = str;
		}
		void setCity(const string str)
		{
			city = str;
		}
		void setState(const string str)
		{
			state = str;
		}
		void setZipCode(const string str)
		{
			zipCode = str;
		}
		void setTelephone(const string str)
		{
			telephone = str;
		}

		string getLastName()
		{
			return lastName;
		}
		string getFirstName()
		{
			return firstName;
		}
		string getAddress()
		{
			return address;
		}
		string getCity()
		{
			return city;
		}
		string getState()
		{
			return state;
		}
		string getZipCode()
		{
			return zipCode;
		}
		string getTelephone()
		{
			return telephone;
		}

};


class Employee : public Person
{
protected:
	string employeeNumber;
	float wage;
public:
	static int employeeCount;
	void setEmployeeNumber(const string str)
	{
		employeeNumber = str;
	}
	string getEmployeeNumber()
	{
		return(employeeNumber);
	}
	void setEmployeeWage(float w)
	{
		wage = w;
	}
	float getEmployeeWage(void)
	{
		return(wage);
	}
	Employee()
	{
		employeeNumber = "";
		wage = 0.0;
		employeeCount = 0;
	}

	/* 2nd constructor to initialize class */
	Employee(string id, string lname, string fname, string address,
		string city, string state, string phone, string zip, float pay) :
		Person(lname, fname, address, city, state, zip, phone)
	{
		Employee * e = this; /* point to the Customer class*/
		employeeNumber = id;
		wage = pay;

	}
	friend ifstream & operator >> (ifstream & infile, Employee e[]);
	friend ofstream & operator << (ofstream & outfile, Employee e[]);
	friend ostream & operator << (ostream & strm, Employee e[]);
};

#endif