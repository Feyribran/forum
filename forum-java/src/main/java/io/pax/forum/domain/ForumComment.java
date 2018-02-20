package io.pax.forum.domain;

/**
 * Created by AELION on 15/02/2018.
 */
public class ForumComment implements Comment {

    int id;
    String content;
    ForumTopic topic;
    User user;

    public ForumComment() {
    }

    public ForumComment(int id) {
        this.id = id;
    }

    public ForumComment(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public ForumComment(int id, ForumTopic topic) {
        this.id = id;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public ForumTopic getTopic() {
        return topic;
    }

    public void setTopic(ForumTopic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return content;
    }
}
