package project;

import java.util.Random;

public class Q8 {
	
	// private static int[] B = { 11, 13, 17, 19, 23, 25, 27, 29, 31, 37, 41, 43, 47, 49 };
	private static int[] C = { 654729075, 293096475, 68333265, 4456305 };
	private static int maxK = 10;
	private static Random r = new Random();
	
	private static BQF randomBQF(int d, int maxA) {
		
		BQF f = null;
		
		boolean tmp = true;
		while (tmp) {
			int a = r.nextInt(maxA) + 1;
			int b = a - ((a - d) % 2) - 2 * r.nextInt(a);
			int c = 0;
			if ((b * b - d) % (4 * a) == 0) {
				c = (b * b - d) / 4 / a;
				try { f = new BQF(a, b, c); } catch(NotPositiveFormException e) { return null; }
				if (f.isPrimitive()) {
					tmp = false;
					break;
				}
			}
		}
		
		return BQF.reduce(f);
		
	}
	
	private static BQF pow(BQF f, int n) {
		
		f = BQF.reduce(f);
		try {
			if (n == 0) {
				if (f.disc() % 2 == 0) return new BQF(1, 0, -f.disc() / 4);
				else return new BQF(1, 1, (1 - f.disc()) / 4);
			} else if (n == 1) return f;
			else if (n == -1) return BQF.reduce(new BQF(f.getA(), -f.getB(), f.getC()));
			else if (n % 2 == 0) return BQF.reduce(pow(BQF.compose(f, f), n / 2));
			else return BQF.reduce(BQF.compose(pow(BQF.compose(f, f), n / 2), f));
		} catch (NotPositiveFormException e) { return null; }
		
	}
	
	private static int task(int N) {
		
		boolean tmp = true;
		BQF f = null;
		int attemptCount = 0;
		
		while (tmp) {
			
			int k = 0;
			if (N % 4 == 0) k = r.nextInt(maxK) + 1;
			else if (N % 4 == 1) k = 4 * r.nextInt((maxK + 1) / 4) + 3;
			else if (N % 4 == 2) k = 2 * r.nextInt(maxK / 2) + 2;
			else k = 4 * r.nextInt((maxK + 3) / 4) + 1;
			System.out.println(k);
			
			int d = -k * N;
			f = randomBQF(d, (int) Math.sqrt(-d / 3));
			System.out.println(f.toString());
			for (int c : C) {
				f = pow(f, c);
				System.out.println(f.toString());
			}
			
			int count = 0;
			while (!Q6.isAmbiguous(f) && count < 1024) {
				f = pow(f, 2);
				System.out.println(f.toString());
				count++;
			}
			System.out.println();
			
			if (count < 1024 && k % f.getA() != 0) break;
			else attemptCount++;
			
		}
		
		return attemptCount;
		
	}
	
	public static void main(String[] args) throws NotPositiveFormException {
		
		// System.out.println(task(92386519) + "\n");
		System.out.println(task(65727293) + "\n");
		
	}
	
}
