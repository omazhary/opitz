package entities;

public enum HolesTeeth {
	
	NONE_1("No auxiliary hole(s)", 0),
	NONE_2("No auxiliary hole(s), gear teeth, and forming", 0),
	HOLES_AXIAL_UNRELATED("Axial hole(s) not related by a drilling pattern", 1),
	HOLES_AXIAL_RELATED("Axial hole(s) related by a drilling pattern", 2),
	HOLES_RADIAL_UNRELATED("Radial hole(s) not related by a drilling pattern", 3),
	HOLES_AXIAL_RADIAL_UNRELATED("Holes axial and/or radial and/or in other directions, not related by a drilling pattern", 4),
	HOLES_AXIAL_RADIAL_RELATED("Holes axial and/or radial and/or in other directions, related by a drilling pattern", 5),
	TEETH_SPUR("Spur gear teeth", 6),
	TEETH_BEVEL("Bevel gear teeth", 7),
	TEETH_MISC("Other gear teeth", 8),
	MISC("Miscellaneous hole and/or teeth features", 9);
	
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
