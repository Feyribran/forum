package io.pax.forum.dao;

import io.pax.forum.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */

public class CommentDao {

    Connector connector = new Connector();

    public List<Comment> listComments() throws SQLException {

        List<Comment> comments = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM comment");

        while (rs.next()){
            String content = rs.getString("content");
            int id = rs.getInt("id");
            ForumComment comment = new ForumComment(id);
            comment.setContent(content);

            comments.add(comment);
        }

        rs.close();
        stmt.close();
        conn.close();

        return comments;
    }

    public List<Comment> listCommentsFromUser(int userId) throws SQLException {

        String query = "SELECT * FROM comment c JOIN user u ON c.user_id=u.id WHERE u.id =?";
        List<Comment> comments = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            String name = rs.getString("name");
            String content = rs.getString("content");
            int id = rs.getInt("id");

            ForumComment comment = new ForumComment(id, new ForumUser(userId, name));
            comment.setContent(content);
            comments.add(comment);
        }

        rs.close();
        statement.close();
        conn.close();

        return comments;
    }

    public ForumUser findUser(int userId) throws SQLException {
        String query = "SELECT * FROM  user WHERE id =?";
        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet rs = statement.executeQuery();

        rs.next();
        String name = rs.getString("name");
        int id = rs.getInt("id");

        ForumUser user = new ForumUser(id, name);

        rs.close();
        statement.close();
        conn.close();

        return user;
    }

    public List<Comment> findCommentsFromTopic(int topicId) throws SQLException {

        String query = "SELECT * FROM comment c JOIN topic t ON c.topic_id=t.id WHERE c.topic_id =?";
        List<Comment> comments = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, topicId);

        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            String title = rs.getString("t.title");
            String content = rs.getString("c.content");
            int id = rs.getInt("c.id");
            System.out.println(id);

            int userId = rs.getInt("c.user_id");
            ForumUser user =findUser(userId);


            ForumComment comment = new ForumComment(id, new ForumTopic(topicId, title));
            comment.setUser(user);
            comment.setContent(content);
            comments.add(comment);
        }
        rs.close();
        statement.close();
        conn.close();

        return comments;
    }

    public static void main(String[] args) throws SQLException {
        CommentDao dao = new CommentDao();


        System.out.println(dao.findCommentsFromTopic(3));
    }

}
