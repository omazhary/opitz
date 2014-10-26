package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CADModelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_PartName;
	private JTextField textField_Code;
	private JTextField txtImagePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CADModelFrame frame = new CADModelFrame();
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
	public CADModelFrame() {
		setTitle("Part - ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPartName = new JLabel("Part Name:");
		lblPartName.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		textField_PartName = new JTextField();
		textField_PartName.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPartName.setLabelFor(textField_PartName);
		textField_PartName.setColumns(10);
		
		JLabel lblPartGtCode = new JLabel("Part GT Code:");
		lblPartGtCode.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		textField_Code = new JTextField();
		textField_Code.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPartGtCode.setLabelFor(textField_Code);
		textField_Code.setColumns(10);
		
		JLabel lblPartDescription = new JLabel("Part Description:");
		lblPartDescription.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblPartName)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textField_PartName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblPartGtCode)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField_Code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblPartDescription))
							.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPartName)
								.addComponent(textField_PartName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPartGtCode)
								.addComponent(textField_Code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblPartDescription))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/insert_20.png")));
		toolBar.add(btnAdd);
		
		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/save_20.png")));
		toolBar.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/delete_20.png")));
		toolBar.add(btnDelete);
		
		JLabel lblImagePath = new JLabel("Part Image:");
		lblImagePath.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtImagePath = new JTextField();
		lblImagePath.setLabelFor(txtImagePath);
		txtImagePath.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtImagePath.setColumns(10);
		
		JLabel lblImage = new JLabel("");
		
		JButton btnBrowsePartImage = new JButton("Browse");
		btnBrowsePartImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
	            int rVal = c.showOpenDialog(CADModelFrame.this);
	            if (rVal == JFileChooser.APPROVE_OPTION) {
	                txtImagePath.setText(c.getSelectedFile().getAbsolutePath());
	            } else if (rVal == JFileChooser.CANCEL_OPTION) {
	                txtImagePath.setText("File path...");
	            } else {
	            	txtImagePath.setText("An error has occured.");
	            }
			}
		});
		btnBrowsePartImage.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(txtImagePath, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblImagePath)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(btnBrowsePartImage)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblImagePath)
						.addComponent(btnBrowsePartImage))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtImagePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		panel.setLayout(gl_panel);
		
		JTextArea textArea_PartDescription = new JTextArea();
		textArea_PartDescription.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPartDescription.setLabelFor(textArea_PartDescription);
		scrollPane.setViewportView(textArea_PartDescription);
		contentPane.setLayout(gl_contentPane);
	}
}
