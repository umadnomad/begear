package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

abstract public class SqlConnection {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(DRIVER);
		} catch( ClassNotFoundException e) {
			System.out.println("couldn't load jdbc driver");
			System.exit(69);
		}
	}
	protected String		url, user, password;
	protected Connection	conn		= null;
	protected boolean		connected	= false;

	public SqlConnection( String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void connect() {

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("connected @ " + url);
			connected = true;
		} catch( SQLException e) {
			System.out.println("couldn't connect to database " + url);
		}
	}

	public boolean isConnected() {

		return connected;
	}

	public void disconnect() {

		if( conn != null) try {
			conn.close();
			conn = null;
			connected = false;
		} catch( SQLException e) {
			System.out.println("couldn't close database connection");
		}
	}

	/**
	 * 
	 * @param queries
	 *            An array of queries whose result will be printed
	 * @param col_delim
	 *            This will be appended after each column's result
	 * @param row_delim
	 *            This will be appended after each new row
	 * @throws SQLException
	 */
	public void forEachQuery( String[] queries, String col_delim, String row_delim) throws SQLException {

		for( String in : queries) {
			try( Statement s = conn.createStatement()) {
				try( ResultSet rs = s.executeQuery(in)) {
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsnumber = rsmd.getColumnCount();
					System.out.println(row_delim);
					while( rs.next()) {
						for( int i = 1; i <= columnsnumber; i++)
							System.out.print(rs.getString(i) + col_delim);
						System.out.println();
					}
				}
			}
		}
	}
}