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
    public void setView(ExploreView view) {
        this.exploreView = view;
    }

    @Override
    public void loadExploreFeed() {
        if (exploreItems != null) {
            exploreView.setExploreItems(exploreItems);
        } else {
            // TODO(jeran): view.showLoading();
            exploreModel.getExploreFeed(new SimpleCallback<ExploreItem[]>() {
                @Override
                public void onSuccess(ExploreItem[] response) {
                    exploreItems = response;
                    exploreView.setExploreItems(exploreItems);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    // TODO(jeran): handle failure
                    // log failure
                    // view.showFailure
                }
            });
        }
    }

    @Override
    public void releaseView() {
        setView(null);
        exploreModel.cancelRequests();
    }
}
