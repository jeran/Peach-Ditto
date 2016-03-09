package com.jeranfox.peach;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class FriendsOfFriendsPage extends FrameLayout {
    public FriendsOfFriendsPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_friends_of_friends, this, true);
    }
}
