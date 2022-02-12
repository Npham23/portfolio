/*
Nathan Pham
CS2 FInal Exam
Question 3
*/

#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <vector> 
using namespace std;

int FindMin(int* aArray);
int FindMax(int* aArray);
double Average(int* aArray);
void Sort(int* aArray);

int main()
{
	ifstream inFile;
	inFile.open("number.txt"); // open file

	if (inFile.fail()) { // optional, but just to see if we can open the file or not
		cout << "\nERROR: file cannot be located" << endl;
		return 0;
	}

	string n;
	int i = 0;
	//establishing the dynamic array
	//not fixed, because "i" will increase
	while (getline(inFile, n))//reading the amount of lines here
	{
		i++;
	}
	inFile.close();

	int *const a = new int[i];
	inFile.open("number.txt");
	for (int j = 0; j < i; j++)
	{

		inFile >> a[j];
		cout << a[j] << endl;
	}
	
double ave = Average(a);
FindMin(a);
int min = *a;
cout << "The minimum is: " << min << endl;
FindMax(a);
int max = *a;
cout << "The maximum is: " << max << endl;
Sort(a);
cout << "The average is: " << ave << endl;
for (int j = 0; j < i; j++)
{
	cout << a[j] << endl;
}


	delete[] a;// remove dynamic array

	system("pause");
	return 0;
}


int FindMin(int* aArray)
{

	ifstream inFile;
	int j = 0;
	inFile.open("number.txt");
	string n;
	while (getline(inFile, n))//reading the amount of lines here
	{
		j++;
	}
	inFile.close();
	int i = 0;
	int min = 100;
	while (i != j) // while array doesn't meet the NULL, because nothing else exists in the array
	{
		if (min > (aArray[i]))
		{
			min = (aArray[i]);
		}
		i++;
	}
	return min;
}


int FindMax(int* aArray)
{
	ifstream inFile;
	int j = 0;
	inFile.open("number.txt");
	string n;
	while (getline(inFile, n))//reading the amount of lines here
	{
		j++;
	}
	int i = 0;
	int max = 0;
	while (i != j)
	{
		if (max < (aArray[i]))
		{
			max = (aArray[i]);
			*aArray = (aArray[i]);
		}
		i++;
	}
	return *aArray;
}


double Average(int* aArray)
{
	ifstream inFile;
	int j = 0;
	inFile.open("number.txt");
	string n;
	while (getline(inFile, n))//reading the amount of lines here
	{
		j++;
	}
	int i = 0;
	int total = 0;
	while (i != j)
	{

		total = total + aArray[i];
		i++;

	}
	double average;
	average = (double)total / (double)j;

	return average;
}

void Sort(int* aArray)
{
	ifstream inFile;
	int j = 0;
	inFile.open("number.txt");
	string n;
	while (getline(inFile, n))
	{
		j++;
	}

	for (int i = 0; i < j; i++)
	{
		for (int k = i + 1; k < j; k++)
		{
			if (aArray[i] > aArray[k])
			{
				int temp = aArray[i];
				aArray[i] = aArray[k];
				aArray[k] = temp;
			}
		}
	}

}
