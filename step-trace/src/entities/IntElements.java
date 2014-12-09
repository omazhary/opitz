package entities;

public enum IntElements {
	
	// Digit #1 < 3
	INT_NO_HOLE_BREAKTHROUGH("No hole, no breakthrough", 0),
	INT_SMOOTH_STEPPED_ONE_SIDE_NONE("No shape elements", 1),
	INT_SMOOTH_STEPPED_ONE_SIDE_THREAD("Has a thread", 2),
	INT_SMOOTH_STEPPED_ONE_SIDE_FUNCTIONAL_GROOVE("Has a functional groove", 3),
	INT_STEPPED_BOTH_SIDES_NONE("No shape elements", 4),
	INT_STEPPED_BOTH_SIDES_THREAD("Has a thread", 5),
	INT_STEPPED_BOTH_SIDES_FUNCTIONAL_GROOVE("Has a functional groove", 6),
	INT_FUNCTION_CONE("Has a functional taper", 7),
	INT_OPERATING_THREAD("Has an operating thread", 8),
	INT_MISC("Others ( > 10 functional diameters)", 9),
	
	// Digit #1 < 5
	ROT_NONE("No rotational machining", 0),
	ROT_EXT_MACHINED("External shape - Machined", 1),
	ROT_EXT_SCREWTHREAD("External shape - With screwthread(s)", 2),
	ROT_INT_SMOOTH("Internal shape - Smooth", 3),
	ROT_INT_STEPPED("Internal shape - Stepped towards one or both ends (multiple increases)", 4),
	ROT_INT_SCREWTHREAD("Internal shape - With screwthreads", 5),
	ROT_EXT_INT_MACHINED("External and internal shape - Machined", 6),
	ROT_EXT_INT_SCREWTHREAD("External and internal shape - Screwthread(s)", 7),
	ROT_EXT("External shape elements", 8),
	ROT_MISC("Other shape elements", 9),
	
	// Digit #1 < 9
	PBRSM_NONE("No rotational machining or bore(s)", 0),
	PBRSM_ONE_BORE_SMOOTH("One principal bore, smooth", 1),
	PBRSM_ONE_BORE_STEPPED("One principal bore, stepped to one or both sides", 2),
	PBRSM_ONE_BORE_SHAPE_ELEMENTS("One principal bore, with shape elements", 3),
	PBRSM_TWO_BORES_PARALLEL("Two principal bores, parallel", 4),
	PBRSM_SEVERAL_BORES_PARALLEL("Several principal bores, parallel", 5),
	PBRSM_SEVERAL_BORES_OTHER("Several principal bores, other than parallel", 6),
	PBRSM_MACHINED_ANNULAR("Machined annular surfaces, annular grooves", 7),
	PBRSM_SEVEN_BORES_OR_MORE("7 + principal bore(s)", 8),
	PBRSM_MISC("Others", 9);
	
	private String value;
	private int code;
	
	IntElements(String value, int code) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	public int getCode() {
		return this.code;
	}
	
}
