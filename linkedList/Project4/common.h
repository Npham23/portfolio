#ifndef VIDEOSTORE_H
#define VIDEOSTORE_H


#include <string>
#include <vector>

using namespace std;

#define NUMSTATES 52
#define NUMCATEGORY 12 
#define NUMRATING   6

#define SPACE				' '

struct  Date
{
	string year;
	string month;
	string day;
};

enum FilmRating { NR = 0, G, PG, PG13, R, X };
string FilmRatingString[6] = { "NR" ,"G","PG","PG13","R","X" };


enum FilmCategory {
	NoCategory,
	Animated,
	Comedy,
	Drama,
	Fantasy,
	Foreign,
	Horror,
	Musical,
	Mystery,
	Science_Fiction,
	Short,
	Silent
};

string FilmCategoryString[12] =
{
"NoCategory",
"Animated",
"Comedy",
"Drama",
"Fantasy",
"Foreign",
"Horror",
"Musical",
"Mystery",
"Science_Fiction",
"Short",
"Silent"
};


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

string StateTitle[] = { // A global array which is permitted 
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

inline FilmCategory getFilmCategoryEnum(const string fstr)
{
	for (int i = 0; i < NUMCATEGORY; i++)
	{
		if (fstr == FilmCategoryString[i])
		{
			return (FilmCategory)i;
		}
	}
	return NoCategory;
}

inline FilmRating getFilmRatingEnum(string rstr)
{
	for (int i = 0; i < NUMRATING; i++)
	{
		if (rstr == FilmRatingString[i])
		{
			return (FilmRating)i;
		}
	}
	return NR;
}

inline string getFilmCategoryString(const FilmCategory c)
{
	return (FilmCategoryString[c]);
}


inline string getFilmRatingString(const FilmRating r)
{
	return (FilmRatingString[r]);
}


#endif