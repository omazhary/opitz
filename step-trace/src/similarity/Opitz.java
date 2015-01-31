package similarity;

public class Opitz implements GTCode {
	
	private String src;
	private Integer[] code;
	
	/**
	 * Initializes an instance of the Opitz code class.
	 */
	public Opitz() {
		this.src = "";
		this.code = new Integer[13];
	}
	
	/**
	 * Takes a string and attempts to return the Opitz code from the numbers contained in the string.
	 * @param src The string containing the Opitz code (without dashes, slashes, or any other delimiters).
	 */
	public Opitz(String src) {
		this.src = src;
		this.code = this.transformString(src);		
	}
	
	/**
	 * Extracts the Opitz code from a string which contains only the Opitz code (again, without any delimiters).
	 * @param src The string containing the Opitz code (without dashes, slashes, or any other delimiters).
	 * @return An Integer array that contains the separate digits of the Opitz code.
	 */
	private Integer[] transformString(String src) {
		src = src.replace("-", "");
		Integer[] result = new Integer[13];
		for (int i = 0; i < 13; i++){
			if (i > src.length() - 1) {
				result[i] = new Integer(0);
			} else {
				result[i] = Integer.parseInt(String.valueOf(this.src.charAt(i)));
			}
		}
		return result;
	}
	
	/**
	 * Gets the GT code as an Integer array.
	 * @return Integer[] that contains the representation of the code in numbers.
	 */
	public Integer[] getCode() {
		return this.code;
	}
	
	/**
	 * Returns the string representation of the Opitz code.
	 */
	@Override
	public String toString() {
		return this.src;
	}

}
