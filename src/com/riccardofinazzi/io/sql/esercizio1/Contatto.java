package com.riccardofinazzi.io.sql.esercizio1;

class Contatto implements Comparable<Contatto> {

	private int cod;
	private String nome, cognome, numero;
	
	public Contatto(int cod, String nome, String cognome, String numero) {
		this.cod = cod;
		this.nome = nome;
		this.cognome = cognome;
		this.numero = numero;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return cod + ";" + nome + ";" + cognome + ";" + numero;
	}

	@Override
	public int hashCode() {
		return (nome.length() + cognome.length()) * 15;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Contatto)
			if ((this.nome == ((Contatto) o).nome) && (this.cognome == ((Contatto) o).cognome))
				return true;
		return false;
	}

	@Override
	public int compareTo(Contatto o) {
		return String.valueOf(this.cod).compareTo(String.valueOf(((Contatto)o).getCod()));
	}
}