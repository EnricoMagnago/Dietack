package com.Dietack.Control;

import com.Dietack.Model.Bean.Ingredient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		List<Ingredient> ingredients = parseIngredients(ingredientsString);
	}

	private List<Ingredient> parseIngredients(String string){
		String[] stringList = string.split(",");
		List<Ingredient> ingredients = new ArrayList<Ingredient>(stringList.length);
		
		// TODO check if the ingredients are in the db. -> query that given a list of ingrediends name returns a list of Ingrediens.
		//ingredients = query(stringList);
		return ingredients;
	}
}
