package entities;

public enum IntElements {
	
	NO_HOLE_BREAKTHROUGH("No hole, no breakthrough"),
	NONE("No shape elements"),
	THREAD("Has a thread"),
	FUNCTIONAL_GROOVE("Has a functional groove"),
	FUNCTION_CONE("Has a functional cone"),
	OPERATING_THREAD("Has an operating thread"),
	MISC("Miscellaneous internal form element");
	
	private String value;
	
	IntElements(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
}
