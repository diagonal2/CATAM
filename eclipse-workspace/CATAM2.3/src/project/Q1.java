package project;

import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Complex;

public class Q1 {
	
	public static void main(String[] args) throws java.io.IOException {
		
		Variable<Complex> varZ = new Variable.Local<Complex>("z");
		Polynomial<Complex> z = Polynomial.valueOf(Complex.ONE, varZ);
		Polynomial<Complex> p = z.pow(3).plus(z.pow(2)).plus(z.times(Complex.valueOf(5, -4))).plus(Complex.valueOf(1, -8));
		
		graphs.XYDataset d = new graphs.XYDataset("Modulus of closest point", "r", "|z|");
		for (int i = 0; i < 35; i++) {
			double r = (double) i / 10;
			Complex w = Section2.main(p, r, "");
			d.addSeries(r, w.magnitude(), "data1");
		}
		d.plotLine(false);
		d.display();
				
	}
	
}
