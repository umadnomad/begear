package utils;

import java.util.Scanner;

public class SysinReader {

	private Scanner scn = new Scanner(System.in);

	public String readString( String msg) {

		System.out.println(msg);
		return scn.nextLine();
	}

	public Integer readInteger( String msg) {

		System.out.println(msg);
		return scn.nextInt();
	}
}
