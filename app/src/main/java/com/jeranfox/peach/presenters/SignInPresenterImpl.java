package com.jeranfox.peach.presenters;

import com.jeranfox.peach.api.Peach;
import com.jeranfox.peach.models.SignInModel;
import com.jeranfox.peach.models.SignInModelImpl;
import com.jeranfox.peach.api.response.SignInResponse;
import com.jeranfox.peach.views.SignInView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPresenterImpl implements SignInPresenter {

    private SignInView signInView;
    private SignInModel signInModel;

    public SignInPresenterImpl(SignInView signInView) {
        this.signInView = signInView;
        signInModel = new SignInModelImpl(signInView.getContext());
    }

    @Override
    public void setView(SignInView view) {
        signInView = view;
    }

    @Override
    public void signIn(String userName, String password) {
        signInView.disableSignInButton();

        Callback<SignInResponse> signInCallback = new Callback<SignInResponse>() {
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
        };

        signInModel.signIn(userName, password, signInCallback);
    }

    @Override
    public void resetPassword() {
        signInModel.resetPassword();
    }

    @Override
    public void releaseView() {
        setView(null);
        signInModel.cancelRequests();
    }
}
