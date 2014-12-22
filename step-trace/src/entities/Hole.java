/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Represents a hole in the object.
 *
 * @author omazhary
 */
public class Hole {

    private FaceBound holeObject;
    private CartesianPoint[] points;
    private float[] representative;
    private Float[] direction;
    private String housingPlaneGeometry;

    public Hole(FaceBound holeObject, Float[] planeDirection, AdvancedFace plane) {
        this.holeObject = holeObject;
        this.points = new CartesianPoint[this.holeObject.getAllPoints().size()];
        this.representative = new float[3];
        Iterator itr = this.holeObject.getAllPoints().iterator();
        int i = 0;
        while (itr.hasNext()) {
            CartesianPoint element = (CartesianPoint) itr.next();
            this.points[i] = element;
            this.representative[0] += element.getX();
            this.representative[1] += element.getY();
            this.representative[2] += element.getZ();
            i++;
        }
        this.representative[0] = this.representative[0] / i;
        this.representative[1] = this.representative[1] / i;
        this.representative[2] = this.representative[2] / i;
        this.direction = planeDirection;

        if (plane.getSurfGeometry() instanceof CylindricalSurface) {
            this.housingPlaneGeometry = "CYLINDER";
        } else if (plane.getSurfGeometry() instanceof Circle) {
            this.housingPlaneGeometry = "CIRCLE";
        } else if (plane.getSurfGeometry() instanceof Plane && plane.getFaceOuterBound().isCircle()) {
            this.housingPlaneGeometry = "CIRCLE";
        } else {
            this.housingPlaneGeometry = "UNKNOWN";
        }
    }

    /**
     * Points getter.
     *
     * @return An array of cartesian points belonging to the object.
     */
    public CartesianPoint[] getPoints() {
        return this.points;
    }

    /**
     * Getter for the cartesian point representative.
     *
     * @return A float array where [0] => Avg(X), [1] => Avg(Y) and [2] =>
     * Avg(Z).
     */
    public float[] getRepresentative() {
        return this.representative;
    }

    /**
     * Getter for the directional components representative.
     *
     * @return A float array where [0] => X, [1] => Y and [2] => Z.
     */
    public Float[] getDirectionComponents() {
        return this.direction;
    }

    /**
     * Getter for the housing plane's geometry.
     *
     * @return A string describing the geometry of the housing plane
     * (uppercase).
     */
    public String getHousingPlaneGeometry() {
        return this.housingPlaneGeometry;
    }

    /**
     * Compares this hole with another hole object and determines if they are
     * the same.
     *
     * @param otherHole The hole to be compared with this one.
     * @return TRUE if both holes are the same, FALSE otherwise.
     */
    public boolean equals(Hole otherHole) {
        if (this.points.length != otherHole.getPoints().length) {
            return false;
        }
        for (int i = 0; i < this.points.length; i++) {
            if (!this.points[i].equals(otherHole.getPoints()[i])) {
                return false;
            }
        }
        return true;
    }
}
