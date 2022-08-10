package project;

import java.io.IOException;

public class Q6 {
	
	public static void main(String[] args) throws IOException {
		
		Q3.fillData(1/4d);
		double[] yData = Q3.yData.clone();
		Q3.fillData(3/4d);
		
		graphs.XYDataset d = new graphs.XYDataset("Q6", "x", "F(x)");
		d.addSeries(Q3.xData, yData, 2049, "p = 1/4");
		d.addSeries(Q3.xData, Q3.yData, 2049, "p = 3/4");
		d.plotLine(false);
	//	d.display();
		d.save("F6.1.jpg");
		
		Q3.fillData(1/2d);
		
		d = new graphs.XYDataset("Q6", "x", "F(x)");
		d.addSeries(Q3.xData, Q3.yData, 2049, "p = 1/2");
		d.plotLine(false);
	//	d.display();
		d.save("F6.2.jpg");
		
	}
	
}
