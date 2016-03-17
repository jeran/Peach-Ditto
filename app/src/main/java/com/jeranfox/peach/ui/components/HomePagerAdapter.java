package com.jeranfox.peach.ui.components;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.jeranfox.peach.ui.views.HomeView;

public class HomePagerAdapter extends PagerAdapter {
    private ExplorePage explorePage;
    private FeedPage feedPage;

    public HomePagerAdapter(Context context, HomeView homeView) {
        explorePage = new ExplorePage(context, homeView);
        feedPage = new FeedPage(context, homeView);
    }

    public void onDestroy(boolean isFinishing) {
        explorePage.onDestroy(isFinishing);
        feedPage.onDestroy(isFinishing);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View page;
        if (position == 0) {
            page = explorePage;
        } else {
            page = feedPage;
        }
        collection.addView(page);
        return page;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
