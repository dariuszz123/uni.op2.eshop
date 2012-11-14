package base;

@SuppressWarnings("serial")
public class EmptyCartException extends Exception {
	
	public EmptyCartException() {
		
	}
	
	@Override
	public String getMessage() {
		return "Cart cannot be empty!";
	}

	@Override
	public String toString() {
		return this.getMessage();
	}
	
}
