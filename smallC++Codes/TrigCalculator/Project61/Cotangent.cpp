#include "calculator.h"
#include <float.h>

double calculateCotangent(double x)
{
	double result = 0.0f;
	double temp_result = 0.0f;
	double temp_error = 0.0f;
	double power = 0.0f;
	double factorial = 0.0f;
	double cosine = 0.0f;
	double sine = 0.0f;
	double FINAL_RESULT = 0.0f;

	bool minus = false;

	int n = 0;

	do
	{
		if (n % 2)
		{
			/* the Maclauring term is odd , do nothing */
		}
		else
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
				temp_result = temp_result + (power / factorial);
				minus = true;
			}

			temp_error = result - temp_result;

			if (temp_error < DBL_EPSILON)
			{
				temp_error = -temp_error;
			}

			result = temp_result;
		}
		n++;

	} while (n < MY_INFINITY && temp_error > error);

	cosine = result;

	double final_result = 0.0f; /* Reset  */
	temp_result = 0.0f;
	temp_error = 0.0f;
	power = 0.0f;
	factorial = 0.0f;

	n = 1;

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

	sine = final_result;

	FINAL_RESULT = cosine / sine; //because cotangent is cosine over sine

	return FINAL_RESULT;
}