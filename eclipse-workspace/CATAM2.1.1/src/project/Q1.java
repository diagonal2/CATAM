package project;

import java.io.IOException;

public class Q1 {
	
	static double[] xData = new double[2049];
	static double[] yData = new double[2049];
	
	static void task(double p, String file) throws IOException {
		
		Coin c = new Coin(p);
		double[] sample = new double[38416];
		
		for(int i = 0; i < 38416; i++) {			
			double tmp = 0;
			for (int j = 0; j < 30; j++) tmp += (double) c.toss() / Math.pow(2, j+1);
			sample[i] = tmp;
		}
		
		utilities.Tools.mergeSort(sample, 38416);
		
		for (int i = 0; i < 2049; i++) xData[i] = (double) i / 2048;
		
		int count = 0, i = 1;
		yData[0] = 0;
		while (i < 2049 && count < 38416)
			if (sample[count] <= (double) i / 2048) count++;
			else {
				yData[i] = (double) count / 38416;
				i++;
			}
		for (int j = i; j < 2049; j++) yData[j] = 1;
		
		graphs.XYDataset d = new graphs.XYDataset("Q1", "x", "F(x)");
		d.addSeries(xData, yData, 2049, "data1");
		d.plotLine(false);
	//	d.display();
		d.save(file);
		
	}
	
	public static void main(String[] args) throws IOException {
		
		task(2/3d, "F1.jpg");
						
	}
	
}
