#include "calculator.h"
#include <float.h>

double calculateExponential(double x)
{
	double result = 0.0f;

	double temp_result = 0.0f;  //I have read the sine.ccp file, and so I got too use to it, 
	//because I understand it more in this format
	double temp_error = 0.0f;
	double power = 0.0f;
	double factorial = 0.0f;

	bool minus = false;

	int n = 0;  //n = 0 because we aren't adding anything to n

	do
	{
	
		//Does it regardless if it n is odd or even, this series isn't alternating
		power = power_function(x, n);
		factorial = factorial_function(n);


		
		if (minus == false)//sine and cosine have a similar structure to the exponential series
		{
			temp_result = temp_result - (power / factorial);

			minus = false;
		}
		else
		{
			temp_result = temp_result + (power / factorial);

			minus = true;
		}
		


		temp_error = result - temp_result;

		if (temp_error < DBL_EPSILON)
		{
			temp_error = -temp_error;
		}

		result = temp_result;
	
		n++;

	} while (n < MY_INFINITY && temp_error > error);


	if (result < 0)//making sure the result will always be positive because this series is not alternating
	{
		result = -result;
	}

	return result;
}