package project;

import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Complex;
import java.util.Scanner;

public class Section2 {
	
	static Complex main(Polynomial<Complex> p, double r, String file) throws java.io.IOException {
		
		Complex[] w = new Complex[10000];

		for (int t = 0; t < 10000; t++)
			w[t] = p.evaluate(Complex.valueOf(r*Math.cos(2*Math.PI*t/10000), r*Math.sin(2*Math.PI*t/10000)));
		
		int minIndex = 0;
		double minMod = w[0].magnitude();
		for (int t = 1; t < 10000; t++)
			if (w[t].magnitude() < minMod) {
				minIndex = t;
				minMod = w[t].magnitude();
			}
		
		if (!file.isEmpty()) {
			graphs.XYDataset d = new graphs.XYDataset("Curve","Re","Im");
			for (int t = 0; t < 10000; t++) {
				d.addSeries(w[t].getReal(), w[t].getImaginary(), "curve");
			}
			d.plotScatter(1);
			d.squareAxis();
			d.display(800, 800);
		//	d.save(file, 800, 800);
		}
		
		return w[minIndex];
		
	}
	
	public static void main(String[] args) throws java.io.IOException {
		
		Variable<Complex> varZ = new Variable.Local<Complex>("z");
		Polynomial<Complex> z = Polynomial.valueOf(Complex.ONE, varZ);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Degree: ");
		int deg = sc.nextInt();
		System.out.println();
		
		Polynomial<Complex> p = z.times(Complex.ZERO);
		for (int i = 0; i <= deg; i++) {
			System.out.print("Coefficient of z^" + i + ": ");
			String coeff = sc.next();
			if (i == 0) p = p.plus(Complex.valueOf(coeff));
			else p = p.plus(z.pow(i).times(Complex.valueOf(coeff)));
		}
		System.out.println();
		
		System.out.print("r: ");
		double r = sc.nextDouble();
		System.out.println();
		
		Complex w = Section2.main(p, r, "Section2.jpg");
		System.out.println("Coordinate = " + w.toString() + "\nMagnitude = " + w.magnitude());
		
		sc.close();
		
	}
	
}
