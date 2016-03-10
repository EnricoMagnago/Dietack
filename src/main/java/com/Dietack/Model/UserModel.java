package com.Dietack.Model;

import com.Dietack.Model.Bean.User;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by hypertesto on 10/03/16.
 */
public class UserModel {

    private static List<User> executeQuery(PreparedStatement prepStatement) throws SQLException {

        ResultSet rs = prepStatement.executeQuery();
        List<User> res = new ArrayList<User>();

        try {

            while (rs.next()) {

                User user = new User();

                user.setIdUser(rs.getInt("id_utente"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("nome"));
                user.setSurname(rs.getString("cognome"));
                user.setPhone(rs.getString("phone"));


                res.add(user);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            rs.close();
            prepStatement.close();

        }

        return res;
    }

    /**
     * todo
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public static User getUserByMail(String email) throws SQLException, NamingException {

        Connection connection;

        String query = "SELECT * from utente\n" +
                "WHERE email = ? ;";

        connection = Connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);

        List<User> userList = executeQuery(preparedStatement);
        connection.close();
        if (userList.size() > 0) {
            System.out.println(userList.get(0));
            return userList.get(0);
        } else return null;
    }

    /**
     * todo
     *
     * @param phone
     * @return
     * @throws SQLException
     */
    public static User getUserByPhone(String phone) throws SQLException, NamingException {

        Connection connection;
        PreparedStatement ps;

        String query = "SELECT * from utente\n" +
                "WHERE phone = ? ;";

        connection = Connector.getConnection();

        ps = connection.prepareStatement(query);
        ps.setString(1, phone);

        List<User> userList = executeQuery(ps);

        connection.close();
        if (userList.size() > 0)
            return userList.get(0);
        else //if it's empty
            return null;
    }
}
