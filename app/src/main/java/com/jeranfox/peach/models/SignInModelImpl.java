package com.jeranfox.peach.models;

import android.content.Context;

import com.jeranfox.peach.api.Peach;
import com.jeranfox.peach.api.response.SignInResponse;

import java.util.HashSet;
import java.util.Set;

import retrofit2.Callback;
import timber.log.Timber;

public class SignInModelImpl implements SignInModel {
    private final Set<Callback> callbacks = new HashSet<>();
    private final Peach peach;

    public SignInModelImpl(Context context) {
        peach = Peach.with(context);
    }

    @Override
    public void cancelRequests() {
        peach.cancelRequests(callbacks);
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
