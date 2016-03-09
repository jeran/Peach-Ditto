package com.jeranfox.peach;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class HomePagerAdapter extends PagerAdapter {
    private FriendsOfFriendsPage friendsOfFriendsPage;
    private FeedPage feedPage;

    public HomePagerAdapter(Context context) {
        friendsOfFriendsPage = new FriendsOfFriendsPage(context);
        feedPage = new FeedPage(context);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View page;
        if (position == 0) {
            page = friendsOfFriendsPage;
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
