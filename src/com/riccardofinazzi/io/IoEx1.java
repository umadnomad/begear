package com.riccardofinazzi.io;

import java.io.*;

public class IoEx1 {
    public static void main(String[] args) {
        String s, s2 = "stringa a caso";

        try {
            BufferedReader in4 = new BufferedReader(new StringReader(s2));
            PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("src/utils/IODemo.out")));
            int lineCount = 1;
            while((s = in4.readLine()) != null)
                out1.println(lineCount++ + " " + s);
            
            out1.close();
            
        } catch(EOFException e) {
            System.err.println("End of stream");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}