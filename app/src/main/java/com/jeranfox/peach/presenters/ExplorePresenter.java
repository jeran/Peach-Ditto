package com.jeranfox.peach.presenters;

import com.jeranfox.peach.ui.views.ExploreView;

public interface ExplorePresenter extends Presenter {
    void setView(ExploreView view);

    void loadExploreFeed();

    void onDestroy(boolean isFinishing);
}
