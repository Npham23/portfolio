#include "calculator.h"
#include <float.h>


double calculateCosecant(double x)
{
	double final_result = 0.0f; /* this is the result to the user              */
	double temp_result = 0.0f;  /* this is a temporary result                  */
	double temp_error = 0.0f;   /* error between temporary and final    result */
	double power = 0.0f;        /* numerator term in the Maclaurin  series     */
	double factorial = 0.0f;    /* denominator term in the Maclauring series   */

	bool minus = false;



	int n = 1; 

	do
	{
		if (n % 2)
		{
			power = power_function(x, n);
			factorial = factorial_function(n);

			if (minus == true)
			{
				temp_result = temp_result - (power / factorial);
				minus = false;
			}
			else
			{
				/* add the odd term */
				temp_result = temp_result + (power / factorial);
				minus = true;
			}
			temp_error = final_result - temp_result;

			if (temp_error < DBL_EPSILON)
			{
	
				temp_error = -temp_error;
			}

			final_result = temp_result;
		}
		else
		{
			/* the Maclauring term is even , do nothing */
		}
		n++;

	} while (n < MY_INFINITY && temp_error > error);

	final_result = 1 / final_result; //because its 1/sine

	return final_result;
}