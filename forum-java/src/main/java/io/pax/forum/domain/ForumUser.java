package io.pax.forum.domain;

import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */
public class ForumUser implements User {
    int id;
    String name;
    List<Comment> comments;

    public ForumUser(){}

    public ForumUser(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Comment> getComments() {
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
