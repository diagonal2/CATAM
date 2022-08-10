package project;

import java.util.ArrayList;

public class Q2 {
	
	public static void main(String[] args) {
		
		System.out.printf("%-9s%-7s%-7s\n", "d", "r(d)", "h(d)");
		
		for (int d = -120; d < 0; d++) {
			
			int h = 0;
			
			if (d % 4 == 0 || d % 4 == -3) {
				
				ArrayList<BQF> fs = BQF.reducedBQFsByDisc(d);
				for (BQF f : fs)
					if (f.isPrimitive()) h++;
				
				System.out.printf("%-9s%-7s%-7s\n", d, fs.size(), h);
				
			}
			
		}
		
	}
	
}
