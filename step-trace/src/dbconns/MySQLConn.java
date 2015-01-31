package dbconns;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Omar Elazhary
 */
public class MySQLConn implements IDBConn {

	private Connection conn;
	private Statement stmt;

	/**
	 * The constructor that attempts to connect to the database.
	 * 
	 * @exception Throws
	 *                an SQLException or a ClassNotFoundException
	 */
	public MySQLConn(String host, String username, String password,
			String database) {
		String connectionUrl = "jdbc:mysql://" + host + "/" + database
				+ "?user=" + username + "&password=" + password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Don't forget to change the username and password values in the
			// connectionUrl text to those of your own machine.
			this.conn = DriverManager.getConnection(connectionUrl);
			this.stmt = this.conn.createStatement();
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.toString());
		} catch (ClassNotFoundException cE) {
			System.out.println("Class Not Found Exception: " + cE.toString());
		}
	}

	/**
	 * This method executes "SELECT" queries on the database.
	 * 
	 * @param query
	 *            The query to be executed.
	 * @return Returns an ArrayList of String arrays if successful, null if
	 *         unsuccessful.
	 * @exception Can
	 *                throw an SQLException if the statement wasn't created
	 *                successfully.
	 */
	public ArrayList<String[]> runSelectQuery(String query) {
		ArrayList<String[]> result = null;
		try {
			ResultSet queryResult = this.stmt.executeQuery(query);
			ResultSetMetaData rsmd = queryResult.getMetaData();
			result = new ArrayList<String[]>();
			while (queryResult.next()) {
				int colCount = rsmd.getColumnCount();
				String[] temp = new String[colCount];
				for (int i = 1; i <= colCount; i++) {
					temp[i - 1] = queryResult.getString(i);
				}
				result.add(temp);
			}
			return result;
		} catch (SQLException ex) {
			Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE, null,
					ex);
			System.out.println(ex.getMessage());
			return null;
		}

	}

	/**
	 * This method executes "INSERT", "UPDATE" or "DELETE" queries on the
	 * database.
	 * 
	 * @param query
	 *            The query to be executed.
	 * @return The number of rows affected or -1 if unsuccessful.
	 * @exception Can
	 *                throw an SQLException if the statement wasn't created
	 *                successfully.
	 */
	public int executeNonSelect(String query) {
		try {
			int result = this.stmt.executeUpdate(query);
			return result;
		} catch (SQLException ex) {
			Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE, null,
					ex);
			System.out.println(ex.getMessage());
			return -1;
		}

	}

}
