package project;

import java.io.IOException;

public class Q3 {
	
	public static void main(String[] args) throws IOException {
		
		double[] x = new double[26];
		for (int i = 0; i < 26; i++)
			x[i] = 0.08d * i;
		double[] a = Section2.euler(0.08, 25);
		double[] b = Section2.ab2(0.08, 25);
		double[] c = Section2.rk4(0.08, 25);
		double[] exact = new double[26];
		for (int i = 0; i < 26; i++) exact[i] = Section2.y_e(0.08d * i);
		
		String format = "%-7s%-22s%-22s%-22s%-22s\n";
		System.out.printf(format, "x_n", "By Euler", "By AB2", "By RK4", "Exact");
		for (int i = 0; i < 26; i++)
			System.out.printf(format, 0.08d * i, a[i], b[i], c[i], exact[i]);
		
		graphs.XYDataset d = new graphs.XYDataset("Q3", "x", "y");
		d.addSeries(x, a, 26, "Euler");
		d.addSeries(x, b, 26, "AB2");
		d.addSeries(x, c, 26, "RK4");
		d.addSeries(x, exact, 26, "exact");
		d.plotLine(true);
	//	d.display();
		d.save("F3.1.jpg");
		
	}
	
}
