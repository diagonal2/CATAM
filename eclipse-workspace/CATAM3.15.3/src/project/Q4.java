package project;

import java.util.Scanner;

public class Q4 {
	
	public static void main(String[] args) throws NotPositiveFormException {
		
		Scanner sc = new Scanner(System.in);
		int[] input = new int[6];
		BQF f1, f2;
		
		while (true) {
			
			while (true) {
				System.out.print("Input coefficients of 1st primitive form: ");
				for (int i = 0 ; i < 3; i++) input[i] = sc.nextInt();
				f1 = new BQF(input[0], input[1], input[2]);
				if (!f1.isPrimitive()) System.out.println("\nForm is not primitive.\n\n");
				else break;
			}
			
			while (true) {
				System.out.print("Input coefficients of 2nd primitive form: ");
				for (int i = 3 ; i < 6; i++) input[i] = sc.nextInt();
				f2 = new BQF(input[3], input[4], input[5]);
				if (!f2.isPrimitive()) System.out.println("\nForm is not primitive.\n\n");
				else break;
			}
			
			if (f1.disc() != f2.disc()) System.out.println("\nForms need to have same discriminant.\n\n");
			else break;
			
		}
		
		sc.close();
		
		BQF f3 = BQF.compose(f1, f2);
		System.out.println("Composition is: " + f3.toString());
		
	}
	
}
