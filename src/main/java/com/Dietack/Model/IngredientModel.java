package com.Dietack.Model;

import com.Dietack.Model.Bean.Ingredient;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by hypertesto on 10/03/16.
 */
public class IngredientModel {

    public static Ingredient getIngredientById(int id) throws SQLException, NamingException {

        Connection connection;
        ResultSet rs;
        Ingredient res;

        String query = "SELECT * from ingredienti\n" +
                "WHERE id = ? ;";

        connection = Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        rs = ps.executeQuery();

        if ( rs.next() ) {

            res = new Ingredient();
            res.setId(rs.getInt("id"));
	        res.setName(rs.getString("nome"));
            res.setCalories(rs.getDouble("kcal"));
            res.setFoto(rs.getString("foto"));
	        res.setMeasureUnit(rs.getString("um"));

        } else {

            res = null;

        }

        rs.close();
        ps.close();
        connection.close();

        return res;

    }


    public static Collection<Ingredient> retainIfExist(Collection<String> lista)  throws SQLException, NamingException{
	    Collection<Ingredient> res = new LinkedList<Ingredient>();
	    for(String ingredient : lista){
		    Ingredient ing = IngredientModel.getIngredientByName(ingredient);
		    if(ing != null){

			    res.add(ing);
			    System.out.println("nome ingrediente aggiunto: " + ing.getName());
		    }
	    }
	    return res;
    }

    public static Ingredient getIngredientByName ( String name ) throws SQLException, NamingException {

        Connection connection;
        ResultSet rs;
        Ingredient res;

        String query = "SELECT * from ingredienti\n" +
                "WHERE nome = ? ;";

        connection = Connector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);

        rs = ps.executeQuery();

        if ( rs.next() ) {

	        res = new Ingredient();
	        res.setId(rs.getInt("id"));
	        res.setCalories(rs.getDouble("kcal"));
	        res.setFoto(rs.getString("foto"));
	        res.setMeasureUnit(rs.getString("um"));


        } else {

            res = null;

        }

        rs.close();
        ps.close();
        connection.close();

        return res;

    }
}
