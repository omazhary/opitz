package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import recognition.Recognizer;
import sun.awt.VerticalBagLayout;
import utils.CommonUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilePath;
	private Recognizer recog;
	private JTextField txtRecCode;
	private JTextArea textArea_log;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setTitle("Step-Trace");
		setResizable(false);
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		JMenu mnModels = new JMenu("Models");
		mnModels.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuBar.add(mnModels);
		
		JMenuItem mntmAddModel = new JMenuItem("Add Model");
		mntmAddModel.setFont(new Font("Verdana", Font.PLAIN, 12));
		mntmAddModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CADModelFrame modelAdd = new CADModelFrame(true, null);
				modelAdd.setVisible(true);
			}
		});
		mntmAddModel.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/insert_20.png")));
		mnModels.add(mntmAddModel);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.recog = new Recognizer();
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
	            int rVal = c.showOpenDialog(MainWindow.this);
	            if (rVal == JFileChooser.APPROVE_OPTION) {
	                txtFilePath.setText(c.getSelectedFile().getAbsolutePath());
	            } else if (rVal == JFileChooser.CANCEL_OPTION) {
	                txtFilePath.setText("File path...");
	            } else {
	            	txtFilePath.setText("An error has occured.");
	            }
			}
		});
		btnChooseFile.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtFilePath = new JTextField();
		txtFilePath.setHorizontalAlignment(SwingConstants.TRAILING);
		txtFilePath.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtFilePath.setColumns(10);
		
		JLabel lblLog = new JLabel("Log:");
		lblLog.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnRunRecognition = new JButton("Run Recognition");
		btnRunRecognition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recog.mainProcedure(txtFilePath.getText(), false);
				txtRecCode.setText(recog.getCode());
				String temp = "";
				for (int i = 0; i < recog.getTrace().size(); i++) {
					temp += recog.getTrace().get(i);
					if ((i + 1) < recog.getTrace().size()) {
						temp += "\n";
					}
				}
				if (textArea_log.getText().isEmpty() || textArea_log.getText() == null || textArea_log.getText() == "\n") {
					textArea_log.setText(temp);
				} else {
					textArea_log.setText(textArea_log.getText() + "\n" + temp);
				}
			}
		});
		btnRunRecognition.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JScrollPane scrollPane_log = new JScrollPane();
		
		JLabel lblRecognizedOpitzCode = new JLabel("Recognized Opitz Code:");
		lblRecognizedOpitzCode.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtRecCode = new JTextField();
		txtRecCode.setColumns(10);
		
		JScrollPane scrollPane_matches = new JScrollPane();
		
		JLabel lblMatches = new JLabel("Possible Matches:");
		lblMatches.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnChooseFile)
							.addGap(2)
							.addComponent(txtFilePath, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRunRecognition))
						.addComponent(lblLog)
						.addComponent(scrollPane_log, GroupLayout.PREFERRED_SIZE, 783, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRecognizedOpitzCode)
								.addComponent(txtRecCode, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMatches)
								.addComponent(scrollPane_matches, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnChooseFile)
						.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRunRecognition))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
							.addComponent(lblRecognizedOpitzCode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtRecCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(lblMatches)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_matches, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
					.addGap(18)
					.addComponent(lblLog)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_log, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
		);
		
		JList list = new JList();
		list.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPane_matches.setViewportView(list);
		
		textArea_log = new JTextArea();
		scrollPane_log.setViewportView(textArea_log);
		textArea_log.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		contentPane.setLayout(gl_contentPane);
	}
}
