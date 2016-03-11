package com.Dietack.Model;

import com.Dietack.Model.Bean.Ingredient;
import com.Dietack.Model.Bean.Recipe;
import com.sun.istack.internal.Nullable;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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

			//now generate the list of pairs
			String queryIngredienti = "SELECT * from ingredienti_ricetta\n" +
					"WHERE id_ricetta = ? ;";
			PreparedStatement ps2 = connection.prepareStatement(queryIngredienti);
			ps2.setInt(1, id);

			rs2 = ps2.executeQuery();

			while ( rs2.next() ) {

				Ingredient temp = IngredientModel.getIngredientById(rs2.getInt("id_ingrediente"));
				res.addIngredient(temp, rs.getDouble("quantita"));

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

	public static List<Recipe> filterByIngredients(Collection<Ingredient> ingredients) throws SQLException, NamingException {
		List<Recipe> recipes = new LinkedList<Recipe>();

		Connection connection;
		ResultSet rs;

		/**
		 * TODO: per prima cosa seleziono tutte le ricette con i loro ingredienti
		 * 			poi il resto viene fatto da java con le collection.
		 *
		 */

		String queryAllRicette = "SELECT * FROM ricetta;";




		connection = Connector.getConnection();
		PreparedStatement ps = connection.prepareStatement(queryAllRicette);


		rs = ps.executeQuery();

		while(rs.next()){
			System.out.println("CICLO");
			Collection collectionTemp = new LinkedList();
			ResultSet rs2;
			Recipe recipe = new Recipe();
			recipe.setId(rs.getInt("id_ricetta"));
			recipe.setName(rs.getString("nome"));
			recipe.setFoto(rs.getString("foto"));
			recipe.setInstructions(rs.getString("istruzioni"));

			//now generate the list of pairs
			String queryIngredienti = "SELECT * from ingredienti_ricetta\n" +
					"WHERE id_ricetta = ? ;";
			PreparedStatement ps2 = connection.prepareStatement(queryIngredienti);
			ps2.setInt(1, recipe.getId());

			rs2 = ps2.executeQuery();

			while ( rs2.next() ) {

				Ingredient temp = IngredientModel.getIngredientById(rs2.getInt("id_ingrediente"));
				collectionTemp.add(temp);
				System.out.println("AGGIUNGO INGREDIENTE " + temp.getId());
				recipe.addIngredient(temp, rs2.getDouble("quantita"));

			}

			rs2.close();
			ps2.close();

			//adesso controllo se aggiungerli o no

			if (collectionTemp.containsAll(ingredients) && ingredients.size() != 0) {

				System.out.println(collectionTemp);
				System.out.println((ingredients));
				System.out.println("FOUND!");
				recipes.add(recipe);

			}

		}

		ps.close();
		rs.close();
		connection.close();

		return recipes;
	}
}
