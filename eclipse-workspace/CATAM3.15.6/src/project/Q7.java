package project;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Q7 {
	
	protected static Polynomial task1(Polynomial f) {
		
		int p = (int) f.getMod();
		try {
			if (p < f.degree() + 2) {
				Polynomial g = Polynomial.term(p, 1, p).plus(Polynomial.term(p, -1, 1));
				return Polynomial.gcd(f, g);
			} else {
				Polynomial g = Polynomial.term(p, 1, f.degree() - 1);
				for (int i = p; i >= f.degree() + 2; i--) {
					g = g.timesX(1);
					if (g.degree() == f.degree())
						g = g.minus(f.times(g.getCoeff(g.degree())));
					// if (i % 1000000 == 0) System.out.println(i);
				}
				return Polynomial.gcd(f, g.timesX(2).plus(Polynomial.term(p, -1, 1)));
			}
		} catch(IncompatibleModulusException e) { return null; }
		
	}
	
	protected static Polynomial task2(Polynomial d, long v) {
		
		int p = (int) d.getMod();
		try {
			int n = (p - 1) / 2;
			Polynomial d0 = d.compose(Polynomial.term(p, 1, 1).plus(-v));
			if (n < d0.degree() + 1) {
				Polynomial g = Polynomial.term(p, 1, n).plus(-1);
				return Polynomial.gcd(d0, g).compose(Polynomial.term(p, 1, 1).plus(v));
			} else {
				Polynomial g = Polynomial.term(p, 1, d0.degree() - 1);
				for (int i = n; i >= d0.degree() + 1; i--) {
					g = g.timesX(1);
					if (g.degree() == d0.degree())
						g = g.minus(d0.times(g.getCoeff(g.degree())));
					// if (i % 1000000 == 0) System.out.println(i);
				}
				return Polynomial.gcd(d0, g.timesX(1).plus(-1)).compose(Polynomial.term(p, 1, 1).plus(v));
			}
		} catch(IncompatibleModulusException e) { return null; }
		
	}
	
	private static void task(int p, long ... a) throws Exception {
		
		Polynomial f = new Polynomial(p, a);
		long[] gcd = integers.Tools.extendedGcd(f.getCoeff(f.degree()), p);
		if (gcd[0] != 1) throw new Exception("Please enter a prime p.");
		f = f.times(gcd[1]);
		
		f = task1(f);
		
		if (f.degree() == 0) System.out.println("Roots: (none)");
		else {
			Polynomial d = f.clone();
			while (d.degree() != 1) {
				long v = ThreadLocalRandom.current().nextLong(p);
				System.out.println("\n" + v);
				d = task2(f, v);
				System.out.println(d);
			}
			System.out.println("Roots: " + ((p - d.getCoeff(0)) % p) + ", " + d.getCoeff(0));
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		int p;
		long a;
		System.out.print("p: ");
		p = sc.nextInt();
		System.out.print("a: ");
		a = sc.nextLong();
		sc.close(); 
		System.out.println();
		
		task(p, -a, 0, 1);
		
	}
	
}
