package com.jeranfox.peach.entities;

import com.jeranfox.peach.api.response.FeedResponse;

public class FeedData {
    private FeedResponse feedResponse;

    public FeedData(FeedResponse feedResponse) {
        this.feedResponse = feedResponse;
    }

    public ExploreItem getExploreItem(int position) {
        return new ExploreItem(feedResponse.getConnections()[position]);
    }

    public int getSize() {
        return feedResponse.getConnections().length;
    }
}
