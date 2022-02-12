/*
Nathan Pham
CS2 Final Exam
Question 6
*/


#include <iostream>
#include <iomanip>
#include <string>
#include <stack>
#include <fstream>
using namespace std;

void delimiter(string j)
{
	stack <char> block;//creating the stack
	for (int i = 0; i < j.length(); i++)//will check until the end of the sentences/line
	{
		if (j[i] == '(' || j[i] == '[' || j[i] == '{')
		{
			block.push(j[i]);
		}
		else if (j[i] == ')' || j[i] == ']' || j[i] == '}')
		{
			if (block.empty())
			{
				cout << "string error, there is nothing here, check your text file again" << endl;
				return;
			}
			if (j[i] == ']' && block.top() == '[')// checks the []
			{
				block.pop();
			}
			else if (j[i] == '}' && block.top() == '{')// checks the {}
			{
				block.pop();
			}
			else if (j[i] == ')' && block.top() == '(')// checks the ()
			{
				block.pop();
			}
			else // tell user that this sentence is INCORRECT in the usage of the brackets
			{
				cout << "This sentence has ERRORS and SYNTAXS ERRORS, please fix the delimiter in the txt" << endl;
				return;
			}
		}
	}
	if (block.empty())//correct usage of brackets
	{
		cout << "This sentence has NO errors or syntax here, GOOD JOB!" << endl;
	}
}



int main()
{
	ifstream inFile;
	inFile.open("words.txt");
	if (inFile.fail()) { // optional, but just to see if we can open the file or not
		cout << "\nERROR: file cannot be located" << endl;
		return 0;
	}

	string l;
	while (getline(inFile, l))// loop until there is no more lines in the file
	{
		delimiter(l);//use function for each line in the file
	}
	
	inFile.close(); // close

	system("pause");
	return 0;
}