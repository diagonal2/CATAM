package project;

public class Q2 {
	
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
		
		for (int a = 1; a < 100; a += 2)
			for (int p = 1; p < 100; p += 2)
				System.out.println(a + " " + p + " " + j(a, p));
		
	}
	
}
