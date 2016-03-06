package com.jeranfox.peach;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Implementation of a PagerAdapter used to display the intro and login pages.
 * https://www.bignerdranch.com/blog/viewpager-without-fragments/
 */
public class LoginPagerAdapter extends PagerAdapter {
    private Context context;

    public LoginPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View page;
        if (position == 0) {
            page = new IntroPage(context);
        } else {
            page = new LoginPage(context);
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
