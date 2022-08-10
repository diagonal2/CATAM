package project;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Q13 {
	
	static void getA2A4() throws IOException {
		
		int T = 10;
		PrintWriter pw = new PrintWriter("T13.1.txt");
		pw.printf("%-7s%-24s%-24s%-24s\n", "k", "\u03b8", "A_2", "A_4");
		
		for (int k = 1; k < 65; k++) {
			
			double t = k * Math.PI / 128;
			Q2 f = new Q2(t);
			
			double[][] data = new double[11][];
			double[] X = new double[1 << 20];
			
			for (int i = 0; i < 1 << 20; i++)
				X[i] = 2 * Math.random() - 1;
			
			double[] tmp = new double[1 << 20];
			for (int i = 0; i < 1 << 20; i++)
				tmp[i] = 0;
			
			double A_2 = 0;
			double A_4 = 0;
			for (int j = 0; j < 10; j++) {
				double h = 0.1 / (1 << j);
				int N = 1 << (10 - j);
				data[j] = new double[N];
				for (int i = 0; i < N; i++) {
					data[j][i] = f.gradDesc(X[i], h, T / h) - tmp[i];
					tmp[i] += data[j][i];
				}
				double E = probability.Tools.E(data[j]);
				if (j == 0)
					for (int i = 0; i < N; i++) {
						double tmp2 = Math.pow(data[0][i] - E, 2) * N / (N - 1);
						if (tmp2 > A_2) A_2 = tmp2;
					}
				else
					for (int i = 0; i < N; i++) {
						double tmp2 = Math.pow(data[j][i] - E, 2) * N / (N - 1) / h / h / 4;
						if (tmp2 > A_4) A_4 = tmp2;
					}
			}
			
			pw.printf("%-7s%-24s%-24s%-24s\n", k, t, A_2, A_4);
			pw.flush();
			
		}
		
		pw.close();
		
	}
	
	public static void main(String[] args) throws IOException, FileNotFoundException{
		
		getA2A4();
		
		double[] A_2 = new double[64], A_4 = new double[64];
		Scanner sc = new Scanner(new FileReader("T13.1.txt"));
		sc.nextLine();
		graphs.XYDataset d = new graphs.XYDataset("Q13", "\u03b8", "p(\u03b8)");
		graphs.XYDataset e = new graphs.XYDataset("Q13", "\u03b8", "Variance");
		
	//	System.out.printf("%-7s%-24s%-24s%-24s%-24s\n", "k", "\u03b8", "p_1(\u03b8)", "p_2(\u03b8)", "Variance");
		
		for (int k = 1; k < 65; k++) {
			
			double t = k * Math.PI / 128;
			Q2 f = new Q2(t);
			
			sc.nextDouble();
			sc.nextDouble();
			A_2[k - 1] = sc.nextDouble();
			A_4[k - 1] = sc.nextDouble();
			
			ArrayList<Integer> N = new ArrayList<Integer>();
			for (int l = 0; l != -1; l++) {
				double tmp = Math.sqrt(A_2[k - 1] / 3) + 0.1 * Math.sqrt(2 * A_4[k - 1]) / (Math.sqrt(2) - 1);
				if (l == 0) tmp = (1 << 20) / 100 * Math.sqrt(A_2[k - 1] / 3) / tmp;
				else tmp = (1 << 20) / 1500 * Math.sqrt(A_4[k - 1]) * Math.pow(2, (-3 * l + 1) / 2) / tmp;
				int N_l = (int) tmp;
				if (N_l > 0) N.add(N_l);
				else l = -2;
			}
			
			double mu =  Q8.MLMC(Q8.getData(f, N.stream().mapToInt(i -> i).toArray(), N.size() - 1));
			double m1 = Math.cos(t / 3), m2 = Math.cos((2 * Math.PI + t) / 3);
			double p1 = (mu - m2) / (m1 - m2), p2 = (mu - m1) / (m2 - m1);
			d.addSeries(t, p1, "p_1(\u03b8)");
			d.addSeries(t, p2, "p_2(\u03b8)");
			
			double V = -(mu - m1) * (mu - m2);
			e.addSeries(t, V, "Variance");
			
		//	System.out.printf("%-7s%-24s%-24s%-24s%-24s\n", k, t, p1, p2, V);
			
		}
		
		sc.close();
		
		d.plotLine(false);
	//	d.display();
		d.save("F13.1.jpg");
		
		e.plotLine(false);
	//	e.display();
		e.save("F13.2.jpg");
		
	}
	
}
