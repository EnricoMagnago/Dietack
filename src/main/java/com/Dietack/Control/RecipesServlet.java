package com.Dietack.Control;

import com.Dietack.Model.Bean.Ingredient;
import com.Dietack.Model.Bean.Recipe;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by enrico on 10/03/16.
 */
@WebServlet(name = "RecipesServlet")
public class RecipesServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); //same things.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		String ingredientsString = request.getParameter("ingredients");
		List<Ingredient> ingredients = null;
		if(ingredientsString != null)
			ingredients = parseIngredients(ingredientsString);

		List<Recipe> recipeList = null; //query that given ingredients gives recipe list with given .
		response.getWriter().write(toJSON(recipeList));
	}

	private List<Ingredient> parseIngredients(String string){
		String[] stringList = string.split(",");
		List<Ingredient> ingredients = new ArrayList<Ingredient>(stringList.length);

		// TODO check if the ingredients are in the db. -> query that given a list of ingrediends name returns a list of Ingrediens.
		//ingredients = query(stringList);
		return ingredients;
	}

	private String toJSON(List<Recipe> recipesList){
		List<JSONObject> recipeJSONList = new ArrayList<JSONObject>(); //list of Recipe encoded in JSON

		for(Recipe recipe : recipesList) { //encode every recipe in JSON and add to the list.
			List<JSONObject> ingredientsJSON = new ArrayList<JSONObject>();
			Set<Ingredient> ingredientsList = recipe.getIngredients();
			for (Ingredient ing : ingredientsList) { //encode Ingredient in JSON.
				JSONObject jsonIng = new JSONObject();
				jsonIng.put("id", ing.getId());
				jsonIng.put("name", ing.getName());
				jsonIng.put("kcal", ing.getCalories());
				jsonIng.put("measureUnit", ing.getMeasureUnit());

				ingredientsJSON.add(jsonIng); //add the single ingredient to the list.
			}
			//encode recipe
			JSONObject recipeJSON = new JSONObject();
			recipeJSON.put("id", recipe.getId());
			recipeJSON.put("name", recipe.getName());
			recipeJSON.put("instructions", recipe.getInstructions());
			recipeJSON.put("ingredients", ingredientsJSON); //add the ingredient list to the recipes.

			recipeJSONList.add(recipeJSON); //add the current Recipe to the list.
		}

		JSONObject obj = new JSONObject();
		obj.put("recipes", recipeJSONList);
		return obj.toJSONString();
	}
}
