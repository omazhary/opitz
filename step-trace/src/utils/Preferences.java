package utils;

public class Preferences {
	
	public Integer[] weightVector;
	
	public Preferences() {
		this.weightVector = new Integer[]{1,1,1,1,1,1,1,1,1};
	}
	
	/**
	 * Getter for the weight vector.
	 * @return An Integer[] containing the weights as set in the preferences frame.
	 */
	public Integer[] getWeightVector() {
		return this.weightVector;
	}
	
	/**
	 * Setter for the weight vector.
	 * @param weightVector The new set of weights to be saved.
	 */
	public void setWeightVector(Integer[] weightVector) {
		this.weightVector = weightVector;
	}
	
	/**
	 * Gets a String representation of the weight vector.
	 * @return A String containing a representation of the weight vector.
	 */
	public String getWeightVectorString() {
		String result = "{ ";
		for (int i = 0; i < this.weightVector.length; i++) {
			result += this.weightVector[i].toString();
			if ((i + 1) < this.weightVector.length) {
				result += ", ";
			}
		}
		result += " }";
		return result;
	}

}
