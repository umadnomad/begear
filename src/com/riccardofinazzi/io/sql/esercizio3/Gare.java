package com.riccardofinazzi.io.sql.esercizio3;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.SqlConnection;
import utils.SysinReader;

public class Gare extends SqlConnection {

	private static final String	$DB_NAME		= "db";
	private static final String	$DB_USER		= "root";
	private static final String	$DB_PASSWORD	= "root";

	public Gare( String url, String user, String password) {
		super(url, user, password);
	}

	public static void main( String[] args) {

		Gare o = new Gare("jdbc:mysql://localhost:3306/" + $DB_NAME + "?useSSL=false", $DB_USER, $DB_PASSWORD);
		SysinReader sr = new SysinReader();

		o.connect();
		if( o.isConnected()) {
			while( true) {
				System.out.println("press 0 to insert a persona");
				System.out.println("press 1 to insert a partecipante");
				System.out.println("press 2 to insert a gara");
				System.out.println("press 3 to multi-query");
				System.out.println("press any other key to quit.");
				Integer i = sr.readInteger("");
				switch( i) {
				case 0:
					o.insertPersone(sr.readString("Inserire il nome: "), sr.readString("Inserire il cognome: "));
					break;
				case 1:
					o.insertPartecipanti(	sr.readInteger("Inserire il codice della gara: "),
											sr.readInteger("Inserire l'ID della persona"),
											sr.readInteger("Insere i punti che ha guadagnato nella gara: "));
					break;
				case 2:
					o.insertGara(	sr.readString("Inserire un nome per questa gara"),
									sr.readString("Inserire località della gara"),
									sr.readString("Inserire una data in formato YYYY-MM-DD"));
					break;
				case 3:
					String[] queries = { "SELECT pe.cognome,pe.nome, SUM(pa.punti) AS punti_totali FROM persone pe RIGHT JOIN partecipanti pa ON pe.id=pa.id_persona GROUP BY pa.id_persona ORDER BY (punti_totali) DESC;",
					                     "SELECT g.id, g.nome, g.località, COUNT(pa.id_persona) AS disputata_n_volte FROM gara g LEFT JOIN partecipanti pa ON g.id = pa.id_gara GROUP BY g.id ORDER BY disputata_n_volte DESC;"};
					try {
						o.forEachQuery(queries, ";", "-------------------------------------------------------");
					} catch( SQLException e) {
						System.out.println("error while multi-querying");
					}
				default:
					System.out.println("good bye");
					o.disconnect();
					System.exit(0);
				}
			}
		}
	}

	public void insertPartecipanti( int id_gara, int id_persona, int punti) {

		String query = "INSERT INTO " + $DB_NAME + ".partecipanti VALUES (?,?,?);";
		try( PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id_gara);
			ps.setInt(2, id_persona);
			ps.setInt(3, punti);
			ps.executeUpdate();
		} catch( SQLException e) {
			System.out.println("couldn't insert into database");
		}
	}

	public void insertGara( String nome, String località, String data) {

		String query = "INSERT INTO " + $DB_NAME + ".gara (nome,località,data) VALUES (?,?,?);";
		try( PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, nome);
			ps.setString(2, località);
			ps.setString(3, data);
			ps.executeUpdate();
		} catch( SQLException e) {
			System.out.println("couldn't insert into database");
			e.printStackTrace();
		}
	}

	public void insertPersone( String nome, String cognome) {

		String query = "INSERT INTO " + $DB_NAME + ".persone (nome, cognome) VALUES (?,?);";
		try( PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.executeUpdate();
		} catch( SQLException e) {
			System.out.println("couldn't insert into database");
		}
	}
}
