package com.jeranfox.peach.ui.components;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.jeranfox.peach.PresenterHolder;
import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.ExploreItem;
import com.jeranfox.peach.presenters.ExplorePresenter;
import com.jeranfox.peach.presenters.ExplorePresenterImpl;
import com.jeranfox.peach.ui.recycler.ExploreRecyclerViewAdapter;
import com.jeranfox.peach.ui.views.ExploreView;
import com.jeranfox.peach.ui.views.HomeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExplorePage extends FrameLayout implements ExploreView {
    private HomeView homeView;
    private ExplorePresenter explorePresenter;

    @Bind(R.id.explore_recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.explore_progress)
    ProgressBar progressBar;

    @Bind(R.id.explore_loading_error)
    View errorLoading;

    public ExplorePage(Context context, HomeView homeView) {
        super(context);
        setId(R.id.home_explore_page);
        this.homeView = homeView;
        LayoutInflater.from(context).inflate(R.layout.page_explore, this, true);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        explorePresenter = createPresenter();
        explorePresenter.loadExploreFeed();
    }

    public void onDestroy(boolean isFinishing) {
        explorePresenter.onDestroy(isFinishing);
        if (isFinishing) {
            PresenterHolder.getInstance().remove(ExplorePresenter.class);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        PresenterHolder.getInstance().putPresenter(ExplorePresenter.class, explorePresenter);
        return super.onSaveInstanceState();
    }

    @Override
    public void setExploreItems(ExploreItem[] exploreItems) {
        recyclerView.setAdapter(new ExploreRecyclerViewAdapter(exploreItems));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(GONE);
    }

    @Override
    public void displayErrorMessage(String message) {
        homeView.displayErrorMessage(message);
    }

    @Override
    public void showLoadingError() {
        errorLoading.setVisibility(VISIBLE);
    }

    @OnClick(R.id.explore_loading_error)
    void reloadExploreFeed() {
        errorLoading.setVisibility(GONE);
        explorePresenter.loadExploreFeed();
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
