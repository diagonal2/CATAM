package project;

import java.io.IOException;

public class Q5 {
	
	public static void main(String[] args) throws IOException {
		
		double p = 3/4d;
		
		double xData[] = new double[65536];
		double yData[] = new double[65536];
		
		for (int k = 1; k < 65537; k++) {
			xData[k - 1] = k / Math.pow(2, 20);
			yData[k - 1] = (Q3.F(p, (9 >> 16) + k, 20) - Q3.F(p, 9 >> 16, 20)) * Math.pow(2, 20) / k;
		}
		
		graphs.XYDataset d = new graphs.XYDataset("Q5", "\u03b4", "(F(c + \u03b4) - F(c)) / \u03b4");
		d.addSeries(xData, yData, 65536, "data5");
		d.setRange('x', 0, Math.pow(2, -5));
		d.plotScatter();
	//	d.display();
		d.save("F5.1.jpg");
		
		for (int k = 1; k < 65537; k++) {
			xData[k - 1] = -k / Math.pow(2, 20);
			yData[k - 1] = (Q3.F(p, (9 << 16) - k, 20) - Q3.F(p, 9 << 16, 20)) * Math.pow(2, 20) / -k;
		}
		
		d = new graphs.XYDataset("Q5", "\u03b4", "(F(c + \u03b4) - F(c)) / \u03b4");
		d.addSeries(xData, yData, 65536, "data5");
		d.setRange('x', -Math.pow(2, -5), 0);
		d.plotScatter();
	//	d.display();
		d.save("F5.2a.jpg");
		
		d = new graphs.XYDataset("Q5", "\u03b4", "(F(c + \u03b4) - F(c)) / \u03b4");
		d.addSeries(xData, yData, 65536, "data5");
		d.setRange('x', -Math.pow(2, -12), 0);
		d.plotScatter();
	//	d.display();
		d.save("F5.2.jpg");
		
	}
}