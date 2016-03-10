package com.Dietack.Model.Bean;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by enrico on 10/03/16.
 */
public class Recipe {
	private int id;
	private String name;
	private String instructions;
	private Set<Pair<Ingredient, Double>> ingredients; //ingredient and quantity.

	public Recipe(){
		ingredients = new HashSet();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName(){ return this.name; }

	public void setName(String name){ this.name = name; }

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Set<Pair<Ingredient, Double>> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient, double quantity) {
		this.ingredients.add(new Pair(ingredient, quantity));
	}

	public double getCalories(){
		double calories = 0;
		for(Pair<Ingredient, Double> element : this.ingredients){
			calories += element.getKey().getCalories()*element.getValue();
		}
		return calories;
	}
}