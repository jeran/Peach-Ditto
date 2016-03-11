package com.jeranfox.peach.models;

import com.jeranfox.peach.SimpleCallback;
import com.jeranfox.peach.entities.ExploreItem;

public interface ExploreModel {
    void cancelRequests();

    void getExploreFeed(SimpleCallback<ExploreItem[]> callback);
}
