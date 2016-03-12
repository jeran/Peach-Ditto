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
                if (messageText != null) {
                    return messageText;
                }
            }
        }
        return "";
    }

    public String getAvatarSrc() {
        return connection.getAvatarSrc();
    }

    public int getLastOnline() {
        return connection.getLastOnline();
    }
}