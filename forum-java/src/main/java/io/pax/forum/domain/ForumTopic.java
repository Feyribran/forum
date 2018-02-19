package io.pax.forum.domain;

import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public class ForumTopic implements Topic{

    String title;
    int id;
    ForumUser admin;
    List<Comment> comments;

    public ForumTopic(){
    }

    public ForumTopic(int id, String title){
        this.id = id;
        this.title = title;
    }

    public ForumTopic(int id, String title, ForumUser admin){
        this.id = id;
        this.title = title;
        this.admin = admin;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public ForumUser getAdmin() {
        return this.admin;
    }

    public void setAdmin(ForumUser admin) {
        this.admin = admin;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Comment> getComments() {
        return this.comments;
    }

    public void setName(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Topic : "+ title;
    }
}
