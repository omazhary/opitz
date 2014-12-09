package entities;

public enum ExtElements {
	SMOOTH("Smooth", -1),
	// Digit #1 < 3
	STEPPED_ONE_SIDE("Stepped on one side", -1),
	STEPPED_BOTH_SIDES("Stepped on both sides", -1),
	FUNCTION_CONE("Has a function taper", 7),
	OPERATING_THREAD("Has an operating thread", 8),
	// Digit #1 < 5
	ONE_AXIS_NO_SEGMENTS("Around one axis, no segments", -1),
	SEGMENTS_AFTER("Segments after rotational machining", 4),
	SEGMENTS_BEFORE("Segments before rotational machining", 5),
	MULITPLE_AXES("Around more than one axis", -1),
	// Digit #1 < 7
	PLANE("Plane", -1),
	FLAT_REC("Flat components, rectangular or right angled with small deviations due to casting, welding, or forming", 5),
	FLAT_ROUND("Flat components, round or of any shape other than those custom specified", 6),
	FLAT_ARCHED_REG("Flat components regularly arched or dished", 7),
	FLAT_ARCHED_IRR("Flat components irregularly arched or dished", 8),
	// Digit #1 < 8
	SHAPE_AXIS_STRAIGHT_UNIFORM("Shape axis is straight with a uniform cross-section", -1),
	SHAPE_AXIS_STRAIGHT_VARYING("Shape axis is straight with a varying cross-section", -1),
	SHAPE_AXIS_CURVED("Shape axis curved (bent)", -1),
	// Digit #1 < 9
	BLOCK("Block and block-like components", -1),
	BOX_NOT_SPLIT("Box and box-like components - Not split", -1),
	BOX_SPLIT("Box and box-like components - Split", -1),
	
	MISC("Miscellaneous external form element", 9);
	
	private String value;
	private int code;
	
	ExtElements(String value, int code) {
		this.value = value;
		this.code = code;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	public int getCode() {
		return this.code;
	}
}
