package com.jeranfox.peach.entities;

import com.jeranfox.peach.api.response.Connection;
import com.jeranfox.peach.api.response.Message;
import com.jeranfox.peach.api.response.Post;

public class ExploreItem {
    private Connection connection;

    public ExploreItem(Connection connection) {
        this.connection = connection;
    }

    public String getDisplayName() {
        return connection.getDisplayName();
    }

    public String getLastPost() {
        Post[] posts = connection.getPosts();
        if (posts.length > 0) {
            Message[] messages = posts[0].getMessage();
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
        Post[] posts = connection.getPosts();
        if (posts.length > 0) {
            return posts[posts.length - 1].getCreatedTime();
        }
        return -1;
    }
}
