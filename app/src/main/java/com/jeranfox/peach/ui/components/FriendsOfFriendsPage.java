package com.jeranfox.peach.ui.components;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jeranfox.peach.PresenterHolder;
import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.Friend;
import com.jeranfox.peach.presenters.FriendsOfFriendsPresenter;
import com.jeranfox.peach.presenters.FriendsOfFriendsPresenterImpl;
import com.jeranfox.peach.ui.views.FriendsOfFriendsView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendsOfFriendsPage extends FrameLayout implements FriendsOfFriendsView {
    private FriendsOfFriendsPresenter friendsOfFriendsPresenter;

    @Bind(R.id.friends_of_friends_recycler_view)
    RecyclerView recyclerView;

    public FriendsOfFriendsPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_friends_of_friends, this, true);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        friendsOfFriendsPresenter = createPresenter();
        friendsOfFriendsPresenter.loadFriendsOfFriends();
    }

    @Override
    public void setFriendsOfFriends(List<Friend> friendsOfFriends) {
        recyclerView.setAdapter(new FriendsOfFriendsRecyclerViewAdapter(friendsOfFriends));
    }

    public FriendsOfFriendsPresenter createPresenter() {
        FriendsOfFriendsPresenter presenter = PresenterHolder.getInstance().getPresenter(FriendsOfFriendsPresenter.class);
        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new FriendsOfFriendsPresenterImpl(this);
        }
        return presenter;
    }
}
