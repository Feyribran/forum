package io.pax.forum.domain;

import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public class ForumTopic implements Topic{

    String title;
    int id;
    List<Comment> comments;

    public ForumTopic(){
    }

    public ForumTopic(int id, String title){
        this.id = id;
        this.title = title;
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
