/*
50. Pow(x, n)

Implement pow(x, n).
*/
class Solution {
	public double myPow(double x, int n) {
		// corner case
		if (n < 0) {
			return 1 / pow(x, -n);
		}
		else {
			return pow(x, n);
		}
	}
	
	private double pow(double x, int n) {
		// corner case
		if (n == 0) {
			return 1.0;
		}
		
		if (n == 1) {
			return x;
		}
		
		double val = pow(x, n/2);
		if (n % 2 == 0) {
			return val * val;
		}
		else {
			return val * val * x;
		}
	}
}