package com.jeranfox.peach.presenters;

import android.os.Handler;
import android.os.Looper;

import com.jeranfox.peach.entities.Friend;
import com.jeranfox.peach.ui.views.FriendsOfFriendsView;

import java.util.ArrayList;
import java.util.List;

public class FriendsOfFriendsPresenterImpl implements FriendsOfFriendsPresenter {
    private FriendsOfFriendsView friendsOfFriendsView;
    private List<Friend> friends = new ArrayList<>();

    public FriendsOfFriendsPresenterImpl(FriendsOfFriendsView friendsOfFriendsView) {
        this.friendsOfFriendsView = friendsOfFriendsView;
    }

    @Override
    public void setView(FriendsOfFriendsView view) {
        this.friendsOfFriendsView = view;
    }

    @Override
    public void loadFriendsOfFriends() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    friends.add(new Friend());
                }
                friendsOfFriendsView.setFriendsOfFriends(friends);
            }
        }, 1000);

//        Peach.with(friendsOfFriendsView.getContext()).getExploreFeed(new Callback<ExploreResponse>() {
//            @Override
//            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
//                Timber.i("" + response.body().getConnections()[0].getDisplayName());
//            }
//
//            @Override
//            public void onFailure(Call<ExploreResponse> call, Throwable t) {
//                // TOT
//                Timber.i("onFailure");
//            }
//        });
    }
}
