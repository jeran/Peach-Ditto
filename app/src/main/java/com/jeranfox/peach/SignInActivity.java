package com.jeranfox.peach;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jeranfox.peach.api.Peach;
import com.jeranfox.peach.api.response.SignInResponse;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SignInActivity extends AppCompatActivity {

    @BindString(R.string.required)
    String requiredString;

    @BindString(R.string.invalid_email_password_pair)
    String invalidEmailPasswordPairString;

    @Bind(R.id.sign_in_root)
    View root;

    @Bind(R.id.sign_in_toolbar)
    Toolbar toolbar;

    @Bind(R.id.sign_in_button)
    Button signInButton;

    @Bind(R.id.sign_in_username)
    EditText userNameEditText;

    @Bind(R.id.sign_in_password)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.translate_in_from_left, R.animator.translate_out_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @OnClick(R.id.forgot_password)
    void onForgotPasswordClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.want_to_reset);
        builder.setView(R.layout.reset_password_dialog);
        AlertDialog dialog = builder.create();
        dialog.show();
        ButterKnife.bind(new ResetPasswordDialog(), dialog);
    }

    @OnClick(R.id.sign_in_button)
    void onSignInButtonClicked() {
        if (localValidation()) {
            signInButton.setEnabled(false);
            Peach.with(this).signIn(userNameEditText.getText().toString(), passwordEditText.getText().toString(), new Callback<SignInResponse>() {
                @Override
                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                    // TODO(jeran): open HomeActivity
                }

                @Override
                public void onFailure(Call<SignInResponse> call, Throwable t) {
                    String errorMessage = invalidEmailPasswordPairString;
                    if (t instanceof Peach.ApiException) {
                        errorMessage = t.getMessage();
                    }
                    displayError(errorMessage);
                    signInButton.setEnabled(true);
                }
            });
        }
    }

    private void displayError(String errorMessage) {
        Snackbar.make(root, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    private boolean localValidation() {
        return validateLocalField(userNameEditText) & validateLocalField(passwordEditText);
    }

    private boolean validateLocalField(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError(requiredString);
            return false;
        }
        return true;
    }

    class ResetPasswordDialog {
        @Bind(R.id.reset_password_dialog_email)
        EditText resetEmailEditText;

        @OnClick(R.id.reset_password_dialog_reset)
        void onResetPasswordClicked() {
            if (validateLocalField(resetEmailEditText)) {
                // TODO(jeran): make network call to reset password
                Timber.i("Reset password clicked.");
            }
        }
    }
}
