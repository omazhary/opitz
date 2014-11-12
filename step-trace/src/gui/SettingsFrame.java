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

public class SettingsFrame extends JFrame {

	private JPanel contentPane;

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
	public SettingsFrame() {
		setResizable(false);
		setTitle("Advanced Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelRec = new JPanel();
		tabbedPane.addTab("Recognition", null, panelRec, null);
		
		JPanel panelSim = new JPanel();
		tabbedPane.addTab("Similarity", null, panelSim, null);
		tabbedPane.setEnabledAt(1, true);
		
		JLabel lblWeightVector = new JLabel("Weight Vector:");
		lblWeightVector.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JSlider sliderD9 = new JSlider();
		sliderD9.setMaximum(10);
		sliderD9.setFont(new Font("Verdana", Font.PLAIN, 12));
		sliderD9.setPaintTicks(true);
		sliderD9.setPaintLabels(true);
		sliderD9.setMajorTickSpacing(2);
		sliderD9.setMinorTickSpacing(1);
		sliderD9.setValue(1);
		sliderD9.setOrientation(SwingConstants.VERTICAL);
		
		JSlider sliderD8 = new JSlider();
		sliderD8.setMaximum(10);
		sliderD8.setFont(new Font("Verdana", Font.PLAIN, 12));
		sliderD8.setPaintLabels(true);
		sliderD8.setPaintTicks(true);
		sliderD8.setMajorTickSpacing(2);
		sliderD8.setMinorTickSpacing(1);
		sliderD8.setValue(1);
		sliderD8.setOrientation(SwingConstants.VERTICAL);
		
		JSlider sliderD7 = new JSlider();
		sliderD7.setMaximum(10);
		sliderD7.setOrientation(SwingConstants.VERTICAL);
		sliderD7.setValue(1);
		sliderD7.setPaintTicks(true);
		sliderD7.setPaintLabels(true);
		sliderD7.setMinorTickSpacing(1);
		sliderD7.setMajorTickSpacing(2);
		sliderD7.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JSlider sliderD6 = new JSlider();
		sliderD6.setMaximum(10);
		sliderD6.setValue(1);
		sliderD6.setPaintTicks(true);
		sliderD6.setPaintLabels(true);
		sliderD6.setOrientation(SwingConstants.VERTICAL);
		sliderD6.setMinorTickSpacing(1);
		sliderD6.setMajorTickSpacing(2);
		sliderD6.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblAuxillaryDigitsWeights = new JLabel("Auxillary Digit Weights");
		lblAuxillaryDigitsWeights.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JSlider sliderD1 = new JSlider();
		sliderD1.setMaximum(10);
		sliderD1.setValue(1);
		sliderD1.setPaintTicks(true);
		sliderD1.setPaintLabels(true);
		sliderD1.setOrientation(SwingConstants.VERTICAL);
		sliderD1.setMinorTickSpacing(1);
		sliderD1.setMajorTickSpacing(2);
		sliderD1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JSlider sliderD2 = new JSlider();
		sliderD2.setMaximum(10);
		sliderD2.setValue(1);
		sliderD2.setPaintTicks(true);
		sliderD2.setPaintLabels(true);
		sliderD2.setOrientation(SwingConstants.VERTICAL);
		sliderD2.setMinorTickSpacing(1);
		sliderD2.setFont(new Font("Verdana", Font.PLAIN, 12));
		sliderD2.setMajorTickSpacing(2);
		
		JSlider sliderD3 = new JSlider();
		sliderD3.setValue(1);
		sliderD3.setPaintTicks(true);
		sliderD3.setPaintLabels(true);
		sliderD3.setOrientation(SwingConstants.VERTICAL);
		sliderD3.setMinorTickSpacing(1);
		sliderD3.setMaximum(10);
		sliderD3.setMajorTickSpacing(2);
		sliderD3.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JSlider sliderD4 = new JSlider();
		sliderD4.setValue(1);
		sliderD4.setPaintTicks(true);
		sliderD4.setPaintLabels(true);
		sliderD4.setOrientation(SwingConstants.VERTICAL);
		sliderD4.setMinorTickSpacing(1);
		sliderD4.setMaximum(10);
		sliderD4.setMajorTickSpacing(2);
		sliderD4.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JSlider sliderD5 = new JSlider();
		sliderD5.setValue(1);
		sliderD5.setPaintTicks(true);
		sliderD5.setPaintLabels(true);
		sliderD5.setOrientation(SwingConstants.VERTICAL);
		sliderD5.setMinorTickSpacing(1);
		sliderD5.setMaximum(10);
		sliderD5.setMajorTickSpacing(2);
		sliderD5.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblFormDigitWeights = new JLabel("Form Digit Weights");
		lblFormDigitWeights.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel labelD1 = new JLabel("1");
		labelD1.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelSim = new GroupLayout(panelSim);
		gl_panelSim.setHorizontalGroup(
			gl_panelSim.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSim.createSequentialGroup()
					.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSim.createSequentialGroup()
							.addGap(12)
							.addComponent(lblWeightVector))
						.addGroup(gl_panelSim.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSim.createSequentialGroup()
									.addGap(12)
									.addComponent(labelD1))
								.addGroup(gl_panelSim.createSequentialGroup()
									.addComponent(sliderD1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addComponent(sliderD2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(sliderD3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(sliderD4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblFormDigitWeights))
									.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panelSim.createSequentialGroup()
											.addGap(6)
											.addComponent(sliderD5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(103)
											.addComponent(sliderD6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(sliderD7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(sliderD8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(sliderD9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.TRAILING, gl_panelSim.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblAuxillaryDigitsWeights)
											.addGap(34)))))))
					.addContainerGap(57, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelSim.createParallelGroup(Alignment.LEADING)
						.addComponent(sliderD1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelSim.createParallelGroup(Alignment.BASELINE)
							.addComponent(sliderD2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sliderD3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sliderD4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sliderD5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sliderD6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sliderD7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sliderD8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(sliderD9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelD1)
					.addContainerGap(154, Short.MAX_VALUE))
		);
		panelSim.setLayout(gl_panelSim);
	}
}
