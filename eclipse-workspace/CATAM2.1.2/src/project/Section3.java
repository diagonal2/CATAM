package project;

class Section3 {
	
	double p, a;
	
	Section3(double pVal, double aVal) {
		
		p = pVal;
		a = aVal;
		
	}
	
	private double[] add(double[] vector1, double[] vector2) {
		
		int n = vector1.length;
		if (n != vector2.length) return null;
		else {
			double[] v = new double[n];
			for (int i = 0; i < n; i++) v[i] = vector1[i] + vector2[i];
			return v;
		}
				
	}
	
	private double[] multiply(double scalar, double[] vector) {
		
		int n = vector.length;
		double[] v = new double[n];
		for (int i = 0; i < n; i++) v[i] = vector[i] * scalar;
		return v;
		
	}
	
	double[] f(double x, double[] y) {
		
		return new double[] {y[1], -p * p * Math.pow(1 + x, -a) * y[0]};
		
	}
	
	double[][] rk4(double h, int n) {
		
		double Y[][] = new double[n+1][2];
		Y[0][0] = 0;
		Y[0][1] = 1;
		
		for (int i = 0; i < n; i++) {
			double[] k1 = f(i*h, Y[i]);
			double[] k2 = f(i*h + h/2, add(Y[i], multiply(h/2, k1)));
			double[] k3 = f(i*h + h/2, add(Y[i], multiply(h/2, k2)));
			double[] k4 = f(i*h + h, add(Y[i], multiply(h, k3)));
			Y[i+1] = add(Y[i], multiply(h/6, add(add(add(k1, multiply(2, k2)), multiply(2, k3)), k4)));
		}
		
		return Y;
		
	}
	
	static double y_e2(double x, double p) {
		
		double C = Math.sqrt(4*p*p - 1) / 2;
		return Math.sqrt(1+x) / C * Math.sin(Math.log(1+x) * C);
		
	}
	
}
