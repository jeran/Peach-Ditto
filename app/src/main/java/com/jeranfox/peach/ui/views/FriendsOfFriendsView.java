package com.jeranfox.peach.ui.views;

import android.content.Context;

import com.jeranfox.peach.entities.Friend;

import java.util.List;

public interface FriendsOfFriendsView {
    void setFriendsOfFriends(List<Friend> friendsOfFriends);
    Context getContext();
}
