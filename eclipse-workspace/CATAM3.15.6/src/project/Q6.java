package project;

public class Q6 {
	
	public static void main(String[] args) throws IncompatibleModulusException {
		
		Polynomial p = new Polynomial(109, 5, 5, 6, 1);
		Polynomial q = new Polynomial(109, 3, 6, 13, 1);
		System.out.println(Polynomial.gcd(p, q));
		
		p = new Polynomial(131, 4, 9, 2, 1);
		q = new Polynomial(131, 9, 7, 3, 1);
		System.out.println(Polynomial.gcd(p, q));
		
		p = new Polynomial(157, 12, 9, 3, 1);
		q = new Polynomial(157, 4, 12, 6, 1);
		System.out.println(Polynomial.gcd(p, q));
		
	}
	
}
