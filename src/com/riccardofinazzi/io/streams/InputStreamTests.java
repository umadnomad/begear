package com.riccardofinazzi.io.streams;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;

class InputStreamTests {

	String			s1		= "Questa è una stringa di prova da processare in uno StringReader";
	StringBuilder	sb;
	byte[]			b_arr	= new byte[s1.length()];

	public static void main( String[] args) {

		// TODO Auto-generated method stub
		InputStreamTests o = new InputStreamTests();
		o.stringReader();
		o.byteArrayInputStream();

	}

	void stringReader() {

		StringReader sr = new StringReader(s1); // can be decorated with a BufferedReader
		sb = new StringBuilder();

		try {
			int i = 0, c;
			while( (c = sr.read()) != -1) {
				sb.append((char) c);
			}
		} catch( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb);
	}

	void byteArrayInputStream() {

		DataInputStream ds = new DataInputStream(new ByteArrayInputStream(s1.getBytes()));
		int i = 0;

		try {
			while( true)
				b_arr[i++] = ds.readByte();
		} catch( EOFException e) {
			// TODO Auto-generated catch block
		} catch( IOException e) {
			// TODO Auto-generated catch block
		}

		sb = new StringBuilder();

		for( byte in : b_arr)
			sb.append((char) in);

		System.out.println(sb);
	}
}
