package com.riccardofinazzi.piped;
import java.io.*;

public class PipedReaderWriter {
    
    public static Reader reverse(Reader source) throws IOException {
        
        BufferedReader in = new BufferedReader(source);
        
        PipedWriter pipeOut = new PipedWriter();
        PipedReader pipeIn = new PipedReader(pipeOut);
        PrintWriter out = new PrintWriter(pipeOut);
        
        new ReverseThread(out, in).start();
        
        return pipeIn;
    }
    
    public static Reader sort(Reader source) throws IOException {
        
        BufferedReader in = new BufferedReader(source);
        
        PipedWriter pipeOut = new PipedWriter();
        PipedReader pipeIn = new PipedReader(pipeOut);
        PrintWriter out = new PrintWriter(pipeOut);
        
        new SortThread(out, in).start();
        
        return pipeIn;
    }
    
    public static void main(String[] args) throws IOException {
        
        FileReader words = new FileReader("src/com.riccardofinazzi.piped/word.txt");
        
        // do the reversing and sorting
        Reader rhymedWords = reverse(sort(reverse(words)));
        
        // write new list to standard out
        BufferedReader in = new BufferedReader(rhymedWords);
        String input;
        
        while ((input = in.readLine()) != null)
            System.out.println(input);
        in.close();
    }
    
}