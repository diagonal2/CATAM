package project;

public class NotPositiveFormException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotPositiveFormException() {
		super("Only positive-definite forms are handled by this class.");
	}

}
