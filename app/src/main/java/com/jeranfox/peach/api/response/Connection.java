package com.jeranfox.peach.api.response;

public class Connection {
    String id;
    String name;
    String displayName;
    String avatarSrc;
    String bio;
    boolean isPublic;
    Post[] posts;
    int unreadPostCount;
    int lastRead;
    int lastOnline;
    boolean followsYou;
    boolean friendsSharing;
    boolean isFavorite;

    public String getDisplayName() {
        return displayName;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public Post[] getPosts() {
        return posts;
    }
}
