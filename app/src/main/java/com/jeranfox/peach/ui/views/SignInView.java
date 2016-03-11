package com.jeranfox.peach.ui.views;

import android.content.Context;

public interface SignInView {
    Context getContext();

    void openHomeActivity();

    void displayErrorMessage(String message);

    void enableSignInButton();

    void disableSignInButton();
}
