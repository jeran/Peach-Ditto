package com.jeranfox.peach.presenters;

import com.jeranfox.peach.SimpleCallback;
import com.jeranfox.peach.entities.ExploreItem;
import com.jeranfox.peach.models.ExploreModel;
import com.jeranfox.peach.models.ExploreModelImpl;
import com.jeranfox.peach.ui.views.ExploreView;

public class ExplorePresenterImpl implements ExplorePresenter {
    private ExploreView exploreView;
    private ExploreModel exploreModel;
    private ExploreItem[] exploreItems;

    public ExplorePresenterImpl(ExploreView exploreView) {
        this.exploreView = exploreView;
        this.exploreModel = new ExploreModelImpl(exploreView.getContext());
    }

    @Override
    public void setView(ExploreView exploreView) {
        this.exploreView = exploreView;
    }

    @Override
    public void loadExploreFeed() {
        if (exploreItems != null) {
            exploreView.setExploreItems(exploreItems);
        } else {
            exploreView.showLoading();
            exploreModel.getExploreFeed(new SimpleCallback<ExploreItem[]>() {
                @Override
                public void onSuccess(ExploreItem[] response) {
                    exploreItems = response;
                    if (exploreView != null) {
                        exploreView.setExploreItems(exploreItems);
                        exploreView.hideLoading();
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {
                    if (exploreView != null) {
                        exploreView.hideLoading();
                    }
                    // TODO(jeran): handle failure
                    // log failure
                    // view.showFailure
                }
            });
        }
    }

    @Override
    public void onDestroy(boolean isFinishing) {
        setView(null);
        if (isFinishing) {
            exploreModel.cancelRequests();
        }
    }
}
