package com.jeranfox.peach.ui.views;

import android.content.Context;

import com.jeranfox.peach.entities.ExploreItem;

public interface ExploreView {
    void setExploreItems(ExploreItem[] exploreItems);

    Context getContext();

    void showLoading();

    void hideLoading();

    void displayErrorMessage(String message);
}
