package io.pax.forum.ws;

import io.pax.forum.dao.CommentDao;
import io.pax.forum.domain.Comment;
import io.pax.forum.domain.ForumComment;
import io.pax.forum.domain.ForumTopic;
import io.pax.forum.domain.ForumUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
@Path("comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentWS {

    @GET
    public List<Comment> getComments() throws SQLException {
        CommentDao dao = new CommentDao();
        return dao.listComments();
    }

/*    @GET
    @Path("{userId}")
    public List<Comment> getCommentsFromUser(@PathParam("userId") int userId) throws SQLException {
        CommentDao dao = new CommentDao();

        return dao.listCommentsFromUser(userId);
    }*/

    @GET
    @Path("{topicId}")
    public List<Comment> getCommentsFromTopic(@PathParam("topicId") int topicId) throws SQLException {
        CommentDao dao = new CommentDao();

        return dao.findCommentsFromTopic(topicId);
    }

    @POST
    public ForumComment createComment(ForumComment comment) throws SQLException {

        ForumTopic topic = comment.getTopic();
        ForumUser user = comment.getUser();
        if (comment.getContent().length() < 2) {
            throw new NotAcceptableException("406: user name must have at least 2 letters");
        }

        try {
            int id = new CommentDao().createComment(comment.getContent(), comment.getUser().getId(), comment.getTopic().getId() );
            return new ForumComment(id, comment.getTopic(), comment.getUser());
        } catch (SQLException e) {
            throw e;
        }

    }

}
