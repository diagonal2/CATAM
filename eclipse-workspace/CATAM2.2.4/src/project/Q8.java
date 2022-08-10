package project;

public class Q8 {
	
	static double[][] getData(Q2 f, int[] N, int L) {
		
		double[][] data = new double[L + 1][];
		int T = 10;
			
		for (int l = 0; l <= L; l++) {
			data[l] = new double[N[l]];
			double h_l = 0.1 / (1 << l);
			for (int i = 0; i < N[l]; i++) {
				double X = 2 * Math.random() - 1;
				if (l == 0) data[l][i] = f.gradDesc(X, h_l, T / h_l);
				else data[l][i] = f.gradDesc(X, h_l, T / h_l) - f.gradDesc(X, 2 * h_l, T / 2 / h_l);
			}
		}
		
		return data;
		
	}
	
	static double MLMC(double[][] data) {
		
		double mu = 0;
		for (int l = 0; l < data.length; l++)
			mu += probability.Tools.E(data[l]);
		
		return mu;
		
	}
	
	public static void main(String[] args) {
		
		int[] N1 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		int[] N2 = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
		System.out.printf("%-7s%-24s%-24s%-24s\n", "k", "\u03b8", "N_l = 5", "N_l = 2^(L-l)");
		
		for (int k = 1; k <= 64; k++) {
			
			double t = k * Math.PI / 128;
			Q2 f = new Q2(t);
			double[][] data1 = getData(f, N1, 10);
			double[][] data2 = getData(f, N2, 10);
			
			double mu1 = MLMC(data1);
			double mu2 = MLMC(data2);
			System.out.printf("%-7s%-24s%-24s%-24s\n", k, t, mu1, mu2);
			
		}
		
	}
	
}
