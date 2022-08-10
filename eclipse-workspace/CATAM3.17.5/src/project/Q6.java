package project;

import java.util.Random;

public class Q6 {
	
	public static void main(String[] args) {
		
		int n = 20;
		
		for (int N = 0; N < 10; N++) {				// ten samples
			
			int[][] indices = new int[n*n][];
			int k = 0;
			for (int i = 0; i < n; i++)
				for (int j = n; j < 2*n; j++) {
					indices[k] = new int[] { i, j };
					k++;
				}
			
			Random r = new Random();
			for (int z = 0; z < 10000; z++) {
				int tmp1 = r.nextInt(n*n);
				int tmp2 = r.nextInt(n*n);
				int[] dummy = indices[tmp1];
				indices[tmp1] = indices[tmp2];
				indices[tmp2] = dummy;				// edges randomly ordered
			}
			
			System.out.println(N);
			int[][] adj = new int[2*n][2*n];
			for (int i = 0; i < 2*n; i++)
				for (int j = 0; j < 2*n; j++) adj[i][j] = 0;
			for (k = 0; k < n*n; k++) {
				adj[indices[k][0]][indices[k][1]] = 1;
				adj[indices[k][1]][indices[k][0]] = 1;			// adding edges one by one in order
				Q5 G = new Q5(n, adj);
				if ((k + 1) % 4 == 0) System.out.printf("%-5s%-5s\n", k + 1, G.matchExists() ? "true" : G.getBlockingSet().size());
			}
			System.out.println();
			
		}
		
	}
	
}
