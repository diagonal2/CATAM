package project;

import java.util.ArrayList;

public class Q2a {
	
	public static Long j(long a, long n) {
		if (n % 2 == 0) return null;
		else {
			a = ((a % n) + n) % n;
			return _j(a, n);
		}
	}
	
	private static long _j(long a, long n) {
		a -= (a + n / 2 - 1) / n * n;
		if (a == 0) return 0;
		else if (a == 1) return 1;
		else {
			int m = 1;
			if (a < 0) {
				a = -a;
				m *= (n % 4 == 1) ? 1 : -1;
			}
			long n8 = (n % 8 == 1 || n % 8 == 7) ? 1 : -1;
			while (a % 2 == 0) {
				a = a / 2;
				m *= n8;
			}
			m *= (a % 4 == 1 || n % 4 == 1) ? 1 : -1;
			return m * _j(n, Math.abs(a));
		}
	}
	
	public static void main(String[] args) {
		
		for (int d = -7; d >= -120; d--) {
			
			if (d % 4 == -1) d -= 3;
			
			ArrayList<BQF> fs1 = BQF.reducedPrimBQFsByDisc(d);
			int fs1s = fs1.size();
			
			int alpha = 0, b = -d;
			while (b % 2 == 0) {
				b /= 2;
				alpha++;
			}
			
			boolean passed = true;
			for (int k : new int[] { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113 }) {
				int D = d * k * k;
				ArrayList<BQF> fs = BQF.reducedPrimBQFsByDisc(D);
				long guess = j(k, b);
				if (alpha % 2 == 1) guess *= j(2, k);
				if (b % 4 == 1) guess *= j(-1, k);
				if (k - fs.size() / fs1s - guess != 0) passed = false;
			}
			System.out.println(d + ": " + passed);
			
		}
		
	}
	
}
