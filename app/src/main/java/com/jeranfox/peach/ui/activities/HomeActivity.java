package com.jeranfox.peach.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jeranfox.peach.ui.components.HomePagerAdapter;
import com.jeranfox.peach.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.home_view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        viewPager.setAdapter(new HomePagerAdapter(this));
    }

    @OnClick(R.id.home_fab)
    void openPersonalFeed() {
        startActivity(new Intent(this, PersonalFeedActivity.class));
    }
}
