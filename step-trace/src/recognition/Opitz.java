package recognition;

public class Opitz {
	
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
		Integer[] result = new Integer[13];
		for (int i = 0; i < this.src.length(); i++){
			result[i] = Integer.parseInt(String.valueOf(this.src.charAt(i)));
		}
		return result;
	}

}
