double factorial_function(int x)
{
	double result = 1.0f;

	if (x < 0)
	{
		result = 0.0f;  /* this should never be the case */
	}
	else if (x == 0)
	{
		result = 1.0f;
	}
	else
	{
		/* x is definitely greater than 0 */

		for (int j = 1; j <= x; j++)
		{
			/* result = 1 * 1 * 2 * 3 ... * x */
			result = result * (double)j;
		}
	}
	return result;
}