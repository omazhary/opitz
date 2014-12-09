package entities;

public enum PlaneSurfaceMachining {

	NONE("No surface machining", 0),
	MISC("Others", 9),
	// Digit #1 < 5
	CURVED_ONE_DIRECTION("Surface plane and/or curved in one direction, external", 1),
	CURVED_CIRCLE("External plane surface related by graduation around a circle", 2),
	EXT_GROOVE_SLOT("External groove and/or slot", 3),
	EXT_SPLINE("External spline (Polygon)", 4),
	EXT_SPLINE_SLOT("External plane surface and/or slot, external spline", 5),
	INT_PLANE_SLOT("Internal plane surface and/or slot", 6),
	INT_SPLINE("Internal spline (Polygon)", 7),
	INT_EXT_POLYGON_GROOVE_SLOT("Internal and external polygon, groove and/or slot", 8),
	// Digit #1 < 9
	FUN_CHAMFERS("Functional chamfers (e.g. welding prep.)", 1),
	ONE_PLANE_SURF("One plane surface", 2),
	STEPPED_PLANE_SURF("Stepped plane surfaces", 3),
	STEPPED_PLANE_SURF_ADV("Stepped plane surfaces at right angles, inclined and/or opposite", 4),
	GROOVE_SLOT("Groove and/or slot", 5),
	GROOVE_SLOT_PLANE_SURF_ADV("Groove and/or slot in addition to fifth option", 6),
	CURVED_SURF("Curved surface", 7),
	GUIDE_SURF("Guide surface", 8);
	
	private String value;
	private int digit;
	
	PlaneSurfaceMachining(String value, int digit) {
		this.value = value;
		this.digit = digit;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	public int getDigit() {
		return this.digit;
	}
	
}
