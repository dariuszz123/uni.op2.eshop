package base;

@SuppressWarnings("serial")
public class EmptyValueException extends Exception {
	
	private String valueName;
	
	public EmptyValueException(String valueName) {
		this.valueName = valueName;
	}
	
	@Override
	public String getMessage() {
		return "Value '" + valueName + "' cannot be empty!";
	}

	@Override
	public String toString() {
		return this.getMessage();
	}
	
}
