package com.Dietack.Model;

import com.Dietack.Model.Bean.Ingredient;
import com.Dietack.Model.Bean.Recipe;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
