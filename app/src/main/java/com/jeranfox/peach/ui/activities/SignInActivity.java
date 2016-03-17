package com.jeranfox.peach.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jeranfox.peach.PresenterHolder;
import com.jeranfox.peach.R;
import com.jeranfox.peach.presenters.SignInPresenter;
import com.jeranfox.peach.presenters.SignInPresenterImpl;
import com.jeranfox.peach.ui.views.SignInView;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements SignInView {

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

    private SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        signInPresenter = createPresenter();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.translate_in_from_left, R.animator.translate_out_to_right);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        PresenterHolder.getInstance().putPresenter(SignInPresenter.class, signInPresenter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signInPresenter.releaseView();
        if (isFinishing()) {
            PresenterHolder.getInstance().remove(SignInPresenter.class);
        }
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
            signInPresenter.signIn(userNameEditText.getText().toString(), passwordEditText.getText().toString());
        }
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

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void openHomeActivity() {
        startActivity(new Intent(SignInActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void displayErrorMessage(String message) {
        if (message == null) {
            message = invalidEmailPasswordPairString;
        }
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void enableSignInButton() {
        signInButton.setEnabled(true);
    }

    @Override
    public void disableSignInButton() {
        signInButton.setEnabled(false);
    }

    public SignInPresenter createPresenter() {
        SignInPresenter presenter = PresenterHolder.getInstance().getPresenter(SignInPresenter.class);
        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new SignInPresenterImpl(this);
        }
        return presenter;
    }

    class ResetPasswordDialog {
        @Bind(R.id.reset_password_dialog_email)
        EditText resetEmailEditText;

        @OnClick(R.id.reset_password_dialog_reset)
        void onResetPasswordClicked() {
            if (validateLocalField(resetEmailEditText)) {
                signInPresenter.resetPassword();
            }
        }
    }
}
