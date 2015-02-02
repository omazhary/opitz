package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;

import dbconns.IDBConn;
import dbconns.MySQLConn;
import entities.Repository;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

public class DBSyncFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblProg;
	private JTextField txtuname;
	private JTextField txtdb;
	private JTextField txtip;
	@SuppressWarnings("rawtypes")
	private JComboBox cboxType;
	private JProgressBar pbarProg;
	private JPasswordField pwdPass;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBSyncFrame frame = new DBSyncFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public DBSyncFrame() {
		setTitle("Database Synchronization");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblConnectionType = new JLabel("Connection Type:");
		lblConnectionType.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		this.cboxType = new JComboBox();
		cboxType.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtuname = new JTextField();
		txtuname.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtuname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblDatabase = new JLabel("Database:");
		lblDatabase.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtdb = new JTextField();
		txtdb.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtdb.setColumns(10);
		
		JLabel lblDatabaseLocationip = new JLabel("Database Location (IP):");
		lblDatabaseLocationip.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		txtip = new JTextField();
		txtip.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtip.setColumns(10);
		
		this.pbarProg = new JProgressBar();
		pbarProg.setStringPainted(true);
		pbarProg.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JButton btnSync = new JButton("Synchronize");
		btnSync.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Determine conn type:
				String user = txtuname.getText();
				String pass = String.copyValueOf(pwdPass.getPassword());
				String db = txtdb.getText();
				String loc = txtip.getText();
				IDBConn conn = null;
				lblProg.setText("Attempting to connect to server...");
				System.out.println("Attempting to connect to server...");
				if (cboxType.getSelectedIndex() == 0) { // MySQL Connection
					conn = new MySQLConn(loc, user, pass, db);
					lblProg.setText("MySQL connection successfully established.");
					System.out.println("MySQL connection successfully established.");
				}
				pbarProg.setValue(50);
				if (conn != null) {
					lblProg.setText("Attempting synchronization...");
					System.out.println("Attempting synchronization...");
					Repository rep = new Repository(conn);
					if (rep.synchronize()) {
						lblProg.setText("Synchronization successful.");
					} else {
						lblProg.setText("Synchronization unsuccessful!");
					}
				}
				pbarProg.setValue(100);
			}
		});
		
		pwdPass = new JPasswordField();
		pwdPass.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		lblProg = new JLabel("-");
		lblProg.setFont(new Font("Verdana", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConnectionType)
								.addComponent(lblUsername)
								.addComponent(lblPassword)
								.addComponent(lblDatabase, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtdb, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
								.addComponent(txtuname, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
								.addComponent(cboxType, 0, 260, Short.MAX_VALUE)
								.addComponent(pwdPass, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
						.addComponent(pbarProg, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(btnSync, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDatabaseLocationip)
								.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProg)
								.addComponent(txtip, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConnectionType))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(txtuname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pwdPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatabase, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtdb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDatabaseLocationip, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProg))
					.addGap(18)
					.addComponent(pbarProg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(btnSync)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		this.cboxType.addItem("MySQL Database Server");
		this.cboxType.addItem("PostgreSQL Database Server");
		this.cboxType.addItem("Microsoft SQL Server");
	}
}
