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
		
		JPanel panelRec = new JPanel();
		tabbedPane.addTab("Recognition", null, panelRec, null);
		
		JPanel panelSim = new JPanel();
		tabbedPane.addTab("Similarity", null, panelSim, null);
		tabbedPane.setEnabledAt(1, true);
		
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
		
		JButton btnSave = new JButton("Save");
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
							.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
							.addGroup(gl_panelSim.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelSim.createSequentialGroup()
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addComponent(sliderD6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(10)
											.addComponent(label_4)))
									.addGap(18)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addComponent(sliderD7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(10)
											.addComponent(label_5)))
									.addGap(16)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addComponent(sliderD8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(10)
											.addComponent(label_6)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(10)
											.addComponent(label_7))
										.addComponent(sliderD9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(lblAuxillaryDigitsWeights)
									.addGap(28)))))
					.addContainerGap(52, Short.MAX_VALUE))
				.addGroup(gl_panelSim.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(268))
		);
		gl_panelSim.setVerticalGroup(
			gl_panelSim.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSim.createSequentialGroup()
					.addGap(12)
					.addComponent(lblWeightVector)
					.addGap(10)
					.addGroup(gl_panelSim.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFormDigitWeights)
						.addComponent(lblAuxillaryDigitsWeights))
					.addGap(18)
					.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSim.createSequentialGroup()
							.addComponent(sliderD5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSim.createSequentialGroup()
							.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_5))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_4))
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
									.addComponent(label))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_7))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_6)))
							.addGap(30)
							.addComponent(btnSave)))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		panelSim.setLayout(gl_panelSim);
	}
}
