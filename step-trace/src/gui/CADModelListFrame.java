package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;

import entities.CADModelList;

import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;

import utils.Preferences;

public class CADModelListFrame extends JFrame {

	private JPanel contentPane;
	private CADModelList model_list;
	private JLabel labelProgress;
	private JTable tableModels;
	private JProgressBar progressBarList;
	private CADModelListFrame tempFrame;
	private Preferences pref;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CADModelListFrame frame = new
	 * CADModelListFrame(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public CADModelListFrame(Preferences prefer) {
		this.pref = prefer;
		setTitle("Available Models");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		this.progressBarList = new JProgressBar();
		
		labelProgress = new JLabel("");
		labelProgress.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(progressBarList, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelProgress, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(progressBarList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelProgress))
					.addContainerGap())
		);
		
		tableModels = new JTable();
		tableModels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableModels.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableModels.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					CADModelFrame temp = new CADModelFrame(true, model_list.getModel(tableModels.getSelectedRow()).getPartIdentifier(), tempFrame, pref);
					temp.setVisible(true);
				}
			}
		});
		tableModels.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Part Name", "Part Description"
			}
		));
		tableModels.setFont(new Font("Verdana", Font.PLAIN, 12));
		scrollPane.setViewportView(tableModels);
		contentPane.setLayout(gl_contentPane);
		
		tempFrame = this;
		
		this.retrieveModels();
	}
	
	/**
	 * Retrieves the CAD models from the XML index.
	 */
	public void retrieveModels() {
		this.labelProgress.setText("Checking XML index...");
		this.model_list = new CADModelList();
		this.progressBarList.setValue(50);
		this.labelProgress.setText("Retrieving Model info...");
		
		DefaultTableModel tempModel = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Part Name", "Part Description"}) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		for (int i  = 0; i < this.model_list.getModelListSize(); i++) {
			int progressValue = (int) Math.ceil(50 + ((i / this.model_list.getModelListSize()) / 2));
			tempModel.addRow(this.model_list.getModel(i).toModelListData());
			this.progressBarList.setValue(progressValue);
			this.labelProgress.setText((i + 1) + " of " + (this.model_list.getModelListSize() + 1) + " loaded...");
		}
		tableModels.setModel(tempModel);
		this.labelProgress.setText("Models retrieved.");
		this.progressBarList.setValue(100);
	}
}
