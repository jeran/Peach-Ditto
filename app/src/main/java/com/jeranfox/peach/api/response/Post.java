package com.jeranfox.peach.api.response;

public class Post {
    String id;
    Message[] message;
    int commentCount;
    boolean hasMoreComments;
    int likeCount;
    boolean hasMoreLikes;
    boolean likedByMe;
    boolean isUnread;
    int createdTime;
    int updatedTime;

    public Message[] getMessage() {
        return message;
    }

    public int getCreatedTime() {
        return createdTime;
    }
}
