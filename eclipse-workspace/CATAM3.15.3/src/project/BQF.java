package project;

import java.util.ArrayList;

public class BQF {
	
	private long a, b, c;
	
	public BQF(long a, long b, long c) throws NotPositiveFormException {
		if (b * b - 4 * a * c >= 0 || a <= 0) throw new NotPositiveFormException();
		else {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	
	public BQF(BQF f) {
		this.a = f.a;
		this.b = f.b;
		this.c = f.c;
	}
	
	public long getA() {
		return a;
	}
	
	public long getB() {
		return b;
	}
	
	public long getC() {
		return c;
	}
	
	public long disc() {
		return b * b - 4 * a * c;
	}
	
	public boolean isPrimitive() {
		long tmp = integers.Tools.gcd(integers.Tools.gcd(a, b), c);
		return (tmp == 1) || (tmp == -1);
	}
	
	public boolean isReduced() {
		boolean tmp1 = (-a < b) && (b <= a) && (a < c);
		boolean tmp2 = (0 <= b) && (b <= a) && (a == c);
		return tmp1 || tmp2;
	}
	
	@Override
	public String toString() {
		return "(" + a + ", " + b + ", " + c + ")";
	}
	
	public static BQF reduce(BQF f) {
		
		try {
			while (!f.isReduced()) {
				if (f.c < f.a || (f.c == f.a && f.b < 0)) f = new BQF(f.c, -f.b, f.a);
				else if (f.b > f.a || f.b <= -f.a) {
					long k = (f.b > 0) ? (f.b + f.a) / 2 / f.a : (f.b - f.a) / 2 / f.a;
					f = new BQF(f.a, f.b - 2 * k * f.a, k * k * f.a - k * f.b + f.c);
				}
			}
		} catch (NotPositiveFormException e) { }
		
		return f;
		
	}
	
	public static boolean areEqual(BQF f, BQF g) {
		return (f.a == g.a && f.b == g.b && f.c == g.c);
	}
	
	public static boolean areEquiv(BQF f, BQF g) {
		BQF f1 = BQF.reduce(f);
		BQF g1 = BQF.reduce(g);
		return (f1.a == g1.a && f1.b == g1.b && f1.c == g1.c);
	}
	
	public static ArrayList<BQF> reducedBQFsByDisc(long d) {
		
		if (d % 4 == 0 || d % 4 == -3) {
			
			ArrayList<BQF> fs = new ArrayList<BQF>(0);
			
			for (long b = (d % 2 + 2) % 2; b <= Math.sqrt(-d / 3); b += 2) {
				
				for (long a = Math.max(1, b); a <= Math.sqrt(-d / 3); a++) {
					
					if ((b * b - d) % (4 * a) == 0) {
						
						long c = (b * b - d) / 4 / a;
						
						{
							BQF f = null;
							try { f = new BQF(a, b, c); } catch (NotPositiveFormException e) { }
							if (f.isReduced()) fs.add(f);
						}
						
						if (b != 0) {
							BQF f = null;
							try { f = new BQF(a, -b, c); } catch (NotPositiveFormException e) { }
							if (f.isReduced()) fs.add(f);
						}
						
					}
					
				}
			
			}
			
			return fs;
			
		} else return new ArrayList<BQF>(0);
		
	}
	
	public static ArrayList<BQF> reducedPrimBQFsByDisc(long d) {
		
		ArrayList<BQF> fs = BQF.reducedBQFsByDisc(d);
		for (int i = 0; i < fs.size(); i++)
			if (!fs.get(i).isPrimitive()) {
				fs.remove(i);
				i--;
			}
		return fs;
		
	}
	
	public static BQF compose(BQF f1, BQF f2) throws NotPositiveFormException {
		
		if (f1.isPrimitive() && f2.isPrimitive()) {
			
			long beta = (f1.getB() + f2.getB()) / 2, gamma = (f2.getB() - f1.getB()) / 2;
			long[] mxy = integers.Tools.extendedGcd(f1.getA(), beta);
			long m = mxy[0], x = mxy[1], y = mxy[2];
			long[] nuv = integers.Tools.extendedGcd(m, f2.getA());
			long n = nuv[0], z = nuv[1] * (gamma * x - f1.getC() * y);
			
			long tmp = f2.getA() / n;
			z = ((z % tmp) + tmp) % tmp;
			if (z > tmp / 2) z -= tmp;
			
			long a = f1.getA() * f2.getA() / n / n;
			long b = f1.getB() + 2 * f1.getA() * z / n;
			long c = (b * b - f1.disc()) / 4 / a;
			return new BQF(a, b, c);
			
		} else return null;
		
	}
	
}
