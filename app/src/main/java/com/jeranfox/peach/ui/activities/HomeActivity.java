package com.jeranfox.peach.ui.activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeranfox.peach.R;
import com.jeranfox.peach.ui.components.HomePagerAdapter;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private static final int EXPLORE_PAGE_INDEX = 0;
    private static final int FEED_PAGE_INDEX = 1;
    private static final float PAGER_BUTTON_SELECTED_ALPHA = 0.8f;
    private static final float PAGER_BUTTON_UNSELECTED_ALPHA = 0.3f;

    @Bind(R.id.home_view_pager)
    ViewPager viewPager;

    @Bind(R.id.home_gradient)
    View gradient;

    @Bind(R.id.home_explore_button)
    View exploreButton;

    @Bind(R.id.home_feed_button)
    View feedButton;

    @BindColor(R.color.bg_explore)
    int exploreBackgroundColor;

    @BindColor(R.color.bg_home_feed)
    int feedBackgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        viewPager.setAdapter(new HomePagerAdapter(this));
        viewPager.addOnPageChangeListener(new HomeOnPageChangeListener());
        openPage(FEED_PAGE_INDEX, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((HomePagerAdapter) viewPager.getAdapter()).onDestroy(isFinishing());
    }

    @OnClick(R.id.home_fab)
    void openPersonalFeed() {
        startActivity(new Intent(this, PersonalFeedActivity.class));
    }

    @OnClick(R.id.home_feed_button)
    void pageToFeed() {
        openPage(FEED_PAGE_INDEX, true);
    }

    @OnClick(R.id.home_explore_button)
    void pageToHome() {
        openPage(EXPLORE_PAGE_INDEX, true);
    }

    private void openPage(int page, boolean animate) {
        viewPager.setCurrentItem(page, animate);
        setPagerButtonColors(page);
    }

    private void setPagerButtonColors(int page) {
        exploreButton.setAlpha(page == EXPLORE_PAGE_INDEX ?
                PAGER_BUTTON_SELECTED_ALPHA : PAGER_BUTTON_UNSELECTED_ALPHA);
        feedButton.setAlpha(page == EXPLORE_PAGE_INDEX
                ? PAGER_BUTTON_UNSELECTED_ALPHA : PAGER_BUTTON_SELECTED_ALPHA);
    }

    private void setColorTheme(int color) {
        gradient.setBackgroundDrawable(new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0x00000000, color}));
        viewPager.setBackgroundColor(color);
    }

    private class HomeOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private ValueAnimator colorAnimator;

        public HomeOnPageChangeListener() {
            colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), exploreBackgroundColor, feedBackgroundColor);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffset != 0) {
                colorAnimator.setCurrentFraction(positionOffset);
                setColorTheme((int) colorAnimator.getAnimatedValue());
            }
        }

        @Override
        public void onPageSelected(int position) {
            setColorTheme(position == 0 ? exploreBackgroundColor : feedBackgroundColor);
            setPagerButtonColors(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
