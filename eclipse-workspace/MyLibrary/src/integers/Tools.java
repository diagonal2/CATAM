package integers;

public class Tools {
	
	public static int gcd(int a, int b) {
		
		if (Math.abs(a) < Math.abs(b)) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		if (b == 0) return a;
		
		a = a % b;
		
		if (a == 0) return b;
		else return gcd(b, a);
		
	}
	
	public static long gcd(long a, long b) {
		
		if (Math.abs(a) < Math.abs(b)) {
			long tmp = a;
			a = b;
			b = tmp;
		}
		
		if (b == 0) return a;
		
		a = a % b;
		
		if (a == 0) return b;
		else return gcd(b, a);
		
	}
	
	public static int[] extendedGcd(int a, int b) {
		
		if (Math.abs(a) >= Math.abs(b)) return extendedGcd(a, b, 1, 0, 0, 1);
		else return extendedGcd(b, a, 0, 1, 1, 0);
		
	}
	
	public static long[] extendedGcd(long a, long b) {
		
		if (Math.abs(a) >= Math.abs(b)) return extendedGcd(a, b, 1, 0, 0, 1);
		else return extendedGcd(b, a, 0, 1, 1, 0);
		
	}
	
	private static int[] extendedGcd(int a, int b, int c, int d, int e, int f) {
		
		if (b == 0) return new int[] { a, 1, 0 };
		
		int q = a / b;
		a = a % b;
		
		if (a == 0) return new int[] { b, e, f };
		else return extendedGcd(b, a, e, f, c - q * e, d - q * f);
		
	}
	
	private static long[] extendedGcd(long a, long b, long c, long d, long e, long f) {
		
		if (b == 0) return new long[] { a, 1, 0 };
		
		long q = a / b;
		a = a % b;
		
		if (a == 0) return new long[] { b, e, f };
		else return extendedGcd(b, a, e, f, c - q * e, d - q * f);
		
	}
	
	public static Long pow(long a, long b, long m) {
		
		if (m <= 0) return null;
		
		a = ((a % m) + m) % m;
		
		if (b == 0) return 1L;
		else if (b > 0) {
			if (b == 1) return a;
			else if (b % 2 == 0) return pow(a * a, b / 2, m) % m;
			else return (pow(a * a, b / 2, m) * a) % m;
		} else {
			long[] dxy = extendedGcd(a, m);
			if (dxy[0] != 1) return null;
			else return pow(dxy[1], -b, m) % m;
		}
		
	}
	
}
