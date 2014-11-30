package entities;

public enum InitForm {
	
	BAR_ROUND_BLACK("Round bar, black", 0),
	BAR_ROUND_BRIGHT_DRAWN("Round bar, bright drawn", 1),
	BAR_MISC("Bar - triangular, square, hexagonal, others", 2),
	TUBING("Tubing", 3),
	ANGLE("Angle, U-, T-, and similar sections", 4),
	SHEET("Sheet", 5),
	PLATE_SLABS("Plate and slabs", 6),
	CAST_FORGED("Cast or forged components", 7),
	WELDED("Welded assembly", 8),
	PRE_MACHINED("Pre-machined components", 9);
	
	private String value;
	private int digit;
	
	InitForm(String value, int digit) {
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
