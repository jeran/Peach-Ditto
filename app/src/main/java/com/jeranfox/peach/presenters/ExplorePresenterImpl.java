package com.jeranfox.peach.presenters;

import android.os.Handler;
import android.os.Looper;

import com.jeranfox.peach.entities.Connection;
import com.jeranfox.peach.ui.views.ExploreView;

import java.util.ArrayList;
import java.util.List;

public class ExplorePresenterImpl implements ExplorePresenter {
    private ExploreView exploreView;
    private List<Connection> connections = new ArrayList<>();

    public ExplorePresenterImpl(ExploreView exploreView) {
        this.exploreView = exploreView;
    }

    @Override
    public void setView(ExploreView view) {
        this.exploreView = view;
    }

    @Override
    public void loadExploreFeed() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    connections.add(new Connection());
                }
                exploreView.setConnections(connections);
            }
        }, 1000);

//        Peach.with(exploreView.getContext()).getExploreFeed(new Callback<ExploreResponse>() {
//            @Override
//            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
//                Timber.i("" + response.body().getConnections()[0].getDisplayName());
//            }
//
//            @Override
//            public void onFailure(Call<ExploreResponse> call, Throwable t) {
//                // TOT
//                Timber.i("onFailure");
//            }
//        });
    }
}
