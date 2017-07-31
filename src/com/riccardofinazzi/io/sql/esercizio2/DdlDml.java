package com.riccardofinazzi.io.sql.esercizio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DdlDml {

	private static final String		DRIVER		= "com.mysql.jdbc.Driver";
	private static final String		$DB			= "viaggi";
	private static final String[]	$SQL_PROP	= { "useSSL=false" };
	private String					url, user, password;
	private boolean					connected	= false;
	private Connection				conn		= null;

	private DdlDml( String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public static DdlDml getInstance( String url, String user, String password) {

		DdlDml o = null;
		try {
			Class.forName(DRIVER);
			//@formatter:off
			o = new DdlDml(url, user, password) {
				@Override public void populate() {} // TO-DO
				@Override public void dml() {} // TO-DO
			};
			//@formatter:on
		} catch( ClassNotFoundException e) {
			System.out.println("couldn't load jdbc driver");
		}
		return o;
	}

	public boolean isConnected() {

		return connected;
	}

	public static void main( String[] args) {

		// connection to no specific db
		DdlDml o = DdlDml.getInstance("jdbc:mysql://localhost:3306" + "?" + $SQL_PROP[0], "root", "root");
		if( o != null) {
			o.connect();
			if( o.isConnected()) {
				try {
					o.createDatabase();
				} catch( SQLException e) {
					System.out.println("error while creating database " + $DB);
					System.exit(0);
				}
				o.disconnect();
			}
		}
		// connection to specific db
		o = DdlDml.getInstance("jdbc:mysql://localhost:3306/" + $DB + "?" + $SQL_PROP[0], "root", "root");
		if( o != null) {
			o.connect();
			if( o.isConnected()) {
				try {
					o.ddl();
					o.populate();
					o.dml();
				} catch( SQLException e) {
					e.printStackTrace();
				}
				o.disconnect();
			}
		}
		System.out.println("end of program.");
	}

	public void createDatabase() throws SQLException {

		try( Statement s = conn.createStatement()) {
			s.executeUpdate("CREATE DATABASE IF NOT EXISTS viaggi CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;");
		}
	}

	public void ddl() throws SQLException {

		try( Statement s = conn.createStatement()) {
			conn.setAutoCommit(false); /* This is done so that all the Batch
										 * statements execute in a single
										 * transaction and no operation in the
										 * batch is committed individually. */
			s.addBatch("CREATE TABLE IF NOT EXISTS vacanza (codice INT, descrizione VARCHAR(32));");
			s.addBatch("CREATE TABLE IF NOT EXISTS cliente (codice INT, nome VARCHAR(32), cognome VARCHAR(32), codicefiscale VARCHAR(32), datadiregistrazione VARCHAR(32), recapitotel VARCHAR(32), email VARCHAR(32), PRIMARY KEY(codice));");
			s.addBatch("CREATE TABLE IF NOT EXISTS villeggiante (codicefiscale VARCHAR(32), nome VARCHAR(32), vacanza INT);");
			/* utilizzando il comando ALTER TABLE, imponete sullo schema
			 * definito al punto precedente i seguenti vincoli */
			s.addBatch("ALTER TABLE viaggi.vacanza ADD PRIMARY KEY(codice);");
			s.addBatch("ALTER TABLE viaggi.cliente DROP PRIMARY KEY, ADD PRIMARY KEY(codicefiscale);");
			s.addBatch("ALTER TABLE viaggi.cliente CHANGE COLUMN codice codice INT DEFAULT NULL;");
			s.addBatch("ALTER TABLE viaggi.villeggiante ADD CONSTRAINT FK__villeggiante__vacanze FOREIGN KEY (vacanza) REFERENCES viaggi.vacanza(codice) ON DELETE CASCADE ON UPDATE RESTRICT;");
			s.addBatch("ALTER TABLE viaggi.villeggiante ADD CONSTRAINT FK__villeggiante__cliente FOREIGN KEY (codicefiscale) REFERENCES viaggi.cliente(codicefiscale) ON DELETE CASCADE ON UPDATE RESTRICT;");
			s.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} // end of ddl
	}

	public abstract void dml() throws SQLException;

	public abstract void populate() throws SQLException;

	public boolean connect() {

		try {
			conn = DriverManager.getConnection(url, user, password);
			return connected = true;
		} catch( SQLException e) {
			System.out.println("couldn't connect to database");
		}
		return connected = false;
	}

	public void disconnect() {

		if( conn != null) try {
			conn.close();
			conn = null;
		} catch( SQLException e) {
			System.out.println("couldn't close Connection conn");
		}
	}
}
