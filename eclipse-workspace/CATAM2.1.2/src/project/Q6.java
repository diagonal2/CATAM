package project;

import java.io.IOException;

public class Q6 {
	
	public static void main(String[] args) throws IOException {
		
		double p = 4, a = 2;
		
		Section3 s = new Section3(p, a);
		String format = "%-6s%-15s%-24s%-24s%-24s\n";
		System.out.printf(format, "k", "h", "By RK4", "Exact", "Error");
		
		double[] xData = new double[13];
		double[] yData = new double[13];
		
		for (int k = 0; k < 13; k++) {
			double c = s.rk4(1d / (10 << k), (10 << k))[10 << k][0];
			double exact = Section3.y_e2(1, p);
			System.out.printf(format, k, 1d / (10 << k), c, exact, c - exact);
			xData[k] = 1d / (10 << k);
			yData[k] = Math.abs(c - exact);
		}
		
		graphs.XYDataset d = new graphs.XYDataset("Q6", "h", "|E_n|");		
		d.addSeries(xData, yData, 12, "data");
		d.useLogAxis('x');
		d.useLogAxis('y');
		d.plotScatter();
		d.display();
	//	d.save("F8.2.jpg");
		
		double[] fit = (new graphs.XYData(xData, yData, 0, 8)).powerFit1();
		System.out.println(fit[0] + "   " + fit[1]);
		
	}
	
}
