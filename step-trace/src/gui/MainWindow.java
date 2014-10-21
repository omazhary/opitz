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

import recognition.Recognizer;
import sun.awt.VerticalBagLayout;
import utils.CommonUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilePath;
	private Recognizer recog;
	private JTextField txtRecCode;

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
		txtFilePath.setText("File path...");
		txtFilePath.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtFilePath.setColumns(10);
		
		final JTextArea textArea_log = new JTextArea();
		textArea_log.setWrapStyleWord(true);
		
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
				textArea_log.setText(temp);
			}
		});
		btnRunRecognition.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane(textArea_log);
		
		JLabel lblRecognizedOpitzCode = new JLabel("Recognized Opitz Code:");
		lblRecognizedOpitzCode.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtRecCode = new JTextField();
		txtRecCode.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnChooseFile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFilePath, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRunRecognition))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblLog)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRecognizedOpitzCode)
					.addContainerGap(728, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtRecCode, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(554, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnChooseFile)
						.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRunRecognition))
					.addGap(29)
					.addComponent(lblRecognizedOpitzCode)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtRecCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
					.addComponent(lblLog)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
		);
		
		
		scrollPane.setColumnHeaderView(textArea_log);
		textArea_log.setRows(10);
		textArea_log.setColumns(50);
		textArea_log.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		contentPane.setLayout(gl_contentPane);
	}
}
