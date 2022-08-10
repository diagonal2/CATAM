package project;

public class IncompatibleModulusException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public IncompatibleModulusException() {
		super("Two polynomials need to be over the same field.");
	}
	
}
