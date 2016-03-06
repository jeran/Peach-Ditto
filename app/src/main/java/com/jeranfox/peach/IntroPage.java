package com.jeranfox.peach;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class IntroPage extends FrameLayout {

    public IntroPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_intro, this, true);
    }

    public IntroPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IntroPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
