package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class OpitzCodeWizardFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
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
	}

	/**
	 * Create the frame.
	 */
	public OpitzCodeWizardFrame() {
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
		
		JButton btnToDigit2 = new JButton("Next >");
		btnToDigit2.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnBkDigit1 = new JButton("< Back");
		btnBkDigit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnBkDigit1.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panelDigit1 = new GroupLayout(panelDigit1);
		gl_panelDigit1.setHorizontalGroup(
			gl_panelDigit1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelDigit1.createSequentialGroup()
					.addContainerGap(447, Short.MAX_VALUE)
					.addComponent(btnBkDigit1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnToDigit2)
					.addContainerGap())
		);
		gl_panelDigit1.setVerticalGroup(
			gl_panelDigit1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelDigit1.createSequentialGroup()
					.addContainerGap(378, Short.MAX_VALUE)
					.addGroup(gl_panelDigit1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToDigit2)
						.addComponent(btnBkDigit1))
					.addContainerGap())
		);
		panelDigit1.setLayout(gl_panelDigit1);
	}
}
