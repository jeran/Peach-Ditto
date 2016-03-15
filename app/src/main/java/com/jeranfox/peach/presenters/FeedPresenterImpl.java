package com.jeranfox.peach.presenters;

import com.jeranfox.peach.SimpleCallback;
import com.jeranfox.peach.entities.FeedData;
import com.jeranfox.peach.models.FeedModel;
import com.jeranfox.peach.models.FeedModelImpl;
import com.jeranfox.peach.ui.views.FeedView;

import timber.log.Timber;

public class FeedPresenterImpl implements FeedPresenter {
    private FeedView feedView;
    private FeedModel feedModel;

    private FeedData feedData;

    public FeedPresenterImpl(FeedView feedView) {
        this.feedView = feedView;
        this.feedModel = new FeedModelImpl(feedView.getContext());
    }

    @Override
    public void setView(FeedView view) {
        this.feedView = view;
    }

    @Override
    public void loadFeed() {
        feedModel.getFeed(new SimpleCallback<FeedData>() {
            @Override
            public void onSuccess(FeedData response) {
                feedData = response;
                feedView.setFeedData(feedData);
            }

            @Override
            public void onFailure(Throwable throwable) {
                // TODO(jeran): handle failure
                // log failure
                // show failure
            }
        });
    }
}
