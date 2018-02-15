package io.pax.forum.domain;

/**
 * Created by AELION on 15/02/2018.
 */
public class ForumComment implements Comment {
    String title;
    String content;

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getContent() {
        return this.content;
    }

}
