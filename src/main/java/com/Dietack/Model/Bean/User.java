package com.Dietack.Model.Bean;


import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author enrico.t
 */

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idUser;
	private String name;
	private String surname;
	private String phone;
	private String email;
	private String passwordHash;
	private Date birthDate;
	private LinkedList<EatEvent> eatEvents = null;
	//private LinkedList<SportEvent> sportEvents;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void addEatEvent(Recipe recipe){
		if(eatEvents == null)
			eatEvents = new LinkedList<EatEvent>();
		EatEvent eatEvent = new EatEvent(this.idUser, recipe, System.currentTimeMillis());
		this.eatEvents.add(eatEvent);
	}

	public List<EatEvent> getEatEvents(){
		return this.eatEvents;
	}


}