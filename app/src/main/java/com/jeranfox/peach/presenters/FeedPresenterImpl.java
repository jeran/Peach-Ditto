package com.jeranfox.peach.presenters;

import com.jeranfox.peach.SimpleCallback;
import com.jeranfox.peach.entities.FeedData;
import com.jeranfox.peach.models.FeedModel;
import com.jeranfox.peach.models.FeedModelImpl;
import com.jeranfox.peach.ui.views.FeedView;

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
        if (feedData != null) {
            feedView.setFeedData(feedData);
        } else {
            feedView.showLoading();
            feedModel.getFeed(new SimpleCallback<FeedData>() {
                @Override
                public void onSuccess(FeedData response) {
                    feedData = response;
                    if (feedView != null) {
                        feedView.setFeedData(feedData);
                        feedView.hideLoading();
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {
                    if (feedView != null) {
                        feedView.hideLoading();
                        feedView.displayErrorMessage(throwable.getMessage());
                        feedView.showLoadingError();
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy(boolean isFinishing) {
        setView(null);
        if (isFinishing) {
            feedModel.cancelRequests();
        }
    }

    @Override
    public void signOut() {
        feedModel.signOut();
    }
}
