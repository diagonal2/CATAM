package project;

public class Q5 {
	
	private static Long sqrt(long a, long p) {
		
		int alpha = 0;
		long s = p - 1;
		while (s % 2 == 0) {
			alpha++;
			s /= 2;
		}
		
		long n = 2;
		while (true) {
			if (Q2.j(n, p) == 1) n++;
			else break;
		}
		
		long b = integers.Tools.pow(n, s, p);
		long bInv = integers.Tools.extendedGcd(b, p)[1];
		
		long A = integers.Tools.pow(a, s, p);
		long[] t = new long[alpha];
		long r = 0;
		for (int k = 0; k < alpha; k++) {
			long tmp = integers.Tools.pow(A, 1 << (alpha - 1 - k), p);
			if (tmp == 1) t[k] = 0;
			else {
				t[k] = 1;
				if (k != 0) r += 1 << (k - 1);
				A = (A * integers.Tools.pow(bInv, 1 << k, p)) % p;
			}
		}
		
		if (t[0] == 1) return null;
		else return (integers.Tools.pow(b, r, p) * integers.Tools.pow(a, (p - s) / 2, p)) % p;
		
	}
	
	public static void main(String[] args) {
		
		System.out.printf("%-11s%-9s%-9s\n", "p", "a", "square root");
		for (int a = 1; a < 21; a++) {
			System.out.printf("%-11s%-9s%-9s\n", 30275233, a, sqrt(a, 30275233));
		}
		
		System.out.printf("%-11s%-9s%-9s\n", 23456789, 972005, sqrt(972005, 23456789));
		System.out.printf("%-11s%-9s%-9s\n", 41516171, 392290, sqrt(392290, 41516171));
		System.out.printf("%-11s%-9s%-9s\n", 20201231, 276168, sqrt(276168, 20201231));
		
	}
	
}
