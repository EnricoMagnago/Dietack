package com.Dietack.Control;

import com.Dietack.Model.Bean.Recipe;
import com.Dietack.Model.Bean.User;
import com.Dietack.Model.RecipeModel;
import com.Dietack.Model.UserModel;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by enrico on 11/03/16.
 */

public class AddHistoryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idRecipe = Integer.parseInt(request.getParameter("idRicetta"));
		int idUser = 1;//Integer.parseInt(request.getParameter("idUtente"));

		Recipe recipe = null;
		try {
			recipe = RecipeModel.getRecipeById(idRecipe);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		User user = null;
		try {
			user = UserModel.getUserById(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if(user == null) {
			System.out.println("\tnull user: can not update history");
			response.getWriter().write("null user");
		}else if(recipe == null) {
			System.out.println("\tnull recipe: can not update history");
			response.getWriter().write("null recipe");
		}else {
			user.addEatEvent(recipe);
			System.out.println("\trecipe " + recipe.getId() + " added to user: " + user.getIdUser());
			response.getWriter().write("done");
		}
	}
}
