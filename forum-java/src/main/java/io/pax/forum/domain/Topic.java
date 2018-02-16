package io.pax.forum.domain;

import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public interface Topic {
    int getId();
    String getTitle();
    List<Comment> getComments();
}
