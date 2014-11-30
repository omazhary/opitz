package entities;

public enum Material {

	CAST_IRON("Cast iron", 0),
	GRPH_MALL_CAST_IRON("Modular graphitic cast iron and malleable cast iron", 1),
	STEEL_LESS("Steel <= 26.5 tonf/in² - Not heat treated", 2),
	STEEL_MORE("Steel > 26.5 tonf/in² - Heat treatable low carbon and case hardening steel, not heat treated", 3),
	STEEL_MORE_LESS_HEAT("Previous two types of steels - Heat treated", 4),
	STEEL_ALLOY("Alloy steel - Not heat treated", 5),
	STEEL_ALLOY_HEAT("Alloy steel - Heat treated", 6),
	NON_FER_METAL("Non-ferrous metal", 7),
	LIGHT_ALLOY("Light alloy", 8),
	MISC("Other materials", 9);
	
	private String value;
	private int digit;
	
	Material(String value, int digit) {
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
