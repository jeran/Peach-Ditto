package com.jeranfox.peach.models;

import android.content.Context;

import com.jeranfox.peach.SimpleCallback;
import com.jeranfox.peach.api.Peach;
import com.jeranfox.peach.api.response.FeedResponse;
import com.jeranfox.peach.entities.FeedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedModelImpl implements FeedModel {
    private final Peach peach;

    public FeedModelImpl(Context context) {
        peach = new Peach(context);
    }

    @Override
    public void cancelRequests() {
        peach.cancelRequests();
    }

    @Override
    public void getFeed(final SimpleCallback<FeedData> simpleCallback) {
        peach.getFeed(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                simpleCallback.onSuccess(processFeed(response.body()));
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                simpleCallback.onFailure(t);
            }
        });
    }

    private static FeedData processFeed(FeedResponse feedResponse) {
        return new FeedData(feedResponse);
    }
}
