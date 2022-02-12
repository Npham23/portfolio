#include "calculator.h"
#include <float.h>
// Do not touch because this was finished


/* _______________________________________________________________________________________ */
/*|            oo                                                                         |*/
/*|          ______                                                                       |*/
/*|          \         n                          3       5       7       9       11      |*/
/*|           \    (-1)       2n+1               x       x       x       x       x        |*/
/*| sine(x) =  \  __________ x      =  1 + x - _____ + _____ - _____ + _____ - ____ ...   |*/
/*|            /                                 3!      5!      7!      9!      11!      |*/
/*|           /    (2n + 1)!                                                              |*/
/*|          /_____                                                                       |*/
/*|            n=0                                                                        |*/
/*|                                                                                       |*/
/*|_______________________________________________________________________________________|*/

double calculateSine(double x)
{
	double final_result = 0.0f; /* this is the result to the user              */
	double temp_result = 0.0f;  /* this is a temporary result                  */
	double temp_error = 0.0f;   /* error between temporary and final    result */
	double power = 0.0f;        /* numerator term in the Maclaurin  series     */
	double factorial = 0.0f;    /* denominator term in the Maclauring series   */

	/* start with addition because the first term in the Maclaurin series is x */
	bool minus = false;



	int n = 1;   /* start with the first Maclauring term which is x            */

	do
	{
		if (n % 2)
		{
			/*--------------------------------------------------------------------------*/
			/* n is odd                                                                 */
			/* the Maclaurin series for sine(x) factors in only the odd terms of the    */
			/* series. The terms also alternate between addition of one odd term and    */
			/* subtraction of the next odd term                                         */
			/*--------------------------------------------------------------------------*/
			power = power_function(x, n);
			factorial = factorial_function(n);

			if (minus == true)
			{
				/* subtract the odd term */
				temp_result = temp_result - (power / factorial);
				minus = false;
			}
			else
			{
				/* add the odd term */
				temp_result = temp_result + (power / factorial);
				minus = true;
			}

			/* temp_error calculates the difference between two consecutive terms of    */
			/* the Maclaurin series and check if this error with within the desired     */
			/* error tolerance to terminate the while loop                              */
			temp_error = final_result - temp_result;

			if (temp_error < DBL_EPSILON)
			{
				/* FLT_EPSILON is the closest thing to zero to check for a negative number*/
				/* convert to the absolute value of temp_error */
				temp_error = -temp_error;
			}

			final_result = temp_result;
		}
		else
		{
			/* the Maclauring term is even , do nothing */
		}

		/* increment the loop counter */
		n++;

	} while (n < MY_INFINITY && temp_error > error);

	return final_result;
}