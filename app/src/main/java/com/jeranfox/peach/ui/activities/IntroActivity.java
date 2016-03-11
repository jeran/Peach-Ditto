package com.jeranfox.peach.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeranfox.peach.R;
import com.jeranfox.peach.api.Peach;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Peach.with(this).signedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.intro_sign_up)
    void openSignUp() {
        // TODO(jeran): open sign up activity
        // openActivity(SignUpActivity.class);
    }

    @OnClick(R.id.intro_sign_in)
    void openSignIn() {
        openActivity(SignInActivity.class);
    }

    @OnClick(R.id.intro_privacy_policy_and_terms)
    void openPrivacyPolicyAndTerms() {
        // TODO(jeran): open privacy policy and terms in web view
    }

    private void openActivity(Class activity) {
        startActivity(new Intent(this, activity));
        overridePendingTransition(R.animator.translate_in_from_right, R.animator.translate_out_to_left);
    }
}
