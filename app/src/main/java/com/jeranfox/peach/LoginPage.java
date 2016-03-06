package com.jeranfox.peach;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class LoginPage extends FrameLayout {

    public LoginPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.page_login, this, true);
    }

    public LoginPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
