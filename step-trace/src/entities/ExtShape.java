package entities;

public enum ExtShape {
	PURE_CIRCLE_OR_CYLINDER("General circle or cylinder"),
	CIRCLE_OR_CYLINDER_WITH_DEVIATIONS("Circle or cylinder with deviations"),
	FLAT_LONG_CUBIC("Flat, long, or cubic");
	
	private String value;
	
	ExtShape(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
