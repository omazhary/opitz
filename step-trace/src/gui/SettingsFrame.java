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
		
		JPanel panelSim = new JPanel();
		tabbedPane.addTab("Similarity", null, panelSim, null);
		tabbedPane.setEnabledAt(0, true);
		GroupLayout gl_panelSim = new GroupLayout(panelSim);
		gl_panelSim.setHorizontalGroup(
			gl_panelSim.createParallelGroup(Alignment.LEADING)
				.addGap(0, 625, Short.MAX_VALUE)
		);
		gl_panelSim.setVerticalGroup(
			gl_panelSim.createParallelGroup(Alignment.LEADING)
				.addGap(0, 442, Short.MAX_VALUE)
		);
		panelSim.setLayout(gl_panelSim);
	}

}
