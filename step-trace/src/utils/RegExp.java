package utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
	
	private static Pattern parantheses = Pattern.compile("\\((.+)\\)");
	private static Pattern doubleParantheses = Pattern.compile("\\(.+\\((.+)\\).*\\)");
	private static Pattern twoParameters = Pattern.compile("\\w+\\s*\\((.+),(.+)\\)");
	private static Pattern threeParameters = Pattern.compile("\\w+\\s*\\((.+),(.+),(.+)\\)");
	private static Pattern fourParameters = Pattern.compile("\\w+\\s*\\((.+),(.+),(.+),(.+)\\)");
	private static Pattern fiveParameters = Pattern.compile("\\w+\\s*\\((.+),(.+),(.+),(.+),(.+)\\)");
	
	/**
         * Filters out the string between parentheses.
         * @param s The string to be filtered.
         * @return The string between the parentheses or null.
         */
        public static String getValueBetweenParentheses(String s) {
		Matcher m = parantheses.matcher(s);
		while (m.find()) {
			return m.group(1).trim();
		}
		return null;
	}
	
	// CLOSED_SHELL ( 'NONE', ( #22, #19, #24, #23, #21, #20 ) ) ->  #22, #19, #24, #23, #21, #20
        /**
         * Filters out the string between double-parentheses.
         * @param s The string to be filtered.
         * @return The string(s) between double parentheses or null.
         */
	public static String getValueBetweenDoubleParentheses(String s) {
		Matcher m = doubleParantheses.matcher(s);
		while (m.find()) {
			return m.group(1).trim();
		}
		return null;
	}
	
	// Example: ADVANCED_FACE ( 'NONE',  #2 , #130, .F. ), 2 ->  #2
	// without parantheses inside
        /**
         * Filters out specific parameters of an ADVANCED_FACE entity.
         * @param s The definition of the ADVANCED_FACE entity.
         * @param paramNum The number of the desired parameter.
         * @param totalParamsCount The total number of parameters.
         * @return A string containing the desired parameter or null.
         */
	public static String getParameter(String s, int paramNum, int totalParamsCount) {
		Matcher m = totalParamsCount == 2 ? twoParameters.matcher(s) : totalParamsCount == 3 ? threeParameters.matcher(s)
				: totalParamsCount == 4 ? fourParameters.matcher(s) : totalParamsCount == 5 ? fiveParameters.matcher(s) : null;
		while (m.find()) {
			return m.group(paramNum).trim();
		}
		return null;
	}


}
