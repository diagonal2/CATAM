package project;

public class Q1 {
	
	public static void main(String[] args) {
		
		double[] hList = {0.5, 0.375, 0.25, 0.125, 0.1, 0.05};
		int[] printStep = {1, 1, 2, 4, 5, 10};
		
		for (int j = 0; j < 6; j++) {
			
			double h = hList[j];
			int n = (int) (3d / h);
			
			System.out.println("h = " + h);
			String format = "%-15s%-30s%-30s%-30s\n";
			System.out.printf(format, "x_n", "Y_n", "y_e(x_n)", "E_n");
			System.out.printf(format, 0, 0, 0, 0);
			
			double Y[] = Section2.ab2(h, n);
			double y[] = new double[99];
			y[0] = 0;
			
			for (int i = 0; i < n; i++) {
				y[i+1] = Section2.y_e((i+1)*h);
				if((i+1) % printStep[j] == 0)
					System.out.printf(format, (i+1)*h, Y[i+1], y[i+1], (Y[i+1]-y[i+1]));
			}
			
			double tmp1 = 0, tmp2 = 0, tmp3 = 0, tmp4 = 0;
			for (int i = 1; i < 7; i++) {
				tmp1 += i*h * Math.log(Math.abs(Y[i]-y[i]));
				tmp2 += i*h;
				tmp3 += Math.log(Math.abs(Y[i]-y[i]));
				tmp4 += i*i*h*h;
			}
			System.out.println("\u03b3 = " + (6*tmp1 - tmp2*tmp3) / (6*tmp4 - tmp2*tmp2));
			
			System.out.println();
			
		}
			
	}
	
}
