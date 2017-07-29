package com.riccardofinazzi.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

class CpFile {

	public static void main(String[] args) {
		File fa = new File("src/utils/a.file");
		File fb = new File("src/utils/b.file");
		new CpFile().copyFileUsingJava7Files(fa, fb);
	}

	private void copyFileUsingJava7Files(File source, File dest) {
		try {
			Files.copy(source.toPath(), dest.toPath());
		} catch (FileNotFoundException ex) {
			System.out.println("Il file: " + dest + " non esiste.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}