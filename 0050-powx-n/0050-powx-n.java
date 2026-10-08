class Solution {
    public double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) return 1.0 / power(x, -exp);   // invert only at the end
        return power(x, exp);
    }

    private double power(double x, long n) {
        if (n == 0) return 1.0;
        double half = power(x, n / 2);
        return (n % 2 == 0) ? half * half : half * half * x;
    }
}