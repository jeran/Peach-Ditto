package com.jeranfox.peach.presenters;

import com.jeranfox.peach.views.SignInView;

public interface SignInPresenter extends Presenter {

    void setView(SignInView view);

    void signIn(String userName, String password);

    void resetPassword();

    void releaseView();
}
