package project;

public class Q2 {
	
	double t;
	
	Q2(double theta) {
		t = theta;
	}
	
	double eval(double x) {
		return Math.pow(x * x - 3/4, 2) - x * Math.cos(t);
	}
	
	double derivative(double x) {
		return 4 * Math.pow(x, 3) - 3 * x - Math.cos(t);
	}
	
	double gradDesc(double ini, double step, double number) {
		double res = ini;
		for (int i = 0; i < number; i++)
			res -= step * derivative(res);
		return res;
	}
	
	public static void main(String[] args) {
		
		Q2 f = new Q2(Math.PI / 6);
		double h = 0.01;
		
		for (int k = -50; k < 51; k++) {
			double x0 = (double) k / 50;
			System.out.printf("%-8s%-24s\n", x0, f.gradDesc(x0, h, 1000));
		}
		
	}
	
}
