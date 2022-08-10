package project;

import java.io.IOException;

public class Rubbish {
	
	public static void main(String[] args) throws IOException {
		
		int a = 2;
		int n = 256;
		double[] xData = new double[129];
		double[] yData = new double[129];
		
		for (int i = 0; i < 129; i++) {
			xData[i] = 4 + (double) i / 128;
			yData[i] = (new Section3(xData[i], a)).rk4(1d / n, n)[n][0];
		}
		
		graphs.XYDataset d = new graphs.XYDataset("Q7", "p", "value");
		d.addSeries(xData, yData, 129, "y(1)");
		d.addSeries(new double[] {4, 5}, new double[] {yData[0], yData[128]}, 2, "line1");
		d.addSeries(new double[] {4, 5}, new double[] {0, yData[128]}, 2, "line2");
		d.setRange('x', 4, 5);
		d.plotLine(false);
	//	d.display();
		d.save("F7.1.jpg");
		
		System.out.println(yData[128] - yData[0]);
		System.out.println(yData[128]);
		
		a = 10;
		xData = new double[8001];
		yData = new double[8001];
		
		for (int i = 0; i < 8001; i++) {
			xData[i] = (double) i / 100;
			yData[i] = (new Section3(xData[i], a)).rk4(1d / n, n)[n][0];
		}
		
		d = new graphs.XYDataset("Q8", "p", "value");
		d.addSeries(xData, yData, 8001, "y(1)");
		d.addSeries(new double[] {0, 80}, new double[] {0, 0}, 2, "0");
		d.setRange('x', 0, 80);
		d.plotLine(false);
	//	d.display();
		d.save("F8.1.jpg");
				
	}
	
}
