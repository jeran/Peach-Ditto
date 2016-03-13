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

    @Bind(R.id.home_view_pager)
    ViewPager viewPager;

    @Bind(R.id.home_gradient)
    View gradient;

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
        viewPager.setCurrentItem(1, false);
    }

    @OnClick(R.id.home_fab)
    void openPersonalFeed() {
        startActivity(new Intent(this, PersonalFeedActivity.class));
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
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
