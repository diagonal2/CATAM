package graphs;

public class XYData {
	
	public double[] x, y;
	int n;
	
	public XYData(double[][] data, int number) {
		
		n = number;
		
		x = new double[n];
		for (int i = 0; i < number; i++) x[i] = data[i][0];
		
		y = new double[n];
		for (int i = 0; i < number; i++) y[i] = data[i][1];
		
		
	}
	
	public XYData(double[] xData, double[] yData, int number) {
		
		n = number;
		
		x = new double[number];
		System.arraycopy(xData, 0, x, 0, number);
		
		y = new double[number];
		System.arraycopy(yData, 0, y, 0, number);
		
	}
	
	public XYData(double[] xData, double[] yData, int startPos, int number) {
		
		n = number;
		
		x = new double[number];
		System.arraycopy(xData, startPos, x, 0, number);
		
		y = new double[number];
		System.arraycopy(yData, startPos, y, 0, number);
		
	}
	
	private double[] linearFit(double[] x, double[] y) {
		
		double sumX = 0, sumX2 = 0, sumXY = 0, sumY = 0;
		for (int i = 0; i < n; i++) {
			sumX += x[i];
			sumX2 += x[i] * x[i];
			sumXY += x[i] * y[i];
			sumY += y[i];
		}
		return new double[] {(sumY*sumX2 - sumX*sumXY) / (n*sumX2 - sumX*sumX), (n*sumXY - sumX*sumY) / (n*sumX2 - sumX*sumX)};
		
	}
	
	public double[] linearFit() {
		
		return linearFit(x, y);
		
	}
	
	private double[] log(double[] a) {
		
		double[] b = new double[n];
		for (int i = 0; i < n; i++) b[i] = Math.log(a[i]);
		return b;
		
	}
	
	public double[] exponentialFit1() {
		
		double[] b = linearFit(x, log(y));
		return new double[] {Math.exp(b[0]), b[1]};
		
	}
	
	public double[] powerFit1() {
		
		double[] b = linearFit(log(x), log(y));
		return new double[] {Math.exp(b[0]), b[1]};
		
	}
			
}
