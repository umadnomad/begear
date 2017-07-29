package com.riccardofinazzi.io.sql.esercizio2;

import com.mysql.jdbc.Driver;
import java.sql.*;

public abstract class DdlDml {

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private String url, user, password;
	private boolean connected = false;
	private Connection conn = null;
	private Statement s = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private DdlDml(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public static DdlDml getInstance(String url, String user, String password) {
		DdlDml o = null;
		try {
			Class.forName(DRIVER);

			o = new DdlDml(url, user, password) {
					@Override public void populate() {} 	//TO-DO
					@Override public void dml() {}			//TO-DO
			};

		} catch (ClassNotFoundException e) {
			System.out.println("couldn't load jdbc driver");
		}
		return o;
	}

	public boolean isConnected() {
		return connected;
	}

	public static void main(String[] args) {

		DdlDml o = DdlDml.getInstance("jdbc:mysql://localhost:3306", "root", "root");

		if (o != null) {
			o.connect();

			if (o.isConnected()) {
				try {
					o.ddl();
					o.populate();
					o.dml();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("end of program.");
	}

	public boolean connect() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			return connected = true;
		} catch (SQLException e) {
			System.out.println("couldn't connect to database");
		}
		return connected = false;
	}

	public void ddl() throws SQLException {

		s = conn.createStatement();
		s.executeQuery("CREATE DATABASE IF NOT EXISTS viaggi CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;");
		s = conn.createStatement();
		s.executeQuery("CREATE TABLE IF NOT EXISTS vacanza (codice INT, descrizione VARCHAR(32));");
		s = conn.createStatement();
		s.executeQuery("CREATE TABLE IF NOT EXISTS cliente (codice INT, nome VARCHAR(32), cognome VARCHAR(32), codicefiscale VARCHAR(32), datadiregistrazione VARCHAR(32), recapitotel VARCHAR(32), email VARCHAR(32), PRIMARY KEY(codice));");
		s = conn.createStatement();
		s.executeQuery("CREATE TABLE IF NOT EXISTS villeggiante (codice INT, nome VARCHAR(32), vacanza VARCHAR(32));");
		// utilizzando il comando ALTER TABLE, imponete sullo schema definito al
		// punto precedente i seguenti vincoli
		s = conn.createStatement();
		s.executeUpdate("ALTER TABLE viaggi.vacanza ADD PRIMARY KEY(codice);");
		s = conn.createStatement();
		s.executeUpdate("ALTER TABLE viaggi.cliente DROP PRIMARY KEY, ADD PRIMARY KEY(codicefiscale);");
		s = conn.createStatement();
		s.executeUpdate("ADD INDEX FK__villeggiante__codice_idx (vacanza ASC);");
		s = conn.createStatement();
		s.executeUpdate("ALTER TABLE viaggi.villeggiante ADD CONSTRAINT FK__villeggiante__codice FOREIGN KEY (vacanza) REFERENCES viaggi.vacanza(codice) ON DELETE CASCADE ON UPDATE RESTRICT;");
		s = conn.createStatement();
		s.executeUpdate("ALTER TABLE viaggi.villeggiante ADD CONSTRAINT FK__villegiante__cliente_codice FOREIGN KEY (codice) REFERENCES viaggi.cliente(codice) ON DELETE CASCADE ON UPDATE RESTRICT;");
		// end of ddl
	}

	public abstract void dml() throws SQLException;
	
	public abstract void populate() throws SQLException;
}
