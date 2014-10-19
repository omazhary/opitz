package main;

import entities.AdvancedFace;
import entities.CartesianPoint;
import entities.ClosedShell;
import entities.CylindricalSurface;
import entities.FaceBound;
import entities.FaceOuterBound;
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
import utils.StepFileReader;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;

    private static List<String> trace = new ArrayList<String>();
    private static boolean showGUI = true;
    private static ClosedShell cs;
    private static boolean isTest;
    private static MaxMeasures m;
    private static CartesianPoint absoluteCenter;

    public static String mainProcedure(String filePath, boolean isTest) {
        Main.isTest = isTest;
        print("-----start ");
        int firstDigit = -1, secondDigit = -1, thirdDigit = -1, fourthDigit = -1, fifthDigit = -1;
        StepFileReader sfr = new StepFileReader(filePath == null ? (CommonUtils._PATH_PRODUCTION + "50.03220-01/03220-01.stp") : filePath);
        cs = new ClosedShell(sfr.getClosedShellLineId());
        m = CartesianPointKeeper.getMaxShapeMeasures();
        absoluteCenter = getAbsoluteCenter(m);
        AdvancedFace bottom = cs.getBottomPlane();
        AdvancedFace front = cs.getFrontPlane();
        AdvancedFace back = cs.getBackPlane();
        int cylinders = cs.getCylindricalSurfacesWithoutThroughHoles().size();
        if (cylinders > 0
                && (bottom == null || (bottom != null && isBottomLessThanCircleOfCylinder(cs.getCylindricalSurfacesWithoutThroughHoles(), bottom)))) {
            // rotational
            print("rotational shape");
            double rat = m.maxLength / m.maxWidth;
            firstDigit = rat <= 0.5 ? 0 : (rat >= 0.5 && rat < 3) ? 1 : rat >= 3 ? 2 : -1;
            if (cylinders == 1) {
                print("one external cylinder");
            } else if (cylinders == 2) {
                print("two external cylinders");
            } else if (cylinders == 3) {
                print("three external cylinders");
            } else {
                print("more than 3 external cylinders");
            }
            secondDigit = getExternMachinigRotational(cylinders);
            thirdDigit = getThirdDigitRotational(cylinders);
            fourthDigit = 0;
        } else if (bottom != null) {
            // non rotational
            print("non rotational");
            if (m.maxLength / m.maxWidth <= 3 && m.maxLength / m.maxHeight >= 4) {
                // flat
                firstDigit = 6;
                print("flat");
                if (bottom.getFaceOuterBound().isRectangle()) {
                    print("bottom: rectangle");
                    if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                        secondDigit = 0;
                    }
                } else if (bottom.getFaceOuterBound().isTriangle()) {
                    print("bottom: triangle");
                    if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                        secondDigit = 1;
                    }
                } else if (bottom.getFaceOuterBound().isAllAnglesTheSame()) {
                    print("bottom: same angled");
                    if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                        secondDigit = 2;
                    }
                } else if (bottom.getFaceOuterBound().isCircularAndOrtogonal()) {
                    print("bottom: CircularAndOrtogonal");
                    if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                        secondDigit = 3;
                    }
                } else if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                    secondDigit = 4;
                } else {
                    secondDigit = 9;
                }
                if (0 <= secondDigit && secondDigit <= 9) {
                    fourthDigit = getFourthDigit();
                    thirdDigit = getThirdDigit(bottom);
                }
            } else if (m.maxLength / m.maxWidth <= 3 && m.maxLength / m.maxHeight < 4) {
                // cubic
                print("cubic");
                firstDigit = 8;
                if (bottom.getFaceOuterBound().isRectangle()) {
                    print("bottom: rectangle");
                    if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                        secondDigit = 0;
                    }
                } else if (bottom.getFaceOuterBound().isTriangle()) {
                    print("bottom: triangle");
                    if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                        secondDigit = 1;
                    }
                } else if (bottom.getFaceOuterBound().isRecangPrismatic()) {
                    print("bottom: rectangular prisms");
                    if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                        secondDigit = 2;
                    }
                } else if (bottom.getFaceOuterBound().areAdjacentsXZOriented()) {
                    print("bottom: other shape");
                    secondDigit = 5;
                }
                if (0 <= secondDigit && secondDigit <= 9) {
                    fourthDigit = getFourthDigit();
                    thirdDigit = getThirdDigit(bottom);
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
                        if (CommonUtils.arePlanesEqualAlongZ(frontFob, backFob)) {
                            if (frontFob.isRectangle() && backFob.isRectangle() && bottom.getFaceOuterBound().isRectangle()) {
                                print("front plane, back plane: uniform (equal) cross sections (rectangular)");
                                secondDigit = 0;
                                //fourthDigit = getFourthDigit();
                                //thirdDigit = getThirdDigit(bottom);
                            } else if (frontFob.isTriangle() && backFob.isTriangle() && frontFob.areAdjacentsXYOriented()
                                    && backFob.areAdjacentsXYOriented()) {
                                print("front plane, back plane: uniform (equal) cross sections (triangular)");
                                secondDigit = 1;
                            } else {
                                print("front plane, back plane: uniform (equal) cross sections");
                                secondDigit = 2;
                            }
                        } else {
                            if (frontFob.isRectangle() && backFob.isRectangle()) {
                                print("front plane, back plane: varying cross sections (rectangular)");
                                secondDigit = 3;
                            } else if (frontFob.isTriangle() && backFob.isTriangle()) {
                                print("front plane, back plane: varying cross sections (triangular)");
                                secondDigit = 4;
                            } else {
                                print("front plane, back plane: varying cross sections");
                                secondDigit = 5;
                            }
                        }
                        if (0 <= secondDigit && secondDigit <= 9) {
                            fourthDigit = getFourthDigit();
                            thirdDigit = getThirdDigit(bottom);
                        }
                    }
                }
            }
        }

        fifthDigit = getFifthDigit(firstDigit);

        String res = "" + firstDigit + secondDigit + thirdDigit + fourthDigit + fifthDigit;
        print("-----done: " + res);
        return res;
    }

    private static boolean isBottomLessThanCircleOfCylinder(List<AdvancedFace> cylinders, AdvancedFace bottom) {
        float biggestRadius = 0;
        for (AdvancedFace af : cylinders) {
            if (af.getSurfGeometry() instanceof CylindricalSurface && af.getSurfGeometry().getDirection().isYOriented()) {
                float r = ((CylindricalSurface) af.getSurfGeometry()).getRadius();
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
        /*System.out.println(minX + " " + circleStart.getX() + " " + circleEnd.getX() + " " + maxX);
         System.out.println(minZ + " " + circleStart.getZ() + " " + circleEnd.getZ() + " " + maxZ);
         System.out.println(maxLength + " " + biggestRadius);*/
        return maxWidth < biggestRadius
                && maxLength < biggestRadius;
    }

    private static int getThirdDigitRotational(int cylinderCount) {
        if (cylinderCount == 1 || cylinderCount == 2) {
            if (cs.getThroughHolesCount() != 0) {
                print("inner bore is found");
                return 1;
            }
        } else if (cylinderCount == 3) {
            if (cs.getThroughHolesCount() != 0) {
                print("inner bore is found");
                return 4;
            }
        }
        print("no inner shape is found");
        return 0;
    }

    private static int getExternMachinigRotational(int cylinderCount) {
        if (hasGroove(true, cs)) {
            print("machining: external groove is found");
            if (cylinderCount == 1 || cylinderCount == 2) {
                return 3;
            } else if (cylinderCount >= 3) {
                return 6;
            }
        } else {
            if (cylinderCount == 2) {
                print("no external machining");
                return 1;
            } else if (cylinderCount >= 3) {
                print("no external machining");
                return 4;
            }
        }
        print("no external machining");
        return 0;
    }

    public static boolean hasGroove(boolean isRotational, ClosedShell cs) {
        if (isRotational) {
            for (AdvancedFace af : cs.getCylindricalSurfacesWithoutThroughHoles()) {
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

    private static int getFourthDigit() {
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
            print("machining: curved surface");
        } else if (k == 2) {
            if (cs.getTopPlane().getFaceOuterBound().hasTopChamfers()) {
                fourthDigit = 1;
                print("machining: has chambers");
            } else if (isGroove) {
                fourthDigit = 5;
                print("machining: external groove is found");
            } else {
                fourthDigit = 0;
                print("machining: no machining");
            }
        } else if (k == 3) {
            if (!isGroove) {
                fourthDigit = 2;
                print("machining: stepped 2");
            } else {
                fourthDigit = 6;
                print("machining: stepped 2 + groove");
            }
        } else if (k > 3) {
            if (!isGroove) {
                fourthDigit = 3;
                print("machining: stepped > 2");
            } else {
                fourthDigit = 6;
                print("machining: stepped 2 + groove");
            }
        } else {
            fourthDigit = 0;
            print("machining: no machining");
        }
        return fourthDigit;
    }

    private static int getThirdDigit(AdvancedFace bottom) {
        int thirdDigit = -1;
        int innerHoles = cs.getThroughHolesCount();
        if (innerHoles == 0) {
            thirdDigit = 0;
            print("inner shape: no principal bores");
        } else if (innerHoles == 1) {
            thirdDigit = 1;
            print("one principal bore");
        } else if (innerHoles == 2) {
            thirdDigit = 4;
            print("two principal bores, parallel");
        } else if (innerHoles > 2) {
            thirdDigit = 5;
            print("several principal bores, parallel");
        }
        return thirdDigit;
    }

    private static int getFifthDigit(int firstDigit) {
        int result = 5;
        // If there are no holes and no teeth.
        boolean teethExist = cs.teethExist(0.6);
        if (cs.getHoleCount() == 0 && !teethExist) {
            return 0;
        } else {
            boolean holesEquidistant = cs.areHolesEquidistant(0.9);
            boolean holesEvenlyDistributed = cs.areHolesEvenlyDistributed(absoluteCenter, 0.9);
            boolean holesSameOrientation = cs.holesHaveSameOrientation(0.9);
            boolean holesRadial = cs.areHolesRadial();
            boolean holesAxial = cs.areHolesAxial();
            boolean teethParallel = cs.teethParalleltoRotationAxis(0.9, 0.006);
            String teethGeometry = cs.getTeethGeometry();
            System.out.println("Equidistance is " + holesEquidistant);
            System.out.println("Equidistribution is " + holesEvenlyDistributed);
            System.out.println("Similar Orientation is " + holesSameOrientation);
            System.out.println("Radiality is " + holesRadial);
            System.out.println("Axiality is " + holesAxial);
            System.out.println("Teeth Existence is " + teethExist);
            System.out.println("Teeth Parallelism is " + teethParallel);
            System.out.println("Teeth Geometry is " + teethGeometry);
            result = 1;
            print("Holes found: " + cs.getHoleCount());
            if (firstDigit < 3) {
                if (!teethExist) {
                    print("No teeth found.");
                    if (!holesEquidistant && !holesEvenlyDistributed && holesAxial && !holesRadial) {
                        print("Axial holes, not equally distributed.");
                        return 1;
                    } else if (holesEquidistant && holesEvenlyDistributed && holesAxial && !holesRadial) {
                        print("Axial holes, equally distributed.");
                        return 2;
                    } else if (!holesEquidistant && !holesEvenlyDistributed && holesRadial && !holesAxial) {
                        print("Radial holes, not equally distributed.");
                        return 3;
                    } else if (!holesEquidistant && !holesEvenlyDistributed && (holesRadial || holesAxial || holesSameOrientation)) {
                        print("Unknown different orientations, not equally distributed.");
                        return 4;
                    } else if (holesEquidistant && holesEvenlyDistributed && (holesRadial || holesAxial || holesSameOrientation)) {
                        print("Unknown similar orienation, equally distributed.");
                        return 5;
                    }
                } else {
                    print("Teeth found.");
                    if (teethParallel && (!teethGeometry.equalsIgnoreCase("CIRCLE") && !teethGeometry.equalsIgnoreCase("OTHER"))) {
                        print("Teeth parallel to the rotation axis.");
                        return 6;
                    } else if (!teethParallel && (!teethGeometry.equalsIgnoreCase("CIRCLE") && !teethGeometry.equalsIgnoreCase("OTHER"))) {
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
                    if (!holesEquidistant && !holesEvenlyDistributed && holesAxial && !holesRadial) {
                        print("Axial holes, not equally distributed.");
                        return 1;
                    } else if (!holesEquidistant && !holesEvenlyDistributed && (holesAxial || holesRadial || holesSameOrientation)) {
                        print("Unknown or radial orientation, not equally distributed.");
                        return 2;
                    } else if (holesEquidistant && holesEvenlyDistributed && holesAxial && !holesRadial) {
                        print("Axial holes, equally distributed.");
                        return 3;
                    } else if (holesEquidistant && holesEvenlyDistributed && (holesAxial || holesRadial || holesSameOrientation)) {
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
            		} else if(cs.getHoleCount() != 0) {
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
                print("Undocumented first digit case.");
            }
        }
        return result;
    }

    /**
     * Gets the absolute center of the model.
     *
     * @param measures The object that contains the maximum and minimum
     * measurements of the model.
     * @return A CartesianPoint object that represents the center.
     */
    private static CartesianPoint getAbsoluteCenter(MaxMeasures measures) {
        float x = ((measures.maxX - measures.minX) / 2) + measures.minX;
        float y = ((measures.maxY - measures.minY) / 2) + measures.minY;
        float z = ((measures.maxZ - measures.minZ) / 2) + measures.minZ;
        return new CartesianPoint(x, y, z);
    }

    private static void print(String s) {
        if (isTest) {
            return;
        }
        System.out.println(s);
        trace.add(s);
    }

    public Main() {
        JPanel p = new JPanel();
        open.addActionListener(new OpenL());
        p.add(open);
        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        filename.setEditable(false);
        p = new JPanel();
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.add(filename);
        p.add(Box.createVerticalStrut(5)); //extra space
        textArea = new JTextArea(8, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        p.add(scrollPane);
        p.add(Box.createVerticalStrut(5)); //extra space
        cp.add(p, BorderLayout.NORTH);
    }

    class OpenL implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            int rVal = c.showOpenDialog(Main.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                String path = c.getSelectedFile().getAbsolutePath();
                filename.setText(path);
                mainProcedure(path, false);
                textArea.setText("");
                for (String s : trace) {
                    textArea.append(s);
                    textArea.append("\n");
                }
                trace.clear();
                CommonUtils.clearMaps();
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
            }
        }
    }

    public static void main(String[] arr) {
        if (showGUI) {
            run(new Main(), 450, 250);
        } else {
            mainProcedure(null, false);
        }
    }

    public static void run(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setTitle("ISO 10303 STEP Tracer");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private JTextField filename = new JTextField();
    private JTextArea textArea = new JTextArea();
    private JButton open = new JButton("Open STEP File");

}
