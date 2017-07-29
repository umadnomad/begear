package com.riccardofinazzi.io.serialization;

import java.io.Serializable;

class Contatto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String	name,
					surname,
					telephone,
					email,
					address;
	
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
		return name + ";" + surname + ";" + telephone + ";" + email+ ";" + address;
	}

}