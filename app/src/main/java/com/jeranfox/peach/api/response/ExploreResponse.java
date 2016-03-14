package com.jeranfox.peach.api.response;

public class ExploreResponse extends ApiResponse {
    Data data;

    public Connection[] getConnections() {
        return data.connections;
    }

    static class Data {
        Connection[] connections;
    }

    public static class Connection {
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

    public static class Post {
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

    public static class Message {
        String text;
        String type;
        int height;
        String src;
        int width;

        public String getText() {
            return text;
        }

        public String getType() {
            return type;
        }
    }
}
