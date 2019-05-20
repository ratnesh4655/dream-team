package com.example.dreamteam.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.dreamteam.R;
import com.example.dreamteam.common.Utils;
import com.example.dreamteam.fragment.LoginFragment;
import com.example.dreamteam.fragment.RegistationFragment;

public class RegisterLoginActivty extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login_activty);
        toolbar = findViewById(R.id.toolBar);
        getToolbar();
        boolean isLogin = getIntent().getBooleanExtra("isLogin", false);
        if (isLogin)
            setLogInFragment();
        else setRegistrationFragment();
    }

    private void getToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            Drawable upArrow = getResources().getDrawable(R.drawable.back);
            upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(upArrow);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLogInFragment() {
        toolbar.setTitle("Login");
        Utils.replaceFragment(new LoginFragment()
                , R.id.framlayout
                , getSupportFragmentManager()
                , LoginFragment.TAG);
    }

    private void setRegistrationFragment() {
        toolbar.setTitle("Registration");
        Utils.replaceFragment(new RegistationFragment()
                , R.id.framlayout
                , getSupportFragmentManager()
                , RegistationFragment.TAG);
    }
}
