#include "calculator.h"
#include <float.h>


double calculateSecant(double x)
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
		if (n % 2)
		{
			/* the Maclauring term is odd , do nothing */
		}
		else
		{
			/*--------------------------------------------------------------------------*/
			/* n is EVEN                                                                 */
			/* the Maclaurin series for cos(x) factors in only the EVEN terms of the    */
			/* series. The terms also alternate between addition of one even term and    */
			/* subtraction of the next odd term
			This was quite similar to the sine function                                  */
			/*--------------------------------------------------------------------------*/
			power = power_function(x, n);
			factorial = factorial_function(n);

			if (minus == true)//Well it doesn't matter if it is negative or not, as long as n is equal to 0, 
				//x will always be to the power of an even number, which will always makes it positive
			{
				/* subtract the EVEN term */
				temp_result = temp_result - (power / factorial);
				minus = false;
			}
			else
			{
				/* add the Even term */
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

	result = 1 / result;

	return result;
}