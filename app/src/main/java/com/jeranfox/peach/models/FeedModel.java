package com.jeranfox.peach.models;

import com.jeranfox.peach.SimpleCallback;
import com.jeranfox.peach.entities.FeedData;

public interface FeedModel {
    void cancelRequests();

    void getFeed(SimpleCallback<FeedData> callback);
}
