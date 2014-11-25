package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;

import entities.ExtShape;

public class OpitzCodeWizardFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private ButtonGroup btnQ1Group;
	private JRadioButton rdbtnQ1Yes;
	private JRadioButton rdbtnQ1No;
	private boolean answerQ1;
	private JTextField txtLength;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtDiameter;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxExtShape;
	private JLabel lblHeight;
	private JLabel lblWidth;
	private JLabel lblLength;
	private JLabel lblDiameter;
	private String code;
	private int[] code_int;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpitzCodeWizardFrame frame = new OpitzCodeWizardFrame();
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
	public OpitzCodeWizardFrame() {
		
		code_int = new int[9];
		
		setTitle("Opitz Code Recognition Wizard");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelWelcome = new JPanel();
		tabbedPane.addTab("Welcome", null, panelWelcome, null);
		tabbedPane.setEnabledAt(0, true);
		
		JLabel lblIconWizard = new JLabel("");
		lblIconWizard.setIcon(new ImageIcon(OpitzCodeWizardFrame.class.getResource("/icons/wizard.png")));
		
		JLabel lblNewLabel = new JLabel("<html>Welcome to the Opitz code <br />recognition Wizard!!</html>");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 26));
		
		JTextPane txtpnThisWizardWill = new JTextPane();
		txtpnThisWizardWill.setEditable(false);
		txtpnThisWizardWill.setBackground(UIManager.getColor("Button.background"));
		txtpnThisWizardWill.setText("This wizard will guide you through the steps to construct the Opitz\r\ngroup technology code for the part you'd like to search for.\r\n\r\nPlease attempt to answer as much of the questions as possible.\r\n\r\n(Unanswered questions decrease the generated Opitz code's\r\naccuracy, thereby decreasing your chances of finding a match!!)");
		txtpnThisWizardWill.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnToFirstDigit = new JButton("Start!!");
		btnToFirstDigit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnToFirstDigit.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelWelcome = new GroupLayout(panelWelcome);
		gl_panelWelcome.setHorizontalGroup(
			gl_panelWelcome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWelcome.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIconWizard, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelWelcome.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelWelcome.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnToFirstDigit)
							.addComponent(txtpnThisWizardWill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_panelWelcome.setVerticalGroup(
			gl_panelWelcome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWelcome.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelWelcome.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblIconWizard, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(txtpnThisWizardWill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
					.addComponent(btnToFirstDigit)
					.addContainerGap())
		);
		panelWelcome.setLayout(gl_panelWelcome);
		
		JPanel panelDigit1 = new JPanel();
		tabbedPane.addTab("First Digit", null, panelDigit1, null);
		
		JPanel panelDigit2 = new JPanel();
		tabbedPane.addTab("Second Digit", null, panelDigit2, null);
		
		JPanel panelD2O1 = new JPanel();
		GroupLayout gl_panelDigit2 = new GroupLayout(panelDigit2);
		gl_panelDigit2.setHorizontalGroup(
			gl_panelDigit2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelDigit2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelD2O1, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelDigit2.setVerticalGroup(
			gl_panelDigit2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelDigit2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelD2O1, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelDigit2.setLayout(gl_panelDigit2);
		
		JButton btnToDigit2 = new JButton("Next >");
		btnToDigit2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnToDigit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				code_int[0] = getFirstDigit();
				tabbedPane.setSelectedIndex(2);
			}
		});
		
		JButton btnBkDigit1 = new JButton("< Back");
		btnBkDigit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnBkDigit1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblQ1 = new JLabel("1. Is the part rotational (i.e. does it spin around an axis) or is it non-rotational?");
		lblQ1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		rdbtnQ1Yes = new JRadioButton("Yes");
		rdbtnQ1Yes.setSelected(true);
		rdbtnQ1Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answerQ1 = getYesNo(e);
				updateD1Components();
			}
		});
		rdbtnQ1Yes.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		rdbtnQ1No = new JRadioButton("No");
		rdbtnQ1No.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answerQ1 = getYesNo(e);
				updateD1Components();
			}
		});
		rdbtnQ1No.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblQ2 = new JLabel("2. How would you describe the external surface of the part?");
		lblQ2.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxExtShape = new JComboBox();
		
		JLabel lblPleaseGive = new JLabel("3. Please give (as best you can) the approximate dimensions of your part:");
		lblPleaseGive.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		lblLength = new JLabel("Length:");
		lblLength.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtLength = new JTextField();
		txtLength.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtLength.setColumns(10);
		
		lblWidth = new JLabel("Width:");
		lblWidth.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtWidth = new JTextField();
		txtWidth.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtWidth.setColumns(10);
		
		lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtHeight = new JTextField();
		txtHeight.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtHeight.setColumns(10);
		
		lblDiameter = new JLabel("Diameter:");
		lblDiameter.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtDiameter = new JTextField();
		txtDiameter.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDiameter.setColumns(10);
		GroupLayout gl_panelDigit1 = new GroupLayout(panelDigit1);
		gl_panelDigit1.setHorizontalGroup(
			gl_panelDigit1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDigit1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDigit1.createSequentialGroup()
							.addComponent(btnBkDigit1)
							.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
							.addComponent(btnToDigit2))
						.addGroup(gl_panelDigit1.createSequentialGroup()
							.addGap(12)
							.addComponent(rdbtnQ1Yes)
							.addGap(35)
							.addComponent(rdbtnQ1No))
						.addComponent(lblQ1)
						.addGroup(gl_panelDigit1.createSequentialGroup()
							.addGap(12)
							.addComponent(cboxExtShape, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblQ2)
						.addComponent(lblPleaseGive)
						.addGroup(gl_panelDigit1.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panelDigit1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDiameter)
								.addComponent(lblLength))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelDigit1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDigit1.createSequentialGroup()
									.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblWidth)
									.addGap(29)
									.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblHeight)
									.addGap(29)
									.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtDiameter, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panelDigit1.setVerticalGroup(
			gl_panelDigit1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDigit1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblQ1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelDigit1.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnQ1Yes)
						.addComponent(rdbtnQ1No))
					.addGap(18)
					.addComponent(lblQ2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxExtShape, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPleaseGive)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDigit1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLength)
						.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelDigit1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiameter)
						.addComponent(txtDiameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
					.addGroup(gl_panelDigit1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBkDigit1)
						.addComponent(btnToDigit2))
					.addContainerGap())
		);
		panelDigit1.setLayout(gl_panelDigit1);
		
		/* Custom code starts here. */
		
		this.rdbtnQ1No.setActionCommand("No");
		this.rdbtnQ1Yes.setActionCommand("Yes");
		
		this.btnQ1Group = new ButtonGroup();
		
		this.btnQ1Group.add(rdbtnQ1Yes);
		this.btnQ1Group.add(rdbtnQ1No);
		
		this.answerQ1 = true;
		
		this.updateD1Components();
	
	}
	
	/**
	 * In a radio button group that only has a yes/no option, checks whether the answer is yes or no.
	 * @param yesNoRadio The button group that contains the two radio buttons.
	 * @return TRUE if "Yes" is selected, FALSE otherwise.
	 */
	private boolean getYesNo(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Yes")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Updates the form components according to the answer to question 1.
	 */
	@SuppressWarnings("unchecked")
	private void updateD1Components() {
		this.cboxExtShape.removeAllItems();
		if (this.answerQ1) {
			this.cboxExtShape.addItem(ExtShape.PURE_CIRCLE_OR_CYLINDER);
			this.cboxExtShape.addItem(ExtShape.CIRCLE_OR_CYLINDER_WITH_DEVIATIONS);
			this.lblDiameter.setVisible(true);
			this.lblLength.setVisible(true);
			this.lblHeight.setVisible(false);
			this.lblWidth.setVisible(false);
			this.txtDiameter.setVisible(true);
			this.txtLength.setVisible(true);
			this.txtHeight.setVisible(false);
			this.txtWidth.setVisible(false);
		} else {
			this.cboxExtShape.addItem(ExtShape.FLAT_LONG_CUBIC);
			this.lblDiameter.setVisible(false);
			this.lblLength.setVisible(true);
			this.lblHeight.setVisible(true);
			this.lblWidth.setVisible(true);
			this.txtDiameter.setVisible(false);
			this.txtLength.setVisible(true);
			this.txtHeight.setVisible(true);
			this.txtWidth.setVisible(true);
		}
	}
	
	/**
	 * Gets the first digit of the Opitz code based on the user's answers.
	 * @return The first digit in the Opitz code.
	 */
	private int getFirstDigit() {
		
		int result = 0;
		double lByD = 0;
		double aByB = 0;
		double aByC = 0;
		
		if (this.answerQ1) {
			if (this.txtLength.getText().isEmpty() || this.txtDiameter.getText().isEmpty()) {
				return -1;
			}
			double L = Double.parseDouble(this.txtLength.getText());
			double D = Double.parseDouble(this.txtDiameter.getText());
			lByD = L / D;
			switch ((ExtShape) this.cboxExtShape.getSelectedItem()) {
				case PURE_CIRCLE_OR_CYLINDER :
					if (lByD <= 0.5) {
						result = 0;
					} else if (lByD < 3 && lByD > 0.5) {
						result = 1;
					} else if (lByD >= 3) {
						result = 2;
					} else {
						result = -1;
					}
					break;
				case CIRCLE_OR_CYLINDER_WITH_DEVIATIONS :
					if (lByD <= 2) {
						result = 3;
					} else if (lByD > 2) {
						result = 4;
					} else {
						result = -1;
					}
					break;
				default :
					result = -2;
					break;
			}
		} else {
			if (this.txtLength.getText().isEmpty() || this.txtWidth.getText().isEmpty() || this.txtHeight.getText().isEmpty()) {
				return -1;
			}
			double A = Double.parseDouble(this.txtLength.getText());
			double B = Double.parseDouble(this.txtWidth.getText());
			double C = Double.parseDouble(this.txtHeight.getText());
			double[] temp = new double[] { A, B, C };
			// Reordering according to p. 4
			A = this.getMaxWithExclusion(temp);
			B = this.getMaxWithExclusion(temp);
			C = this.getMaxWithExclusion(temp);
			
			aByB = A / B;
			aByC = A / C;
			
			switch ((ExtShape) this.cboxExtShape.getSelectedItem()) {
				case FLAT_LONG_CUBIC :
					if (aByB <= 3 && aByC >= 4) {
						result = 6;
					} else if (aByB > 3) {
						result = 7;
					} else if (aByB <= 3 && aByC < 4) {
						result = 8;
					} else {
						result = -1;
					}
					break;
				default :
					result = -2;
					break;
			}
		}
		
		return result;
	}
	
	/**
	 * Gets a maximum value in a double array and effectively removes it afterwards (given the array is composed of non-zero integers).
	 * @param in The incoming array (must consist of zeroes or positive integers for exclusion to work).
	 * @return The maximum double in the given array or -1 if none is found.
	 */
	private double getMaxWithExclusion(double[] in) {
		double result = -1;
		int index = -1;
		
		for (int i = 0; i < in.length; i++) {
			if (in[i] > result) {
				result = in[i];
				index = i;
			}
		}
		
		in[index] = -100;
		
		return result;
	}
}
