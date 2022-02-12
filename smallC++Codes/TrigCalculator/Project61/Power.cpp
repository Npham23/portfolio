double power_function(double x, int exponent)
{
	double result = 1.0F;
	for (int i = 0; i < exponent; i++) //loops the amount of times of big the exponent of x is
	{
		result = result * x;
	}
	return result;
}