package io.pax.forum.domain;

/**
 * Created by AELION on 15/02/2018.
 */
public interface Comment {

    User getUser();
    Topic getTopic();
    String getContent();
}
