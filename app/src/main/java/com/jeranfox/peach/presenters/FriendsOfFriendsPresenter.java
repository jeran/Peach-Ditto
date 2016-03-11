package com.jeranfox.peach.presenters;

import com.jeranfox.peach.ui.views.FriendsOfFriendsView;

public interface FriendsOfFriendsPresenter extends Presenter {
    void setView(FriendsOfFriendsView view);
    void loadFriendsOfFriends();
}
