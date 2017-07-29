package com.riccardofinazzi.io.serialization;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class TestCode {

	public static void main(String[] args) {

		Library lib = new Library();
		lib.readFromCsv();
		lib.serializeMe();
		System.out.println(lib.getSet());

	}
}

class Library {

	private Set<Contatto> set = new HashSet<Contatto>();

	public void setSet(Set<Contatto> set) {
		this.set = set;
	}

	public Set<Contatto> getSet() {
		return set;
	}

	public void readFromCsv() {

		String line = "";
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader("src/utils/rubrica.csv"));
			while ((line = br.readLine()) != null) {
				String[] vet = line.split(";");
				set.add(new Contatto(vet[0], vet[1], vet[2], vet[3], vet[4]));
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readFromSerializedCsv() {

		try {
			FileInputStream fis = new FileInputStream("src/utils/csv.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			set = (Set<Contatto>) ois.readObject();

			ois.close();

		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public void serializeMe() {
		try {
			FileOutputStream fs = new FileOutputStream("src/utils/csv.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);

			os.writeObject(set);

			os.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class Contatto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name, surname, telephone, email, address;

	public Contatto(String name, String surname, String telephone, String email, String address) {
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return name + ";" + surname + ";" + telephone + ";" + email + ";" + address;
	}

	@Override
	public int hashCode() {
		return (name.length() + surname.length()) * 15;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Contatto)
			if ((this.name == ((Contatto) o).name) && (this.surname == ((Contatto) o).surname))
				return true;
		return false;
	}
}