package project;

import java.util.ArrayList;

public class Polynomial {
	
	protected ArrayList<Long> a = new ArrayList<Long>();
	private long mod;
	
	public Polynomial(long mod, long ... a) {
		for (int k = 0; k < a.length; k++) {
			this.a.add(((a[k] % mod) + mod) % mod);
		}
		this.a = removeTrailingZeros(this.a);
		this.mod = mod;
	}
	
	public Polynomial(long mod, ArrayList<Long> a) {
		for (int k = 0; k < a.size(); k++) {
			this.a.add(((a.get(k) % mod) + mod) % mod);
		}
		this.a = removeTrailingZeros(this.a);
		this.mod = mod;
	}
	
	private ArrayList<Long> removeTrailingZeros(ArrayList<Long> v) {
		int k = v.size() - 1;
		while (k >= 0 && v.get(k) == 0) {
			v.remove(k);
			k--;
		}
		return v;
	}
	
	public long getCoeff(int k) {
		if (k >= 0 && k <= this.degree()) return a.get(k);
		else return 0;
	}
	
	public int degree() {
		return a.size() - 1;
	}
	
	public long getMod() {
		return mod;
	}
	
	@Override
	public Polynomial clone() {
		return new Polynomial(mod, a);
	}
	
	public Polynomial plus(Polynomial g) throws IncompatibleModulusException {
		if (this.mod != g.mod) throw new IncompatibleModulusException();
		else {
			int n = Math.max(this.degree(), g.degree());
			ArrayList<Long> b = new ArrayList<Long>(n + 1);
			for (int k = 0; k <= n; k++) b.add(this.getCoeff(k) + g.getCoeff(k));
			return new Polynomial(mod, b);
		}
	}
	
	public Polynomial plus(long c) {
		@SuppressWarnings("unchecked")
		ArrayList<Long> b = (ArrayList<Long>) a.clone();
		if (this.degree() == -1) b.add(c % mod);
		else b.set(0, b.get(0) + (c % mod));
		return new Polynomial(mod, b);
	}
	
	public Polynomial negative() {
		ArrayList<Long> b = new ArrayList<Long>(degree() + 1);
		for (int k = 0; k <= degree(); k++) b.add(-getCoeff(k));
		return new Polynomial(mod, b);
	}
	
	public Polynomial minus(Polynomial g) throws IncompatibleModulusException {
		if (this.mod != g.mod) throw new IncompatibleModulusException();
		else {
			int n = Math.max(this.degree(), g.degree());
			ArrayList<Long> b = new ArrayList<Long>(n + 1);
			for (int k = 0; k <= n; k++) b.add(this.getCoeff(k) - g.getCoeff(k));
			return new Polynomial(mod, b);
		}
	}
	
	public Polynomial times(Polynomial g) throws IncompatibleModulusException {
		if (this.mod != g.mod) throw new IncompatibleModulusException();
		else {
			int n = this.degree() + g.degree();
			ArrayList<Long> b = new ArrayList<Long>(n + 1);
			if (this.degree() >= g.degree())
				for (int k = 0; k <= n; k++) {
					long tmp = 0;
					for (int j = Math.max(0, k - g.degree()); j <= Math.min(k, this.degree()); j++)
						tmp += (this.getCoeff(j) * g.getCoeff(k - j)) % mod;
					b.add(tmp);
				}
			else for (int k = 0; k <= n; k++) {
					long tmp = 0;
					for (int j = Math.max(0, k - this.degree()); j <= Math.min(k, g.degree()); j++)
						tmp += (this.getCoeff(k - j) * g.getCoeff(j)) % mod;
					b.add(tmp);
				}
			return new Polynomial(mod, b);
		}
	}
	
	public Polynomial times(long c) {
		@SuppressWarnings("unchecked")
		ArrayList<Long> b = (ArrayList<Long>) a.clone();
		for (int k = 0; k <= this.degree(); k++) b.set(k, b.get(k) * (c % mod));
		return new Polynomial(mod, b);
	}
	
	public Polynomial timesX(int toPower) {
		if (toPower < 0) return null;
		else if (toPower == 0) return this.clone();
		else {
			@SuppressWarnings("unchecked")
			ArrayList<Long> b = (ArrayList<Long>) a.clone();
			for (int i = 0; i < toPower; i++) b.add(0, 0L);
			return new Polynomial(mod, b);
		}
	}
	
	public Polynomial quotient(Polynomial g) throws IncompatibleModulusException {
		if (this.mod != g.mod) throw new IncompatibleModulusException();
		else {
			Polynomial r = this.clone();
			Polynomial q = Polynomial.term(mod, 0, 0);
			while (r.degree() >= g.degree()) {
				long rn = r.getCoeff(r.degree());
				long gm = g.getCoeff(g.degree());
				long[] gcd = integers.Tools.extendedGcd(gm, mod);
				if (rn % gcd[0] != 0) return null;
				Polynomial t = Polynomial.term(mod, gcd[1] * rn / gcd[0], r.degree() - g.degree());
				r = r.minus(g.times(t));
				q = q.plus(t);
			}
			return q;
		}
	}
	
	public Polynomial remainder(Polynomial g) throws IncompatibleModulusException {
		if (this.mod != g.mod) throw new IncompatibleModulusException();
		else {
			Polynomial r = this.clone();
			while (r.degree() >= g.degree()) {
				long rn = r.getCoeff(r.degree());
				long gm = g.getCoeff(g.degree());
				long[] gcd = integers.Tools.extendedGcd(gm, mod);
				if (rn % gcd[0] != 0) return null;
				r = r.minus(g.times(gcd[1] * rn / gcd[0]).timesX(r.degree() - g.degree()));
			}
			return r;
		}
	}
	
	public Polynomial compose(Polynomial g) throws IncompatibleModulusException {
		if (this.mod != g.mod) throw new IncompatibleModulusException();
		else {
			int n = this.degree();
			Polynomial h = Polynomial.term(mod, this.getCoeff(n), 0);
			for (int k = n - 1; k >= 0; k--) h = h.times(g).plus(this.getCoeff(k));
			return h;
		}
	}
	
	@Override
	public String toString() {
		if (a.size() == 0) return "0";
		else if (a.size() == 1) return getCoeff(0) + "";
		else if (a.size() == 2) return getCoeff(0) + " + " + getCoeff(1) + "x";
		else {
			String s = getCoeff(0) + " + " + getCoeff(1) + "x";
			for (int k = 2; k <= degree(); k++) s += " + " + getCoeff(k) + "x^" + k;
			return s;
		}
	}
	
	public static Polynomial term(long mod, long coeff, int power) {
		ArrayList<Long> a = new ArrayList<Long>(power + 1);
		for (int k = 0; k < power; k++) a.add(0L);
		a.add(coeff % mod);
		return new Polynomial(mod, a);
	}
	
	public static Polynomial gcd(Polynomial f, Polynomial g) throws IncompatibleModulusException {
		if (f.mod != g.mod) throw new IncompatibleModulusException();
		else {
			if (f.degree() < g.degree()) {
				Polynomial tmp = f;
				f = g;
				g = tmp;
			}
			if (g.degree() == -1) {
				long[] gcd = integers.Tools.extendedGcd(f.getCoeff(f.degree()), f.mod);
				return f.times(gcd[1]);
			}
			f = f.remainder(g);
			if (f.degree() == -1) {
				long[] gcd = integers.Tools.extendedGcd(g.getCoeff(g.degree()), g.mod);
				return g.times(gcd[1]);
			}
			else return gcd(g, f);
		}
	}
	
}
