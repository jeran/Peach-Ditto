package com.jeranfox.peach.ui.components;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jeranfox.peach.PresenterHolder;
import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.ExploreItem;
import com.jeranfox.peach.presenters.ExplorePresenter;
import com.jeranfox.peach.presenters.ExplorePresenterImpl;
import com.jeranfox.peach.ui.views.ExploreView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExplorePage extends FrameLayout implements ExploreView {
    private ExplorePresenter explorePresenter;

    @Bind(R.id.explore_recycler_view)
    RecyclerView recyclerView;

    public ExplorePage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_explore, this, true);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        explorePresenter = createPresenter();
        explorePresenter.loadExploreFeed();
    }

    @Override
    public void setExploreItems(ExploreItem[] exploreItems) {
        recyclerView.setAdapter(new ExploreRecyclerViewAdapter(exploreItems));
    }

    public ExplorePresenter createPresenter() {
        ExplorePresenter presenter = PresenterHolder.getInstance().getPresenter(ExplorePresenter.class);
        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new ExplorePresenterImpl(this);
        }
        return presenter;
    }
}
