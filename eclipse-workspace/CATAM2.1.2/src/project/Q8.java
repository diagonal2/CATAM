package project;

import java.io.IOException;

public class Q8 {
	
	private static int a = 10, n = 1024;
	
	private static double binarySearch(double p1, double p2, double delta) {
		
		if (p1 < p2) {
			double q1 = (new Section3(p1, a)).rk4(1d/n, n)[n][0];
			double q2 = (new Section3(p2, a)).rk4(1d/n, n)[n][0];
			if (q1 * q2 < 0) {
				if (p2 - p1 < delta) return p1;
				else return binarySearch(p1, q1, p2, q2, delta);
			}
			else return search(p1, q1, p2, delta, (byte) Math.signum(q1), 0);
		} else return Double.NaN;
		
	}
	
	private static double search(double p1, double q1, double p2, double delta, byte sign, int stack) {
		
		double p = (p1 + p2) / 2;
		double q = (new Section3(p, a)).rk4(1d/n, n)[n][0];
		
		if (stack > 15) return Double.NaN;
		else if (q * sign > 0) {
			double q7 = search(p1, q1, p, delta, sign, stack + 1);
			if (Double.isNaN(q7)) return search(p, q, p2, delta, sign, stack + 1);
			else return q7;
		} else return binarySearch(p1, q1, p, q, delta);
		
	}
	
	private static double binarySearch(double p1, double q1, double p2, double q2, double delta) {
		
		double p = (p1 + p2) / 2;
		double q = (new Section3(p, a)).rk4(1d/n, n)[n][0];
		
		if (q * q1 < 0) {
			if (p - p1 < delta) return p1;
			else return binarySearch(p1, q1, p, q, delta);
		} else {
			if (p2 - p < delta) return p;
			else return binarySearch(p, q, p2, q2, delta);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		double[] p = {
			binarySearch(6, 20, 0.000001), 
			binarySearch(20, 34, 0.000001), 
			binarySearch(34, 48, 0.000001), 
			binarySearch(48, 62, 0.000001), 
			binarySearch(62, 76, 0.000001)
		};
		
		graphs.XYDataset d = new graphs.XYDataset("Q8", "x", "y");
		double xData[] = new double[n + 1];
		for (int j = 0; j <= n; j++) xData[j] = (double) j / n;
		
		for (int i = 0; i < 5; i++) {
			
			System.out.println("p^(" + (i+1) + ") = " + p[i]);
			double yData[] = new double[n + 1];
			for (int j = 0; j <= n; j++)
				yData[j] = (new Section3(p[i], 10)).rk4(1d/n, n)[j][0];
			
			double tmp[] = new double[n + 1];
			for (int j = 0; j <= n; j++)
				tmp[j] = Math.pow(1 + xData[j], -10) * yData[j] * yData[j];
			double normaliser = tmp[0] - tmp[n];
			for (int k = 1; k <= n / 2; k++)
				normaliser += 4 * tmp[2*k - 1] + 2 * tmp[2*k];
			normaliser = p[i] * Math.sqrt(normaliser / 3 / n);
			for (int j = 0; j <= n; j++)
				yData[j] /= normaliser;
			
			d.addSeries(xData, yData, n + 1, "y^(" + (i+1) + ")");
		}
		
		d.plotLine(false);
	//	d.display();
		d.save("F8.3.jpg");
		
	}
	
}
