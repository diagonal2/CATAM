package project;

class Section2 {
	
	static double f(double x, double y) {
		
		return -8 * y + 6 * Math.exp(-2 * x);
		
	}
	
	static double y_e(double x) {
		
		return Math.exp(-2 * x) - Math.exp(-8 * x);
		
	}
	
	static double[] euler(double h, int n) {
		
		double Y[] = new double[n+1];
		Y[0] = 0;

		for (int i = 0; i < n; i++)
			Y[i+1] = Y[i] + h * f(i*h, Y[i]);
		
		return Y;
		
	}
	
	static double[] ab2(double h, int n) {		
		
		double Y[] = new double[n+1];
		Y[0] = 0;

		Y[1] = Y[0] + h * f(0, Y[0]);

		for (int i = 1; i < n; i++)
			Y[i+1] = Y[i] + h * (1.5d * f(i*h, Y[i]) - 0.5d * f((i-1)*h, Y[i-1]));
		
		return Y;
		
	}
	
	static double[] rk4(double h, int n) {		
		
		double Y[] = new double[n+1];
		Y[0] = 0;

		for (int i = 0; i < n; i++) {
			double k1 = f(i*h, Y[i]);
			double k2 = f(i*h + h/2, Y[i] + h/2*k1);
			double k3 = f(i*h + h/2, Y[i] + h/2*k2);
			double k4 = f(i*h + h, Y[i] + h*k3);
			Y[i+1] = Y[i] + h / 6 * (k1 + 2*k2 + 2*k3 + k4);
		}
		
		return Y;
		
	}
		
}
