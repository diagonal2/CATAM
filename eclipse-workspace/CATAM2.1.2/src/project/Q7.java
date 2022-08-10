package project;

public class Q7 {
	
	private static int a = 2, n = 256;
	private static String format = "%-24s%-34s\n";
	
	private static double falsePosition(double p1, double p2, double epsilon) {
		
		if (p1 < p2) {
			double q1 = (new Section3(p1, a)).rk4(1d/n, n)[n][0];
			System.out.printf(format, p1, q1);
			if (Math.abs(q1) < epsilon) return p1;
			else {
				double q2 = (new Section3(p2, a)).rk4(1d/n, n)[n][0];
				System.out.printf(format, p2, q2);
				if (Math.abs(q2) < epsilon) return p2;
				else if (q1 * q2 < 0) return falsePosition(p1, q1, p2, q2, epsilon);
				else return search(p1, q1, p2, epsilon, (byte) Math.signum(q1), 0);
			}
		} else return Double.NaN;
		
	}
	
	private static double search(double p1, double q1, double p2, double epsilon, byte sign, int stack) {
		
		double p = (p1 + p2) / 2;
		double q = (new Section3(p, a)).rk4(1d/n, n)[n][0];
		System.out.printf(format, p, q);
		
		if (stack > 15) return Double.NaN;
		else if (q * sign > 0) {
			double q7 = search(p1, q1, p, epsilon, sign, stack + 1);
			if (Double.isNaN(q7)) return search(p, q, p2, epsilon, sign, stack + 1);
			else return q7;
		} else return falsePosition(p1, q1, p, q, epsilon);
		
	}
	
	private static double falsePosition(double p1, double q1, double p2, double q2, double epsilon) {
		
		double p = (q2*p1 - q1*p2) / (q2 - q1);
		double q = (new Section3(p, a)).rk4(1d/n, n)[n][0];
		System.out.printf(format, p, q);
		
		if (Math.abs(q) < epsilon) return p;
		else if (q * q1 < 0) return falsePosition(p1, q1, p, q, epsilon);
		else return falsePosition(p, q, p2, q2, epsilon);
		
	}
	
	public static void main(String[] args) {
		
		System.out.printf(format, "p", "y(1)");
		double p = falsePosition(4, 5, 0.00000001);
		System.out.println("p^(1) = " + p);
		
	}
	
}
