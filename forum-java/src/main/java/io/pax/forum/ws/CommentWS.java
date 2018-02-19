package io.pax.forum.ws;

import io.pax.forum.dao.CommentDao;
import io.pax.forum.domain.Comment;

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

    @GET
    @Path("{id}")
    public List<Comment> getCommentsFromUser(@PathParam("id") int userId) throws SQLException {
        CommentDao dao = new CommentDao();
        return dao.listCommentsFromUser(userId);
    }

}
