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

import entities.ExtElements;
import entities.ExtElementsStepped;
import entities.ExtShape;
import entities.HolesTeeth;
import entities.InitForm;
import entities.IntElements;
import entities.Material;
import entities.PlaneSurfaceMachining;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

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
	@SuppressWarnings({ "unchecked", "rawtypes", "rawtypes" })
	public OpitzCodeWizardFrame() {
		
		this.code_int = new int [9];
		this.code = "";
		
		for (int i = 0; i < this.code_int.length; i++) {
			this.code_int[i] = -1;
		}
		
		setTitle("Opitz Code Recognition Wizard");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
						.addComponent(txtpnThisWizardWill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(185, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelWelcome.createSequentialGroup()
					.addContainerGap(698, Short.MAX_VALUE)
					.addComponent(btnToFirstDigit)
					.addContainerGap())
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
					.addPreferredGap(ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
					.addComponent(btnToFirstDigit)
					.addContainerGap())
		);
		panelWelcome.setLayout(gl_panelWelcome);
		
		JPanel panelDigit1 = new JPanel();
		tabbedPane.addTab("Part Class", null, panelDigit1, null);
		
		JPanel panelDigit2 = new JPanel();
		tabbedPane.addTab("External Shape", null, panelDigit2, null);
		
		panelD2O1 = new JPanel();
		panelD2O1.setVisible(false);
		
		btnToDigit3 = new JButton("Next >");
		btnToDigit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[1] = getSecondDigit();
				code += code_int[1];
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
		gl_panelDigit2.setHorizontalGroup(
			gl_panelDigit2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDigit2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit2.createParallelGroup(Alignment.LEADING)
						.addComponent(panelD2O1, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panelDigit2.createSequentialGroup()
							.addComponent(btnBkDigit2)
							.addPreferredGap(ComponentPlacement.RELATED, 443, Short.MAX_VALUE)
							.addComponent(btnToDigit3)))
					.addContainerGap())
		);
		gl_panelDigit2.setVerticalGroup(
			gl_panelDigit2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelDigit2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelD2O1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(gl_panelDigit2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToDigit3)
						.addComponent(btnBkDigit2))
					.addContainerGap())
		);
		
		JLabel lblD2O1Q1 = new JLabel("1. How would you describe the external shape of the part?");
		lblD2O1Q1.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxExtElements = new JComboBox();
		
		cboxExtElementsStepped = new JComboBox();
		
		cboxExtElements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCboxExtElementsStepped();
			}
		});
		cboxExtElements.setFont(new Font("Verdana", Font.PLAIN, 12));
		this.cboxExtElements.addItem(ExtElements.SMOOTH);
		this.cboxExtElements.addItem(ExtElements.STEPPED_ONE_SIDE);
		this.cboxExtElements.addItem(ExtElements.STEPPED_BOTH_SIDES);
		this.cboxExtElements.addItem(ExtElements.FUNCTION_CONE);
		this.cboxExtElements.addItem(ExtElements.OPERATING_THREAD);
		this.cboxExtElements.addItem(ExtElements.MISC);
		
		JLabel lblAreThere = new JLabel("2. Are there any additional shape details?");
		lblAreThere.setFont(new Font("Verdana", Font.PLAIN, 12));		
		
		cboxExtElementsStepped.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelD2O1 = new GroupLayout(panelD2O1);
		gl_panelD2O1.setHorizontalGroup(
			gl_panelD2O1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelD2O1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelD2O1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelD2O1.createSequentialGroup()
							.addGap(10)
							.addComponent(cboxExtElementsStepped, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelD2O1.createSequentialGroup()
							.addGap(10)
							.addComponent(cboxExtElements, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblD2O1Q1)
						.addComponent(lblAreThere))
					.addContainerGap(217, Short.MAX_VALUE))
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
					.addContainerGap(240, Short.MAX_VALUE))
		);
		panelD2O1.setLayout(gl_panelD2O1);
		panelDigit2.setLayout(gl_panelDigit2);
		
		btnToDigit2 = new JButton("Next >");
		btnToDigit2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnToDigit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				code_int[0] = getFirstDigit();
				code += code_int[0];
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
		
		JPanel panelDigit3 = new JPanel();
		tabbedPane.addTab("Internal Shape", null, panelDigit3, null);
		
		btnToDigit4 = new JButton("Next >");
		btnToDigit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[2] = getThirdDigit();
				code += code_int[2];
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
		gl_panelDigit3.setHorizontalGroup(
			gl_panelDigit3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDigit3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit3.createParallelGroup(Alignment.LEADING)
						.addComponent(panelD3O1, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
						.addGroup(gl_panelDigit3.createSequentialGroup()
							.addComponent(btnBkDigit3)
							.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
							.addComponent(btnToDigit4)))
					.addContainerGap())
		);
		gl_panelDigit3.setVerticalGroup(
			gl_panelDigit3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelDigit3.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelD3O1, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDigit3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToDigit4)
						.addComponent(btnBkDigit3))
					.addContainerGap())
		);
		
		JLabel label = new JLabel("1. How would you describe the internal shape of the part?");
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxIntElements = new JComboBox();
		cboxIntElements.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panelD3O1);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(cboxIntElements, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label))
					.addGap(221))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxIntElements, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(307, Short.MAX_VALUE))
		);
		panelD3O1.setLayout(gl_panel);
		panelDigit3.setLayout(gl_panelDigit3);
		
		JPanel panelDigit4 = new JPanel();
		tabbedPane.addTab("Plane Surface Machining", null, panelDigit4, null);
		
		btnToDigit5 = new JButton("Next >");
		btnToDigit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[3] = getFourthDigit();
				code += code_int[3];
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
		
		lblHowWouldYou = new JLabel("How would you describe the part's plane surface machining?");
		lblHowWouldYou.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxPlSfMg = new JComboBox();
		cboxPlSfMg.setFont(new Font("Verdana", Font.PLAIN, 12));
		//GroupLayout gl_panel;
		gl_panelDigit4 = new GroupLayout(panelDigit4);
		gl_panelDigit4.setHorizontalGroup(
			gl_panelDigit4.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelDigit4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDigit4.createSequentialGroup()
							.addGap(10)
							.addComponent(cboxPlSfMg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDigit4.createSequentialGroup()
							.addComponent(btnBkDigit4)
							.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
							.addComponent(btnToDigit5))
						.addComponent(lblHowWouldYou))
					.addContainerGap())
		);
		gl_panelDigit4.setVerticalGroup(
			gl_panelDigit4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDigit4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHowWouldYou)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxPlSfMg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
					.addGroup(gl_panelDigit4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToDigit5)
						.addComponent(btnBkDigit4))
					.addContainerGap())
		);
		panelDigit4.setLayout(gl_panelDigit4);
		
		JPanel panelDigit5 = new JPanel();
		tabbedPane.addTab("Auxiliary Hole(s) and Gear Teeth", null, panelDigit5, null);
		
		btnToDigit78 = new JButton("Next >");
		btnToDigit78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code_int[4] = getFifthDigit();
				code += code_int[4];
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
		
		JLabel lblD5Q2 = new JLabel("2. How would you describe the part's holes/teeth?");
		lblD5Q2.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxHolesTeeth = new JComboBox();
		cboxHolesTeeth.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelDigit5 = new GroupLayout(panelDigit5);
		gl_panelDigit5.setHorizontalGroup(
			gl_panelDigit5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDigit5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblD5Q1, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelDigit5.createSequentialGroup()
							.addGap(12)
							.addComponent(rdbtnQ5Yes, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(rdbtnQ5No, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblD5Q2, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelDigit5.createSequentialGroup()
							.addGap(12)
							.addComponent(cboxHolesTeeth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panelDigit5.createSequentialGroup()
							.addComponent(btnBkDigit5)
							.addPreferredGap(ComponentPlacement.RELATED, 609, Short.MAX_VALUE)
							.addComponent(btnToDigit78)))
					.addContainerGap())
		);
		gl_panelDigit5.setVerticalGroup(
			gl_panelDigit5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDigit5.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblD5Q1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_panelDigit5.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnQ5Yes, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnQ5No, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblD5Q2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(cboxHolesTeeth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(400)
					.addGroup(gl_panelDigit5.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToDigit78)
						.addComponent(btnBkDigit5))
					.addContainerGap())
		);
		panelDigit5.setLayout(gl_panelDigit5);
		
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
		
		JPanel panelDigit78 = new JPanel();
		tabbedPane.addTab("Material and Initial Form", null, panelDigit78, null);
		
		JLabel lblWhatMaterial = new JLabel("1. What material should the part be made of?");
		lblWhatMaterial.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxMaterial = new JComboBox();
		cboxMaterial.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblWhatIs = new JLabel("2. What is the initial form of the material you chose above?");
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
				code_int[5] = getSixthDigit();
				code += code_int[5];
				code_int[6] = getSeventhDigit();
				code += code_int[6];
				code_int[7] = getEighthDigit();
				code += code_int[7];
				code_int[8] = getNinthDigit();
				code += code_int[8];
				updateSummary();
				tabbedPane.setSelectedIndex(7);
			}
		});
		btnToSummary.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelDigit78 = new GroupLayout(panelDigit78);
		gl_panelDigit78.setHorizontalGroup(
			gl_panelDigit78.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDigit78.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDigit78.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDigit78.createSequentialGroup()
							.addGap(12)
							.addComponent(cboxInitForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelDigit78.createSequentialGroup()
							.addGap(12)
							.addComponent(cboxMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblWhatMaterial)
						.addComponent(lblWhatIs)
						.addGroup(gl_panelDigit78.createSequentialGroup()
							.addComponent(btnBkDigit78)
							.addPreferredGap(ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
							.addComponent(btnToSummary)))
					.addContainerGap())
		);
		gl_panelDigit78.setVerticalGroup(
			gl_panelDigit78.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDigit78.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWhatMaterial)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblWhatIs)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxInitForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
					.addGroup(gl_panelDigit78.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBkDigit78)
						.addComponent(btnToSummary))
					.addContainerGap())
		);
		panelDigit78.setLayout(gl_panelDigit78);
		
		this.answerQ1 = true;
		this.answerQ5 = true;
		
		this.updateD1Components();
		this.updateD2Components();
		this.updateD3Components();
		this.updateD4Components();
		this.updateD5Components();
		this.updateD78Components();
		
		this.updateCboxExtElementsStepped();
		
		/* Custom code ends here. */
	
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
	 * Updates the elements in the combo box for external elements (stepped). 
	 */
	@SuppressWarnings("unchecked")
	private void updateCboxExtElementsStepped() {
		this.cboxExtElementsStepped.removeAllItems();
		switch ((ExtElements) this.cboxExtElements.getSelectedItem()) {
			case SMOOTH:
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.NONE);
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.SCREW_THREADS);
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.FUNCTIONAL_GROOVE);
				break;
			case STEPPED_ONE_SIDE:
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.SMOOTH);
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.SCREW_THREADS);
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.FUNCTIONAL_GROOVE);
				break;
			case STEPPED_BOTH_SIDES:
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.SMOOTH);
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.SCREW_THREADS);
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.FUNCTIONAL_GROOVE);
				break;
			default:
				this.cboxExtElementsStepped.addItem(ExtElementsStepped.NONE);
					break;
		}
	}
	
	/**
	 * Populates the combo box with internal shape elements.
	 */
	@SuppressWarnings({ "unchecked" })
	private void updateCboxIntElements() {
		this.cboxIntElements.removeAllItems();
		this.cboxIntElements.addItem(IntElements.NO_HOLE_BREAKTHROUGH);
		if (this.code_int[1] < 4 || (this.code_int[1] < 7 && this.code_int[1] > 3)) {
			this.cboxIntElements.addItem(IntElements.NONE);
			this.cboxIntElements.addItem(IntElements.THREAD);
			this.cboxIntElements.addItem(IntElements.FUNCTIONAL_GROOVE);
		}
		this.cboxIntElements.addItem(IntElements.FUNCTION_CONE);
		this.cboxIntElements.addItem(IntElements.OPERATING_THREAD);
		this.cboxIntElements.addItem(IntElements.MISC);
	}
	
	/**
	 * Determines which panel to show depending on the value of the first digit.
	 */
	private void updateD2Components () {
		if (this.code_int[0] < 0) {
			this.btnToDigit3.setEnabled(false);
			return;
		} else {
			this.btnToDigit3.setEnabled(true);
			if (code_int[0] < 3) {
				this.panelD2O1.setVisible(true);
			}
		}		
	}
	
	/**
	 * Determines which panel to show depending on the value of the first digit.
	 */
	private void updateD3Components() {
		if(this.code_int[0] < 0 || this.code_int[1] < 0) {
			this.btnToDigit4.setEnabled(false);
			return;
		} else {
			this.btnToDigit4.setEnabled(true);
			if (code_int[0] < 3) {
				this.updateCboxIntElements();
				this.panelD3O1.setVisible(true);
			}
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
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.CURVED_ONE_DIRECTION);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.CURVED_CIRCLE);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.EXT_GROOVE_SLOT);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.EXT_SPLINE);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.EXT_SPLINE_SLOT);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.INT_PLANE_SLOT);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.INT_SPLINE);
				this.cboxPlSfMg.addItem(PlaneSurfaceMachining.INT_EXT_POLYGON_GROOVE_SLOT);
			}
			this.cboxPlSfMg.addItem(PlaneSurfaceMachining.MISC);
		}
	}
	
	/**
	 * Decides what components to show based on the first digit's value and populates the combo boxes accordingly.
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
					this.cboxHolesTeeth.addItem(HolesTeeth.MISC);
				} else {
					this.cboxHolesTeeth.addItem(HolesTeeth.NONE_1);
					this.cboxHolesTeeth.addItem(HolesTeeth.HOLES_AXIAL_UNRELATED);
					this.cboxHolesTeeth.addItem(HolesTeeth.HOLES_AXIAL_RELATED);
					this.cboxHolesTeeth.addItem(HolesTeeth.HOLES_RADIAL_UNRELATED);
					this.cboxHolesTeeth.addItem(HolesTeeth.HOLES_AXIAL_RADIAL_UNRELATED);
					this.cboxHolesTeeth.addItem(HolesTeeth.HOLES_AXIAL_RADIAL_RELATED);
				}
			}
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
	 * Gets the second Opitz digit based on the user's answers.
	 * @return An int containing the second digit of the Opitz code.
	 */
	private int getSecondDigit() {
		int result = -1;
		if (this.code_int[0] < 3) {
			switch ((ExtElements) this.cboxExtElements.getSelectedItem()) {
				case SMOOTH:
					switch ((ExtElementsStepped) this.cboxExtElementsStepped.getSelectedItem()) {
						case NONE:
							result = 0;
							break;
						case SCREW_THREADS:
							result = 2;
							break;
						case FUNCTIONAL_GROOVE:
							result = 3;
							break;
						default:
							result = -2;
							break;
					}
					break;
				case STEPPED_ONE_SIDE:
					switch ((ExtElementsStepped) this.cboxExtElementsStepped.getSelectedItem()) {
						case SMOOTH:
							result = 1;
							break;
						case SCREW_THREADS:
							result = 2;
							break;
						case FUNCTIONAL_GROOVE:
							result = 3;
							break;
						default:
							result = -2;
							break;
					}
					break;
				case STEPPED_BOTH_SIDES:
					switch ((ExtElementsStepped) this.cboxExtElementsStepped.getSelectedItem()) {
						case SMOOTH:
							result = 1;
							break;
						case SCREW_THREADS:
							result = 2;
							break;
						case FUNCTIONAL_GROOVE:
							result = 3;
							break;
						default:
							result = -2;
							break;
					}
					break;
				case FUNCTION_CONE:
					result = 7;
					break;
				case OPERATING_THREAD:
					result = 8;
					break;
				case MISC:
					result = 9;
					break;
				default:
					result = -2;
					break;
			}
		}
		return result;
	}
	
	/**
	 * Gets the third digit of the Opitz code based on the user's answers.
	 * @return An int containing the third digit of the Opitz code for the user's query.
	 */
	private int getThirdDigit() {
		int result = -1;
		if (this.code_int[0] < 3) {
			switch ((IntElements) this.cboxIntElements.getSelectedItem()) {
				case NO_HOLE_BREAKTHROUGH:
					result = 0;
					break;
				case NONE:
					if (this.code_int[1] < 4) {
						result = 1;
					} else {
						result = 4;
					}
					break;
				case THREAD:
					if (this.code_int[1] < 4) {
						result = 2;
					} else {
						result = 5;
					}
					break;
				case FUNCTIONAL_GROOVE:
					if (this.code_int[1] < 4) {
						result = 3;
					} else {
						result = 6;
					}
					break;
				case FUNCTION_CONE:
					result = 7;
					break;
				case OPERATING_THREAD:
					result = 8;
					break;
				case MISC:
					result = 9;
					break;
				default:
					result = -2;
					break;
			}
		}
		return result;
	}
	
	/**
	 * Gets the fourth digit of the Opitz code based on the user's answers.
	 * @return An int containing the fourth digit of the Opitz code.
	 */
	private int getFourthDigit() {
		PlaneSurfaceMachining temp = (PlaneSurfaceMachining) this.cboxPlSfMg.getSelectedItem(); 
		return temp.getDigit();
	}
	
	/**
	 * Gets the fifth digit of the Opitz code based on the user's answers.
	 * @return An int containing the fifth digit of the Opitz code.
	 */
	private int getFifthDigit() {
		HolesTeeth temp = (HolesTeeth) this.cboxHolesTeeth.getSelectedItem(); 
		return temp.getDigit();
	}
	
	/**
	 * Gets the sixth digit of the Opitz code based on the user's answers in Q1.
	 * @return An int containing the sixth digit of the Opitz code.
	 */
	private int getSixthDigit() {
		return 0;
	}
	
	/**
	 * Gets the seventh digit of the Opitz code based on the user's answers.
	 * @return An int containing the seventh digit of the Opitz code.
	 */
	private int getSeventhDigit() {
		Material temp = (Material) this.cboxMaterial.getSelectedItem(); 
		return temp.getDigit();
	}
	
	/**
	 * Gets the eighth digit of the Opitz code based on the user's answers.
	 * @return An int containing the eighth digit of the Opitz code.
	 */
	private int getEighthDigit() {
		InitForm temp = (InitForm) this.cboxInitForm.getSelectedItem(); 
		return temp.getDigit();
	}
	
	/**
	 * Gets the ninth digit of the Opitz code based on the user's self-accuracy answers.
	 * @return An int containing the ninth digit of the Opitz code.
	 */
	private int getNinthDigit() {
		return 0;
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
