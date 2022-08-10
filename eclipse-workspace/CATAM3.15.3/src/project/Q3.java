package project;

public class Q3 {
	
	private static int[] reduce(int[] f) {
		
		boolean tmp1 = (-f[0] < f[1]) && (f[1] <= f[0]) && (f[0] < f[2]);
		boolean tmp2 = (0 <= f[1]) && (f[1] <= f[0]) && (f[0] == f[2]);
		
		while (!(tmp1 || tmp2)) {
			if (f[2] < f[0] || (f[2] == f[0] && f[1] < 0)) {
				System.out.printf("%-7s%-15s\n", "S:", "(" + f[2] + ", " + -f[1] + ", " + f[0] + ")");
				f = new int[] { f[2], -f[1], f[0] };
			}
			else if (f[1] > f[0]) {
				int k = (f[1] + f[0]) / 2 / f[0];
				int b = f[1] - 2 * k * f[0], c = k * k * f[0] - k * f[1] + f[2];
				System.out.printf("%-7s%-15s\n", "T^-" + k + ":", "(" + f[0] + ", " + b + ", " + c + ")");
				f = new int[] { f[0], b, c };
			}
			else if (f[1] <= -f[0]) {
				int k = (f[1] - f[0]) / 2 / f[0];
				int b = f[1] - 2 * k * f[0], c = k * k * f[0] - k * f[1] + f[2];
				System.out.printf("%-7s%-15s\n", "T^" + (-k) + ":", "(" + f[0] + ", " + b + ", " + c + ")");
				f = new int[] { f[0], b, c };
			}
			tmp1 = (-f[0] < f[1]) && (f[1] <= f[0]) && (f[0] < f[2]);
			tmp2 = (0 <= f[1]) && (f[1] <= f[0]) && (f[0] == f[2]);
		}
		
		return f;
		
	}
	
	public static void main(String[] args) {
		
		System.out.printf("%-7s%-15s\n", "", "(134, 383, 274)");
		reduce(new int[] { 134, 383, 274 } );
		
		System.out.printf("\n%-7s%-15s\n", "", "(209, 274, 90)");
		reduce(new int[] { 209, 274, 90 } );
		
	}
	
}
