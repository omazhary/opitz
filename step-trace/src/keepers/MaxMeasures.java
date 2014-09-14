package keepers;

public class MaxMeasures {
	public double maxWidth, maxLength, maxHeight;
	public float minY, maxY, minZ, maxZ, minX, maxX;

        /**
         * Parameterized constructor which initializes the object to the given values.
         * @param maxLength The maximum length of the CAD object.
         * @param maxHeight The maximum height of the CAD object.
         * @param maxWidth The maximum width of the CAD object.
         * @param minY The minimum Y-value encountered in the CAD object.
         * @param minZ The minimum Z-value encountered in the CAD object.
         * @param maxZ The maximum Z-value encountered in the CAD object.
         * @param minX The minimum X-value encountered in the CAD object.
         * @param maxX The maximum X-value encountered in the CAD object.
         * @param maxY The maximum Y-value encountered in the CAD object.
         */
	public MaxMeasures(double maxLength, double maxHeight, double maxWidth, float minY, float minZ, float maxZ, float minX, float maxX, float maxY) {
		this.maxLength = maxLength > maxWidth ? maxLength : maxWidth;
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth < maxLength ? maxWidth : maxLength;
		this.minY = minY;
		this.minZ = minZ;
		this.maxZ = maxZ;
		this.minX = minX;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
        /**
         * An override of the toString() method in the "Object" class.
         * @return A string containing all the maximum-measurement information of the object.
         */
	@Override
	public String toString() {
		return "maxLength " + maxLength + ", maxWidth " + maxWidth + ", maxHeight " + maxHeight;
	}
}