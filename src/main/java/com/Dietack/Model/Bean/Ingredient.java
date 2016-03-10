package com.Dietack.Model.Bean;


public class Ingredient {
	private int id;
	private String name;
	private double calories;
	private String foto;
	private String measureUnit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public String getFoto(){ return this.foto; }

	public void setFoto(String measureUnit){ this.foto = measureUnit; }

	public String getMeasureUnit(){ return this.measureUnit; }

	public void setMeasureUnit(String measureUnit){ this.measureUnit = measureUnit; }
}
