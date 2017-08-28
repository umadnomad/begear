package com.riccardofinazzi.io.sql.esercizio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import utils.SysinReader;

/**
 * @author rfinazzi
 */
public class RubricaDatabase {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String $DB = "testing";
	private static final String $TABLE = "contatti";

	private static String Sql = "";

	private static Connection conn = null;
	private static Statement s = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static Map<Integer, Contatto> map = new TreeMap<Integer, Contatto>();

	public static void main(String[] args) {

		RubricaDatabase rd = new RubricaDatabase();
		SysinReader sr = new SysinReader();
		
		System.out.println("selecting all the contacts and adding them to the storage TreeMap");
		rd.stampaRubrica();
		System.out.println();

		System.out.println(
				"prompting user to insert a cod in order to find a contact and adding it to the storage TreeMap in the eventuality it wasn't already there. it won't add a duplicate due to map keys being unique");
		rd.ricercaContatto(sr.readInteger("please insert cod:"));
		System.out.println();

		System.out.println("prompting user to update a contact: cod,nome,cognome,numero");
		rd.aggiornaContatto(sr.readInteger("please insert cod:"), sr.readString("please insert nome:"),
				sr.readString("please insert cognome:"), sr.readString("please insert numero:"));
		System.out.println();

		System.out.println("prompting user to delete a contact");
		rd.eliminaContatto(sr.readInteger("please insert cod:"));
		
		System.out.println("prompting user to add a contact");
		rd.inserisciContatto(sr.readInteger("please insert cod:"), sr.readString("please insert nome:"),
				sr.readString("please insert cognome:"), sr.readString("please insert numero:"));

		if (conn != null) // closing connection
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("error while closing sql connection");
			}

		System.out.println("exiting...");
		System.exit(0); // good bye

	}

	/**
	 * Costruttore al cui interno si carica il driver del connettore, si apre
	 * una connessione col database e -----si istanzia un oggetto di classe
	 * Statement<----- potenzialmente superfluo
	 */
	public RubricaDatabase() {

		try {
			Class.forName(DRIVER_NAME); // loading driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + $DB + "?useSSL=false", "root", "root"); // starting
																														// connection
			System.out.println("connected.");
		} catch (ClassNotFoundException e) {
			System.out.println("class " + DRIVER_NAME + " not found.");
		} catch (SQLException e) {
			System.out.println("error while opening sql connection");
			e.printStackTrace();
		}
	}

	/**
	 * Inserisce un contatto nella rubrica
	 * 
	 * @param codCont
	 *            il codice del contatto
	 * @param nome
	 *            il nome del contatto
	 * @param cognome
	 *            il cognome del contatto
	 * @param numero
	 *            il numero del contatto
	 */
	public void inserisciContatto(int codCont, String nome, String cognome, String numero) {

		Sql = "INSERT INTO " + $TABLE + " VALUES (?,?,?,?)";

		try {
			ps = conn.prepareStatement(Sql);
			ps.setInt(1, codCont);
			ps.setString(2, nome);
			ps.setString(3, cognome);
			ps.setString(4, numero);
			ps.executeUpdate();

			map.put(codCont, new Contatto(codCont, nome, cognome, numero));

		} catch (SQLException e) {
			System.out.println("couldn't add contact to database");
			e.printStackTrace();
		} finally {
			chiudiConnessione();
		}

	}

	/**
	 * Elimina un contatto dalla rubrica
	 * 
	 * @param conCont
	 *            il codice del contatto da eliminare
	 */
	public void eliminaContatto(int codCont) {

		Sql = "DELETE FROM " + $TABLE + " WHERE cod = ?";

		try {
			ps = conn.prepareStatement(Sql);
			ps.setInt(1, codCont);
			ps.executeUpdate();

			map.remove(codCont);

		} catch (SQLException e) {
			System.out.println("couldn't delete contact from database");
		} finally {
			chiudiConnessione();
		}
	}

	/**
	 * Aggiorna le informazioni di un contatto
	 * 
	 * @param codCont
	 *            il codice del contatto
	 * @param nuovoNome
	 *            il nuovo nome del contatto
	 * @param nuovoCognome
	 *            il nuovo cognome del contatto
	 * @param nuovoNumero
	 *            il nuovo numero del contatto
	 */
	public void aggiornaContatto(int codCont, String nuovoNome, String nuovoCognome, String nuovoNumero) {

		Sql = "UPDATE " + $TABLE + " SET nome = ?, cognome = ?, numero = ? WHERE cod = ?";

		try {
			ps = conn.prepareStatement(Sql);
			ps.setString(1, nuovoNome);
			ps.setString(2, nuovoCognome);
			ps.setString(3, nuovoNumero);
			ps.setInt(4, codCont);
			ps.executeUpdate();

			map.remove(codCont);

			Contatto c = new Contatto(codCont, nuovoNome, nuovoCognome, nuovoNumero);
			map.put(codCont, c);
			System.out.println(c);

		} catch (SQLException e) {
			System.out.println("couldn't update contact");
			e.printStackTrace();
		} finally {
			chiudiConnessione();
		}
	}

	/**
	 * Ricerca un contatto nella rubrica
	 * 
	 * @param codCont
	 *            il codice del contatto da ricercare
	 */
	public void ricercaContatto(int codCont) {

		Sql = "SELECT * FROM " + $TABLE + " WHERE cod = ?";

		try {
			ps = conn.prepareStatement(Sql);
			ps.setInt(1, codCont);
			rs = ps.executeQuery();

			while (rs.next()) {

				int cod = rs.getInt("cod");
				if (map.containsKey(cod))
					System.out.println(map.get(cod));
				else {
					Contatto c = new Contatto(cod, rs.getString("nome"), rs.getString("cognome"),
							rs.getString("numero"));
					map.put(cod, c);
					System.out.println(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			chiudiConnessione();
		}
	}

	/**
	 * Stampa la rubrica
	 */
	public void stampaRubrica() {

		Sql = "SELECT * FROM " + $TABLE;

		try {
			s = conn.createStatement();
			rs = s.executeQuery(Sql);
			while (rs.next()) {
				int cod = rs.getInt("cod");
				map.put(cod, new Contatto(cod, rs.getString("nome"), rs.getString("cognome"), rs.getString("numero")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			chiudiConnessione();
		}

		System.out.println("iterating through the storage TreeMap");
		for (Contatto in : map.values()) {
			System.out.println(in);
		}

	}

	/**
	 * Chiude la connessione col database
	 */
	public void chiudiConnessione() {
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
			}
	}
}
