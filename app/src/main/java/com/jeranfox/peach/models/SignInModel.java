package com.jeranfox.peach.models;

import com.jeranfox.peach.api.response.SignInResponse;

import retrofit2.Callback;

public interface SignInModel {
    void cancelRequests();

    void signIn(String userName, String password, Callback<SignInResponse> callback);

    void resetPassword();
}
