package com.jeranfox.peach.ui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jeranfox.peach.R;

public class FeedPage extends FrameLayout {
    public FeedPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_feed, this, true);
    }
}
