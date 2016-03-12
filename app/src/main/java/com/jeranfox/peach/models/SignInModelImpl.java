package com.jeranfox.peach.models;

import android.content.Context;

import com.jeranfox.peach.api.Peach;
import com.jeranfox.peach.api.response.SignInResponse;

import retrofit2.Callback;
import timber.log.Timber;

public class SignInModelImpl implements SignInModel {
    private final Peach peach;

    public SignInModelImpl(Context context) {
        peach = new Peach(context);
    }

    @Override
    public void cancelRequests() {
        peach.cancelRequests();
    }

    @Override
    public void signIn(String userName, String password, Callback<SignInResponse> callback) {
        peach.signIn(userName, password, callback);
    }

    @Override
    public void resetPassword() {
        // TODO(jeran): make network call to reset password
        Timber.i("Reset password clicked.");
    }
}
