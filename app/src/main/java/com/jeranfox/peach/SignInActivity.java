package com.jeranfox.peach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {

    @Bind(R.id.sign_in_toolbar)
    Toolbar toolbar;

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
}
