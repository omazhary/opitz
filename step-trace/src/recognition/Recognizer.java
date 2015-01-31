package recognition;

import entities.AdvancedFace;
import entities.CartesianPoint;
import entities.Circle;
import entities.ClosedShell;
import entities.ConicalSurface;
import entities.CylindricalSurface;
import entities.EdgeCurve;
import entities.EdgeLoop;
import entities.Ellipse;
import entities.FaceBound;
import entities.FaceOuterBound;
import entities.InitForm;
import entities.Material;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import keepers.CartesianPointKeeper;
import keepers.MaxMeasures;
import utils.CommonUtils;
import utils.Preferences;
import utils.StepFileReader;

public class Recognizer {

	//private final long serialVersionUID = 1L;

	private List<String> trace = new ArrayList<String>();
	private boolean showGUI = true;
	private ClosedShell cs;
	private boolean isTest;
	private MaxMeasures m;
	private CartesianPoint absoluteCenter;
	private String code;
	private Preferences pref;

	public String mainProcedure(String filePath, boolean isTest,
			Material material, InitForm initialForm, Preferences pref) {
		this.clearAll();
		this.pref = pref;
		this.isTest = isTest;
		print("**** Start: " + filePath);
		int firstDigit = -1, secondDigit = -1, thirdDigit = -1, fourthDigit = -1, fifthDigit = -1, sixthDigit = -1, seventhDigit = -1, eighthDigit = -1, ninthDigit = -1;
		StepFileReader sfr = new StepFileReader(
				filePath == null ? (CommonUtils._PATH_PRODUCTION + "50.03220-01/03220-01.stp")
						: filePath);
		cs = new ClosedShell(sfr.getClosedShellLineId());
		m = CartesianPointKeeper.getMaxShapeMeasures();
		absoluteCenter = getAbsoluteCenter(m);
		AdvancedFace bottom = cs.getBottomPlane();
		AdvancedFace front = cs.getFrontPlane();
		AdvancedFace back = cs.getBackPlane();
		int cylinders = cs.getCylindricalSurfacesWithoutThroughHoles().size();
		if (cylinders > 0
				&& (bottom == null || (bottom != null && isBottomLessThanCircleOfCylinder(
						cs.getCylindricalSurfacesWithoutThroughHoles(), bottom)))) {
			// rotational
			print("Rotational shape");
			double rat = m.maxLength / m.maxWidth;
			double circ_rat = 0;
			// Determine deviations:
			double circles = 0;
			double all_edges = cs.getRotationPlane().getFaceOuterBound()
					.getEdgeCurves().size();
			for (int i = 0; i < cs.getRotationPlane().getFaceOuterBound()
					.getEdgeCurves().size(); i++) {
				if (cs.getRotationPlane().getFaceOuterBound().getEdgeCurves()
						.get(i).getEdgeGeometry() == null
						|| cs.getRotationPlane().getFaceOuterBound()
								.getEdgeCurves().get(i).getEdgeGeometry() instanceof Circle
						|| cs.getRotationPlane().getFaceOuterBound()
								.getEdgeCurves().get(i).getEdgeGeometry() instanceof Ellipse) {
					circles++;
				}
			}
			circ_rat = circles / all_edges;
			// TODO: Put threshold in preferences.
			if (circ_rat >= this.pref.getThresholds()[0]) {
				firstDigit = rat <= 0.5 ? 0 : (rat >= 0.5 && rat < 3) ? 1
						: rat >= 3 ? 2 : -1;
			} else {
				if (rat <= 2) {
					firstDigit = 3;
				} else {
					firstDigit = 4;
				}
			}
			if (cylinders == 1) {
				print("One external cylinder");
			} else if (cylinders == 2) {
				print("Two external cylinders");
			} else if (cylinders == 3) {
				print("Three external cylinders");
			} else {
				print("More than 3 external cylinders");
			}
			secondDigit = getExternMachinigRotational(cylinders);
			thirdDigit = getThirdDigitRotational(cylinders, firstDigit,
					secondDigit);
			fourthDigit = 0;
		} else if (bottom != null) {
			// non rotational
			print("Non Rotational");
			if (m.maxLength / m.maxWidth <= 3 && m.maxLength / m.maxHeight >= 4) {
				// flat
				firstDigit = 6;
				print("flat");
				if (bottom.getFaceOuterBound().isRectangle()) {
					print("Rectangular");
					if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
						secondDigit = 0;
					}
				} else if (bottom.getFaceOuterBound().isTriangle()) {
					print("Rectangular, with one deviation (Right-angle or Triangular)");
					if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
						secondDigit = 1;
					}
				} else if (bottom.getFaceOuterBound().isAllAnglesTheSame()) {
					print("Rectangular, with angular deviations");
					if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
						secondDigit = 2;
					}
				} else if (bottom.getFaceOuterBound().isCircularAndOrtogonal()) {
					print("Rectangular, with circular deviation");
					if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
						secondDigit = 3;
					}
					// TODO: Put this threshold in the preferences frame.
				} else if (bottom.getFaceOuterBound().areAdjacentsXZOriented()
						&& !cs.hasDeviations(this.pref.getThresholds()[1])) {
					secondDigit = 4;
					print("Any flat shape other than 0 to 3");
					// TODO: Put this threshold in the preferences frame.
				} else if ((bottom.getFaceOuterBound().isRectangle() || bottom
						.getFaceOuterBound().isTriangle())
						&& cs.hasDeviations(this.pref.getThresholds()[1])) {
					secondDigit = 5;
					print("Flat components, rectangular or right-angled with small deviations due to casting, welding, forming");
					// TODO: Put this threshold in the preferences frame.
				} else if ((bottom.getFaceOuterBound().isAllAnglesTheSame()
						|| bottom.getFaceOuterBound().isCircularAndOrtogonal() || bottom
						.getFaceOuterBound().areAdjacentsXZOriented())
						&& cs.hasDeviations(this.pref.getThresholds()[1])) {
					secondDigit = 6;
					print("Flat components, round or of any shape other than 5");
				} else if (cs.isRegularlyArched()) {
					secondDigit = 7;
					print("Flat components, regularly arched or dished");
				} else if (!cs.isRegularlyArched()) {
					secondDigit = 8;
					print("Flat components, irregularly arched or dished");
				} else {
					secondDigit = 9;
					print("Other overall shape");
				}
				if (0 <= secondDigit && secondDigit <= 9) {
					fourthDigit = getFourthDigit();
					thirdDigit = getThirdDigitNonRotational(bottom);
				}
			} else if (m.maxLength / m.maxWidth <= 3
					&& m.maxLength / m.maxHeight < 4) {
				// cubic
				print("cubic");
				firstDigit = 8;
				if (cs.getFrontPlane() != null && cs.getBackPlane() != null
						&& cs.getTopPlane() != null
						&& cs.getBottomPlane() != null
						&& cs.getLeftPlane() != null
						&& cs.getRightPlane() != null) {
					print("Block and block-like component");
					if (bottom.getFaceOuterBound().isRectangle()) {
						print("Bottom: Rectangle");
						if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
							secondDigit = 0;
						}
					} else if (bottom.getFaceOuterBound().isTriangle()) {
						print("Bottom: Triangle");
						if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
							secondDigit = 1;
						}
					} else if (bottom.getFaceOuterBound().isRecangPrismatic()) {
						print("Bottom: Rectangular prisms");
						if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
							secondDigit = 2;
						}
					} else if (cs.getHoleCount() > 0) {
						secondDigit = 3;
						print("Component with a mounting or locating surface and principal bore");
					} else if (cs.getHoleCount() > 0 && cs.hasDividingSurface()) {
						secondDigit = 4;
						print("Component with a mounting or locating surface, principal bore, with dividing surface");
					} else if (bottom.getFaceOuterBound()
							.areAdjacentsXZOriented()) {
						print("Bottom: Other shape");
						secondDigit = 5;
					}
				} else {
					print("Box and box-like component");
					if (!cs.isSplit()) {
						if (bottom.getFaceOuterBound().isRecangPrismatic()) {
							print("Approximate or compunded of rectangular prisms.");
							secondDigit = 6;
						} else {
							print("Shape other than 6");
							secondDigit = 7;
						}
					} else {
						if (bottom.getFaceOuterBound().isRecangPrismatic()) {
							print("Approximate or compunded of rectangular prisms.");
							secondDigit = 8;
						} else {
							print("Shape other than 8");
							secondDigit = 9;
						}
					}
				}
				if (0 <= secondDigit && secondDigit <= 9) {
					fourthDigit = getFourthDigit();
					thirdDigit = getThirdDigitNonRotational(bottom);
				}
			} else if (m.maxLength / m.maxWidth > 3) {
				// long
				print("long");
				firstDigit = 7;
				thirdDigit = 0;
				fourthDigit = 0;
				if (front != null && back != null) {
					FaceOuterBound frontFob = front.getFaceOuterBound();
					FaceOuterBound backFob = back.getFaceOuterBound();
					if (!frontFob.isCircle() && !backFob.isCircle()) {
						if (cs.isShapeAxisStraight(0.9)) {
							if (CommonUtils.arePlanesEqualAlongZ(frontFob,
									backFob)) {
								if (frontFob.isRectangle()
										&& backFob.isRectangle()
										&& bottom.getFaceOuterBound()
												.isRectangle()) {
									print("Front plane, back plane: uniform (equal) cross sections (rectangular)");
									secondDigit = 0;
									// fourthDigit = getFourthDigit();
									// thirdDigit = getThirdDigit(bottom);
								} else if (frontFob.isTriangle()
										&& backFob.isTriangle()
										&& frontFob.areAdjacentsXYOriented()
										&& backFob.areAdjacentsXYOriented()) {
									print("Front plane, back plane: uniform (equal) cross sections (triangular)");
									secondDigit = 1;
								} else {
									print("Front plane, back plane: uniform (equal) cross sections");
									secondDigit = 2;
								}
							} else {
								if (frontFob.isRectangle()
										&& backFob.isRectangle()) {
									print("Front plane, back plane: varying cross sections (rectangular)");
									secondDigit = 3;
								} else if (frontFob.isTriangle()
										&& backFob.isTriangle()) {
									print("Front plane, back plane: varying cross sections (triangular)");
									secondDigit = 4;
								} else {
									print("Front plane, back plane: varying cross sections");
									secondDigit = 5;
								}
							}
						} else {
							if ((frontFob.isRectangle() && backFob
									.isRectangle())
									|| (frontFob.isTriangle() && backFob
											.isTriangle())
									|| (frontFob.areAdjacentsXYOriented() && backFob
											.areAdjacentsXYOriented())) {
								secondDigit = 6;
								print("Rectangular, angular, and other cross-sections");
							} else if (cs.getFrontPlane().getSurfGeometry() != cs
									.getBackPlane().getSurfGeometry()) {
								secondDigit = 7;
								print("Formed component");
								// TODO: Put this threshold in the preferences
								// frame.
							} else if (cs.getFrontPlane().getSurfGeometry() != cs
									.getBackPlane().getSurfGeometry()
									&& !cs.isShapeAxisStraight(this.pref
											.getThresholds()[2])) {
								secondDigit = 8;
								print("Formed component with deviations in the main axis");
							} else {
								secondDigit = 9;
								print("Other overall shape");
							}
						}
						if (0 <= secondDigit && secondDigit <= 9) {
							fourthDigit = getFourthDigit();
							thirdDigit = getThirdDigitNonRotational(bottom);
						}
					}
				}
			}
		}

		fifthDigit = getFifthDigit(firstDigit);
		sixthDigit = getSixthDigit(firstDigit);
		seventhDigit = getSeventhDigit(material);
		eighthDigit = getEighthDigit(initialForm);
		ninthDigit = getNinthDigit();
		
		if (firstDigit < 0) {
			firstDigit = 9;
		}
		if (secondDigit < 0) {
			secondDigit = 9;
		}
		if (thirdDigit < 0) {
			thirdDigit = 9;
		}
		if (fourthDigit < 0) {
			fourthDigit = 9;
		}
		if (fifthDigit < 0) {
			fifthDigit = 9;
		}

		String res = "" + firstDigit + secondDigit + thirdDigit + fourthDigit
				+ fifthDigit + sixthDigit + seventhDigit + eighthDigit
				+ ninthDigit;
		print("**** Done: " + res);
		this.code = res;
		return res;
	}

	private boolean isBottomLessThanCircleOfCylinder(
			List<AdvancedFace> cylinders, AdvancedFace bottom) {
		float biggestRadius = 0;
		for (AdvancedFace af : cylinders) {
			if (af.getSurfGeometry() instanceof CylindricalSurface
					&& af.getSurfGeometry().getDirection().isYOriented()) {
				float r = ((CylindricalSurface) af.getSurfGeometry())
						.getRadius();
				if (r > biggestRadius) {
					biggestRadius = r;
				}
			}
		}
		float maxWidth, maxLength, minX = 0, maxX = 0, minZ = 0, maxZ = 0;
		int i = 0;
		for (CartesianPoint p : bottom.getFaceOuterBound().getAllPoints()) {
			if (i++ == 0) {
				minX = p.getX();
				maxX = p.getX();
				minZ = p.getZ();
				maxZ = p.getZ();
			}
			if (p.getX() < minX) {
				minX = p.getX();
			} else if (p.getX() > maxX) {
				maxX = p.getX();
			}
			if (p.getZ() < minZ) {
				minZ = p.getZ();
			} else if (p.getZ() > maxZ) {
				maxZ = p.getZ();
			}
		}
		maxWidth = maxZ - minZ;
		maxLength = maxX - minX;
		biggestRadius *= 2;
		/*
		 * System.out.println(minX + " " + circleStart.getX() + " " +
		 * circleEnd.getX() + " " + maxX); System.out.println(minZ + " " +
		 * circleStart.getZ() + " " + circleEnd.getZ() + " " + maxZ);
		 * System.out.println(maxLength + " " + biggestRadius);
		 */
		return maxWidth < biggestRadius && maxLength < biggestRadius;
	}

	private int getThirdDigitRotational(int cylinderCount, int firstDigit,
			int secondDigit) {
		/*
		 * if (cylinderCount == 1 || cylinderCount == 2) { if
		 * (cs.getThroughHolesCount() != 0) { print("inner bore is found");
		 * return 1; } } else if (cylinderCount == 3) { if
		 * (cs.getThroughHolesCount() != 0) { print("inner bore is found");
		 * return 4; } }
		 */
		int thirdDigit = -1;
		if (firstDigit < 3) {
			if (cs.getThroughHolesCount() == 0) {
				print("Without through bore - Blind hole");
				thirdDigit = 0;
			} else if (secondDigit < 4 && secondDigit >= 0) {
				// Stepped to one end or smooth
				print("Smooth or stepped to one end");
				if (cs.hasInternalScrewThread()) {
					print("With screw thread");
					thirdDigit = 2;
				} else if (this.hasGroove(true, cs)) {
					print("With functional groove");
					thirdDigit = 3;
				} else {
					print("No shape elements");
					thirdDigit = 1;
				}
			} else if (secondDigit < 7) {
				// Stepped to both ends - multiple increases
				print("Stepped to both ends (Multiple increases)");
				if (cs.hasInternalScrewThread()) {
					print("With screw thread");
					thirdDigit = 5;
				} else if (this.hasGroove(true, cs)) {
					print("With functional groove");
					thirdDigit = 6;
				} else {
					print("No shape elements");
					thirdDigit = 4;
				}
			} else {
				if (cs.getRotationPlane().getSurfGeometry() instanceof ConicalSurface) {
					print("Functional taper");
					thirdDigit = 7;
					// TODO: Threshold in preferences pane.
				} else if (cs.hasInternalOperatingThread(this.pref
						.getThresholds()[3])) {
					print("Operating thread");
					thirdDigit = 8;
				} else {
					print("Others ( > 10 functional diameters)");
					thirdDigit = 9;
				}
			}
		} else if (firstDigit < 5) {
			if ((this.getExternMachinigRotational(cylinderCount) != 0)
					&& (cs.hasInternalOperatingThread(0.9) || cs
							.hasInternalScrewThread())) {
				print("Ext. and int. shape...");
				if (cs.hasExternalScrewThread() || cs.hasInternalScrewThread()) {
					thirdDigit = 7;
					print("With screwthread(s)");
				} else {
					thirdDigit = 6;
					print("Machined");
				}
				// TODO: Preferences pane threshold.
			} else if (cs
					.hasInternalOperatingThread(this.pref.getThresholds()[3])
					|| cs.hasInternalScrewThread()) {
				print("Internal shape...");
				if (cs.hasInternalScrewThread()) {
					thirdDigit = 5;
					print("With screwthread(s)");
				} else {
					thirdDigit = 3;
					print("Smooth");
				}
			} else if (this.getExternMachinigRotational(cylinderCount) != 0) {
				print("External shape...");
				if (cs.hasExternalScrewThread()) {
					thirdDigit = 2;
					print("With screwthread(s)");
				} else {
					thirdDigit = 1;
					print("Machined");
				}
			} else if (this.getExternMachinigRotational(cylinderCount) != 0) {
				thirdDigit = 8;
				print("External shape elements");
			} else {
				thirdDigit = 9;
				print("Other shape elements");
			}
		} else {
			return 0;
		}
		// print("no inner shape is found");
		return thirdDigit;
	}

	private int getExternMachinigRotational(int cylinderCount) {
		if (hasGroove(true, cs)) {
			// print("machining: external groove is found");
			if (cylinderCount == 1 || cylinderCount == 2) {
				return 3;
			} else if (cylinderCount >= 3) {
				return 6;
			}
		} else {
			if (cylinderCount == 2) {
				// print("no external machining");
				return 1;
			} else if (cylinderCount >= 3) {
				// print("no external machining");
				return 4;
			}
		}
		return 0;
	}

	public boolean hasGroove(boolean isRotational, ClosedShell cs) {
		if (isRotational) {
			for (AdvancedFace af : cs
					.getCylindricalSurfacesWithoutThroughHoles()) {
				for (FaceBound fb : af.getFaceInnerBound()) {
					if (!fb.isCircle()) {
						return true;
					}
				}
			}
		} else {
			if (cs.getTopPlane() != null) {
				for (FaceBound fb : cs.getTopPlane().getFaceInnerBound()) {
					if (!fb.isCircle()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private int getFourthDigit() {
		int fourthDigit = -1;
		if (cs.getTopPlane() == null) {
			return 0;
		}
		int k = cs.getYOrientedPlaneFacesCount();
		boolean isGroove = false;
		if (hasGroove(false, cs)) {
			isGroove = true;
		}
		if (cs.hasUpperMachining()) {
			fourthDigit = 7;
			print("Machining: curved surface");
		} else if (k == 2) {
			if (cs.getTopPlane().getFaceOuterBound().hasTopChamfers()) {
				fourthDigit = 1;
				print("Machining: has chambers");
			} else if (isGroove) {
				fourthDigit = 5;
				print("Machining: external groove is found");
			} else {
				fourthDigit = 0;
				print("Machining: no machining");
			}
		} else if (k == 3) {
			if (!isGroove) {
				fourthDigit = 2;
				print("Machining: stepped 2");
			} else {
				fourthDigit = 6;
				print("Machining: stepped 2 + groove");
			}
		} else if (k > 3) {
			if (!isGroove) {
				fourthDigit = 3;
				print("Machining: stepped > 2");
			} else {
				fourthDigit = 6;
				print("Machining: stepped 2 + groove");
			}
		} else {
			fourthDigit = 0;
			print("Machining: no machining");
		}
		return fourthDigit;
	}

	private int getThirdDigitNonRotational(AdvancedFace bottom) {
		int thirdDigit = -1;
		int innerHoles = cs.getThroughHolesCount();
		if (innerHoles == 0) {
			thirdDigit = 0;
			print("Inner shape: no principal bores");
		} else if (innerHoles == 1) {
			thirdDigit = 1;
			print("One principal bore");
		} else if (innerHoles == 2) {
			thirdDigit = 4;
			print("Two principal bores, parallel");
		} else if (innerHoles > 2) {
			thirdDigit = 5;
			print("Several principal bores, parallel");
		} else if (getExternMachinigRotational(1) != 0 && innerHoles == 1) {
			thirdDigit = 2;
			print("One principal bore, stepped to one or both ends");
		} else if (innerHoles > 6) {
			thirdDigit = 8;
			print("7+ principal bore(s)");
		} else {
			thirdDigit = 9;
			print("Others");
		}
		return thirdDigit;
	}

	private int getFifthDigit(int firstDigit) {
		int result = 5;
		// If there are no holes and no teeth.
		boolean teethExist = cs.teethExist(this.pref.getThresholds()[7]);
		if (cs.getHoleCount() == 0 && !teethExist) {
			return 0;
		} else {
			// TODO: Put thresholds in preferences frame.
			boolean holesEquidistant = cs.areHolesEquidistant(this.pref
					.getThresholds()[4]);
			boolean holesEvenlyDistributed = cs.areHolesEvenlyDistributed(
					absoluteCenter, this.pref.getThresholds()[5]);
			boolean holesSameOrientation = cs
					.holesHaveSameOrientation(this.pref.getThresholds()[6]);
			boolean holesRadial = cs.areHolesRadial();
			boolean holesAxial = cs.areHolesAxial();
			boolean teethParallel = false;
			String teethGeometry = "";
/*			System.out.println("Equidistance is " + holesEquidistant);
			System.out.println("Equidistribution is " + holesEvenlyDistributed);
			System.out
					.println("Similar Orientation is " + holesSameOrientation);
			System.out.println("Radiality is " + holesRadial);
			System.out.println("Axiality is " + holesAxial);
			System.out.println("Teeth Existence is " + teethExist);*/
			if (firstDigit < 3) {
				teethParallel = cs.teethParalleltoRotationAxis(
						this.pref.getThresholds()[8], 0.006);
				teethGeometry = cs.getTeethGeometry();
	/*			System.out.println("Teeth Parallelism is " + teethParallel);
				System.out.println("Teeth Geometry is " + teethGeometry);*/
			}
			result = 1;
			print("Holes found: " + cs.getHoleCount());
			if (firstDigit < 3) {
				if (!teethExist) {
					print("No teeth found.");
					if (!holesEquidistant && !holesEvenlyDistributed
							&& holesAxial && !holesRadial) {
						print("Axial holes, not equally distributed.");
						return 1;
					} else if (holesEquidistant && holesEvenlyDistributed
							&& holesAxial && !holesRadial) {
						print("Axial holes, equally distributed.");
						return 2;
					} else if (!holesEquidistant && !holesEvenlyDistributed
							&& holesRadial && !holesAxial) {
						print("Radial holes, not equally distributed.");
						return 3;
					} else if (!holesEquidistant
							&& !holesEvenlyDistributed
							&& (holesRadial || holesAxial || holesSameOrientation)) {
						print("Unknown different orientations, not equally distributed.");
						return 4;
					} else if (holesEquidistant
							&& holesEvenlyDistributed
							&& (holesRadial || holesAxial || holesSameOrientation)) {
						print("Unknown similar orienation, equally distributed.");
						return 5;
					}
				} else {
					print("Teeth found.");
					if (teethParallel
							&& (!teethGeometry.equalsIgnoreCase("CIRCLE") && !teethGeometry
									.equalsIgnoreCase("OTHER"))) {
						print("Teeth parallel to the rotation axis.");
						return 6;
					} else if (!teethParallel
							&& (!teethGeometry.equalsIgnoreCase("CIRCLE") && !teethGeometry
									.equalsIgnoreCase("OTHER"))) {
						print("Teeth are conically oriented to the rotation axis.");
						return 7;
					} else {
						print("Teeth have a non-defined orientation to the rotation axis. ");
						return 8;
					}
				}
			} else if (firstDigit < 5 && firstDigit > 2) {
				if (!teethExist) {
					print("No teeth.");
					if (!holesEquidistant && !holesEvenlyDistributed
							&& holesAxial && !holesRadial) {
						print("Axial holes, not equally distributed.");
						return 1;
					} else if (!holesEquidistant
							&& !holesEvenlyDistributed
							&& (holesAxial || holesRadial || holesSameOrientation)) {
						print("Unknown or radial orientation, not equally distributed.");
						return 2;
					} else if (holesEquidistant && holesEvenlyDistributed
							&& holesAxial && !holesRadial) {
						print("Axial holes, equally distributed.");
						return 3;
					} else if (holesEquidistant
							&& holesEvenlyDistributed
							&& (holesAxial || holesRadial || holesSameOrientation)) {
						print("Unknown or radial orientation, equally distributed.");
						return 4;
					} else if (cs.getHoleCount() == 0) {
						print("No holes found.");
						return 5;
					} else if (cs.getHoleCount() > 0) {
						print("Holes found.");
						return 6;
					}
				} else {
					if (cs.getHoleCount() == 0) {
						print("Teeth found, no holes.");
						return 7;
					} else if (cs.getHoleCount() > 0) {
						print("Teeth found, holes found.");
						return 8;
					} else {
						print("Miscellaneous case.");
						return 9;
					}
				}
			} else if (firstDigit == 5 || firstDigit == 9) {
				// Custom case.
				if (!teethExist) {
					if (cs.getHoleCount() != 0) {
						if (!holesEquidistant && !holesEvenlyDistributed) {
							print("Hole pattern does not exist.");
							if (holesSameOrientation) {
								print("Holes similarly oriented.");
								return 1;
							} else {
								print("Holes not similarly oriented.");
								return 2;
							}
						} else if (holesEquidistant || holesEvenlyDistributed) {
							print("Hole pattern exists.");
							if (holesSameOrientation) {
								print("Holes similarly oriented.");
								return 3;
							} else {
								print("Holes not similarly oriented.");
								return 4;
							}
						}
						return 6;
					} else {
						return 5;
					}
				} else {
					if (cs.getHoleCount() == 0) {
						print("Teeth found, no holes.");
						return 7;
					} else if (cs.getHoleCount() != 0) {
						print("Teeth found, holes found.");
						return 8;
					} else {
						print("Miscellaneous case.");
						return 9;
					}
				}
			} else if (firstDigit == 5 || firstDigit == 9) {
				// Custom case.
				print("This is a custom case.");
				return firstDigit;
			} else {
				// Default case:
				// print("Undocumented first digit case.");
				System.out.println("Undocumented first digit case.");
			}
		}
		return result;
	}

	/**
	 * Gets the model's corresponding sixth digit according to the Opitz code.
	 * 
	 * @return An integer containing the sixth digit corresponding to the model.
	 */
	private int getSixthDigit(int firstDigit) {
		double measure = -1;
		if (firstDigit < 5) {
			measure = this.m.maxWidth;
			this.print("Rotational: using diameter as 'measure' at a value of "
					+ measure);
		} else {
			measure = this.m.maxLength;
			this.print("Non-Rotational: using length as 'measure' at a value of "
					+ measure);
		}
		if (measure <= 20 || measure <= 0.8) {
			return 0;
		} else if (measure <= 50 || measure <= 2) {
			return 1;
		} else if (measure <= 100 || measure <= 4) {
			return 2;
		} else if (measure <= 160 || measure <= 6.5) {
			return 3;
		} else if (measure <= 250 || measure <= 10) {
			return 4;
		} else if (measure <= 400 || measure <= 16) {
			return 5;
		} else if (measure <= 600 || measure <= 25) {
			return 6;
		} else if (measure <= 1000 || measure <= 40) {
			return 7;
		} else if (measure <= 2000 || measure <= 80) {
			return 8;
		} else {
			return 9;
		}
	}

	/**
	 * Gets the model's corresponding seventh digit according to the Opitz code.
	 * 
	 * @return An integer containing the seventh digit corresponding to the
	 *         model.
	 */
	private int getSeventhDigit(Material material) {
		return material.getDigit();
	}

	/**
	 * Gets the model's corresponding eighth digit according to the Opitz code.
	 * 
	 * @return An integer containing the eighth digit corresponding to the
	 *         model.
	 */
	private int getEighthDigit(InitForm initialForm) {
		return initialForm.getDigit();
	}

	/**
	 * Gets the model's corresponding ninth digit according to the Opitz code.
	 * 
	 * @return An integer containing the ninth digit corresponding to the model.
	 */
	private int getNinthDigit() {
		boolean d2 = this.pref.checkDefault(1) && this.pref.checkDefault(2);
		boolean d3 = this.pref.checkDefault(3);
		boolean d4 = this.pref.checkDefault(9);
		boolean d5 = this.pref.checkDefault(4) && this.pref.checkDefault(5)
				&& this.pref.checkDefault(6) && this.pref.checkDefault(7)
				&& this.pref.checkDefault(8);
		if (d2 && d3 && d4 && d5) {
			print("Accuracy: Full");
			return 9;
		} else if (d3 && d4) {
			print("Accuracy: 3 and 4");
			return 8;
		} else if (d2 && d5) {
			print("Accuracy: 2 and 5");
			return 7;
		} else if (d2 && d4) {
			print("Accuracy: 2 and 4");
			return 6;
		} else if (d2 && d3) {
			print("Accuracy: 2 and 3");
			return 5;
		} else if (d5) {
			print("Accuracy: 5");
			return 4;
		} else if (d4) {
			print("Accuracy: 4");
			return 3;
		} else if (d3) {
			print("Accuracy: 3");
			return 2;
		} else if (d2) {
			print("Accuracy: 2");
			return 1;
		} else {
			print("Accuracy: None specified");
			return 0;
		}
	}

	/**
	 * Gets the absolute center of the model.
	 *
	 * @param measures
	 *            The object that contains the maximum and minimum measurements
	 *            of the model.
	 * @return A CartesianPoint object that represents the center.
	 */
	private CartesianPoint getAbsoluteCenter(MaxMeasures measures) {
		float x = ((measures.maxX - measures.minX) / 2) + measures.minX;
		float y = ((measures.maxY - measures.minY) / 2) + measures.minY;
		float z = ((measures.maxZ - measures.minZ) / 2) + measures.minZ;
		return new CartesianPoint(x, y, z);
	}

	private void print(String s) {
		if (isTest) {
			return;
		}
		// System.out.println(s);
		trace.add(s);
	}

	/**
	 * Clears all information stored in this object.
	 */
	private void clearAll() {
		this.trace.clear();
		this.showGUI = true;
		this.cs = null;
		this.isTest = true;
		this.m = null;
		this.absoluteCenter = null;
		this.code = "";
		this.pref = null;
	}

	/**
	 * Getter for the log information.
	 * 
	 * @return A List of String objects that contains a step-by-step account of
	 *         the performed operations.
	 */
	public List<String> getTrace() {
		return this.trace;
	}

	/**
	 * Getter for the code.
	 * 
	 * @return A string containing the Opitz code of the model.
	 */
	public String getCode() {
		return this.code;
	}

	/*
	 * public Recognizer() { JPanel p = new JPanel(); open.addActionListener(new
	 * OpenL()); p.add(open); Container cp = getContentPane(); cp.add(p,
	 * BorderLayout.SOUTH); filename.setEditable(false); p = new JPanel();
	 * p.setBorder(new EmptyBorder(10, 10, 10, 10)); p.setLayout(new
	 * BoxLayout(p, BoxLayout.PAGE_AXIS)); p.add(filename);
	 * p.add(Box.createVerticalStrut(5)); //extra space textArea = new
	 * JTextArea(8, 20); JScrollPane scrollPane = new JScrollPane(textArea);
	 * textArea.setEditable(false); p.add(scrollPane);
	 * p.add(Box.createVerticalStrut(5)); //extra space cp.add(p,
	 * BorderLayout.NORTH);
	 * 
	 * }
	 * 
	 * 
	 * class OpenL implements ActionListener {
	 * 
	 * public void actionPerformed(ActionEvent e) { JFileChooser c = new
	 * JFileChooser(); int rVal = c.showOpenDialog(Recognizer.this); if (rVal ==
	 * JFileChooser.APPROVE_OPTION) { String path =
	 * c.getSelectedFile().getAbsolutePath(); filename.setText(path);
	 * mainProcedure(path, false); textArea.setText(""); for (String s : trace)
	 * { textArea.append(s); textArea.append("\n"); } trace.clear();
	 * CommonUtils.clearMaps(); } if (rVal == JFileChooser.CANCEL_OPTION) {
	 * filename.setText("You pressed cancel"); } } }
	 * 
	 * public static void main(String[] arr) { if (showGUI) { run(new
	 * Recognizer(), 450, 250); } else { mainProcedure(null, false); } }
	 * 
	 * public static void run(JFrame frame, int width, int height) {
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * frame.setSize(width, height); frame.setVisible(true);
	 * frame.setTitle("ISO 10303 STEP Tracer"); Dimension dim =
	 * Toolkit.getDefaultToolkit().getScreenSize(); frame.setLocation(dim.width
	 * / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height
	 * / 2); }
	 * 
	 * private JTextField filename = new JTextField(); private JTextArea
	 * textArea = new JTextArea(); private JButton open = new
	 * JButton("Open STEP File");
	 */
}
