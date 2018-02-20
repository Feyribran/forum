package io.pax.forum.domain;

/**
 * Created by AELION on 15/02/2018.
 */
public interface Comment {

    ForumTopic getTopic();
    User getUser();
    String getContent();
}
