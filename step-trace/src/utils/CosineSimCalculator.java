/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculates the Cosine Similarity between two numerical vectors.
 * @author omazhary
 */
public class CosineSimCalculator {
    
    private double result;
    private double[] vector1;
    private double[] vector2;
    
    public CosineSimCalculator() {
        this.result = 0.0;
        this.vector1 = null;
        this.vector2 = null;
    }
    
    /**
     * Attempts to parse a given object (regardless of its original type) to a double.
     * @param in The object that will be parsed.
     * @return The numerical value of the object.
     * @throws NumberFormatException In case the object is not a number.
     */
    private double parseToDouble(Object in) throws NumberFormatException {
        return Double.parseDouble(in.toString());
    }
    
    /**
     * Clears all previous calculations.
     */
    private void clear() {
        this.result = 0.0;
        this.vector1 = null;
        this.vector2 = null;
    }
    
    /**
     * Calculates the dot product between two vectors (can be used to calculate the norm).
     * @param in1 The first input vector.
     * @param in2 The second input vector.
     * @return The scalar product of both vectors (or the norm if both vectors are the same).
     * @throws CosineSimException If the vectors do not have the same number of elements.
     */
    private double getDotProduct(double[] in1, double[] in2) throws CosineSimException {
        double temp_result = 0;
        if (in1.length != in2.length) {
            throw new CosineSimException("The two vectors do not have the same amount of elements.");
        }
        for (int i = 0; i < in1.length; i++) {
            temp_result += in1[i] * in2[i];
        }
        return temp_result;
    }
    
    /**
     * Gets the norm of a single vector.
     * @param in The input vector.
     * @return The norm of the given vector.
     * @throws CosineSimException Uses the getDotProduct function.
     */
    private double getNorm(double[] in) throws CosineSimException {
        double temp_result = this.getDotProduct(in, in);
        return Math.sqrt(temp_result);
    }
    
    /**
     * Prepares the vectors for processing.
     * @param in1 The first vector.
     * @param in2 The second vector.
     */
    private void prepVectors(Object[] in1, Object[] in2) {
        this.vector1 = new double[in1.length];
        this.vector2 = new double[in2.length];
        for (int i = 0; i < in1.length; i++) {
            this.vector1[i] = this.parseToDouble(in1[i]);
        }
        for (int i = 0; i < in2.length; i++) {
            this.vector2[i] = this.parseToDouble(in2[i]);
        }
    }
    
    /**
     * Calculates the cosine of the angle between the two given vectors.
     * @param in1 The first vector.
     * @param in2 The second vector.
     * @return A value between 0 and 1, where 0 is totally dissimilar and 1 is totally similar.
     */
    public double getSimilarity(Object[] in1, Object[] in2) {
        this.clear();
        this.prepVectors(in1, in2);
        try {
        	double numerator = this.getDotProduct(this.vector1, this.vector2);
        	double deno1 = this.getNorm(this.vector1);
        	double deno2 = this.getNorm(this.vector2);
            this.result = numerator / (deno1 * deno2);
        } catch (CosineSimException ex) {
            Logger.getLogger(CosineSimCalculator.class.getName()).log(Level.SEVERE, null, ex);
            this.result = 0;
        }
        return Math.abs(this.result);
    }
    
}
