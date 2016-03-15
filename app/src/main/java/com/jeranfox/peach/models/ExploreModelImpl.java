package com.jeranfox.peach.models;

import android.content.Context;

import com.jeranfox.peach.SimpleCallback;
import com.jeranfox.peach.api.Peach;
import com.jeranfox.peach.api.response.Connection;
import com.jeranfox.peach.api.response.ExploreResponse;
import com.jeranfox.peach.entities.ExploreItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreModelImpl implements ExploreModel {
    private final Peach peach;

    public ExploreModelImpl(Context context) {
        peach = new Peach(context);
    }

    @Override
    public void cancelRequests() {
        peach.cancelRequests();
    }

    @Override
    public void getExploreFeed(final SimpleCallback<ExploreItem[]> simpleCallback) {
        peach.getExploreFeed(new Callback<ExploreResponse>() {
            @Override
            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
                simpleCallback.onSuccess(processExploreFeed(response.body()));
            }

            @Override
            public void onFailure(Call<ExploreResponse> call, Throwable t) {
                simpleCallback.onFailure(t);
            }
        });
    }

    private static ExploreItem[] processExploreFeed(ExploreResponse exploreResponse) {
        Connection[] connections = exploreResponse.getConnections();
        ExploreItem[] exploreItems = new ExploreItem[connections.length];
        for (int i = 0; i < exploreItems.length; i++) {
            exploreItems[i] = new ExploreItem(connections[i]);
        }
        return exploreItems;
    }
}
