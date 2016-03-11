package com.Dietack.Model.Bean;


import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.util.Date;
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
	private double calories;
	private Date birthDate;
	private List<EatEvent> eatEvents;
	//private LinkedList<SportEvent> sportEvents;


	public User(){
		eatEvents = new LinkedList<EatEvent>();
	}

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

	public double getCalories(){ return this.calories; }

	public void setCalories(double calories){ this.calories = calories; }

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void addEatEvent(Recipe recipe){
		EatEvent eatEvent = new EatEvent(this.idUser, recipe, System.currentTimeMillis());
		this.eatEvents.add(eatEvent);
	}

	public double getAteCalories(){
		double total = 0;
		for(EatEvent event : this.eatEvents){
			Date date = new Date(event.getTimeStamp());
			Date now = new Date(System.currentTimeMillis());
			date = DateUtils.addDays(date, 1); //next day
			if(date.before(now)) //ignore all the event occured before yesterday.
				total += event.getRecipe().getCalories();
		}
		return total;
	}

	public List<EatEvent> getEatEvents(){
		return this.eatEvents;
	}


}