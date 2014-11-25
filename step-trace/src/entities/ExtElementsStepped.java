package entities;

public enum ExtElementsStepped {
	SMOOTH("Smooth"),
	SCREW_THREADS("Has screw threads"),
	FUNCTIONAL_GROOVE("Has functional groove");
	
	private String value;
	
	ExtElementsStepped(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
