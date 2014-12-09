package entities;

public enum HolesTeeth {
	
	// Digit #1 < 3
	NONE_1("No auxiliary hole(s)", 0),
	HOLES_AXIAL_UNRELATED("Axial hole(s) not related by a drilling pattern", 1),
	HOLES_AXIAL_RELATED("Axial hole(s) related by a drilling pattern", 2),
	HOLES_RADIAL_UNRELATED("Radial hole(s) not related by a drilling pattern", 3),
	HOLES_AXIAL_RADIAL_UNRELATED("Holes axial and/or radial and/or in other directions, not related by a drilling pattern", 4),
	HOLES_AXIAL_RADIAL_RELATED("Holes axial and/or radial and/or in other directions, related by a drilling pattern", 5),
	TEETH_SPUR("Spur gear teeth", 6),
	TEETH_BEVEL("Bevel gear teeth", 7),
	TEETH_MISC("Other gear teeth", 8),
	// Digit #1 < 5
	ROT_5_NONE("No auxiliary hole(s), gear teeth, and forming", 0),
	ROT_5_AXIAL_HOLES_NOT_RELATED("Axial hole(s) not related by a drilling pattern", 1),
	ROT_5_OTHER_HOLES_NOT_RELATED("Axial hole(s), radial hole(s) or in other directions, and not related by a drilling pattern", 2),
	ROT_5_AXIAL_HOLES_RELATED("Axial hole(s) related by a drilling pattern", 3),
	ROT_5_OTHER_HOLES_RELATED("Axial hole(s), radial hole(s) or in other directions, and related by a drilling pattern", 4),
	ROT_5_FORMED_NO_AUX_HOLES("Formed, no auxilliary holes", 5),
	ROT_5_FORMED_WITH_AUX_HOLES("Formed, with auxiliary holes", 6),
	ROT_5_GEAR_TEETH_NO_HOLES("Gear teeth, no auxiliary holes", 7),
	ROT_5_GEAR_TEETH_WITH_HOLES("Gear teeth, with auxiliary holes", 8),
	// Digit #1 < 9	
	NOT_ROT_9_NONE("No auxiliary hole(s), gear teeth, and forming", 0),
	NOT_ROT_9_HOLES_ONE_DIR("Holes drilled in one direction only", 1),
	NOT_ROT_9_HOLES_MULT_DIR("Holes drilled in more than one direction", 2),
	NOT_ROT_9_HOLES_RELATED_ONE_DIR("Holes drilled in one direction only, and related by a drilling pattern", 3),
	NOT_ROT_9_HOLES_RELATED_MULT_DIR("Holes drilled in more than one direction, and related by a drilling pattern", 4),
	NOT_ROT_9_FORMED_NO_AUX_HOLES("Formed, no auxiliary holes", 5),
	NOT_ROT_9_FORMED_WITH_AUX_HOLES("Formed, with auxiliary holes", 6),
	NOT_ROT_9_GEAR_TEETH_NO_HOLES("Gear teeth, no auxiliary holes", 7),
	NOT_ROT_9_GEAR_TEETH_WITH_HOLES("Gear teeth, with auxiliary holes", 8),
	
	MISC("Others", 9);
	
	private String value;
	private int digit;
	
	HolesTeeth(String value, int digit) {
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
