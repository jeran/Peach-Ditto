package com.jeranfox.peach.ui.components;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jeranfox.peach.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedPage extends FrameLayout {

    @Bind(R.id.feed_recycler_view)
    RecyclerView recyclerView;

    public FeedPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_feed, this, true);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new FeedRecyclerViewAdapter());
    }
}
