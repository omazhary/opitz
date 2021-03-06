package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import utils.Preferences;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class SettingsFrame extends JFrame {

	private JPanel contentPane;
	private Preferences pref;
	private JSlider sliderD1;
	private JSlider sliderD2;
	private JSlider sliderD3;
	private JSlider sliderD4;
	private JSlider sliderD5;
	private JSlider sliderD6;
	private JSlider sliderD7;
	private JSlider sliderD8;
	private JSlider sliderD9;
	private JSlider sldRotDev;
	private JSlider sldNonRotDev;
	private JSlider sldSAS;
	private JSlider sldIOTS;
	private JSlider sldHED;
	private JSlider sldHDST;
	private JSlider sldHOS;
	private JSlider sldTDS;
	private JSlider sldTPS;
	private JSlider sldPSM;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsFrame frame = new SettingsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public SettingsFrame(Preferences preferences) {
		setResizable(false);
		setTitle("Advanced Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.pref = preferences;
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(110, 110, 635, 400);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelSim = new JPanel();
		tabbedPane.addTab("Similarity", null, panelSim, null);
		tabbedPane.setEnabledAt(0, true);
		
		JLabel lblWeightVector = new JLabel("Weight Vector:");
		lblWeightVector.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sliderD9 = new JSlider();
		sliderD9.setMinimum(1);
		sliderD9.setMaximum(5);
		sliderD9.setFont(new Font("Verdana", Font.PLAIN, 12));
		sliderD9.setPaintTicks(true);
		sliderD9.setPaintLabels(true);
		sliderD9.setMajorTickSpacing(1);
		sliderD9.setValue(0);
		sliderD9.setOrientation(SwingConstants.VERTICAL);
		
		sliderD8 = new JSlider();
		sliderD8.setMinimum(1);
		sliderD8.setMaximum(5);
		sliderD8.setFont(new Font("Verdana", Font.PLAIN, 12));
		sliderD8.setPaintLabels(true);
		sliderD8.setPaintTicks(true);
		sliderD8.setMajorTickSpacing(1);
		sliderD8.setValue(0);
		sliderD8.setOrientation(SwingConstants.VERTICAL);
		
		sliderD7 = new JSlider();
		sliderD7.setMinimum(1);
		sliderD7.setMaximum(5);
		sliderD7.setOrientation(SwingConstants.VERTICAL);
		sliderD7.setValue(0);
		sliderD7.setPaintTicks(true);
		sliderD7.setPaintLabels(true);
		sliderD7.setMajorTickSpacing(1);
		sliderD7.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sliderD6 = new JSlider();
		sliderD6.setMinimum(1);
		sliderD6.setMaximum(5);
		sliderD6.setValue(0);
		sliderD6.setPaintTicks(true);
		sliderD6.setPaintLabels(true);
		sliderD6.setOrientation(SwingConstants.VERTICAL);
		sliderD6.setMajorTickSpacing(1);
		sliderD6.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblAuxillaryDigitsWeights = new JLabel("Auxillary Digit Weights");
		lblAuxillaryDigitsWeights.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sliderD1 = new JSlider();
		sliderD1.setMinimum(1);
		sliderD1.setMaximum(5);
		sliderD1.setValue(0);
		sliderD1.setPaintTicks(true);
		sliderD1.setPaintLabels(true);
		sliderD1.setOrientation(SwingConstants.VERTICAL);
		sliderD1.setMajorTickSpacing(1);
		sliderD1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sliderD2 = new JSlider();
		sliderD2.setMinimum(1);
		sliderD2.setMaximum(5);
		sliderD2.setValue(0);
		sliderD2.setPaintTicks(true);
		sliderD2.setPaintLabels(true);
		sliderD2.setOrientation(SwingConstants.VERTICAL);
		sliderD2.setFont(new Font("Verdana", Font.PLAIN, 12));
		sliderD2.setMajorTickSpacing(1);
		
		sliderD3 = new JSlider();
		sliderD3.setMinimum(1);
		sliderD3.setValue(0);
		sliderD3.setPaintTicks(true);
		sliderD3.setPaintLabels(true);
		sliderD3.setOrientation(SwingConstants.VERTICAL);
		sliderD3.setMaximum(5);
		sliderD3.setMajorTickSpacing(1);
		sliderD3.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sliderD4 = new JSlider();
		sliderD4.setMinimum(1);
		sliderD4.setValue(0);
		sliderD4.setPaintTicks(true);
		sliderD4.setPaintLabels(true);
		sliderD4.setOrientation(SwingConstants.VERTICAL);
		sliderD4.setMaximum(5);
		sliderD4.setMajorTickSpacing(1);
		sliderD4.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sliderD5 = new JSlider();
		sliderD5.setMinimum(1);
		sliderD5.setValue(0);
		sliderD5.setPaintTicks(true);
		sliderD5.setPaintLabels(true);
		sliderD5.setOrientation(SwingConstants.VERTICAL);
		sliderD5.setMaximum(5);
		sliderD5.setMajorTickSpacing(1);
		sliderD5.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblFormDigitWeights = new JLabel("Form Digit Weights");
		lblFormDigitWeights.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel labelD1 = new JLabel("1");
		labelD1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label = new JLabel("2");
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_1 = new JLabel("3");
		label_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_2 = new JLabel("4");
		label_2.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_3 = new JLabel("5");
		label_3.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_4 = new JLabel("6");
		label_4.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_5 = new JLabel("7");
		label_5.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_6 = new JLabel("8");
		label_6.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_7 = new JLabel("9");
		label_7.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnSave = new JButton("Save Weights");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer[] weights = new Integer[9];
				weights[0] = new Integer(sliderD1.getValue());
				weights[1] = new Integer(sliderD2.getValue());
				weights[2] = new Integer(sliderD3.getValue());
				weights[3] = new Integer(sliderD4.getValue());
				weights[4] = new Integer(sliderD5.getValue());
				weights[5] = new Integer(sliderD6.getValue());
				weights[6] = new Integer(sliderD7.getValue());
				weights[7] = new Integer(sliderD8.getValue());
				weights[8] = new Integer(sliderD9.getValue());
				pref.setWeightVector(weights);
			}
		});
		btnSave.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelSim = new GroupLayout(panelSim);
		gl_panelSim.setHorizontalGroup(
			gl_panelSim.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelSim.createSequentialGroup()
					.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSim.createSequentialGroup()
							.addGap(12)
							.addComponent(lblWeightVector))
						.addGroup(gl_panelSim.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSim.createSequentialGroup()
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addComponent(sliderD1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(12)
											.addComponent(labelD1)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFormDigitWeights)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(4)
											.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
												.addComponent(sliderD2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panelSim.createSequentialGroup()
													.addGap(10)
													.addComponent(label)))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelSim.createSequentialGroup()
													.addGap(10)
													.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE))
												.addComponent(sliderD3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelSim.createSequentialGroup()
													.addGap(10)
													.addComponent(label_2))
												.addComponent(sliderD4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
									.addGap(18)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addComponent(sliderD5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(10)
											.addComponent(label_3)))
									.addGap(77)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addComponent(sliderD6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(12)
											.addComponent(label_4)))
									.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addComponent(lblAuxillaryDigitsWeights)
											.addGap(27))
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(10)
											.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
												.addComponent(sliderD7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panelSim.createSequentialGroup()
													.addGap(12)
													.addComponent(label_5)))
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
												.addComponent(sliderD8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panelSim.createSequentialGroup()
													.addGap(10)
													.addComponent(label_6)))
											.addGap(18)
											.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelSim.createSequentialGroup()
													.addGap(10)
													.addComponent(label_7))
												.addComponent(sliderD9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(Alignment.TRAILING, gl_panelSim.createSequentialGroup()
									.addComponent(btnSave)
									.addGap(18)
									.addComponent(btnClose)
									.addGap(330)))
							.addGap(75)))
					.addContainerGap())
		);
		gl_panelSim.setVerticalGroup(
			gl_panelSim.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSim.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelSim.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelSim.createSequentialGroup()
							.addComponent(lblWeightVector)
							.addGap(10)
							.addComponent(lblFormDigitWeights)
							.addGap(18)
							.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelD1))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_2))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label))))
						.addGroup(gl_panelSim.createSequentialGroup()
							.addComponent(lblAuxillaryDigitsWeights)
							.addGap(18)
							.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_7))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addGroup(gl_panelSim.createParallelGroup(Alignment.BASELINE)
										.addComponent(sliderD6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(sliderD7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_4)
										.addComponent(label_5)))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_6)))))
					.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
					.addGroup(gl_panelSim.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnClose))
					.addContainerGap())
		);
		panelSim.setLayout(gl_panelSim);
		
		JPanel panelRec = new JPanel();
		tabbedPane.addTab("Recognition", null, panelRec, null);
		
		JButton btnClose_1 = new JButton("Close");
		btnClose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnSave_1 = new JButton("Save Thresholds");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pref.setThreshold(0, sldRotDev.getValue());
				pref.setThreshold(1, sldNonRotDev.getValue());
				pref.setThreshold(2, sldSAS.getValue());
				pref.setThreshold(3, sldIOTS.getValue());
				pref.setThreshold(4, sldHED.getValue());
				pref.setThreshold(5, sldHDST.getValue());
				pref.setThreshold(6, sldHOS.getValue());
				pref.setThreshold(7, sldTDS.getValue());
				pref.setThreshold(8, sldTPS.getValue());
				pref.setThreshold(9, sldPSM.getValue());
			}
		});
		btnSave_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblDeviationDetectionSensitivity = new JLabel("Rotational Deviation Detection Sensitivity:");
		lblDeviationDetectionSensitivity.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldRotDev = new JSlider();
		sldRotDev.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblNonrotationalDeviationDetection = new JLabel("Non-Rotational Deviation Detection Sensitivity:");
		lblNonrotationalDeviationDetection.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldNonRotDev = new JSlider();
		sldNonRotDev.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblShapeAxisStraightness = new JLabel("Shape Axis Straightness:");
		lblShapeAxisStraightness.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldSAS = new JSlider();
		sldSAS.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblInternalOperatingThread = new JLabel("Internal Operating Thread Sensitivity:");
		lblInternalOperatingThread.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldIOTS = new JSlider();
		sldIOTS.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblHoleEquidistanceSensitivity = new JLabel("Hole Equidistance Sensitivity:");
		lblHoleEquidistanceSensitivity.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldHED = new JSlider();
		sldHED.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblHoleDistributionSensitivity = new JLabel("Hole Distribution Sensitivity:");
		lblHoleDistributionSensitivity.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldHDST = new JSlider();
		sldHDST.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblHoleOrientationSensitivity = new JLabel("Hole Orientation Sensitivity:");
		lblHoleOrientationSensitivity.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldHOS = new JSlider();
		sldHOS.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblTeethDetectionSensitivity = new JLabel("Teeth Detection Sensitivity:");
		lblTeethDetectionSensitivity.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldTDS = new JSlider();
		sldTDS.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblTeethParallelismSensitivity = new JLabel("Teeth Parallelism Sensitivity:");
		lblTeethParallelismSensitivity.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldTPS = new JSlider();
		sldTPS.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblPlaneSurfaceMachining = new JLabel("Plane Surface Machining Sensitivity:");
		lblPlaneSurfaceMachining.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sldPSM = new JSlider();
		sldPSM.setPaintTicks(true);
		sldPSM.setMajorTickSpacing(20);
		sldPSM.setMinorTickSpacing(10);
		sldPSM.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_8 = new JLabel("0");
		label_8.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel label_9 = new JLabel("1");
		label_9.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelRec = new GroupLayout(panelRec);
		gl_panelRec.setHorizontalGroup(
			gl_panelRec.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRec.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRec.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panelRec.createSequentialGroup()
							.addComponent(btnSave_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClose_1))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addComponent(lblShapeAxisStraightness, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sldSAS, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNonrotationalDeviationDetection, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDeviationDetectionSensitivity))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING, false)
								.addComponent(sldRotDev, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(sldNonRotDev, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addComponent(lblInternalOperatingThread, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sldIOTS, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addComponent(lblHoleEquidistanceSensitivity, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sldHED, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addComponent(lblHoleDistributionSensitivity, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sldHDST, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addComponent(lblHoleOrientationSensitivity, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sldHOS, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addGroup(gl_panelRec.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTeethDetectionSensitivity, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTeethParallelismSensitivity, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelRec.createParallelGroup(Alignment.TRAILING)
								.addComponent(sldTDS, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
								.addComponent(sldTPS, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
						.addGroup(gl_panelRec.createSequentialGroup()
							.addComponent(lblPlaneSurfaceMachining, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelRec.createSequentialGroup()
									.addComponent(label_8)
									.addPreferredGap(ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE))
								.addComponent(sldPSM, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panelRec.setVerticalGroup(
			gl_panelRec.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelRec.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDeviationDetectionSensitivity)
						.addComponent(sldRotDev, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNonrotationalDeviationDetection, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldNonRotDev, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblShapeAxisStraightness, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldSAS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInternalOperatingThread, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldIOTS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHoleEquidistanceSensitivity, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldHED, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHoleDistributionSensitivity, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldHDST, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHoleOrientationSensitivity, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldHOS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTeethDetectionSensitivity, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldTDS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTeethParallelismSensitivity, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldTPS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPlaneSurfaceMachining, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(sldPSM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.LEADING)
						.addComponent(label_8)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
					.addGroup(gl_panelRec.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave_1)
						.addComponent(btnClose_1))
					.addContainerGap())
		);
		panelRec.setLayout(gl_panelRec);
		
		// Applying the preferences values:
		sldRotDev.setValue(this.pref.getDisplayThresholdByIndex(0));
		sldNonRotDev.setValue(this.pref.getDisplayThresholdByIndex(1));
		sldSAS.setValue(this.pref.getDisplayThresholdByIndex(2));
		sldIOTS.setValue(this.pref.getDisplayThresholdByIndex(3));
		sldHED.setValue(this.pref.getDisplayThresholdByIndex(4));
		sldHDST.setValue(this.pref.getDisplayThresholdByIndex(5));
		sldHOS.setValue(this.pref.getDisplayThresholdByIndex(6));
		sldTDS.setValue(this.pref.getDisplayThresholdByIndex(7));
		sldTPS.setValue(this.pref.getDisplayThresholdByIndex(8));
		sldPSM.setValue(this.pref.getDisplayThresholdByIndex(9));
		
		sliderD1.setValue(this.pref.getWeightVector()[0]);
		sliderD2.setValue(this.pref.getWeightVector()[1]);
		sliderD3.setValue(this.pref.getWeightVector()[2]);
		sliderD4.setValue(this.pref.getWeightVector()[3]);
		sliderD5.setValue(this.pref.getWeightVector()[4]);
		sliderD6.setValue(this.pref.getWeightVector()[5]);
		sliderD7.setValue(this.pref.getWeightVector()[6]);
		sliderD8.setValue(this.pref.getWeightVector()[7]);
		sliderD9.setValue(this.pref.getWeightVector()[8]);
		
	}
}
