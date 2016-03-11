package com.jeranfox.peach.ui.components;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.Friend;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendsOfFriendsPage extends FrameLayout {

    @Bind(R.id.friends_of_friends_recycler_view)
    RecyclerView recyclerView;

    public FriendsOfFriendsPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_friends_of_friends, this, true);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<Friend> friends = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            friends.add(new Friend());
        }

        recyclerView.setAdapter(new FriendsOfFriendsRecyclerViewAdapter(friends));
    }
}
