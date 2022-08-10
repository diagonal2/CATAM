package project;

import java.io.IOException;

public class Q3 {
	
	static double[] xData = new double[2049];
	static double[] yData = new double[2049];
	
	static double F(double p, int i, int n) {
		
		byte[] b = utilities.Tools.extractBinary(i, n);
		int count = 0;
		double sum = 0;
		for (int j = 1; j < n + 1; j++)
			if (b[n - j] == 1) {
				sum += Math.pow(p, count) * Math.pow(1d - p, j - count);
				count++;
			}
		return sum;
		
	}
	
	static void fillData(double p) {
		
		for (int i = 0; i < 2048; i++) {
			xData[i] = (double) i / 2048;
			yData[i] = F(p, i, 11);
		}
		xData[2048] = 1;
		yData[2048] = 1;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		double p = 3/4d;
		
		fillData(p);
		
		graphs.XYDataset d = new graphs.XYDataset("Q3", "x", "F(x)");
		d.addSeries(xData, yData, 2049, "data3");
		d.plotLine(false);
	//	d.display();
		d.save("F3.1.png");
		
		Q1.task(p,"2.png");
		double max = 0d, average = 0d;
		int badEst = 0;
		for (int i = 0; i < 2049; i++) {
			double tmp = Math.abs(Q1.yData[i] - Q3.yData[i]);
			if (tmp > 0.005d) badEst++;
			if (tmp > max) max = tmp;
			average += tmp;
		}
		average /= 2049;
		System.out.println(max + "\n" + average + "\n" + badEst);
		
	}
	
}
