package com.riccardofinazzi.io;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class IoEx2 {

    public static void main(String[] args) {
		
		try {
			PrintStream ps = new PrintStream(new FileOutputStream("src/utils/matrix.out"));
			System.setOut(ps);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	
        int[][] matrix = new int[5][5];
        int r_index = 0;
        int c_index = 0;
        String s = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/utils/matrix.in"));
            while ((s = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(s, "\t");
                while (st.hasMoreTokens()) {
                    matrix[r_index][c_index] = Integer.parseInt(st.nextToken());
                    c_index++;
                }
                c_index = 0;
                r_index++;
            }
            
            in.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(Arrays.deepToString(matrix));
		
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(sum(matrix,Somma.VERTICALE,i));
		}
		
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(sum(matrix,Somma.ORIZZONTALE,i));
		}
		
		System.out.println(sum(matrix,Somma.DIAGONALE,null));
		
	}
	
	private enum Somma {
		VERTICALE, ORIZZONTALE, DIAGONALE
	}
	
	private static Integer sum(int[][] arr, Somma e, Integer offset) {
		
		int total = 0;
		
		switch(e) {
			case VERTICALE:
				System.out.println("summing columns with offset " + offset);
				for (int i = 0; i < arr.length; i++) {
					total += arr[i][0+offset];
				}
				break;
				
			case ORIZZONTALE:
				System.out.println("summing rows with offset " + offset);
				for (int i = 0; i < arr.length; i++) {
					total += arr[0+offset][i];
				}
				break;
				
			case DIAGONALE:
				System.out.println("summing diag");
				for (int i = 0; i < arr.length; i++) {
					total+=arr[i][i]; // federico
				}
				break;
		}
		
		return total;
	}
}