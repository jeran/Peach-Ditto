package com.jeranfox.peach.presenters;

import com.jeranfox.peach.api.Peach;
import com.jeranfox.peach.api.response.SignInResponse;
import com.jeranfox.peach.views.SignInView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SignInPresenterImpl implements SignInPresenter {

    private SignInView signInView;

    @Override
    public void setView(SignInView view) {
        signInView = view;
    }

    @Override
    public void signIn(String userName, String password) {
        signInView.disableSignInButton();

        Peach.with(signInView.getContext()).signIn(userName, password, new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                signInView.openHomeActivity();
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                String errorMessage = null;
                if (t instanceof Peach.ApiException) {
                    errorMessage = t.getMessage();
                }
                signInView.displayErrorMessage(errorMessage);
                signInView.enableSignInButton();
            }
        });
    }

    @Override
    public void resetPassword() {
        // TODO(jeran): make network call to reset password
        Timber.i("Reset password clicked.");
    }
}
