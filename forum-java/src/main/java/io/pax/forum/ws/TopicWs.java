package io.pax.forum.ws;

import io.pax.forum.dao.TopicDao;
import io.pax.forum.domain.ForumTopic;
import io.pax.forum.domain.Topic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
@Path("topics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicWs {

    @GET
    public List<Topic> getTopics() throws SQLException {
        TopicDao dao = new TopicDao();
        return dao.listTopics();
    }

    @POST
    public ForumTopic createTopic(ForumTopic topic) throws SQLException {
        if (topic.getTitle().length() < 2){
            throw new NotAcceptableException("406: user name must have at least 2 letters");
        }

        try {
            System.out.println(topic.getTitle());
            int id = new TopicDao().createTopic(topic.getTitle());
            return new ForumTopic(id, topic.getTitle());
        } catch (SQLException e) {
            throw e;
        }

    }

    @DELETE
    @Path("{id}")
    public void deleteTopic(@PathParam("id") int topicId) throws SQLException {

        TopicDao dao = new TopicDao();
        dao.deleteTopicById(topicId);
    }

}
