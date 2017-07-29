package com.riccardofinazzi.io;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class DataEPrint {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("src/utils/lIODemo.out"));
		PrintStream psOut = new PrintStream(new FileOutputStream("src/utils/lIODemo2.out"));
		;

		try {
			dataOut.writeFloat((float) 3.14);
			psOut.print((float) 3.14);

			dataOut.close();
			psOut.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}