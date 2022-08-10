package project;

import java.io.IOException;

public class Q4 {
	
	public static void main(String[] args) throws IOException {
		
		double[] a = new double[16];
		double[] b = new double[16];
		double[] c = new double[16];
		double[] x = new double[16];
		String format = "%-6s%-16s%-25s%-25s%-25s\n";
		
		System.out.printf(format, "k", "h", "By Euler", "By AB2", "By RK4");
		for (int k = 0; k < 16; k++) {
			int n = 1 << k;
			double exact = Section2.y_e(0.16d);
			a[k] = Section2.euler(0.16d / n, n)[n] - exact;
			b[k] = Section2.ab2(0.16d / n, n)[n] - exact;
			c[k] = Section2.rk4(0.16d / n, n)[n] - exact;
			x[k] = 0.16d / n;
			System.out.printf(format, k, x[k], a[k], b[k], c[k]);
			a[k] = Math.abs(a[k]);
			b[k] = Math.abs(b[k]);
			c[k] = Math.abs(c[k]);
		}
		
		graphs.XYDataset d = new graphs.XYDataset("Q4", "h", "|E_n|");		
		d.addSeries(x, a, 16, "Euler");
		d.addSeries(x, b, 16, "AB2");
		d.addSeries(x, c, 16, "RK4");
		d.useLogAxis('x');
		d.useLogAxis('y');
		d.plotLine(true);
	//	d.display();
		d.save("F4.1.jpg");
		
		System.out.println((new graphs.XYData(x, a, 16)).powerFit1()[1]);
		System.out.println((new graphs.XYData(x, b, 16)).powerFit1()[1]);
		System.out.println((new graphs.XYData(x, c, 12)).powerFit1()[1]);
		
	}
	
}
