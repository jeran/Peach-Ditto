package com.jeranfox.peach.ui.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.jeranfox.peach.PresenterHolder;
import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.FeedData;
import com.jeranfox.peach.presenters.FeedPresenter;
import com.jeranfox.peach.presenters.FeedPresenterImpl;
import com.jeranfox.peach.ui.activities.IntroActivity;
import com.jeranfox.peach.ui.recycler.FeedRecyclerViewAdapter;
import com.jeranfox.peach.ui.views.FeedView;
import com.jeranfox.peach.ui.views.HomeView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedPage extends FrameLayout implements FeedView {
    private HomeView homeView;
    private FeedPresenter feedPresenter;

    @Bind(R.id.feed_recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.feed_progress)
    ProgressBar progressBar;

    @Bind(R.id.feed_loading_error)
    View errorLoading;

    public FeedPage(Context context, HomeView homeView) {
        super(context);
        setId(R.id.home_feed_page);
        this.homeView = homeView;
        LayoutInflater.from(context).inflate(R.layout.page_feed, this, true);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        feedPresenter = createPresenter();
        feedPresenter.loadFeed();
    }

    public void onDestroy(boolean isFinishing) {
        feedPresenter.onDestroy(isFinishing);
        if (isFinishing) {
            PresenterHolder.getInstance().remove(FeedPresenter.class);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        PresenterHolder.getInstance().putPresenter(FeedPresenter.class, feedPresenter);
        return super.onSaveInstanceState();
    }

    @Override
    public void setFeedData(FeedData feedData) {
        recyclerView.setAdapter(new FeedRecyclerViewAdapter(feedData, this));
        recyclerView.scrollToPosition(1);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(GONE);
    }

    @Override
    public void signOut() {
        feedPresenter.signOut();
        ((Activity) getContext()).finish();
        getContext().startActivity(new Intent(getContext(), IntroActivity
                .class));
    }

    @Override
    public void displayErrorMessage(String message) {
        homeView.displayErrorMessage(message);
    }

    @Override
    public void showLoadingError() {
        errorLoading.setVisibility(VISIBLE);
    }

    public FeedPresenter createPresenter() {
        FeedPresenter presenter = PresenterHolder.getInstance().getPresenter(FeedPresenter.class);
        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new FeedPresenterImpl(this);
        }
        return presenter;
    }
}
