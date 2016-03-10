package com.Dietack.Model;

import com.Dietack.Model.Bean.Ingredient;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            res.setCalories(rs.getDouble("kcal"));
            res.setFoto(rs.getString("foto"));

        } else {

            res = null;

        }

        rs.close();
        ps.close();
        connection.close();

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

        } else {

            res = null;

        }

        rs.close();
        ps.close();
        connection.close();

        return res;

    }
}
