package probability;

public class Tools {
	
	public static double E(double[] data) {
		
		double sum = 0;
		for (int i = 0; i < data.length; i++)
			sum += data[i];
		return sum / data.length;
		
	}
	
	public static double Var(double[] data) {
		
		double sum = 0, E = E(data);
		for (int i = 0; i < data.length; i++)
			sum += Math.pow(data[i] - E, 2);
		return sum / data.length;
		
	}
	
	public static double sVar(double[] data) {
		
		double sum = 0, E = E(data);
		for (int i = 0; i < data.length; i++)
			sum += Math.pow(data[i] - E, 2);
		return sum / (data.length - 1);
		
	}
	
}
