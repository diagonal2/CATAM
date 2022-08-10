package project;

import java.util.concurrent.ThreadLocalRandom;

public class Q1 {
	
	private static long l(long a, long p) {
		long l = integers.Tools.pow(a, (p - 1) / 2, p);
		return (l > 1 ? l - p : l);
	}
	
	public static void main(String[] args) {
		
		long p = 30275233;
		System.out.printf("%-11s%-5s\n", "a", "(a|p)");
		int counter1 = 0;
		for (int i = 0; i < 100; i++) {
			long a = ThreadLocalRandom.current().nextLong(p);
			long l = l(a, p);
			System.out.printf("%-11s%-5s\n", a, l);
			if (l == 1) counter1++;
		}
		System.out.println("#1s = " + counter1 + ", #(-1)s = " + (100 - counter1));
		
		System.out.printf("\n\n%-11s%-5s\n", "a", "(a|p)");
		counter1 = 0;
		for (int a = 1; a < 101; a++) {
			long l = l(a, p);
			System.out.printf("%-11s%-5s\n", a, l);
			if (l == 1) counter1++;
		}
		System.out.println("#1s = " + counter1 + ", #(-1)s = " + (100 - counter1));
		
	}
	
}
