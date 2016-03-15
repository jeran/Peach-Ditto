package com.jeranfox.peach.presenters;

import com.jeranfox.peach.ui.views.FeedView;

public interface FeedPresenter extends Presenter {
    void setView(FeedView view);

    void loadFeed();
}
