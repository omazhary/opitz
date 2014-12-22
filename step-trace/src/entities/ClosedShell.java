package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import keepers.CartesianPointKeeper;
import keepers.ClosedShellKeeper;
import utils.CosineSimCalculator;
import utils.RegExp;

public class ClosedShell extends AbstractEntity {

	public final static String _CLOSED_SHELL = "CLOSED_SHELL";
	private List<AdvancedFace> list = new ArrayList<AdvancedFace>();
	private int throughHolesCount, auxiliaryHolesCount;
	private Float[] rotationAxisOrientation;
	private AdvancedFace rotationPlane;
	private ArrayList<Hole> holes;
	private CosineSimCalculator calculator;

	/**
	 * Getter for a list containing the advanced faces of a CAD model.
	 *
	 * @return A List<AdvancedFace> object containing all the advanced faces of
	 *         the model.
	 */
	public List<AdvancedFace> getAdvancedFaces() {
		return list;
	}

	// CLOSED_SHELL ( 'NONE', ( #22, #19, #24, #23, #21, #20 ) )
	/**
	 * Parameterized constructor which initializes this object to a certain
	 * advanced face.
	 *
	 * @param lineId
	 *            The line ID of an advanced-face entity within the hashmap.
	 */
	public ClosedShell(String lineId) {
		super(lineId);
		this.holes = new ArrayList<Hole>();
		this.calculator = new CosineSimCalculator();
		this.rotationAxisOrientation = new Float[3];
		this.rotationPlane = null;
		String val = RegExp.getValueBetweenDoubleParentheses(linesMap
				.get(lineId));
		// Splits the closed shell and treats each of its parameters as an
		// ADVANCED_FACE entity.
		for (String advFaceId : Arrays.asList(val.split(","))) {
			AdvancedFace af = new AdvancedFace(advFaceId.trim());
			boolean isDuplicate = false;
			if (af.getSurfGeometry() instanceof CylindricalSurface) {
				for (AdvancedFace afInner : list) {
					List<EdgeCurve> l1 = af.getFaceOuterBound()
							.getAllCircleEdgeCurves();
					List<EdgeCurve> l2 = afInner.getFaceOuterBound()
							.getAllCircleEdgeCurves();
					if (afInner.getSurfGeometry() instanceof CylindricalSurface
							&& afInner.getSurfGeometry().equals(
									af.getSurfGeometry())
							&& l1.size() == 2
							&& l2.size() == 2
							&& (l1.get(0)
									.getEdgeGeometry()
									.getDirection()
									.equals(l2.get(0).getEdgeGeometry()
											.getDirection()) || l1
									.get(0)
									.getEdgeGeometry()
									.getDirection()
									.equals(l2.get(1).getEdgeGeometry()
											.getDirection()))
							&& (l1.get(1)
									.getEdgeGeometry()
									.getDirection()
									.equals(l2.get(0).getEdgeGeometry()
											.getDirection()) || l1
									.get(1)
									.getEdgeGeometry()
									.getDirection()
									.equals(l2.get(1).getEdgeGeometry()
											.getDirection()))) {
						isDuplicate = true;
						break;
					}
				}
			}
			if (!isDuplicate) {
				list.add(af);
			}
		}
		ClosedShellKeeper.set(this);
		this.getCylindricalSurfacesWithoutThroughHoles();
		this.getRotationCore();
		markThroughHoles();
		markAuxiliaryHoles();
	}

	@Override
	public String getEntityName() {
		return _CLOSED_SHELL;
	}

	/*
	 * public void getParallel() { for (AdvancedFace af : list) {
	 * Axis2Placement3D a2p3D = af.getSurfGeometry().getAxis2Placement3D(); for
	 * (AdvancedFace aFinner : list) { if
	 * (af.getLineId().equals(aFinner.getLineId())) { continue; }
	 * Axis2Placement3D a2p3DInner =
	 * aFinner.getSurfGeometry().getAxis2Placement3D(); if
	 * (a2p3D.getAxis().equals(a2p3DInner.getAxis()) &&
	 * a2p3D.getRef_direction().equals(a2p3DInner.getRef_direction())) {
	 * System.out.println("da " + af.getLineId() + ", " + aFinner.getLineId());
	 * } } } }
	 */
	public AdvancedFace getBottomPlane() {
		for (AdvancedFace af : list) {
			Axis2Placement3D a2p3D = af.getSurfGeometry().getAxis2Placement3D();
			if (af.getSurfGeometry() instanceof Plane
					&& a2p3D.getCartesianPoint().getY() == CartesianPointKeeper
							.getMaxShapeMeasures().minY
					&& a2p3D.getAxis().isYOriented()) {
				return af;
			}
		}
		return null;
	}

	public AdvancedFace getFrontPlane() {
		for (AdvancedFace af : list) {
			Axis2Placement3D a2p3D = af.getSurfGeometry().getAxis2Placement3D();
			if (af.getSurfGeometry() instanceof Plane
					&& a2p3D.getCartesianPoint().getZ() == CartesianPointKeeper
							.getMaxShapeMeasures().maxZ
					&& a2p3D.getAxis().isZOriented()) {
				return af;
			}
		}
		return null;
	}

	public AdvancedFace getBackPlane() {
		for (AdvancedFace af : list) {
			Axis2Placement3D a2p3D = af.getSurfGeometry().getAxis2Placement3D();
			if (af.getSurfGeometry() instanceof Plane
					&& a2p3D.getCartesianPoint().getZ() == CartesianPointKeeper
							.getMaxShapeMeasures().minZ
					&& a2p3D.getAxis().isZOriented()) {
				return af;
			}
		}
		return null;
	}

	public AdvancedFace getTopPlane() {
		for (AdvancedFace af : list) {
			Axis2Placement3D a2p3D = af.getSurfGeometry().getAxis2Placement3D();
			if (af.getSurfGeometry() instanceof Plane
					&& a2p3D.getCartesianPoint().getY() == CartesianPointKeeper
							.getMaxShapeMeasures().maxY
					&& a2p3D.getAxis().isYOriented()) {
				return af;
			}
		}
		return null;
	}

	public AdvancedFace getLeftPlane() {
		for (AdvancedFace af : list) {
			Axis2Placement3D a2p3D = af.getSurfGeometry().getAxis2Placement3D();
			if (af.getSurfGeometry() instanceof Plane
					&& a2p3D.getCartesianPoint().getX() == CartesianPointKeeper
							.getMaxShapeMeasures().minX
					&& a2p3D.getAxis().isXOriented()) {
				return af;
			}
		}
		return null;
	}

	public AdvancedFace getRightPlane() {
		for (AdvancedFace af : list) {
			Axis2Placement3D a2p3D = af.getSurfGeometry().getAxis2Placement3D();
			if (af.getSurfGeometry() instanceof Plane
					&& a2p3D.getCartesianPoint().getX() == CartesianPointKeeper
							.getMaxShapeMeasures().maxX
					&& a2p3D.getAxis().isXOriented()) {
				return af;
			}
		}
		return null;
	}

	public int getYOrientedPlaneFacesCount() {
		int res = 0;
		Set<Float> set = new HashSet<Float>();
		for (AdvancedFace af : list) {
			if (af.getSurfGeometry().getDirection().isYOriented()
					&& af.getSurfGeometry() instanceof Plane) {
				float yValue = af.getSurfGeometry().getAxis2Placement3D()
						.getCartesianPoint().getY();
				if (!set.contains(yValue)) {
					res++;
				}
				set.add(yValue);
			}
		}
		return res;
	}

	public boolean hasUpperMachining() {
		if (getTopPlane() != null) {
			for (AdvancedFace af : list) {
				if (af.getSurfGeometry() instanceof CylindricalSurface
						&& af.getSurfGeometry().getDirection().isZXOriented()) {
					return true;
				}
			}
		}
		return false;
	}

	public AdvancedFace getAdvancedFaceByFaceBoundId(String id) {
		for (AdvancedFace af : list) {
			if (af.getFaceOuterBound().getLineId().equals(id)) {
				return af;
			}
		}
		return null;
	}

	public boolean isAllPlanes() {
		boolean res = true;
		for (AdvancedFace af : list) {
			res &= af.isPlane();
		}
		if (res) {
			System.out.println("all faces are planes");
		}
		return res;
	}

	// Gets all cylindrical surfaces even in groove (there are 2 cylinr), even
	// in flat rectangle with circular deviation, even with upper machining.
	// For non-rotational we don't care about amount of these cylinrSurfaces,
	// but for rotational - care
	public List<AdvancedFace> getCylindricalSurfacesWithoutThroughHoles() {
		List<AdvancedFace> res = new ArrayList<AdvancedFace>();
		for (AdvancedFace af : list) {
			if (af.getSurfGeometry() instanceof CylindricalSurface
					&& !af.isThroughHole && !af.isAuxiliaryHole
					&& !res.contains(af)) {
				res.add(af);
			}
		}
		return res;
	}

	/**
	 * Gets the core cylindrical object around which the model rotates.
	 */
	private void getRotationCore() {
		AdvancedFace max = null;
		int numberOfPoints = 0;
		for (AdvancedFace af : list) {
			if ((af.getSurfGeometry() instanceof CylindricalSurface)
					|| (af.getSurfGeometry() instanceof ConicalSurface)) {
				if (af.getFaceOuterBound().getAllPoints().size() > numberOfPoints) {
					max = af;
					numberOfPoints = af.getFaceOuterBound().getAllPoints()
							.size();
					this.rotationPlane = af;
					this.rotationAxisOrientation[0] = af.getSurfGeometry()
							.getDirection().getComponents()[0];
					this.rotationAxisOrientation[1] = af.getSurfGeometry()
							.getDirection().getComponents()[1];
					this.rotationAxisOrientation[2] = af.getSurfGeometry()
							.getDirection().getComponents()[2];
				}
			}
		}
	}

	public int getThroughHolesCount() {
		return throughHolesCount;
	}

	private void markThroughHoles() {
		if (getBackPlane() != null && getFrontPlane() != null) {
			for (FaceBound f : getBackPlane().getFaceInnerBound()) {
				if (f.isCircle() && !f.isAdjacentMarkedAsThroughHole()
						&& f.hasOppositeCircle(getFrontPlane())) {
					f.markAdjacentAsThroughHole();
					throughHolesCount++;
					Hole temp = new Hole(f, getBackPlane().getSurfGeometry()
							.getDirection().getComponents(), getBackPlane());
					if (!this.holeContained(temp)) {
						this.holes.add(temp);
					}
				}
			}
		}
		if (getLeftPlane() != null && getRightPlane() != null) {
			for (FaceBound f : getLeftPlane().getFaceInnerBound()) {
				if (f.isCircle() && !f.isAdjacentMarkedAsThroughHole()
						&& f.hasOppositeCircle(getRightPlane())) {
					f.markAdjacentAsThroughHole();
					throughHolesCount++;
					Hole temp = new Hole(f, getLeftPlane().getSurfGeometry()
							.getDirection().getComponents(), getLeftPlane());
					if (!this.holeContained(temp)) {
						this.holes.add(temp);
					}
				}
			}
		}
		if (getTopPlane() != null && getBottomPlane() != null) {
			for (FaceBound f : getTopPlane().getFaceInnerBound()) {
				if (f.isCircle() && !f.isAdjacentMarkedAsThroughHole()
						&& f.hasOppositeCircle(getBottomPlane())) {
					f.markAdjacentAsThroughHole();
					throughHolesCount++;
					Hole temp = new Hole(f, getTopPlane().getSurfGeometry()
							.getDirection().getComponents(), getBottomPlane());
					if (!this.holeContained(temp)) {
						this.holes.add(temp);
					}
				}
			}
		}
	}

	private int getInnerCirclesCountForOnePlane(AdvancedFace af) {
		int res = 0;
		for (FaceBound fb : af.getFaceInnerBound()) {
			if (fb.isCircle() && fb.getAdjacentCylinder() != null) {
				fb.getAdjacentCylinder().isAuxiliaryHole = true;
				Hole temp = new Hole(fb, af.getSurfGeometry().getDirection()
						.getComponents(), af);
				if (!this.holeContained(temp)) {
					this.holes.add(temp);
				}
				res++;
			}
		}
		return res;
	}

	private boolean isNotFoundOrHasNoInnerCircles(AdvancedFace plane) {
		return plane == null || getInnerCirclesCountForOnePlane(plane) == 0;
	}

	public int getAuxiliaryHolesCount() {
		return auxiliaryHolesCount;
	}

	private void markAuxiliaryHoles() {
		int res = 0;
		if (getFrontPlane() != null
				&& isNotFoundOrHasNoInnerCircles(getBackPlane())) {
			res += getInnerCirclesCountForOnePlane(getFrontPlane());
		} else if (getBackPlane() != null
				&& isNotFoundOrHasNoInnerCircles(getFrontPlane())) {
			res += getInnerCirclesCountForOnePlane(getBackPlane());
		} else if (getLeftPlane() != null
				&& isNotFoundOrHasNoInnerCircles(getRightPlane())) {
			res += getInnerCirclesCountForOnePlane(getLeftPlane());
		} else if (getRightPlane() != null
				&& isNotFoundOrHasNoInnerCircles(getLeftPlane())) {
			res += getInnerCirclesCountForOnePlane(getRightPlane());
		} else if (getTopPlane() != null
				&& isNotFoundOrHasNoInnerCircles(getBottomPlane())) {
			res += getInnerCirclesCountForOnePlane(getTopPlane());
		} else if (getBottomPlane() != null
				&& isNotFoundOrHasNoInnerCircles(getTopPlane())) {
			res += getInnerCirclesCountForOnePlane(getBottomPlane());
		}
		auxiliaryHolesCount = res;
	}

	/**
	 * Returns the total number of holes within the model.
	 *
	 * @return An integer containing the total number of holes(through and
	 *         auxiliary) in the model.
	 */
	public int getHoleCount() {
		return this.holes.size();
	}

	/**
	 * Checks if a hole is already within the list of holes.
	 *
	 * @param hole
	 *            The hole that will be checked.
	 * @return True if it is already in the list, false if not.
	 */
	private boolean holeContained(Hole hole) {
		for (Hole temp : this.holes) {
			if (temp.equals(hole)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the existing holes are equidistant from each other.
	 *
	 * @param holeDistanceThreshold
	 *            The minimum similarity of differences between the holes (0-1).
	 * @return True if they are equidistant, false if not.
	 */
	public boolean areHolesEquidistant(double holeDistanceThreshold) {
		double maxSimilarity = 2;

		if (this.holes.isEmpty()) {
			System.out.println("There are no holes.");
			return false;
		}

		Float[][][] differences = new Float[this.holes.size()][this.holes
				.size()][3];

		// Get the distance from all hole representatives:
		for (int i = 0; i < this.holes.size(); i++) {
			for (int j = 0; j < this.holes.size(); j++) {
				differences[i][j][0] = Math.abs(this.holes.get(i)
						.getRepresentative()[0]
						- this.holes.get(j).getRepresentative()[0]);
				differences[i][j][1] = Math.abs(this.holes.get(i)
						.getRepresentative()[1]
						- this.holes.get(j).getRepresentative()[1]);
				differences[i][j][2] = Math.abs(this.holes.get(i)
						.getRepresentative()[2]
						- this.holes.get(j).getRepresentative()[2]);
			}
		}

		// Check if the distances comply with the threshold:
		for (int i = 0; i < this.holes.size(); i++) {
			for (int j = 0; j < this.holes.size(); j++) {
				for (int k = 0; k < this.holes.size(); k++) {
					for (int l = 0; l < this.holes.size(); l++) {
						double tempResult = this.calculator.getSimilarity(
								differences[i][j], differences[k][l]);
						if (tempResult < maxSimilarity) {
							maxSimilarity = tempResult;
						}
					}
				}
			}
		}

		return maxSimilarity >= holeDistanceThreshold;
	}

	/**
	 * Checks if the holes are equally distant from the center.
	 *
	 * @param absoluteCenter
	 *            The center of the model (absolute regarding all dimensions).
	 * @param holeDistributionThreshold
	 *            The minimum similarity between the difference between each
	 *            hole and the center (0-1).
	 * @return True if the holes are evenly distributed around the center, false
	 *         if not.
	 */
	public boolean areHolesEvenlyDistributed(CartesianPoint absoluteCenter,
			double holeDistributionThreshold) {
		Float[] temp = new Float[3];
		Float[][] differences = new Float[this.holes.size()][3];
		double maxSimilarity = 2;
		Float[] center = new Float[3];
		center[0] = absoluteCenter.getX();
		center[1] = absoluteCenter.getY();
		center[2] = absoluteCenter.getZ();

		if (this.holes.isEmpty()) {
			System.out.println("There are no holes.");
			return false;
		}

		for (int i = 0; i < this.holes.size(); i++) {
			temp[0] = this.holes.get(i).getRepresentative()[0];
			temp[1] = this.holes.get(i).getRepresentative()[1];
			temp[2] = this.holes.get(i).getRepresentative()[2];
			differences[i][0] = Math.abs(temp[0] - center[0]);
			differences[i][1] = Math.abs(temp[1] - center[1]);
			differences[i][2] = Math.abs(temp[2] - center[2]);
		}

		for (int i = 0; i < differences.length; i++) {
			for (int j = 0; j < differences.length; j++) {
				if (i != j) {
					double tempResult = this.calculator.getSimilarity(
							differences[i], differences[j]);
					if (tempResult < maxSimilarity) {
						maxSimilarity = tempResult;
					}
				}
			}
		}

		return maxSimilarity >= holeDistributionThreshold;
	}

	/**
	 * Checks if all holes have the same orientation within a given threshold.
	 *
	 * @param orientationThreshold
	 *            The allowable variation in similarity between hole
	 *            orientations.
	 * @return True if all holes have similar orientations, False otherwise.
	 */
	public boolean holesHaveSameOrientation(double orientationThreshold) {
		double maxSimilarity = 2;
		Float[] in1 = new Float[3];
		Float[] in2 = new Float[3];
		for (int i = 0; i < this.holes.size(); i++) {
			in1[0] = this.holes.get(i).getDirectionComponents()[0];
			in1[1] = this.holes.get(i).getDirectionComponents()[1];
			in1[2] = this.holes.get(i).getDirectionComponents()[2];
			for (int j = 0; j < this.holes.size(); j++) {
				in2[0] = this.holes.get(j).getDirectionComponents()[0];
				in2[1] = this.holes.get(j).getDirectionComponents()[1];
				in2[2] = this.holes.get(j).getDirectionComponents()[2];
				double tempResult = this.calculator.getSimilarity(in1, in2);
				if (tempResult < maxSimilarity) {
					maxSimilarity = tempResult;
				}
			}
		}
		return maxSimilarity >= orientationThreshold;
	}

	/**
	 * Checks if a single hole is radial or not.
	 *
	 * @param hole
	 *            The hole that will be checked.
	 * @return True if the hole is radially oriented, False otherwise.
	 */
	private boolean isHoleRadial(Hole hole) {
		Float[] dir = new Float[3];
		dir[0] = hole.getDirectionComponents()[0];
		dir[1] = hole.getDirectionComponents()[1];
		dir[2] = hole.getDirectionComponents()[2];
		double simDiff01 = 1 - this.calculator.getSimilarity(dir,
				this.rotationAxisOrientation); // Distance to parallel
		double simDiff02 = this.calculator.getSimilarity(dir,
				this.rotationAxisOrientation); // Distance to orthogonal
		return simDiff01 > simDiff02;
	}

	/**
	 * Checks if a single hole is axial or not.
	 *
	 * @param hole
	 *            The hole that will be checked.
	 * @return True if the hole is axially oriented, False otherwise.
	 */
	private boolean isHoleAxial(Hole hole) {
		Float[] dir = new Float[3];
		dir[0] = hole.getDirectionComponents()[0];
		dir[1] = hole.getDirectionComponents()[1];
		dir[2] = hole.getDirectionComponents()[2];
		double simDiff01 = 1 - this.calculator.getSimilarity(dir,
				this.rotationAxisOrientation); // Distance to parallel
		double simDiff02 = this.calculator.getSimilarity(dir,
				this.rotationAxisOrientation); // Distance to orthogonal
		return simDiff01 < simDiff02;
	}

	/**
	 * Checks if all existing holes are radially oriented.
	 *
	 * @return True if all holes are radially oriented, False otherwise.
	 */
	public boolean areHolesRadial() {
		for (int i = 0; i < this.holes.size(); i++) {
			if (!this.isHoleRadial(this.holes.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if all existing holes are axially oriented.
	 *
	 * @return True if all holes are axially oriented, False otherwise.
	 */
	public boolean areHolesAxial() {
		for (int i = 0; i < this.holes.size(); i++) {
			if (!this.isHoleAxial(this.holes.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if there are teeth on the edge of the model.
	 *
	 * @param teethSensitivity
	 *            Double between 0 and 1 to define how sensitive the algorithm
	 *            is.
	 * @return True if teeth are found, False otherwise.
	 */
	public boolean teethExist(double teethSensitivity) {
		double lines = 0;
		double totalEdges = 0;
		boolean rotational = true;
		for (int i = 0; i < 6; i++) {
			if (this.rotationPlane == null) {
				// Non-rotational:
				rotational = false;
				switch (i) {
				case 0:
					this.rotationPlane = this.getBackPlane();
					break;
				case 1:
					this.rotationPlane = this.getFrontPlane();
					break;
				case 2:
					this.rotationPlane = this.getLeftPlane();
					break;
				case 3:
					this.rotationPlane = this.getRightPlane();
					break;
				case 4:
					this.rotationPlane = this.getBottomPlane();
					break;
				case 5:
					this.rotationPlane = this.getTopPlane();
					break;
				default:
					this.rotationPlane = null;
					break;
				}
			}
			if (this.rotationPlane == null) {
				continue;
			}
			for (AdvancedFace face : this.rotationPlane.getFaceOuterBound()
					.getAdjacents()) {
				for (EdgeCurve edge : face.getFaceOuterBound().getEdgeCurves()) {
					totalEdges++;
					if (!(edge.getEdgeGeometry() instanceof Circle)) {
						lines++;
					}
				}
			}
			if (rotational) {
				break;
			} else {
				this.rotationPlane = null;
			}
		}
		return teethSensitivity <= (lines / totalEdges);
	}

	/**
	 * Checks if the teeth are parallel to the rotation axis.
	 *
	 * @param teethOrientationThreshold
	 *            Double specifying the minimum similarity between the teeth
	 *            orientation and the rotation axis.
	 * @return True if the teeth are parallel to the rotation axis, False
	 *         otherwise.
	 */
	public boolean teethParalleltoRotationAxis(
			double teethOrientationThreshold, double teethSensitivity) {
		if (this.rotationPlane.getSurfGeometry() instanceof ConicalSurface) {
			return false;
		}
		Float[] temp = new Float[3];
		double parallels = 0;
		double total = 0;
		for (AdvancedFace face : this.rotationPlane.getFaceOuterBound()
				.getAdjacents()) {
			for (EdgeCurve edge : face.getFaceOuterBound().getEdgeCurves()) {
				total++;
				if (!(edge.getEdgeGeometry() instanceof Circle)
						&& edge.getEdgeGeometry() != null) {
					temp[0] = edge.getEdgeGeometry().getDirection()
							.getComponents()[0];
					temp[1] = edge.getEdgeGeometry().getDirection()
							.getComponents()[1];
					temp[2] = edge.getEdgeGeometry().getDirection()
							.getComponents()[2];
					if (this.calculator.getSimilarity(temp,
							this.rotationAxisOrientation) >= teethOrientationThreshold) {
						parallels++;
					}
				}
			}
		}
		return teethSensitivity <= (parallels / total);
	}

	/**
	 * Gets the majority geometry of the model's teeth.
	 *
	 * @return LINE if the majority are lines, ELLIPSE if the majority are
	 *         ellipses, CIRCLE if the majority are circles and OTHER if none of
	 *         the above.
	 */
	public String getTeethGeometry() {
		String result = "OTHER";
		int circles = 0, lines = 0, ellipses = 0, others = 0;
		ArrayList<String> geometries = new ArrayList<String>();
		for (AdvancedFace face : this.rotationPlane.getFaceOuterBound()
				.getAdjacents()) {
			for (EdgeCurve edge : face.getFaceOuterBound().getEdgeCurves()) {
				if (edge.getEdgeGeometry() != null) {
					if (edge.getEdgeGeometry() instanceof Line) {
						geometries.add("LINE");
					} else if (edge.getEdgeGeometry() instanceof Ellipse) {
						geometries.add("ELLIPSE");
					} else if (edge.getEdgeGeometry() instanceof Circle) {
						geometries.add("CIRCLE");
					} else {
						System.out.println(edge.getEdgeGeometry().getClass()
								.toString());
						geometries.add("OTHER");
					}
				}
			}
		}
		for (int i = 0; i < geometries.size(); i++) {
			if (geometries.get(i).equalsIgnoreCase("LINE")) {
				lines++;
			} else if (geometries.get(i).equalsIgnoreCase("ELLIPSE")) {
				ellipses++;
			} else if (geometries.get(i).equalsIgnoreCase("CIRCLE")) {
				circles++;
			} else {
				others++;
			}
		}

		if (lines > ellipses && lines > circles && lines > others) {
			result = "LINE";
		} else if (ellipses > lines && ellipses > circles && ellipses > others) {
			result = "ELLIPSE";
		} else if (circles > lines && circles > ellipses && circles > others) {
			result = "CIRCLE";
		}

		return result;
	}

	/**
	 * A getter for the rotation plane.
	 * 
	 * @return A reference to the model's rotation plane.
	 */
	public AdvancedFace getRotationPlane() {
		return this.rotationPlane;
	}

	/**
	 * Checks whether the bottom plane of a FLAT shape has deviations or not.
	 * 
	 * @param threshold
	 *            The maximum amount of allowable deviations before the shape is
	 *            considered to have deviations.
	 * @return TRUE if the shape has deviations, FALSE otherwise.
	 */
	public boolean hasDeviations(double threshold) {
		double non_line = 0;
		for (int i = 0; i < this.getBottomPlane().getFaceOuterBound()
				.getEdgeCurves().size(); i++) {
			if (!(this.getBottomPlane().getFaceOuterBound().getEdgeCurves()
					.get(i).getEdgeGeometry() instanceof Line)) {
				non_line++;
			}
		}
		double ratio = non_line
				/ this.getBottomPlane().getFaceOuterBound().getEdgeCurves()
						.size();
		return ratio > threshold;
	}

	/**
	 * Checks whether the bottom plane of a FLAT shape is regularly arched or
	 * not.
	 * 
	 * @return TRUE if the plane is regularly arched, FALSE otherwise.
	 */
	public boolean isRegularlyArched() {
		int circles = 0;
		for (int i = 0; i < this.getBottomPlane().getFaceOuterBound()
				.getEdgeCurves().size(); i++) {
			if (this.getBottomPlane().getFaceOuterBound().getEdgeCurves()
					.get(i).getEdgeGeometry() instanceof Circle) {
				circles++;
			}
		}
		return circles == 1;
	}

	/**
	 * Checks if the shape axis is straight.
	 * 
	 * @param threshold
	 *            The minimum parallelism threshold for the shape to be
	 *            considered straight.
	 * @return TRUE if the shape is straight, FALSE otherwise.
	 */
	public boolean isShapeAxisStraight(double threshold) {
		double sim = this.calculator.getSimilarity(this.getFrontPlane()
				.getSurfGeometry().getDirection().getComponents(), this
				.getBackPlane().getSurfGeometry().getDirection()
				.getComponents());
		return sim > threshold;
	}

	/**
	 * Checks whether the model has any dividing surfaces on its faces.
	 * 
	 * @return TRUE if a dividing surface is found, FALSE otherwise.
	 */
	public boolean hasDividingSurface() {
		boolean result = false;
		AdvancedFace surface = null;
		for (int i = 0; i < 6; i++) {
			switch (i) {
			case 0:
				surface = this.getFrontPlane();
				break;
			case 1:
				surface = this.getBackPlane();
				break;
			case 2:
				surface = this.getTopPlane();
				break;
			case 3:
				surface = this.getBottomPlane();
				break;
			case 4:
				surface = this.getLeftPlane();
				break;
			case 5:
				surface = this.getRightPlane();
				break;
			default:
				surface = this.getFrontPlane();
				break;
			}
			if (surface != null) {
				for (AdvancedFace adjacent : surface.getFaceOuterBound()
						.getAdjacents()) {
					if (this.isAbove(adjacent, surface)) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Checks if face1 is in a higher (or in the same) position as face2
	 * 
	 * @param face1
	 *            The face whose position we're attempting to determine.
	 * @param face2
	 *            The base face to which the other one is compared.
	 * @return TRUE if face1 is higher or on the same level as face2, FALSE
	 *         otherwise.
	 */
	private boolean isAbove(AdvancedFace face1, AdvancedFace face2) {
		Float[] face1_loc = new Float[3];
		Float[] face2_loc = new Float[3];

		for (Iterator<CartesianPoint> i = face1.getFaceOuterBound()
				.getAllPoints().iterator(); i.hasNext();) {
			CartesianPoint temp = i.next();
			face1_loc[0] += temp.getX();
			face1_loc[1] += temp.getY();
			face1_loc[2] += temp.getZ();
		}
		face1_loc[0] += face1_loc[0]
				/ face1.getFaceOuterBound().getAllPoints().size();
		face1_loc[1] += face1_loc[1]
				/ face1.getFaceOuterBound().getAllPoints().size();
		face1_loc[2] += face1_loc[2]
				/ face1.getFaceOuterBound().getAllPoints().size();

		for (Iterator<CartesianPoint> i = face2.getFaceOuterBound()
				.getAllPoints().iterator(); i.hasNext();) {
			CartesianPoint temp = i.next();
			face2_loc[0] += temp.getX();
			face2_loc[1] += temp.getY();
			face2_loc[2] += temp.getZ();
		}
		face2_loc[0] = face2_loc[0]
				/ face2.getFaceOuterBound().getAllPoints().size();
		face2_loc[1] = face2_loc[1]
				/ face2.getFaceOuterBound().getAllPoints().size();
		face2_loc[2] = face2_loc[2]
				/ face2.getFaceOuterBound().getAllPoints().size();

		return face1_loc[0] >= face2_loc[0] && face1_loc[1] >= face2_loc[1]
				&& face1_loc[2] >= face2_loc[2];
	}

	/**
	 * Checks whether a box is split into several smaller boxes.
	 * 
	 * @return TRUE if it is split into several boxes, FALSE otherwise.
	 */
	public boolean isSplit() {
		int boxNumber = 0;
		AdvancedFace surface = null;
		for (int i = 0; i < 6; i++) {
			switch (i) {
			case 0:
				surface = this.getFrontPlane();
				break;
			case 1:
				surface = this.getBackPlane();
				break;
			case 2:
				surface = this.getTopPlane();
				break;
			case 3:
				surface = this.getBottomPlane();
				break;
			case 4:
				surface = this.getLeftPlane();
				break;
			case 5:
				surface = this.getRightPlane();
				break;
			default:
				surface = this.getFrontPlane();
				break;
			}
			if (surface != null) {
				for (FaceBound adjacent : surface.getFaceInnerBound()) {
					if (adjacent.isRectangle()) {
						boxNumber++;
					}
				}
			}
		}
		return boxNumber > 1;
	}

	/**
	 * Checks if there is an external screw thread on the rotational shape.
	 * 
	 * @return TRUE if one or more screw threads exist, FALSE otherwise.
	 */
	public boolean hasExternalScrewThread() {
		boolean result = false;
		int rotPoints = this.rotationPlane.getFaceOuterBound().getAllPoints()
				.size();
		for (int i = 0; i < this.rotationPlane.getFaceOuterBound()
				.getAdjacents().size(); i++) {
			AdvancedFace adjacent = this.rotationPlane.getFaceOuterBound()
					.getAdjacents().get(i);
			if (adjacent.getSurfGeometry() instanceof Circle
					|| adjacent.getSurfGeometry() instanceof Ellipse) {
				result = result
						|| adjacent.getFaceOuterBound().getAllPoints().size() > rotPoints;
			}
		}
		return result;
	}

	/**
	 * Checks if there is an internal screw thread on the rotational shape.
	 * 
	 * @return TRUE if one or more screw threads exist, FALSE otherwise.
	 */
	public boolean hasInternalScrewThread() {
		boolean result = false;
		int rotPoints = this.rotationPlane.getFaceOuterBound().getAllPoints()
				.size();
		for (int i = 0; i < this.rotationPlane.getFaceOuterBound()
				.getAdjacents().size(); i++) {
			AdvancedFace adjacent = this.rotationPlane.getFaceOuterBound()
					.getAdjacents().get(i);
			if (adjacent.getSurfGeometry() instanceof Circle
					|| adjacent.getSurfGeometry() instanceof Ellipse) {
				result = result
						|| adjacent.getFaceOuterBound().getAllPoints().size() < rotPoints;
			}
		}
		return result;
	}
	
	/**
	 * Checks for the existence of an external operating thread.
	 * @param threadThreshold The maximum distance between screw threads allowed.
	 * @return TRUE if an operating thread exists, FALSE otherwise.
	 */
	public boolean hasExternalOperatingThread(double threadThreshold) {
		int rotPoints = this.rotationPlane.getFaceOuterBound().getAllPoints()
				.size();
		double averageSim = 0;
		double counter = 0;
		for (int i = 0; i < this.rotationPlane.getFaceOuterBound()
				.getAdjacents().size(); i++) {
			AdvancedFace adjacent = this.rotationPlane.getFaceOuterBound()
					.getAdjacents().get(i);
			if ((adjacent.getSurfGeometry() instanceof Circle || adjacent
					.getSurfGeometry() instanceof Ellipse)
					&& adjacent.getFaceOuterBound().getAllPoints().size() > rotPoints) {
				Float[] closest_rep = new Float[3];
				Float[] adj_rep = new Float[3];
				// Get adjacent rep:
				for (CartesianPoint temp : adjacent.getFaceOuterBound()
						.getAllPoints()) {
					adj_rep[0] += temp.getX();
					adj_rep[1] += temp.getY();
					adj_rep[2] += temp.getZ();
				}
				adj_rep[0] = adj_rep[0]
						/ adjacent.getFaceOuterBound().getAllPoints().size();
				adj_rep[1] = adj_rep[1]
						/ adjacent.getFaceOuterBound().getAllPoints().size();
				adj_rep[2] = adj_rep[2]
						/ adjacent.getFaceOuterBound().getAllPoints().size();

				// Get closest rep:
				double minSim = 1;
				for (int j = 0; j < this.rotationPlane.getFaceOuterBound()
						.getAdjacents().size(); j++) {
					AdvancedFace closest = this.rotationPlane
							.getFaceOuterBound().getAdjacents().get(j);
					for (CartesianPoint temp : closest.getFaceOuterBound()
							.getAllPoints()) {
						closest_rep[0] += temp.getX();
						closest_rep[1] += temp.getY();
						closest_rep[2] += temp.getZ();
					}
					closest_rep[0] = closest_rep[0]
							/ adjacent.getFaceOuterBound().getAllPoints()
									.size();
					closest_rep[1] = closest_rep[1]
							/ adjacent.getFaceOuterBound().getAllPoints()
									.size();
					closest_rep[2] = closest_rep[2]
							/ adjacent.getFaceOuterBound().getAllPoints()
									.size();
					if ((minSim < this.calculator.getSimilarity(adj_rep,
							closest_rep) || minSim == 1)
							&& this.calculator.getSimilarity(adj_rep,
									closest_rep) != 1) {
						minSim = this.calculator.getSimilarity(adj_rep,
								closest_rep);
					}
				}
				averageSim += minSim;
				counter++;
			}
		}
		averageSim /= counter;
		return averageSim > threadThreshold;
	}
	
	/**
	 * Checks for the existence of an internal operating thread.
	 * @param threadThreshold The maximum distance between screw threads allowed.
	 * @return TRUE if an operating thread exists, FALSE otherwise.
	 */
	public boolean hasInternalOperatingThread(double threadThreshold) {
		int rotPoints = this.rotationPlane.getFaceOuterBound().getAllPoints()
				.size();
		double averageSim = 0;
		double counter = 0;
		for (int i = 0; i < this.rotationPlane.getFaceOuterBound()
				.getAdjacents().size(); i++) {
			AdvancedFace adjacent = this.rotationPlane.getFaceOuterBound()
					.getAdjacents().get(i);
			if ((adjacent.getSurfGeometry() instanceof Circle || adjacent
					.getSurfGeometry() instanceof Ellipse)
					&& adjacent.getFaceOuterBound().getAllPoints().size() < rotPoints) {
				Float[] closest_rep = new Float[3];
				Float[] adj_rep = new Float[3];
				// Get adjacent rep:
				for (CartesianPoint temp : adjacent.getFaceOuterBound()
						.getAllPoints()) {
					adj_rep[0] += temp.getX();
					adj_rep[1] += temp.getY();
					adj_rep[2] += temp.getZ();
				}
				adj_rep[0] = adj_rep[0]
						/ adjacent.getFaceOuterBound().getAllPoints().size();
				adj_rep[1] = adj_rep[1]
						/ adjacent.getFaceOuterBound().getAllPoints().size();
				adj_rep[2] = adj_rep[2]
						/ adjacent.getFaceOuterBound().getAllPoints().size();

				// Get closest rep:
				double minSim = 1;
				for (int j = 0; j < this.rotationPlane.getFaceOuterBound()
						.getAdjacents().size(); j++) {
					AdvancedFace closest = this.rotationPlane
							.getFaceOuterBound().getAdjacents().get(j);
					for (CartesianPoint temp : closest.getFaceOuterBound()
							.getAllPoints()) {
						closest_rep[0] += temp.getX();
						closest_rep[1] += temp.getY();
						closest_rep[2] += temp.getZ();
					}
					closest_rep[0] = closest_rep[0]
							/ adjacent.getFaceOuterBound().getAllPoints()
									.size();
					closest_rep[1] = closest_rep[1]
							/ adjacent.getFaceOuterBound().getAllPoints()
									.size();
					closest_rep[2] = closest_rep[2]
							/ adjacent.getFaceOuterBound().getAllPoints()
									.size();
					if ((minSim < this.calculator.getSimilarity(adj_rep,
							closest_rep) || minSim == 1)
							&& this.calculator.getSimilarity(adj_rep,
									closest_rep) != 1) {
						minSim = this.calculator.getSimilarity(adj_rep,
								closest_rep);
					}
				}
				averageSim += minSim;
				counter++;
			}
		}
		averageSim /= counter;
		return averageSim > threadThreshold;
	}

}
