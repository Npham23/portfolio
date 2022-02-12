#ifndef EMPLOYEE_TEST_H

#include <string>

#define SPACE " "

#define NUMSTATES 52

using namespace std;

enum StateAbbr {
	NOSTATE,
	AL, //Alabama
	AK, //Alaska
	AR, //Arkansas
	AZ, //Arizona
	CA, //California
	CO, //Colorado
	CT, //Connecticut
	DE, //Delaware
	DC, //District of Columbia                 
	FL, //Florida
	GA, //Georgia
	HI, //Hawaii 
	ID, //Idaho
	IL, //Illinois
	IN, //Indiana 
	IA, //Iowa
	KS, //Kansas        
	KY, //Kentucky
	LA, //Louisiana 
	ME, //Maine
	MD, //Maryland
	MA, //Massachusetts
	MI, //Michigan
	MN, //Minnesota
	MS, //Mississippi
	MO, //Missouri
	MT, //Montana
	NE, //Nebraska
	NV, //Nevada
	NH, //New Hampshire
	NJ, //New Jersey
	NM, //New Mexico
	NY, //New York  
	NC, //North Carolina
	ND, //North Dakota
	OH, //Ohio
	OK, //Oklahoma
	OR, //Oregon
	PA, //Pennsylvania
	RI, //Rhode Island     
	SC, //South Carolina
	SD, //South Dakota 
	TN, //Tennesses
	TX, //Texas
	UT, //Utah
	VT, //Vermont
	VA, //Virginia
	WA, //Washington  
	WV, //West Virginia
	WI, //Wisconsin
	WY
};//Wyoming

enum RetCode {
	Success = 0,
	ListFull,
	NotFound,
	ListEmpty,
	FileAllocationErr,
	UnexpectedError,
	End_of_file,
	InvalidDataRead
};

static string StateTitle[] = { // A global array which is permitted 
				   " ",
				   "AL", //Alabama
				   "AK", //Alaska
				   "AR", //Arkansas 
				   "AZ", //Arizona
				   "CA", //California
				   "CO", //Colorado
				   "CT", //Connecticut
				   "DE", //Delaware
				   "DC", //District of Columbia                 
				   "FL", //Florida
				   "GA", //Georgia
				   "HI", //Hawaii 
				   "ID", //Idaho
				   "IL", //Illinois
				   "IN", //Indiana 
				   "IA", //Iowa
				   "KS", //Kansas        
				   "KY", //Kentucky
				   "LA", //Louisiana 
				   "ME", //Maine
				   "MD", //Maryland
				   "MA", //Massachusetts
				   "MI", //Michigan
				   "MN", //Minnesota
				   "MS", //Mississippi
				   "MO", //Missouri
				   "MT", //Montana
				   "NE", //Nebraska
				   "NV", //Nevada
				   "NH", //New Hampshire
				   "NJ", //New Jersey
				   "NM", //New Mexico
				   "NY", //New York  
				   "NC", //North Carolina
				   "ND", //North Dakota
				   "OH", //Ohio
				   "OK", //Oklahoma
				   "OR", //Oregon
				   "PA", //Pennsylvania
				   "RI", //Rhode Island     
				   "SC", //South Carolina
				   "SD", //South Dakota 
				   "TN", //Tennesses
				   "TX", //Texas
				   "UT", //Utah
				   "VT", //Vermont
				   "VA", //Virginia
				   "WA", //Washington  
				   "WV", //West Virginia
				   "WI", //Wisconsin
				   "WY" };//Wyoming

inline StateAbbr getStateEnum(string stateStr)
{
	for (int i = 0; i < NUMSTATES; i++)
	{
		if (stateStr == StateTitle[i])
		{
			return (StateAbbr(i));
		}
	}
	return NOSTATE;
}


inline string getStateString(const StateAbbr s)
{
	return (StateTitle[s]);
}

#define EMPLOYEE_TEST_H

#endif