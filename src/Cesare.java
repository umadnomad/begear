import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cesare {
	
	private static final int CESAR = 7;
	
	public static void main(String[] args) {
		Cesare c = new Cesare();
		try {
			System.out.println(c.readFile());
			System.out.println(Cesare.decrypt(c.readFile()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println((int) 'a');
		System.out.println((int) 'w'); 
	}
	
	public static StringBuilder decrypt(StringBuilder s) {

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ')
				s.setCharAt(i, (char) (s.charAt(i) - CESAR) );
		}
		return s;
	}
	
	public StringBuilder readFile() throws IOException {
		
		StringBuilder s = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new FileReader(
				"src/utils/cesare.crypt"));
		s.append(br.readLine());
		br.close();
		
		return s;
	}
}