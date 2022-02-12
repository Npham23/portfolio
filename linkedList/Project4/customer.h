#ifndef CUSTOMER_H
#define CUSTOMER_H
#include "common.h"
#include "person.h"
#include <iostream>

using namespace std;

class Customer : public Person
{
protected:
	string customerNumber;
	Date regDate;

public:
	Customer()
	{
		customerNumber = "";
	}
	Customer(string id, string lname, string fname, string address, string city, string state, string zip, string phone, string date):
		Person(lname, fname, address, city, state, zip, phone)
	{
		/*
		Person * p = this;
		customerNumber = id;

		string date;
		int position1;
		int position2;
		date.shrink_to_fit();
		position1 = date.find("/", 0);
		regDate.month = date.substr(0, position1);
		position2 = date.find("/", position1 + 1);
		regDate.day = date.substr(position1 + 1, position2 - position1 - 1);
		regDate.year = date.substr(position2 + 1, date.length() - position2);
		*/
	}
	~Customer() {}

	friend ifstream & operator >> (ifstream & infile, Customer & c);
	friend ofstream & operator << (ofstream & outfile, Customer & c);
	friend ostream & operator << (ostream & strm, Customer & c);

	bool operator < (const Customer & right)
	{
		bool status = false;

		if (customerNumber < right.customerNumber)
		{
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
	}
	
	bool operator == (const Customer & right)
	{
		bool status = false;

		if (customerNumber == right.customerNumber)
		{
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
	}

	bool operator != (const Customer & right)
	{
		bool status = false;

		if (customerNumber != right.customerNumber)
		{
			status = true;
		}
		else
		{
			status = false;
		}
		return status;
	}

	Customer operator = (string str)
	{
		Customer customer;
		customer.customerNumber = str;
		return customer;
	}

	void setCustomerNumber(const string str)
	{
		customerNumber = str;
	}
	string getCustomerName()
	{
		return customerNumber;
	}

};
ifstream & operator >> (ifstream & infile, Customer & c)
{
	Person * p = &c;
	string date;
	vector <string> tokens;
	infile >> c.customerNumber;

	return infile;
}

ofstream & operator << (ofstream & outfile, Customer & c)
{
	Person * p = &c;
	outfile << c.customerNumber << endl;
	return outfile;
}
ostream & operator << (ostream & strm, Customer & c)
{
	Person * p = &c;
	strm << c.customerNumber << endl;
	return strm;
}


#endif // 