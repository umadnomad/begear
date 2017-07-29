package com.riccardofinazzi.io.sql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DataPopulator {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static final String $INPUT = "src/utils/feed.txt";
	private static final String $OUTPUT = "src/utils/file.sql";

	public static void main(String[] args) {

		DataPopulator dp = new DataPopulator();

		try {
			
			dp.processInput($INPUT);
			dp.outputSql();

			br.close();
			bw.close();
			
			System.out.println("done.");
			
		} catch (FileNotFoundException ex) {
			System.out.println("Input file " + $INPUT + " not found");
			System.exit(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String[] generatePosition() {

		String[] output = new String[2];

		int value = (int) (Math.random() * 10);

		if (value == 9) {
			output[0] = "Direzione";
			output[1] = String.valueOf( (int)(Math.random() * (85-70) ) + 70);
		} else if (value >= 6 && value <= 8) {
			output[0] = "Amministrazione";
			output[1] = String.valueOf( (int)(Math.random() * (50-40) ) + 40);
		} else {
			output[0] = "Produzione";
			output[1] = String.valueOf( (int)(Math.random() * (40-30) ) + 30);
		}

		return output;
	}
	
	public void processInput(String s) throws FileNotFoundException, IOException {
		
		System.out.println("Full path is " + $INPUT);
		br = new BufferedReader(new FileReader($INPUT));
		
	}

	public void writeSqlEntry(String a, String b) throws IOException {
		
		String[] position = generatePosition();
		bw.write("INSERT INTO Impiegati VALUES ('" + a + "','" + b + "','" + position[0] + "','" + position[1] + "');" +"\n");
		
	}
	
	public void outputSql() throws IOException {

		String s = "";
		String[] arr = null;

		bw = new BufferedWriter(new FileWriter($OUTPUT));

		while ((s = br.readLine()) != null) {
			s = s.replaceAll("'", "\\\\'");
			arr = s.split(",");
			writeSqlEntry(arr[0], arr[1]);
		}
	}
}