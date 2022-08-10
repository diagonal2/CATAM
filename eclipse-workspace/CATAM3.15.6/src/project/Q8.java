package project;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Q8 extends Q7 {
	
	private static ArrayList<Long> roots = new ArrayList<Long>();
	
	private static void task2a(Polynomial f) {
		
		if (f.degree() == 1) {
			roots.add((f.getMod() - f.getCoeff(0)) % f.getMod());
			return;
		}
		
		Polynomial d = f.clone();
		while (d.degree() == 0 || d.degree() == f.degree()) {
			d = f.clone();
			long v = ThreadLocalRandom.current().nextLong(f.getMod());
			System.out.println("\nv = " + v);
			d = task2(f, v);
			System.out.println("d = " + d);
		}
		
		task2a(d);
		Polynomial q = null;
		try { q = f.quotient(d); } catch(IncompatibleModulusException e) { }
		task2a(q);
		
	}
	
	private static void task(int p, long ... a) throws Exception {
		
		Polynomial f = new Polynomial(p, a);
		long[] gcd = integers.Tools.extendedGcd(f.getCoeff(f.degree()), p);
		if (gcd[0] != 1) throw new Exception("Please enter a prime p.");
		f = f.times(gcd[1]);
		roots = new ArrayList<Long>();
		
		f = task1(f);
		System.out.println("f = " + f);
		
		if (f.degree() == 0) System.out.println("\nRoots: (none)");
		else {
			task2a(f);
			System.out.print("\nRoots: ");
			for (int i = 0; i < roots.size() - 1; i++) {
				System.out.print(roots.get(i) + ", ");
			}
			System.out.println(roots.get(roots.size() - 1));
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		long start = System.currentTimeMillis();
	    
		task(35564117, 6, 0, 12, 5, 1);
		System.out.println("\n");
		task(35564117, 4, 7, 3, 1, 1);
		System.out.println("\n");
		task(35564117, 8, 3, 15, 4, 1);
		System.out.println("\n");
		
		long end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");
		
	}
	
}
