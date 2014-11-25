package entities;

public enum ExtElements {
	SMOOTH("Smooth"),
	STEPPED_ONE_SIDE("Stepped on one side"),
	STEPPED_BOTH_SIDES("Stepped on both sides"),
	FUNCTION_CONE("Has a functional cone"),
	OPERATING_THREAD("Has an operating thread"),
	MISC("Miscellaneous external form element");
	
	private String value;
	
	ExtElements(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
