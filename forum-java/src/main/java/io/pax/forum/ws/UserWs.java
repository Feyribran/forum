package io.pax.forum.ws;


import io.pax.forum.dao.UserDao;
import io.pax.forum.domain.ForumUser;
import io.pax.forum.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 *
 **/
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}")
    public ForumUser getUser(@PathParam("id") int userId) throws SQLException {
        UserDao dao = new UserDao();
        return dao.findUserWithTopics(userId);
    }

    @POST
    public ForumUser createUser(ForumUser user) throws SQLException {
        if (user.getName().length() < 2){
            throw new NotAcceptableException("406: user name must have at least 2 letters");
        }

        try {
            System.out.println(user.getName());
            int id = new UserDao().createUser(user.getName());
            return new ForumUser(id, user.getName());
        } catch (SQLException e) {
            throw e;
        }

    }

    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") int userId) throws SQLException {

        UserDao dao = new UserDao();
        dao.deleteUserById(userId);
    }
}
