package com.jeranfox.peach.ui.views;

import android.content.Context;

import com.jeranfox.peach.entities.FeedData;

public interface FeedView {
    Context getContext();

    void setFeedData(FeedData feedData);

    void showLoading();

    void hideLoading();
}
