package keepers;

import java.util.HashMap;
import java.util.Map;

import entities.CartesianPoint;

public class CartesianPointKeeper {

    private static Map<String, CartesianPoint> map = new HashMap<String, CartesianPoint>();
    private static MaxMeasures maxMeasures;

    /**
     * Transforms the initial line containing the cartesian point info to a CartesianPoint object.
     * @param lineNum The line number corresponding to the required cartesian point.
     * @return A CartesianPoint object containing the information indicated by the line.
     */
    public static CartesianPoint getCartesianPoint(String lineNum) {
        CartesianPoint res = map.get(lineNum);
        if (res == null) {
            res = new CartesianPoint(lineNum);
            map.put(lineNum, res);
        }
        return res;
    }

    /**
     * A getter for the map containing the available cartesian points.
     * @return A hashmap containing CartesianPoint objects with "String" as keys.
     */
    public static Map<String, CartesianPoint> getMap() {
        return map;
    }

    /**
     * Calculates the maximum and minimum measurements of the entire CAD object.
     * @return A MaxMeasures object containing all the extreme measurements of the CAD object.
     */
    public static MaxMeasures getMaxShapeMeasures() {
        if (maxMeasures != null) {
            return maxMeasures;
        }
        CartesianPoint p = map.values().iterator().next();
        float minX = p.getX(), maxX = p.getX(), minY = p.getY(), maxY = p.getY(), minZ = p.getZ(), maxZ = p.getZ();
        for (CartesianPoint cp : map.values()) {
            if (cp.getX() < minX) {
                minX = cp.getX();
            } else if (cp.getX() > maxX) {
                maxX = cp.getX();
            }
            if (cp.getY() < minY) {
                minY = cp.getY();
            } else if (cp.getY() > maxY) {
                maxY = cp.getY();
            }
            if (cp.getZ() < minZ) {
                minZ = cp.getZ();
            } else if (cp.getZ() > maxZ) {
                maxZ = cp.getZ();
            }
        }
        maxMeasures = new MaxMeasures(maxZ - minZ, maxY - minY, maxX - minX, minY, minZ, maxZ, minX, maxX, maxY);
        return maxMeasures;
    }

    /**
     * Clears all the data in this object.
     */
    public static void clearAll() {
        maxMeasures = null;
        map.clear();
    }
}
