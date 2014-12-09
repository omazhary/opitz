package entities;

public enum ExtElementsAdv {
	// Digit #1 < 3
	NONE("Smooth, no shape elements", 0),
	SMOOTH("Smooth", 0),
	STEPPED_ONE_SIDE_SCREW_THREADS("Has screw threads", 2),
	STEPPED_ONE_SIDE_FUNCTIONAL_GROOVE("Has functional groove", 3),
	STEPPED_BOTH_SIDES_SCREW_THREADS("Has screw threads", 5),
	STEPPED_BOTH_SIDES_FUNCTIONAL_GROOVE("Has functional groove", 6),
	// Digit #1 < 5
	// 		One Axis - No Segments
	HEX_BAR("Hexagonal bar", 0),
	SQUARE_POLY("Square or other regular polygonal section", 1),
	SYM_CROSS("Symmetrical cross-section producting no unbalance", 2),
	CROSS_MISC("Cross-sections other than the previous options", 3),
	//		More than one axis
	ROT_CURVED("Rotational components with curved axis", 6),
	ROT_PARALLEL("Rotational components with two or more parallel axes", 7),
	ROT_INTERSECT("Rotational components with intersecting axes", 8),
	// Digit #1 < 7:
	PLANE_RECT("Rectangular", 0),
	PLANE_RECT_ONE_DEV("Rectangular with one deviation (right angle or triangular)", 1),
	PLANE_RECT_ANG_DEV("Rectangular with angular deviations", 2),
	PLANE_RECT_CIR_DEV("Rectangular with circular deviation", 3),
	PLANE_RECT_MISC("Any flat shape other than the previous options", 4),
	// Digit #1 < 8
		//		Shape axis - straight
	SAS_UNI_RECT("Rectangular", 0),
	SAS_UNI_RECT_ONE_DEV("Rectangular with one deviation (right angle or triangular)", 1),
	SAS_UNI_RECT_MISC("Any flat shape other than the previous options", 2),
	SAS_VAR_RECT("Rectangular", 3),
	SAS_VAR_RECT_ONE_DEV("Rectangular with one deviation (right angle or triangular)", 4),
	SAS_VAR_RECT_MISC("Any flat shape other than the previous options", 5),
	//		Shape axis - curved
	RECT_ANG_OTHER("Rectangular, angular and other cross-sections", 6),
	FORMED("Formed component", 7),
	FORMED_DEV("Formed component with deviations in the main axis", 8),
	SAC_MISC("Others", 9),
	// Digit #1 < 9
	// 		Block and block-like components
	BLOCK_RECT_PRISM("Rectangular prism", 0),
	BLOCK_RECT_DEV("Rectangular with deviations (right angle or triangular)", 1),
	BLOCK_COMP_MULT("Compounded of rectangular prisms", 2),
	BLOCK_COMP_BORE("Components with a mounting or locating surface and principal bore", 3),
	BLOCK_COMP_BORE_DIV("Components with a mounting or locating surface, principal bore with dividing surface", 4),
	BLOCK_COMP_MISC("Components other than the previous options", 5),
	// 		Box and box-like components
	BOX_NO_SPLIT_APPROX("Approximate or compounded of rectangular prisms", 6),
	BOX_NO_SPLIT_MISC("Components other than the previous option", 7),
	BOX_SPLIT_APPROX("Approximate or compounded of rectangular prisms", 8),
	BOX_SPLIT_MISC("Components other than the previous option", 9),
	
	STEPPED_ONE_SIDE_NONE("No advanced shape elements", 1),
	STEPPED_BOTH_SIDES_NONE("No advanced shape elements", 4);
	
	private String value;
	private int code;
	
	ExtElementsAdv(String value, int code) {
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
