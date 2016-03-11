package com.Dietack.Control;

import com.Dietack.Model.Bean.Ingredient;
import com.Dietack.Model.Bean.Recipe;
import com.Dietack.Model.Bean.User;
import com.Dietack.Model.IngredientModel;
import com.Dietack.Model.RecipeModel;
import com.Dietack.Model.UserModel;
import javafx.util.Pair;
import org.json.simple.JSONObject;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by enrico on 10/03/16.
 */
public class RecipesServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); //same things.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		int id = 1;
		User user = null;
		try {
			user = UserModel.getUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		String ingredientsString = request.getParameter("ingredients");
		System.out.println("ingredients from http: " + ingredientsString);
		Collection<Ingredient> ingredients = null;
		if(ingredientsString != null)
			ingredients = parseIngredients(ingredientsString);

		List<Recipe> recipeList = null; //query that given ingredients gives recipe list with given ingr.
		try {
			recipeList = RecipeModel.filterByIngredients(ingredients);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		System.out.println("user: " + user);
		Collection<Recipe> filteredRecipes = user.filterRecipesForDiet(recipeList);
		if(filteredRecipes == null || filteredRecipes.isEmpty())
			response.getWriter().write("{}");
		else
			response.getWriter().write(toJSON(filteredRecipes));
	}

	private Collection<Ingredient> parseIngredients(String string){
		String[] stringList = string.split(",");
		System.out.println("string splitted: " + stringList.length);

		Collection<Ingredient> ingredients = null;

		try {
			ingredients = IngredientModel.retainIfExist(stringList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		System.out.println("ingredients back: " + ingredients.size());
		return ingredients;
	}

	private String toJSON(Collection<Recipe> recipesList){
		List<JSONObject> recipeJSONList = new ArrayList<JSONObject>(); //list of Recipe encoded in JSON

		for(Recipe recipe : recipesList) { //encode every recipe in JSON and add to the list.
			List<JSONObject> ingredientsJSON = new ArrayList<JSONObject>();
			Set<Pair<Ingredient, Double>> ingredientsList = recipe.getIngredients();
			for (Pair<Ingredient, Double> couple : ingredientsList) { //encode Ingredient in JSON.
				Ingredient ing = couple.getKey();
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

/* //just for testing toJSON().
	public static void main(String args[]){
		Ingredient[] ingredienti = new Ingredient[10];
		for(int i=0; i<10; i++) {
			ingredienti[i] = new Ingredient();
			ingredienti[i].setName("ingr"+i);
			ingredienti[i].setId(i);
			ingredienti[i].setCalories(i);
			ingredienti[i].setMeasureUnit("ms"+i);
		}

		List<Recipe> lista = new ArrayList<Recipe>();
		Recipe ricetta1 = new Recipe();
		ricetta1.setId(1);
		ricetta1.setInstructions("per fare dei canederli gne gne");
		ricetta1.setName("canederli");
		for(int i=0; i< 5; i++)
			ricetta1.addIngredient(ingredienti[i], 20*i%7/2);

		lista.add(ricetta1);

		Recipe ricetta2 = new Recipe();
		ricetta2.setId(2);
		ricetta2.setInstructions("se fa balotole de pan grataaaaaaa");
		ricetta2.setName("proCnodel");
		for(int i=5; i< 10; i++)
			ricetta2.addIngredient(ingredienti[i], 9*i%7/2);

		lista.add(ricetta2);
		RecipesServlet servlet = new RecipesServlet();
		System.out.println(servlet.toJSON(lista));
	}*/


}
