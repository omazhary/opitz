package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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

import entities.ExtElements;
import entities.ExtElementsAdv;
import entities.ExtShape;
import entities.HolesTeeth;
import entities.InitForm;
import entities.IntElements;
import entities.Material;
import entities.PlaneSurfaceMachining;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JCheckBox;

public class OpitzCodeWizardFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelD2O1;
	private JPanel panelD3O1;
	private ButtonGroup btnQ1Group;
	private ButtonGroup btnQ5Group;
	private JRadioButton rdbtnQ1Yes;
	private JRadioButton rdbtnQ1No;
	private JRadioButton rdbtnQ5Yes;
	private JRadioButton rdbtnQ5No;
	private boolean answerQ1;
	private boolean answerQ5;
	private JTextField txtLength;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtDiameter;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxExtShape;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxExtElements;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxExtElementsStepped;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxIntElements;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxHolesTeeth;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxMaterial;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxInitForm;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxPlSfMg;
	private JLabel lblHeight;
	private JLabel lblWidth;
	private JLabel lblLength;
	private JLabel lblDiameter;
	private String code;
	private int[] code_int;
	private GroupLayout gl_panelDigit4;
	private JButton btnToDigit2;
	private JButton btnToDigit3;
	private JButton btnToDigit4;
	private JButton btnToDigit5;
	private JButton btnToDigit78;
	private JButton btnToSummary;
	private JLabel lblHowWouldYou;
	private JPanel panelSummary;
	private JTextPane textPaneSummary;
	private JTextField txtWizardCode;
	private JLabel lblD3;
	private JLabel lblcongratulationsyoureDone;
	private JCheckBox chkD2;
	private JCheckBox chkD3;
	private JCheckBox chkD4;
	private JCheckBox chkD5;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { OpitzCodeWizardFrame frame = new
	 * OpitzCodeWizardFrame(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OpitzCodeWizardFrame(JTextField wizardCode) {

		this.txtWizardCode = wizardCode;

		this.code_int = new int[9];
		this.code = "";

		for (int i = 0; i < this.code_int.length; i++) {
			this.code_int[i] = -1;
		}

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
		lblIconWizard.setIcon(new ImageIcon(OpitzCodeWizardFrame.class
				.getResource("/icons/wizard.png")));

		JLabel lblNewLabel = new JLabel(
				"<html>Welcome to the Opitz code <br />recognition Wizard!!</html>");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 26));

		JTextPane txtpnThisWizardWill = new JTextPane();
		txtpnThisWizardWill.setEditable(false);
		txtpnThisWizardWill.setBackground(UIManager
				.getColor("Button.background"));
		txtpnThisWizardWill
				.setText("This wizard will guide you through the steps to construct the Opitz\r\ngroup technology code for the part you'd like to search for.\r\n\r\nPlease attempt to answer as much of the questions as possible.\r\n\r\n(Unanswered questions decrease the generated Opitz code's\r\naccuracy, thereby decreasing your chances of finding a match!!)");
		txtpnThisWizardWill.setFont(new Font("Verdana", Font.PLAIN, 12));

		JButton btnToFirstDigit = new JButton("Start!!");
		btnToFirstDigit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnToFirstDigit.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelWelcome = new GroupLayout(panelWelcome);
		gl_panelWelcome
				.setHorizontalGroup(gl_panelWelcome
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelWelcome
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblIconWizard,
												GroupLayout.PREFERRED_SIZE,
												139, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												gl_panelWelcome
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblNewLabel,
																GroupLayout.PREFERRED_SIZE,
																431,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtpnThisWizardWill,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(185, Short.MAX_VALUE))
						.addGroup(
								Alignment.TRAILING,
								gl_panelWelcome.createSequentialGroup()
										.addContainerGap(698, Short.MAX_VALUE)
										.addComponent(btnToFirstDigit)
										.addContainerGap()));
		gl_panelWelcome.setVerticalGroup(gl_panelWelcome.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panelWelcome
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panelWelcome
										.createParallelGroup(
												Alignment.TRAILING, false)
										.addComponent(lblNewLabel,
												Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblIconWizard,
												Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, 164,
												Short.MAX_VALUE))
						.addGap(18)
						.addComponent(txtpnThisWizardWill,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 212,
								Short.MAX_VALUE).addComponent(btnToFirstDigit)
						.addContainerGap()));
		panelWelcome.setLayout(gl_panelWelcome);

		JPanel panelDigit1 = new JPanel();
		tabbedPane.addTab("Part Class", null, panelDigit1, null);

		JPanel panelDigit2 = new JPanel();
		tabbedPane.addTab("External Shape", null, panelDigit2, null);

		panelD2O1 = new JPanel();

		btnToDigit3 = new JButton("Next >");
		btnToDigit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[1] = getSecondDigit();
				updateCode();
				updateD3Components();
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnToDigit3.setFont(new Font("Verdana", Font.PLAIN, 12));

		JButton btnBkDigit2 = new JButton("< Back");
		btnBkDigit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnBkDigit2.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelDigit2 = new GroupLayout(panelDigit2);
		gl_panelDigit2
				.setHorizontalGroup(gl_panelDigit2
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panelDigit2
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panelDigit2
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panelD2O1,
																GroupLayout.DEFAULT_SIZE,
																599,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																gl_panelDigit2
																		.createSequentialGroup()
																		.addComponent(
																				btnBkDigit2)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				443,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnToDigit3)))
										.addContainerGap()));
		gl_panelDigit2.setVerticalGroup(gl_panelDigit2.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						Alignment.LEADING,
						gl_panelDigit2
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panelD2O1,
										GroupLayout.PREFERRED_SIZE, 355,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										12, Short.MAX_VALUE)
								.addGroup(
										gl_panelDigit2
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnToDigit3)
												.addComponent(btnBkDigit2))
								.addContainerGap()));

		JLabel lblD2O1Q1 = new JLabel(
				"1. How would you describe the external shape of the part?");
		lblD2O1Q1.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxExtElements = new JComboBox();

		cboxExtElementsStepped = new JComboBox();

		cboxExtElements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCboxExtElementsAdv();
			}
		});
		cboxExtElements.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblAreThere = new JLabel(
				"2. Are there any additional shape details?");
		lblAreThere.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxExtElementsStepped.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		chkD2 = new JCheckBox("My answers are 100% accurate.");
		chkD2.setFont(new Font("Verdana", Font.PLAIN, 12));
		chkD2.setSelected(true);
		GroupLayout gl_panelD2O1 = new GroupLayout(panelD2O1);
		gl_panelD2O1.setHorizontalGroup(
			gl_panelD2O1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelD2O1.createSequentialGroup()
					.addGroup(gl_panelD2O1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelD2O1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelD2O1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblD2O1Q1)
								.addComponent(lblAreThere)))
						.addGroup(gl_panelD2O1.createSequentialGroup()
							.addGap(22)
							.addComponent(cboxExtElements, 0, 567, Short.MAX_VALUE))
						.addGroup(gl_panelD2O1.createSequentialGroup()
							.addGap(22)
							.addComponent(cboxExtElementsStepped, 0, 567, Short.MAX_VALUE))
						.addGroup(gl_panelD2O1.createSequentialGroup()
							.addContainerGap()
							.addComponent(chkD2)))
					.addContainerGap())
		);
		gl_panelD2O1.setVerticalGroup(
			gl_panelD2O1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelD2O1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblD2O1Q1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxExtElements, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAreThere)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxExtElementsStepped, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chkD2)
					.addContainerGap(192, Short.MAX_VALUE))
		);
		panelD2O1.setLayout(gl_panelD2O1);
		panelDigit2.setLayout(gl_panelDigit2);

		btnToDigit2 = new JButton("Next >");
		btnToDigit2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnToDigit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				code_int[0] = getFirstDigit();
				updateCode();
				updateD2Components();
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

		JLabel lblQ1 = new JLabel(
				"1. Is the part rotational (i.e. does it spin around an axis) or is it non-rotational?");
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

		JLabel lblQ2 = new JLabel(
				"2. How would you describe the external surface of the part?");
		lblQ2.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxExtShape = new JComboBox();

		JLabel lblPleaseGive = new JLabel(
				"3. Please give (as best you can) the approximate dimensions of your part:");
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
		gl_panelDigit1
				.setHorizontalGroup(gl_panelDigit1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelDigit1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panelDigit1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panelDigit1
																		.createSequentialGroup()
																		.addComponent(
																				btnBkDigit1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				449,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnToDigit2))
														.addGroup(
																gl_panelDigit1
																		.createSequentialGroup()
																		.addGap(12)
																		.addComponent(
																				rdbtnQ1Yes)
																		.addGap(35)
																		.addComponent(
																				rdbtnQ1No))
														.addComponent(lblQ1)
														.addGroup(
																gl_panelDigit1
																		.createSequentialGroup()
																		.addGap(12)
																		.addComponent(
																				cboxExtShape,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(lblQ2)
														.addComponent(
																lblPleaseGive)
														.addGroup(
																gl_panelDigit1
																		.createSequentialGroup()
																		.addGap(12)
																		.addGroup(
																				gl_panelDigit1
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblDiameter)
																						.addComponent(
																								lblLength))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_panelDigit1
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_panelDigit1
																										.createSequentialGroup()
																										.addComponent(
																												txtLength,
																												GroupLayout.PREFERRED_SIZE,
																												57,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												lblWidth)
																										.addGap(29)
																										.addComponent(
																												txtWidth,
																												GroupLayout.PREFERRED_SIZE,
																												57,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												lblHeight)
																										.addGap(29)
																										.addComponent(
																												txtHeight,
																												GroupLayout.PREFERRED_SIZE,
																												57,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								txtDiameter,
																								GroupLayout.PREFERRED_SIZE,
																								56,
																								GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
		gl_panelDigit1
				.setVerticalGroup(gl_panelDigit1
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panelDigit1
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblQ1)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panelDigit1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																rdbtnQ1Yes)
														.addComponent(rdbtnQ1No))
										.addGap(18)
										.addComponent(lblQ2)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(cboxExtShape,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblPleaseGive)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panelDigit1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblLength)
														.addComponent(
																txtLength,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblWidth)
														.addComponent(
																txtWidth,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblHeight)
														.addComponent(
																txtHeight,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panelDigit1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDiameter)
														.addComponent(
																txtDiameter,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												188, Short.MAX_VALUE)
										.addGroup(
												gl_panelDigit1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnBkDigit1)
														.addComponent(
																btnToDigit2))
										.addContainerGap()));
		panelDigit1.setLayout(gl_panelDigit1);

		JPanel panelDigit3 = new JPanel();
		tabbedPane.addTab("Internal Shape", null, panelDigit3, null);

		btnToDigit4 = new JButton("Next >");
		btnToDigit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[2] = getThirdDigit();
				updateCode();
				updateD4Components();
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnToDigit4.setFont(new Font("Verdana", Font.PLAIN, 12));

		JButton btnBkDigit3 = new JButton("< Back");
		btnBkDigit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnBkDigit3.setFont(new Font("Verdana", Font.PLAIN, 12));

		panelD3O1 = new JPanel();
		GroupLayout gl_panelDigit3 = new GroupLayout(panelDigit3);
		gl_panelDigit3
				.setHorizontalGroup(gl_panelDigit3
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panelDigit3
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panelDigit3
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																panelD3O1,
																GroupLayout.DEFAULT_SIZE,
																601,
																Short.MAX_VALUE)
														.addGroup(
																gl_panelDigit3
																		.createSequentialGroup()
																		.addComponent(
																				btnBkDigit3)
																		.addGap(449)
																		.addComponent(
																				btnToDigit4)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_panelDigit3.setVerticalGroup(gl_panelDigit3.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						gl_panelDigit3
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panelD3O1,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panelDigit3
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnToDigit4)
												.addComponent(btnBkDigit3))
								.addContainerGap()));

		lblD3 = new JLabel(
				"How would you describe the internal shape of the part?");
		lblD3.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxIntElements = new JComboBox();
		cboxIntElements.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		chkD3 = new JCheckBox("My answer is 100% accurate.");
		chkD3.setSelected(true);
		chkD3.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panelD3O1);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(cboxIntElements, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblD3)
						.addComponent(chkD3))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblD3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxIntElements, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chkD3)
					.addContainerGap(294, Short.MAX_VALUE))
		);
		panelD3O1.setLayout(gl_panel);
		panelDigit3.setLayout(gl_panelDigit3);

		JPanel panelDigit4 = new JPanel();
		tabbedPane.addTab("Plane Surface Machining", null, panelDigit4, null);

		btnToDigit5 = new JButton("Next >");
		btnToDigit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[3] = getFourthDigit();
				updateCode();
				updateD5Components();
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnToDigit5.setFont(new Font("Verdana", Font.PLAIN, 12));

		JButton btnBkDigit4 = new JButton("< Back");
		btnBkDigit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnBkDigit4.setFont(new Font("Verdana", Font.PLAIN, 12));

		lblHowWouldYou = new JLabel(
				"How would you describe the part's plane surface machining?");
		lblHowWouldYou.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxPlSfMg = new JComboBox();
		cboxPlSfMg.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		chkD4 = new JCheckBox("My answer is 100% accurate.");
		chkD4.setSelected(true);
		chkD4.setFont(new Font("Verdana", Font.PLAIN, 12));
		// GroupLayout gl_panel;
		gl_panelDigit4 = new GroupLayout(panelDigit4);
		gl_panelDigit4.setHorizontalGroup(
			gl_panelDigit4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDigit4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDigit4.createSequentialGroup()
							.addGap(10)
							.addComponent(cboxPlSfMg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDigit4.createSequentialGroup()
							.addComponent(btnBkDigit4)
							.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
							.addComponent(btnToDigit5))
						.addComponent(lblHowWouldYou)
						.addComponent(chkD4))
					.addContainerGap())
		);
		gl_panelDigit4.setVerticalGroup(
			gl_panelDigit4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDigit4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHowWouldYou)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxPlSfMg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chkD4)
					.addPreferredGap(ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
					.addGroup(gl_panelDigit4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToDigit5)
						.addComponent(btnBkDigit4))
					.addContainerGap())
		);
		panelDigit4.setLayout(gl_panelDigit4);

		JPanel panelDigit5 = new JPanel();
		tabbedPane.addTab("Auxiliary Hole(s) and Gear Teeth", null,
				panelDigit5, null);

		btnToDigit78 = new JButton("Next >");
		btnToDigit78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[4] = getFifthDigit();
				updateCode();
				updateD78Components();
				tabbedPane.setSelectedIndex(6);
			}
		});
		btnToDigit78.setFont(new Font("Verdana", Font.PLAIN, 12));

		JButton btnBkDigit5 = new JButton("< Back");
		btnBkDigit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnBkDigit5.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblD5Q1 = new JLabel("1. Does the part have gear teeth?");
		lblD5Q1.setFont(new Font("Verdana", Font.PLAIN, 12));

		rdbtnQ5Yes = new JRadioButton("Yes");
		rdbtnQ5Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answerQ5 = getYesNo(e);
				updateD5Components();
			}
		});
		rdbtnQ5Yes.setSelected(true);
		rdbtnQ5Yes.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnQ5Yes.setActionCommand("Yes");

		rdbtnQ5No = new JRadioButton("No");
		rdbtnQ5No.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answerQ5 = getYesNo(e);
				updateD5Components();
			}
		});
		rdbtnQ5No.setFont(new Font("Verdana", Font.PLAIN, 12));
		rdbtnQ5No.setActionCommand("No");

		JLabel lblD5Q2 = new JLabel(
				"2. How would you describe the part's holes/teeth?");
		lblD5Q2.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxHolesTeeth = new JComboBox();
		cboxHolesTeeth.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		chkD5 = new JCheckBox("My answers are 100% accurate.");
		chkD5.setSelected(true);
		chkD5.setFont(new Font("Verdana", Font.PLAIN, 12));

		GroupLayout gl_panelDigit5 = new GroupLayout(panelDigit5);
		gl_panelDigit5.setHorizontalGroup(
			gl_panelDigit5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDigit5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit5.createParallelGroup(Alignment.LEADING)
						.addComponent(chkD5)
						.addGroup(gl_panelDigit5.createSequentialGroup()
							.addGap(12)
							.addComponent(cboxHolesTeeth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDigit5.createSequentialGroup()
							.addGap(12)
							.addComponent(rdbtnQ5Yes)
							.addGap(55)
							.addComponent(rdbtnQ5No))
						.addComponent(lblD5Q1)
						.addGroup(gl_panelDigit5.createSequentialGroup()
							.addComponent(btnBkDigit5)
							.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
							.addComponent(btnToDigit78))
						.addComponent(lblD5Q2))
					.addContainerGap())
		);
		gl_panelDigit5.setVerticalGroup(
			gl_panelDigit5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDigit5.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblD5Q1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelDigit5.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnQ5Yes)
						.addComponent(rdbtnQ5No))
					.addGap(18)
					.addComponent(lblD5Q2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cboxHolesTeeth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chkD5)
					.addGap(233)
					.addGroup(gl_panelDigit5.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBkDigit5)
						.addComponent(btnToDigit78))
					.addContainerGap())
		);
		panelDigit5.setLayout(gl_panelDigit5);

		JPanel panelDigit78 = new JPanel();
		tabbedPane
				.addTab("Material and Initial Form", null, panelDigit78, null);

		JLabel lblWhatMaterial = new JLabel(
				"1. What material should the part be made of?");
		lblWhatMaterial.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxMaterial = new JComboBox();
		cboxMaterial.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel lblWhatIs = new JLabel(
				"2. What is the initial form of the material you chose above?");
		lblWhatIs.setFont(new Font("Verdana", Font.PLAIN, 12));

		cboxInitForm = new JComboBox();
		cboxInitForm.setFont(new Font("Verdana", Font.PLAIN, 12));

		JButton btnBkDigit78 = new JButton("< Back");
		btnBkDigit78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnBkDigit78.setFont(new Font("Verdana", Font.PLAIN, 12));

		btnToSummary = new JButton("Next >");
		btnToSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//code_int[5] = getSixthDigit();
				code_int[6] = getSeventhDigit();
				code_int[7] = getEighthDigit();
				code_int[8] = getNinthDigit();
				updateCode();
				updateSummary();
				tabbedPane.setSelectedIndex(7);
			}
		});
		btnToSummary.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelDigit78 = new GroupLayout(panelDigit78);
		gl_panelDigit78
				.setHorizontalGroup(gl_panelDigit78
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelDigit78
										.createSequentialGroup()
										.addGroup(
												gl_panelDigit78
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panelDigit78
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_panelDigit78
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblWhatMaterial)
																						.addComponent(
																								lblWhatIs)
																						.addGroup(
																								gl_panelDigit78
																										.createSequentialGroup()
																										.addComponent(
																												btnBkDigit78)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												449,
																												Short.MAX_VALUE)
																										.addComponent(
																												btnToSummary))))
														.addGroup(
																gl_panelDigit78
																		.createSequentialGroup()
																		.addGap(24)
																		.addComponent(
																				cboxMaterial,
																				0,
																				589,
																				Short.MAX_VALUE))
														.addGroup(
																gl_panelDigit78
																		.createSequentialGroup()
																		.addGap(24)
																		.addComponent(
																				cboxInitForm,
																				0,
																				589,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_panelDigit78.setVerticalGroup(gl_panelDigit78.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panelDigit78
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblWhatMaterial)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cboxMaterial,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblWhatIs)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cboxInitForm,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										282, Short.MAX_VALUE)
								.addGroup(
										gl_panelDigit78
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnBkDigit78)
												.addComponent(btnToSummary))
								.addContainerGap()));
		panelDigit78.setLayout(gl_panelDigit78);

		panelSummary = new JPanel();
		tabbedPane.addTab("Summary", null, panelSummary, null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(OpitzCodeWizardFrame.class
				.getResource("/icons/wizard.png")));

		textPaneSummary = new JTextPane();
		textPaneSummary.setFont(new Font("Verdana", Font.PLAIN, 12));
		textPaneSummary.setEditable(false);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCode();
				txtWizardCode.setText(code);
				dispose();
			}
		});
		btnFinish.setFont(new Font("Verdana", Font.PLAIN, 12));

		JButton btnBkSummary = new JButton("< Back");
		btnBkSummary.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBkSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		
		lblcongratulationsyoureDone = new JLabel("<html>Congratulations!!<br />You're done!!</html>");
		lblcongratulationsyoureDone.setFont(new Font("Verdana", Font.BOLD, 26));
		GroupLayout gl_panelSummary = new GroupLayout(panelSummary);
		gl_panelSummary.setHorizontalGroup(
			gl_panelSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSummary.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSummary.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelSummary.createSequentialGroup()
							.addComponent(btnBkSummary)
							.addGap(455)
							.addComponent(btnFinish)
							.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelSummary.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(29)
							.addComponent(lblcongratulationsyoureDone, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
							.addGap(188))
						.addComponent(textPaneSummary, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelSummary.setVerticalGroup(
			gl_panelSummary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSummary.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSummary.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblcongratulationsyoureDone))
					.addGap(18)
					.addComponent(textPaneSummary, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_panelSummary.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFinish)
						.addComponent(btnBkSummary))
					.addContainerGap())
		);
		panelSummary.setLayout(gl_panelSummary);

		/* Custom code starts here. */

		this.rdbtnQ1No.setActionCommand("No");
		this.rdbtnQ1Yes.setActionCommand("Yes");
		this.rdbtnQ5No.setActionCommand("No");
		this.rdbtnQ5Yes.setActionCommand("Yes");

		this.btnQ1Group = new ButtonGroup();
		this.btnQ5Group = new ButtonGroup();

		this.btnQ1Group.add(this.rdbtnQ1Yes);
		this.btnQ1Group.add(this.rdbtnQ1No);
		this.btnQ5Group.add(this.rdbtnQ5Yes);
		this.btnQ5Group.add(this.rdbtnQ5No);

		this.answerQ1 = true;
		this.answerQ5 = true;

		this.updateD1Components();
		this.updateD2Components();
		this.updateD3Components();
		this.updateD4Components();
		this.updateD5Components();
		this.updateD78Components();

		/* Custom code ends here. */

	}

	/**
	 * In a radio button group that only has a yes/no option, checks whether the
	 * answer is yes or no.
	 * 
	 * @param yesNoRadio
	 *            The button group that contains the two radio buttons.
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
			this.cboxExtShape
					.addItem(ExtShape.CIRCLE_OR_CYLINDER_WITH_DEVIATIONS);
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
	 * Populates the combo box with internal shape elements.
	 */
	@SuppressWarnings({ "unchecked" })
	private void updateCboxIntElements() {
		this.cboxIntElements.removeAllItems();
		if (this.code_int[0] < 0) {
			return;
		} else if (this.code_int[0] < 3) {
			this.lblD3
					.setText("How would you describe the internal shape of the part?");
			this.cboxIntElements.addItem(IntElements.INT_NO_HOLE_BREAKTHROUGH);
			if (this.code_int[1] < 4) {
				this.cboxIntElements
						.addItem(IntElements.INT_SMOOTH_STEPPED_ONE_SIDE_NONE);
				this.cboxIntElements
						.addItem(IntElements.INT_SMOOTH_STEPPED_ONE_SIDE_THREAD);
				this.cboxIntElements
						.addItem(IntElements.INT_SMOOTH_STEPPED_ONE_SIDE_FUNCTIONAL_GROOVE);
			} else if (this.code_int[1] < 7) {
				this.cboxIntElements
						.addItem(IntElements.INT_STEPPED_BOTH_SIDES_NONE);
				this.cboxIntElements
						.addItem(IntElements.INT_STEPPED_BOTH_SIDES_THREAD);
				this.cboxIntElements
						.addItem(IntElements.INT_STEPPED_BOTH_SIDES_FUNCTIONAL_GROOVE);
			}
			this.cboxIntElements.addItem(IntElements.INT_FUNCTION_CONE);
			this.cboxIntElements.addItem(IntElements.INT_OPERATING_THREAD);
			this.cboxIntElements.addItem(IntElements.INT_MISC);
		} else if (this.code_int[0] < 5) {
			this.lblD3
					.setText("How would you describe the part's rotational machining?");
			this.cboxIntElements.addItem(IntElements.ROT_NONE);
			this.cboxIntElements.addItem(IntElements.ROT_EXT_MACHINED);
			this.cboxIntElements.addItem(IntElements.ROT_EXT_SCREWTHREAD);
			this.cboxIntElements.addItem(IntElements.ROT_INT_SMOOTH);
			this.cboxIntElements.addItem(IntElements.ROT_INT_STEPPED);
			this.cboxIntElements.addItem(IntElements.ROT_INT_SCREWTHREAD);
			this.cboxIntElements.addItem(IntElements.ROT_EXT_INT_MACHINED);
			this.cboxIntElements.addItem(IntElements.ROT_EXT_INT_SCREWTHREAD);
			this.cboxIntElements.addItem(IntElements.ROT_EXT);
			this.cboxIntElements.addItem(IntElements.ROT_MISC);
		} else if (this.code_int[0] < 9) {
			this.lblD3
					.setText("How would you describe the part's principal bore and/or its rotational surface machining?");
			this.cboxIntElements.addItem(IntElements.PBRSM_NONE);
			this.cboxIntElements.addItem(IntElements.PBRSM_ONE_BORE_SMOOTH);
			this.cboxIntElements.addItem(IntElements.PBRSM_ONE_BORE_STEPPED);
			this.cboxIntElements
					.addItem(IntElements.PBRSM_ONE_BORE_SHAPE_ELEMENTS);
			this.cboxIntElements.addItem(IntElements.PBRSM_TWO_BORES_PARALLEL);
			this.cboxIntElements
					.addItem(IntElements.PBRSM_SEVERAL_BORES_PARALLEL);
			this.cboxIntElements.addItem(IntElements.PBRSM_SEVERAL_BORES_OTHER);
			this.cboxIntElements.addItem(IntElements.PBRSM_MACHINED_ANNULAR);
			this.cboxIntElements.addItem(IntElements.PBRSM_SEVEN_BORES_OR_MORE);
			this.cboxIntElements.addItem(IntElements.PBRSM_MISC);
		}
	}

	/**
	 * Determines which panel to show depending on the value of the first digit.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updateD2Components() {
		if (this.code_int[0] < 0) {
			this.btnToDigit3.setEnabled(false);
			return;
		} else {
			this.btnToDigit3.setEnabled(true);
			// this.cboxExtElements.removeAllItems();
			this.cboxExtElements.setModel(new DefaultComboBoxModel());
			if (this.code_int[0] < 3) {
				this.cboxExtElements.addItem(ExtElements.SMOOTH);
				this.cboxExtElements.addItem(ExtElements.STEPPED_ONE_SIDE);
				this.cboxExtElements.addItem(ExtElements.STEPPED_BOTH_SIDES);
				this.cboxExtElements.addItem(ExtElements.FUNCTION_CONE);
				this.cboxExtElements.addItem(ExtElements.OPERATING_THREAD);
				this.cboxExtElements.addItem(ExtElements.MISC);
			} else if (this.code_int[0] < 5) {
				this.cboxExtElements.addItem(ExtElements.ONE_AXIS_NO_SEGMENTS);
				this.cboxExtElements.addItem(ExtElements.SEGMENTS_AFTER);
				this.cboxExtElements.addItem(ExtElements.SEGMENTS_BEFORE);
				this.cboxExtElements.addItem(ExtElements.MULITPLE_AXES);
				this.cboxExtElements.addItem(ExtElements.MISC);
			} else if (this.code_int[0] < 7) {
				this.cboxExtElements.addItem(ExtElements.PLANE);
				this.cboxExtElements.addItem(ExtElements.FLAT_REC);
				this.cboxExtElements.addItem(ExtElements.FLAT_ROUND);
				this.cboxExtElements.addItem(ExtElements.FLAT_ARCHED_REG);
				this.cboxExtElements.addItem(ExtElements.FLAT_ARCHED_IRR);
				this.cboxExtElements.addItem(ExtElements.MISC);
			} else if (this.code_int[0] < 8) {
				this.cboxExtElements
						.addItem(ExtElements.SHAPE_AXIS_STRAIGHT_UNIFORM);
				this.cboxExtElements
						.addItem(ExtElements.SHAPE_AXIS_STRAIGHT_VARYING);
				this.cboxExtElements.addItem(ExtElements.SHAPE_AXIS_CURVED);
			} else if (this.code_int[0] < 9) {
				this.cboxExtElements.addItem(ExtElements.BLOCK);
				this.cboxExtElements.addItem(ExtElements.BOX_NOT_SPLIT);
				this.cboxExtElements.addItem(ExtElements.BOX_SPLIT);
			}
			this.updateCboxExtElementsAdv();
		}
	}

	/**
	 * Updates the elements in the combo box for external elements (stepped).
	 */
	@SuppressWarnings("unchecked")
	private void updateCboxExtElementsAdv() {
		this.cboxExtElementsStepped.removeAllItems();
		switch ((ExtElements) this.cboxExtElements.getSelectedItem()) {
		case SMOOTH:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.NONE);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_ONE_SIDE_SCREW_THREADS);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_ONE_SIDE_FUNCTIONAL_GROOVE);
			break;
		case STEPPED_ONE_SIDE:
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_ONE_SIDE_NONE);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_ONE_SIDE_SCREW_THREADS);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_ONE_SIDE_FUNCTIONAL_GROOVE);
			break;
		case STEPPED_BOTH_SIDES:
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_BOTH_SIDES_NONE);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_BOTH_SIDES_SCREW_THREADS);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.STEPPED_BOTH_SIDES_FUNCTIONAL_GROOVE);
			break;
		case ONE_AXIS_NO_SEGMENTS:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.HEX_BAR);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.SQUARE_POLY);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.SYM_CROSS);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.CROSS_MISC);
			break;
		case MULITPLE_AXES:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.ROT_CURVED);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.ROT_PARALLEL);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.ROT_INTERSECT);
			break;
		case PLANE:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.PLANE_RECT);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.PLANE_RECT_ONE_DEV);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.PLANE_RECT_ANG_DEV);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.PLANE_RECT_CIR_DEV);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.PLANE_RECT_MISC);
			break;
		case SHAPE_AXIS_STRAIGHT_UNIFORM:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.SAS_UNI_RECT);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.SAS_UNI_RECT_ONE_DEV);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.SAS_UNI_RECT_MISC);
			break;
		case SHAPE_AXIS_STRAIGHT_VARYING:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.SAS_VAR_RECT);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.SAS_VAR_RECT_ONE_DEV);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.SAS_VAR_RECT_MISC);
			break;
		case SHAPE_AXIS_CURVED:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.RECT_ANG_OTHER);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.FORMED);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.FORMED_DEV);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.SAC_MISC);
			break;
		case BLOCK:
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.BLOCK_RECT_PRISM);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.BLOCK_RECT_DEV);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.BLOCK_COMP_MULT);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.BLOCK_COMP_BORE);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.BLOCK_COMP_BORE_DIV);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.BLOCK_COMP_MISC);
			break;
		case BOX_NOT_SPLIT:
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.BOX_NO_SPLIT_APPROX);
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.BOX_NO_SPLIT_MISC);
			break;
		case BOX_SPLIT:
			this.cboxExtElementsStepped
					.addItem(ExtElementsAdv.BOX_SPLIT_APPROX);
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.BOX_SPLIT_MISC);
			break;
		default:
			this.cboxExtElementsStepped.addItem(ExtElementsAdv.NONE);
			break;
		}
	}

	/**
	 * Determines which panel to show depending on the value of the first digit.
	 */
	private void updateD3Components() {
		if (this.code_int[0] < 0 || this.code_int[1] < 0) {
			this.btnToDigit4.setEnabled(false);
			return;
		} else {
			this.btnToDigit4.setEnabled(true);
			this.updateCboxIntElements();
		}
	}

	/**
	 * Populates the combo box based on the first digit's value.
	 */
	@SuppressWarnings({ "unchecked" })
	private void updateD4Components() {
		if (this.code_int[0] < 0) {
			this.btnToDigit5.setEnabled(false);
			return;
		} else {
			this.btnToDigit5.setEnabled(true);
			this.cboxPlSfMg.removeAllItems();
			this.cboxPlSfMg.addItem(PlaneSurfaceMachining.NONE);
			if (this.code_int[0] < 5) {
				this.cboxPlSfMg
						.addItem(PlaneSurfaceMachining.CURVED_ONE_DIRECTION);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.CURVED_CIRCLE);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.EXT_GROOVE_SLOT);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.EXT_SPLINE);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.EXT_SPLINE_SLOT);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.INT_PLANE_SLOT);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.INT_SPLINE);
				this.cboxPlSfMg
						.addItem(PlaneSurfaceMachining.INT_EXT_POLYGON_GROOVE_SLOT);
			} else if (this.code_int[0] < 9) {
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.FUN_CHAMFERS);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.ONE_PLANE_SURF);
				this.cboxPlSfMg
						.addItem(PlaneSurfaceMachining.STEPPED_PLANE_SURF);
				this.cboxPlSfMg
						.addItem(PlaneSurfaceMachining.STEPPED_PLANE_SURF_ADV);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.GROOVE_SLOT);
				this.cboxPlSfMg
						.addItem(PlaneSurfaceMachining.GROOVE_SLOT_PLANE_SURF_ADV);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.CURVED_SURF);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.GUIDE_SURF);
			}
			this.cboxPlSfMg.addItem(PlaneSurfaceMachining.MISC);
		}
	}

	/**
	 * Decides what components to show based on the first digit's value and
	 * populates the combo boxes accordingly.
	 */
	@SuppressWarnings("unchecked")
	private void updateD5Components() {
		if (this.code_int[0] < 0) {
			this.btnToDigit78.setEnabled(false);
			return;
		} else {
			this.btnToDigit78.setEnabled(true);
			this.cboxHolesTeeth.removeAllItems();
			if (this.code_int[0] < 3) {
				if (this.answerQ5) {
					this.cboxHolesTeeth.addItem(HolesTeeth.TEETH_SPUR);
					this.cboxHolesTeeth.addItem(HolesTeeth.TEETH_BEVEL);
					this.cboxHolesTeeth.addItem(HolesTeeth.TEETH_MISC);
				} else {
					this.cboxHolesTeeth.addItem(HolesTeeth.NONE_1);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.HOLES_AXIAL_UNRELATED);
					this.cboxHolesTeeth.addItem(HolesTeeth.HOLES_AXIAL_RELATED);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.HOLES_RADIAL_UNRELATED);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.HOLES_AXIAL_RADIAL_UNRELATED);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.HOLES_AXIAL_RADIAL_RELATED);
				}
			} else if (this.code_int[0] < 5) {
				this.cboxHolesTeeth.addItem(HolesTeeth.ROT_5_NONE);
				if (this.answerQ5) {
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_GEAR_TEETH_NO_HOLES);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_GEAR_TEETH_WITH_HOLES);
				} else {
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_AXIAL_HOLES_NOT_RELATED);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_OTHER_HOLES_NOT_RELATED);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_AXIAL_HOLES_RELATED);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_OTHER_HOLES_RELATED);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_FORMED_NO_AUX_HOLES);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.ROT_5_FORMED_WITH_AUX_HOLES);
				}
			} else if (this.code_int[0] < 9) {
				this.cboxHolesTeeth.addItem(HolesTeeth.NOT_ROT_9_NONE);
				if (this.answerQ5) {
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_GEAR_TEETH_NO_HOLES);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_GEAR_TEETH_WITH_HOLES);
				} else {
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_HOLES_ONE_DIR);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_HOLES_MULT_DIR);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_HOLES_RELATED_ONE_DIR);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_HOLES_RELATED_MULT_DIR);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_FORMED_NO_AUX_HOLES);
					this.cboxHolesTeeth
							.addItem(HolesTeeth.NOT_ROT_9_FORMED_WITH_AUX_HOLES);
				}
			}

			this.cboxHolesTeeth.addItem(HolesTeeth.MISC);
		}
	}

	/**
	 * Populates the combo boxes for digits 7 and 8.
	 */
	@SuppressWarnings("unchecked")
	private void updateD78Components() {
		if (this.code_int[0] < 0) {
			this.btnToSummary.setEnabled(false);
			return;
		} else {
			this.btnToSummary.setEnabled(true);

			this.cboxMaterial.addItem(Material.CAST_IRON);
			this.cboxMaterial.addItem(Material.GRPH_MALL_CAST_IRON);
			this.cboxMaterial.addItem(Material.STEEL_LESS);
			this.cboxMaterial.addItem(Material.STEEL_MORE);
			this.cboxMaterial.addItem(Material.STEEL_MORE_LESS_HEAT);
			this.cboxMaterial.addItem(Material.STEEL_ALLOY);
			this.cboxMaterial.addItem(Material.STEEL_ALLOY_HEAT);
			this.cboxMaterial.addItem(Material.NON_FER_METAL);
			this.cboxMaterial.addItem(Material.LIGHT_ALLOY);
			this.cboxMaterial.addItem(Material.MISC);

			this.cboxInitForm.addItem(InitForm.BAR_ROUND_BLACK);
			this.cboxInitForm.addItem(InitForm.BAR_ROUND_BRIGHT_DRAWN);
			this.cboxInitForm.addItem(InitForm.BAR_MISC);
			this.cboxInitForm.addItem(InitForm.TUBING);
			this.cboxInitForm.addItem(InitForm.ANGLE);
			this.cboxInitForm.addItem(InitForm.SHEET);
			this.cboxInitForm.addItem(InitForm.PLATE_SLABS);
			this.cboxInitForm.addItem(InitForm.CAST_FORGED);
			this.cboxInitForm.addItem(InitForm.WELDED);
			this.cboxInitForm.addItem(InitForm.PRE_MACHINED);
		}
	}

	/**
	 * Updates the summary page for the wizard.
	 */
	private void updateSummary() {
		this.textPaneSummary.setText("");
		String result = "Your choices were:\n";
		String measures = "";
		// Getting the results for the first digit:
		if (this.answerQ1) {
			result += "Rotational - ";
			measures = "Length: " + this.txtLength.getText() + ", Diameter: "
					+ this.txtDiameter.getText();
		} else {
			result += "Non-Rotational - ";
			measures = "Length: " + this.txtLength.getText() + ", Width: "
					+ this.txtWidth.getText() + ", Height: "
					+ this.txtHeight.getText();
		}
		result += this.cboxExtShape.getSelectedItem().toString();
		result += "\n";
		result += "With measures...\n";
		result += measures;
		result += "\n";
		// Getting the results for the second digit:
		result += this.cboxExtElements.getSelectedItem().toString() + " with "
				+ this.cboxExtElementsStepped.getSelectedItem().toString()
				+ "\n";
		// Getting the results for the third digit:
		result += this.cboxIntElements.getSelectedItem().toString() + "\n";
		// Getting the results for the fourth digit:
		result += this.cboxPlSfMg.getSelectedItem().toString() + "\n";
		// Getting the results for the fifth digit:
		if (this.answerQ5) {
			result += "With teeth, and ";
		} else {
			result += "Without teeth, and ";
		}
		result += this.cboxHolesTeeth.getSelectedItem().toString() + "\n";
		// Getting the results for the seventh and eighth digits:
		result += this.cboxMaterial.getSelectedItem().toString() + "\n";
		result += this.cboxInitForm.getSelectedItem().toString() + "\n";

		// Getting the code:
		result += "Recognized code is " + this.code;

		this.textPaneSummary.setText(result);
	}

	/**
	 * Updates the code string using the values in the code_int array.
	 */
	private void updateCode() {
		this.code = "";
		for (int i = 0; i < this.code_int.length; i++) {
			this.code += this.code_int[i];
		}
	}

	/**
	 * Gets the first digit of the Opitz code based on the user's answers.
	 * 
	 * @return The first digit in the Opitz code.
	 */
	private int getFirstDigit() {

		int result = 0;
		double lByD = 0;
		double aByB = 0;
		double aByC = 0;
		double L = 0;
		double A = 0;
		double D = 0;
		double B = 0;
		double C = 0;
		
		if (!this.txtLength.getText().isEmpty()) {
			L = Double.parseDouble(this.txtLength.getText());
			A = Double.parseDouble(this.txtLength.getText());
		}
		if (!this.txtDiameter.getText().isEmpty()) {
			D = Double.parseDouble(this.txtDiameter.getText());
		}
		if (!this.txtWidth.getText().isEmpty()) {
			B = Double.parseDouble(this.txtWidth.getText());
		}
		if (!this.txtHeight.getText().isEmpty()) {
			C = Double.parseDouble(this.txtHeight.getText());
		}
		
		double[] temp = new double[] { A, B, C };
		// Reordering according to p. 4
		A = this.getMaxWithExclusion(temp);
		B = this.getMaxWithExclusion(temp);
		C = this.getMaxWithExclusion(temp);

		if (this.answerQ1) {
			if (this.txtLength.getText().isEmpty()
					|| this.txtDiameter.getText().isEmpty()) {
				return -1;
			}
			lByD = L / D;
			switch ((ExtShape) this.cboxExtShape.getSelectedItem()) {
			case PURE_CIRCLE_OR_CYLINDER:
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
			case CIRCLE_OR_CYLINDER_WITH_DEVIATIONS:
				if (lByD <= 2) {
					result = 3;
				} else if (lByD > 2) {
					result = 4;
				} else {
					result = -1;
				}
				break;
			default:
				result = -2;
				break;
			}
		} else {
			if (this.txtLength.getText().isEmpty()
					|| this.txtWidth.getText().isEmpty()
					|| this.txtHeight.getText().isEmpty()) {
				return -1;
			}

			aByB = A / B;
			aByC = A / C;

			switch ((ExtShape) this.cboxExtShape.getSelectedItem()) {
			case FLAT_LONG_CUBIC:
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
			default:
				result = -2;
				break;
			}
		}
		
		this.code_int[5] = this.getSixthDigit(result, D, A);

		return result;
	}

	/**
	 * Gets the second Opitz digit based on the user's answers.
	 * 
	 * @return An int containing the second digit of the Opitz code.
	 */
	private int getSecondDigit() {
		int result = -1;
		ExtElements temp1 = (ExtElements) this.cboxExtElements
				.getSelectedItem();
		if (temp1.getCode() >= 0) {
			result = temp1.getCode();
		} else {
			ExtElementsAdv temp2 = (ExtElementsAdv) this.cboxExtElementsStepped
					.getSelectedItem();
			result = temp2.getCode();
		}
		return result;
	}

	/**
	 * Gets the third digit of the Opitz code based on the user's answers.
	 * 
	 * @return An int containing the third digit of the Opitz code for the
	 *         user's query.
	 */
	private int getThirdDigit() {
		IntElements temp = (IntElements) this.cboxIntElements.getSelectedItem();
		return temp.getCode();
	}

	/**
	 * Gets the fourth digit of the Opitz code based on the user's answers.
	 * 
	 * @return An int containing the fourth digit of the Opitz code.
	 */
	private int getFourthDigit() {
		PlaneSurfaceMachining temp = (PlaneSurfaceMachining) this.cboxPlSfMg
				.getSelectedItem();
		return temp.getDigit();
	}

	/**
	 * Gets the fifth digit of the Opitz code based on the user's answers.
	 * 
	 * @return An int containing the fifth digit of the Opitz code.
	 */
	private int getFifthDigit() {
		HolesTeeth temp = (HolesTeeth) this.cboxHolesTeeth.getSelectedItem();
		return temp.getDigit();
	}

	/**
	 * Gets the sixth digit of the Opitz code based on the user's answers in Q1.
	 * 
	 * @return An int containing the sixth digit of the Opitz code.
	 */
	private int getSixthDigit(int firstDigit, double D, double A) {
		double measure = -1;
		if (firstDigit < 5) {
			measure = D;
		} else {
			measure = A;
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
	 * Gets the seventh digit of the Opitz code based on the user's answers.
	 * 
	 * @return An int containing the seventh digit of the Opitz code.
	 */
	private int getSeventhDigit() {
		Material temp = (Material) this.cboxMaterial.getSelectedItem();
		return temp.getDigit();
	}

	/**
	 * Gets the eighth digit of the Opitz code based on the user's answers.
	 * 
	 * @return An int containing the eighth digit of the Opitz code.
	 */
	private int getEighthDigit() {
		InitForm temp = (InitForm) this.cboxInitForm.getSelectedItem();
		return temp.getDigit();
	}

	/**
	 * Gets the ninth digit of the Opitz code based on the user's self-accuracy
	 * answers.
	 * 
	 * @return An int containing the ninth digit of the Opitz code.
	 */
	private int getNinthDigit() {
		boolean d2 = this.chkD2.isSelected();
		boolean d3 = this.chkD3.isSelected();
		boolean d4 = this.chkD4.isSelected();
		boolean d5 = this.chkD5.isSelected();
		if (d2 && d3 && d4 && d5) {
			return 9;
		} else if (d3 && d4) {
			return 8;
		} else if (d2 && d5) {
			return 7;
		} else if (d2 && d4) {
			return 6;
		} else if (d2 && d3) {
			return 5;
		} else if (d5) {
			return 4;
		} else if (d4) {
			return 3;
		} else if (d3) {
			return 2;
		} else if (d2) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Gets a maximum value in a double array and effectively removes it
	 * afterwards (given the array is composed of non-zero integers).
	 * 
	 * @param in
	 *            The incoming array (must consist of zeroes or positive
	 *            integers for exclusion to work).
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
