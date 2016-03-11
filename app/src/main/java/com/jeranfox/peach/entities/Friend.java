package com.jeranfox.peach.entities;

public class Friend {
    private String title = "Title";
    private String lastPost = "this is the last post";
    private String profileImageUrl = "https://scontent.fsnc1-1.fna.fbcdn.net/hphotos-xta1/t31.0-8/12182841_10153802110789015_8568192674955858701_o.jpg";
    private String timeSinceLastPost = "7 hours";

    public String getTitle() {
        return title;
    }

    public String getLastPost() {
        return lastPost;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getTimeSinceLastPost() {
        return timeSinceLastPost;
    }
}
