package io.pax.forum.dao;

import io.pax.forum.domain.ForumUser;
import io.pax.forum.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 07/02/2018.
 */
public class UserDao {

    Connector connector = new Connector();

    public List<User> listUsers() throws SQLException{

        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");

        while (rs.next()){
            String name = rs.getString("name");
            int id = rs.getInt("id");
            users.add(new ForumUser(id, name));
        }

        rs.close();
        stmt.close();
        conn.close();

        return users;
    }

    public int createUser(String name) throws SQLException {
        String query = "INSERT INTO user (name) VALUES (?)";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,name);
        //statement.setInt(1,id);

        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        int userId = keys.getInt(1);


        statement.close();
        conn.close();

        return userId;
    }


    public void deleteUserById(int userId) throws SQLException {
        String query = "DELETE FROM user WHERE id=?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,userId);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    public void deleteByName(String name) throws SQLException{
        String query = "DELETE FROM user WHERE name=?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }


    public List<User> findByName(String extract) throws SQLException{
        String query = "SELECT * FROM user WHERE name LIKE ?";
        List<User> users = new ArrayList<>();
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, extract + "%");

        ResultSet keys = statement.executeQuery();


        while (keys.next()){
            int id = keys.getInt("id");
            String name = keys.getString("name");
            users.add(new ForumUser(id, name));
        }

        statement.execute();
        statement.close();
        conn.close();


        return users;
    }


    public void updateUser(int userId, String newName) throws SQLException{
        String query = "UPDATE user SET name=? WHERE id=?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,newName);
        statement.setInt(2,userId);

        statement.executeUpdate();

        statement.close();
        conn.close();
    }


    public static void main(String[] args) throws SQLException {

        UserDao dao = new UserDao();
        //dao.deleteByName("Walou");
        dao.createUser("Prout");

    }


}
