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
import similarity.Opitz;
import similarity.SimilarityCalculator;
import sun.awt.VerticalBagLayout;
import utils.CommonUtils;
import utils.Preferences;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSlider;

import entities.CADModel;
import entities.CADModelList;
import entities.InitForm;
import entities.Material;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilePath;
	private Recognizer recog;
	private JTextField txtRecCode;
	private JTextArea textArea_log;
	private JTable tableSimModels;
	private JSlider sliderSimThreshold;
	private Preferences pref;
	private JComboBox cboxMaterial;
	private JComboBox cboxInitForm;

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
	@SuppressWarnings("unchecked")
	public MainWindow() {
		setTitle("Step-Trace");
		setResizable(false);
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.pref = new Preferences();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Verdana", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Dump Log to File");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
				c.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
				c.setFileHidingEnabled(true);
	            int rVal = c.showOpenDialog(MainWindow.this);
	            String path = "";
	            if (rVal == JFileChooser.APPROVE_OPTION) {
	            	if (!c.getSelectedFile().getAbsolutePath().endsWith(".txt")) {
	            		path = c.getSelectedFile().getAbsolutePath() + ".txt";
	            	} else {
	            		path = c.getSelectedFile().getAbsolutePath();
	            	}
	            	try {
						BufferedOutputStream dump_out = new BufferedOutputStream(new FileOutputStream(new File(path)));
						dump_out.write(textArea_log.getText().getBytes());
						dump_out.flush();
						dump_out.close();
						printToLog("Dump Successful: Log dumped to " + path);
					} catch (FileNotFoundException e1) {
						printToLog("Error: Unable to open dump file.");
					} catch (IOException e1) {
						printToLog("Error: Unable to write to dump file.");
					}
	            } else if (rVal == JFileChooser.CANCEL_OPTION) {
	            	printToLog("Log dump cancelled.");
	            } else {
	            	printToLog("An error has occured during log dump.");
	            }
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/save_20.png")));
		mntmNewMenuItem.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnFile.add(mntmNewMenuItem);
		mntmQuit.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/quit_20.png")));
		mntmQuit.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnFile.add(mntmQuit);
		
		JMenu mnModels = new JMenu("Models");
		mnModels.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuBar.add(mnModels);
		
		JMenuItem mntmAddModel = new JMenuItem("Add Model");
		mntmAddModel.setFont(new Font("Verdana", Font.PLAIN, 12));
		mntmAddModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CADModelFrame modelAdd = new CADModelFrame(true, null, null, pref);
				modelAdd.pack();
				modelAdd.setVisible(true);
			}
		});
		mntmAddModel.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/insert_20.png")));
		mnModels.add(mntmAddModel);
		
		JMenuItem mntmViewAvailableModels = new JMenuItem("View Available Models");
		mntmViewAvailableModels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CADModelListFrame model_list = new CADModelListFrame(pref);
				model_list.setVisible(true);
			}
		});
		mntmViewAvailableModels.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/view_20.png")));
		mntmViewAvailableModels.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnModels.add(mntmViewAvailableModels);
		
		JMenu mnRecognition = new JMenu("Recognition");
		mnRecognition.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuBar.add(mnRecognition);
		
		JMenuItem mntmLoadStepFile = new JMenuItem("Load *.stp File");
		mntmLoadStepFile.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/open_20.png")));
		mntmLoadStepFile.addActionListener(new ActionListener() {
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
		mntmLoadStepFile.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnRecognition.add(mntmLoadStepFile);
		
		JMenuItem mntmRecogWizard = new JMenuItem("Use Recognition Wizard");
		mntmRecogWizard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpitzCodeWizardFrame wizard = new OpitzCodeWizardFrame(txtRecCode);
				wizard.setVisible(true);
			}
		});
		mntmRecogWizard.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/wizard_20.png")));
		mntmRecogWizard.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnRecognition.add(mntmRecogWizard);
		
		JMenu mnTools = new JMenu("Tools");
		mnTools.setFont(new Font("Verdana", Font.PLAIN, 12));
		menuBar.add(mnTools);
		
		JMenuItem mntmAdvancedOptions = new JMenuItem("Advanced Options");
		mntmAdvancedOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsFrame settings = new SettingsFrame(pref);
				settings.setVisible(true);
			}
		});
		
		JMenu mnDtSrc = new JMenu("Data Sources");
		mnDtSrc.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/data_20.png")));
		mnTools.add(mnDtSrc);
		mnDtSrc.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JMenu mnXML = new JMenu("XML Options");
		mnXML.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/xml_20.png")));
		mnXML.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnDtSrc.add(mnXML);
		
		JMenuItem mntmXMLImport = new JMenuItem("Import XML Index");
		mntmXMLImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
				c.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
				c.setFileHidingEnabled(true);
	            int rVal = c.showOpenDialog(MainWindow.this);
	            if (rVal == JFileChooser.APPROVE_OPTION) {
	            	CADModelList model_list = new CADModelList();
					if (model_list.importModelList(c.getSelectedFile().getAbsolutePath())) {
						printToLog(c.getSelectedFile().getAbsolutePath() + " imported successfully.");
					} else {
						printToLog("Unable to import " + c.getSelectedFile().getAbsolutePath());
					}
	            } else if (rVal == JFileChooser.CANCEL_OPTION) {
	            	printToLog("File import cancelled.");
	            } else {
	            	printToLog("An error has occured during import.");
	            }
			}
		});
		mntmXMLImport.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/import_20.png")));
		mntmXMLImport.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnXML.add(mntmXMLImport);
		
		JMenuItem mntmXMLExport = new JMenuItem("Export XML Index");
		mntmXMLExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
				c.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
				c.setFileHidingEnabled(true);
	            int rVal = c.showOpenDialog(MainWindow.this);
	            String path = "";
	            if (rVal == JFileChooser.APPROVE_OPTION) {
	            	if (!c.getSelectedFile().getAbsolutePath().endsWith(".xml")) {
	            		path = c.getSelectedFile().getAbsolutePath() + ".xml";
	            	} else {
	            		path = c.getSelectedFile().getAbsolutePath();
	            	}
	            	CADModelList model_list = new CADModelList();
					if (model_list.exportModelList(path)) {
						printToLog(path + " exported successfully.");
					} else {
						printToLog("Unable to export " + path);
					}
	            } else if (rVal == JFileChooser.CANCEL_OPTION) {
	            	printToLog("File export cancelled.");
	            } else {
	            	printToLog("An error has occured during export.");
	            }
			}
		});
		mntmXMLExport.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/export_20.png")));
		mntmXMLExport.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnXML.add(mntmXMLExport);
		mntmAdvancedOptions.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/settings_20.png")));
		mntmAdvancedOptions.setFont(new Font("Verdana", Font.PLAIN, 12));
		mnTools.add(mntmAdvancedOptions);
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
				recog.mainProcedure(txtFilePath.getText(), false, (Material) cboxMaterial.getSelectedItem(), (InitForm) cboxInitForm.getSelectedItem(), pref);
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
		
		JLabel lblRecognizedOpitzCode = new JLabel("Recognized Group Technology Code:");
		lblRecognizedOpitzCode.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtRecCode = new JTextField();
		txtRecCode.setColumns(10);
		
		JScrollPane scrollPane_matches = new JScrollPane();
		
		JLabel lblMatches = new JLabel("Possible Matches:");
		lblMatches.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblSimilarityThreshold = new JLabel("Set Similarity Threshold:");
		lblSimilarityThreshold.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		sliderSimThreshold = new JSlider();
		sliderSimThreshold.setPaintTicks(true);
		sliderSimThreshold.setPaintLabels(true);
		sliderSimThreshold.setMinorTickSpacing(10);
		sliderSimThreshold.setMajorTickSpacing(20);
		sliderSimThreshold.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnSearchForSimilar = new JButton("Search for Similar Models");
		btnSearchForSimilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CADModelList model_list = new CADModelList();
				SimilarityCalculator calc = new SimilarityCalculator();
				double threshold = Double.parseDouble(Integer.toString(sliderSimThreshold.getValue()));
				printToLog("Running similarity comparisons at " + sliderSimThreshold.getValue() + "% minimum.");
				printToLog("Weights set at: " + pref.getWeightVectorString());
				CADModel[] simModels = calc.getSimilarModelsViaOpitz(new Opitz(txtRecCode.getText()), model_list, (threshold / 100), pref.getWeightVector());
				printToLog("Similarity comparisons complete. " + simModels.length + " models found.");
				DefaultTableModel tempTableModel = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Part Name", "Part Similarity %"}) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				for (int i = 0; i < simModels.length; i++) {
					tempTableModel.addRow(simModels[i].toSimListData());
					printToLog(simModels[i].getPartName() + " - " + simModels[i].getSimilarity());
				}
				tableSimModels.setModel(tempTableModel);
				printToLog("Models retrieved.");
			}
		});
		btnSearchForSimilar.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxMaterial = new JComboBox();
		
		JLabel lblInitialForm = new JLabel("Initial Form:");
		lblInitialForm.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cboxInitForm = new JComboBox();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_log, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblRecognizedOpitzCode)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtRecCode, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnRunRecognition, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnChooseFile))
								.addComponent(cboxMaterial, Alignment.TRAILING, 0, 401, Short.MAX_VALUE)
								.addComponent(cboxInitForm, Alignment.TRAILING, 0, 401, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblSimilarityThreshold)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sliderSimThreshold, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSearchForSimilar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
								.addComponent(lblMaterial)
								.addComponent(lblInitialForm)
								.addComponent(lblLog))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMatches)
								.addComponent(scrollPane_matches, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMatches)
						.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChooseFile))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_matches, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMaterial)
									.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
									.addComponent(lblInitialForm)
									.addGap(272)))
							.addGap(16))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(cboxMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(cboxInitForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRunRecognition)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRecCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRecognizedOpitzCode))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSimilarityThreshold)
								.addComponent(sliderSimThreshold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnSearchForSimilar)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(lblLog)
							.addGap(6)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_log, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		
		textArea_log = new JTextArea();
		scrollPane_log.setViewportView(textArea_log);
		textArea_log.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		tableSimModels = new JTable();
		tableSimModels.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					CADModelList model_list = new CADModelList();
					CADModelFrame temp = new CADModelFrame(false, model_list.getModel(tableSimModels.getSelectedRow()).getPartIdentifier(), null, pref);
					temp.setVisible(true);
				}
			}
		});
		tableSimModels.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Part Name", "Part Similarity %"
			}
		));
		tableSimModels.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPane_matches.setViewportView(tableSimModels);
		
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
		
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Prints a line of text to the log text area.
	 * @param log_data The line of text to be printed in the log text area.
	 */
	private void printToLog(String log_data) {
		String temp = this.textArea_log.getText();
		if (temp != null && !temp.isEmpty()) {
			temp += "\n";
		}
		temp += log_data;
		this.textArea_log.setText(temp);
	}
}
