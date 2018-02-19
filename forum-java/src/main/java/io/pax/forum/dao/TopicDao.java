package io.pax.forum.dao;

import io.pax.forum.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public class TopicDao {
    Connector connector = new Connector();

    public List<Topic> listTopics() throws SQLException {

        List<Topic> topics = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM topic");

        while (rs.next()) {
            String title = rs.getString("title");
            int id = rs.getInt("id");
            topics.add(new ForumTopic(id, title));
        }

        rs.close();
        stmt.close();
        conn.close();

        return topics;
    }

    public int createTopic(int adminId, String title) throws SQLException {
        String query = "INSERT INTO topic (title, admin_id) VALUES (?,?)";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, title);
        statement.setInt(2, adminId);

        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);


        statement.close();
        conn.close();

        return id;
    }

/*    public Topic findTopicWithComments(int topicId) throws SQLException {
        Connection connection = this.connector.getConnection();
        String query = "SELECT * FROM user u JOIN comment co ON u.id=co.user_id WHERE co.topic_id =?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, topicId);
        ResultSet set = statement.executeQuery();

        ForumComment comment = null;
        ForumTopic topic = new ForumTopic(topicId);
        List<Comment> comments = new ArrayList<>();

        while (set.next()) {
            int userId = set.getInt("co.user_id");
            String name = set.getString("name");
            User user = new ForumUser(name);
            comment = new ForumComment(user, topicId);
            comments.add(comment);

        }
        topic.setComments(comments);

        return topic;
    }*/

    public List<Topic> findByTitle(String extract) throws SQLException {
        String query = "SELECT * FROM topic WHERE title LIKE ?";
        List<Topic> topics = new ArrayList<>();
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, extract + "%");

        ResultSet keys = statement.executeQuery();


        while (keys.next()) {
            int id = keys.getInt("id");
            String title = keys.getString("title");
            topics.add(new ForumTopic(id, title));
        }

        statement.execute();
        statement.close();
        conn.close();

        return topics;
    }

    public void deleteTopicById(int topicId) throws SQLException {
        String query = "DELETE FROM topic WHERE id=?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, topicId);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException {

        TopicDao dao = new TopicDao();
        System.out.println(dao.listTopics());
        dao.createTopic(13, "termites");

    }
}
