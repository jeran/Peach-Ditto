package com.jeranfox.peach.entities;

import com.jeranfox.peach.api.response.ExploreResponse;

public class ExploreItem {
    private ExploreResponse.Connection connection;

    public ExploreItem(ExploreResponse.Connection connection) {
        this.connection = connection;
    }

    public String getDisplayName() {
        return connection.getDisplayName();
    }

    public String getLastPost() {
        ExploreResponse.Post[] posts = connection.getPosts();
        if (posts.length > 0) {
            ExploreResponse.Message[] messages = posts[0].getMessage();
            if (messages.length > 0) {
                String messageText = messages[0].getText();
                if (messageText != null && !messageText.isEmpty()) {
                    return messageText;
                } else {
                    return messages[0].getType();
                }
            }
        }
        return "";
    }

    public String getAvatarSrc() {
        return connection.getAvatarSrc();
    }

    public int getLastPostTime() {
        ExploreResponse.Post[] posts = connection.getPosts();
        if (posts.length > 0) {
            return posts[posts.length - 1].getCreatedTime();
        }
        return -1;
    }
}
