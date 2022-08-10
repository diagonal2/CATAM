package project;

class Coin {
	
	double p;
	
	byte toss() {
		if (Math.random() < p) return 1;
		else return 0;
	}
	
	Coin(double one) {
		p = one;
	}
	
}
