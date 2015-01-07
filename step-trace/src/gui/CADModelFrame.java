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
import javax.swing.AbstractButton;
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
import entities.InitForm;
import entities.Material;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Window.Type;

import javax.swing.SwingConstants;

import recognition.Recognizer;
import utils.Preferences;

import javax.swing.JComboBox;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CADModelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtPartName;
	private JTextField txtCode;
	private JTextField txtImagePath;
	private JLabel lblImage;
	private JTextField txtFilePath;
	private JTextArea txtAreaPartDescription;
	private JLabel lblStatus;
	private JComboBox cboxMaterial;
	private JComboBox cboxInitForm;
	private JLabel lblIdentifier;
	
	private Preferences pref;	
	private Recognizer recog;
	private CADModel cadModel;
	private CADModelListFrame list_CAD;

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
	@SuppressWarnings("unchecked")
	public CADModelFrame(boolean editable, String identifier, CADModelListFrame list, final Preferences pref) {
		
		this.list_CAD = list;
		this.pref = pref;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (list_CAD != null) {
					list_CAD.retrieveModels();
				}
			}
		});
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
		
		lblIdentifier = new JLabel("");
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
	            recog.mainProcedure(txtFilePath.getText(), false, (Material) cboxMaterial.getSelectedItem(), (InitForm) cboxInitForm.getSelectedItem(), pref);
	            txtCode.setText(recog.getCode());
			}
		});
		btnBrowsePartFile.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		lblStatus = new JLabel("Ready...");
		lblStatus.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxMaterial = new JComboBox();
		cboxMaterial.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblInitialForm = new JLabel("Initial Form:");
		lblInitialForm.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxInitForm = new JComboBox();
		cboxInitForm.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPartDescription)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblPartIdentifier)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblIdentifier, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
										.addComponent(lblMaterial)
										.addComponent(cboxMaterial, 0, 335, Short.MAX_VALUE)
										.addComponent(lblInitialForm)
										.addComponent(cboxInitForm, 0, 335, Short.MAX_VALUE)
										.addComponent(lblPartGtCode)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblPartPath)
											.addGap(27)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtCode, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(btnBrowsePartFile, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblPartName)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtPartName, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
							.addGap(10))))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPartIdentifier)
								.addComponent(lblIdentifier, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPartName)
								.addComponent(txtPartName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblMaterial)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboxMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInitialForm)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboxInitForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPartPath)
								.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBrowsePartFile))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPartGtCode)
								.addComponent(txtCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addComponent(lblPartDescription)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(lblStatus)
					.addContainerGap())
		);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadModel = new CADModel(txtPartName.getText(), txtCode.getText(), txtImagePath.getText(), txtAreaPartDescription.getText(), txtFilePath.getText());
				if (cadModel.writeModelData()) {
					lblStatus.setText("Model saved successfully.");
				} else {
					lblStatus.setText("Model was not saved!!");
				}
			}
		});
		btnAdd.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAdd.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/insert_20.png")));
		toolBar.add(btnAdd);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String identifier = lblIdentifier.getText();
				cadModel.setPartName(txtPartName.getText());
				cadModel.setPartPath(txtFilePath.getText());
				cadModel.setPartGTCode(txtCode.getText());
				cadModel.setPartDescription(txtAreaPartDescription.getText());
				cadModel.setPartImagePath(txtImagePath.getText());
				if (cadModel.updateModelData()) {
					lblStatus.setText("Model saved successfully.");
				} else {
					lblStatus.setText("Model was not saved!!");
				}
			}
		});
		btnSave.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSave.setIcon(new ImageIcon(CADModelFrame.class.getResource("/icons/save_20.png")));
		toolBar.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String identifier = lblIdentifier.getText();
				if (cadModel.deleteModel()) {
					lblStatus.setText("Model deleted successfully.");
				} else {
					lblStatus.setText("Model was not deleted!!");
				}
			}
		});
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
		
		this.cboxMaterial.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
		this.cboxInitForm.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
		
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
		
		if(!editable) {
			txtPartName.setEditable(false);
			txtCode.setEditable(false);
			txtImagePath.setEditable(false);
			txtAreaPartDescription.setEditable(false);
			txtFilePath.setEditable(false);
			//this.cboxMaterial.setEnabled(false);
			this.setComboBoxReadOnly(this.cboxMaterial);
			//this.cboxInitForm.setEnabled(false);
			this.setComboBoxReadOnly(this.cboxInitForm);
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnDelete.setEnabled(false);
			btnBrowsePartFile.setEnabled(false);
			btnBrowsePartImage.setEnabled(false);
		}
		
		if (identifier != null) {
			this.cadModel = new CADModel(identifier);
			this.setTitle(identifier + " - " + this.cadModel.getPartName());
			this.txtPartName.setText(this.cadModel.getPartName());
			this.txtCode.setText(this.cadModel.getPartGTCode());
			this.txtImagePath.setText(this.cadModel.getPartImagePath());
			this.txtAreaPartDescription.setText(this.cadModel.getPartDescription());
			this.txtFilePath.setText(this.cadModel.getPartPath());
			this.updateImageLabel(this.lblImage);
			lblIdentifier.setText(identifier);
			String material = this.cadModel.getPartGTCode().charAt(5) + "";
			String initForm = this.cadModel.getPartGTCode().charAt(6) + "";
			this.cboxMaterial.setSelectedIndex(Integer.parseInt(material));
			this.cboxInitForm.setSelectedIndex(Integer.parseInt(initForm));
			btnAdd.setEnabled(false);
		} else {
			this.setTitle("New Model");
			btnSave.setEnabled(false);
			btnDelete.setEnabled(false);
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
			if (label.getWidth() == 0 || label.getHeight() == 0) {
				label.setSize(240, 175);
			}
			Image image_scaled = image_raw.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_FAST);
			ImageIcon image_final = new ImageIcon(image_scaled);
			label.setIcon(image_final);
		}
		return;
	}
	
	private void setComboBoxReadOnly(JComboBox box) {
		JTextField txt = (JTextField) box.getEditor().getEditorComponent();
		txt.setEditable(false);
		for (Object temp : box.getComponents()) {
			if (temp instanceof AbstractButton) {
				AbstractButton x = (AbstractButton) temp;
				x.setEnabled(false);
				for (ActionListener lis : box.getActionListeners()) {
					box.removeActionListener(lis);
				}
				for (MouseListener lis : box.getMouseListeners()) {
					box.removeMouseListener(lis);
				}
				for (MouseMotionListener lis : box.getMouseMotionListeners()) {
					box.removeMouseMotionListener(lis);
				}
				for (MouseWheelListener lis : box.getMouseWheelListeners()) {
					box.removeMouseWheelListener(lis);
				}
			}
		}
	}
}
