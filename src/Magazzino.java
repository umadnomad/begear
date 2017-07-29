/**
In un file viene memorizzato il magazzino di una azienda, secondo la notazione: 
biella#7@5 
ruota#3@8 
stelo#2@9 
prima del simbolo '#' vi è il nome del prodotto; fra il simbolo '#' e il simbolo '@' vi è il costo del prodotto;
dopo il simbolo '@' vi è il quantitativo in pezzi di quel prodotto. 
Il programma deve restituire il controvalore del magazzino.
 * @author Federico
 *
 */

class Magazzino {
	
	static String[] arr = { "biella#7@5","ruota#3@8","stelo#2@9" };
	
	public static void main(String[] args) {
		for (String in:arr) {
			
			String[] tokens = in.split("#|@");

			System.out.println("Controvalore "+ tokens[0] + ": " + Integer.parseInt(tokens[1])*Integer.parseInt(tokens[2]));
			
		}
	}
}