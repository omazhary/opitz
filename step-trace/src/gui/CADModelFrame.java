package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
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

import entities.CADModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Window.Type;
import javax.swing.SwingConstants;

import recognition.Recognizer;

public class CADModelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtPartName;
	private JTextField txtCode;
	private JTextField txtImagePath;
	private JLabel lblImage;
	private JTextField txtFilePath;
	private JTextArea txtAreaPartDescription;
	
	private Recognizer recog;
	private CADModel cadModel;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CADModelFrame frame = new CADModelFrame(Boolean.parseBoolean(args[0]));
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
	public CADModelFrame(boolean editable, String identifier) {
		this.recog = new Recognizer();
		
		setAlwaysOnTop(true);
		setType(Type.NORMAL);
		setTitle("Part - ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPartName = new JLabel("Part Name:");
		lblPartName.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtPartName = new JTextField();
		txtPartName.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPartName.setLabelFor(txtPartName);
		txtPartName.setColumns(10);
		
		JLabel lblPartGtCode = new JLabel("Part GT Code:");
		lblPartGtCode.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPartGtCode.setLabelFor(txtCode);
		txtCode.setColumns(10);
		
		JLabel lblPartDescription = new JLabel("Part Description:");
		lblPartDescription.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblPartIdentifier = new JLabel("Part Identifier:");
		lblPartIdentifier.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblIdentifier = new JLabel("");
		lblIdentifier.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblPartPath = new JLabel("Part Path:");
		lblPartPath.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtFilePath = new JTextField();
		txtFilePath.setHorizontalAlignment(SwingConstants.TRAILING);
		txtFilePath.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtFilePath.setColumns(10);
		
		JButton btnBrowsePartFile = new JButton("Browse");
		btnBrowsePartFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
	            int rVal = c.showOpenDialog(CADModelFrame.this);
	            if (rVal == JFileChooser.APPROVE_OPTION) {
	                txtFilePath.setText(c.getSelectedFile().getAbsolutePath());
	            } else if (rVal == JFileChooser.CANCEL_OPTION) {
	                txtFilePath.setText("File path...");
	            } else {
	            	txtFilePath.setText("An error has occured.");
	            }
	            recog.mainProcedure(txtFilePath.getText(), false);
	            txtCode.setText(recog.getCode());
			}
		});
		btnBrowsePartFile.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPartDescription)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPartIdentifier)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPartPath)
												.addComponent(lblPartName))
											.addGap(27)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtPartName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(txtCode))
														.addComponent(txtFilePath))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(btnBrowsePartFile))))
										.addComponent(lblPartGtCode))
									.addGap(38)
									.addComponent(lblIdentifier, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
					.addGap(8))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIdentifier, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPartIdentifier)
									.addGap(11)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPartName)
										.addComponent(txtPartName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPartPath)
										.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBrowsePartFile))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPartGtCode)
										.addComponent(txtCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(129)
							.addComponent(lblPartDescription))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadModel = new CADModel(txtPartName.getText(), txtCode.getText(), txtImagePath.getText(), txtAreaPartDescription.getText(), txtFilePath.getText());
				cadModel.writeModelData();
			}
		});
		btnAdd.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAdd.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/insert_20.png")));
		toolBar.add(btnAdd);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSave.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/save_20.png")));
		toolBar.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDelete.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/delete_20.png")));
		toolBar.add(btnDelete);
		
		JLabel lblImagePath = new JLabel("Part Image:");
		lblImagePath.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtImagePath = new JTextField();
		lblImagePath.setLabelFor(txtImagePath);
		txtImagePath.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtImagePath.setColumns(10);
		
		lblImage = new JLabel("");
		
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
	            updateImageLabel(lblImage);
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
		
		txtAreaPartDescription = new JTextArea();
		txtAreaPartDescription.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPartDescription.setLabelFor(txtAreaPartDescription);
		scrollPane.setViewportView(txtAreaPartDescription);
		contentPane.setLayout(gl_contentPane);
		
		updateImageLabel(lblImage);
		
		if(!editable) {
			txtPartName.setEditable(false);
			txtCode.setEditable(false);
			txtImagePath.setEditable(false);
			txtAreaPartDescription.setEditable(false);
		}
		
		if (identifier != null) {
			this.cadModel = new CADModel(identifier);
			this.setTitle(this.cadModel.getPartName());
		} else {
			this.setTitle("New Model");
		}
	}
	
	private void updateImageLabel(JLabel label) {
		if (!txtImagePath.getText().isEmpty()) {
			BufferedImage image_raw = null;
			try {
				image_raw = ImageIO.read(new File(txtImagePath.getText()));				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Image image_scaled = image_raw.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_FAST);
			ImageIcon image_final = new ImageIcon(image_scaled);
			label.setIcon(image_final);
		}
		return;
	}
}
