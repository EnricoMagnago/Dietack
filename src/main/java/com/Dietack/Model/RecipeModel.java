package com.Dietack.Model;

import com.Dietack.Model.Bean.Ingredient;
import com.Dietack.Model.Bean.Recipe;
import com.sun.istack.internal.Nullable;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hypertesto on 10/03/16.
 */
public class RecipeModel {

	public static Recipe getRecipeById(int id) throws SQLException, NamingException {

		Connection connection;
		ResultSet rs, rs2;
		Recipe res;

		String query = "SELECT * from ricetta\n" +
				"WHERE id_ricetta = ? ;";

		connection = Connector.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);

		rs = ps.executeQuery();

		if ( rs.next() ) {

			res = new Recipe();
			res.setId(rs.getInt("id_ricetta"));
			res.setName(rs.getString("nome"));
			res.setFoto(rs.getString("foto"));
			res.setInstructions(rs.getString("istruzioni"));

			//now generate the list of pairs
			String queryIngredienti = "SELECT * from ingredienti_ricetta\n" +
					"WHERE id_ricetta = ? ;";
			PreparedStatement ps2 = connection.prepareStatement(queryIngredienti);
			ps2.setInt(1, id);

			rs2 = ps2.executeQuery();

			while ( rs2.next() ) {

				Ingredient temp = IngredientModel.getIngredientById(rs2.getInt("id_ingrediente"));
				res.addIngredient(temp, rs2.getDouble("quantita"));

			}

			rs2.close();
			ps2.close();

		} else {

			res = null;

		}

		rs.close();
		ps.close();
		connection.close();

		return res;


	}

	public static List<Recipe> filterByIngredients(@Nullable  Collection<Ingredient> ingredients) throws SQLException, NamingException {
		List<Recipe> recipes = new LinkedList<Recipe>();

		Connection connection;
		ResultSet rs;

		String query = "SELECT id_ricetta from ingredienti_ricetta AS ing_ric\n";
		if(ingredients == null || ingredients.isEmpty()) //if there is nothing to filter, return all.
			query += ";";
		else {

			for (int i = 0; i < ingredients.size() - 1; i++) {
				query += " EXISTS (SELECT * from ingredienti_ricetta WHERE ingredienti_ricetta.id_ricetta = ing_ric.id.ricetta AND ingredienti_ricetta.id_ingrediente = ?) AND\n";
			}
			query += " EXISTS (SELECT * from ingredienti_ricetta WHERE ingredienti_ricetta.id_ricetta = ing_ric.id.ricetta AND ingredienti_ricetta.id_ingrediente = ?);";
		}

		connection = Connector.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);

		if(ingredients != null && !ingredients.isEmpty()) {
			int i = 1;
			for (Ingredient ing : ingredients) {
				ps.setInt(i, ing.getId());
				i++;
			}
		}
		rs = ps.executeQuery();

		while(rs.next()){
			Recipe recipe = RecipeModel.getRecipeById(rs.getInt("id_ricetta"));
			recipes.add(recipe);
		}
		return recipes;
	}
}
