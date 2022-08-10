package project;

public class Q4 {
	
	public static void main(String[] args) {
		
		Q2 f = new Q2(Math.PI / 4);
		int T = 10;
		System.out.printf("%-7s%-15s%-24s%-24s\n", "k", "h", "(\u03bc^h)_N", "(V^h)_N");
		
		for (int k = 0; k < 11; k++) {
			
			double h = 0.1 / (1 << k);
			int N = 1 << (20 - k);
			double[] data = new double[N];
			
			for (int i = 0; i < N; i++) {
				double X = 2 * Math.random() - 1;
				data[i] = f.gradDesc(X, h, T / h);
			}
			
			double E = probability.Tools.E(data);
			double V = probability.Tools.sVar(data);
			
			System.out.printf("%-7s%-15s%-24s%-24s\n", k, h, E, V);
			
		}
		
	}
	
}
