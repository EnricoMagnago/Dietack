package com.Dietack.Model.Bean;

/**
 * Created by enrico on 10/03/16.
 */
public class EatEvent {
	private int userId;
	private Recipe recipe;
	private long timeStamp;

	public EatEvent(int idUser, Recipe recipe, long l) {
		this.userId = idUser;
		this.recipe = recipe;
		this.timeStamp = l;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
