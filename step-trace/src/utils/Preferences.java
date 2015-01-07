package utils;

public class Preferences {

	private double[] thresholds;
	private double[] defaults;
	private Integer[] weightVector;

	public Preferences() {
		this.weightVector = new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		this.thresholds = new double[] { 
				0.5, // Rotational deviation detection
				0.5, // Non-rotational deviation detection
				0.1, // Shape Axis Straightness
				0.9, // Internal Operating Thread
				0.9, // Hole Equidistance
				0.9, // Hole Distribution
				0.9, // Hole Orientation
				0.6, // Tooth Sensitivity
				0.9, // Tooth Parallelism
				0.5  // Plane Surface Machining
		};
		this.defaults = new double[] { 
				0.5, // Rotational deviation detection
				0.5, // Non-rotational deviation detection
				0.1, // Shape Axis Straightness
				0.9, // Internal Operating Thread
				0.9, // Hole Equidistance
				0.9, // Hole Distribution
				0.9, // Hole Orientation
				0.6, // Tooth Sensitivity
				0.9, // Tooth Parallelism
				0.5  // Plane Surface Machining
		};
	}

	/**
	 * Getter for the thresholds array.
	 * 
	 * @return A double array containing all relevant thresholds where 0:
	 *         rotational deviations, 1: non-rotational deviations, 2: shape
	 *         axis straightness, 3: internal operating threads, 4: hole
	 *         equidistance, 5: hole distribution, 6: hole orientation, 7: tooth
	 *         sensitivity, 8: tooth parallelism, 9: plane surface machining.
	 */
	public double[] getThresholds() {
		return this.thresholds;
	}
	
	/**
	 * Checks if a certain threshold is set at its default value or not.
	 * @param index The index of the desired threshold.
	 * @return TRUE if the threshold is equal to its default value, FALSE otherwise.
	 */
	public boolean checkDefault(int index) {
		return this.thresholds[index] == this.defaults[index];
	}
	
	/**
	 * Gets the display integer version (for sliders) of a certain threshold.
	 * 
	 * @return A int containing the desired threshold where indexes give 0:
	 *         rotational deviations, 1: non-rotational deviations, 2: shape
	 *         axis straightness, 3: internal operating threads, 4: hole
	 *         equidistance, 5: hole distribution, 6: hole orientation, 7: tooth
	 *         sensitivity, 8: tooth parallelism, 9: plane surface machining.
	 */
	public int getDisplayThresholdByIndex(int index) {
		return Integer.parseInt(Double.toString(this.thresholds[index] * 100).split("\\.")[0]);
	}
	
	/**
	 * Sets the value of a certain threshold.
	 * @param index The position of the required threshold.
	 * @param value The new value of the required threshold.
	 */
	public void setThreshold(int index, double value) {
		System.out.println("Set " + index + " to " + value);
		this.thresholds[index] = value / 100;
	}

	/**
	 * Getter for the weight vector.
	 * 
	 * @return An Integer[] containing the weights as set in the preferences
	 *         frame.
	 */
	public Integer[] getWeightVector() {
		return this.weightVector;
	}

	/**
	 * Setter for the weight vector.
	 * 
	 * @param weightVector
	 *            The new set of weights to be saved.
	 */
	public void setWeightVector(Integer[] weightVector) {
		this.weightVector = weightVector;
	}

	/**
	 * Gets a String representation of the weight vector.
	 * 
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
