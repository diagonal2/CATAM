package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
// import java.util.Scanner;

public class Q5 {
	
	private int n;
	private HashMap<Integer, HashSet<Integer>> adjList;
	
	private HashSet<Integer> U;
	private HashMap<Integer, Integer> M;
	private HashMap<Integer, Integer> MInv;
	
	private boolean matchExists;
	
	public Q5(int n, int[][] adj) {
		
		this.n = n;
		matrixToList(adj);
		U = new HashSet<Integer>();
		HashSet<Integer> V = new HashSet<Integer>();
		M = new HashMap<Integer, Integer>();
		MInv = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < n; i++) {
			
			if (U.contains(i)) continue;
			
			U.add(i);
			V = new HashSet<Integer>();
			ArrayList<ArrayList<Integer>> altPaths = new ArrayList<ArrayList<Integer>>();
			
			HashSet<Integer> UTmp = new HashSet<Integer>();
			UTmp.add(i);
			
			for (int m = 0; ; m++) {
				
				HashSet<Integer> VTmp = new HashSet<Integer>();
				HashMap<Integer, HashSet<Integer>> reducedAdjList = new HashMap<Integer, HashSet<Integer>>();
				for (int u : UTmp) {
					HashSet<Integer> uAdjs = new HashSet<Integer>(adjList.get(u));
					uAdjs.removeAll(V);
					reducedAdjList.put(u, uAdjs);
					VTmp.addAll(uAdjs);
				}
				
				if (VTmp.isEmpty()) {
					matchExists = false;
					return;
				}
				
				boolean matchFound = false;
				ArrayList<ArrayList<Integer>> newAltPaths = new ArrayList<ArrayList<Integer>>();
				if (m == 0) {
					for (int v : reducedAdjList.get(i)) {
						ArrayList<Integer> altPath = new ArrayList<Integer>();
						altPath.add(i);
						altPath.add(v);
						newAltPaths.add(altPath);
						if (!MInv.keySet().contains(v)) {
							M.put(i, v);
							MInv.put(v, i);
							matchFound = true;
							break;
						}
					}
				} else {
					for (int p = 0; p < altPaths.size(); p++) {
						int u = MInv.get(altPaths.get(p).get(altPaths.get(p).size() - 1));
						HashSet<Integer> vUsed = new HashSet<Integer>();
						for (int v : reducedAdjList.get(u)) {
							if (vUsed.contains(v)) continue;
							vUsed.add(v);
							ArrayList<Integer> altPath = new ArrayList<Integer>(altPaths.get(p));
							altPath.add(u);
							altPath.add(v);
							newAltPaths.add(altPath);
							if (!MInv.keySet().contains(v)) {
								M.put(i, altPath.get(1));
								for (int j = 2; j < altPath.size() - 1; j += 2) M.replace(altPath.get(j), altPath.get(j + 1));
								for (int j = 1; j < altPath.size() - 2; j += 2) MInv.replace(altPath.get(j), altPath.get(j - 1));
								MInv.put(v, u);
								matchFound = true;
								break;
							}
						}
						if (matchFound) break;
					}
				}
				
				U.addAll(UTmp);
				UTmp = new HashSet<Integer>();
				for (int v : VTmp) UTmp.add(MInv.get(v));
				V.addAll(VTmp);
				altPaths = newAltPaths;
				
				if (matchFound) break;
				
			}
			
		}
		
		matchExists = true;
		
	}
	
	private void matrixToList(int[][] adj) {
		
		adjList = new HashMap<Integer, HashSet<Integer>>();
		
		for (int i = 0; i < n; i++) {
			HashSet<Integer> tmp = new HashSet<Integer>();
			for (int j = n; j < 2*n; j++)
				if (adj[i][j] != 0) tmp.add(j);
			adjList.put(i, tmp);
		}
		
	}
	
	public boolean matchExists() {
		return matchExists;
	}
	
	public HashMap<Integer, Integer> getMatching() {
		
		if (matchExists) return new HashMap<Integer, Integer>(M);
		else return null;
		
	}
	
	public HashSet<Integer> getBlockingSet() {
		
		if (matchExists) return null;
		else return new HashSet<Integer>(U);
		
	}
	
	public HashMap<Integer, HashSet<Integer>> getAdjList() {
		
		return new HashMap<Integer, HashSet<Integer>>(adjList);
		
	}
	
	public static void main(String[] args) {
		
		Random r = new Random();
		
		int n = 40;
		double[] ps = new double[14];
		for (int i = 0; i < 7; i++) ps[i] = 0.06d * (i + 1);
		for (int i = 7; i < 14; i++) ps[i] = (0.1d + 0.3d * (i - 7)) * Math.log(40) / 40;
		
		for (double p : ps) {
			
			System.out.println("p = " + p);
			
			for (int N = 0; N < 20; N++) {
				
				int[][] adj = new int[2*n][2*n];
				for (int i = 0; i < 2*n; i++) {
					adj[i][i] = 0;
					for (int j = i + 1; j < 2*n; j++) {
						if (r.nextDouble() <= p) {
							adj[i][j] = 1;
							adj[j][i] = 1;
						} else {
							adj[i][j] = 0;
							adj[j][i] = 0;
						}
					}
				}
				
				Q5 G = new Q5(n, adj);
				System.out.printf("%-5s%-9s%-7s\n", N, G.matchExists(), G.matchExists() ? "N/A" : G.getBlockingSet().size());
				
			}
			
			System.out.println();
			
		}
		
	}
	
}
